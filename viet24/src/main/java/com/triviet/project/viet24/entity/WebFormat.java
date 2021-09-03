package com.triviet.project.viet24.entity;

import com.fasterxml.jackson.annotation.*;

import java.util.Date;

import javax.persistence.*;


@Entity
@Table(name = "web_format")
public class WebFormat {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
	private long id;
	@ManyToOne
    @JoinColumn(name = "websource_id")
	 private WebSource webSource;
	@ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
	 private Category category;
	@Column(name = "category_menu")
	 private String categoryMenu;
	@Column(name = "category_parent")
	 private String categoryParent;
	 @Column(name = "category_format")
	 private String categoryFormat;
	 @Column(name = "format")
	 private String format;
	 @Column(name = "format_content_detail")
	 private String formatContentDetail;
	 @Column(name = "created_at")
	 private int createdAt;
	 @Column(name = "updated_at")
	 private int updatedAt;

	 @JsonProperty("id")
	 public long getID() { return id; }
	 @JsonProperty("id")
	 public void setID(long value) { this.id = value; }

	 @JsonProperty("web_source")
	 public WebSource getWebSource() { return webSource; }
	 @JsonProperty("web_source")
	 public void setWebSource(WebSource value) { this.webSource = value; }

	 @JsonProperty("category")
	 public Category getCategory() { return category; }
	 @JsonProperty("category")
	 public void setCategory(Category value) { this.category = value; }

	 @JsonProperty("category_menu")
	 public String getCategoryMenu() { return categoryMenu; }
	 @JsonProperty("category_menu")
	 public void setCategoryMenu(String value) { this.categoryMenu = value; }

	 @JsonProperty("category_parent")
	 public String getCategoryParent() { return categoryParent; }
	 @JsonProperty("category_parent")
	 public void setCategoryParent(String value) { this.categoryParent = value; }

	 @JsonProperty("category_format")
	 public String getCategoryFormat() { return categoryFormat; }
	 @JsonProperty("category_format")
	 public void setCategoryFormat(String value) { this.categoryFormat = value; }

	 @JsonProperty("format")
	 public String getFormat() { return format; }
	 @JsonProperty("format")
	 public void setFormat(String value) { this.format = value; }

	 @JsonProperty("format_content_detail")
	 public String getFormatContentDetail() { return formatContentDetail; }
	 @JsonProperty("format_content_detail")
	 public void setFormatContentDetail(String value) { this.formatContentDetail = value; }

	 @JsonProperty("created_at")
	 public int getCreatedAt() { return createdAt; }
	 @JsonProperty("created_at")
	 public void setCreatedAt(int value) { this.createdAt = value; }

	 @JsonProperty("updated_at")
	 public int getUpdatedAt() { return updatedAt; }
	 @JsonProperty("updated_at")
	 public void setUpdatedAt(int value) { this.updatedAt = value; }
}
