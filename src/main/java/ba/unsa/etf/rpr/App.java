package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.business.DepartmentManager;
import ba.unsa.etf.rpr.business.EmployeeManager;
import ba.unsa.etf.rpr.business.ProjectManager;
import ba.unsa.etf.rpr.domain.Departments;
import ba.unsa.etf.rpr.domain.Project;
import org.apache.commons.cli.*;
import java.sql.Date;
import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Stream;
import ba.unsa.etf.rpr.dao.EmployeeDAOSQLImpl;
import ba.unsa.etf.rpr.domain.Employee;



/**
 * Command Line Interface implementation in this class
 */

public class App 
{
    private static final Option addEmployee=new Option("e","add-employee", false,"Adding new employee to database of company");
    private static final Option addProject=new Option("p", "add-project", false, "Adding new project to database of company");
    private static final Option getEmployees =new Option("getE", "get-employees", false, "Printing all employees from DB");
    private static final Option getProjects =new Option("getP", "get-projects", false, "Printing all projects from DB");
public static void printFormattedOptions(Options options){
    HelpFormatter helpFormatter=new HelpFormatter();
    PrintWriter printWriter=new PrintWriter(System.out);
    helpFormatter.printUsage(printWriter,150,"java -jar Projekat.jar [option] 'something else if needed' ");
helpFormatter.printOptions(printWriter,150,options,2,7);
printWriter.close();
}
public static  Options addOptions(){
    Options options=new Options();
    options.addOption(addEmployee);
    options.addOption(addProject);
    options.addOption(getEmployees);
    options.addOption(getProjects);
    return options;
}
    public static Project searchThroughProjects(List<Project> listOfPro, String eName) {

   Project e = null;
      e = listOfPro.stream().filter(emp -> emp.getProject_name().toLowerCase().equals(eName.toLowerCase())).findAny().get();
        return e;

    }
    public static Departments searchThroughDepartments(List<Departments> listOfDep, String eName) {

    Departments e = null;
        e = listOfDep.stream().filter(emp -> emp.getDepname().toLowerCase().equals(eName.toLowerCase())).findAny().get();
        return e;

    }
    public static void main( String[] args ) throws Exception
    {
        Options options = addOptions();

        CommandLineParser commandLineParser = new DefaultParser();

        CommandLine cl = commandLineParser.parse(options, args);
        if((cl.hasOption(addEmployee.getOpt()) || cl.hasOption(addEmployee.getLongOpt())) ){
          EmployeeManager eManager = new EmployeeManager();
           ProjectManager pManager = new ProjectManager();
            DepartmentManager departmentManager=new DepartmentManager();
            Project p = null;
            try {
              p = searchThroughProjects(pManager.getAll(), cl.getArgList().get(1));
            }catch(Exception e) {
                System.out.println("There is no project in the list! Try again.");
                System.exit(1);
            }
          Departments d = null;
            try {
                d = searchThroughDepartments(departmentManager.getAll(), cl.getArgList().get(1));
            }catch(Exception e) {
                System.out.println("There is no department in the list! Try again.");
                System.exit(1);
            }

           Employee e=new Employee();
            e.setFirst_name(cl.getArgList().get(0));
            e.setLast_name(cl.getArgList().get(0));
            e.setAddress(cl.getArgList().get(0));
                e.setHire_date(Date.valueOf(LocalDate.now()).toLocalDate());

            e.setDepartment(d);
            e.setProject(p);
            e.setEdu(cl.getArgList().get(0));
            e.setPayoff(Integer.parseInt(cl.getArgList().get(0)));
          eManager.add(e);
            System.out.println("You successfully added employee to database!");
        } else if(cl.hasOption(getEmployees.getOpt()) || cl.hasOption(getEmployees.getLongOpt())){
           EmployeeManager eManager = new EmployeeManager();
            eManager.getAll().forEach(q -> System.out.println(q.getFirst_name()+" "+ q.getLast_name()));
        } else if(cl.hasOption(addProject.getOpt()) || cl.hasOption(addProject.getLongOpt())){
            try {
          ProjectManager pManager = new ProjectManager();
              Project p = new Project();
                p.setProject_name(cl.getArgList().get(0));
             pManager.add(p);
                System.out.println("Project has been added successfully");
            }catch(Exception e) {
                System.out.println("There is already project with same name in database! Try again");
                System.exit(1);
            }

        } else if(cl.hasOption(getProjects.getOpt()) || cl.hasOption(getProjects.getLongOpt())){
           ProjectManager p=new ProjectManager();
            p.getAll().forEach(c -> System.out.println(c.getProject_name()));
        } else {
            printFormattedOptions(options);
            System.exit(-1);
        }

    }
}
