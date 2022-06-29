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
import android.widget.TextClock;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class TuitionForAdmin extends AppCompatActivity {


    //when Student find Tuition

    EditText tuitionSchoolName, tuitionClassName, tuitionStudentNumber, tuitionCity, tuitionLocation, tuitionContactNumber;
    Button saveTuitionData;
    private ProgressDialog progressDialog;

    String[] itemRole;
    Spinner tuitionRoleForAdmin;
    ArrayAdapter<String> adapterRole;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tuition_for_admin);


        progressDialog = new ProgressDialog(this);

        tuitionSchoolName = (EditText) findViewById(R.id.schoolNameAdmin);
        tuitionClassName = (EditText) findViewById(R.id.classNameAdmin);
        tuitionStudentNumber = (EditText) findViewById(R.id.numbersOfStudentsAdmin);
        tuitionCity = (EditText) findViewById(R.id.studentCityAdmin);
        tuitionLocation = (EditText) findViewById(R.id.locationAdmin);
        tuitionContactNumber = (EditText) findViewById(R.id.StudentContactNumberAdmin) ;

        tuitionRoleForAdmin = (Spinner) findViewById(R.id.teacherRoleForAdmin);
        itemRole = getResources().getStringArray(R.array.gender);
        adapterRole = new ArrayAdapter<String >(TuitionForAdmin.this, R.layout.spinner_layout, R.id.spinnerTextView,itemRole);
        tuitionRoleForAdmin.setAdapter(adapterRole);

        saveTuitionData = (Button) findViewById(R.id.saveDataTuitionAdmin);


        saveTuitionData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertTuitionData();
            }
        });



    }

    private void insertTuitionData() {


        final String schoolNameTuition = tuitionSchoolName.getText().toString();
        final String classNameTuition = tuitionClassName.getText().toString();
        final String studentNumberTuition = tuitionStudentNumber.getText().toString();
        final String cityTuition= tuitionCity.getText().toString();
        final String locationTuition = tuitionLocation.getText().toString();
        final String contactNumber = tuitionContactNumber.getText().toString();
        final String teacherGenderRequeried = tuitionRoleForAdmin.getSelectedItem().toString();
        final String secondContactNumberTuition = "+923008656610";

        if (TextUtils.isEmpty(schoolNameTuition)){
            tuitionSchoolName.requestFocus();
            tuitionSchoolName.setError("School Name");
        }
        else if (TextUtils.isEmpty(classNameTuition)){
            tuitionClassName.requestFocus();
            tuitionClassName.setError("Class Name");
        }
        else if (TextUtils.isEmpty(studentNumberTuition)){
            tuitionStudentNumber.requestFocus();
            tuitionStudentNumber.setError("Number of Students");
        }
        else if (TextUtils.isEmpty(cityTuition)){
            tuitionCity.requestFocus();
            tuitionCity.setError("City");
        }
        else if (TextUtils.isEmpty(locationTuition)){
            tuitionLocation.requestFocus();
            tuitionLocation.setError("Your Area");
        }
        else if (TextUtils.isEmpty(contactNumber)){
            tuitionContactNumber.requestFocus();
            tuitionContactNumber.setError("Contact Number");
        }
        else if (!contactNumber.matches("\\+[0-9]{10,13}$")){
            tuitionContactNumber.requestFocus();
            tuitionContactNumber.setError("+923008656610");
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

            tuitionInsertData.put("SchoolName",schoolNameTuition);
            tuitionInsertData.put("ClassName",classNameTuition);
            tuitionInsertData.put("NumberOfStudents" , studentNumberTuition);
            tuitionInsertData.put("City",cityTuition);
            tuitionInsertData.put("Location",locationTuition);
            tuitionInsertData.put("ContactNumber",contactNumber);
            tuitionInsertData.put("SecondContactNumber",secondContactNumberTuition);
            tuitionInsertData.put("Gender",teacherGenderRequeried);


            FirebaseDatabase.getInstance().getReference("ProTuition")
                    .push().setValue(tuitionInsertData)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Intent intent= new Intent(TuitionForAdmin.this, LogIn.class);
                                startActivity(intent);
                                Toast.makeText(TuitionForAdmin.this, "Successfully Insertions", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(TuitionForAdmin.this, ""+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


        }



    }


}