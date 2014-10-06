package com.google.android.apps.iosched;

import android.test.ActivityInstrumentationTestCase2;
import android.test.InstrumentationTestCase;
import android.test.suitebuilder.annotation.LargeTest;

import com.google.android.apps.iosched.ui.HomeActivity;

import fr.spoonware.Spoonware;

/**
 * Created by nicolas on 01/10/2014.
 */
public class ReportTest extends ActivityInstrumentationTestCase2<HomeActivity> {

    public ReportTest() {
        super(HomeActivity.class);
    }

    @LargeTest
    public void testReportSize() throws Exception {

        Usage usage = new Usage();
        usage.start();

        Spoonware.init(getActivity().getApplication(), "spoonware.lille.inria.fr/rest", "ioapp", "AZERTY");
        Thread.sleep(2 * 1000);

        usage.end();
        System.out.println(usage.toCsv());

        usage = new Usage();
        usage.start();


        Spoonware.reportHandledException(new Exception());

        Thread.sleep(2 * 1000);

        usage.end();
        System.out.println(usage.toCsv());
    }
}
