package lingntao.check3;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class FragWork extends Fragment implements View.OnClickListener{
    Context context;
    private File file;
    private String action;
    int iii=0;
    public static String sdPath;
    CustRGroup rg_sect, rg_prob;
    RadioButton rb_good, rb_bad;
    Button bt_cam;
    EditText et_note;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragView = inflater.inflate(R.layout.fragb_work, container, false);
        bt_cam = (Button) fragView.findViewById(R.id.bt_cam);
        bt_cam.setOnClickListener(this);
        rb_good=(RadioButton) fragView.findViewById(R.id.rb_good);
        rb_bad=(RadioButton) fragView.findViewById(R.id.rb_bad);
        rb_bad.setChecked(true);
        et_note = (EditText) fragView.findViewById(R.id.et_note);
        rg_sect = new CustRGroup(fragView,
                R.id.rb_s1, R.id.rb_s2, R.id.rb_s3, R.id.rb_s4, R.id.rb_s5,
                R.id.rb_s6, R.id.rb_s7, R.id.rb_s8, R.id.rb_s9, R.id.rb_s10);
        rg_prob=new CustRGroup(fragView,
                R.id.rb_p1, R.id.rb_p2, R.id.rb_p3, R.id.rb_p4, R.id.rb_p5, R.id.rb_p6, R.id.rb_p7);
        return fragView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_cam:
                Date dNow = new Date();
                SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
                SimpleDateFormat df2 = new SimpleDateFormat("yyyyMMdd_hhmmss");
                ActMain.str_temp[2]="'"+df.format(dNow)+"'";
                ActMain.str_temp[3]="'"+rg_sect.selected+"'";
                ActMain.str_temp[4]="'"+rg_prob.selected+"'";
                if(rb_good.isChecked()) ActMain.str_temp[5]="1";else ActMain.str_temp[5]="0";
                ActMain.str_temp[6]="'"+et_note.getText().toString()+"'";
                if(rb_good.isChecked()) ActMain.str_temp[7]="1";else ActMain.str_temp[7]="0";
                ActMain.str_temp[8]=sdPath + "/" + "IMG_"+df2.format(dNow)+".jpg";
                String str="";
                for(int i=0;i< ActMain.str_temp.length;i++){
                    str += ActMain.str_temp[i]+", ";
                }
                ActMain.dbl.addNote(ActMain.str_temp[4], ActMain.str_temp[3], ActMain.str_temp[2]
                        , ActMain.str_temp[5], ActMain.str_temp[7], ActMain.str_temp[6], ActMain.str_temp[8]);
                Log.e("debug_tag", str);
                String[] str_save = Arrays.copyOf(ActMain.str_temp, ActMain.str_temp.length);
                ActMain.strL_offline.add(str_save);
                et_note.setText("");
                Intent it = new Intent(action);
                file = new File(sdPath + "/" + "IMG_"+df2.format(dNow)+".jpg");// 將照片存入SD卡根路徑，檔名：photo1.png
                iii++;
                it.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                startActivityForResult(it, 100);
                Toast.makeText(getActivity(),"儲存成功",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        action = MediaStore.ACTION_IMAGE_CAPTURE;
        sdPath = Environment.getExternalStorageDirectory().getAbsolutePath();// 取得SD卡根路徑
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}



//================================BlankFrag//================================
//public class BFrag extends Fragment {
//    Context context;
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        context = getActivity();
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View fragView = inflater.inflate(R.layout.fragb_work, container, false);
//        return fragView;
//    }
//
//    @Override
//    public void onActivityCreated(Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//    }
//
//    @Override
//    public void onPause() {
//        super.onPause();
//    }


//====================================debug arrayList//====================================
//                for(int i=0;i<Main.strL_offline.size();i++){
//                    String[] strn=new String[3];
//                    String strg="";
//                    strn=Main.strL_offline.get(i);
//                    for(int j=0;j<strn.length;j++){
//                        strg+=strn[j]+", ";
//                    }
//                    Log.e("debug_tag", i+", "+strg);
//                }