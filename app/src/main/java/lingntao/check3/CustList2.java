package lingntao.check3;

import java.util.List;
import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CustList2 extends BaseAdapter {
    Context context;
    List<CustItem> rowItems;

    public CustList2(Context context, List<CustItem> items) {
        this.context = context;
        this.rowItems = items;
    }

    /*private view holder class*/
    private class ViewHolder {
        TextView list_user;
        TextView list_SD;
        TextView list_store;
        TextView list_idform;
        TextView list_totscore;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        LayoutInflater mInflater = (LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.cust_list2, null);
            holder = new ViewHolder();
            holder.list_user = (TextView) convertView.findViewById(R.id.list_user);
            holder.list_SD = (TextView) convertView.findViewById(R.id.list_SD);
            holder.list_store = (TextView) convertView.findViewById(R.id.list_store);
            holder.list_idform = (TextView) convertView.findViewById(R.id.list_idform);
            holder.list_totscore = (TextView) convertView.findViewById(R.id.list_totscore);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }
        CustItem rowItem = (CustItem) getItem(position);
        holder.list_user.setText(rowItem.getUser());
        holder.list_SD.setText(rowItem.getSD());
        holder.list_store.setText(rowItem.getStore());
        holder.list_idform.setText(String.valueOf(rowItem.getIdform()));
        if(rowItem.getTotscore()==0){
            holder.list_totscore.setText("得分");
        }else holder.list_totscore.setText(String.valueOf(rowItem.getTotscore()));
        return convertView;
    }

    @Override
    public int getCount() {
        return rowItems.size();
    }

    @Override
    public Object getItem(int position) {
        return rowItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return rowItems.indexOf(getItem(position));
    }

}