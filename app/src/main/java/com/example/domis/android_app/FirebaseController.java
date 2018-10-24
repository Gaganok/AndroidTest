package com.example.domis.android_app;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.database.*;

import java.util.HashMap;

public class FirebaseController {

    private static final FirebaseController ourInstance = new FirebaseController();
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private HashMap<String, String> userList;

    public static FirebaseController getInstance() {
        return ourInstance;
    }

    private FirebaseController() {
        database = FirebaseDatabase.getInstance();
        getUserList();
    }

    public boolean checkUserLogin(User user) {

        System.out.println("USERNAME: " + user.getUsername());
        System.out.println("PASSWORD: " + user.getPassword());

        if(userList.containsKey(user.getUsername()))
        {
            System.out.println("Key found");
            System.out.println("Here is its value: " + userList.get(user.getUsername()));
        }

        if (userList.containsKey(user.getUsername()) && user.getPassword().equals(userList.get(user.getUsername())))
        {
            System.out.println("Success");
            return true;
        }
        else
        {
            System.out.println("Kind of Success");
            return false;
        }
    }

    private void getUserList() {
        userList = new HashMap<>();

        myRef = database.getReference().child("USERS");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.e("Count ", "" + dataSnapshot.getChildrenCount());
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    User user1 = postSnapshot.getValue(User.class);
                    System.out.println("FireUser: " + user1.getUsername());
                    System.out.println("FirePass: " + user1.getPassword());
                    userList.put(user1.getUsername(), user1.getPassword());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}

