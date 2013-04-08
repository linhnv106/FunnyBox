package com.linhnv.apps.funnybox.model;

public class PictureEntry {
	private int id;
	private String title;
	private String imgUrl;
	
	
	public PictureEntry(int id, String title, String imgUrl) {
		super();
		this.id = id;
		this.title = title;
		this.imgUrl = imgUrl;
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
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
	
}
