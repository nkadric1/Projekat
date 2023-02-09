package ba.unsa.etf.rpr.dao;

import java.util.List;

import ba.unsa.etf.rpr.exceptions.EmployeeException;

/**
 * @author Kadric Nerma
 * Interface for all DAO classes.
 */
public interface Dao<T> {
    /**
     * To get entity from DB
     *
     * @param ID - PK of entity
     * @return -  entity from DB
     */
    T getById(int ID) throws EmployeeException;

    /**
     * To save entity into DB
     *
     * @param x - item for saving to DB
     * @return - saved item
     */
    T add(T x) throws EmployeeException;

    /**
     * To update entity
     *
     * @param x -  item to be updated
     * @return - updated version of bean
     */
    T update(T x) throws EmployeeException;

    /**
     * To delete item from DB
     *
     * @param ID - PK of entity
     */
    void delete(int ID) throws EmployeeException;

    /**
     * Lists all entities from DB
     *
     * @return - list of entitiesss from DB
     */
    List<T> getAll() throws EmployeeException;
}
