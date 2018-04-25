package sang.com.commonlibrary.http;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 作者： ${桑小年} on 2017/10/15.
 * 努力，为梦长留
 */

public class RxUtils {


    public static <T> ObservableTransformer<T, T> getSchedulders() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream
                        .throttleFirst(3, TimeUnit.SECONDS)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());

            }
        };
    }


}
