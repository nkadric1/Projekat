package ba.unsa.etf.rpr.domain;
import java.util.Date;
import java.util.Objects;

/**
 * @author Kadrić Nerma
 * this is the bean for employee
 */
public class Employee {
    public Employee(int ID, String first_name, String last_name, String address, Date hire_date, int department_id, int project_id, int att_id, String edu, int payoff) {
        this.ID = ID;
        this.first_name = first_name;
        Last_name = last_name;
        this.address = address;
        this.hire_date = hire_date;
        this.department_id = department_id;
        this.project_id = project_id;
        this.att_id = att_id;
        this.edu = edu;
        this.payoff = payoff;
    }

    public Employee() {
    }

    private int ID;
    private String first_name;
    private String Last_name;
    private String address;
    private Date hire_date;
    private int department_id;
    private int project_id;
    private int att_id;
    private String edu;
    private int payoff;

    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    public int getAtt_id() {
        return att_id;
    }

    public void setAtt_id(int att_id) {
        this.att_id = att_id;
    }

    public int getID(){
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        Last_name = last_name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setHire_date(Date hire_date) {
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

    public java.sql.Date getHire_date() {
        return (java.sql.Date) hire_date;
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
        return ID == employee.ID && department_id == employee.department_id && project_id == employee.project_id && att_id == employee.att_id && payoff == employee.payoff && Objects.equals(first_name, employee.first_name) && Objects.equals(Last_name, employee.Last_name) && Objects.equals(address, employee.address) && Objects.equals(hire_date, employee.hire_date) && Objects.equals(edu, employee.edu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, first_name, Last_name, address, hire_date, department_id, project_id, att_id, edu, payoff);
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
                ", att_id=" + att_id +
                ", edu='" + edu + '\'' +
                ", payoff=" + payoff +
                '}';
    }
}
