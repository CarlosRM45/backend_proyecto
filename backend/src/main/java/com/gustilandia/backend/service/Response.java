package com.gustilandia.backend.service;

public class Response {

    public Response(){}

	public Response(boolean success, Object result, String message) {
        
        this.success = success;
        this.result = result;
        this.message = message;
    }

    private boolean success;
	private Object result;
	private String message;


	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public Object getResult() {
		return result;
	}
	public void setResult(Object result) {
		this.result = result;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
