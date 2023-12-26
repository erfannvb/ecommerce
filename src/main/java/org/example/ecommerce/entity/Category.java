package org.example.ecommerce.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.example.ecommerce.base.entity.BaseEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbl_category")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class Category extends BaseEntity<Long> {

    @Column(name = "category_title")
    private String categoryTitle;

    @Column(name = "category_desc")
    private String categoryDesc;

    @OneToMany(mappedBy = "category")
    private List<Product> productList = new ArrayList<>();

}
