package com.example.haji.examples1.inflate

import android.os.Bundle
import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import com.example.haji.examples1.R

import kotlinx.android.synthetic.main.activity_layout_inflator.*

class MyLayoutInflator : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layout_inflator)

        val layoutInflater1 = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater // import android.content.Context
//        val layoutInflater =  layoutInflater //  java getLayoutInflator()

        val frameLayout_blue :FrameLayout = findViewById<FrameLayout>(R.id.frameLayout_Inflator)
        val linearLayout_yellow :LinearLayout = findViewById<LinearLayout>(R.id.linearLayout_Inflator)

/**        case1 although the textView have height and width ="wrap_content" , the v_textView will have height and width = "match_parent" */
//        val v_textView = layoutInflater.inflate(R.layout.txtview, null)
//        frameLayout_blue.addView(v_textView) // OBS v_textView will fill the screen
//        linearLayout_yellow.addView(v_textView)  // OBS v_textView will fill the linearLayout



/*        case2  inflate return the parent linearLayout + use layoutParams specified by frameLayout */
//        val v_linearLayout_yellow = layoutInflater1.inflate(R.layout.txtview, linearLayout_yellow)


/*        case3  inflate return the TextView which have a parent = null + use layoutParams specified by linearLayout */
        val v_textView = layoutInflater1.inflate(R.layout.txtview, linearLayout_yellow , false)
//        frameLayout_blue.addView(v_textView) // invisible linearLayout_yellow
        linearLayout_yellow.addView(v_textView) // the same as case2





    }

}
