package dishmenu.nerdcutlet.com.dishmenuandroidapp.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import dishmenu.nerdcutlet.com.dishmenuandroidapp.Model.dishes;
import dishmenu.nerdcutlet.com.dishmenuandroidapp.R;

import java.util.List;

import static dishmenu.nerdcutlet.com.dishmenuandroidapp.R.id.quantity;

/**
 * Created by deeksha on 15/3/17.
 */

public class CheckboxAdapter extends RecyclerView.Adapter<CheckboxAdapter.MyView> {
    private List<dishes> listselected;
    private ImageButton delete;
    private TextView Price, Name;
    private EditText Quantity;


    public CheckboxAdapter(View view) {
        super();
        Name = (TextView) view.findViewById(R.id.customText1);
        Price = (TextView) view.findViewById(R.id.customText2);
        Quantity=(EditText) view.findViewById(quantity);
        //checkbox = (CheckBox) view.findViewById(R.id.checkBox);
    }


    public CheckboxAdapter(List<dishes> listselected) {
        this.listselected = listselected;


    }


    @Override
    public CheckboxAdapter.MyView onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.checklist_custom, parent, false);
        return new CheckboxAdapter.MyView(itemView);
    }

    @Override
    public void onBindViewHolder(MyView holder, int position) {


        dishes dish = listselected.get(position);
        holder.Name.setText(dish.Name);
        holder.Price.setText(dish.Price);
       holder.Quantity.setText(""+dish.Quantity);



    }


    @Override
    public int getItemCount() {


        return listselected.size();
    }

    public static class MyView extends RecyclerView.ViewHolder {

        public TextView Name, Price,Quantity;
        public Object delete;

        public MyView(View view) {

            super(view);
            Name = (TextView) view.findViewById(R.id.checkText1);
            Price = (TextView) view.findViewById(R.id.checkText2);
            Quantity=(TextView) view.findViewById(R.id.checkText3);
        }

    }


}
