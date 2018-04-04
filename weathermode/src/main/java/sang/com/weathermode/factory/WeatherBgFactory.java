package sang.com.weathermode.factory;


import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import sang.com.weathermode.drawable.WeatherConver;
import sang.com.weathermode.drawable.holder.BasicWeatherHolder;
import sang.com.weathermode.drawable.holder.CloudHolder;
import sang.com.weathermode.drawable.holder.FogHolder;
import sang.com.weathermode.drawable.holder.HazeHolder;
import sang.com.weathermode.drawable.holder.RainHolder;
import sang.com.weathermode.drawable.holder.SnowHolder;
import sang.com.weathermode.drawable.holder.StarHolder;
import sang.com.weathermode.drawable.holder.SunnyHolder;
import sang.com.weathermode.drawable.holder.WindHolder;


/**
 * 作者： ${PING} on 2018/3/30.
 * 天气背景
 */
public class WeatherBgFactory {


    public static List<BasicWeatherHolder> creatWeatherHolder(WeatherConver.WeatherType type, Context mContext, int width, int height) {
        List<BasicWeatherHolder> weatherBgColor = null;
        switch (type) {
            //晴天
            case CLEAR_D:
                weatherBgColor = creatSunny(mContext, 1, width, height);
                break;
            case CLEAR_N:
                weatherBgColor = creatStar(mContext, 100, width, height);
                break;

            //雨天
            case RAIN_D:
            case RAIN_N:
                weatherBgColor = creatRain(mContext, 100, width, height);
                break;

            //下雪
            case SNOW_D:
            case SNOW_N:
                weatherBgColor = creatSnow(mContext, 100, width, height);
                break;
            //多云
            case CLOUDY_D:
            case CLOUDY_N:
                weatherBgColor = creatCloud(mContext, 1, width, height);
                break;
            //阴天
            case OVERCAST_D:

            case OVERCAST_N:
                weatherBgColor = creatCloud(mContext, 1, width, height);
                break;
            //大雾
            case FOG_D:
                weatherBgColor = creatFog(false, mContext, 100, width, height);
                break;
            case FOG_N:
                weatherBgColor = creatFog(true, mContext, 100, width, height);
                break;
            //雾霾
            case HAZE_D:
                weatherBgColor = creatHaze(false, mContext, 100, width, height);
                break;
            case HAZE_N:
                weatherBgColor = creatHaze(true, mContext, 100, width, height);
                break;

            //风沙
            case SAND_D:
                weatherBgColor = creatSundAndWind(false,mContext, 100, width, height);
                break;
            case SAND_N:
                weatherBgColor = creatSundAndWind(true,mContext, 100, width, height);
                break;

            //大风
            case WIND_D:
                weatherBgColor = creatWind(false, mContext, 100, width, height);
                break;
            case WIND_N:
                weatherBgColor = creatWind(true, mContext, 100, width, height);
                break;

            //雨夹雪
            case RAIN_SNOW_D:
            case RAIN_SNOW_N:
                weatherBgColor = creatRainAndSnow(mContext, 100, width, height);
                break;

            //未知天气
            case UNKNOWN_D:
                break;
            case UNKNOWN_N:
                break;
            default:
        }
        return weatherBgColor;
    }


    public static List<BasicWeatherHolder> creatRain(Context context, int count, int width, int height) {
        List<BasicWeatherHolder> holders = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            RainHolder rainHolder = new RainHolder(width, height, context);
            holders.add(rainHolder);
        }
        return holders;
    }

    public static List<BasicWeatherHolder> creatSnow(Context context, int count, int width, int height) {
        List<BasicWeatherHolder> holders = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            SnowHolder rainHolder = new SnowHolder(width, height, context);
            holders.add(rainHolder);
        }
        return holders;
    }

    public static List<BasicWeatherHolder> creatRainAndSnow(Context context, int count, int width, int height) {
        List<BasicWeatherHolder> holders = new ArrayList<>();
        for (int i = 0; i < count / 2; i++) {
            RainHolder rainHolder = new RainHolder(width, height, context);
            holders.add(rainHolder);
            SnowHolder snowHolder = new SnowHolder(width, height, context);
            holders.add(snowHolder);
        }
        return holders;
    }

    public static List<BasicWeatherHolder> creatStar(Context context, int count, int width, int height) {
        List<BasicWeatherHolder> holders = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            StarHolder rainHolder = new StarHolder(width, height, context);
            holders.add(rainHolder);
        }
        return holders;
    }



    public static List<BasicWeatherHolder> creatCloud(Context context, int count, int width, int height) {
        List<BasicWeatherHolder> holders = new ArrayList<>();
        CloudHolder rainHolder1 = new CloudHolder(width, height, context);
        CloudHolder rainHolder = new CloudHolder(width, height, context);
        holders.add(rainHolder1);
        holders.add(rainHolder);
        return holders;
    }


    public static List<BasicWeatherHolder> creatSunny(Context context, int count, int width, int height) {
        List<BasicWeatherHolder> holders = new ArrayList<>();
        SunnyHolder sunnyHolder = new SunnyHolder(width, height, context);
        holders.add(sunnyHolder);
        return holders;
    }

    public static List<BasicWeatherHolder> creatHaze(boolean isNight, Context context, int count, int width, int height) {
        List<BasicWeatherHolder> holders = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            HazeHolder hazeHolder = new HazeHolder(isNight,width, height, context);
            holders.add(hazeHolder);
        }
        return holders;
    }public static List<BasicWeatherHolder> creatSundAndWind(boolean isNight, Context context, int count, int width, int height) {
        List<BasicWeatherHolder> holders = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            HazeHolder hazeHolder = new HazeHolder(isNight,width, height, context);
            WindHolder windHolder = new WindHolder(isNight,width, height, context);
            holders.add(windHolder);
            holders.add(hazeHolder);
        }
        return holders;
    }



    public static List<BasicWeatherHolder> creatFog(boolean isNight, Context context, int count, int width, int height) {
        List<BasicWeatherHolder> holders = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            FogHolder hazeHolder = new FogHolder(isNight,width, height, context);
            holders.add(hazeHolder);
        }
        return holders;
    }

    public static List<BasicWeatherHolder> creatWind(boolean isNight, Context context, int count, int width, int height) {
        List<BasicWeatherHolder> holders = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            WindHolder hazeHolder = new WindHolder(isNight,width, height, context);
            holders.add(hazeHolder);
        }
        return holders;
    }

}
