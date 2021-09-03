package Entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "Websource",uniqueConstraints = { @UniqueConstraint(columnNames = { "id" }) })
public class Websource implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    @Column(name = "url", nullable = false)
    private String url;

    @Column(name = "name")
    private String name;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "websource")
    private Set<WebFormat> listWebFormat;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "websource")
    private Set<News> listNews;


    public Websource() {
    }


    public Websource(long id,String url, String name) {
        this.url = url;
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
