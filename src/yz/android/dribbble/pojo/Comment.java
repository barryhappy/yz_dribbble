package yz.android.dribbble.pojo;

import java.io.Serializable;
import java.util.Date;

public class Comment implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2850876237980965666L;
	private long id ;
	private int likesCount;
	private String body ;
	private Player player;
	private Date createdAt;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getLikesCount() {
		return likesCount;
	}
	public void setLikesCount(int likesCount) {
		this.likesCount = likesCount;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	
}

