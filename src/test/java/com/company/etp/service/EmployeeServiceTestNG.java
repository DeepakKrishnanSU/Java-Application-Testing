package com.company.etp.service;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class EmployeeServiceTestNG {

    @BeforeMethod
    public void setUp() {
        System.out.println("Before each test");
    }

    @DataProvider(name = "employeeData")
    public Object[][] employeeData() {
        return new Object[][] {
                {"EMP101", "Ravi", "ravi@gmail.com"},
                {"EMP102", "Kumar", "kumar@gmail.com"},
                {"EMP103", "Arun", "arun@gmail.com"}
        };
    }

    @Test(dataProvider = "employeeData")
    public void testEmployeeData(
            String employeeCode,
            String name,
            String email) {

        System.out.println(
                employeeCode + " - " + name + " - " + email
        );
    }

    @AfterMethod
    public void tearDown() {
        System.out.println("After each test");
    }
}

















// package com.company.etp.service;
//import com.company.etp.exception.DuplicateEmployeeException;
//import com.company.etp.model.Employee;
//import com.company.etp.repository.AttendanceRepository;
//import com.company.etp.repository.EmployeeRepository;
//import com.company.etp.repository.EmployeeStatusHistoryRepository;
//import com.company.etp.repository.LeaveRequestRepository;
//import com.company.etp.repository.UserRepository;
//
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.testng.annotations.AfterMethod;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.DataProvider;
//import org.testng.annotations.Test;
//
//import java.util.Optional;
//
//import static org.mockito.Mockito.when;
//import static org.testng.Assert.assertThrows;
//
//public class EmployeeServiceTestNG {
//
//    private AutoCloseable mocks;
//
//    @Mock
//    private EmployeeRepository employeeRepository;
//
//    @Mock
//    private LeaveRequestRepository leaveRequestRepository;
//
//    @Mock
//    private AttendanceRepository attendanceRepository;
//
//    @Mock
//    private UserRepository userRepository;
//
//    @Mock
//    private EmployeeStatusHistoryRepository historyRepository;
//
//    private EmployeeServiceImpl employeeService;
//
//
//    // --------------------------------------------------
//    // BEFORE METHOD
//    // --------------------------------------------------
//
//    @BeforeMethod
//    public void setUp() {
//
//        System.out.println("========== BEFORE TEST ==========");
//
//        mocks = MockitoAnnotations.openMocks(this);
//
//        employeeService = new EmployeeServiceImpl(
//                employeeRepository,
//                leaveRequestRepository,
//                attendanceRepository,
//                userRepository,
//                historyRepository
//        );
//    }
//
//
//    // --------------------------------------------------
//    // DATA PROVIDER
//    // --------------------------------------------------
//
//    @DataProvider(name = "employeeData")
//    public Object[][] employeeData() {
//
//        return new Object[][] {
//
//                {"EMP101", "Ravi", "ravi@gmail.com"},
//                {"EMP102", "Kumar", "kumar@gmail.com"},
//                {"EMP103", "Arun", "arun@gmail.com"}
//
//        };
//    }
//
//
//    // --------------------------------------------------
//    // TEST 1 - DATA PROVIDER
//    // --------------------------------------------------
//
//    @Test(dataProvider = "employeeData")
//    public void testEmployeeData(
//            String employeeCode,
//            String name,
//            String email) {
//
//        System.out.println(
//                "Testing Employee : "
//                        + employeeCode
//                        + " | "
//                        + name
//                        + " | "
//                        + email
//        );
//
//        Employee employee = Employee.builder()
//                .employeeCode(employeeCode)
//                .name(name)
//                .email(email)
//                .phone("9876543210")
//                .leaveBalance(12)
//                .active(true)
//                .build();
//
//        when(employeeRepository.findByEmployeeCode(employeeCode))
//                .thenReturn(Optional.empty());
//
//        when(employeeRepository.findByEmailIgnoreCase(email))
//                .thenReturn(Optional.empty());
//
//        when(employeeRepository.save(employee))
//                .thenReturn(employee);
//
//        Employee result = employeeService.save(employee);
//
//        System.out.println(
//                "Employee saved : "
//                        + result.getEmployeeCode()
//        );
//    }
//
//
//    // --------------------------------------------------
//    // TEST 2 - DUPLICATE EMPLOYEE CODE
//    // --------------------------------------------------
//
//    @Test
//    public void shouldRejectDuplicateEmployeeCode() {
//
//        System.out.println(
//                "Testing duplicate employee code..."
//        );
//
//        Employee employee = Employee.builder()
//                .employeeCode("EMP001")
//                .name("Test Employee")
//                .email("test@gmail.com")
//                .phone("9876543210")
//                .leaveBalance(12)
//                .active(true)
//                .build();
//
//        Employee existingEmployee = Employee.builder()
//                .id(1L)
//                .employeeCode("EMP001")
//                .name("Existing Employee")
//                .email("existing@gmail.com")
//                .build();
//
//        when(employeeRepository.findByEmployeeCode("EMP001"))
//                .thenReturn(Optional.of(existingEmployee));
//
//        assertThrows(
//                DuplicateEmployeeException.class,
//                () -> employeeService.save(employee)
//        );
//
//        System.out.println(
//                "Duplicate employee code rejected successfully."
//        );
//    }
//
//
//    // --------------------------------------------------
//    // AFTER METHOD
//    // --------------------------------------------------
//
//    @AfterMethod
//    public void tearDown() throws Exception {
//
//        System.out.println(
//                "========== AFTER TEST =========="
//        );
//
//        if (mocks != null) {
//            mocks.close();
//        }
//    }
//}