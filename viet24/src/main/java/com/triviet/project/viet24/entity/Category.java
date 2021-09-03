package com.triviet.project.viet24.entity;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;

import java.util.Date;

@Entity
@Table(name = "category")
public class Category {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private long id;
	@Column(name = "name")
	private String name;
	@Column(name = "category_name")
	private String categoryName;
	@Column(name = "display")
	private long display;
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

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	@JsonProperty("name")
	public void setName(String value) {
		this.name = value;
	}

	@JsonProperty("category_name")
	public String getCategoryName() {
		return categoryName;
	}

	@JsonProperty("category_name")
	public void setCategoryName(String value) {
		this.categoryName = value;
	}

	@JsonProperty("display")
	public long getDisplay() {
		return display;
	}

	@JsonProperty("display")
	public void setDisplay(long value) {
		this.display = value;
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
