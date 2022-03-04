package com.myqa.project.DTOs;


public class LoadStockRequest {
	private String type;
	private int amount;
	public String getType() {
		return type;
	}
	public int getAmount() {
		return amount;
	}
	public LoadStockRequest() {
		
	}
	public LoadStockRequest(String type, int amount) {
		this.type = type;
		this.amount = amount;
	}
}
