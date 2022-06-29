package com.example.muallim;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Network;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class UpdateMyAdminProfile extends AppCompatActivity {

    TextView updateEmailAdmin;
    CircleImageView updateImageAdmin;
    EditText updateNameAdmin, updateNumberAdmin, updatePasswordAdmin, updateConfirmPasswordAdmin;
    Button updateSaveData;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private String currentUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_my_admin_profile);




        updateImageAdmin = (CircleImageView) findViewById(R.id.updateAdminImage);
        updateEmailAdmin = (TextView) findViewById(R.id.updateAdminEmail);

        updateNameAdmin = (EditText) findViewById(R.id.updateAdminName);
        updateNumberAdmin = (EditText) findViewById(R.id.updateAdminNumber);
//        updatePasswordAdmin = (EditText) findViewById(R.id.updateAdminPassword);
//        updateConfirmPasswordAdmin = (EditText) findViewById(R.id.updateAdminConfirmPassword);
        updateSaveData = (Button) findViewById(R.id.saveUpdateAdminData);


        firebaseAuth = FirebaseAuth.getInstance();
        currentUserId = firebaseAuth.getCurrentUser().getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference("Admin").child(currentUserId);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()){

                    String myImage = dataSnapshot.child("ProfileImageURL").getValue().toString();
                    String myEmail = dataSnapshot.child("Email").getValue().toString();
                    String myName =dataSnapshot.child("Name").getValue().toString();
                    String myNumber =dataSnapshot.child("ContactNumber").getValue().toString();
//                    String myPassword =dataSnapshot.child("Password").getValue().toString();
//                    String myConfirmPassword =dataSnapshot.child("ConfirmPassword").getValue().toString();


                    Picasso.get().load(myImage).into(updateImageAdmin);
                    updateEmailAdmin.setText(myEmail);
                    updateNameAdmin.setText(myName);
                    updateNumberAdmin.setText(myNumber);
//                    updatePasswordAdmin.setText(myPassword);
//                    updateConfirmPasswordAdmin.setText(myConfirmPassword);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(UpdateMyAdminProfile.this, ""+databaseError.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

        updateSaveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                updateMyData();
            }
        });
    }

    private void updateMyData() {
        String name = updateNameAdmin.getText().toString();
        String number = updateNumberAdmin.getText().toString();
//        String password = updatePasswordAdmin.getText().toString();
//        String confirmPassword = updateConfirmPasswordAdmin.getText().toString();

        if (TextUtils.isEmpty(name)){
            updateNameAdmin.requestFocus();
            updateNameAdmin.setError("Required");
        }
        else if (TextUtils.isEmpty(number)){
            updateNumberAdmin.requestFocus();
            updateNumberAdmin.setError("Required");
        }
//        else if (TextUtils.isEmpty(password))
//        {
//            updatePasswordAdmin.requestFocus();
//            updatePasswordAdmin.setError("Required");
//        }
//        else if (TextUtils.isEmpty(confirmPassword)){
//            updateConfirmPasswordAdmin.requestFocus();
//            updateConfirmPasswordAdmin.setError("Required");
//        }
//        else if (!password.equals(confirmPassword)){
//            updateConfirmPasswordAdmin.requestFocus();
//            updateConfirmPasswordAdmin.setError("Not Match");
//            Toast.makeText(this, "Password Not Match", Toast.LENGTH_SHORT).show();
//        }
        else {

            HashMap adminUpdateData = new HashMap();
            adminUpdateData.put("Name",name);
            adminUpdateData.put("ContactNumber",number);
//            adminUpdateData.put("Password",password);
//            adminUpdateData.put("ConfirmPassword",confirmPassword);



            databaseReference.updateChildren(adminUpdateData).addOnCompleteListener(new OnCompleteListener() {
                @Override
                public void onComplete(@NonNull Task task) {

                    if (task.isSuccessful()){

                        final AlertDialog.Builder alert = new AlertDialog.Builder(UpdateMyAdminProfile.this);
                        alert.setTitle("Update Data");
                        alert.setMessage("Information updated successfully");
                        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                dialogInterface.dismiss();
                                Intent intent = new Intent(UpdateMyAdminProfile.this, AdminDashboard.class);
                                startActivity(intent);
                            }
                        });
                        alert.show();
                    }
                }
            });

        }
    }
}