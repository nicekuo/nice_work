package com.core.bean;

public class VersionListBean extends BaseBean{
	private static final long serialVersionUID = 7100465559042745268L;
	private VersionBean data;
	
	private int version=1;//版本      相当于是version_code
	private String versionName=""; //版本名称    相当于是version_name
	private String url="";//更新地址
	private String description="";//更新描述
	private String update_time="";//应用上次更新时间
	private int force=-1;
	public int getForce() {
		return force;
	}

	public void setForce(int force) {
		this.force = force;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getVersionName() {
		return versionName;
	}

	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}


	
	
	public VersionBean getData() {
		return data;
	}

	public void setData(VersionBean data) {
		this.data = data;
	}
}
