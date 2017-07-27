package com.shadow.coctool.common;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.shadow.coctool.R;

/**
 * Created by LXF on 2017/7/27.
 */

public class PaddingDecoration extends RecyclerView.ItemDecoration {
        private int divider;

        public PaddingDecoration(Context context) {

            divider = context.getResources().getDimensionPixelSize(R.dimen.default_padding);
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            outRect.bottom = divider;
        }
}
