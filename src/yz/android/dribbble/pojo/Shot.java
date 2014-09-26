package yz.android.dribbble.pojo;

import java.io.Serializable;

public class Shot implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 327786732775257077L;
	private long id;
	private String title;
	private String description;
	private int height;
	private int width;
	private int likeCount;
	private int commentsCount;
	private int reboundsCount;
	private String url;
	private String shorturl;
	private int viewsCount;
	private String reboundSourceId;
	private String imageUrl;
	private String iamgeTeaserUrl;
	private String iamge400Url;
	
	private Player palyer;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	public int getCommentsCount() {
		return commentsCount;
	}

	public void setCommentsCount(int commentsCount) {
		this.commentsCount = commentsCount;
	}

	public int getReboundsCount() {
		return reboundsCount;
	}

	public void setReboundsCount(int reboundsCount) {
		this.reboundsCount = reboundsCount;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getShorturl() {
		return shorturl;
	}

	public void setShorturl(String shorturl) {
		this.shorturl = shorturl;
	}

	public int getViewsCount() {
		return viewsCount;
	}

	public void setViewsCount(int viewsCount) {
		this.viewsCount = viewsCount;
	}

	public String getReboundSourceId() {
		return reboundSourceId;
	}

	public void setReboundSourceId(String reboundSourceId) {
		this.reboundSourceId = reboundSourceId;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getIamgeTeaserUrl() {
		return iamgeTeaserUrl;
	}

	public void setIamgeTeaserUrl(String iamgeTeaserUrl) {
		this.iamgeTeaserUrl = iamgeTeaserUrl;
	}

	public String getIamge400Url() {
		return iamge400Url;
	}

	public void setIamge400Url(String iamge400Url) {
		this.iamge400Url = iamge400Url;
	}

	public Player getPalyer() {
		return palyer;
	}

	public void setPalyer(Player palyer) {
		this.palyer = palyer;
	}
	
	
}
