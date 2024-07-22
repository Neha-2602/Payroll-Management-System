import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.io.Serializable;

abstract class Employee implements Serializable {
    private int id;
    private String name;

    public Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract double calculateSalary();

    public abstract String getEmployeeType();

    @Override
    public String toString() {
        return String.format("| %10d | %-20s | %-10s | %15.2f |", id, name, getEmployeeType(), calculateSalary());
    }
}

class FullTimeEmployee extends Employee {
    private double monthlySalary;

    public FullTimeEmployee(int id, String name, double monthlySalary) {
        super(id, name);
        this.monthlySalary = monthlySalary;
    }

    @Override
    public double calculateSalary() {
        return monthlySalary;
    }

    @Override
    public String getEmployeeType() {
        return "Full-Time";
    }

    public void setMonthlySalary(double monthlySalary) {
        this.monthlySalary = monthlySalary;
    }
}

class PartTimeEmployee extends Employee {
    private int hoursWorked;
    private double hourlyRate;

    public PartTimeEmployee(int id, String name, int hoursWorked, double hourlyRate) {
        super(id, name);
        this.hoursWorked = hoursWorked;
        this.hourlyRate = hourlyRate;
    }

    @Override
    public double calculateSalary() {
        return hoursWorked * hourlyRate;
    }

    @Override
    public String getEmployeeType() {
        return "Part-Time";
    }

    public void setHoursWorked(int hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }
}

class PayrollSystem {
    private ArrayList<Employee> employeeList;

    public PayrollSystem() {
        employeeList = new ArrayList<>();
    }

    public void addEmployee(Employee employee) {
        employeeList.add(employee);
    }

    public void removeEmployee(int id) {
        Employee employeeToRemove = null;
        for (Employee employee : employeeList) {
            if (employee.getId() == id) {
                employeeToRemove = employee;
                break;
            }
        }

        if (employeeToRemove != null) {
            employeeList.remove(employeeToRemove);
            System.out.println("Employee with ID " + id + " removed successfully.");
        } else {
            System.out.println("Employee with ID " + id + " not found.");
        }
    }

    public void updateEmployee(int id, Scanner scanner) {
        Employee employeeToUpdate = null;
        for (Employee employee : employeeList) {
            if (employee.getId() == id) {
                employeeToUpdate = employee;
                break;
            }
        }

        if (employeeToUpdate != null) {
            System.out.println("Employee found: " + employeeToUpdate);

            System.out.println("What do you want to update?");
            System.out.println("1. Name");
            if (employeeToUpdate instanceof FullTimeEmployee) {
                System.out.println("2. Monthly Salary");
            } else if (employeeToUpdate instanceof PartTimeEmployee) {
                System.out.println("2. Hours Worked");
                System.out.println("3. Hourly Rate");
            }

            int updateChoice = scanner.nextInt();
            scanner.nextLine();

            switch (updateChoice) {
                case 1:
                    System.out.print("Enter new Name: ");
                    String newName = scanner.nextLine();
                    employeeToUpdate.setName(newName);
                    break;
                case 2:
                    if (employeeToUpdate instanceof FullTimeEmployee) {
                        System.out.print("Enter new Monthly Salary: ");
                        double newSalary = scanner.nextDouble();
                        ((FullTimeEmployee) employeeToUpdate).setMonthlySalary(newSalary);
                    } else if (employeeToUpdate instanceof PartTimeEmployee) {
                        System.out.print("Enter new Hours Worked: ");
                        int newHoursWorked = scanner.nextInt();
                        ((PartTimeEmployee) employeeToUpdate).setHoursWorked(newHoursWorked);
                    }
                    break;
                case 3:
                    if (employeeToUpdate instanceof PartTimeEmployee) {
                        System.out.print("Enter new Hourly Rate: ");
                        double newHourlyRate = scanner.nextDouble();
                        ((PartTimeEmployee) employeeToUpdate).setHourlyRate(newHourlyRate);
                    } else {
                        System.out.println("Invalid choice.");
                    }
                    break;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }

            System.out.println("Employee with ID " + id + " updated successfully.");
        } else {
            System.out.println("Employee with ID " + id + " not found.");
        }
    }

    public void displayEmployees() {
        System.out.println(String.format("| %10s | %-20s | %-10s | %15s |", "ID", "Name", "Type", "Salary"));
        System.out.println("------------------------------------------------------------------------------");
        for (Employee employee : employeeList) {
            System.out.println(employee);
        }
        System.out.println("------------------------------------------------------------------------------");
        System.out.println("Total number of employees: " + employeeList.size());
    }

    public void searchEmployeeById(int id) {
        for (Employee employee : employeeList) {
            if (employee.getId() == id) {
                System.out.println(employee);
                return;
            }
        }
        System.out.println("Employee with ID " + id + " not found.");
    }

    public void searchEmployeeByName(String name) {
        for (Employee employee : employeeList) {
            if (employee.getName().equalsIgnoreCase(name)) {
                System.out.println(employee);
                return;
            }
        }
        System.out.println("Employee with name " + name + " not found.");
    }

    public void sortEmployeesById() {
        Collections.sort(employeeList, Comparator.comparingInt(Employee::getId));
        System.out.println("Employees sorted by ID.");
    }

    public void sortEmployeesByName() {
        Collections.sort(employeeList, Comparator.comparing(Employee::getName));
        System.out.println("Employees sorted by Name.");
    }

    public void sortEmployeesBySalary() {
        Collections.sort(employeeList, Comparator.comparingDouble(Employee::calculateSalary));
        System.out.println("Employees sorted by Salary.");
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PayrollSystem payrollSystem = new PayrollSystem();

        while (true) {
            System.out.println("\nPayroll Management System");
            System.out.println("1. Add Full-Time Employee");
            System.out.println("2. Add Part-Time Employee");
            System.out.println("3. Remove Employee");
            System.out.println("4. Update Employee");
            System.out.println("5. Display Employees");
            System.out.println("6. Search Employee by ID");
            System.out.println("7. Search Employee by Name");
            System.out.println("8. Sort Employees by ID");
            System.out.println("9. Sort Employees by Name");
            System.out.println("10. Sort Employees by Salary");
            System.out.println("11. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter ID: ");
                    int ftId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Name: ");
                    String ftName = scanner.nextLine();
                    System.out.print("Enter Monthly Salary: ");
                    double monthlySalary = scanner.nextDouble();
                    if (monthlySalary < 0) {
                        System.out.println("Salary cannot be negative.");
                        break;
                    }
                    FullTimeEmployee ftEmployee = new FullTimeEmployee(ftId, ftName, monthlySalary);
                    payrollSystem.addEmployee(ftEmployee);
                    break;
                case 2:
                    System.out.print("Enter ID: ");
                    int ptId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Name: ");
                    String ptName = scanner.nextLine();
                    System.out.print("Enter Hours Worked: ");
                    int hoursWorked = scanner.nextInt();
                    if (hoursWorked < 0) {
                        System.out.println("Hours worked cannot be negative.");
                        break;
                    }
                    System.out.print("Enter Hourly Rate: ");
                    double hourlyRate = scanner.nextDouble();
                    if (hourlyRate < 0) {
                        System.out.println("Hourly rate cannot be negative.");
                        break;
                    }
                    PartTimeEmployee ptEmployee = new PartTimeEmployee(ptId, ptName, hoursWorked, hourlyRate);
                    payrollSystem.addEmployee(ptEmployee);
                    break;
                case 3:
                    System.out.print("Enter ID of Employee to Remove: ");
                    int removeId = scanner.nextInt();
                    payrollSystem.removeEmployee(removeId);
                    break;
                case 4:
                    System.out.print("Enter ID of Employee to Update: ");
                    int updateId = scanner.nextInt();
                    payrollSystem.updateEmployee(updateId, scanner);
                    break;
                case 5:
                    payrollSystem.displayEmployees();
                    break;
                case 6:
                    System.out.print("Enter ID to Search: ");
                    int searchId = scanner.nextInt();
                    payrollSystem.searchEmployeeById(searchId);
                    break;
                case 7:
                    scanner.nextLine();
                    System.out.print("Enter Name to Search: ");
                    String searchName = scanner.nextLine();
                    payrollSystem.searchEmployeeByName(searchName);
                    break;
                case 8:
                    payrollSystem.sortEmployeesById();
                    payrollSystem.displayEmployees();
                    break;
                case 9:
                    payrollSystem.sortEmployeesByName();
                    payrollSystem.displayEmployees();
                    break;
                case 10:
                    payrollSystem.sortEmployeesBySalary();
                    payrollSystem.displayEmployees();
                    break;
                case 11:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
