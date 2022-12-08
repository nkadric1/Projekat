package ba.unsa.etf.rpr.dao;
import ba.unsa.etf.rpr.domain.Attendance;
import ba.unsa.etf.rpr.domain.Departments;
import ba.unsa.etf.rpr.domain.Employee;
import ba.unsa.etf.rpr.domain.Project;
import org.apache.maven.shared.invoker.SystemOutHandler;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class AttendanceDAOSQLImpl implements AttendanceDao{
    private Connection con;
    public AttendanceDAOSQLImpl() {

            try {
                this.con = DriverManager.getConnection("jdbc:mysql://sql7.freemysqlhosting.net:3306/sql7582729", "sql7582729", "7F1FfHWIiY");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
    }

    @Override
    public int getMaxAttendance() {
           int h=1;
           try{
               PreparedStatement tmp=this.con.prepareStatement("SELECT  MAX(Workhours) FROM Attendance");
               ResultSet r=tmp.executeQuery();
               if(r.next()){
                   h=r.getInt(2);
                   r.close();
                   return h;
               }
           }catch(SQLException e){
               System.out.println("Problem with DB");
               System.out.println(e.getMessage());
           }
        return h;
    }

    @Override
    public Attendance getById(int ID) {
        String q="SELECT * FROM Attendance WHERE ID = ?";
        try{
            PreparedStatement s=this.con.prepareStatement(q);
            s.setInt(1,ID);
            ResultSet r=s.executeQuery();
            if(r.next()){
               Attendance a=new Attendance();
                a.setID(r.getInt("ID_att"));
               a.setHours(r.getInt("Workhours"));
                r.close();
                return a;
            } else
            { return null;}

        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Attendance add(Attendance x) {
        return null;
    }

    @Override
    public Attendance update(Attendance x) {
        return null;
    }

    @Override
    public void delete(int ID) {

    }

    @Override
    public List<Attendance> getAll() {
        return null;
    }
}
