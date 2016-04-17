package com.benson.graduate.base.pagemodel;

/**
 * JSON模型
 * 
 * @author Benson
 * 
 */
public class Json implements java.io.Serializable {

	private static final long serialVersionUID = 2066919327297059677L;
	
	private boolean success = false;// 是否成功
	private String msg = "";// 提示信息
	private Object obj = null;// 其他信息

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

}
