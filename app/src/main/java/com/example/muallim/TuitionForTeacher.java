package com.example.muallim;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class TuitionForTeacher extends AppCompatActivity {
    //when Admin gives Tuition to teachers


    EditText tuitionTeacherSchoolName, tuitionTeacherClassName, tuitionTeacherStudentNumber, tuitionTeacherCity, tuitionTeacherLocation,
            tuitionTeacherContactNumber;
    Button saveTeacherTuitionData;
    private ProgressDialog progressDialog;

    String[] itemRole;
    Spinner tuitionRoleForTeacher;
    ArrayAdapter<String> adapterRole;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tuition_for_teacher);

        progressDialog = new ProgressDialog(this);

        tuitionTeacherSchoolName = (EditText) findViewById(R.id.schoolNameTeacher);
        tuitionTeacherClassName = (EditText) findViewById(R.id.classNameTeacher);
        tuitionTeacherStudentNumber = (EditText) findViewById(R.id.numbersOfStudentsTeacher);
        tuitionTeacherCity = (EditText) findViewById(R.id.studentCityTeacher);
        tuitionTeacherLocation = (EditText) findViewById(R.id.locationTeacher);

        tuitionRoleForTeacher = (Spinner) findViewById(R.id.teacherRoleForTeacher);
        itemRole = getResources().getStringArray(R.array.gender);
        adapterRole = new ArrayAdapter<String >(TuitionForTeacher.this, R.layout.spinner_layout, R.id.spinnerTextView,itemRole);
        tuitionRoleForTeacher.setAdapter(adapterRole);

        saveTeacherTuitionData = (Button) findViewById(R.id.saveDataTuitionTeacher);


        saveTeacherTuitionData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertTuitionForTeacherData();
            }
        });



    }

    private void insertTuitionForTeacherData() {

        final String schoolNameTuition = tuitionTeacherSchoolName.getText().toString();
        final String classNameTuition = tuitionTeacherClassName.getText().toString();
        final String studentNumberTuition = tuitionTeacherStudentNumber.getText().toString();
        final String cityTuition= tuitionTeacherCity.getText().toString();
        final String locationTuition = tuitionTeacherLocation.getText().toString();
        final String teacherGenderRequeried = tuitionRoleForTeacher.getSelectedItem().toString();
//        final String contactNumber = tuitionTeacherContactNumber.getText().toString();
        final String secondContactNumberTuition = "+923008656610";

        if (TextUtils.isEmpty(schoolNameTuition)){
            tuitionTeacherSchoolName.requestFocus();
            tuitionTeacherSchoolName.setError("School Name");
        }
        else if (TextUtils.isEmpty(classNameTuition)){
            tuitionTeacherClassName.requestFocus();
            tuitionTeacherClassName.setError("Class Name");
        }
        else if (TextUtils.isEmpty(studentNumberTuition)){
            tuitionTeacherStudentNumber.requestFocus();
            tuitionTeacherStudentNumber.setError("Number of Students");
        }
        else if (TextUtils.isEmpty(cityTuition)){
            tuitionTeacherCity.requestFocus();
            tuitionTeacherCity.setError("City");
        }
        else if (TextUtils.isEmpty(locationTuition)){
            tuitionTeacherLocation.requestFocus();
            tuitionTeacherLocation.setError("Your Area");
        }
        else if (teacherGenderRequeried.equals("Gender")){
            Toast.makeText(this, "Select Gender", Toast.LENGTH_SHORT).show();

        }
        else {

            progressDialog.setTitle("Insert Data");
            progressDialog.setMessage("Please wait...");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            HashMap tuitionInsertData = new HashMap();

            tuitionInsertData.put("Gender",teacherGenderRequeried);
            tuitionInsertData.put("SchoolName",schoolNameTuition);
            tuitionInsertData.put("ClassName",classNameTuition);
            tuitionInsertData.put("NumberOfStudents" , studentNumberTuition);
            tuitionInsertData.put("City",cityTuition);
            tuitionInsertData.put("Location",locationTuition);
//            tuitionInsertData.put("ContactNumber",contactNumber);
            tuitionInsertData.put("SecondContactNumber",secondContactNumberTuition);


            FirebaseDatabase.getInstance().getReference("FinalTuition")
                    .push().setValue(tuitionInsertData)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Intent intent= new Intent(TuitionForTeacher.this, AdminDashboard.class);
                                startActivity(intent);
                                Toast.makeText(TuitionForTeacher.this, "Successfully Insertions", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(TuitionForTeacher.this, ""+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


        }



    }
}