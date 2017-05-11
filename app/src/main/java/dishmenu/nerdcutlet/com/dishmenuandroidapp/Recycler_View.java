package dishmenu.nerdcutlet.com.dishmenuandroidapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import dishmenu.nerdcutlet.com.dishmenuandroidapp.Adapter.Adapter;
import dishmenu.nerdcutlet.com.dishmenuandroidapp.Model.dishes;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Recycler_View extends AppCompatActivity {

    public static List<dishes> listselected = new ArrayList<>();
    DatabaseReference mDatabase;
    DatabaseReference dishes;

   /*public Recycler_View(DatabaseReference mDatabase) {
pu        this.mDatabase = mDatabase;
    }*/

    RecyclerView recyclerView;
    List<dishes> list = new ArrayList<>();
   public List<dishes> listSelected =new ArrayList<>();
    private Adapter mAdapter;
    private EditText Quantity;


     Button but ;
    String j,k;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler__view);

        Intent i = getIntent();
        k =i.getStringExtra("selected");
        j =i.getStringExtra("menu");

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        but=(Button)findViewById(R.id.submit);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);

      //  recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        mAdapter = new Adapter(list);
        recyclerView.setAdapter(mAdapter);


        mDatabase = FirebaseDatabase.getInstance().getReference();
        dishes = mDatabase.child("menuitem").child("McDonalds").child("Drinks");


        /*recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                final TextView name,price;

                name=(TextView)view.findViewById(R.id.customText1);
                price=(TextView)view.findViewById(R.id.customText2);
                CheckBox checkbox;
                checkbox=(CheckBox) view.findViewById(R.id.checkBox);

                checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if(isChecked){
                            dishes f= new dishes(name.getText().toString().trim(),price.getText().toString().trim());
                            listselected.add(f);
                            Toast.makeText(Recycler_View.this,""+listselected.size(),Toast.LENGTH_LONG).show();

                        }


                    }
                });
            }
        }));*/


        dishes.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

               Quantity=(EditText) findViewById(R.id.quantity);
                HashMap hashMap = (HashMap) dataSnapshot.getValue();
                String a = (String) hashMap.get("Name");
                //String c=().getText().toString();

                String b = (String) hashMap.get("Price");
                dishes f = new dishes(a, b,"");

                list.add(f);
                mAdapter = new Adapter(list);
                recyclerView.setAdapter(mAdapter);
                System.out.println("woks" + a);


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



        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // EditText qty;
               //qty=(EditText) findViewById(R.id.quantity);
                Intent n = new Intent(Recycler_View.this,CheckBoxList.class);
                n.putExtra("but", (Serializable) listselected);
               //String somevariable=(qty).getText().toString();
              // n.putExtra("quantity",somevariable);
                startActivity(n);



            }});




    }

}





