package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Project;
import org.apache.commons.collections.map.TransformedSortedMap;

import java.io.FileReader;
import java.sql.*;
import java.util.*;

/** @author Kadrić Nerma
 * class where is implementation of methods we will use to manipulate the projects
 */
public class ProjectDAOSQLImpl extends AbstractDao<Project> implements ProjectDao{

    public ProjectDAOSQLImpl(){
       super("Project");
    }


    @Override
    public Project rowtoobject(ResultSet r) {
        try{
            Project p=new Project();
            p.setId(r.getInt("id"));
            p.setProject_name(r.getString("Project_name"));
            return p;
        }catch(SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Map<String, Object> objecttorow(Project object) {
        Map<String,Object> r=new TreeMap<String,Object>();
    r.put("id", object.getId());
    r.put("Project_name", object.getProject_name());
    return r;
    }
}
