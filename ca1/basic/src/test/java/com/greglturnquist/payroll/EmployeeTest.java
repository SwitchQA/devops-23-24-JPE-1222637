package com.greglturnquist.payroll;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class EmployeeTest {

    @Test
    public void testEmployeeCreation() {
        Employee employee = new Employee("John", "Doe", "Software Engineer", 5, "email@email.com");

        assertNotNull(employee);
        assertEquals("John", employee.getFirstName());
        assertEquals("Doe", employee.getLastName());
        assertEquals("Software Engineer", employee.getDescription());
        assertEquals(5, employee.getJobYears());
    }

    @Test
    public void testAttributesNotNullOrEmpty() {
        Employee employee = new Employee("John", "Doe", "Software Engineer", 5, "email@email.com");

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
        Employee employee = new Employee("John", "Doe", "Software Engineer", 5, "email@email.com");
        employee.setJobYears(10);

        assertEquals(10, employee.getJobYears());
    }

    @Test
    public void testEmployeeConstructorWithInvalidJobYears() {
        assertThrows(IllegalArgumentException.class, () -> new Employee("John", "Doe", "Software Engineer", -1, "email@email.com"));
    }

    @Test
    public void testGetJobYears() {
        Employee employee = new Employee("John", "Doe", "Software Engineer", 5, "email@email.com");
        int expected = 5;
        int result = employee.getJobYears();
        assertEquals(expected, result);
    }

    @Test
    public void testSetJobYears() {
        Employee employee = new Employee("John", "Doe", "Software Engineer", 5, "email@email.com");
        employee.setJobYears(10);
        int expected = 10;
        int result = employee.getJobYears();
        assertEquals(expected, result);
    }

    @Test
    public void testConstructorWithInvalidEmail() {
        assertThrows(IllegalArgumentException.class, () -> new Employee("John", "Doe", "Software Engineer", 5, ""));
    }

    @Test
    public void testGetEmail() {
        Employee employee = new Employee("John", "Doe", "Software Engineer", 5, "email@email.com");
        String expected = "email@email.com";
        String result = employee.getEmail();
        assertEquals(expected, result);
    }

    @Test
    public void testSetEmail() {
        Employee employee = new Employee("John", "Doe", "Software Engineer", 5, "email@email.com");
        employee.setEmail("otherEmail@email.com");
        String expected = "otherEmail@email.com";
        String result = employee.getEmail();
        assertEquals(expected, result);
    }
}
