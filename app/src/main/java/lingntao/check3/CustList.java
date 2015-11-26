package lingntao.check3;

import java.util.List;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustList extends BaseAdapter {
    Context context;
    List<CustItem> rowItems;

    public CustList(Context context, List<CustItem> items) {
        this.context = context;
        this.rowItems = items;
    }

    /*private view holder class*/
    private class ViewHolder {
        ImageView imageView;
        TextView list_user;
        TextView list_date;
        TextView list_sect;
        TextView list_prob;
        TextView list_note;
        TextView list_idnote;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        LayoutInflater mInflater = (LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.cust_list, null);
            holder = new ViewHolder();
            holder.list_user = (TextView) convertView.findViewById(R.id.list_user);
            holder.list_date = (TextView) convertView.findViewById(R.id.list_date);
            holder.list_sect = (TextView) convertView.findViewById(R.id.list_sect);
            holder.list_prob = (TextView) convertView.findViewById(R.id.list_prob);
            holder.list_note = (TextView) convertView.findViewById(R.id.list_note);
            holder.list_idnote = (TextView) convertView.findViewById(R.id.list_idnote);
            holder.imageView = (ImageView) convertView.findViewById(R.id.icon);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        CustItem rowItem = (CustItem) getItem(position);
        DisplayMetrics dm = new DisplayMetrics();
        holder.list_user.setText(rowItem.getUser());
        holder.list_date.setText(rowItem.getDate());
        holder.list_sect.setText(rowItem.getSect());
        holder.list_prob.setText(rowItem.getProb());
        holder.list_note.setText(rowItem.getNote());
        holder.list_idnote.setText(rowItem.getIdnote());

//        holder.imageView.setImageURI(rowItem.getImageId());
        try{
            holder.imageView.setMinimumHeight(dm.heightPixels);
            holder.imageView.setMinimumWidth(dm.widthPixels);
            String path=rowItem.getImageId().toString();
            holder.imageView.setImageBitmap(decodeSampledBitmapFromResource(path, 100, 100));
        }catch (Exception e){
        }

//        holder.imageView.setImageBitmap(decodeSampledBitmapFromResource(getResources(), R.id.myimage, 100, 100));

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

    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            final int halfHeight = height / 2;
            final int halfWidth = width / 2;
            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }
//    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId, int reqWidth, int reqHeight) {
//        // First decode with inJustDecodeBounds=true to check dimensions
//        final BitmapFactory.Options options = new BitmapFactory.Options();
//        options.inJustDecodeBounds = true;
//        BitmapFactory.decodeResource(res, resId, options);
//        BitmapFactory.decodeFile();
//        // Calculate inSampleSize
//        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
//        // Decode bitmap with inSampleSize set
//        options.inJustDecodeBounds = false;
//        return BitmapFactory.decodeResource(res, resId, options);
//    }

    public static Bitmap decodeSampledBitmapFromResource(String path, int reqWidth, int reqHeight) {
        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);
        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(path, options);
    }
}