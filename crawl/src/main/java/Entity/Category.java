package Entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "CATEGORY")
public class Category implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "display")
    private int display;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "category")
    private Set<WebFormat> listWebformat;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "category")
    private Set<News> listNews;

    public Category() {
    }

    public Category(String name, String categoryName) {
        this.name = name;
        this.categoryName = categoryName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
