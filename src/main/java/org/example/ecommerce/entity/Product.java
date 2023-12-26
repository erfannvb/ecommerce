package org.example.ecommerce.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.example.ecommerce.base.entity.BaseEntity;

@Entity
@Table(name = "tbl_product")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class Product extends BaseEntity<Long> {

    @Column(name = "product_title")
    private String productTitle;

    @Column(name = "product_desc", length = 1500)
    private String productDesc;

    @Column(name = "product_photo")
    private String productPhoto;

    @Column(name = "product_price")
    private double productPrice;

    @Column(name = "product_discount")
    private double productDiscount;

    @Column(name = "product_quantity")
    private int productQuantity;

    @ManyToOne
    private Category category;

}
