package dishmenu.nerdcutlet.com.dishmenuandroidapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MenuItems extends AppCompatActivity {

    private static final String TAG="MenuItems";


    private DatabaseReference database,restdb,restdbR,selectdb;
    private FirebaseUser user;
    private String mUserId;

    private ListView listView,listViewR;
    private Button order;

    int item,item1;
    String selected,k,j,rate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_items);

        Intent i = getIntent();
        k =i.getStringExtra("selected");
        j =i.getStringExtra("menu");

        listView = (ListView) findViewById(R.id.listView3);
        order=(Button)findViewById(R.id.button2);

        user = FirebaseAuth.getInstance().getCurrentUser();
        database = FirebaseDatabase.getInstance().getReference();


        mUserId = user.getUid();

//        database.child("order").child(mUserId).child("orders").addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
////                    User user = dataSnapshot.getValue(User.class);
//
//                String FirstNameS =(String)dataSnapshot.child("order").getValue();
//
//                item=Integer.parseInt(FirstNameS);
//
//                Toast.makeText(MenuItems.this,"item no sucessfuk",Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                Log.w(TAG, "Failed to read value.", databaseError.toException());
//            }
//        });
//




        restdb=database.child("menuitem").child(j).child(k);
        restdbR=database.child("menuitems").child("McDonalds").child("rates").child("Dessert");

        FirebaseListAdapter<String> firebaseListAdapter = new FirebaseListAdapter<String>
                (this,String.class,android.R.layout.simple_list_item_1,restdb) {
            @Override
            protected void populateView(View v, String model, int position) {
                TextView textView = (TextView)v.findViewById(android.R.id.text1);
                textView.setText(model);

            }
        };






        listView.setAdapter(firebaseListAdapter);

        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selected = listView.getItemAtPosition(i).toString();


                final int item1=i*7+30;
                final String jS;
                jS=Integer.toString(item1);
                rate=jS;


                alertDialogBuilder.setMessage(selected +"\n Rate : ₹"+rate);

                alertDialogBuilder.setPositiveButton("Order Item ?", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        Toast.makeText(MenuItems.this,"Dish addded to your order!",Toast.LENGTH_LONG).show();
                        database.child("order").child(mUserId).child("orders").child("order1").child(Integer.toString(item)).setValue(selected);
                        item++;
                    }
                });



                alertDialogBuilder.setNegativeButton("Cancle",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MenuItems.this,"Cancled",Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();


            }
        });

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), OrderList.class));
            }
        });


    }
//
//    @Override
//    public void onBackPressed() {
//        Intent intent =   new Intent(getApplicationContext(), Menus.class);
//        Toast.makeText(MenuItems.this,"item == "+item,Toast.LENGTH_LONG).show();
//        intent.putExtra("menu",j);
//        intent.putExtra("item",item);
//        startActivity(intent);
//    }


}
//  #212121
//  #323232
//  #0D7377
//  #