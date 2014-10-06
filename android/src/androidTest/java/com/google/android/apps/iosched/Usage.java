package com.google.android.apps.iosched;

import android.net.TrafficStats;

/**
 * Created by nicolas on 30/09/2014.
 */
public class Usage {
    private static class BreadCrum {
        private final long time;
        private final long tx;
        private final long rx;

        private BreadCrum(int uid) {
            time = System.currentTimeMillis();


            tx = TrafficStats.getUidTxBytes(uid);
            rx = TrafficStats.getUidRxBytes(uid);
        }

        @Override
        public String toString() {
            return "BreadCrum{" +
                    "time=" + time +
                    ", tx=" + tx +
                    ", rx=" + rx +
                    '}';
        }
    }

    private final int myUid;
    BreadCrum start;
    BreadCrum end;

    public Usage() {
        myUid = android.os.Process.myUid();
    }

    public void start() {
        start = new BreadCrum(myUid);
    }

    public void end() {
        end = new BreadCrum(myUid);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("duration : ").append(end.time - start.time).append(" - ");

        builder.append("send : ").append(humanReadableByteCount(end.rx - start.rx, true)).append(" - ");
        builder.append("received : ").append(humanReadableByteCount(end.tx - start.tx, true));

        return builder.toString();
    }

    public String toCsv() {
        StringBuilder builder = new StringBuilder();

        builder.append(end.time - start.time).append(";");
        builder.append(humanReadableByteCount(end.rx - start.rx, true)).append(";");
        builder.append(humanReadableByteCount(end.tx - start.tx, true)).append(";");

        return builder.toString();
    }

    public static String humanReadableByteCount(long bytes, boolean si) {
        int unit = si ? 1000 : 1024;
        if (bytes < unit) return bytes + " B";
        int exp = (int) (Math.log(bytes) / Math.log(unit));
        String pre = (si ? "kMGTPE" : "KMGTPE").charAt(exp - 1) + (si ? "" : "i");
        return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
    }

}
