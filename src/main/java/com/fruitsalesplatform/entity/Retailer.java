package com.fruitsalesplatform.entity;

//db_table_retailer零售商
public class Retailer extends PageEntity{
	private String retailerid;//pk
	private String name;//零售商姓名
	private String telephone;//零售商电话
	private String address;//零售商地址
	private String status;//状态(0/1)
	private String createtime;//创建时间
	
	
	public String getRetailerid() {
		return retailerid;
	}
	public void setRetailerid(String retailerid) {
		this.retailerid = retailerid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	
}
