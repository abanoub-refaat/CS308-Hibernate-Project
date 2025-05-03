package com.abanoub.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.FetchType;

import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "products", schema = "sys")
@Data
@SequenceGenerator(name = "product_gen", sequenceName = "sys.PRODUCTS_SEQ", allocationSize = 1)
public class Product implements Serializable {
    private static final long serialVersionUID = -915428707036605461L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_gen")
    @Column(name = "product_id")
    private Integer employeeId;
    @Column(name = "prosuct_name")
    private String productName;
    @Column(name = "product_price")
    private Integer productPrice;
    @Column(name = "categoriy_id")
    private Integer cateId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoriy_id", insertable = false, updatable = false)
    Category category;
}
