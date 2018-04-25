package sang.com.commonlibrary.http;


import sang.com.commonlibrary.http.control.TaskControl;

/**
 * 作者： ${桑小年} on 2017/11/26.
 * 努力，为梦长留
 */

public class NetObserver<T> extends BaseObserver<T> {


    public NetObserver(TaskControl.OnTaskListener listener) {
        super(listener);
    }
}
