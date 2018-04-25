package sang.com.commonlibrary.http.control;

import io.reactivex.disposables.Disposable;

/**
 * 作者： ${桑小年} on 2017/11/26.
 * 努力，为梦长留
 */

public class TaskControl {
    public interface OnTaskListener {
        void onTaskStart(Disposable d);

        void onTaskSuccess();

        void onTaskFail(String code, String msg);
    }
}
