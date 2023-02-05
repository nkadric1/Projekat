package ba.unsa.etf.rpr.domain;

import java.time.LocalDate;
import java.util.Objects;

/**
 * @author Kadric Nerma
 * this is the bean for employee
 */
public class Employee implements Idable {

    private int ID;
    private String first_name;
    private String Last_name;
    private String address;
    private LocalDate hire_date;
    public Departments getDepartment() {
        return department;
    }

    public void setDepartment(Departments department) {
        this.department = department;
    }

    private Project project;

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    private Departments department;
    private String edu;
    private int payoff;


    public Employee() {
    }

    public Employee(int ID, String first_name, String last_name, String address, LocalDate hire_date, Departments department, Project project, String edu, int payoff) {
        this.ID = ID;
        this.first_name = first_name;
        this.Last_name = last_name;
        this.address = address;
        this.hire_date = hire_date;
        this.department = department;
        this.project = project;
        this.edu = edu;
        this.payoff = payoff;

    }
    public Employee( String first_name, String last_name, String address, LocalDate hire_date, Departments department, Project project, String edu, int payoff) {
        this.first_name = first_name;
        this.Last_name = last_name;
        this.address = address;
        this.hire_date = hire_date;
        this.department = department;
        this.project = project;
        this.edu = edu;
        this.payoff = payoff;

    }
    public void setId(int id) {
        this.ID = id;
    }
    public int getId() {
        return ID;
    }
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.Last_name = last_name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setHire_date(LocalDate hire_date) {
        this.hire_date = hire_date;
    }
    public void setEdu(String edu) {
        this.edu = edu;
    }

    public void setPayoff(int payoff) {
        this.payoff = payoff;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return Last_name;
    }

    public String getAddress() {
        return address;
    }

    public LocalDate getHire_date() {
        return hire_date;
    }
    public String getEdu() {
        return edu;
    }

    public int getPayoff() {
        return payoff;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return ID == employee.ID && department == employee.department && project == employee.project && payoff == employee.payoff && first_name.equals(employee.first_name) && Last_name.equals(employee.Last_name) && address.equals(employee.address) && hire_date.equals(employee.hire_date) && edu.equals(employee.edu);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "ID=" + ID +
                ", first_name='" + first_name + '\'' +
                ", Last_name='" + Last_name + '\'' +
                ", address='" + address + '\'' +
                ", hire_date=" + hire_date +
                ", department_id=" + department +
                ", project_id=" + project +
                ", edu='" + edu + '\'' +
                ", payoff=" + payoff +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, first_name, Last_name, address, hire_date, department, project, edu, payoff);
    }
}
