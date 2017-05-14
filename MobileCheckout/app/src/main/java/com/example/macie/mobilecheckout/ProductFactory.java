package com.example.macie.mobilecheckout;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by macie on 13-May-17.
 */

public class ProductFactory {

    private static String name;
    private static Long price;
    private static String color;
    private static String imageUrl;

    public static void createProduct(final String barcode) {
        DatabaseReference databaseReference =
                FirebaseDatabase.getInstance().getReference();

        databaseReference.orderByValue().addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                if (barcode.equals(dataSnapshot.getKey())) {
                    for (DataSnapshot child : dataSnapshot.getChildren()) {
                        switch (child.getKey()) {
                            case "name":
                                name = (String)child.getValue();
                                System.out.println(name);
                                break;
                            case "price":
                                price = (Long)child.getValue();
                                System.out.println(price);
                                break;
                            case "color":
                                color = (String) child.getValue();
                                System.out.println(color);
                                break;
                            case "url":
                                imageUrl = (String) child.getValue();
                                System.out.println(imageUrl);
                                break;
                        }
                    }
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {}

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {}

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {}

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });
    }

}
