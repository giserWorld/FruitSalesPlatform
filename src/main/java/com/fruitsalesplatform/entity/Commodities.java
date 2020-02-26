package com.fruitsalesplatform.entity;

//水果货物(分页)
public class Commodities extends PageEntity{
	
	private String fruitid;//商品Id
	private String name;//水果名称
	private double price;//价格
	private String locality;//产地
	private String createtime;//录单时间
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
	public String getLocality() {
		return locality;
	}
	public void setLocality(String locality) {
		this.locality = locality;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	
	
}
