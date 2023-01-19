package ba.unsa.etf.rpr.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

/**
 * @author Kadrić Nerma
 * this is the bean for employee
 */
public class Employee implements Idable {

    private int ID;
    private String first_name;
    private String Last_name;
    private String address;
    private LocalDate hire_date;
    private int department_id;

    public Departments getDepartment() {
        return department;
    }

    public void setDepartment(Departments department) {
        this.department = department;
    }

    private Departments department;
    private int project_id;
    private String edu;
    private int payoff;



    public Employee() {
    }
    public Employee(int ID, String first_name, String last_name, String address, LocalDate hire_date, int department_id, int project_id, String edu, int payoff) {
        this.ID = ID;
        this.first_name = first_name;
        this.Last_name = last_name;
        this.address = address;
        this.hire_date = hire_date;
        this.department_id = department_id;
        this.project_id = project_id;
        this.edu = edu;
        this.payoff = payoff;

    }



    public void setId(int id) {
        this.ID=id;
    }


    public int getId() {
        return ID;
    }
    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
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

    public void setDepartment_id(int department_id) {
        this.department_id = department_id;
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
        return  hire_date;
    }

    public int getDepartment_id() {
        return department_id;
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
        return ID == employee.ID && department_id == employee.department_id && project_id == employee.project_id && payoff == employee.payoff && first_name.equals(employee.first_name) && Last_name.equals(employee.Last_name) && address.equals(employee.address) && hire_date.equals(employee.hire_date) && edu.equals(employee.edu);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "ID=" + ID +
                ", first_name='" + first_name + '\'' +
                ", Last_name='" + Last_name + '\'' +
                ", address='" + address + '\'' +
                ", hire_date=" + hire_date +
                ", department_id=" + department_id +
                ", project_id=" + project_id +
                ", edu='" + edu + '\'' +
                ", payoff=" + payoff +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, first_name, Last_name, address, hire_date, department_id, project_id, edu, payoff);
    }
}
