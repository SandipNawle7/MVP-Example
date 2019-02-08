package demo.fragment.com.retrofitmvnpatternapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import demo.fragment.com.retrofitmvnpatternapp.R;
import demo.fragment.com.retrofitmvnpatternapp.dto.Employee;

import static demo.fragment.com.retrofitmvnpatternapp.common.CommonClass.isConnected;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder> {

    private List<Employee> employeeList;
    private EmployeeClickListner employeeClickListner;
    private Context context;
    public interface EmployeeClickListner{
        void onEmployeeDelete(int position);
    }

    public EmployeeAdapter(List<Employee> employeeList, EmployeeClickListner employeeClickListner) {
        this.employeeList = employeeList;
        this.employeeClickListner = employeeClickListner;
    }

    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        context = viewGroup.getContext();
        View view = layoutInflater.inflate(R.layout.employee_data_view_layout, viewGroup, false);
        return new EmployeeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeViewHolder holder, final int position) {
        holder.txtId.setText("Id: ".concat(employeeList.get(position).getId()));
        holder.txtName.setText("Name: ".concat(employeeList.get(position).getEmployeeName()));
        holder.txtNameInitial.setText(getFirstLetter(employeeList.get(position).getEmployeeName()));
        holder.txtSalary.setText("Salary: ".concat(employeeList.get(position).getEmployeeSalary()));
        holder.txtAge.setText("Age: ".concat(employeeList.get(position).getEmployeeAge()));
        if(isConnected(context)){
            holder.icDelete.setVisibility(View.GONE);
        }else {
            holder.icDelete.setVisibility(View.VISIBLE);
        }
        holder.icDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                employeeClickListner.onEmployeeDelete(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(employeeList != null)
            return employeeList.size();
        else
            return 0;
    }

    class EmployeeViewHolder extends RecyclerView.ViewHolder{

        TextView txtName,txtSalary,txtAge,txtNameInitial,txtId;
        ImageView icDelete;


        public EmployeeViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.empName);
            txtSalary = itemView.findViewById(R.id.empSalary);
            txtAge = itemView.findViewById(R.id.empAge);
            txtNameInitial = itemView.findViewById(R.id.nameIcon);
            icDelete = itemView.findViewById(R.id.delete_ic);
            txtId = itemView.findViewById(R.id.empId);
        }
    }

    private String getFirstLetter(String name){
        if (name!= null && name.length()>0)
            return  String.valueOf(name.charAt(0)).toUpperCase();
        else
            return "";
    }
}
