package com.example.muallim;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class UpdateMyTeacherProfile extends AppCompatActivity {

    TextView updateEmailTeacher;
    EditText updateNameTeacher, updateNumberTeacher, updatePreferableTeachingArea, updateAddressTeacher, updateDescriptionTeacher;
    Button updateSaveDataTeacher;

    private FirebaseAuth firebaseAuthTeacher;
    private DatabaseReference databaseReferenceTeacher;
    private String currentUserIdTeacher;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_my_teacher_profile);


        updateEmailTeacher = (TextView) findViewById(R.id.updateTeacherEmail);

        updateNameTeacher = (EditText) findViewById(R.id.updateTeacherName);
        updateNumberTeacher = (EditText) findViewById(R.id.updateTeacherNumber);
        updatePreferableTeachingArea = (EditText) findViewById(R.id.updatePreferableTeachingArea);
        updateAddressTeacher = (EditText) findViewById(R.id.updateTeacherAddress);
        updateDescriptionTeacher = (EditText) findViewById(R.id.description);
        updateSaveDataTeacher = (Button) findViewById(R.id.saveUpdateTeacherData);


        firebaseAuthTeacher = FirebaseAuth.getInstance();
        currentUserIdTeacher = firebaseAuthTeacher.getCurrentUser().getUid();
        databaseReferenceTeacher = FirebaseDatabase.getInstance().getReference("Teacher").child(currentUserIdTeacher);

        databaseReferenceTeacher.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String myEmail = dataSnapshot.child("Email").getValue().toString();
                String myName =dataSnapshot.child("UserName").getValue().toString();
                String myNumber =dataSnapshot.child("ContactNumber").getValue().toString();
                String myPreferableTeachingArea =dataSnapshot.child("PreferableTeachingArea").getValue().toString();
                String myAddress =dataSnapshot.child("Address").getValue().toString();
                String myDescription = dataSnapshot.child("Description").getValue().toString();

                updateEmailTeacher.setText(myEmail);
                updateNameTeacher.setText(myName);
                updateNumberTeacher.setText(myNumber);
                updatePreferableTeachingArea.setText(myPreferableTeachingArea);
                updateAddressTeacher.setText(myAddress);
                updateDescriptionTeacher.setText(myDescription);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(UpdateMyTeacherProfile.this, ""+databaseError.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


        updateSaveDataTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = updateNameTeacher.getText().toString();
                String number = updateNumberTeacher.getText().toString();
                String preferableTeachingArea = updatePreferableTeachingArea.getText().toString();
                String address = updateAddressTeacher.getText().toString();
                String description = updateDescriptionTeacher.getText().toString();

                if (TextUtils.isEmpty(name)){
                    updateNameTeacher.requestFocus();
                    updateNameTeacher.setError("Required");
                }
                else if (TextUtils.isEmpty(number)){
                    updateNumberTeacher.requestFocus();
                    updateNumberTeacher.setError("Required");
                }
                else if (!number.matches("\\+[0-9]{10,13}$")){
                    updateNumberTeacher.requestFocus();
                    updateNumberTeacher.setError("+923008656610");
                }
                else if (TextUtils.isEmpty(preferableTeachingArea)){
                    updatePreferableTeachingArea.requestFocus();
                    updatePreferableTeachingArea.setError("Required");
                }
                else if (TextUtils.isEmpty(address)){
                    updateAddressTeacher.requestFocus();
                    updateAddressTeacher.setError("Required");
                }
                else if (TextUtils.isEmpty(description)){
                    updateDescriptionTeacher.requestFocus();
                    updateDescriptionTeacher.setError("Required");
                }
                else if (description.length() < 100){
                    updateDescriptionTeacher.requestFocus();
                    Toast.makeText(UpdateMyTeacherProfile.this, "Minimum 100 Letters", Toast.LENGTH_SHORT).show();
                }

                else {


                    final HashMap teacherUpdateData = new HashMap();

                    teacherUpdateData.put("UserName",name);
                    teacherUpdateData.put("ContactNumber",number);
                    teacherUpdateData.put("Password",preferableTeachingArea);
                    teacherUpdateData.put("Address",address);
                    teacherUpdateData.put("Description",description);

                    databaseReferenceTeacher.updateChildren(teacherUpdateData).addOnCompleteListener(new OnCompleteListener() {
                        @Override
                        public void onComplete(@NonNull Task task) {

                            if (task.isSuccessful()){
                                final AlertDialog.Builder alert = new AlertDialog.Builder(UpdateMyTeacherProfile.this);
                                alert.setTitle("Update Data");
                                alert.setMessage("Information updated successfully");
                                alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                        dialogInterface.dismiss();
                                        Intent intent = new Intent(UpdateMyTeacherProfile.this, TeacherDashboard.class);
                                        startActivity(intent);
                                    }
                                });
                                alert.show();
                            }
                        }
                    });

                }



            }
        });



    }
}