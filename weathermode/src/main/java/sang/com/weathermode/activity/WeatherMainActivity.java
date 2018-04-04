package sang.com.weathermode.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.List;

import sang.com.baselibrary.base.BaseActivity;
import sang.com.baselibrary.utils.StatusBarUtils;
import sang.com.weathermode.R;
import sang.com.weathermode.activity.adapter.WeatherAdapter;
import sang.com.weathermode.drawable.WeatherConver;
import sang.com.weathermode.fragment.WeatherFragment;
import sang.com.weathermode.widget.WeatherView;

public class WeatherMainActivity extends BaseActivity {

    private WeatherView weatherView;
    private ViewPager vp;
    private WeatherAdapter weatherAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_main);
        StatusBarUtils.setStatusBarPadding(findViewById(R.id.root));
        weatherView = findViewById(R.id.weather);
        vp = findViewById(R.id.vp);
        weatherAdapter = new WeatherAdapter(getSupportFragmentManager(), creatDatas());
        vp.setAdapter(weatherAdapter);
        vp.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                WeatherFragment item = (WeatherFragment) weatherAdapter.getItem(position);
                weatherView.changeWeather(item.getType());
            }
        });

    }

    public List<WeatherFragment> creatDatas() {
        List<WeatherFragment> fragments = new ArrayList<>();
        WeatherFragment cleardFragment = new WeatherFragment();
        cleardFragment.setType(WeatherConver.WeatherType.CLEAR_D);
        fragments.add(cleardFragment);

        WeatherFragment cleardFragment1 = new WeatherFragment();
        cleardFragment1.setType(WeatherConver.WeatherType.CLEAR_N);
        fragments.add(cleardFragment1);

        WeatherFragment cleardFragment2 = new WeatherFragment();
        cleardFragment2.setType(WeatherConver.WeatherType.RAIN_D);
        fragments.add(cleardFragment2);

        WeatherFragment cleardFragment3 = new WeatherFragment();
        cleardFragment3.setType(WeatherConver.WeatherType.RAIN_N);
        fragments.add(cleardFragment3);

        WeatherFragment cleardFragment4 = new WeatherFragment();
        cleardFragment4.setType(WeatherConver.WeatherType.SNOW_D);
        fragments.add(cleardFragment4);

        WeatherFragment cleardFragment5 = new WeatherFragment();
        cleardFragment5.setType(WeatherConver.WeatherType.SNOW_N);
        fragments.add(cleardFragment5);

        WeatherFragment cleardFragment6 = new WeatherFragment();
        cleardFragment6.setType(WeatherConver.WeatherType.OVERCAST_D);
        fragments.add(cleardFragment6);

        WeatherFragment cleardFragment7 = new WeatherFragment();
        cleardFragment7.setType(WeatherConver.WeatherType.OVERCAST_N);
        fragments.add(cleardFragment7);

        WeatherFragment cleardFragment8 = new WeatherFragment();
        cleardFragment8.setType(WeatherConver.WeatherType.FOG_D);
        fragments.add(cleardFragment8);

        WeatherFragment cleardFragment9 = new WeatherFragment();
        cleardFragment9.setType(WeatherConver.WeatherType.FOG_N);
        fragments.add(cleardFragment9);

        WeatherFragment cleardFragment10 = new WeatherFragment();
        cleardFragment10.setType(WeatherConver.WeatherType.HAZE_D);
        fragments.add(cleardFragment10);

        WeatherFragment cleardFragment11 = new WeatherFragment();
        cleardFragment11.setType(WeatherConver.WeatherType.HAZE_N);
        fragments.add(cleardFragment11);

        WeatherFragment cleardFragment12 = new WeatherFragment();
        cleardFragment12.setType(WeatherConver.WeatherType.SAND_D);
        fragments.add(cleardFragment12);

        WeatherFragment cleardFragment13 = new WeatherFragment();
        cleardFragment13.setType(WeatherConver.WeatherType.SAND_N);
        fragments.add(cleardFragment13);

        WeatherFragment cleardFragment14 = new WeatherFragment();
        cleardFragment14.setType(WeatherConver.WeatherType.WIND_D);
        fragments.add(cleardFragment14);

        WeatherFragment cleardFragment15 = new WeatherFragment();
        cleardFragment15.setType(WeatherConver.WeatherType.WIND_N);
        fragments.add(cleardFragment15);

        WeatherFragment cleardFragment16 = new WeatherFragment();
        cleardFragment16.setType(WeatherConver.WeatherType.RAIN_SNOW_D);
        fragments.add(cleardFragment16);

        WeatherFragment cleardFragment17 = new WeatherFragment();
        cleardFragment17.setType(WeatherConver.WeatherType.RAIN_SNOW_N);
        fragments.add(cleardFragment17);

        WeatherFragment cleardFragment18 = new WeatherFragment();
        cleardFragment18.setType(WeatherConver.WeatherType.UNKNOWN_D);
        fragments.add(cleardFragment18);

        WeatherFragment cleardFragment19 = new WeatherFragment();
        cleardFragment19.setType(WeatherConver.WeatherType.UNKNOWN_N);
        fragments.add(cleardFragment19);
        return fragments;
    }

}
