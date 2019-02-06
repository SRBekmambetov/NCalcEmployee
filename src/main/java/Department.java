import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Getter
@Setter
public class Department {

    public static List<String> transfersEmployeesList = new ArrayList<>();
    private int id;
    private String name;
    private List<Employee> employeesList;

    public Department createNewDepartment(List<Department> departmentsList, Employee employee, String nameDepartment) {
        Department department = new Department();
        List<Employee> listEmployees = new ArrayList<>();
        employee.setId(listEmployees.size() +1);
        listEmployees.add(employee);
        department.setId(departmentsList.size() + 1);
        department.setName(nameDepartment);
        department.setEmployeesList(listEmployees);
        departmentsList.add(department);
        return department;
    }

    public static void calcAndPrintAverageSalaryDepartment(List<Department> departmentsList) {
        for (Department department: departmentsList) {
            System.out.println(calcAverageSalaryDepartments(department));
        }
    }

    public static BigDecimal calcAverageSalaryDepartments(Department department) {
        BigDecimal sumSalary = new BigDecimal(0);
        for (Employee employee: department.getEmployeesList()) {
            BigDecimal salary = employee.getSalary();
            sumSalary = sumSalary.add(salary);
        }
        BigDecimal averageSalary = sumSalary.divide(new BigDecimal(department.getEmployeesList().size()), 4, RoundingMode.HALF_UP);
        return averageSalary;
    }

    public static void compareSalary(List<Department> departmentsList) throws NullPointerException {
        Department department1 = new Department();
        Department department2 = new Department();
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите наименование 1-го отдела");
        String introducedDepartment1 = sc.nextLine();
        System.out.println("Введите наименование 2-го отдела");
        String introducedDepartment2 = sc.nextLine();
        for (Department department: departmentsList) {
            String currNameDepartment = department.getName();
            if (currNameDepartment.equals(introducedDepartment1)) {
                department1 = department;
            }
            if (currNameDepartment.equals(introducedDepartment2)) {
                department2 = department;
            }
        }

        BigDecimal averrageSalary1 = calcAverageSalaryDepartments(department1);
        BigDecimal averrageSalary2 = calcAverageSalaryDepartments(department2);

        List<Employee> employeesList = department1.getEmployeesList();
        for (Employee employee: employeesList) {
            if (employee.getSalary().compareTo(averrageSalary1) == -1 && employee.getSalary().compareTo(averrageSalary2) == 1) {
                transfersEmployeesList.add(department1.getName() + ": " + employee.getFirstName() + " --> " + department2.getName());
            }
        }
    }

    public static void printDepartmentsInfo(List<Department> departmentsList) {
        for (Department department: departmentsList) {
            System.out.println(department.getId());
            System.out.println(department.getName());
            System.out.println(department.getEmployeesList());
        }
    }
}
