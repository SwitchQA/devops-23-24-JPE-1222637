package com.greglturnquist.payroll;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class EmployeeTest {

    @Test
    public void testEmployeeCreation() {
        Employee employee = new Employee("John", "Doe", "Software Engineer", 5);

        assertNotNull(employee);
        assertEquals("John", employee.getFirstName());
        assertEquals("Doe", employee.getLastName());
        assertEquals("Software Engineer", employee.getDescription());
        assertEquals(5, employee.getJobYears());
    }

    @Test
    public void testAttributesNotNullOrEmpty() {
        Employee employee = new Employee("John", "Doe", "Software Engineer", 5);

        assertAll("employee",
                () -> assertNotNull(employee.getFirstName()),
                () -> assertNotNull(employee.getLastName()),
                () -> assertNotNull(employee.getDescription()),
                () -> assertNotEquals("", employee.getFirstName()),
                () -> assertNotEquals("", employee.getLastName()),
                () -> assertNotEquals("", employee.getDescription())
        );
    }

    @Test
    public void testJobYearsIsInteger() {
        Employee employee = new Employee("John", "Doe", "Software Engineer", 5);
        employee.setJobYears(10);

        assertEquals(10, employee.getJobYears());
    }

    @Test
    public void testEmployeeConstructorWithInvalidJobYears() {
        assertThrows(IllegalArgumentException.class, () -> new Employee("John", "Doe", "Software Engineer", -1));
    }

}
