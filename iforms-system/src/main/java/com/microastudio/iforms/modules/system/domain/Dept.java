package com.microastudio.iforms.modules.system.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @author peng
 */
@Entity
@Getter
@Setter
@Table(name = "dept")
public class Dept implements Serializable {

    private static final long serialVersionUID = -1287039399523002080L;

    @Id
    @Column(name = "id")
    @NotNull(groups = Update.class)
    private String id;

    @Column(name = "name", nullable = false)
    @NotBlank
    private String name;

    @JoinColumn(name = "market_id", insertable = false, updatable = false)
    @OneToOne
    private Market market;

    @Column(name = "market_id")
    private String marketId;

    private String contactNo;
    private String email;
    private String address;

    @NotNull
    private byte isActive;

    private String createdBy;

    @Column(name = "created_date")
    @CreationTimestamp
    private Timestamp createdDate;

    private String modifiedBy;
    private Timestamp modifiedDate;

    public @interface Update {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Dept dept = (Dept) o;
        return Objects.equals(id, dept.id) &&
                Objects.equals(name, dept.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
