package com.microastudio.iforms.modules.system.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
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
@Table(name="user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(groups = Update.class)
    private Long id;

    @NotBlank
    @Column(unique = true)
    private String userName;

    /** 用户昵称 */
    @NotBlank
    private String nickName;

    /** 性别 */
    private String sex;

    /** 权限 */
    private String roleId;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String phone;

    @NotNull
    private byte isActive;

    private String password;

    @CreationTimestamp
    private Timestamp createdDate;

    private Timestamp modifiedDate;

    public @interface Update {}

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(userName, user.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName);
    }
}
