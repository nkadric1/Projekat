package ba.unsa.etf.rpr.domain;

import ba.unsa.etf.rpr.dao.DepartmentDAOSQLImpl;

import java.util.Objects;

public class Department implements Idable{



    private int ID;
    private String depname;
    private int hourlypay;
    public Department() {
    }
    public Department(int ID, String depname, int hourlypay) {
        this.ID = ID;
        this.depname = depname;
        this.hourlypay = hourlypay;
    }

    public void setId(int id) {
      this.ID=id;
    }


    public int getId() {
        return ID;
    }

    public String getDepname() {
        return depname;
    }

    public void setDepname(String depname) {
        this.depname = depname;
    }

    public int getHourlypay() {
        return hourlypay;
    }

    public void setHourlypay(int hourlypay) {
        this.hourlypay = hourlypay;
    }

    @Override
    public String toString() {
        return "Department{" +
                "ID=" + ID +
                ", depname='" + depname + '\'' +
                ", hourlypay=" + hourlypay +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Department)) return false;
        Department that = (Department) o;
        return ID == that.ID && hourlypay == that.hourlypay && depname.equals(that.depname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, depname, hourlypay);
    }


}
