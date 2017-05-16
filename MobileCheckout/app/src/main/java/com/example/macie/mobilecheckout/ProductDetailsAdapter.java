package com.example.macie.mobilecheckout;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by mac on 15/05/2017.
 */

public class ProductDetailsAdapter extends ArrayAdapter<Product> {

    Context context;
    Product product;

    public ProductDetailsAdapter(Context context, int resourceId, Product product) {
        super(context, resourceId);
        this.context = context;
        this.product = product;
    }

    private class ViewHolder {
        ImageView imageView;
        TextView nameView;
        TextView priceView;
        TextView colorView;
    }

    @Override
    public View getView(int position, View contentView, ViewGroup parent){
        ViewHolder viewHolder = null;
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (contentView == null) {
            contentView = inflater.inflate(R.layout.product_details, null);
            viewHolder = new ViewHolder();
            viewHolder.imageView = (ImageView)contentView.findViewById(R.id.image);
            viewHolder.nameView = (TextView)contentView.findViewById(R.id.name_text);
            viewHolder.colorView = (TextView)contentView.findViewById(R.id.color_text);
            viewHolder.priceView = (TextView)contentView.findViewById(R.id.price_text);
            contentView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder)contentView.getTag();
        }
        // debug values
        Picasso.with(context).load(product.getImageUrl()).into(viewHolder.imageView);
        viewHolder.nameView.setText(product.getName());
        viewHolder.colorView.setText(product.getColor());
        viewHolder.priceView.setText(String.format("%s", product.getPrice()));
        return contentView;
    }

    @Override
    public int getCount() {
        return 1;
    }
}
