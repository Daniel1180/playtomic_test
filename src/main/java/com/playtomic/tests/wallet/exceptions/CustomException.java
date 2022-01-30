package com.playtomic.tests.wallet.exceptions;

public class CustomException extends Exception{

	private static final long serialVersionUID = 1L;
	
    private int errorCode;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

	public CustomException(){
        super();
    }

    public CustomException(String message, int errorCode){
        super(message);
        this.errorCode = errorCode;
    }

    public CustomException(Exception e){
        super(e);
    }
}
