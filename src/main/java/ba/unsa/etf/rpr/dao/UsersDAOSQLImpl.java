package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Employee;
import ba.unsa.etf.rpr.domain.Users;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;

public class UsersDAOSQLImpl extends AbstractDao<Users> implements UsersDao{
    public UsersDAOSQLImpl(){
        super("Users");
    }
    @Override
    public boolean validate(String u, String p) {
        String q="SELECT * FROM Users WHERE username = ? AND password = ?";
        try{
            PreparedStatement s=getConnection().prepareStatement(q);
            s.setString(1, u);
            s.setString(2,p);
            ResultSet r=s.executeQuery();

            while (r.next()){
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public Users rowtoobject(ResultSet r) {
        try{
         Users u=new Users();
            u.setId(r.getInt("id"));
            u.setUsername(r.getString("username"));
            u.setPass(r.getString("password"));

            return u;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Map<String, Object> objecttorow(Users object) {
        Map<String, Object> r=new TreeMap<String,Object>();
        r.put("id", object.getId());
        r.put("username", object.getUsername());
        r.put("password", object.getPass());
        return r;
    }
}
