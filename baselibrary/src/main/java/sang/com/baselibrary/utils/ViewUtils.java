package sang.com.baselibrary.utils;

import android.widget.TextView;

/**
 * 作者： ${PING} on 2018/4/25.
 */

public class ViewUtils {

    public static void setText(TextView text, String msg) {
        setText(text, msg, "--");
    }

    public static void setText(TextView text, String msg, String defaultMsg) {
        if (msg == null) {
            msg = defaultMsg;
        }
        text.setText(msg == null ? "" : msg);
    }


}
