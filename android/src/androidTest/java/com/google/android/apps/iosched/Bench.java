package com.google.android.apps.iosched;

import android.net.TrafficStats;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.LargeTest;
import com.google.android.apps.iosched.ui.HomeActivity;
import com.robotium.solo.Solo;


/**
 * Created by nicolas on 30/09/2014.
 */


public class Bench extends ActivityInstrumentationTestCase2<HomeActivity> {

    private Solo solo;

    public Bench() {
        super(HomeActivity.class);
    }


    @LargeTest
    public void testLoginAndUse() {
        solo = new Solo(getInstrumentation(), getActivity());

        Usage usage = new Usage();
        usage.start();

        // Choose google + account
        clickText("Se connecter avec Google");

        // click on my account in list
        clickText("nicolas.petitprez@gmail.com");

        // we won't configure wifi
        clickText("No, I'll be viewing from home");

        // try to show keynote
        clickText("Keynote");

        // go to explore tab
        clickText("Explore");
        clickText("Tech Talk");
        clickText("Cognitive Science and Design");
        clickText("Add to schedule");
        // go to main screen
        solo.goBack();
        solo.goBack();

        // go on last tab
        clickText("Stream");

        // search something
        searchView("andr", "android");
        clickText("Best Practices for Bluetooth Development");
        clickText("Add to schedule");
        solo.goBack();
        solo.goBack();
        solo.goBack();

        solo.clickOnMenuItem("Sign out");

        usage.end();

        solo.finishOpenedActivities();

        System.out.println(usage.toCsv());
    }

    private void searchView(String text, String preview) {
        solo.clickOnActionBarItem(R.id.menu_search);
        solo.enterText(0, text);
        solo.clickOnText(preview);
    }

    private void clickText(String text) {
        solo.waitForText(text);
        solo.clickOnText(text);
    }

}
