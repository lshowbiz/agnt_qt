package com.joymain.ng.util;
/*----------------------------------------------------------------
//Copyright (C) 2013 JM International 
//版权所有。 
//
//文件名：GroupPage.java
//文件功能描述：会员中心分页类封装
//
//
//创建者：WuCF
//创建时间：2013-11-22
//----------------------------------------------------------------*/
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public class GroupPage {
	private int pagenum = 1;//当前页码
	private int pagecount;//总记录数
	private String pageInfo;//页面展示的“首页 上一页 下一页 尾页”
	private int pages;//总页数
	private int pagesize = 10;//每页显示记录数
	
	private List dataList = new ArrayList();//当前页面的数据集合List
	
	private String requestMapping;//连接的URL对应一级目录，如果没有则默认为空字符串""
	private String path;//连接URL对应的方法名

	
	public GroupPage() {

	}
	
	public GroupPage(String requestMapping,String path,int pagesize,HttpServletRequest request){
		this.requestMapping = requestMapping;
		this.path = path;
		this.pagesize = pagesize;
		String pageNum = request.getParameter("pageNum");
		if(StringUtil.isInteger(pageNum)){
			this.setPagenum(Integer.parseInt(pageNum));
		}
	}

	public GroupPage(int pagenum, int pagecount, int pagesize, String path) {
		this.pagenum = pagenum;
		this.pagecount = pagecount;
		this.pagesize = pagesize;
		this.path = path;
	}

	public GroupPage(int pagenum, int pagecount, String path) {
		this.pagenum = pagenum;
		this.pagecount = pagecount;
		this.path = path;
	}

	/**
	 * 后台分页
	 * 
	 * @return
	 */
	public String getPageInfo() {
		
		//=== start 如果没有数据，不展示分页框架 add by hdg 2016-09-18 ====
		if(pagecount == 0) {
			return "";
		}
		// ======================= end ==================
		//真实的路径地址路径地址：
		//一级目录+二级目录   (一级目录可能为空)
		String pathUrl = ""; 
		if(StringUtil.isEmpty(requestMapping)){
			pathUrl = "/"+path;
		}else{
			pathUrl = "/"+requestMapping+"/"+path;
		}
		
		String pageUp = "";
		String pageDown = "";
		String pageFirst = "";
		String pageLast = ""; 
		
		// 用于分页中间的那些数字链接 add by hdg 2016-09-18
		String pageHref = "";

		if (null != path && !"".equals(path)) {
			if (path.indexOf("?") > -1) {
				
				pageUp = path + "&pageNum=" + (this.pagenum - 1);
				pageDown = path + "&pageNum=" + (this.pagenum + 1);
				pageFirst = path + "&pageNum=1";
				pageLast = path + "&pageNum=" + (this.pages );
				
				pageHref =  path + "&pageNum=";
				path += "&";
			} else {
				pageUp = path + "?pageNum=" + (this.pagenum - 1);
				pageDown = path + "?pageNum=" + (this.pagenum + 1);
				pageFirst = path + "?pageNum=1";
				pageLast = path + "?pageNum=" + (this.pages );
				pageHref =  path + "?pageNum=";
				path += "?";
			}
		}
		
		//分页代码
		//StringBuffer pageinfo = new StringBuffer("<table width='100%' class='tabPages'><tr>  ");
		/*
		pageinfo.append("<td style='height:26px;line-height:26px;text-align:center;'><span>共<em class='red'>");
		pageinfo.append(this.pagecount + "</em>条记录&nbsp;&nbsp;</span>");
		pageinfo.append("<td style='height:26px;line-height:26px;text-align:center;'>");
		*/
		//pageinfo.append("<td style='height:26px;line-height:26px;text-align:center;'>");

		StringBuffer pageinfo = new StringBuffer("<div id='kkpager' class='mt fr'><div><span class='pageBtnWrap'>");
		// =================== start modify by hdg 2016-09-18 ============================
		if (this.pagenum > 1) {
			if(pagenum == 1) {
				pageinfo.append("<span class='disabled'>首页</span>");
				pageinfo.append("<span class='disabled'>上一页</span>");
			} else {
				pageinfo.append("<a href='" + pageFirst +"'>首页</a>");
				pageinfo.append("<a href='" + pageUp +"'>上一页</a>");
			}
		} else {
			pageinfo.append("<span class='disabled'>首页</span>");
			pageinfo.append("<span class='disabled'>上一页</span>");
		}
		if(pages <= 10){
            for(int i = 1;i<=pages;i++){
                if(pagenum == i){
                	//当前页不给超链接
                	pageinfo.append("<span class='curr'>"+i+"</span>");
                }else{
                	pageinfo.append("<a href='" + pageHref + i +"'>"+i+"</a>");
                }
            }
        } else {
        	if(pagenum<=4){
                for(int i = pagenum-1;i>=(pagenum-1);i--){
                	if(i != 0) {
                		pageinfo.append("<a href='" + pageHref + i +"'>"+i+"</a>");
                	}
                }
                //当前页不给超链接
                pageinfo.append("<span class='curr'>"+pagenum+"</span>");
                pageinfo.append("<a href='" + pageHref + (pagenum+1) +"'>"+(pagenum+1)+"</a>");
                pageinfo.append("<a href='" + pageHref + (pagenum+2) +"'>"+(pagenum+2)+"</a>");
                pageinfo.append("<a href='" + pageHref + (pagenum+3) +"'>"+(pagenum+3)+"</a>");
                if(pagenum == 1) {
                	pageinfo.append("<a href='" + pageHref + (pagenum+4) +"'>"+(pagenum+4)+"</a>");
                }
                pageinfo.append("<span class='normalsize'>……</span>");
                pageinfo.append("<a href='" + pageHref + pages +"'>"+pages+"</a>");
            }
        	//5个页数
        	if(pagenum>4 && (pagenum<=pages-3)){
        		/*pageinfo.append("<a href='" + pageHref + 1 + "'>"+1+"</a>");
        		pageinfo.append("<span class='normalsize'>……</span>");*/
        		pageinfo.append("<a href='" + pageHref + (pagenum-2) + "'>"+(pagenum-2)+"</a>");
                pageinfo.append("<a href='" + pageHref + (pagenum-1) + "'>"+(pagenum-1)+"</a>");
                pageinfo.append("<span class='curr'>"+pagenum+"</span>");
                pageinfo.append("<a href='" + pageHref + (pagenum+1) +"'>"+(pagenum+1)+"</a>");
                pageinfo.append("<a href='" + pageHref + (pagenum+2) +"'>"+(pagenum+2)+"</a>");
                if((pagenum+1) != pages && (pagenum+3) != pages) {
                	pageinfo.append("<span class='normalsize'>……</span>");
                }
                pageinfo.append("<a href='" + pageHref + pages +"'>"+pages+"</a>");
            }
        	if(pagenum>pages-3){
        		pageinfo.append("<a href='" + pageHref + 1 + "'>"+1+"</a>");
        		pageinfo.append("<span class='normalsize'>……</span>");
        		if(pagenum == pages) {
        			pageinfo.append("<a href='" + pageHref + (pagenum-4) + "'>"+(pagenum-4)+"</a>");
        			pageinfo.append("<a href='" + pageHref + (pagenum-3) + "'>"+(pagenum-3)+"</a>");
        		}
        		if((pagenum+1) == pages) {
        			pageinfo.append("<a href='" + pageHref + (pagenum-3) + "'>"+(pagenum-3)+"</a>");
        		}
        		pageinfo.append("<a href='" + pageHref + (pagenum-2) + "'>"+(pagenum-2)+"</a>");
                pageinfo.append("<a href='" + pageHref + (pagenum-1) + "'>"+(pagenum-1)+"</a>");
                pageinfo.append("<span class='curr'>"+pagenum+"</span>");
                for(int i = pagenum+1;i<=pages;i++){
                	pageinfo.append("<a href='" + pageHref + i +"'>"+i+"</a>");
                }
            }
        }
		if (this.pagenum < this.pages && this.pages > 1) {
			pageinfo.append("<a href='" + pageDown +"'>下一页</a>");
			pageinfo.append("<a href='" + pageLast +"'>末页</a>");
		} else {
			pageinfo.append("<span class='disabled'>下一页</span>");
			pageinfo.append("<span class='disabled'>末页</span>");
		}
		pageinfo.append("</span></div></div>");
		// =================== end modify by hdg 2016-09-18 ============================
		/*if(this.pagecount==0) {
			pageinfo.append("<span>第<em class='red'>");
			pageinfo.append("0</em><b>/0</b>页</span>");
		}else{
			pageinfo.append("<span>第<em class='red'>");
			pageinfo.append(this.pagenum);
			pageinfo.append("</em><b>/");
			pageinfo.append(pages);
			pageinfo.append("</b>页</span>");
		}	
		pageinfo.append("<span>&nbsp;转到：</span>");
		pageinfo.append("<input name='jumpPagenum' id='jumpPagenum' type='text' onfocus='javascript:this.value=\"\"' style='width:40px;' value='   页' onkeydown='if((event.keyCode<96 || event.keyCode>105) && (event.keyCode<48 || event.keyCode>57) && event.keyCode!=8 && event.keyCode!=13 && event.keyCode!=46 && (event.keyCode<37 || event.keyCode>40)){return false;}'/> ");
		pageinfo.append("<input name='button12' type='button' value='跳转!' onclick='jumpPagenum123();' class='btn_common btn_mini corner2' style='display:inline-block;'>");
		pageinfo.append("<span>&nbsp;&nbsp;每页显示：</span><select onchange='tiaoZhuanSelect(this.value);' class='mySelect' name='pageSelect' id='pageSelectId'><option value='20' ");
		
		if(this.pagesize==20){
			pageinfo.append(" selected=\"selected\" ");
		}
		pageinfo.append(" >20</option><option value='50' ");
		if(this.pagesize==50){
			pageinfo.append(" selected=\"selected\" ");
		}
		pageinfo.append(" >50</option><option value='100' ");
		if(this.pagesize==100){
			pageinfo.append(" selected=\"selected\" ");
		}
		pageinfo.append(">100</option></select><span>&nbsp;条</span></td></tr></table>");
		pageinfo.append("<script>var pathName=window.document.location.pathname;var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);function jumpPagenum123(){var jumpPagenum = document.getElementById('jumpPagenum').value;if(jumpPagenum == ");
		pageinfo.append((this.pagenum ));
		pageinfo.append("){return;}if(isNaN(jumpPagenum)){alert('输入的页码应该为数字!');return;}if(jumpPagenum<=0){alert('输入的页码应大于0');return;}if(jumpPagenum >parseInt("+pages+")){alert('输入的页数不能大于最大页数："+pages+"');return;}location.href= projectName+'");
		pageinfo.append(pathUrl);
		pageinfo.append("' + '&pageNum='+(jumpPagenum);}");
		pageinfo.append(" function tiaoZhuanSelect(pz){if(pz!=null && pz!=''){location.href = projectName+'");
		if(pathUrl!=null && !pathUrl.contains("?")){
			pathUrl = pathUrl+"?1=1";
		}
		pageinfo.append(pathUrl);
		pageinfo.append("&pageNum=1&pageSizeSelect='+pz+'&pageNum=");
		pageinfo.append(pagenum);
		pageinfo.append("';}}");
		pageinfo.append("</script>"); */
		//pageinfo.append("</td></tr></table>");
		//System.out.println(pageinfo.toString());
		return pageinfo.toString();
	}

	public int getPages() {
		return pages;
	}

	public int getPagenum() {
		return pagenum;
	}

	public void setPagenum(int pagenum) {
		this.pagenum = pagenum;
	}

	public int getPagecount() {
		return pagecount;
	}

	public void setPagecount(int pagecount) {
		this.pagecount = pagecount;

		this.pages = this.pagecount % this.pagesize == 0 ? this.pagecount
				/ this.pagesize : this.pagecount / this.pagesize + 1;

/*		if (this.pagenum >= pages) {
			this.pagenum = pages - 1;
		}

		if (pagenum < 0) {
			this.pagenum = 0;
		}
		
		if (pages < 1) {
			this.pages = 1;
		}*/
		if (pagenum < 1) {
			this.pagenum = 1;
		}
/*		if(pagenum>pages){
			pagenum=pages;
		}*/
	}

	public int getPagesize() {
		return pagesize;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public List getDataList() {
		return dataList;
	}

	public void setDataList(List dataList) {
		this.dataList = dataList;
	}

	public void setPageInfo(String pageInfo) {
		this.pageInfo = pageInfo;
	}

	public String getRequestMapping() {
		return requestMapping;
	}

	public void setRequestMapping(String requestMapping) {
		this.requestMapping = requestMapping;
	}
}