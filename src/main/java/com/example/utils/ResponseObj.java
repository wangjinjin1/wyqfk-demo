package com.example.utils;

public class ResponseObj {
	
	private Integer status;
	
	private Object obj;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}
	
	public ResponseObj(){
		super();
	}
	
	public ResponseObj(Integer status,Object obj){
		super();
		this.status=status;
		this.obj=obj;
	}
}
