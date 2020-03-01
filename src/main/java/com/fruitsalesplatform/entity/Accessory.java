package com.fruitsalesplatform.entity;

//db_table_Accessory附属品
public class Accessory {
	private String accessoryid;//附属品Id
	private String fruitid;//商品id
	private String name;//附属品名称
	private double price;//价格
	private String createtime;//创建时间
	
	
	public String getAccessoryid() {
		return accessoryid;
	}
	public void setAccessoryid(String accessoryid) {
		this.accessoryid = accessoryid;
	}
	public String getFruitid() {
		return fruitid;
	}
	public void setFruitid(String fruitid) {
		this.fruitid = fruitid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	
	
}
