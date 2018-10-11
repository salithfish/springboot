package com.fang.ychat.vo;

/**
 * vo类，用于在展示层存储用户的姓名和昵称
 */
public class BaseUser {
	private String name;
	private String nickName;

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public BaseUser(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
