package com.triviet.project.viet24.entity;

import com.fasterxml.jackson.annotation.*;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "news")
public class News {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private long id;
	@Column(name = "title")
	private String title;
	@Column(name = "link")
	private String link;
	@ManyToOne
	@JoinColumn(name = "category_id", nullable = false)
	private Category category;
	@ManyToOne
	@JoinColumn(name = "websource_id", insertable = false, updatable = false)
	private WebSource webSource;
	@Column(name = "image")
	private String image;
	@Column(name = "description")
	private String description;
	@Column(name = "href")
	private String href;
	@Column(name = "status")
	private long status;
	@Column(name = "number_of_access")
	private long numberOfAccess;
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

	@JsonProperty("title")
	public String getTitle() {
		return title;
	}

	@JsonProperty("title")
	public void setTitle(String value) {
		this.title = value;
	}

	@JsonProperty("link")
	public String getLink() {
		return link;
	}

	@JsonProperty("link")
	public void setLink(String value) {
		this.link = value;
	}

	@JsonProperty("category")
	public Category getCategory() {
		return category;
	}

	@JsonProperty("category")
	public void setCategory(Category value) {
		this.category = value;
	}

	@JsonProperty("web_source")
	public WebSource getWebSource() {
		return webSource;
	}

	@JsonProperty("web_source")
	public void setWebSource(WebSource value) {
		this.webSource = value;
	}

	@JsonProperty("image")
	public String getImage() {
		return image;
	}

	@JsonProperty("image")
	public void setImage(String value) {
		this.image = value;
	}

	@JsonProperty("description")
	public String getDescription() {
		return description;
	}

	@JsonProperty("description")
	public void setDescription(String value) {
		this.description = value;
	}

	@JsonProperty("href")
	public String getHref() {
		return href;
	}

	@JsonProperty("href")
	public void setHref(String value) {
		this.href = value;
	}

	@JsonProperty("status")
	public long getStatus() {
		return status;
	}

	@JsonProperty("status")
	public void setStatus(long value) {
		this.status = value;
	}

	@JsonProperty("number_of_access")
	public long getNumberOfAccess() {
		return numberOfAccess;
	}

	@JsonProperty("number_of_access")
	public void setNumberOfAccess(long value) {
		this.numberOfAccess = value;
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
