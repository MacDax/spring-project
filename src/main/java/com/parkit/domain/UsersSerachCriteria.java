package com.parkit.domain;

import org.springframework.stereotype.Component;

@Component
public class UsersSerachCriteria {
	
	private String whereto;
	
	public UsersSerachCriteria() {}

	public String getWhereto() {
		return whereto;
	}

	public void setWhereto(String whereto) {
		this.whereto = whereto;
	}
	
	

}
