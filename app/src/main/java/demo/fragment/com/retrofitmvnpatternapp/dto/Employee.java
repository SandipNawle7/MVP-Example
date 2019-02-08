package demo.fragment.com.retrofitmvnpatternapp.dto;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import demo.fragment.com.retrofitmvnpatternapp.constant.DB_Constant;

@Entity(tableName = "employee")
public class Employee {

    @SerializedName("sqlId")
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = DB_Constant.empSqlId)
    private Integer sqlId;

    @SerializedName("id")
    @ColumnInfo(name = DB_Constant.empId)
    private String id;


    @SerializedName("employee_name")
    @ColumnInfo(name = DB_Constant.employeeName)
    private String employeeName;


    @SerializedName("employee_salary")
    @ColumnInfo(name = DB_Constant.employeeSalary)
    private String employeeSalary;



    @SerializedName("employee_age")
    @ColumnInfo(name = DB_Constant.employeeAge)
    private String employeeAge;


    @SerializedName("profile_image")
    @ColumnInfo(name = DB_Constant.profileImage)
    private String profileImage;

    public Integer getSqlId() {
        return sqlId;
    }

    public void setSqlId(Integer sqlId) {
        this.sqlId = sqlId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeSalary() {
        return employeeSalary;
    }

    public void setEmployeeSalary(String employeeSalary) {
        this.employeeSalary = employeeSalary;
    }

    public String getEmployeeAge() {
        return employeeAge;
    }

    public void setEmployeeAge(String employeeAge) {
        this.employeeAge = employeeAge;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }
}
