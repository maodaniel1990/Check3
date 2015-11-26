package lingntao.check3;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class FragLocal extends Fragment {
    //    private TextView textView1;
//    private ImageView imageView1;
    private static Context context;
    public static String[] idnote;
    public static String[] user;
    public static String[] dates;
    public static String[] sect;
    public static String[] prob;
    public static String[] note;
    public static Uri[] images;
    ListView listView;
    List<CustItem> rowItems;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_search, container, false);
        listView = (ListView) view.findViewById(R.id.list);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        rowItems = new ArrayList<>();
        try{
            for (int i = 0; i < dates.length; i++) {
                CustItem item;
                if(images[0].equals(Uri.EMPTY)){item = new CustItem(user[i], dates[i], sect[i], prob[i], note[i], idnote[i]);}
                else {item = new CustItem(images[i], user[i], dates[i], sect[i], prob[i], note[i], idnote[i]);}
                rowItems.add(item);
            }
            CustList adapter = new CustList(context, rowItems);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    TextView list_user = (TextView) view.findViewById(R.id.list_user);
                    TextView list_date = (TextView) view.findViewById(R.id.list_date);
                    TextView list_sect = (TextView) view.findViewById(R.id.list_sect);
                    TextView list_prob = (TextView) view.findViewById(R.id.list_prob);
                    TextView list_note = (TextView) view.findViewById(R.id.list_note);
                    final TextView list_idnote = (TextView) view.findViewById(R.id.list_idnote);
//                    CheckBox list_check = (CheckBox) view.findViewById(R.id.checkBox);
                    // 自定Layout
                    LayoutInflater inflater = getActivity().getLayoutInflater();
                    // 將 xml layout 轉換成視圖 View 物件
                    View layout = inflater.inflate(R.layout.cust_dialog, (ViewGroup) getActivity().findViewById(R.id.root));
                    // 自定View
                    final TextView dig_user = (TextView) layout.findViewById(R.id.dig_user);
                    final TextView dig_date = (TextView) layout.findViewById(R.id.dig_date);
                    final TextView dig_sect = (TextView) layout.findViewById(R.id.dig_sect);
                    final TextView dig_prob = (TextView) layout.findViewById(R.id.dig_prob);
                    final EditText dig_note = (EditText) layout.findViewById(R.id.dig_note);
                    final CheckBox dig_check = (CheckBox) layout.findViewById(R.id.dig_check);
                    dig_user.setText(list_user.getText().toString());
                    dig_date.setText(list_date.getText().toString());
                    dig_sect.setText(list_sect.getText().toString());
                    dig_prob.setText(list_prob.getText().toString());
                    dig_note.setText(list_note.getText().toString());
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle(R.string.button_text7);
                    builder.setIcon(android.R.drawable.ic_dialog_info);
                    builder.setView(layout);
                    builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String message="更新成功";
                            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                    });
                    builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.create().show();
//                    showDialog_7();
                    Log.e("debug_tag", "something");
                }
            });
            DBweb.strd=new String[1][1];
        }catch(Exception e){
            Toast.makeText(getActivity(), "查無照片與備註 (FragmentLocal)", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onPause() {
        super.onPause();
    }

    private void showDialog_7() {
        // 自定Layout
        LayoutInflater inflater = getActivity().getLayoutInflater();
        // 將 xml layout 轉換成視圖 View 物件
        View layout = inflater.inflate(R.layout.cust_dialog, (ViewGroup) getActivity().findViewById(R.id.root));
        // 自定View
        final TextView dig_user = (TextView) layout.findViewById(R.id.dig_user);
        final TextView dig_date = (TextView) layout.findViewById(R.id.dig_date);
        final TextView dig_sect = (TextView) layout.findViewById(R.id.dig_sect);
        final TextView dig_prob = (TextView) layout.findViewById(R.id.dig_prob);
        final EditText dig_note = (EditText) layout.findViewById(R.id.dig_note);
        final CheckBox dig_check = (CheckBox) layout.findViewById(R.id.dig_check);
        dig_user.setText("使用者：");
        dig_date.setText("日期：");
        dig_sect.setText("站台：");
        dig_prob.setText("問題：");
        dig_note.setText("備註：");
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.button_text7);
        builder.setIcon(android.R.drawable.ic_dialog_info);
        builder.setView(layout);
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String message;
                if(dig_check.isChecked())message="合格儲存成功";else message="不合格儲存成功";
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

}



//===================================List onItemClcik//===================================
//                    Toast toast = Toast.makeText(context,
//                            "Item " + (position + 1) + ": " + rowItems.get(position),
//                            Toast.LENGTH_SHORT);
//                    toast.show();