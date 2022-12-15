package ba.unsa.etf.rpr.domain;
import java.util.Objects;
public class Attendance {
    public Attendance(int ID, int hours) {
        this.ID = ID;
        this.hours = hours;
    }
public Attendance(){}
    private int ID;
    private int hours;

    public int getID() {
        return ID;
    }

    public int getHours() {
        return hours;
    }

    public void setID(int ID) {
        this.ID = ID;
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
