package com.example.macie.mobilecheckout;

import android.content.Context;
import android.content.Intent;

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
    private static boolean productionDone = false;

//    class Task<Product> GetProduct implements Continuation<Void, Task<Product>> {
//        @Override
//        public Task<Product> then(Task<Void> task) {
//
//        }
//        return new Task<>();
//    }
//    public Task<Product> execute() {
//        return Tasks.<Void>forResult(null)
//                .then(new GetProduct());
//    }
//
//    public void updateInBackground() {
//        Tasks.<Void>forResult(null)
//                .then(new GetProduct())
//                .addOnSuccessListener(this)
//                .addOnFailureListener(this);
//    }
//
//    @Override
//    public void onFailure(@NonNull Exception error) {
//        Log.e(TAG, error.getMessage());
//    }
//
//    @Override
//    public void onSuccess(Customer customer) {
//        // Do something with the result
//    }
    public static void downloadProductInformation(final Context context, final String barcode) {
        DatabaseReference databaseReference =
                FirebaseDatabase.getInstance().getReference();
        databaseReference.orderByValue().addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if (barcode.equals(dataSnapshot.getKey())) {
                    for (DataSnapshot child : dataSnapshot.getChildren()) {
                        switch (child.getKey()) {
                            case "Name":
                                name = (String) child.getValue();
                                System.out.println(name);
                                break;
                            case "Price":
                                price = (Long) child.getValue();
                                System.out.println(price);
                                break;
                            case "Color":
                                color = (String) child.getValue();
                                System.out.println(color);
                                break;
                            case "Url":
                                imageUrl = (String) child.getValue();
                                System.out.println(imageUrl);
                                break;
                        }
                    }
                    Product newProduct = new Product(barcode, name, price, color, imageUrl);
                    context.sendBroadcast(new Intent("Product Information").putExtra("product", newProduct));
                }

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }



    private static void clear() {
        name = null;
        price = null;
        color = null;
        imageUrl = null;
        productionDone = false;
    }
}

