package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

public class ProjectDAOSQLImpl implements ProjectDao{
    private Connection con;
    public ProjectDAOSQLImpl(){
        try{
            this.con= DriverManager.getConnection("jdbc:mysql://sql7.freemysqlhosting.net:3306/sql7582729", "sql7582729", "7F1FfHWIiY");
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }


    @Override
    public Project getById(int ID) {
        return null;
    }

    @Override
    public Project add(Project x) {
        return null;
    }

    @Override
    public Project update(Project x) {
        return null;
    }

    @Override
    public void delete(int ID) {

    }

    @Override
    public List<Project> getAll() {
        return null;
    }

    @Override
    public List<Project> getByProjectName(String name) {
        return null;
    }
}
