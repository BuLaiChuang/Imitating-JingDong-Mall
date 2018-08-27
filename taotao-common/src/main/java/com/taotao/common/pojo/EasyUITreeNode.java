package com.taotao.common.pojo;

import java.io.Serializable;

public class EasyUITreeNode implements Serializable {

	private long id;//目录的id
	private String text;//目录的名称
	private String state;//目录的状态，状态是否为父级目录，为1就表示父级目录，为0表示普通
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}

	
}
