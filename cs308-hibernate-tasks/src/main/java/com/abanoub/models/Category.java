package com.abanoub.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "categories", schema = "sys")
@Data

@SequenceGenerator(name = "categoriy_gen", sequenceName = "sys.CATEGORY_SEQ", allocationSize = 1)
public class Category implements Serializable {
    private static final long serialVersionUID = -915428707036605461L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_gen")
    @Column(name = "category_id")
    private Integer categoryId;
    @Column(name = "categoriy_name")
    private String categoryName;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoriy_id", insertable = false, updatable = false)
    private List<Product> products;
}