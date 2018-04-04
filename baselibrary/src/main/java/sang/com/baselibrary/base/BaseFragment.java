package sang.com.baselibrary.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 作者： ${PING} on 2018/4/4.
 */

public class BaseFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return initView(inflater);
    }

    /**
     * 初始化控件
     *
     * @param inflater
     * @return
     */
    protected View initView(LayoutInflater inflater) {
        return null;
    }

    /**
     * 初始化数据
     */
    protected void initData() {
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }
}
