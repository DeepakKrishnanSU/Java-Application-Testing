package com.company.etp.testng;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class EmployeeTestNGTest {

    @Test
    public void employeeNameTest() {

        String actualName = "Dinesh Williams";
        String expectedName = "Dinesh Williams";

        assertEquals(actualName, expectedName);
    }
}