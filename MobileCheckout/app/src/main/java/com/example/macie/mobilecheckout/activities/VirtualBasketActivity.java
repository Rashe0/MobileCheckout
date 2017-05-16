package com.example.macie.mobilecheckout.activities;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.macie.mobilecheckout.fragments.PopupFragment;
import com.example.macie.mobilecheckout.R;
import com.example.macie.mobilecheckout.program_logic.Product;
import com.example.macie.mobilecheckout.program_logic.VirtualBasket;

import java.util.List;

public class VirtualBasketActivity extends AppCompatActivity {

    private ListView mListView;
    private ListAdapter mListAdapter;
    private List list;

    private EditText mEditText;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getAttributes().windowAnimations = R.style.Slide;
        setContentView(R.layout.activity_virtual_basket);

        mListView = (ListView) findViewById(R.id.basket_list_view);

        List<Product> productList = VirtualBasket.getInstance().getProductList();

        mListAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, productList);

        mListView.setAdapter(mListAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Product chosenProduct = VirtualBasket.getInstance().findByIndex(position);
                showDialog(chosenProduct);
            }
        });
    }


    public void showDialog(Product product) {
        DialogFragment newFragment = PopupFragment.newInstance(R.string.app_name, product);
        newFragment.show(getSupportFragmentManager().beginTransaction(), "dialog");
    }

}
