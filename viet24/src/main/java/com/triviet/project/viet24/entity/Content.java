package com.triviet.project.viet24.entity;

import com.fasterxml.jackson.annotation.*;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "content")
public class Content {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private long id;
	@Column(name = "time_content")
	private String timeContent;

	@ManyToOne
	@JoinColumn(name = "category_id",insertable = false, updatable = false)
	private Category category;

	@Column(name = "content")
	private String content;

	@ManyToOne
	@JoinColumn(name = "news_id", nullable = false, insertable = false, updatable = false)
	private News news;
	@Column(name = "created_at")
	private int createdAt;
	@Column(name = "updated_at")
	private int updatedAt;

	@JsonProperty("id")
	public long getID() {
		return id;
	}

	@JsonProperty("id")
	public void setID(long value) {
		this.id = value;
	}

	@JsonProperty("time_content")
	public String getTimeContent() {
		return timeContent;
	}

	@JsonProperty("time_content")
	public void setTimeContent(String value) {
		this.timeContent = value;
	}

	@JsonProperty("category")
	public Category getCategory() {
		return category;
	}

	@JsonProperty("category")
	public void setCategory(Category value) {
		this.category = value;
	}

	@JsonProperty("content")
	public String getContent() {
		return content;
	}

	@JsonProperty("content")
	public void setContent(String value) {
		this.content = value;
	}

	@JsonProperty("news")
	public News getNews() {
		return news;
	}

	@JsonProperty("news")
	public void setNews(News value) {
		this.news = value;
	}

	@JsonProperty("created_at")
	public int getCreatedAt() {
		return createdAt;
	}

	@JsonProperty("created_at")
	public void setCreatedAt(int value) {
		this.createdAt = value;
	}

	@JsonProperty("updated_at")
	public int getUpdatedAt() {
		return updatedAt;
	}

	@JsonProperty("updated_at")
	public void setUpdatedAt(int value) {
		this.updatedAt = value;
	}
}
