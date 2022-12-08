package ba.unsa.etf.rpr.dao;
import ba.unsa.etf.rpr.domain.Attendance;

import java.util.List;

/**
 * @Kadrić Nerma
 * Interface for Attendance domain bean as Dao
 * method getbyworkhours actually gives  to user list of employees by how much hours every of them spend at work
 */
public interface AttendanceDao extends Dao<Attendance> {
    List<Attendance> getByWorkHours(int hours);
}
