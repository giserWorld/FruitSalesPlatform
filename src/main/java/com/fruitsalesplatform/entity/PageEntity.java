package com.fruitsalesplatform.entity;

//分页查询
public class PageEntity {
	private Integer currentPage=1;//当前页面
	private Integer pageSize=10;//页面大小
	private Integer startPage=0;//数据请求位置index,等同于offset
	private Integer offset=0;//数据偏移数，即数据起始index
	
	
	
	public Integer getOffset() {
		return (currentPage-1)*pageSize;
	}
	public void setOffset(Integer offset) {
		this.offset = offset;
	}
	public Integer getCurrentPage() {
		//如果当前页面为空，则设置为1
		if(currentPage==null) currentPage=1;
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public Integer getStartPage() {
		if(startPage==null) startPage=0;
		return startPage;
	}
	public void setStartPage(Integer startPage) {
		this.startPage = startPage;
	}
	public Integer getPageSize() {
		if(pageSize==null) pageSize=10;
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
}
