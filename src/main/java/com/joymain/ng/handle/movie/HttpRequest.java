package com.joymain.ng.handle.movie;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.joymain.ng.util.chanjet.Md5;

public class HttpRequest {
	
	private ArrayList<Param> _params = null;
	private String _url, _md5Key, _session;
	private int _channelId;
 
	public ArrayList<Param> params() { return _params; }
	public String url() { return _url; }
	public String md5Key() { return _md5Key; }
	public String session() { return _session; }
	public int channelId() { return _channelId; }

	public HttpRequest (String url, String md5Key, int channelId) {
		_url = url;
		_md5Key = md5Key;
		_channelId = channelId;
	}
	
	public HttpRequest (String url, String md5Key, String session, int channelId) {
		_url = url;
		_session = session;
		_md5Key = md5Key;
		_channelId = channelId;
	}
	
	public void addParam (Param param) {
		if (param == null) {
			return;
		}
		if (_params == null) {
			_params = new ArrayList<Param>();
		}
		_params.add(param);
	}
	
	public boolean appendVerifyInfo () {
		
		StringBuilder strToVerfy = new StringBuilder();

		//addParam(new Param("time_stamp", System.currentTimeMillis()));
		addParam(new Param("time_stamp", "1380162605"));

		if (_params.size() > 0) {
			Collections.sort(_params, new Comparator<Param>() {
				public int compare(Param o1, Param o2) {
					return o1.key().compareTo(o2.key());
				}
			});

			for (Param param : _params) {
				strToVerfy.append(param.value());
			}
			
			strToVerfy.append(md5Key());
		}

		String finalToMD5 = null;
		try {
			System.out.println(strToVerfy);
			finalToMD5 = URLEncoder.encode(strToVerfy.toString(), "utf-8");
			System.out.println(finalToMD5.toString());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		String enc = null;
		if (finalToMD5 != null) {
			enc = Md5.Md5(finalToMD5);
			addParam(new Param("enc", enc));
		}
		
		return enc == null;
	}
	
	public String getRequest (int timeoutSecs) throws Exception {
		
		appendVerifyInfo();
		StringBuffer finalUrl = new StringBuffer(_url);
		if (_params.size() > 0) {
			finalUrl.append('?');
			for (Param param : _params) {
				finalUrl.append(param.key()).append('=').append(param.urlValue()).append('&');
			}
			finalUrl.deleteCharAt(finalUrl.length()-1);
		}
		
		System.out.println(finalUrl.toString());
		
		URL Url = new URL(finalUrl.toString());
		HttpURLConnection conn = (HttpURLConnection)Url.openConnection();
		conn.setConnectTimeout(20*1000);
		conn.setRequestProperty("channel_id", channelId()+"");
		if ( session()!=null ) {
			conn.setRequestProperty("session_id", session());
		}
		conn.setReadTimeout(timeoutSecs*1000);
		
		if (conn.getResponseCode() == 200) {
			InputStream input = conn.getInputStream();
			byte[] a = new byte[1024];
			int length = 0;
			ByteArrayOutputStream out=new ByteArrayOutputStream();
			while((length = input.read(a,0,1024))>0) {
				out.write(a,0,length);
			}
			String s = new String(out.toByteArray(),"utf-8");
			return s.trim();
		}
		return "";
	
	}
	
	public String postRequest (int timeoutSecs) {
		InputStream is = null;
		String str = null;
		try{
			appendVerifyInfo();

			URL Url = new URL(_url);
			HttpURLConnection conn = (HttpURLConnection) Url.openConnection();
			conn.setConnectTimeout(20*1000);
			conn.setReadTimeout(timeoutSecs*1000);
			
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			if ( session()!=null ) {
				conn.setRequestProperty("session_id", session());
			}
			conn.setRequestProperty("Proxy-Connection", "Keep-Alive");
//			conn.setUseCaches(false);
//			conn.setRequestProperty("Content-type","text/xml");
			conn.connect();
			
			
			if (_params.size() > 0) {
				DataOutputStream out = new DataOutputStream(conn.getOutputStream());
				StringBuffer content = new StringBuffer();
				//content.append('?');
				for (Param param : _params) {
					content.append(param.key()).append('=').append(param.value()).append('&');
				}
				out.write(content.toString().getBytes("utf-8"));
				out.flush();
				out.close();
			}
			
			is = conn.getInputStream();	
			byte[] a = new byte[1024];
			int length = 0;
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			while ((length = is.read(a, 0, 1024)) > 0) {
				os.write(a, 0, length);
			}
			str = new String(os.toByteArray(), "utf-8");

			return str.trim();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return str;
	}

	public static void main (String[] args) {
		
	}
}
