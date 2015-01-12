package com.onclick.raisesurvey;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;
import com.squareup.spoon.Spoon;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class SurveyTest extends ActivityInstrumentationTestCase2<MainActivity> {
    public SurveyTest() {
        super(MainActivity.class);
    }

    private static final String TAG = SurveyTest.class.getSimpleName();
    private Activity activity;
    private Solo solo;
    private Instrumentation instrumentation;
    private Context appContext;
    private int screenCount = 0;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        appContext = getInstrumentation().getTargetContext();

        activity = getActivity();
        solo = new Solo(getInstrumentation(), activity);
        instrumentation = getInstrumentation();

    }

    public void testListView() {

        solo.waitForActivity(MainActivity.class, 5000);

        Spoon.screenshot(activity,"init");

        solo.waitForView(R.id.listSurveyCards);

        Spoon.screenshot(activity,"init_list_view");

        solo.clickInList(0);

        solo.waitForView(R.id.buttonRefresh);

        Spoon.screenshot(activity,"post_list_view_click");

        return;

    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();

    }

}