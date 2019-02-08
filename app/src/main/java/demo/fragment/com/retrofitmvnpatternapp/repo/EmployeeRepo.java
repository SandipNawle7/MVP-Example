package demo.fragment.com.retrofitmvnpatternapp.repo;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import demo.fragment.com.retrofitmvnpatternapp.dto.Employee;

@Dao
public interface EmployeeRepo {

    @Insert
    void insertAllEmployee(List<Employee> employeeList);

    @Delete
    void deleteEmployee(Employee employee);

    @Query("Delete from employee")
    void deleteAllEmployee();

    @Query("Select * from employee")
    List<Employee> getAllEmployee();
}
