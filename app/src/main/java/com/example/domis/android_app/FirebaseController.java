package com.example.domis.android_app;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.database.*;

import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class FirebaseController {

    private static final FirebaseController ourInstance = new FirebaseController();
    private FirebaseDatabase database;
    private DatabaseReference myRef;

    public static FirebaseController getInstance() {
        return ourInstance;
    }

    private FirebaseController() {
        database = FirebaseDatabase.getInstance();
    }

    public void findUser(final User user) {
        /*final CountDownLatch done = new CountDownLatch(1);*/

        database.getReference().child("USERS")
                .child(user.getUsername()).addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                PersonalData.USER = dataSnapshot.getValue(User.class);
                PersonalData.validate(user.getPassword());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("Error", "Failed To Get the user");
            }
        });

        /*try {
            done.await(10000, TimeUnit.MILLISECONDS);
        } catch (Throwable e) {Log.e("Error", "Exceed Waiting Time for the database");}
*/
    }
}

