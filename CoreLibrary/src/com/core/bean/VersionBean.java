package com.core.bean;
/**
 * @describe 版本控制器model
 * @author sufun
 * @time 2015年5月6日 11:02:09
 * 
 */
public class VersionBean {
	private String message;//更新提示，可以用作更新说明
	private int force;  //布尔值 ，表示是否强制用户更新版本；
	private String download_url;
	private int version_code;//最新版本号     "Android 中的version_code   " <---------(android版本的version_name)
	private String version_name;//android版本的  "Android中的version_name"<-------------version_code
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getForce() {
		return force;
	}
	public void setForce(int force) {
		this.force = force;
	}
	public String getDownload_url() {
		return download_url;
	}
	public void setDownload_url(String download_url) {
		this.download_url = download_url;
	}
	public int getVersion_code() {
		return version_code;
	}
	public void setVersion_code(int version_code) {
		this.version_code = version_code;
	}
	public String getVersion_name() {
		return version_name;
	}
	public void setVersion_name(String version_name) {
		this.version_name = version_name;
	}
}
