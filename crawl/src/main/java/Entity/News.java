/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import javax.persistence.*;

/**
 * @author Anhkhoa
 */
@Entity
@Table(name = "News",uniqueConstraints = { @UniqueConstraint(columnNames = { "id" }) })
public class News {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    @Column(name = "title",nullable = true)
    private String title;

    @Column(name = "link",nullable = true)
    private String link;

    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private String image;

    private Long idWebsource;

    @Column(name="href")
    private String href;

    @Column(name = "number_of_access")
    private Long numberOfAccess;

    @Column(name = "status")
    private Integer status;


//    @OneToOne(mappedBy = "news")
//    private Content content;
@OneToMany(mappedBy="news")
private Set<Content> content;


    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "websource_id", nullable = false)
    private Websource websource;

    private String pathImage;

    public  News() {
    }
    public News(String title, String link) {
        this.title = title;
        this.link = link;
    }
    public News(String title, String link, String image) {
        this.image = image;
        this.title = title;
        this.link = link;
    }


    public News(String title, String link, String description, String image, Category category, String href, Long numberOfAccess, Integer status,Date timeCreated, Date timeUpdated, Content content, Websource websource) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.image = image;
        this.category = category;
        this.href = href;
        this.numberOfAccess = numberOfAccess;
        this.status = status;
//        this.timeCreated = timeCreated;
//        this.timeUpdated = timeUpdated;
//        this.listContent = listContent;
        this.websource = websource;
    }

    public News(String title, String link, String img, Category category,Long idWebsource, String description, String convertStringToUnsignedForms) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.image = img;
        this.category = category;
        this.idWebsource = idWebsource;
        this.href = convertStringToUnsignedForms;
    }

    public News(String title, String link, String image, Category category,
                Long idWebsource,
                String description, String href, Long numberOfAccess, Integer status,Date timeCreated, Date timeUpdated, Websource websource, String pathImage) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.image = image;
        this.category = category;
        this.href = href;
        this.numberOfAccess = numberOfAccess;
        this.status = status;
        this.idWebsource = idWebsource;
        this.pathImage = pathImage;
//        this.timeUpdated = timeUpdated;
//        this.timeCreated = timeCreated;
        this.websource = websource;
    }

    public News(String title, String link, String img, Category category, String description, String convertStringToUnsignedForms,String pathImage) {
        this.title = title;
        this.link = link;
        this.image = img;
        this.category = category;
        this.description = description;
        this.href = convertStringToUnsignedForms;
        this.pathImage = pathImage;
    }

    public String getPathImage() {
        return pathImage;
    }

    public void setPathImage(String pathImage) {
        this.pathImage = pathImage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public Long getNumberOfAccess() {
        return numberOfAccess;
    }

    public void setNumberOfAccess(Long numberOfAccess) {
        this.numberOfAccess = numberOfAccess;
    }

    public Integer getStatus() {
        return status;
    }



    public void setStatus(Integer status) {
        this.status = status;
    }



    public Long getIdWebsource() {
        return idWebsource;
    }

    public void setIdWebsource(Long idWebsource) {
        this.idWebsource = idWebsource;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Websource getWebsource() {
        return websource;
    }

    public void setWebsource(Websource websource) {
        this.websource = websource;
    }

    public Set<Content> getContent() {
        return content;
    }

    public void setContent(Set<Content> content) {
        this.content = content;
    }


}
