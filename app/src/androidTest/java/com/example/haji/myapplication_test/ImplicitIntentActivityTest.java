package com.example.haji.examples1;


import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.net.Uri;
import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;

import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.allOf;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static org.hamcrest.CoreMatchers.not;

public class ImplicitIntentActivityTest {

    @Rule
    public ActivityTestRule<ImplicitIntentActivity> nameActivity = new ActivityTestRule(ImplicitIntentActivity.class);

    @Test
    public void useAppContext() throws Exception {

        Uri u = Uri.parse("android.resource://com.example.haji.examples1/drawable/b_android");

        Intent resultData = new Intent();
        resultData.setData(u);
        //ActivityResult(int resultCode, Intent resultData)
        Instrumentation.ActivityResult result = new Instrumentation.ActivityResult(Activity.RESULT_OK, resultData);

        Matcher<Intent> expectedIntent = allOf(hasAction(Intent.ACTION_OPEN_DOCUMENT));


        //For each time you call init(), you must follow it up with a call to release() at the end of the test. This will clear the Intents state and stop recording intents
        Intents.init();
        intending(expectedIntent).respondWith(result);
        onView(withId(R.id.btn_load_ImplicitIntentActivity)).perform(click());
        intended(expectedIntent); // to check if button5.onclick did actually launched Intent.ACTION_OPEN_DOCUMENT
        Intents.release();


       // https://blog.bananacoding.com/2016/08/03/testing-your-intent-with-expresso/#.WpBfNUso9QI

    }
}
