package yz.android.dribbble.pojo;

import java.io.Serializable;
import java.util.ArrayList;

public class Shots implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 919566232456766794L;
	private String page;
	private int perPage;
	private int pages;
	private int total;
	private ArrayList<Shot> shots = new ArrayList<Shot>();
	
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public int getPerPage() {
		return perPage;
	}
	public void setPerPage(int perPage) {
		this.perPage = perPage;
	}
	public int getPages() {
		return pages;
	}
	public void setPages(int pages) {
		this.pages = pages;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public ArrayList<Shot> getShots() {
		return shots;
	}
	public void setShots(ArrayList<Shot> shots) {
		this.shots = shots;
	}
	

}
