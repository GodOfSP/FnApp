package com.fnhelper.fnapp.funcs;

import android.os.Build;

/**
 * Created by GuoYu on 2015/4/13.
 */
public class NavigationBar {
    public static void closeBar() {
        try {
            //需要root 权限
            Build.VERSION_CODES vc = new Build.VERSION_CODES();
            Build.VERSION vr = new Build.VERSION();
            String ProcID = "79";

            if (vr.SDK_INT >= vc.ICE_CREAM_SANDWICH) {
                ProcID = "42"; //ICS AND NEWER
            }

            //需要root 权限
            Process proc = Runtime.getRuntime().exec(new String[]{"su", "-c", "service call activity " + ProcID + " s16 com.android.systemui"}); //WAS 79
            proc.waitFor();

        } catch (Exception ex) {
//            Toast.makeText(activity.getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public static void showBar() {
        try {
            // 需要root 权限
            Process proc = Runtime.getRuntime().exec(
                    new String[]{"am", "startservice", "-n",
                            "com.android.systemui/.SystemUIService"}); // WAS
            // 79
            proc.waitFor();

        } catch (Exception ex) {
        }
    }
}
