package com.example.testfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.text1);

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

//        mDatabase.child("User").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DataSnapshot> task) {
//                if (!task.isSuccessful()) {
//                    Log.e("firebase", "Error getting data", task.getException());
//                }
//                else {
//                    for (DataSnapshot ds : task.getResult().getChildren()){
//                        User user = ds.getValue(User.class);
//                        textView.setText(
//                                textView.getText().toString() + user.name + " " + user.password + '\n'
//                        );
//
//                    }
//                }
//            }
//        });
        mDatabase.child("User").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                textView.setText("");
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    User user = ds.getValue(User.class);
                    textView.setText(
                            textView.getText().toString() + user.name + " " + user.password + '\n'
                    );
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }
}