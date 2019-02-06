import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@ToString
public class Employee {

    private int id;
    private String firstName;
    private String secondName;
    private String middleName;
    private BigDecimal salary;

    public Employee createEmployee(String firstName, String secondName, String middleName, BigDecimal salary) {
        Employee employee = new Employee();
        employee.setFirstName(firstName);
        employee.setSecondName(secondName);
        employee.setMiddleName(middleName);
        employee.setSalary(salary);
        return employee;
    }

    public static void addEmployee(List<Department> departmentsList, Employee employee, String nameDepartment) {
        for (Department department: departmentsList) {
            String currNameDepartment = department.getName();
            if (currNameDepartment.equals(nameDepartment)) {
                List<Employee> employeesList = department.getEmployeesList();
                employee.setId(employeesList.size() + 1);
                employeesList.add(employee);
                return;
            }
        }
        Department department = new Department();
        department = department.createNewDepartment(departmentsList, employee, nameDepartment);
    }
}