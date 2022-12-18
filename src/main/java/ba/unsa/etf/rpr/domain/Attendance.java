package ba.unsa.etf.rpr.domain;
import ba.unsa.etf.rpr.dao.AttendanceDAOSQLImpl;
import ba.unsa.etf.rpr.dao.AttendanceDao;

import java.util.Objects;
public class Attendance implements Idable{

    private int ID;
    private int hours;
    public Attendance(int ID, int hours) {
        this.ID = ID;
        this.hours = hours;
    }
    public Attendance(){}
    public void setId(int id) {
this.ID=id;
    }


    public int getId() {
        return ID;
    }


    public int getHours() {
        return hours;
    }


    public void setHours(int hours) {
        this.hours = hours;
    }

    @Override
    public String toString() {
        return "Attendance{" +
                "ID=" + ID +
                ", hours=" + hours +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Attendance)) return false;
        Attendance that = (Attendance) o;
        return ID == that.ID && hours == that.hours;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, hours);
    }



}
