package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.dao.EmployeeDAOSQLImpl;
import ba.unsa.etf.rpr.dao.EmployeeDao;
import ba.unsa.etf.rpr.domain.Employee;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

public class AddEmpController {

    public TextField empName;


    public void saveEmp(ActionEvent event){
        Employee e = new Employee();
        e.setLast_name(empName.getText());

        EmployeeDao dao = new EmployeeDAOSQLImpl();

        dao.add(e);
    }
}
