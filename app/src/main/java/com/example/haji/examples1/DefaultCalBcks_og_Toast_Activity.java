package com.example.haji.examples1;

import android.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DefaultCalBcks_og_Toast_Activity extends AppCompatActivity {

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


    /*
    * You can customize the location of your Toast by using:

setGravity(int gravity, int xOffset, int yOffset)

This allows you to be very specific about where you want the location of your Toast to be.

One of the most useful things about the xOffset and yOffset parameters is that you can use them to place the Toast relative to a certain View.

For example, if you want to make a custom Toast that appears on top of a Button, you could create a function like this:
private void displayToastAboveButton(View v, int messageId)
{
    int xOffset = 0;
    int yOffset = 0;
    Rect gvr = new Rect();

    View parent = (View) v.getParent();
    int parentHeight = parent.getHeight();

    if (v.getGlobalVisibleRect(gvr))
    {
        View root = v.getRootView();

        int halfWidth = root.getRight() / 2;
        int halfHeight = root.getBottom() / 2;

        int parentCenterX = ((gvr.right - gvr.left) / 2) + gvr.left;

        int parentCenterY = ((gvr.bottom - gvr.top) / 2) + gvr.top;

        if (parentCenterY <= halfHeight)
        {
            yOffset = -(halfHeight - parentCenterY) - parentHeight;
        }
        else
        {
            yOffset = (parentCenterY - halfHeight) - parentHeight;
        }

        if (parentCenterX < halfWidth)
        {
            xOffset = -(halfWidth - parentCenterX);
        }

        if (parentCenterX >= halfWidth)
        {
            xOffset = parentCenterX - halfWidth;
        }
    }

    Toast toast = Toast.makeText(getActivity(), messageId, Toast.LENGTH_SHORT);
    toast.setGravity(Gravity.CENTER, xOffset, yOffset);
    toast.show();
}

**/






    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_defualt_call_backs);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String msg="The onCreate() event";
        Log.d("DefaultCallBacks", ""+msg);

        fillUp_Taost(msg, R.drawable.create , Gravity.TOP , Gravity.LEFT);

        findViewById(R.id.btn_dialog_DefaultCalBcks_og_Toast).setOnClickListener(new VisDialog());

        //Back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    public class VisDialog implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            // 1. Instantiate an AlertDialog.Builder with its constructor
            AlertDialog.Builder builder = new AlertDialog.Builder(DefaultCalBcks_og_Toast_Activity.this);

            builder.setCancelable(true);
            builder.setTitle("Cancel");
            builder.setMessage("Are you sure?");
            builder.setPositiveButton("Confirm",
                    (dialog, which) -> finish());
            builder.setNegativeButton(android.R.string.cancel, null);

// 3. Get the AlertDialog from create()
            AlertDialog dialog = builder.create();
            dialog.show();
        }
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
