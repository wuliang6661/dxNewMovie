package com.myp.cinema.widget;

import android.content.Context;
import android.util.AttributeSet;

/**
 * Created by Witness on 2020/12/10
 * Describe:
 */
public class AlwaysMarqueeTextView extends androidx.appcompat.widget.AppCompatTextView {

    public AlwaysMarqueeTextView(Context context) {
        super(context);
    }

    public AlwaysMarqueeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AlwaysMarqueeTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean isFocused() {
        return true;
    }
}