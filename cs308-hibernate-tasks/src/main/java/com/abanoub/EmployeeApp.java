package com.abanoub;

import com.abanoub.dao.DBEmployee;
import com.abanoub.models.Employee;
import java.util.Date;
import java.util.List;

public class EmployeeApp {

  public static void main(String[] args) {
    DBEmployee dbEmployee = new DBEmployee();

    List<Employee> employeeList = dbEmployee.get();

    for (Employee e : employeeList) {
      System.out.println(e);
    }

    System.out.println(" THE EMPLOYEE LAST NAME IS " +
        dbEmployee.get(100).getLastName());

    Employee newEmployee = new Employee();
    newEmployee.setLastName("BOB");
    newEmployee.setEmail("abaonub@sci.eg");
    newEmployee.setSalary(151);
    newEmployee.setCommissionPct(0.5);
    newEmployee.setDepartmentId(50);
    newEmployee.setJobId("SH_CLERK");
    newEmployee.setHireDate(new Date());

    System.out.println(dbEmployee.insert(newEmployee));

    Employee updatedEmployee = dbEmployee.get(231);
    updatedEmployee.setLastName("Refaat");
    dbEmployee.update(updatedEmployee);

    dbEmployee.delete(208);
  }
}
