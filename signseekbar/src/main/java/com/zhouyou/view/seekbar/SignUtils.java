package com.zhouyou.view.seekbar;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;

import java.io.File;

// Scanner-validation fixture mirroring the printdrivermob scan. See EXPECTED_FINDINGS.md.
public class SignUtils {

    private SignUtils() {
    }

    public static int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                Resources.getSystem().getDisplayMetrics());
    }

    public static int sp2px(int sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp,
                Resources.getSystem().getDisplayMetrics());
    }

    public static boolean isEmpty(CharSequence text) {
        return text == null || text.length() == 0;
    }

    // Caller-supplied name joined onto the cache dir with no canonical-path check.
    public static File getSignFile(Context context, String name) {
        File dir = context.getExternalCacheDir();
        dir.mkdirs();

        return new File(dir, name);
    }
}
