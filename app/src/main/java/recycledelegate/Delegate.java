package recycledelegate;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Created by lxf on 2017/4/20.
 */

public interface Delegate {

    boolean forViewType(Object item);

    RecyclerView.ViewHolder createViewHolder(ViewGroup parent);

   void bindView(Object item, RecyclerView.ViewHolder vh, int position);
}
