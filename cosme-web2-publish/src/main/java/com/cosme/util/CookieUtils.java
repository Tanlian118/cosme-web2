package com.cosme.util;

import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CookieUtils {

	private String domain;
	private int expireSeconds;
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	public CookieUtils(HttpServletRequest request, HttpServletResponse response, String domain){
		this.domain = domain;
		this.request = request;
		this.response = response;
	}
	
	public String getCurrentDomain(){
		return this.domain;
	}
	
	public HttpServletRequest getCurrentRequest(){
		return this.request;
	}
	
	public HttpServletResponse getCurrentResponse(){
		return this.response;
	}
	
	public static String getDomainFromServerName(String serverName) {
		if(serverName.endsWith(".com.cn")) {
			int index = serverName.lastIndexOf(".", serverName.length() - ".com.cn".length() - 1);
			if(index == -1) {
				return "." + serverName;
			}
			return serverName.substring(index);
		}
		Pattern pattern = Pattern.compile("\\.{0,1}\\w*?\\.\\w*?$");
		Matcher m = pattern.matcher(serverName);
		if(m.find()){
			String domain = m.group();
			if(domain.indexOf(".") != 0){
				domain = "."+domain;
			}
			return domain;
		}else{
			int index = serverName.indexOf(".");
			return serverName.substring(index);
		}
	}

	public void setDomain(String domain){
		this.domain = domain;
	}
	
	public void setExpireTimeBySeconds(int seconds){
		this.expireSeconds = seconds;
	}

	public void addCookie(String name, String value){
	    Cookie cookie = new Cookie(name, value);
	    cookie.setDomain(this.domain);
	    cookie.setMaxAge(this.expireSeconds);
	    cookie.setPath("/");
	    this.response.addCookie(cookie);
    }

		public String getCookieValue(String key){
		if(!StringUtils.hasText(key)){
			return "";
		}
		Cookie[] cookies = request.getCookies();
		if (cookies != null){
			for (int i = 0, total = cookies.length; i < total; i++){
				if(key.equalsIgnoreCase(cookies[i].getName())){
					String value = cookies[i].getValue();
					return value;
				}
			}
		}
		return "";
	}
	
	public void delCookie(String key){
	    Cookie cookie = new Cookie(key, null);
	    cookie.setDomain(this.domain);
	    cookie.setMaxAge(0);
	    cookie.setPath("/");
	    response.addCookie(cookie);
    }
	
	
	public String getOSInfo() {
		StringBuffer osinfo = new StringBuffer();
		osinfo.append(request.getHeader("user-agent"));

		return osinfo.toString();
	}
	
}
