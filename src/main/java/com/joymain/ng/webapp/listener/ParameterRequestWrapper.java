package com.joymain.ng.webapp.listener;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import java.util.HashMap;  

public class ParameterRequestWrapper extends HttpServletRequestWrapper {

	private Map<String , String[]> params = new HashMap<String, String[]>();  
	  
	  
    @SuppressWarnings("unchecked")  
    public ParameterRequestWrapper(HttpServletRequest request) {  
        // 将request交给父类，以便于调用对应方法的时候，将其输出，其实父亲类的实现方式和第一种new的方式类似  
        super(request);  
        //将参数表，赋予给当前的Map以便于持有request中的参数  
        this.params.putAll(request.getParameterMap());  
    }  
    //重载一个构造方法  
    public ParameterRequestWrapper(HttpServletRequest request , Map<String , Object> extendParams) {  
        this(request);  
    }  
      
    @Override  
    public String getParameter(String name) {//重写getParameter，代表参数从当前类中的map获取  
        String[]values = params.get(name);  
        if(values == null || values.length == 0) {  
            return null;  
        }else if(values.length>1){
        	if("strAction".equals(name)){
        		return values[0];
        	}else{//如果是多个值，则返回数组的字符串
	        	String strs = "";
	        	for(int i=0;i<values.length;i++){
	        		strs += ","+ values[i];
	        	}
	        	return strs.substring(1);
        	}
        }
        return values[0];
    }  
      
    public String[] getParameterValues(String name) {//同上  
         return params.get(name);
    }  
    
    public void setParameterValues(String name , Object value)
    {
    	params.remove(name);
    	if(value != null) {  
            if(value instanceof String[]) {  
                params.put(name , (String[])value);  
            }else if(value instanceof String) {
            	//如果是字符串的数组，需要重新拆分为数组返回
            	if(((String) value).split(",").length>=2){
            		params.put(name , ((String) value).split(","));  
            	}else{
            		params.put(name , new String[] {(String)value});  
            	}
            }else {  
                params.put(name , new String[] {String.valueOf(value)});  
            }  
        }  
    }
}
