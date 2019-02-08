package demo.fragment.com.retrofitmvnpatternapp.view;

import java.util.List;

import demo.fragment.com.retrofitmvnpatternapp.dto.Employee;

public interface IEmployeeView {

    void showProgress();

    void hideProgress();

    void showOnlineEmployeeData(List<Employee> employeeList);

    void showOfflineEmployeeData(List<Employee> employeeList);

    void onResponseFailure(Throwable throwable);

    void refreshEmployee(List<Employee> employeeList);
}
