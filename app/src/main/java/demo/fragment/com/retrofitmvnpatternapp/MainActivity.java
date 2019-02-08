package demo.fragment.com.retrofitmvnpatternapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import demo.fragment.com.retrofitmvnpatternapp.adapter.EmployeeAdapter;
import demo.fragment.com.retrofitmvnpatternapp.common.CommonClass;
import demo.fragment.com.retrofitmvnpatternapp.constant.DB_Constant;
import demo.fragment.com.retrofitmvnpatternapp.database.AppDatabase;
import demo.fragment.com.retrofitmvnpatternapp.dto.Employee;
import demo.fragment.com.retrofitmvnpatternapp.model.EmplyeeModelImpl;
import demo.fragment.com.retrofitmvnpatternapp.presenter.EmployeePresenter;
import demo.fragment.com.retrofitmvnpatternapp.presenter.IEmployeePresenter;
import demo.fragment.com.retrofitmvnpatternapp.view.IEmployeeView;

import static demo.fragment.com.retrofitmvnpatternapp.common.CommonClass.isConnected;

public class MainActivity extends AppCompatActivity implements IEmployeeView, EmployeeAdapter.EmployeeClickListner {
    RecyclerView employeeRecyclerView;
    EmployeeAdapter employeeAdapter;
    IEmployeePresenter employeePresenter;
    ProgressDialog progressDialog;
    ProgressBar progressBar;
    List<Employee> employeeList;
    Context context;
    CommonClass commonClass;
    AppDatabase appDatabase;
    boolean doubleBackToExitPressedOnce =false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        commonClass = new CommonClass();
        performDatabaseOperation();
        initializeRecyclerViewBar();
        employeeList = new ArrayList<>();

        initProgressBar();

        employeePresenter = new EmployeePresenter(this, new EmplyeeModelImpl(), appDatabase);

        getEmployeeData();

    }

    private void getEmployeeData() {
        if (isConnected(context)) {
            employeePresenter.requestDataFromServer();
        } else {
            employeePresenter.requestDataFromDatabase();
        }
    }

    private void performDatabaseOperation() {

        commonClass.copyDB(context, DB_Constant.DB_PATH, DB_Constant.DB_NAME);
        appDatabase = commonClass.getAppDatabase(context);
    }

    private void initializeRecyclerViewBar() {
        employeeRecyclerView = findViewById(R.id.empRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        employeeRecyclerView.setLayoutManager(layoutManager);

    }

    private void initProgressBar() {
        progressBar = new ProgressBar(this, null, android.R.attr.progressBarStyleLarge);
        progressBar.setIndeterminate(true);

        RelativeLayout relativeLayout = new RelativeLayout(this);
        relativeLayout.setGravity(Gravity.CENTER);
        relativeLayout.addView(progressBar);

        RelativeLayout.LayoutParams params = new
                RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        progressBar.setVisibility(View.INVISIBLE);

        this.addContentView(relativeLayout, params);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showOnlineEmployeeData(List<Employee> employeeList) {
        employeePresenter.deleteAllEmployeeFromDb();
        employeePresenter.insertEmployee(employeeList);
        loadEmployeeAdater(employeeList);
    }

    private void loadEmployeeAdater(List<Employee> employeeList) {
        this.employeeList.clear();
        this.employeeList.addAll(employeeList);
        if (employeeAdapter == null) {
            employeeAdapter = new EmployeeAdapter(this.employeeList, this);
            employeeRecyclerView.setAdapter(employeeAdapter);
        }
        employeeAdapter.notifyDataSetChanged();
    }

    @Override
    public void showOfflineEmployeeData(List<Employee> employeeList) {
        loadEmployeeAdater(employeeList);
    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        Toast.makeText(this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void refreshEmployee(List<Employee> employeeList) {

    }

    @Override
    public void onEmployeeDelete(int position) {
        showDeleteConfirmationDialog(position);
    }

    private void showDeleteConfirmationDialog(final int position) {
        AlertDialog alertDialog = null;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(false);
        builder.setTitle("Confirmation!!");
        builder.setMessage("Do you want to delete this employee ?")
                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        employeePresenter.deleteEmployeeFromDb(employeeList.get(position));
                        employeeList.remove(position);
                        employeeAdapter.notifyItemRemoved(position);
                        employeeAdapter.notifyItemRangeChanged(position, employeeList.size());
                        dialogInterface.dismiss();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
        alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.setCancelable(false);
        alertDialog.show();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        appDatabase.close();
        employeePresenter.onDestroy();
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            finish();
            System.exit(0);
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(context,getString(R.string.exit_back_msg),Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }

}
