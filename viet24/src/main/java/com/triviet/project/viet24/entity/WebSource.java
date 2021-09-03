package com.triviet.project.viet24.entity;

import com.fasterxml.jackson.annotation.*;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "web_source")
public class WebSource {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private long id;
	@Column(name = "url", nullable = false)
	private String url;
	@Column(name = "name")
	private String name;
	@Column(name = "status")
	private long status;
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

	@JsonProperty("url")
	public String getURL() {
		return url;
	}

	@JsonProperty("url")
	public void setURL(String value) {
		this.url = value;
	}

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	@JsonProperty("name")
	public void setName(String value) {
		this.name = value;
	}

	@JsonProperty("status")
	public long getStatus() {
		return status;
	}

	@JsonProperty("status")
	public void setStatus(long value) {
		this.status = value;
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
