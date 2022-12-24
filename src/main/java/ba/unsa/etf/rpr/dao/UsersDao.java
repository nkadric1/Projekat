package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Users;

public interface UsersDao extends Dao<Users>{
    public boolean validate(String u, String p);
}
