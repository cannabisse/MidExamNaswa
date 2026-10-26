package com.example.midexam;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {
    List<StudentModel> student;
    Context context;
    DBHelper dbHelper;

    public StudentAdapter(List<StudentModel> student, Context context) {
        this.student = student;
        this.context = context;
        dbHelper = new DBHelper(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.student_item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        final StudentModel studentModel = student.get(position);
        holder.textViewStudentID.setText(studentModel.getStudentId());
        holder.editTextName.setText(studentModel.getName());
        holder.checkBoxEnrolled.setChecked(studentModel.isEnrolled());

        holder.isEditing = false;
        holder.editTextName.setEnabled(false);
        holder.checkBoxEnrolled.setEnabled(false);
        holder.btnEdit.setText("Edit");

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!holder.isEditing) {
                    holder.isEditing = true;
                    holder.editTextName.setEnabled(true);
                    holder.checkBoxEnrolled.setEnabled(true);
                    holder.btnEdit.setText("Save");
                } else {
                    String strName = holder.editTextName.getText().toString();
                    boolean enrolled = holder.checkBoxEnrolled.isChecked();

                    if (strName.length() <= 0) {
                        Toast.makeText(context, "Enter data", Toast.LENGTH_SHORT).show();
                    } else {
                        dbHelper.updateStudent(new StudentModel(studentModel.getId(),
                                studentModel.getStudentId(), strName, enrolled));
                        studentModel.setName(strName);
                        studentModel.setEnrolled(enrolled);

                        holder.isEditing = false;
                        holder.editTextName.setEnabled(false);
                        holder.checkBoxEnrolled.setEnabled(false);
                        holder.btnEdit.setText("Edit");
                        Toast.makeText(context, "Update Successful", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHelper.deleteStudent(studentModel.getId());
                student.remove(position);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return student.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewStudentID;
        EditText editTextName;
        CheckBox checkBoxEnrolled;
        Button btnEdit, btnDelete;
        boolean isEditing = false;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewStudentID = itemView.findViewById(R.id.textViewStudentID);
            editTextName = itemView.findViewById(R.id.editTextName);
            checkBoxEnrolled = itemView.findViewById(R.id.checkBoxEnrolled);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}