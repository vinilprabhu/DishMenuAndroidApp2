package dishmenu.nerdcutlet.com.dishmenuandroidapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import dishmenu.nerdcutlet.com.dishmenuandroidapp.Adapter.CheckboxAdapter;
import dishmenu.nerdcutlet.com.dishmenuandroidapp.Model.dishes;

import java.util.ArrayList;
import java.util.List;

public class CheckBoxList extends AppCompatActivity {


    List<dishes> listselected = new ArrayList<>();
    RecyclerView selected;
    private CheckboxAdapter mAdapter;
    private Button cal,   confirm;
    float total;
    double z=300;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_box_list);
        selected = (RecyclerView) findViewById(R.id.select);
        RecyclerView.LayoutManager tLayoutManager = new LinearLayoutManager(getApplicationContext());
        selected.setLayoutManager(tLayoutManager);

       // selected.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        listselected=(List<dishes>)getIntent().getSerializableExtra("but");
        mAdapter=new CheckboxAdapter(listselected);
        selected.setAdapter(mAdapter);
        selected.setNestedScrollingEnabled(false);

        cal=(Button) findViewById(R.id.calculate);
        confirm=(Button)findViewById(R.id.confirmOrder);


        cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                calculate();




            }
        });

        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);


        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate();

                Toast.makeText(CheckBoxList.this,"Order confirmed!",Toast.LENGTH_SHORT).show();
                alertDialogBuilder.setMessage("Total : ₹"+z);

                alertDialogBuilder.setPositiveButton("Confirm Order?", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        Toast.makeText(CheckBoxList.this,"Order confirmed!",Toast.LENGTH_SHORT).show();
                        Intent intent =   new Intent(getApplicationContext(), ConfirmOrder.class);
//                intent.putExtra("menu",j);
                        startActivity(intent);
                    }
                });

                alertDialogBuilder.setNegativeButton("Cancle",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(CheckBoxList.this,"Cancled",Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();



            }
        });
        selected.addOnItemTouchListener(
                new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, final int position) {

                        ImageButton del;
                        del =(ImageButton) view.findViewById(R.id.delete);
                        del.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                listselected.remove(position);
                                selected.setAdapter(mAdapter);


                            }
                        });

                    }
                })
        );








    }


    public void calculate()
    {


        double a=0.06,total =0,b=0,c=0;

        for(int i=0;i<listselected.size();i++)
        {
            final int e = !listselected.get(i).Quantity.equals("")?Integer.parseInt(listselected.get(i).Quantity) : 1;

            total =total+ Double.parseDouble(listselected.get(i).Price.toString().trim())*e;
           b=total*a;
            c=total+b;


            z=c;

        }
        TextView sum =(TextView)findViewById(R.id.sum);
        sum.setText("   Total: "+total);
        TextView tax =(TextView)findViewById(R.id.tax);
        tax.setText("   Service tax: 6%");
        TextView t =(TextView)findViewById(R.id.total);
        t.setText("   Grand Total: "+c);


    }






}




