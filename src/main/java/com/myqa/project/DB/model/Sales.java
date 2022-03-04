package com.myqa.project.DB.model;

import java.sql.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Sales {
	@Id
    @GeneratedValue
    private int id;
	private String type;
	private int item_price;
	private Date date;
	public Sales() {
		this.id = 0;
		this.type = "undefined";
		this.item_price = 0;
	}
	public Sales(int id, String type, int item_price, Date date) {
		this.id = id;
		this.type = type;
		this.item_price = item_price;
		this.date = date;
	}
	public int getId() {
		return id;
	}
	public String getType() {
		return type;
	}
	public int getItemPrice() {
		return item_price;
	}
	public Date getDate() {
		return date;
	}
}
