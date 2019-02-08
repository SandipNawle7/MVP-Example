package demo.fragment.com.retrofitmvnpatternapp.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import demo.fragment.com.retrofitmvnpatternapp.dto.Employee;
import demo.fragment.com.retrofitmvnpatternapp.repo.EmployeeRepo;

@Database(version = 1,entities = {Employee.class})
public abstract class AppDatabase extends RoomDatabase {

    public abstract EmployeeRepo employeeRepo();
}
