package ba.unsa.etf.rpr.dao;
import ba.unsa.etf.rpr.domain.Attendance;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/** @author Kadrić Nerma
 * class where is implementation of methods we will use to manipulate the attendances
 */
public class AttendanceDAOSQLImpl implements AttendanceDao{
    private Connection con;
    public AttendanceDAOSQLImpl() {

            try {
                this.con = DriverManager.getConnection("jdbc:mysql://sql7.freemysqlhosting.net:3306/sql7582729", "sql7582729", "7F1FfHWIiY");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
    }

    /**
     * method that works with id
     *   @return maxId+1 for a new row in which we can put a new object

     */
    private int getMaxId(){
        int id=1;
        try {
            PreparedStatement tmp = this.con.prepareStatement("SELECT MAX(ID_att)+1 FROM Attendance");
            ResultSet r= tmp.executeQuery();
            if(r.next()) {
                id = r.getInt(1);
                r.close();
                return id;
            }
        } catch (SQLException e) {
            System.out.println("Problem with DB");
            System.out.println(e.getMessage());
        }
        return id;
    }

    /**
     * this method returns Attendance that is the biggest
     * @return
     */
    @Override
    public int getMaxAttendance() {
           int h=1;
           try{
               PreparedStatement tmp=this.con.prepareStatement("SELECT  MAX(Workhours) FROM Attendance");
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

    /**
     *
     * @param ID
     * @return department of Id which we passed as parameter
     */
    @Override
    public Attendance getById(int ID) {
        String q="SELECT * FROM Attendance WHERE ID_att = ?";
        try{
            PreparedStatement s=this.con.prepareStatement(q);
            s.setInt(1,ID);
            ResultSet r=s.executeQuery();
            if(r.next()){
               Attendance a=new Attendance();
                a.setID(r.getInt(1));
               a.setHours(r.getInt(2));
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

    /**
     * this method add new attendance into the base
     * @param x
     * @return added attendance
     */
    @Override
    public Attendance add(Attendance x) {
        int id = getMaxId();
        try {
            PreparedStatement tmp= this.con.prepareStatement("INSERT INTO Attendance VALUES (id, x.getHours())");
            tmp.executeUpdate();
            x.setID(id);
            return x;
        } catch (SQLException e) {
            System.out.println("Problem with DB");
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * this method updates the database with the parameter we passed to it
     * @param x
     * @return updated attendance
     */
    @Override
    public Attendance update(Attendance x) {
        try{
            PreparedStatement tmp = this.con.prepareStatement("UPDATE Attendance SET Workhours=? WHERE ID_att=?");
            tmp.setInt(1, x.getID());
            tmp.setInt(2, x.getHours());

            tmp.executeUpdate();
            return x;
        }catch (SQLException e){
            System.out.println("Problem with DB");
            System.out.println(e.getMessage());
        }
        return null;
    }
/**
 * this method deletes the department whose id is passed
 */
    @Override
    public void delete(int ID) {
        try{
            PreparedStatement tmp = this.con.prepareStatement("DELETE FROM Attendance WHERE ID_att = ?");
            tmp.setInt(1, ID);
            tmp.executeUpdate();
        }catch (SQLException e){
            System.out.println("Problem with DB");
            System.out.println(e.getMessage());
        }
    }

    /**
     *
     * @return list of attendance
     */
    @Override
    public List<Attendance> getAll() {
        List<Attendance> att = new ArrayList<>();
        try{
            PreparedStatement tmp= this.con.prepareStatement("SELECT * FROM Attendance");
            ResultSet r = tmp.executeQuery();
            while (r.next()){
                Attendance a=new Attendance();
                a.setID(r.getInt(1));
               a.setHours(r.getInt(2));
                att.add(a);
            }
            r.close();
        }catch (SQLException e){
            System.out.println("Problem with DB");
            System.out.println(e.getMessage());
        }
        return att;

    }
}
