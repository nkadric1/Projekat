package ba.unsa.etf.rpr.dao;
import ba.unsa.etf.rpr.domain.Departments;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAOSQLImpl implements  DepartmentDao{
    private Connection con;
    public DepartmentDAOSQLImpl() {

        try {
            this.con = DriverManager.getConnection("jdbc:mysql://sql7.freemysqlhosting.net:3306/sql7582729", "sql7582729", "7F1FfHWIiY");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    private int getMaxId(){
        int id=1;
        try {
            PreparedStatement tmp = this.con.prepareStatement("SELECT MAX(ID_dep)+1 FROM Departments");
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
    @Override
    public Departments getById(int ID) {
        String q="SELECT * FROM Departments WHERE ID_dep = ?";
        try{
            PreparedStatement s=this.con.prepareStatement(q);
            s.setInt(1,ID);
            ResultSet r=s.executeQuery();
            if(r.next()){
               Departments d=new Departments();
                d.setID(r.getInt(1));
                d.setHourlypay(r.getInt(2));
                d.setDepname(r.getString(3));
                r.close();
                return d;
            } else
            { return null;}

        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Departments add(Departments x) {
        int id = getMaxId();
        try {                                          //zasto se ne uzima metoda iz deps
            PreparedStatement tmp= this.con.prepareStatement("INSERT INTO Departments VALUES (id, x.getHourlypay(), x.getDepname())");
            tmp.executeUpdate();
            x.setID(id);
            return x;
        } catch (SQLException e) {
            System.out.println("Problem with DB");
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Departments update(Departments x) {
        try{
            PreparedStatement tmp = this.con.prepareStatement("UPDATE Departments SET Hourly_pay=?, Department_name=? WHERE ID_dep=?");
            tmp.setInt(1, x.getID());
            tmp.setInt(2, x.getHourlypay());
                tmp.setString(3, x.getDepname());
            tmp.executeUpdate();
            return x;
        }catch (SQLException e){
            System.out.println("Problem with DB");
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void delete(int ID) {
        try{
            PreparedStatement tmp = this.con.prepareStatement("DELETE FROM Departments WHERE ID_dep = ?");
            tmp.setInt(1, ID);
            tmp.executeUpdate();
        }catch (SQLException e){
            System.out.println("Problem with DB");
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Departments> getAll() {
        List<Departments> d = new ArrayList<>();
        try{
            PreparedStatement tmp= this.con.prepareStatement("SELECT * FROM Departments");
            ResultSet r = tmp.executeQuery();
            while (r.next()){
                Departments a=new Departments();
                a.setID(r.getInt(1));
                //kako ovo popraviti??
                  // a.setDepname(new DepartmentDAOSQLImpl().getD(r.getString(2)));
                d.add(a);
            }
            r.close();
        }catch (SQLException e){
            System.out.println("Problem with DB");
            System.out.println(e.getMessage());}
        return d;
    }

    @Override
    public Departments ReturnDepartmentForId(int id) {
        String q = "SELECT * FROM Departments WHERE ID_dep = ?";
        try {
            PreparedStatement tmp = this.con.prepareStatement(q);
            tmp.setInt(1, id);
            ResultSet r = tmp.executeQuery();
            if (r.next()) {

                Departments c = new Departments();
                c.setID(r.getInt(1));
                c.setDepname(r.getString(2));
                c.setHourlypay(r.getInt(3));
                return c;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
