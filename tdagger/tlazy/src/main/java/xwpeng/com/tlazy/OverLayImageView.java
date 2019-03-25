package xwpeng.com.tlazy;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class OverLayImageView  extends RelativeLayout {
    public OverLayImageView(Context context) {
        super(context);
        init();
    }

    public OverLayImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    public OverLayImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();

    }

    public OverLayImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();

    }

    private void init(){
        ImageView imageView = new ImageView(getContext());
        imageView.setImageResource(R.mipmap.ic_launcher);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        addView(imageView, params);
        View view = new View(getContext());
        view.setBackgroundDrawable(getContext().getResources().getDrawable(R.drawable.over_layer));
        view.setClickable(true);
        addView(view, params);
    }
}
