package sang.com.weathermode.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import sang.com.weathermode.R;
import sang.com.weathermode.drawable.WeatherConver;

/**
 * 作者： ${PING} on 2018/4/2.
 */

public class WeatherFragment extends Fragment {

    private TextView tvWeather;
    WeatherConver.WeatherType type;




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.weather_fragment, null);
        tvWeather=inflate.findViewById(R.id.tv_weather);
        return inflate;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setWeatherName(type);

    }

    public void setType(WeatherConver.WeatherType type) {
        this.type = type;
        setWeatherName(type);
    }

    private void setWeatherName(WeatherConver.WeatherType type) {
        if (type!=null&&tvWeather!=null) {
            tvWeather.setText(WeatherConver.creatSkyName(type));
        }
    }

    public WeatherConver.WeatherType getType() {
        return type;
    }


}
