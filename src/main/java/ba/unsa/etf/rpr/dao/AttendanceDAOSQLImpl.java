package ba.unsa.etf.rpr.dao;
import ba.unsa.etf.rpr.domain.Attendance;

import java.io.FileReader;
import java.sql.*;
import java.util.*;

/** @author Kadrić Nerma
 * class where is implementation of methods we will use to manipulate the attendances
 */
public class AttendanceDAOSQLImpl extends AbstractDao<Attendance> implements AttendanceDao{
public AttendanceDAOSQLImpl(){
    super("Attendance");
}
    @Override
    public Attendance rowtoobject(ResultSet r) {
    try{
        Attendance a=new Attendance();
        a.setId(r.getInt("id"));
        a.setHours(r.getInt("Workhours"));
        return a;
    }catch(SQLException e){
        e.printStackTrace();
    }
        return null;
    }

    @Override
    public Map<String, Object> objecttorow(Attendance object) {
    Map<String,Object> r=new TreeMap<String,Object>();
    r.put("id",object.getId());
    r.put("Workhours",object.getHours());
    return r;

    }

    @Override
    public int getMaxAttendance() {
        int h=1;
        try{
            PreparedStatement tmp=getConnection().prepareStatement("SELECT  MAX(Workhours) FROM Attendance");
            ResultSet r=tmp.executeQuery();
            if(r.next()){
                h=r.getInt(1);
                r.close();
                return h;
            }
        }catch(SQLException e){
            System.out.println("Problem with DB");
            System.out.println(e.getMessage());
        }
        return h;
    }
}
