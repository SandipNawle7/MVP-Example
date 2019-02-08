package demo.fragment.com.retrofitmvnpatternapp.model;

import java.util.List;

import demo.fragment.com.retrofitmvnpatternapp.dto.Employee;
import demo.fragment.com.retrofitmvnpatternapp.retrofit_helper.RetrofitApiClient;
import demo.fragment.com.retrofitmvnpatternapp.retrofit_helper.RetrofitApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmplyeeModelImpl implements IEmployeeModel {

    @Override
    public void getEmployeeModelList(final OnFinishListner onFinishListner) {
        RetrofitApiInterface retrofitApiInterface = RetrofitApiClient.getClient().create(RetrofitApiInterface.class);

        Call<List<Employee>> call = retrofitApiInterface.getEmployeeData();
        call.enqueue(new Callback<List<Employee>>() {
            @Override
            public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {
                onFinishListner.onFinish(response.body());
            }

            @Override
            public void onFailure(Call<List<Employee>> call, Throwable t) {
                onFinishListner.onFailure(t);
            }
        });
    }
}
