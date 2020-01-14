package sample.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true)
@Table(name = "TestResult", uniqueConstraints = {@UniqueConstraint(columnNames = "ID")})
public class TestResultEntity implements Serializable {

    private static final long serialVersionUID = -5970670510974895047L;

    @Id
    @Column(name = "ID", unique = true, nullable = false)
    private Integer id;
    @Column(name = "NAME", unique = true, nullable = false, length = 100)
    private String name;
    @Column(name = "APPLICATION", unique = false, nullable = false, length = 100)
    private String application;
    @Column(name = "RESULT", unique = false, nullable = false)
    private Integer result;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getApplication() {
        return this.application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public Integer getResult() {
        return this.result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }
}
