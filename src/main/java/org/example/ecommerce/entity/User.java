package org.example.ecommerce.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import org.example.ecommerce.base.entity.BaseEntity;

@Entity
@Table(name = "tbl_user")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity<Long> {

    @Column(name = "user_name", length = 100)
    private String username;

    @Column(name = "user_email", length = 100)
    private String userEmail;

    @Column(name = "user_password", length = 100)
    private String userPassword;

    @Column(name = "user_phone", length = 12)
    private String userPhone;

    @Column(name = "user_address", length = 1500)
    private String userAddress;

    @Column(name = "user_type")
    private String userType;

}
