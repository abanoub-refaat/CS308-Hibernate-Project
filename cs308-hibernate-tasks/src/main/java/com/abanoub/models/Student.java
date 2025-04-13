package com.abanoub.models;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "students", schema = "sys")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "students_gen", sequenceName = "sys.STUDENTS_SEQ", allocationSize = 1)
public class Student implements Serializable {

    private static final long serialVersionUID = -915428707036605461L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "students_gen")
    @Column(name = "student_id")
    private Integer studentId;
    @Column(name = "student_name")
    private String studentName;
    @Column(name = "student_address")
    private String address;
    @Column(name = "joined_date")
    private Date joinDate;
    @Column(name = "student_age")
    private Integer age;
}