package com.example.haji.examples1;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class ShowItemsActivity extends AppCompatActivity {

    private String [][] items = {
            {"00","01","02"},
            {"10","11","12"},
            {"20","21","22"},
            {"30","31","32"},
            {"40","41","42"},
            {"50","51","52"},
            {"60","61","62"},
            {"70","71","72"},
            {"80","81","82"},
            {"90","91","92"}
    };

    ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_items);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        HashMap<String,String> item;
        for(int i=0;i<items.length;i++){
            item = new HashMap<String,String>();
            item.put( "name", items[i][0]);
            item.put( "id", items[i][1]);
            item.put( "price", items[i][2]);
            list.add( item );
        }


//  SimpleAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to)
        SimpleAdapter sa = new SimpleAdapter(
                this,  //Context context
                list, // List<? extends Map<String, ?>> data
                R.layout.item_layout, // int resource
                new String[] { "name","id","price" }, // String[] from
                new int[] {R.id.item_name, R.id.item_id, R.id.item_price} //int[] to
                );


        ((ListView)findViewById(R.id.listView_showItems)).setAdapter(sa);


        //Back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }






}
