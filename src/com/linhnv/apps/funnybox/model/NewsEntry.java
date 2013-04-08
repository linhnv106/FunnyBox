package com.linhnv.apps.funnybox.model;

public class NewsEntry {
	private int id;
	private String title;
	private String imgUrl;
	private String content;			
	public NewsEntry(int id, String title, String imgUrl, String content) {
		super();
		this.id = id;
		this.title = title;
		this.imgUrl = imgUrl;
		this.content = content;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImage() {
		return imgUrl;
	}
	public void setImage(String image) {
		this.imgUrl = image;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
	
	
}
