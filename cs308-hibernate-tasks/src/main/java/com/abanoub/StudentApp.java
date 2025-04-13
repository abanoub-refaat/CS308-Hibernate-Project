package com.abanoub;

import com.abanoub.dao.DBStudent;
import com.abanoub.models.Student;
import java.util.Date;
import java.util.List;

public class StudentApp {

    public static void main(String[] args) {
        DBStudent dbStudent = new DBStudent();

        // adding first student:
        Student newStudent = new Student();
        newStudent.setStudentName("Youssef");
        newStudent.setAddress("Cairo");
        newStudent.setJoinDate(new Date());
        newStudent.setAge(20);

        System.out.println("INSERTED A NEW STUDENT WITH ID: " +
                dbStudent.insert(newStudent));

        // update a student:
        Student updatedStudent = dbStudent.get(6);
        updatedStudent.setAddress("Giza");
        dbStudent.update(updatedStudent);

        // looping over the students:
        List<Student> studentList = new DBStudent().get();

        for (Student s : studentList) {
            System.out.println(s.getStudentId() + " " + s.getStudentName() + " " +
                    s.getAddress() + " " + s.getJoinDate() + " " + s.getAge());
        }

        // deleting a student:
        dbStudent.delete(7);
    }

}
