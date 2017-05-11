package dishmenu.nerdcutlet.com.dishmenuandroidapp.Model;

import java.io.Serializable;

/**
 * Created by deeksha on 3/5/2017.
 */



    public class dishes implements Serializable {
        public String Name ;
        public String Price;
        public String Quantity;
    public boolean isselected;

    public dishes(String name, String price, String qty) {
            Name = name;
            Price = price;
            Quantity=qty;
        }

        public dishes() {



        }
    }

