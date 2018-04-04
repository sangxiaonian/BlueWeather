package sang.com.baselibrary.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import sang.com.baselibrary.utils.StatusBarUtils;

/**
 * 作者： ${PING} on 2018/4/4.
 */

public class BaseActivity extends AppCompatActivity {


    public Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext=this;
        StatusBarUtils.setTranslucent(this);
    }
}
