package com.weasleyclock.linebot.exception;

public class WeasleyClockAppException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public WeasleyClockAppException() {
		super("WeasleyClockAppException");
	}
	public WeasleyClockAppException(String msg) {
		super(msg);
	}
	public WeasleyClockAppException(Throwable ex) {
		super(ex);
	} 
}