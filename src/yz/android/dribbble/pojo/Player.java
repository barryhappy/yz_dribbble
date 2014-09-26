package yz.android.dribbble.pojo;

import java.io.Serializable;
import java.util.Date;

public class Player implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6496650503887253181L;
	private long id = 0;
	private String name;
	private String location;
	private int followersCount;
	private int drafteesCount;
	private int likesCount;
	private int likesReceivedCount;
	private int commentsCount;
	private int commentsReceivedCount;
	private int reboundsCount;
	private int reboundsReceivedCount;
	private String url;
	private String avatarUrl;
	private String username;
	private String twitterScreenname;
	private String webSiteUrl;
	private String draftedByPlayerId;
	private int shotsCount;
	private int followingCount;
	private Date createdAt;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getFollowersCount() {
		return followersCount;
	}
	public void setFollowersCount(int followersCount) {
		this.followersCount = followersCount;
	}
	public int getDrafteesCount() {
		return drafteesCount;
	}
	public void setDrafteesCount(int drafteesCount) {
		this.drafteesCount = drafteesCount;
	}
	public int getLikesCount() {
		return likesCount;
	}
	public void setLikesCount(int likesCount) {
		this.likesCount = likesCount;
	}
	public int getLikesReceivedCount() {
		return likesReceivedCount;
	}
	public void setLikesReceivedCount(int likesReceivedCount) {
		this.likesReceivedCount = likesReceivedCount;
	}
	public int getCommentsCount() {
		return commentsCount;
	}
	public void setCommentsCount(int commentsCount) {
		this.commentsCount = commentsCount;
	}
	public int getCommentsReceivedCount() {
		return commentsReceivedCount;
	}
	public void setCommentsReceivedCount(int commentsReceivedCount) {
		this.commentsReceivedCount = commentsReceivedCount;
	}
	public int getReboundsCount() {
		return reboundsCount;
	}
	public void setReboundsCount(int reboundsCount) {
		this.reboundsCount = reboundsCount;
	}
	public int getReboundsReceivedCount() {
		return reboundsReceivedCount;
	}
	public void setReboundsReceivedCount(int reboundsReceivedCount) {
		this.reboundsReceivedCount = reboundsReceivedCount;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}
	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getTwitterScreenname() {
		return twitterScreenname;
	}
	public void setTwitterScreenname(String twitterScreenname) {
		this.twitterScreenname = twitterScreenname;
	}
	public String getWebSiteUrl() {
		return webSiteUrl;
	}
	public void setWebSiteUrl(String webSiteUrl) {
		this.webSiteUrl = webSiteUrl;
	}
	public String getDraftedByPlayerId() {
		return draftedByPlayerId;
	}
	public void setDraftedByPlayerId(String draftedByPlayerId) {
		this.draftedByPlayerId = draftedByPlayerId;
	}
	public int getShotsCount() {
		return shotsCount;
	}
	public void setShotsCount(int shotsCount) {
		this.shotsCount = shotsCount;
	}
	public int getFollowingCount() {
		return followingCount;
	}
	public void setFollowingCount(int followingCount) {
		this.followingCount = followingCount;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	
}
