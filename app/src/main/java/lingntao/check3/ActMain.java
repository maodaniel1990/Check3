package lingntao.check3;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ActMain extends AppCompatActivity {
//    TextView tv_user, tv_store, tv_date;
    public static String[] str_set=new String[3];
    public static ArrayList<String[]> strL_offline =new ArrayList<>();
    public static ArrayList<String> strL_insert =new ArrayList<>();
    public static String[] str_temp =new String[9];
    public static int id_note=0;
    public static int id_form=0;
    public static int temp_idform=0;
    DBweb fm_link =new DBweb();
    public static DBlocal dbl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);
        dbl=new DBlocal(this, DBlocal.tb_note, null, 1);
        dbl=new DBlocal(this, DBlocal.tb_form, null, 1);
        Intent intent=getIntent();
        String msg = intent.getStringExtra(ActLogin.EXTRA_MESSAGE);
        setTitle(msg);
        str_set[0]=msg.substring(4);
        Log.e("debug_tag", msg + " ROCK!!");

        final FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_addin_linearlayout, new FragStore(), "f_a");
        ft.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        final FragmentTransaction ft = getFragmentManager().beginTransaction();
        //noinspection SimplifiableIfStatement
        switch (id){
            case R.id.action_settings:
                putin();
                buildin();
                for(String str:strL_insert){
                    fm_link.linkinsert(str);
                }
                strL_insert.clear();
                strL_offline.clear();
                Toast.makeText(getApplicationContext(), "上傳成功", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.search:
                do_2ndCk();
                ft.replace(R.id.fragment_addin_linearlayout, new FragSearch(), "f_c");
                ft.commit();
                return true;
            case R.id.local:
                do_local();
                ft.replace(R.id.fragment_addin_linearlayout, new FragLocal(), "f_l");
                ft.commit();
                return true;
            case R.id.form:
                do_form();
                ft.replace(R.id.fragment_addin_linearlayout, new FragForm(), "f_f");
                ft.commit();
                return true;
            case R.id.score:
                ft.replace(R.id.fragment_addin_linearlayout, new FragScore(), "f_s");
                ft.commit();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void putin(){
        fm_link.linkselectdouble("select idnote from note order by idnote desc limit 1");
        id_note = Integer.parseInt(DBweb.strd[0][0])+1;
        fm_link.linkselectdouble("select idform from form order by idform desc limit 1");
        id_form = Integer.parseInt(DBweb.strd[0][0])+1;
        fm_link.linkinsert("insert into form(idform, user, store, SD) values("
                +id_form+", '"+str_set[0]+"', '"+str_set[1]+"', '"+str_set[2]+"')");
        for(int i=0;i< strL_offline.size();i++){
            str_temp = strL_offline.get(i);
            str_temp[0]=String.valueOf(id_note);
            str_temp[1]=String.valueOf(id_form);
            strL_offline.set(i, str_temp);
            id_note++;
        }
    }

    public void buildin(){
        for(int i=0;i< strL_offline.size();i++){
            String str="";
            for(int j=0;j< str_temp.length-2;j++){
                str_temp = strL_offline.get(i);
                str+= str_temp[j]+", ";
            }str+= str_temp[str_temp.length-2];
            strL_insert.add(i, "insert into note(idnote, idform, date1, section, problem, check1, note, check2) values("+str+")");
        }
    }

    private String doSmart(String hey){
        String yo="";
        switch(hey){
            case "s1":yo="品壽司區";break;
            case "s2":yo= "壽司吧台";break;
            case "s3":yo= "煮物";break;
            case "s4":yo= "沙拉水果吧";break;
            case "s5":yo= "水砧區";break;
            case "s6":yo= "炸物";break;
            case "s7":yo= "外場";break;
            case "s8":yo= "後場/公共區域";break;
            case "s9":yo= "表單確認";break;
            case "s10":yo= "烤物";break;
            case "p1":yo= "整理整潔";break;
            case "p2":yo= "日期標示";break;
            case "p3":yo= "食材使用庫存管理";break;
            case "p4":yo= "個人衛生";break;
            case "p5":yo= "交互污染";break;
            case "p6":yo= "蟲鼠害問題";break;
            case "p7":yo= "其他";break;
            case "p8":yo= "上次問題改善";break;
        }
        return yo;
    }

    private void do_2ndCk(){
        DBweb dl=new DBweb();
//第一個欄位不能是null
        String ck;
        ck="select user, date1, section, problem, note, idnote, picture from view2 where store='" + str_set[1] + "' and check2=0";
        dl.getImage(ck);
        Log.e("debug_tag", ck);
        Log.e("debug_log", DBweb.strd.length + ", " + DBweb.strd[0].length);
        String path = Environment.getExternalStorageDirectory().toString();
        try{
            FragSearch.idnote=new String[DBweb.strd.length];
            FragSearch.images=new Uri[DBweb.strd.length];
            FragSearch.user =new String[DBweb.strd.length];
            FragSearch.dates =new String[DBweb.strd.length];
            FragSearch.sect =new String[DBweb.strd.length];
            FragSearch.prob =new String[DBweb.strd.length];
            FragSearch.note =new String[DBweb.strd.length];
        }catch (Exception e){
            Toast.makeText(ActMain.this, "查無照片與備註 (MainActivity)", Toast.LENGTH_SHORT).show();
        }

        for(int i=0;i< DBweb.strd.length;i++){
            try{
                Log.e("debug_tag", String.valueOf(i));
//                    Toast.makeText(Main.this, "下載完成"+String.valueOf(i), Toast.LENGTH_SHORT).show();
                File file = new File(path, i + ".jpg"); // the File to save to String.valueOf(i)
                Uri imgUri = Uri.parse(file.getAbsolutePath());
                FragSearch.images[i]=imgUri;

                FragSearch.user[i]= DBweb.strd[i][0];
                FragSearch.dates[i]= DBweb.strd[i][1].substring(0,10);
                FragSearch.sect[i]=doSmart(DBweb.strd[i][2]);
                FragSearch.prob[i]=doSmart(DBweb.strd[i][3]);
                FragSearch.note[i]= DBweb.strd[i][4];
                FragSearch.idnote[i]= DBweb.strd[i][5];
                byte[] decodedString = Base64.decode(DBweb.strd[i][6], Base64.DEFAULT);
                FileOutputStream out = null;
                Bitmap bp = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                try {
                    out = new FileOutputStream(file);
                    bp.compress(Bitmap.CompressFormat.PNG, 100, out);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (out != null) {
                            out.close();
                        }
                    } catch (IOException e) {e.printStackTrace();}
                }
            }catch (Exception e){
                Log.e("debug_tag", "else "+String.valueOf(i));
                FragSearch.images[0]=Uri.parse("");
                FragSearch.user[i]= DBweb.strd[i][0];
                FragSearch.dates[i]= DBweb.strd[i][1].substring(0,10);
                FragSearch.sect[i]=doSmart(DBweb.strd[i][2]);
                FragSearch.prob[i]=doSmart(DBweb.strd[i][3]);
                FragSearch.note[i]= DBweb.strd[i][4];
                FragSearch.idnote[i]= DBweb.strd[i][5];
            }


        }
        Log.e("debug_log", "good");
    }

    private void do_local(){
        Cursor db_note=dbl.getNote(null, null);
        try{
            FragLocal.idnote=new String[db_note.getCount()];
            FragLocal.images=new Uri[db_note.getCount()];
            FragLocal.user =new String[db_note.getCount()];
            FragLocal.dates =new String[db_note.getCount()];
            FragLocal.sect =new String[db_note.getCount()];
            FragLocal.prob =new String[db_note.getCount()];
            FragLocal.note =new String[db_note.getCount()];
        }catch (Exception e){
            Toast.makeText(ActMain.this, "查無照片與備註 (MainActivity)", Toast.LENGTH_SHORT).show();
        }

        int kk=0;
        while ( db_note.moveToNext()){
            try{
                Log.e("debug_tag", String.valueOf(kk));
                FragLocal.images[kk]=Uri.parse(db_note.getString(6));
                FragLocal.user[kk]= str_set[0];
                FragLocal.dates[kk]= db_note.getString(1).substring(1, db_note.getString(1).length()-1);
                FragLocal.sect[kk]=doSmart(db_note.getString(2).substring(1, db_note.getString(2).length()-1));
                FragLocal.prob[kk]=doSmart(db_note.getString(3).substring(1, db_note.getString(3).length()-1));
                FragLocal.note[kk]= db_note.getString(4).substring(1, db_note.getString(4).length()-1);
            }catch (Exception e){
                Log.e("debug_tag", "else "+String.valueOf(kk));
                FragLocal.images[kk]=Uri.parse("");
                FragLocal.user[kk]= str_set[0];
                FragLocal.dates[kk]= db_note.getString(1).substring(1, db_note.getString(1).length()-1);
                FragLocal.sect[kk]=doSmart(db_note.getString(2).substring(1, db_note.getString(2).length()-1));
                FragLocal.prob[kk]=doSmart(db_note.getString(3).substring(1, db_note.getString(3).length()-1));
                FragLocal.note[kk]= db_note.getString(4).substring(1, db_note.getString(4).length()-1);
            }
            kk++;
        }
        Log.e("debug_log", "good");

    }

    private void do_form(){
        Cursor db_form=dbl.getForm(null, null);
        Log.e("debug_tag", "do_form: "+ String.valueOf(db_form.getCount()));
        try{
            FragForm.idform=new int[db_form.getCount()];
            FragForm.SD=new String[db_form.getCount()];
            FragForm.store =new String[db_form.getCount()];
            FragForm.user =new String[db_form.getCount()];
            FragForm.totscore =new int[db_form.getCount()];

        }catch (Exception e){
            Toast.makeText(ActMain.this, "查無表單 (MainActivity)", Toast.LENGTH_SHORT).show();
        }
        int kk=0;
        while ( db_form.moveToNext()){
            FragForm.idform[kk]=Integer.valueOf(db_form.getString(0));
            FragForm.SD[kk]=db_form.getString(1);
            FragForm.user[kk]=db_form.getString(3);
            FragForm.store[kk]= db_form.getString(2);
            try{
                FragForm.totscore[kk]= Integer.valueOf(db_form.getString(4));

            }catch (Exception e){
                FragForm.totscore[kk]= 0;
            }
            kk++;
        }
        Log.e("debug_log", "do_form while end");
    }
}


//    public void fragA(){
//
//    }

//    public void fragB(){
//        final FragmentTransaction ft = getFragmentManager().beginTransaction();
//        ft.replace(R.id.fragment_addin_linearlayout, new BFrag(), "f_b");
//        ft.commit();
//    }


//===========================================debug strL_offline//===========================================
//        for(String str_a[]:strL_offline){
//            String str="";
//            for(int i=0;i<str_a.length;i++){
//                str+=str_a[i]+", ";
//            }
//            Log.e("debug_tag", str);
//        }



//===========================================do2ndCk photo//===========================================
//                if(DBlink.strd[i][2]!=null){
//                    Log.e("debug_tag", "lulu");
//                    File file = new File(path, i + ".jpg"); // the File to save to   String.valueOf(i)
//                    Uri imgUri = Uri.parse(file.getAbsolutePath());
//                    CheckList.images[i]=imgUri;
//                    CheckList.note[i]=DBlink.strd[i][1];
//                    CheckList.dates[i]=DBlink.strd[i][0].substring(0,10);
//                    byte[] decodedString = Base64.decode(DBlink.strd[i][2], Base64.DEFAULT);
//                    Bitmap bp = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
//                    try {
//                        out = new FileOutputStream(file);//fOut = new FileOutputStream(file);
//                        bp.compress(Bitmap.CompressFormat.PNG, 100, out); // bmp is your Bitmap instance
////                    if(bp!=null){
////                        bp.recycle();
////                    }
//                        // PNG is a lossless format, the compression factor (100) is ignored
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    } finally {
//                        try {
//                            if (out != null) {
//                                out.close();//fOut.close();
//                            }
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }

//                }
//                else{
//                    CheckList.dates[i]=DBlink.strd[i][0].substring(0,10);
//                    CheckList.note[i]=DBlink.strd[i][1];
//                    Log.e("debug_tag","haha");
//                }


//===========================================debug strd//===========================================
//            String str="";
//            for(int i=0;i<DBlink.strd.length;i++){
//                for(int j=0;j<DBlink.strd[0].length;j++){
//                    str+=DBlink.strd[i][j]+", ";
//                }str+="\n";
//            }Log.e("debug_tag", str);


//===========================================bitmap//===========================================
//                    byte[] decodedString = Base64.decode(DBlink.strd[i][5], Base64.DEFAULT);
//                    FileOutputStream out = null;//OutputStream fOut = null;
//                    Bitmap bp = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
//                    try {
//                        out = new FileOutputStream(file);//fOut = new FileOutputStream(file);
//                        bp.compress(Bitmap.CompressFormat.PNG, 100, out); // bmp is your Bitmap instance
////                    if(bp!=null){
////                        bp.recycle();
////                    }
//                        // PNG is a lossless format, the compression factor (100) is ignored
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    } finally {
//                        try {
//                            if (out != null) {
//                                out.close();//fOut.close();
//                            }
//                        } catch (IOException e) {e.printStackTrace();}
//                    }



//===========================================Bad Statment//===========================================
//                if(DBlink.strd[i][5]!=null){
//                }else{
//                }


//===================================onOptionItemSelected Statment//===================================
//        if (id == R.id.action_settings) {
//
//        }



//===================================doLocal old version//===================================
//        try{
//            FragLocal.idnote=new String[strL_offline.size()];
//            FragLocal.images=new Uri[strL_offline.size()];
//            FragLocal.user =new String[strL_offline.size()];
//            FragLocal.dates =new String[strL_offline.size()];
//            FragLocal.sect =new String[strL_offline.size()];
//            FragLocal.prob =new String[strL_offline.size()];
//            FragLocal.note =new String[strL_offline.size()];
//        }catch (Exception e){
//            Toast.makeText(ActMain.this, "查無照片與備註 (MainActivity)", Toast.LENGTH_SHORT).show();
//        }
//        for(int i=0;i< strL_offline.size();i++){
//            String[] str_local;
//            str_local=strL_offline.get(i);
//            try{
//                Log.e("debug_tag", String.valueOf(i));
//                FragLocal.images[i]=Uri.parse(str_local[8]);
//                FragLocal.user[i]= str_set[0];
//                FragLocal.dates[i]= str_local[2].substring(1, str_local[2].length()-1);
//                FragLocal.sect[i]=doSmart(str_local[3].substring(1, str_local[3].length()-1));
//                FragLocal.prob[i]=doSmart(str_local[4].substring(1, str_local[4].length()-1));
//                FragLocal.note[i]= str_local[6].substring(1, str_local[6].length()-1);
//
//            }catch (Exception e){
//                Log.e("debug_tag", "else "+String.valueOf(i));
//                FragLocal.images[i]=Uri.parse("");
//                FragLocal.user[i]= str_set[0];
//                FragLocal.dates[i]= str_local[2].substring(1, str_local[2].length()-1);
//                FragLocal.sect[i]=doSmart(str_local[3].substring(1, str_local[3].length()-1));
//                FragLocal.prob[i]=doSmart(str_local[4].substring(1, str_local[4].length()-1));
//                FragLocal.note[i]= str_local[6].substring(1, str_local[6].length()-1);
//            }
//        }
//        Log.e("debug_log", "good");



//================================debug SQLite get//================================
//            Log.e("debug_tag", "kk: "+String.valueOf(kk));
//            Log.e("debug_tag", "idform: "+db_form.getString(0));
//            Log.e("debug_tag", "SD: "+db_form.getString(1));
//            Log.e("debug_tag", "user: "+db_form.getString(3));
//            Log.e("debug_tag", "store: "+db_form.getString(2));