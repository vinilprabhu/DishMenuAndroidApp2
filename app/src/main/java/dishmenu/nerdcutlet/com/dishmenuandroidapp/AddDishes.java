package dishmenu.nerdcutlet.com.dishmenuandroidapp;


import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import dishmenu.nerdcutlet.com.dishmenuandroidapp.Model.dishes;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import javax.net.ssl.HttpsURLConnection;

import static com.google.android.gms.common.internal.safeparcel.SafeParcelable.NULL;

public class AddDishes extends AppCompatActivity {

    private Button viewlist;

    private Button addButton;
    private EditText addName;
    private EditText addPrice;
    private DatabaseReference mDatabase;
    private DatabaseReference dish;


    Button but ;
    String j,k;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dishes);

        Intent i = getIntent();
        k =i.getStringExtra("selected");
        j =i.getStringExtra("menu");

        mDatabase = FirebaseDatabase.getInstance().getReference();
        dish = mDatabase.child("menuitem").child(j).child(k);



        addButton=(Button) findViewById(R.id.addButton);
        addName=(EditText) findViewById(R.id.addName);
        addPrice=(EditText) findViewById(R.id.addPrice);
        viewlist =(Button) findViewById(R.id.viewList);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = addName.getText().toString().trim();
                String price = addPrice.getText().toString().trim();
                String userId=dish.push().getKey();
                dishes briyani = new dishes (name,price,"0");

                dish.child(userId).setValue(briyani);
                Toast.makeText(AddDishes.this,"dish added",Toast.LENGTH_SHORT).show();

            }
        });

        viewlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent =   new Intent(getApplicationContext(), Recycler_View.class);
                intent.putExtra("selected",k);
                intent.putExtra("menu",j);
                startActivity(intent);
            }
        });




    }
}
