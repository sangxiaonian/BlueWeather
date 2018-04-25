package sang.com.commonlibrary.http;

import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * 作者： ${桑小年} on 2017/12/3.
 * 努力，为梦长留
 */

public class HttpParams {

    private Map<String, String> params;


    public HttpParams() {
        params = new TreeMap<>(
                new Comparator<String>() {
                    public int compare(String obj1, String obj2) {
                        // 升序排序
                        return obj1.compareTo(obj2);
                    }
                });
    }

    public void put(String key, String value) {
        params.put(key, value);
    }

    public void remove(String key) {
        params.remove(key);
    }

    public String get(String key) {
        return params.get(key);
    }


    public Map<String, String> getParams() {
        return params;
    }


    @Override
    public String toString() {
        Set<String> set = params.keySet();
        StringBuffer stringBuffer = new StringBuffer();

        for (String key : set) {
            if (key != null) {
                String value = params.get(key);
                if (value != null) {
                    stringBuffer.append(key).append("=").append(value).append("&");
                }
            }
        }
        String s = stringBuffer.toString();
        return s.length() > 1 ? s.substring(0, 1) : s;
    }
}
