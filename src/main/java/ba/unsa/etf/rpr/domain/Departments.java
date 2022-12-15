package ba.unsa.etf.rpr.domain;

import java.util.Objects;

public class Departments {
    public Departments(int ID, String depname, int hourlypay) {
        this.ID = ID;
        this.depname = depname;
        this.hourlypay = hourlypay;
    }

    public Departments() {
    }

    private int ID;
    private String depname;
    private int hourlypay;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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
        return "Departments{" +
                "ID=" + ID +
                ", depname='" + depname + '\'' +
                ", hourlypay=" + hourlypay +
                '}';
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
