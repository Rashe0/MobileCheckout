package com.example.macie.mobilecheckout;

import com.google.android.gms.vision.barcode.Barcode;

/**
 * Created by macie on 13-May-17.
 */

public class VirtualBasket {

    private static VirtualBasket instance = new VirtualBasket();

    private VirtualBasket(){

    }

    public static VirtualBasket getInstance() {
        return instance;
    }

    public void addToBasket(Barcode barcode) {
        //dodawanie
    }
}
