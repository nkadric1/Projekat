package ba.unsa.etf.rpr.domain;

import java.util.Objects;

public class Departments implements Idable {


    private int ID;
    private String depname;
    private int hourlypay;

    public Departments() {
    }

    public Departments(int id) {
        ID = id;
    }

    public Departments(int ID, String depname, int hourlypay) {
        this.ID = ID;
        this.depname = depname;
        this.hourlypay = hourlypay;
    }

    public void setId(int id) {
        this.ID = id;
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
        return depname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Departments)) return false;
        Departments that = (Departments) o;
        return ID == that.ID && hourlypay == that.hourlypay && depname.equals(that.depname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, depname, hourlypay);
    }


}
