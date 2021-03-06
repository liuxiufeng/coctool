package recycledelegate;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lxf on 2017/4/20.
 */

public abstract class AbsBaseAdapter extends RecyclerView.Adapter {
    protected DelegateManager mDelegateManager;

    protected Context mContext;

    protected List<Object> mItems;

    public AbsBaseAdapter(Context context) {
        mContext = context;

        mItems = new ArrayList<>();
        mDelegateManager = new DelegateManager();
        init();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return mDelegateManager.createViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        mDelegateManager.onBindView(mItems.get(position), holder, position);
    }

    @Override
    public int getItemCount() {
        return mItems == null ? 0 : mItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mDelegateManager.getViewType(mItems.get(position));
    }

    public void addItem(Object item) {
        mItems.add(item);
    }

    public void setList(List list) {
        mItems = list;
    }

    protected abstract void init();
}
