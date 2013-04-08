package com.linhnv.apps.funnybox.model;

public class VideoEntry {
	private String videoId;
	private String title;
	private String imgUrl;
	private String source;
	private String category;
	private String type;
	
	public VideoEntry(String id, String title, String imgUrl) {
		super();
		this.videoId = id;
		this.title = title;
		this.imgUrl = imgUrl;
	}
	
	public VideoEntry(String id, String title, String imgUrl, String source,
			String category, String type) {
		super();
		this.videoId = id;
		this.title = title;
		this.imgUrl = imgUrl;
		this.source = source;
		this.category = category;
		this.type = type;
	}
	

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getId() {
		return videoId;
	}
	public void setId(String id) {
		this.videoId = id;
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
