package com.abanoub.models;

import java.io.Serializable;
// import java.util.Date;
import java.util.List;

import javax.persistence.Cacheable;
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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "blogs", schema = "sys")
@Data
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "blogs_gen", sequenceName = "sys.blogs_SEQ", allocationSize = 1)
public class Blog implements Serializable {

    private static final long serialVersionUID = -915428707036605461L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "blogs_gen")
    @Column(name = "blog_id")
    private Integer blogId;
    @Column(name = "title")
    private String title;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "pulbished_date")
    private String pubDate;

    // @OneToMany(fetch = FetchType.LAZY)
    // @JoinColumn(name = "comment_id", insertable = false, updatable = false)
    // private List<Comment> comments;
}