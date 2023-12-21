package com.example.project;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.project.Database.OrderContract;

public class CartAdapter extends CursorAdapter {


    public CartAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.cartlist, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        // getting theviews

        TextView itemName, price, quantity;


        itemName = view.findViewById(R.id.itemNameincart);
        price = view.findViewById(R.id.priceincart);
        quantity = view.findViewById(R.id.quantityincart);

        // getting the values by first getting the position of their columns

        int name = cursor.getColumnIndex(OrderContract.OrderEntry.COLUMN_NAME);
        int priceofitem = cursor.getColumnIndex(OrderContract.OrderEntry.COLUMN_PRICE);
        int quantityofitem = cursor.getColumnIndex(OrderContract.OrderEntry.COLUMN_QUANTITY);



        String nameofitem = cursor.getString(name);
        String pricesofitem = cursor.getString(priceofitem);
        String quantitysofitem = cursor.getString(quantityofitem);




        itemName.setText(nameofitem);
        price.setText(pricesofitem);
        quantity.setText(quantitysofitem);





    }
}