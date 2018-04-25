package sang.com.weathermode.entity;

import android.content.ClipData;

import java.util.List;

public   class ItemInfor {
    String title;
    int max;
    int min;
    int current;
    String centerDes;
    List<Item> items;

    public static class Item{
        String title;
        String value;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public String getCenterDes() {
        return centerDes;
    }

    public void setCenterDes(String centerDes) {
        this.centerDes = centerDes;
    }


}