package com.example.muallim;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

public class MyTeacherAdapter extends FirebaseRecyclerAdapter<TeacherModel, MyTeacherAdapter.MyAllTeacherViewHolder> {
    public MyTeacherAdapter(@NonNull FirebaseRecyclerOptions<TeacherModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull final MyAllTeacherViewHolder myAllTeacherViewHolder, final int position, @NonNull final TeacherModel teacherModel) {


        myAllTeacherViewHolder.textViewTeacherName.setText(teacherModel.getUserName());
        myAllTeacherViewHolder.textViewTeacherClass.setText(teacherModel.getClassYouCanTeach());
        myAllTeacherViewHolder.textViewTeacherSubject.setText(teacherModel.getSubjectsYouCan());




        myAllTeacherViewHolder.imageViewViewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final DialogPlus dialogPlus =  DialogPlus.newDialog(myAllTeacherViewHolder.textViewTeacherName.getContext())
                        .setContentHolder(new ViewHolder(R.layout.teacher_view_layout))
                        .setExpanded(true,1500)
                        .create();

                View myViewForView = dialogPlus.getHolderView();
                TextView receiveMyTeacherViewName = myViewForView.findViewById(R.id.viewTeacherName);
                TextView receiveMyTeacherViewNumber = myViewForView.findViewById(R.id.viewTeacherNumber);
                TextView receiveMyTeacherViewEmail = myViewForView.findViewById(R.id.viewTeacherEmail);
                TextView receiveMyTeacherViewCNIC = myViewForView.findViewById(R.id.viewTeacherCNIC);
                TextView receiveMyTeacherViewAddress = myViewForView.findViewById(R.id.viewTeacherAddress);

                receiveMyTeacherViewName.setText(teacherModel.getUserName());
                receiveMyTeacherViewNumber.setText(teacherModel.getContactNumber());
                receiveMyTeacherViewEmail.setText(teacherModel.getEmail());
                receiveMyTeacherViewCNIC.setText(teacherModel.getCNIC());
                receiveMyTeacherViewAddress.setText(teacherModel.getAddress());


                dialogPlus.show();

            }
        });


        myAllTeacherViewHolder.imageViewDeleteProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(myAllTeacherViewHolder.textViewTeacherName.getContext());
                builder.setTitle("Delete Panel");
                builder.setMessage("Delete......?");


                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {


                        FirebaseDatabase.getInstance().getReference()
                                .child("Teacher")
                                .child(getRef(position).getKey()).
                                removeValue();

                        dialogInterface.dismiss();

                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        dialogInterface.dismiss();
                    }
                });
                builder.show();
            }
        });




    }

    @NonNull
    @Override
    public MyAllTeacherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_teacher, parent, false);
        return new MyTeacherAdapter.MyAllTeacherViewHolder(view);
    }

    public static class MyAllTeacherViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textViewTeacherName,  textViewTeacherClass, textViewTeacherSubject;

        ImageView imageViewViewProfile, imageViewEditProfile, imageViewDeleteProfile;

        public FullUserInterFace fullUserInterFace;


        public MyAllTeacherViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewTeacherName = (TextView) itemView.findViewById(R.id.myTeacherName);
            textViewTeacherClass = (TextView) itemView.findViewById(R.id.myTeacherClass);
            textViewTeacherSubject = (TextView) itemView.findViewById(R.id.myTeacherSubject);


            imageViewViewProfile = (ImageView) itemView.findViewById(R.id.viewProfile);
            // imageViewEditProfile = (ImageView) itemView.findViewById(R.id.editProfile);
            imageViewDeleteProfile = (ImageView) itemView.findViewById(R.id.deleteProfile);
            itemView.setOnClickListener(this);


        }


        @Override
        public void onClick(View view) {
            //   fullUserInterFace.onClick(view,false);

        }

        public void fullUserData(FullUserInterFace myFullUserInterFace){

            this.fullUserInterFace = myFullUserInterFace;
        }
    }
}
