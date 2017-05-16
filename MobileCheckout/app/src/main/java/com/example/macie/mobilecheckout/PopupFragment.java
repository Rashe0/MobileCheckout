package com.example.macie.mobilecheckout;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by mrl on 5/13/2017.
 */

public class PopupFragment extends DialogFragment {

    private Button addButton;
    private Button retryButton;
    private TextView titleText;
    private TextView priceText;
    private TextView colorText;
    private ImageView imageField;
    private ListView productInfoListView;
    BroadcastReceiver broadcastReceiver;
    private static boolean isOperating;

    public static Product p;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        isOperating = true;
        Product product = getArguments().getParcelable("product");

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        builder.setTitle("Kurwa tytul");
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_popup, null);

        productInfoListView = (ListView) view.findViewById(R.id.product_information);
        ProductDetailsAdapter productDetailsAdapter =
                new ProductDetailsAdapter(getContext(), R.layout.product_details, product);
        productInfoListView.setAdapter(productDetailsAdapter);

        addButton = (Button) view.findViewById(R.id.add_to_basket_button);
        retryButton = (Button) view.findViewById(R.id.return_button);

        // Set the dialog layout
        builder.setView(view);

        return builder.create();
    }

    @Override
    public void onDestroy() {
        isOperating = false;
        super.onDestroy();
        Intent closePopup = new Intent("Close Popup");
        this.getContext().sendBroadcast(closePopup);
        //this.getContext().unregisterReceiver(broadcastReceiver);
    }

    public static PopupFragment newInstance(int title, Product product) {
        PopupFragment frag = new PopupFragment();
        Bundle args = new Bundle();
        args.putInt("title", title);
        args.putParcelable("product", product);
        frag.setArguments(args);
        return frag;
    }

    public static boolean isOperating() {
        return isOperating;
    }

}

