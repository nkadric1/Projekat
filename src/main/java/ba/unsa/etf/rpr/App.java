package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.business.DepartmentManager;
import ba.unsa.etf.rpr.business.EmployeeManager;
import ba.unsa.etf.rpr.business.ProjectManager;
import ba.unsa.etf.rpr.domain.Departments;
import ba.unsa.etf.rpr.domain.Project;
import org.apache.commons.cli.*;


import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.*;

import ba.unsa.etf.rpr.domain.Employee;


/**
 * Command Line Interface implementation in this class
 */

public class App {
    private static final Option addEmployee = new Option("e", "add-employee", false, "Adding new employee to database of company");
    private static final Option addProject = new Option("p", "add-project", false, "Adding new project to database of company");
    private static final Option getEmployees = new Option("getE", "get-employees", false, "Printing all employees from DB");
    private static final Option getProjects = new Option("getP", "get-projects", false, "Printing all projects from DB");
    private static final Option updateDepartment = new Option("uD", "update", false, "Updating the hourly price of the department");
    private static final Option deleteProject = new Option("d", "delete", false, "Deleting project");
    private static final Option firstname = new Option("fn", "first-name", false, "Defining the employee first-name");
    private static final Option lastname = new Option("ln", "last-name", false, "Defining the employee last-name");
    private static final Option address = new Option("ad", "addressofemp", false, "Defining the employee address");
    private static final Option hiredate = new Option("hd", "hire-date", false, "Defining the hire date when employee started to work");
    private static final Option depID = new Option("dId", "departmentfk", false, "Defining the department where employee will work");
    private static final Option proID = new Option("pId", "projectfk", false, "Defining the project on which employee will work");
    private static final Option education = new Option("ed", "edustatus", false, "Defining the employee's status of education");
    private static final Option salary = new Option("s", "sal", false, "Defining the employee's salary");

    public static void printFormattedOptions(Options options) {
        HelpFormatter helpFormatter = new HelpFormatter();
        PrintWriter printWriter = new PrintWriter(System.out);
        helpFormatter.printUsage(printWriter, 150, "java -jar Projekat.jar [option] 'something else if needed' ");
        helpFormatter.printOptions(printWriter, 150, options, 2, 7);
        printWriter.close();
    }

    public static Options addOptions() {
        Options options = new Options();
        options.addOption(addEmployee);
        options.addOption(addProject);
        options.addOption(updateDepartment);
        options.addOption(deleteProject);
        options.addOption(getEmployees);
        options.addOption(getProjects);
        options.addOption(firstname);
        options.addOption(lastname);
        options.addOption(address);
        options.addOption(hiredate);
        options.addOption(depID);
        options.addOption(proID);
        options.addOption(education);
        options.addOption(salary);
        return options;
    }

    public static Project searchThroughProjects(List<Project> listOfPro, String eName) {

        Project e = listOfPro.stream().filter(p -> p.getProject_name().equals(eName.toLowerCase())).findAny().get();
        return e;

    }

    public static Departments searchThroughDepartments(List<Departments> listOfDep, int h) {

        Departments e = listOfDep.stream().filter(d -> d.getHourlypay() == h).findAny().orElse(null);
        return e;

    }

    public static void main(String[] args) throws Exception {
        Options options = addOptions();

        CommandLineParser commandLineParser = new DefaultParser();
        CommandLine cl = commandLineParser.parse(options, args);
        if (cl.hasOption(addEmployee.getOpt()) || cl.hasOption(addEmployee.getLongOpt())) {
            EmployeeManager employeeManager = new EmployeeManager();
            LocalDate l = LocalDate.parse(cl.getArgList().get(3));
            Employee e = new Employee(cl.getArgList().get(0), cl.getArgList().get(1), cl.getArgList().get(2), l, new Departments(Integer.parseInt(cl.getArgList().get(4))), new Project(Integer.parseInt(cl.getArgList().get(5))), cl.getArgList().get(6), Integer.parseInt(cl.getArgList().get(7)));
            employeeManager.add(e);
            System.out.println("You successfully added employee to the company DB!");
        } else if (cl.hasOption(getEmployees.getOpt()) || cl.hasOption(getEmployees.getLongOpt())) {
            EmployeeManager eManager = new EmployeeManager();
            eManager.getAll().forEach(q -> System.out.println(q.getFirst_name() + " " + q.getLast_name()));
        } else if (cl.hasOption(addProject.getOpt()) || cl.hasOption(addProject.getLongOpt())) {
            try {
                ProjectManager pManager = new ProjectManager();
                Project p = new Project();
                p.setProject_name(cl.getArgList().get(0));
                pManager.add(p);
                System.out.println("Project has been added successfully");
            } catch (Exception e) {
                System.out.println("There is already project with same name in database! Try again");
                System.exit(1);
            }

        } else if (cl.hasOption(getProjects.getOpt()) || cl.hasOption(getProjects.getLongOpt())) {
            ProjectManager p = new ProjectManager();
            p.getAll().forEach(c -> System.out.println(c.getProject_name()));
        } else if (cl.hasOption("uD")) {
            DepartmentManager d = new DepartmentManager();
            int hpay = Integer.parseInt(cl.getArgList().get(0));
            d.update(searchThroughDepartments(d.getAll(), hpay));
            System.out.println("Department has been updated successfully");
        } else if (cl.hasOption("d")) {
            int pid = Integer.parseInt(cl.getArgList().get(0));
            ProjectManager projectManager = new ProjectManager();
            projectManager.delete(pid);
            System.out.println("Project has been deleted successfully");

        } else {
            printFormattedOptions(options);
            System.exit(-1);
        }

    }
}
