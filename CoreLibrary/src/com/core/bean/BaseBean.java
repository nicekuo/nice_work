package com.core.bean;

import java.io.Serializable;

public class BaseBean implements Serializable{
	private static final long serialVersionUID = 5064565652428690188L;
	private int isSuccess;  //请求后状态标识码   
    
	public String msg="";
	public int getIsSuccess() {
		return isSuccess;
	}

	public void setIsSuccess(int isSuccess) {
		this.isSuccess = isSuccess;
	}

	@Override
	public String toString() {
		return "BaseBean [isSuccess=" + isSuccess + "]";
	}


	public String error_info="";//错误信息

	public String m="";

	public int result=1;  //状态码
}
