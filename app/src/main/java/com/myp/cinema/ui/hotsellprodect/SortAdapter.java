package com.myp.cinema.ui.hotsellprodect;


import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.myp.cinema.R;
import com.myp.cinema.entity.HotSellBO;

import java.util.List;

public class SortAdapter extends RvAdapter<HotSellBO> {

    private int checkedPosition;
    private Context context;

    public void setCheckedPosition(int checkedPosition) {
        this.checkedPosition = checkedPosition;
        notifyDataSetChanged();
    }

    public SortAdapter(Context context, List<HotSellBO> list, RvListener listener) {
        super(context, list, listener);
        this.context = context;
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_sort_list;
    }

    @Override
    protected RvHolder getHolder(View view, int viewType) {
        return new SortHolder(view, viewType, listener);
    }

    private class SortHolder extends RvHolder<HotSellBO> {

        private TextView tvName;
        private View mView;
        private ImageView ivProdect;

        SortHolder(View itemView, int type, RvListener listener) {
            super(itemView, type, listener);
            this.mView = itemView;
            tvName = (TextView) itemView.findViewById(R.id.tv_sort);
            ivProdect = (ImageView) itemView.findViewById(R.id.ivProdect);
        }

        @Override
        public void bindHolder(HotSellBO hotSellBO, int position) {
            tvName.setText(hotSellBO.getItemClass().getName());
            Glide.with(context)
                    .load(hotSellBO.getItemClass().getImageUrl())
                    .asBitmap()
                    .centerCrop()
                    .dontAnimate()
                    .placeholder(R.drawable.zhanwei1)
                    .into(ivProdect);
            if (position == checkedPosition) {
                mView.setBackgroundColor(Color.parseColor("#FFFFFF"));
                tvName.setTextColor(Color.parseColor("#E04F2D"));
            } else {
                mView.setBackgroundColor(Color.parseColor("#EFEFEF"));
                tvName.setTextColor(Color.parseColor("#888888"));
            }
        }

    }
}
