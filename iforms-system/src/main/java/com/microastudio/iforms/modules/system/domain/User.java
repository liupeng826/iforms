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
@Table(name = "user")
public class User implements Serializable {

    private static final long serialVersionUID = -4890257193045287410L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(groups = Update.class)
    private Long id;

    @NotBlank
    @Column(name = "user_name")
    private String userName;

    /**
     * 用户昵称
     */
    @NotBlank
    private String nickName;

    /**
     * 性别
     */
    private byte sex;

    /**
     * 权限
     */
    private int role;

    private String email;

    private String phone;

    private String jobId;

    @NotNull
    private byte isActive;

    private String password;

    @JoinColumn(name = "client_id")
    @OneToOne
    private Client client;

    @JoinColumn(name = "dept_id")
    @OneToOne
    private Dept dept;

    private String createdBy;

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
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(userName, user.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName);
    }
}
