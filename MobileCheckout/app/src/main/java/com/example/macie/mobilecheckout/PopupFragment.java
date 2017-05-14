package com.example.macie.mobilecheckout;

import android.app.AlertDialog;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by mrl on 5/13/2017.
 */

public class PopupFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        // Inflate the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        View view = inflater.inflate(R.layout.fragment_popup, null);

        // Get your views by using view.findViewById() here and do your listeners.
        //...

        // Set the dialog layout
        builder.setView(view);

        return builder.create();
    }
    public static PopupFragment newInstance(int title) {
        PopupFragment frag = new PopupFragment();
        Bundle args = new Bundle();
        args.putInt("title", title);
        frag.setArguments(args);
        return frag;
    }
}
