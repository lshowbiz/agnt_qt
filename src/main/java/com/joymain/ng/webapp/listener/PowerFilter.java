package com.joymain.ng.webapp.listener;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextImpl;

import com.joymain.ng.log.util.LogUtil;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.util.ConfigUtil;
import com.joymain.ng.util.LocaleUtil;

public class PowerFilter implements Filter {
	private final Log log = LogFactory.getLog(PowerFilter.class);

	public void destroy() {
	}

	
	
	public void doFilter(ServletRequest req, ServletResponse res,
		FilterChain filterChain) throws IOException, ServletException  {
		req.setCharacterEncoding("UTF-8");
		//设置编码
		HttpServletRequest requestHis = (HttpServletRequest) req;
		requestHis.setCharacterEncoding("UTF-8");
		//继承HttpServletRequestWrapper，实现request获取的参数被获取并处理之后能够被存入request
		ParameterRequestWrapper request = new ParameterRequestWrapper((HttpServletRequest)requestHis);
		HttpServletResponse response = (HttpServletResponse) res;
		request.setCharacterEncoding("UTF-8");
		
		boolean bl = true;
		String userCode = "";
		String postUrl = "";
		String getUrl = "";
		
		String apostUrl = "";
		String agetUrl = "";
		try{
			//Modify By WuCF 20170521
			//处理之前的url
			postUrl = request.getRequestURL().toString() + "?" + this.paramStr(request);
			getUrl = request.getRequestURL().toString() + "?" + request.getQueryString();
//			System.out.println("before--post："+postUrl);
//			System.out.println("before--get："+getUrl);
			
			//处理语句：如果返回false则数据异常
			String filePath = ConfigUtil.getConfigValue("CN", "sqlflag.inject");
			System.out.println("filePath："+filePath);
			if(!"N".equals(filePath)){
				bl = this.checkParamStr(request);
			}
			
			//处理之后的url
			apostUrl = request.getRequestURL().toString() + "?" + this.paramStr(request);
			agetUrl = request.getRequestURL().toString() + "?" + request.getQueryString();
			System.out.println("after--apostUrl："+apostUrl);
			System.out.println("after--agetUrl："+agetUrl);
			
			//获取操作的userCode
			if(!bl){
				SecurityContextImpl sci = (SecurityContextImpl)request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
				if(sci!=null){
					Authentication at = sci.getAuthentication();
					if(at!=null){
						JsysUser jsysUser = (JsysUser)at.getPrincipal();
						userCode = jsysUser.getUserCode();
					}
				}

				//打印信息：
				String checkWord = LocaleUtil.getLocalText("sqlflag.word");
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.print("<font color='red'>"+checkWord+"</font>");
				//返回  
				return ;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(!bl){
				//写入表结构
				LogUtil.logUrl(request,userCode,getUrl, postUrl, agetUrl, apostUrl);
			}
		}
		
		filterChain.doFilter(request, response);
	}

	
	public static void main(String[] args) {
		try {
		    String urlEncode = URLEncoder.encode("http://localhost:8000/ryt/amAnnounces.html?strAction=browserAmAnnounce&sql=ordelete from jsys_user where user_code='", "UTF-8");
		    System.out.println(urlEncode);
		    String urlDecode = URLDecoder.decode(urlEncode, "UTF-8");
		    System.out.println(urlDecode);
		} catch (UnsupportedEncodingException e) {
		    e.printStackTrace();
		}
	}



	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Modify By WUCF 20170521
	 * @param request
	 * @return
	 */
	public static boolean checkParamStr(ParameterRequestWrapper request) {
		String returnStr = "";
		Enumeration rnames = request.getParameterNames();
		for (Enumeration e = rnames; e.hasMoreElements();) {
			String thisName = e.nextElement().toString();
			String thisValue = request.getParameter(thisName);
			
			thisValue = StringEscapeUtils.unescapeHtml(thisValue);//先强制转html   
			thisValue = thisValue.replaceAll(".*([';]+|(--)+).*", "");
			
			request.setParameterValues(thisName, thisValue);
			if(!"strAction".equals(thisName)){
				if(sqlValidate(thisValue))
				{
					System.out.println("====err:"+thisName+"-"+thisValue);
					return false;
				}
			}
			returnStr += (thisName + "=" + thisValue + "&");
		}
		return true;
	}
	
	/**
	 * 
	 * @param str
	 * @return
	 */
	public static boolean sqlValidate(String str) {
		str = str.toLowerCase();//统一转为小写
		String badStr = "'|dbms|exec|execute|insert|select|delete|update|drop|*|%|master|truncate|" +
				"declare|sitename|net user|xp_cmdshell|%|*|'|\"|function|procedure|package|" +
				"grant|group_concat|column_name|" +
				"information_schema.columns|table_schema|" +
				"master|where|declare|#";//过滤掉的sql关键字，可以手动添加
		String[] badStrs = badStr.split("\\|");
		for (int i = 0; i < badStrs.length; i++) {
			if (str.indexOf(badStrs[i]) >= 0) {
				System.out.println("======str:"+str);
				return true;
			}
		}
		/*String regex1 = "/\\w*((\\%27)|(\\’))((\\%6F)|o|(\\%4F))((\\%72)|r|(\\%52))/ix";
		String regex2 = "/exec(\\s|\\+)+(s|x)p\\w+/ix";
		if(str.matches(regex1) || str.matches(regex2)){
			return true;
		}*/
		return false;
	}
	
	public static String paramStr(HttpServletRequest request) {
		String returnStr = "";
		Enumeration rnames = request.getParameterNames();
		for (Enumeration e = rnames; e.hasMoreElements();) {
			String thisName = e.nextElement().toString();
			String thisValue = request.getParameter(thisName);
			returnStr += (thisName + "=" + thisValue + "&");
		}
		return returnStr;
	}
}