package ba.unsa.etf.rpr.dao;
import ba.unsa.etf.rpr.domain.Attendance;
import ba.unsa.etf.rpr.domain.Employee;

import java.util.List;

/**
 * @Kadrić Nerma
 * Interface for Attendance domain bean as Dao
 * method getMaxAttendance actually gives to user  work hours of employee that spends the most time on work
 */
public interface AttendanceDao extends Dao<Attendance> {
    int getMaxAttendance();
}
