# Payroll Management System

This is a simple payroll management system implemented in Java OOPS. The system supports managing full-time and part-time employees, including functionalities such as adding, removing, updating, displaying, and sorting employees.

## Features

- Add Full-Time and Part-Time Employees
- Remove Employees
- Update Employee Details
  - Update name
  - Update monthly salary for full-time employees
  - Update hours worked and hourly rate for part-time employees
- Display Employees
- Search Employees by ID or Name
- Sort Employees by ID, Name, or Salary

## Classes

### Employee (Abstract Class)

- Fields:
  - `int id`
  - `String name`
- Methods:
  - `public Employee(int id, String name)`
  - `public int getId()`
  - `public String getName()`
  - `public void setName(String name)`
  - `public abstract double calculateSalary()`
  - `public abstract String getEmployeeType()`
  - `public String toString()`

### FullTimeEmployee (Extends Employee)

- Fields:
  - `double monthlySalary`
- Methods:
  - `public FullTimeEmployee(int id, String name, double monthlySalary)`
  - `public double calculateSalary()`
  - `public String getEmployeeType()`
  - `public void setMonthlySalary(double monthlySalary)`

### PartTimeEmployee (Extends Employee)

- Fields:
  - `int hoursWorked`
  - `double hourlyRate`
- Methods:
  - `public PartTimeEmployee(int id, String name, int hoursWorked, double hourlyRate)`
  - `public double calculateSalary()`
  - `public String getEmployeeType()`
  - `public void setHoursWorked(int hoursWorked)`
  - `public void setHourlyRate(double hourlyRate)`

### PayrollSystem

- Fields:
  - `ArrayList<Employee> employeeList`
- Methods:
  - `public PayrollSystem()`
  - `public void addEmployee(Employee employee)`
  - `public void removeEmployee(int id)`
  - `public void updateEmployee(int id, Scanner scanner)`
  - `public void displayEmployees()`
  - `public void searchEmployeeById(int id)`
  - `public void searchEmployeeByName(String name)`
  - `public void sortEmployeesById()`
  - `public void sortEmployeesByName()`
  - `public void sortEmployeesBySalary()`


