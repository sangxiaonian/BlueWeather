package sang.com.weathermode.activity.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import sang.com.weathermode.fragment.WeatherFragment;

public class WeatherAdapter extends FragmentPagerAdapter {

  public   List<WeatherFragment> fragments = new ArrayList<>();

    public WeatherAdapter(FragmentManager fm, List<WeatherFragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }


    @Override
    public int getCount() {
        return fragments.size();
    }
}