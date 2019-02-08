package demo.fragment.com.retrofitmvnpatternapp.common;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import demo.fragment.com.retrofitmvnpatternapp.database.AppDatabase;

public class CommonClass {

    public static boolean isConnected(Context context) {
        try {
            ConnectivityManager cm = (ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo wifiNetwork = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (wifiNetwork != null && wifiNetwork.isConnectedOrConnecting()) {
                return true;
            }

            NetworkInfo mobileNetwork = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if (mobileNetwork != null && mobileNetwork.isConnectedOrConnecting()) {
                return true;
            }

            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            if (activeNetwork != null && activeNetwork.isConnectedOrConnecting()) {
                return true;
            }
        } catch (Exception ex) {
            return false;
        }
        return false;
    }

    public ArrayList<String> copyDB(Context ctx, String DB_PATH, String DB_NAME) {


        ArrayList<String> result = new ArrayList<String>();

        try {
            InputStream fileToRead = ctx.getAssets().open(DB_NAME);
            File directory = new File(DB_PATH);

            if (!directory.exists()) {
                System.out.println("directory Dont Exist");
                directory.mkdir();
            }

            String outputFileName = DB_PATH + DB_NAME;
            File databaseFile = new File(outputFileName);
            if (databaseFile.exists()) {
                OutputStream fileToWrite = new FileOutputStream(outputFileName);

                byte[] buffer = new byte[1024];
                int length;
                while ((length = fileToRead.read(buffer)) > 0) {
                    fileToWrite.write(buffer, 0, length);
                }

                fileToWrite.flush();
                fileToWrite.close();
                fileToRead.close();
                System.out.println("Copied DB");
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        result.add("notAvail");
        return result;
    }

    public AppDatabase getAppDatabase(Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, "employee_db")
                .allowMainThreadQueries()   //Allows room to do operation on main thread
                .build();
    }
}
