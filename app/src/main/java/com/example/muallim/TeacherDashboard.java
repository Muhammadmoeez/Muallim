package com.example.muallim;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TeacherDashboard extends AppCompatActivity {






    private Toolbar toolbar;

    RecyclerView.LayoutManager layoutManager;
    FirebaseDatabase myFirebaseDatabase;
    DatabaseReference myDatabaseReference;
    private RecyclerView myRecyclerView;

    FirebaseRecyclerAdapter<TuitionModel , MyTuitionViewHolder> adapter;
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_dashboard);





        toolbar = (Toolbar) findViewById(R.id.toolBarTeacher);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        myFirebaseDatabase = FirebaseDatabase.getInstance();
        myDatabaseReference = myFirebaseDatabase.getReference("FinalTuition");

        myRecyclerView = (RecyclerView) findViewById(R.id.tuitionRecyclerViewForTeacher);
        layoutManager = new LinearLayoutManager(this);
        myRecyclerView.setLayoutManager(layoutManager);


        showAllConfirmTuition();
        
        
        
        
        
        
        
        
        
    }

    private void showAllConfirmTuition() {


        FirebaseRecyclerOptions options = new FirebaseRecyclerOptions.Builder<TuitionModel>()
                .setQuery(myDatabaseReference, TuitionModel.class).build();

        adapter = new FirebaseRecyclerAdapter<TuitionModel, MyTuitionViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull MyTuitionViewHolder myTuitionViewHolder, int i, @NonNull TuitionModel tuitionModel) {

                myTuitionViewHolder.teacherTuitionGender.setText(tuitionModel.getGender());
                myTuitionViewHolder.teacherTuitionSchoolName.setText(tuitionModel.getSchoolName());
                myTuitionViewHolder.teacherTuitionClassName.setText(tuitionModel.getClassName());
                myTuitionViewHolder.teacherTuitionNumbersOFStudent.setText(tuitionModel.getNumberOfStudents());
                myTuitionViewHolder.teacherTuitionCity.setText(tuitionModel.getCity());
                myTuitionViewHolder.teacherTuitionArea.setText(tuitionModel.getLocation());
                myTuitionViewHolder.teacherTuitionPhoneNumber.setText(tuitionModel.getSecondContactNumber());
            }

            @NonNull
            @Override
            public MyTuitionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_tuition_for_teacher, parent, false);
                return new MyTuitionViewHolder(view);
            }
        };

        adapter.startListening();
        adapter.notifyDataSetChanged();
        myRecyclerView.setAdapter(adapter);


    }


    public static class MyTuitionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


        TextView teacherTuitionGender, teacherTuitionSchoolName, teacherTuitionClassName,
                teacherTuitionNumbersOFStudent, teacherTuitionCity,
                teacherTuitionArea, teacherTuitionPhoneNumber;


        public MyTuitionViewHolder(@NonNull View itemView) {
            super(itemView);


            teacherTuitionGender = (TextView) itemView.findViewById(R.id.showTeacherTuitionGender);
            teacherTuitionSchoolName = (TextView) itemView.findViewById(R.id.showTeacherTuitionSchoolName);
            teacherTuitionClassName = (TextView) itemView.findViewById(R.id.showTeacherTuitionClassName);
            teacherTuitionNumbersOFStudent = (TextView) itemView.findViewById(R.id.showTeacherTuitionStudentNumbers);
            teacherTuitionCity = (TextView) itemView.findViewById(R.id.showTeacherTuitionCity);
            teacherTuitionArea = (TextView) itemView.findViewById(R.id.showTeacherTuitionLocation);
            teacherTuitionPhoneNumber = (TextView) itemView.findViewById(R.id.showTeacherTuitionPhone);

            itemView.setOnClickListener(this);


        }

        @Override
        public void onClick(View view) {

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.teacher_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

         if (id == R.id.logoutTeacherProfile){

            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(TeacherDashboard.this, LogIn.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
         else if (id== R.id.settingTeacherProfile){
             Intent intent = new Intent(TeacherDashboard.this, UpdateMyTeacherProfile.class);
             startActivity(intent);
         }

        return super.onOptionsItemSelected(item);
    }
}