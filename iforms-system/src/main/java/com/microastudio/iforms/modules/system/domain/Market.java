package com.microastudio.iforms.modules.system.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author peng
 */
@Entity
@Getter
@Setter
@Table(name = "market")
public class Market implements Serializable {

    private static final long serialVersionUID = -8475008307861568155L;

    @Id
    @Column(name = "id")
    @NotNull(groups = Update.class)
    private String id;

    @Column(name = "name", nullable = false)
    @NotBlank
    private String name;

    @Column(name = "is_active", nullable = false)
    @NotNull
    private byte isActive;

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
        Market dept = (Market) o;
        return Objects.equals(id, dept.id) &&
                Objects.equals(name, dept.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
