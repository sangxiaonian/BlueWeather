package sang.com.weathermode.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;

import sang.com.baselibrary.base.BaseFragment;
import sang.com.weathermode.R;
import sang.com.weathermode.drawable.WeatherConver;

/**
 * 作者： ${PING} on 2018/4/2.
 */

public class WeatherFragment extends BaseFragment {

    WeatherConver.WeatherType type;

    @Override
    protected View initView(LayoutInflater inflater) {
        View inflate = inflater.inflate(R.layout.weather_fragment, null);
        inflate.findViewById(R.id.root).setBackgroundColor(getContext().getResources().getColor(R.color.color_tran));
        return inflate;
    }

    @Override
    protected void initData() {
        super.initData();
        setWeatherName(type);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    private void setWeatherName(WeatherConver.WeatherType type) {

    }

    public WeatherConver.WeatherType getType() {
        return type;
    }


    public void setType(WeatherConver.WeatherType type) {
        this.type = type;
    }
}
