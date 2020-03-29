package com.example.haji.examples1;
import android.content.Intent;
import android.net.Uri;
import android.os.Parcelable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
//import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.haji.examples1.inflate.MyLayoutInflator;
import com.example.haji.examples1.parcelable.Person;
import com.example.haji.examples1.parcelable.PersonActivity;

public class MainActivity extends AppCompatActivity {
    Person p=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String picPath = "android.resource://com.example.haji.examples1/drawable/"+"b_android";
        Log.i("NOE", "##########################"+(picPath) );
        Uri uri = Uri.parse(picPath);
        p = new Person("navn1", picPath);


        Button btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(( View view)->{
            Intent intent = new Intent(MainActivity.this, PersonActivity.class);
            intent.putExtra("person" , (Parcelable) p);
            startActivity(intent); });
        registerForContextMenu(findViewById(R.id.linearLayout));

        Button btn_callBcks = (Button) findViewById(R.id.btn_callBcks);
        btn_callBcks.setOnClickListener(( View view)->{
            Intent intent = new Intent(MainActivity.this, DefaultCalBcks_og_Toast_Activity.class);
            startActivity(intent); });


        Button btn_showItems = (Button) findViewById(R.id.btn_showItems);
        btn_showItems.setOnClickListener(( View view)->{
            Intent intent = new Intent(MainActivity.this, ShowItemsActivity.class);
            startActivity(intent); });


        Button btn_valid = (Button) findViewById(R.id.btn_valid);
        btn_valid.setOnClickListener(( View view)->{
            Intent intent = new Intent(MainActivity.this, TextValidationActivity.class);
            startActivity(intent); });


        Button btn_asyncUsage = (Button) findViewById(R.id.btn_asyncUsage);
        btn_asyncUsage.setOnClickListener(( View view)->{
            Intent intent = new Intent(MainActivity.this, AsyncUsageActivity.class);
            startActivity(intent); });


        Button btn_telOgSms = (Button) findViewById(R.id.btn_telOgSms);
        btn_telOgSms.setOnClickListener(( View view)->{
            startActivity(new Intent(MainActivity.this, TelOgSmsActivity.class)); });


        Button btn_implicitIntent = (Button) findViewById(R.id.btn_implicitIntent);
        btn_implicitIntent.setOnClickListener(( View view)->{
            startActivity(new Intent(MainActivity.this, ImplicitIntentActivity.class)); });

        Button btn_inflator = (Button) findViewById(R.id.btn_inflator);
        btn_inflator.setOnClickListener(( View view)->{
            startActivity(new Intent(MainActivity.this, MyLayoutInflator.class)); });


        String msg="The onCreate() event";
        Log.d("DefaultCallBacks", ""+msg);
        fillUp_Taost(msg, R.drawable.create , Gravity.TOP , Gravity.LEFT);

        getSupportActionBar().setTitle("Simple Layout");

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_simple_layout, menu);

        setIntentOnMenuItem(menu, R.id.defaultCalBcks_og_Toast_Activity_item,
                new Intent(this, DefaultCalBcks_og_Toast_Activity.class));

        setIntentOnMenuItem(menu, R.id.showItemsActivity_item,
                new Intent(this, ShowItemsActivity.class));

        setIntentOnMenuItem(menu, R.id.personActivity_item,
                new Intent(this, PersonActivity.class).putExtra("person" , (Parcelable) p));

        setIntentOnMenuItem(menu, R.id.asyncUsageActivity_item,
                new Intent(this, AsyncUsageActivity.class));

        setIntentOnMenuItem(menu, R.id.asyncUsageActivity_item,
                new Intent(this, AsyncUsageActivity.class));

        setIntentOnMenuItem(menu, R.id.telOgSms,
                new Intent(this, TelOgSmsActivity.class));

        setIntentOnMenuItem(menu, R.id.implicit_Intent,
                new Intent(this, ImplicitIntentActivity.class));

        setIntentOnMenuItem(menu, R.id.inflator,
                new Intent(this, MyLayoutInflator.class));

        return true;

    }

    boolean toggel = true;
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem dcActivity =  menu.findItem(R.id.defaultCalBcks_og_Toast_Activity_item);
        toggel = !toggel;
        dcActivity.setEnabled(toggel);
        return true;
    }


    private void setIntentOnMenuItem(Menu menu, int menuId,
                                     Intent intent) {
        MenuItem menuItem = menu.findItem(menuId);
        if (menuItem != null) {
            menuItem.setIntent(intent);
        } else {
            Log.w("DEBUG_TAG", "Warning: Can't find menu item: " + menuId);
        }
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu,
                                    View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_context, menu);
    }











    public void fillUp_Taost(String m , int resDrawable, int vertical , int horizontal){

        LayoutInflater inflater = getLayoutInflater();
        View inflatedLayout = inflater.inflate(R.layout.custom_toast,
                (ViewGroup) findViewById(R.id.custom_toast_container));
        TextView toastMsg = (TextView) inflatedLayout.findViewById(R.id.textVeiw_toastMsg);
        ImageView toastIcon = (ImageView) inflatedLayout.findViewById(R.id.imageView_toastIcon);
        Toast toast = new Toast(getApplicationContext());

        toastMsg.setText(m);
        toastIcon.setImageResource(resDrawable);

        toast.setGravity(vertical|horizontal, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(inflatedLayout);
        toast.show();
    }


    /** Called when the activity is about to become visible. */
    @Override
    protected void onStart() {
        super.onStart();

        String msg="The onStart() event";
        Log.d("DefaultCallBacks", ""+msg);
        fillUp_Taost(msg, R.drawable.start, Gravity.TOP , Gravity.CENTER);

    }

    /** Called when the activity has become visible. */
    @Override
    protected void onResume() {
        super.onResume();

        String msg="The onResume() event";
        Log.d("DefaultCallBacks", ""+msg);
        fillUp_Taost(msg, R.drawable.resume, Gravity.TOP , Gravity.RIGHT);

    }

    /** Called when another activity is taking focus. */
    @Override
    protected void onPause() {
        super.onPause();

        String msg="The onPause() event";
        Log.d("DefaultCallBacks", ""+msg);
        fillUp_Taost(msg, R.drawable.pause, Gravity.BOTTOM , Gravity.LEFT);

    }

    /** Called when the activity is no longer visible. */
    @Override
    protected void onStop() {
        super.onStop();

        String msg="The onStop() event";
        Log.d("DefaultCallBacks", ""+msg);
        fillUp_Taost(msg, R.drawable.stop, Gravity.BOTTOM , Gravity.CENTER);

    }

    /** Called just before the activity is destroyed. */
    @Override
    public void onDestroy() {
        super.onDestroy();

        String msg="The onDestroy() event";
        Log.d("DefaultCallBacks", ""+msg);
        fillUp_Taost(msg, R.drawable.destroy, Gravity.BOTTOM , Gravity.RIGHT);

    }



}
