package Entity;

import javax.persistence.*;

@Entity
@Table(name = "webformat",uniqueConstraints = { @UniqueConstraint(columnNames = { "id" }) })
public class WebFormat {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "websource_id")
    private Websource websource;

    @Column(name = "format")
    private String format;

    @Column(name = "category_menu")
    private String categoryMenu;

    @Column(name = "category_format")
    private String categoryFormat;

    @Column(name = "category_parent")
    private String categoryParent;

    @Column(name = "format_content_detail")
    private String formatContentDetail;

    public WebFormat(String format,String formatContentDetail,Websource websource) {
        this.websource = websource;
        this.format = format;
        this.formatContentDetail = formatContentDetail;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public Websource getWebsource() {
        return websource;
    }

    public void setWebsource(Websource websource) {
        this.websource = websource;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getFormatContentDetail() {
        return formatContentDetail;
    }

    public String getCategoryMenu() {
        return categoryMenu;
    }

    public void setCategoryMenu(String categoryMenu) {
        this.categoryMenu = categoryMenu;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getCategoryFormat() {
        return categoryFormat;
    }

    public String getCategoryParent() {
        return categoryParent;
    }

    public void setCategoryParent(String categoryParent) {
        this.categoryParent = categoryParent;
    }

    public void setCategoryFormat(String categoryFormat) {
        this.categoryFormat = categoryFormat;
    }

    public void setFormatContentDetail(String formatContentDetail) {
        this.formatContentDetail = formatContentDetail;
    }
}
