package demo.fragment.com.retrofitmvnpatternapp.model;

import java.util.List;

import demo.fragment.com.retrofitmvnpatternapp.dto.Employee;

public interface IEmployeeModel {

    interface OnFinishListner{

        void onFinish(List<Employee> employeeList);

        void onFailure(Throwable t);
    }

    void getEmployeeModelList(OnFinishListner onFinishListner);
}
