package yz.android.dribbble.pojo;

import java.io.Serializable;
import java.util.ArrayList;

public class Comments implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8548772844252324179L;
	private String page ;
	private int perPage;
	private int pages;
	private int total;
	private  ArrayList<Comment> commments = new ArrayList<Comment>();
	
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
	public ArrayList<Comment> getCommments() {
		return commments;
	}
	public void setCommments(ArrayList<Comment> commments) {
		this.commments = commments;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	
	
}
