package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Idable;
import ba.unsa.etf.rpr.exceptions.EmployeeException;

import java.sql.*;
import java.util.*;

/**
 * @param <T>
 * @author Kadric Nerma
 * This is abstract class that implements DAO CRUD methods for every entity
 */

public abstract class AbstractDao<T extends Idable> implements Dao<T> {
    private static Connection con = null;
    private String name;

    public AbstractDao(String n) {
        this.name = n;
        if (con == null) createConnection();
    }

    /**
     * This method creates connection to database
     */
    private static void createConnection() {
        if (AbstractDao.con == null) {
            try {
                Properties p = new Properties();
                p.load(ClassLoader.getSystemResource("database.properties").openStream());
                String url = p.getProperty("url");
                String username = p.getProperty("username");
                String password = p.getProperty("password");
                AbstractDao.con = DriverManager.getConnection(url, username, password);
            } catch (Exception e) {
                System.out.println("Problem with DB");
                e.printStackTrace();
            } finally {
                Runtime.getRuntime().addShutdownHook(new Thread() {
                    @Override
                    public void run() {
                        try {
                            con.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }
    }

    public static void closeConnection() {
        System.out.println("Method to close the connection has been called.");
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("REMOVE CONNECTION METHOD ERROR: Unable to close this connection!");
            }
        }
    }

    public Connection getConnection() {
        return AbstractDao.con;
    }

    /**
     * This method is for mapping resultset into object
     *
     * @param r - it is result set from DB
     * @return - the Bean object for table
     * @throws EmployeeException - in case of error with DB
     */
    public abstract T rowtoobject(ResultSet r) throws EmployeeException;

    /**
     * This method is for mapping object into map
     *
     * @param object-the Bean object for table
     * @return - (key,value) sorted map
     */
    public abstract Map<String, Object> objecttorow(T object);

    public T getById(int id) throws EmployeeException {
        return executeQUq("SELECT * FROM " + this.name + " WHERE id = ?", new Object[]{id});
    }

    public List<T> getAll() throws EmployeeException {
        return executeQ("SELECT * FROM " + name, null);
    }

    public void delete(int id) throws EmployeeException {
        String sq = "DELETE FROM " + name + " WHERE id = ?";
        try {
            PreparedStatement s = getConnection().prepareStatement(sq, Statement.RETURN_GENERATED_KEYS);
            s.setObject(1, id);
            s.executeUpdate();
        } catch (SQLException e) {
            throw new EmployeeException(e.getMessage(), e);
        }
    }

    public T update(T i) throws EmployeeException {
        Map<String, Object> r = objecttorow(i);
        String ucol = prepareUpdateParts(r);
        StringBuilder build = new StringBuilder();
        build.append("UPDATE ").append(name).append(" SET ").append(ucol).append(" WHERE id = ?");
        try {
            PreparedStatement s = getConnection().prepareStatement(build.toString());
            int c = 1;
            for (Map.Entry<String, Object> e : r.entrySet()) {
                if (e.getKey().equals("id")) continue;
                s.setObject(c, e.getValue());
                c++;
            }
            s.setObject(c, i.getId());
            s.executeUpdate();
            return i;
        } catch (SQLException e) {
            throw new EmployeeException(e.getMessage(), e);
        }

    }

    public T add(T i) throws EmployeeException {
        Map<String, Object> r = objecttorow(i);
        Map.Entry<String, String> col = prepareInsertParts(r);
        StringBuilder build = new StringBuilder();
        build.append("INSERT INTO ").append(name);
        build.append(" (").append(col.getKey()).append(") ");
        build.append("VALUES (").append(col.getValue()).append(")");
        try {
            PreparedStatement s = getConnection().prepareStatement(build.toString(), Statement.RETURN_GENERATED_KEYS);
            int c = 1;
            for (Map.Entry<String, Object> e : r.entrySet()) {
                if (e.getKey().equals("id")) continue;
                s.setObject(c, e.getValue());
                c++;
            }
            s.executeUpdate();
            ResultSet rset = s.getGeneratedKeys();
            rset.next();
            i.setId(rset.getInt(1));
            return i;
        } catch (SQLException e) {
            throw new EmployeeException(e.getMessage(), e);
        }

    }

    /**
     * @param r - accepts KV storage of column names
     * @return - CSV of columns and question marks for insert statement
     */
    private Map.Entry<String, String> prepareInsertParts(Map<String, Object> r) {
        StringBuilder col = new StringBuilder();
        StringBuilder que = new StringBuilder();
        int c = 1;
        for (Map.Entry<String, Object> e : r.entrySet()) {
            if (e.getKey().equals("id")) continue;
            col.append(e.getKey());
            que.append("?");
            if (r.size() - 1 != c) {
                col.append(",");
                que.append(",");
            }
            c++;
        }
        return new AbstractMap.SimpleEntry<String, String>(col.toString(), que.toString());
    }

    /**
     * @param r - row to be converted into string
     * @return - string for update statement
     */
    private String prepareUpdateParts(Map<String, Object> r) {
        StringBuilder col = new StringBuilder();
        int c = 0;
        for (Map.Entry<String, Object> e : r.entrySet()) {
            if (e.getKey().equals("id")) continue;
            c++;
            col.append(e.getKey()).append("= ?");
            if (r.size() != c + 1) {
                col.append(",");
            }
        }
        return col.toString();
    }

    /**
     * Method for executing any kind of query
     *
     * @param q - SQL query
     * @param p - params for query
     * @return - list of objects from DB
     * @throws EmployeeException - in case of error with DB
     */
    public List<T> executeQ(String q, Object[] p) throws EmployeeException {
        try {
            PreparedStatement tmp = getConnection().prepareStatement(q);
            if (p != null) {
                for (int i = 1; i <= p.length; i++) tmp.setObject(i, p[i - 1]);
            }
            ResultSet r = tmp.executeQuery();
            ArrayList<T> rlist = new ArrayList<>();
            while (r.next()) rlist.add(rowtoobject(r));
            return rlist;
        } catch (SQLException e) {
            throw new EmployeeException(e.getMessage(), e);
        }
    }

    /**
     * Method for query execution that always return single record
     *
     * @param q - query that returns single record
     * @param p - list of params for SQL query
     * @return - object
     * @throws EmployeeException - in case of error with DB
     */
    public T executeQUq(String q, Object[] p) throws EmployeeException {
        List<T> r = executeQ(q, p);
        if (r != null && r.size() == 1) {
            return r.get(0);
        } else {
            throw new EmployeeException("Not found!");
        }
    }

}