package demo.fragment.com.retrofitmvnpatternapp.retrofit_helper;

import java.util.List;

import demo.fragment.com.retrofitmvnpatternapp.dto.Employee;
import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitApiInterface {

    @GET("api/v1/employees")
    Call<List<Employee>> getEmployeeData();

}
