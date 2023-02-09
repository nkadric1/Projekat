package ba.unsa.etf.rpr.exceptions;

public class EmployeeException extends Exception {
    public EmployeeException(String msg, Exception e) {
        super(msg, e);
    }

    public EmployeeException(String msg) {
        super(msg);
    }
}
