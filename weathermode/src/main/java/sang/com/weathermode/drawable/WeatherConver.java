package sang.com.weathermode.drawable;


/**
 * 作者： ${PING} on 2018/4/2.
 */

public class WeatherConver {

    public enum WeatherType {
        CLEAR_D, CLEAR_N, RAIN_D, RAIN_N, SNOW_D, SNOW_N, CLOUDY_D, CLOUDY_N,
        OVERCAST_D, OVERCAST_N, FOG_D, FOG_N, HAZE_D, HAZE_N, SAND_D, SAND_N,
        WIND_D, WIND_N, RAIN_SNOW_D, RAIN_SNOW_N, UNKNOWN_D, UNKNOWN_N
    }

    public static class SkyBackground {

        //黑色背景
        public static final Integer[] BLACK = new Integer[]{0xff000000, 0xff000000};
//		public static final Integer[] CLEAR_D = new Integer[] { 0xff3d99c2, 0xff4f9ec5 };
//		public static final Integer[] CLEAR_N = new Integer[] { 0xff0d1229, 0xff262c42 };

        //晴天
        public static final Integer[] CLEAR_D = new Integer[]{0xff3d99c2, 0xff4f9ec5};
        public static final Integer[] CLEAR_N = new Integer[]{0xff0b0f25, 0xff252b42};
        //阴天
        public static final Integer[] OVERCAST_D = new Integer[]{0xff33425f, 0xff617688};//0xff748798, 0xff617688
        public static final Integer[] OVERCAST_N = new Integer[]{0xff262921, 0xff23293e};//0xff1b2229, 0xff262921
        // 雨天
        public static final Integer[] RAIN_D = new Integer[]{0xff4f80a0, 0xff4d748e};
        public static final Integer[] RAIN_N = new Integer[]{0xff0d0d15, 0xff22242f};
        // 雾
        public static final Integer[] FOG_D = new Integer[]{0xff688597, 0xff44515b};
        public static final Integer[] FOG_N = new Integer[]{0xff2f3c47, 0xff24313b};

        //下雪
        public static final Integer[] SNOW_D = new Integer[]{0xff4f80a0, 0xff4d748e};//临时用RAIN_D凑数的
        public static final Integer[] SNOW_N = new Integer[]{0xff1e2029, 0xff212630};
        // 多云
        public static final Integer[] CLOUDY_D = new Integer[]{0xff4f80a0, 0xff4d748e};//临时用RAIN_D凑数的
        public static final Integer[] CLOUDY_N = new Integer[]{0xff071527, 0xff252b42};// 0xff193353 };//{ 0xff0e1623, 0xff222830 }
        //霾
        public static final Integer[] HAZE_D = new Integer[]{0xff616e70, 0xff474644};// 0xff999b95, 0xff818e90
        public static final Integer[] HAZE_N = new Integer[]{0xff373634, 0xff25221d};

        //沙
        public static final Integer[] SAND_D = new Integer[]{0xffb5a066, 0xffd5c086};//0xffa59056
        public static final Integer[] SAND_N = new Integer[]{0xff312820, 0xff514840};
    }

    public static Integer[] creatSkyBackground(WeatherType type) {
        Integer[] weatherBgColor;
        switch (type) {
            //晴天
            case CLEAR_D:
                weatherBgColor = SkyBackground.CLEAR_D;
                break;
            case CLEAR_N:
                weatherBgColor = SkyBackground.CLEAR_N;
                break;

            //雨天
            case RAIN_D:
                weatherBgColor = SkyBackground.RAIN_D;
                break;
            case RAIN_N:
                weatherBgColor = SkyBackground.RAIN_N;
                break;

            //下雪
            case SNOW_D:
                weatherBgColor = SkyBackground.SNOW_D;
                break;
            case SNOW_N:
                weatherBgColor = SkyBackground.SNOW_N;
                break;

            //多云
            case CLOUDY_D:
                weatherBgColor = SkyBackground.CLOUDY_D;
                break;
            case CLOUDY_N:
                weatherBgColor = SkyBackground.CLOUDY_N;
                break;
            //阴天
            case OVERCAST_D:
                weatherBgColor = SkyBackground.OVERCAST_D;
                break;
            case OVERCAST_N:
                weatherBgColor = SkyBackground.OVERCAST_N;
                break;
            //大雾
            case FOG_D:
                weatherBgColor = SkyBackground.FOG_D;
                break;
            case FOG_N:
                weatherBgColor = SkyBackground.FOG_N;
                break;
            //雾霾
            case HAZE_D:
                weatherBgColor = SkyBackground.HAZE_D;
                break;
            case HAZE_N:
                weatherBgColor = SkyBackground.HAZE_N;
                break;
            //风沙
            case SAND_D:
                weatherBgColor = SkyBackground.SAND_D;
                break;
            case SAND_N:
                weatherBgColor = SkyBackground.SAND_N;
                break;
                //大风
            case WIND_D:
                weatherBgColor = SkyBackground.RAIN_D;
                break;
            case WIND_N:
                weatherBgColor = SkyBackground.RAIN_N;
                break;

                //雨夹雪
            case RAIN_SNOW_D:
                weatherBgColor = SkyBackground.RAIN_D;
                break;
            case RAIN_SNOW_N:
                weatherBgColor = SkyBackground.RAIN_N;
                break;

                //未知天气
            case UNKNOWN_D:
                weatherBgColor = SkyBackground.CLEAR_D;
                break;
            case UNKNOWN_N:
                weatherBgColor = SkyBackground.CLEAR_N;
                break;
            default:
                weatherBgColor = SkyBackground.CLEAR_D;
        }
        return weatherBgColor;
    }

    public static String creatSkyName(WeatherType type) {
        String weatherBgColor;
        switch (type) {
            //晴天
            case CLEAR_D:
                weatherBgColor = "晴天";
                break;
            case CLEAR_N:
                weatherBgColor ="晴天夜晚";
                break;

            //雨天
            case RAIN_D:
                weatherBgColor = "雨天";
                break;
            case RAIN_N:
                weatherBgColor = "雨天夜晚";
                break;

            //下雪
            case SNOW_D:
                weatherBgColor = "下雪天";
                break;
            case SNOW_N:
                weatherBgColor = "下雪天夜晚";
                break;

            //多云
            case CLOUDY_D:
                weatherBgColor = "多云";
                break;
            case CLOUDY_N:
                weatherBgColor = "夜间多云";
                break;
            //阴天
            case OVERCAST_D:
                weatherBgColor = "阴天";
                break;
            case OVERCAST_N:
                weatherBgColor = "阴天夜间";
                break;
            //大雾
            case FOG_D:
                weatherBgColor = "大雾";
                break;
            case FOG_N:
                weatherBgColor ="大雾夜间";
                break;
            //雾霾
            case HAZE_D:
                weatherBgColor = "雾霾";
                break;
            case HAZE_N:
                weatherBgColor = "雾霾夜间";
                break;

            //风沙
            case SAND_D:
                weatherBgColor = "风沙";
                break;
            case SAND_N:
                weatherBgColor =  "风沙夜间";
                break;

            //大风
            case WIND_D:
                weatherBgColor =  "大风";
                break;
            case WIND_N:
                weatherBgColor = "大风夜间";
                break;

            //雨夹雪
            case RAIN_SNOW_D:
                weatherBgColor =  "雨夹雪";
                break;
            case RAIN_SNOW_N:
                weatherBgColor =  "雨夹雪夜间";
                break;

            //未知天气
            case UNKNOWN_D:
                weatherBgColor = "未知天气";
                break;
            case UNKNOWN_N:
                weatherBgColor = "未知天气夜间";
                break;
            default:
                weatherBgColor = "未知天气";
        }
        return weatherBgColor;
    }
}
