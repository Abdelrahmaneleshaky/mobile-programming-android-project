package com.example.project;

import android.app.LoaderManager;
import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.StrictMode;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.project.Database.OrderContract;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class Activity7 extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{
    private ImageButton button1,button;
    ImageButton plusquantity, minusquantity;
    TextView quantitynumber, itemName, itemPrice;
    Button addtoCart;
    ImageView image;
    BitmapDrawable drawable;
    Bitmap bitmap;




    int quantity;
    public Uri mCurrentCartUri;
    boolean hasAllRequiredValues = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_7);
        plusquantity = findViewById(R.id.addquantity);
        minusquantity  = findViewById(R.id.subquantity);
        quantitynumber = findViewById(R.id.quantity);
        itemName = findViewById(R.id.item1name);
        itemPrice = findViewById(R.id.itemPrice);
        addtoCart = findViewById(R.id.addtocart);
        button1 = (ImageButton) findViewById(R.id.arrowcart);
        button=(ImageButton) findViewById(R.id.share);
        image=findViewById(R.id.asusimg);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareImage();
            }
        });









        addtoCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SaveCart();


            }
        });

        itemName.setText("iphone13 pro");
        plusquantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // coffee price
                int basePrice = 900;
                quantity++;
                displayQuantity();
                int itemmPrice = basePrice * quantity;
                String setnewPrice = String.valueOf(itemmPrice);
                itemPrice.setText(setnewPrice +"$");



                // checkBoxes functionality



            }
        });
        minusquantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int basePrice = 900;
                // because we dont want the quantity go less than 0
                if (quantity == 0) {
                    Toast.makeText(Activity7.this, "Cant decrease quantity < 0", Toast.LENGTH_SHORT).show();
                } else {
                    quantity--;
                    displayQuantity();
                    int itemmPrice = basePrice * quantity;
                    String setnewPrice = String.valueOf(itemmPrice);
                    itemPrice.setText(setnewPrice+"$");




                }
            }
        });


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity1();

            }
        });
    }

    private void shareImage() {
        StrictMode.VmPolicy.Builder builder= new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        drawable=(BitmapDrawable)image.getDrawable();
        bitmap=drawable.getBitmap();
        File file = new File(getExternalCacheDir()+"/"+"beutiful"+".png");
        Intent intent;
        try{
            FileOutputStream outputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG,90,outputStream);
            outputStream.flush();
            outputStream.close();
            intent =new Intent(Intent.ACTION_SEND);
            intent.setType("image/");
            intent.putExtra(Intent.EXTRA_STREAM,Uri.fromFile(file));
            intent.putExtra(Intent.EXTRA_TEXT,"iphone 13 pro For 900$");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);



        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        startActivity(Intent.createChooser(intent,"Share image Via:"));

    }


    private boolean SaveCart() {

        // getting the values from our views
        String name = itemName.getText().toString();
        String price = itemPrice.getText().toString();
        String quantity = quantitynumber.getText().toString();

        ContentValues values = new ContentValues();
        values.put(OrderContract.OrderEntry.COLUMN_NAME, name);
        values.put(OrderContract.OrderEntry.COLUMN_PRICE, price);
        values.put(OrderContract.OrderEntry.COLUMN_QUANTITY, quantity);



        if (mCurrentCartUri == null) {
            Uri newUri = getContentResolver().insert(OrderContract.OrderEntry.CONTENT_URI, values);
            if (newUri==null) {
                Toast.makeText(this, "Failed to add to Cart", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Success  adding to Cart", Toast.LENGTH_SHORT).show();

            }
        }

        hasAllRequiredValues = true;
        return hasAllRequiredValues;

    }

    private void displayQuantity() {
        quantitynumber.setText(String.valueOf(quantity));
    }

    public void openActivity1(){
        Intent intent =new Intent(this, com.example.project.MainActivity.class);
        startActivity(intent);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = {OrderContract.OrderEntry._ID,
                OrderContract.OrderEntry.COLUMN_NAME,
                OrderContract.OrderEntry.COLUMN_PRICE,
                OrderContract.OrderEntry.COLUMN_QUANTITY,

        };

        return new CursorLoader(this, mCurrentCartUri,
                projection,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {

        if (cursor == null || cursor.getCount() < 1) {
            return;
        }

        if (cursor.moveToFirst()) {

            int name = cursor.getColumnIndex(OrderContract.OrderEntry.COLUMN_NAME);
            int price = cursor.getColumnIndex(OrderContract.OrderEntry.COLUMN_PRICE);
            int quantity = cursor.getColumnIndex(OrderContract.OrderEntry.COLUMN_QUANTITY);

            String nameofitem = cursor.getString(name);
            String priceofitem = cursor.getString(price);
            String quantityofitem = cursor.getString(quantity);


            itemName.setText(nameofitem);
            itemPrice.setText(priceofitem);
            quantitynumber.setText(quantityofitem);
        }


    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {


        itemName.setText("");
        itemPrice.setText("");
        quantitynumber.setText("");

    }
}