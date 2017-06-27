package recycledelegate;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by lxf on 2017/4/20.
 */

public class DelegateManager {
    private final int NO_VIEW_TYPE = -1;

    private List<Delegate> mDelegateList;

    @Inject
   public DelegateManager() {
       mDelegateList = new ArrayList<>();
   }

    public int getViewType(Object item) {
        for (int i = 0; i <= mDelegateList.size() - 1; i++) {
            Delegate delegate = mDelegateList.get(i);
            if (delegate.forViewType(item)) {
                return i;
            }
        }

        return NO_VIEW_TYPE;
    }

    public RecyclerView.ViewHolder createViewHolder(ViewGroup parent, int viewType) {
        if(viewType != NO_VIEW_TYPE) {
            Delegate delegate = mDelegateList.get(viewType);
            return delegate.createViewHolder(parent);
        }

        return null;
    }

    public void onBindView (Object item, RecyclerView.ViewHolder vh, int position) {
        int viewType = vh.getItemViewType();
        Delegate delegate = mDelegateList.get(viewType);
        delegate.bindView(item, vh, position);
    }

    public DelegateManager add(Delegate delegate) {
        mDelegateList.add(delegate);
        return this;
    }
}
