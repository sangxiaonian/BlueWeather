package sang.com.commonlibrary.http;


import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import sang.com.commonlibrary.http.control.TaskControl;
import sang.com.commonlibrary.http.neterror.NetException;

/**
 * 作者： ${桑小年} on 2017/11/26.
 * 努力，为梦长留
 */

public class BaseObserver<T> implements Observer<T> {

    protected TaskControl.OnTaskListener listener;

    public BaseObserver(TaskControl.OnTaskListener listener) {
        this.listener = listener;
    }

    @Override
    public void onSubscribe(Disposable d) {
        if (listener != null) {
            listener.onTaskStart(d);
        }
    }

    @Override
    public void onNext(T t) {

    }


    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        if (listener != null) {
            if (e instanceof NetException) {
                listener.onTaskFail(((NetException) e).getErrorCode(), ((NetException) e).getErrorMSg());
            }else {
                listener.onTaskFail("400", e.getMessage());
            }
        }
    }

    @Override
    public void onComplete() {
        if (listener != null) {
            listener.onTaskSuccess();
        }
    }
}
