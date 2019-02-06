import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static List<Department> departmentsList = new ArrayList<>();

    private static void reader() throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите название файла");
        String nameFile = sc.nextLine();
        File file = new File(nameFile);
        FileReader fileReader = null; // поток который подключается к текстовому файлу
        fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader); // соединяем FileReader с BufferedReader
        String line = "";
        processingData(bufferedReader, line);
    }

    public static void processingData(BufferedReader bufferedReader, String line) throws IOException {
        int j = 1;
        while ((line = bufferedReader.readLine()) != null) {
            String[] arr = line.split("\\|");
            String firstName = arr[0];
            String secondName = arr[1];
            String middleName = arr[2];
            String nameDepartment = arr[3];
            BigDecimal salary;
            try {
                salary = new BigDecimal(arr[4]);
            } catch (NumberFormatException e) {
                System.out.println("Неверно введена зарплата");
                continue;
            }
            Employee employee = new Employee();
            employee = employee.createEmployee(firstName, secondName, middleName, salary);
            if (j == 1) {
                Department department = new Department();
                department = department.createNewDepartment(departmentsList, employee, nameDepartment);
            } else {
                Employee.addEmployee(departmentsList, employee, nameDepartment);
            }
            j++;
        }
        bufferedReader.close(); // закрываем поток
    }

    public static void writer(List<String> transfersEmployeesList) {
        File file = new File("result.txt");
        try {
            FileWriter  fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (String line: transfersEmployeesList) {
                bufferedWriter.write(line + "\n");
            }
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("Невозможно записать файл");
        }
    }

    public static void main(String[] args) {
        try {
            reader();
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
            return;
        } catch (IOException e) {
            System.out.println("Файл не доступен для чтения");
            return;
        }
        Department.printDepartmentsInfo(departmentsList);
        Department.calcAndPrintAverageSalaryDepartment(departmentsList);
        System.out.println();
        try {
            Department.compareSalary(departmentsList);
        } catch (NullPointerException e) {
            System.out.println("Отделов с таким именем нет в файле sample.txt");
            return;
        }
        writer(Department.transfersEmployeesList);
    }
}