package com.abanoub;

import com.abanoub.criteria.FilterQuery;
import com.abanoub.criteria.Operator;
import com.abanoub.dao.DBConfig;
import com.abanoub.dao.DBEmployee;
import com.abanoub.models.Employee;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;

public class EmployeeApp {
  public static void testCache() {

    try (Session session = DBConfig.SESSION_FACTORY.openSession()) {
      System.out.println(session.get(Employee.class, (Integer) 101));
      System.out.println("-----------------------");
      System.out.println(session.get(Employee.class, (Integer) 102));
      System.out.println("-----------------------");
      System.out.println(session.get(Employee.class, (Integer) 102));
    } catch (Exception ex) {
      System.err.println(ex.getMessage());
    }
  }

  public static void main(String[] args) {

    // testCache();

    DBEmployee dbEmployee = new DBEmployee();

    List<FilterQuery> filterQueries = new ArrayList<>();
    filterQueries.add(new FilterQuery("jobId", "IT_PROG", Operator.EQ));
    filterQueries.add(new FilterQuery("salary", "2000", Operator.GT));

    for (Employee e : dbEmployee.getByFilter(filterQueries)) {
      System.out.println(e);
    }

    // DBEmployee dbEmployee = new DBEmployee();

    // List<Employee> employeeList = dbEmployee.get();

    // for (Employee e : employeeList) {
    // System.out.println(e);
    // }

    // System.out.println(" THE EMPLOYEE LAST NAME IS " +
    // dbEmployee.get(100).getLastName());

    // Employee newEmployee = new Employee();
    // newEmployee.setLastName("BOB");
    // newEmployee.setEmail("abaonub@sci.eg");
    // newEmployee.setSalary(151);
    // newEmployee.setCommissionPct(0.5);
    // newEmployee.setDepartmentId(50);
    // newEmployee.setJobId("SH_CLERK");
    // newEmployee.setHireDate(new Date());

    // System.out.println(dbEmployee.insert(newEmployee));

    // Employee updatedEmployee = dbEmployee.get(231);
    // updatedEmployee.setLastName("Refaat");
    // dbEmployee.update(updatedEmployee);

    // dbEmployee.delete(208);
    DBConfig.shutdown();
  }
}
