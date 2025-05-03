package com.abanoub;

import com.abanoub.dao.DBProduct;
import com.abanoub.models.Product;
// import java.util.Date;
// import java.util.List;

public class ProductsApp {

    public static void main(String[] args) {
        DBProduct dbProduct = new DBProduct();

        // adding first product:
        Product newProduct = new Product();
        newProduct.setProductName("Milk");
        newProduct.setProductPrice(200);
        newProduct.setCateId(0);

        System.out.println("INSERTED A NEW PRODUCT WITH ID: " +
                dbProduct.insertProduct(newProduct));

        // // update a student:
        // Student updatedStudent = dbStudent.get(6);
        // updatedStudent.setAddress("Giza");
        // dbStudent.update(updatedStudent);

        // // looping over the students:
        // List<Student> studentList = new DBStudent().get();

        // for (Student s : studentList) {
        // System.out.println(s.getStudentId() + " " + s.getStudentName() + " " +
        // s.getAddress() + " " + s.getJoinDate() + " " + s.getAge());
        // }

        // // deleting a student:
        // dbStudent.delete(7);
    }

}
