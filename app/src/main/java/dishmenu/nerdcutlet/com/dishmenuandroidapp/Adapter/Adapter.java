package dishmenu.nerdcutlet.com.dishmenuandroidapp.Adapter;

import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import dishmenu.nerdcutlet.com.dishmenuandroidapp.Model.dishes;
import dishmenu.nerdcutlet.com.dishmenuandroidapp.R;
import dishmenu.nerdcutlet.com.dishmenuandroidapp.Recycler_View;

import java.util.List;

/**
 * Created by Deeksha on 3/5/2017.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.MyView> {

    private List<dishes> dishList;
    
    private CheckBox checkbox;


    public Adapter(View view) {
        super();
        //Name = (TextView) view.findViewById(R.id.customText1);
        checkbox = (CheckBox) view.findViewById(R.id.checkBox);
    }

    public Adapter(List<dishes> dishList) {
        this.dishList = dishList;
    }

    @Override
    public MyView onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom, parent, false);

        return new MyView(itemView);

    }

    @Override
    public void onBindViewHolder(final MyView holder, final int position) {
        final dishes dish=dishList.get(position);
        holder.Name.setText(dish.Name);
        holder.Price.setText(dish.Price);
        holder.Quantity.setText(String.valueOf(dish.Quantity));

        holder.Quantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if(s.toString()!="")
                {


                    dish.Quantity=(holder.Quantity.getText().toString());
                }

            }
        });


        if(!dish.isselected)
        {
            holder.checkbox.setChecked(false);
        }
        else
        {
            holder.checkbox.setChecked(true);
        }
     holder.checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
         @Override
         public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

             if(b){
                 dish.Quantity=(holder.Quantity.getText().toString());


                 Recycler_View.listselected.add(dish);
                 dish.isselected=true;
                 holder.quantityLayout.setVisibility(View.VISIBLE);




             }
             else if(dish.isselected)
             {
                 if(Recycler_View.listselected.get(position)!=null)
                 {

                     Recycler_View.listselected.remove(position);
                     dish.isselected=false;
                     holder.quantityLayout.setVisibility(View.VISIBLE);

                 }


             }

         }
     });



    }

    @Override
    public int getItemCount() {
        return dishList.size();
    }

    public class MyView extends RecyclerView.ViewHolder{

        public TextView Name,Price;
        public EditText Quantity;
        public CheckBox checkbox;
        public TextInputLayout quantityLayout;

        public MyView(View view){
            super(view);
            this.setIsRecyclable(false);

            Name = (TextView) view.findViewById(R.id.customText1);
            Price = (TextView) view.findViewById(R.id.customText2);
            Quantity=(EditText) view.findViewById(R.id.quantity) ;
            quantityLayout=(TextInputLayout) view.findViewById(R.id.quantitylayout);
            checkbox=(CheckBox) view.findViewById(R.id.checkBox);



        }

    }



}
