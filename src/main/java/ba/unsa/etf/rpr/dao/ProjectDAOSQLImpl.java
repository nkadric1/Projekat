package ba.unsa.etf.rpr.dao;


import ba.unsa.etf.rpr.domain.Project;
import ba.unsa.etf.rpr.exceptions.EmployeeException;

import java.sql.*;
import java.util.*;

/**
 * @author Kadric Nerma
 * MySQL implementation of the DAO
 */
public class ProjectDAOSQLImpl extends AbstractDao<Project> implements ProjectDao {
    private static ProjectDAOSQLImpl instance = null;

    public static ProjectDAOSQLImpl getInstance() {
        if (instance == null) instance = new ProjectDAOSQLImpl();
        return instance;
    }

    public static void removeInstance() {
        if (instance != null) instance = null;
    }

    public ProjectDAOSQLImpl() {
        super("Project");
    }


    @Override
    public Project rowtoobject(ResultSet r) throws EmployeeException {
        try {
            Project p = new Project();
            p.setId(r.getInt("id"));
            p.setProject_name(r.getString("Project_name"));
            return p;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Map<String, Object> objecttorow(Project object) {
        Map<String, Object> r = new TreeMap<String, Object>();
        r.put("id", object.getId());
        r.put("Project_name", object.getProject_name());
        return r;
    }
}
