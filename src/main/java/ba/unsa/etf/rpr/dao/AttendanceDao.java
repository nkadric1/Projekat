package ba.unsa.etf.rpr.dao;
import ba.unsa.etf.rpr.domain.Attendance;

import java.util.List;

/**
 * @Kadrić Nerma
 * Interface for Attendance domain bean as Dao

 */
public interface AttendanceDao extends Dao<Attendance> {
    List<Attendance> getByWorkHours(int hours);
}
