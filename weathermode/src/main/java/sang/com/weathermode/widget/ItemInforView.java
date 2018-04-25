package sang.com.weathermode.widget;

import android.app.Fragment;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import sang.com.baselibrary.utils.ViewUtils;
import sang.com.weathermode.R;
import sang.com.weathermode.entity.ItemInfor;

/**
 * 作者： ${PING} on 2018/4/25.
 */

public class ItemInforView extends FrameLayout {
    TextView tvTitle;
    RoundProgressView progressView;
    LinearLayout ll;

    public ItemInforView(@NonNull Context context) {
        this(context, null, 0);
    }

    public ItemInforView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ItemInforView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs, defStyleAttr);
    }

    private void initView(Context context, AttributeSet attrs, int defStyleAttr) {

        View inflate = LayoutInflater.from(context).inflate(R.layout.item_infor_view, this, false);
        tvTitle = inflate.findViewById(R.id.tv_title);
        progressView = inflate.findViewById(R.id.round_weather);
        ll = inflate.findViewById(R.id.ll);
        addView(inflate);
    }


    public void setData(ItemInfor infor){
        if (infor!=null){
            ViewUtils.setText(tvTitle,infor.getTitle());
            progressView.setMax(infor.getMax());
            progressView.setMin(infor.getMin());
            progressView.setCurrent(infor.getCurrent());
            progressView.setCenterDes(infor.getCenterDes());
            progressView.invalidate();
            List<ItemInfor.Item> items = infor.getItems();
            if (items !=null&&!items.isEmpty()) {
                for (ItemInfor.Item item : items) {
                    View inflate = LayoutInflater.from(getContext()).inflate(R.layout.item_infor_view_item, ll, false);
                    TextView title=inflate.findViewById(R.id.tv_content_title);
                    TextView content=inflate.findViewById(R.id.tv_content_content);
                    ViewUtils.setText(title,item.getTitle());
                    ViewUtils.setText(content,item.getValue());
                    ll.addView(inflate);
                }
            }
        }
    }






}
