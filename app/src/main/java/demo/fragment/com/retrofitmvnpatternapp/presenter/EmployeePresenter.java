package demo.fragment.com.retrofitmvnpatternapp.presenter;

import java.util.List;

import demo.fragment.com.retrofitmvnpatternapp.dto.Employee;
import demo.fragment.com.retrofitmvnpatternapp.database.AppDatabase;
import demo.fragment.com.retrofitmvnpatternapp.model.IEmployeeModel;
import demo.fragment.com.retrofitmvnpatternapp.view.IEmployeeView;

public class EmployeePresenter implements IEmployeePresenter,  IEmployeeModel.OnFinishListner  {

    private IEmployeeModel employeeModel;
    private IEmployeeView employeeView;
    private AppDatabase appDatabase;
    public EmployeePresenter(IEmployeeView employeeView, IEmployeeModel employeeModel, AppDatabase appDatabase) {
        this.employeeModel = employeeModel;
        this.employeeView = employeeView;
        this.appDatabase = appDatabase;
    }

    @Override
    public void onDestroy() {
        employeeModel = null;
    }

    @Override
    public void refreshEmployeeList() {
        if(employeeView != null){
            employeeView.showProgress();
        }
        employeeModel.getEmployeeModelList(this);
    }

    @Override
    public void requestDataFromServer() {
        if(employeeView != null){
            employeeView.showProgress();
        }
        employeeModel.getEmployeeModelList(this);
    }

    @Override
    public void requestDataFromDatabase() {
        List<Employee> employeeList = appDatabase.employeeRepo().getAllEmployee();
        if(employeeView != null){
            employeeView.showOfflineEmployeeData(employeeList);
            employeeView.hideProgress();
        }
    }

    @Override
    public void deleteEmployeeFromDb(Employee employee) {
        appDatabase.employeeRepo().deleteEmployee(employee);
    }

    @Override
    public void deleteAllEmployeeFromDb() {
        appDatabase.employeeRepo().deleteAllEmployee();
    }

    @Override
    public void insertEmployee(List<Employee> employeeList) {
        appDatabase.employeeRepo().insertAllEmployee(employeeList);
    }

    @Override
    public void onFinish(List<Employee> employeeList) {
        if(employeeView != null){
            employeeView.showOnlineEmployeeData(employeeList);
            employeeView.hideProgress();
        }
    }

    @Override
    public void onFailure(Throwable t) {
        if(employeeView != null){
            employeeView.onResponseFailure(t);
            employeeView.hideProgress();
        }
    }

}
