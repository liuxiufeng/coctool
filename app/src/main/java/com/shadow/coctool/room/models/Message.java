package com.shadow.coctool.room.models;

public class Message {
	
	public static final int JOIN = 0;
	
	public static final int LEAVE = 1;
	
	public static final int TLAK = 2;
	
	public static final int INFO = 3;
	
	private int type;
	
	private String content;

	public Message() {

	}

	public Message(int type, String content) {
		this.type = type;
		this.content = content;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
