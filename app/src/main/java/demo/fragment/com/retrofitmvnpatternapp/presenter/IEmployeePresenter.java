package demo.fragment.com.retrofitmvnpatternapp.presenter;

import java.util.List;

import demo.fragment.com.retrofitmvnpatternapp.dto.Employee;

public interface IEmployeePresenter {

    void onDestroy();

    void refreshEmployeeList();

    void requestDataFromServer();

    void requestDataFromDatabase();

    void deleteEmployeeFromDb(Employee employee);

    void deleteAllEmployeeFromDb();

    void insertEmployee(List<Employee> employeeList);
}
