package lingntao.check3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DBlocal extends SQLiteOpenHelper{
    protected final static String tb_user ="user";
    protected final static String id_user ="iduser";
    protected final static String col_pass ="password";

    protected final static String tb_form ="form3";
    protected final static String id_form ="idform";
    protected final static String col_SD ="SD";
    protected final static String col_ED ="ED";
    protected final static String col_user ="user";
    protected final static String col_store ="store";
    protected final static String col_score2 ="totscore";
    protected final static String col_s1 ="s1";
    protected final static String col_s2 ="s2";
    protected final static String col_s3 ="s3";
    protected final static String col_s4 ="s4";
    protected final static String col_s5 ="s5";
    protected final static String col_s6 ="s6";
    protected final static String col_s7 ="s7";
    protected final static String col_s8 ="s8";
    protected final static String col_s9 ="s9";
    protected final static String col_s10 ="s10";


    protected final static String tb_unit ="unit";
    protected final static String id_unit ="idunit";
    protected final static String id_form2 ="idform";
    protected final static String col_officer ="officer";
    protected final static String col_store2 ="store";
    protected final static String col_section ="section";
    protected final static String col_q1 ="q1";
    protected final static String col_q2 ="q2";
    protected final static String col_q3 ="q3";
    protected final static String col_q4 ="q4";
    protected final static String col_q5 ="q5";
    protected final static String col_q6 ="q6";
    protected final static String col_q7 ="q7";
    protected final static String col_q8 ="q8";
    protected final static String col_score ="score";

    protected final static String tb_note ="note2";
    protected final static String id_note ="idnote";
    protected final static String col_problem ="problem";
    protected final static String col_section2 ="section";
    protected final static String col_date1 ="date1";
    protected final static String col_check1 ="check1";
    protected final static String col_date2 ="date2";
    protected final static String col_check2 ="check2";
    protected final static String col_note ="note";
    protected final static String col_picture ="picture";
    protected final static String col_picture2 ="picture2";
    private Context mycontext;
    public SQLiteDatabase myDataBase;
    private static String DB_NAME = "note";
    private static String DB_PATH = "";

    public DBlocal(Context context, String name, CursorFactory factory, int version){
        super(context, name, factory, version);

    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql2="create table "+ tb_form +"("
                + id_form +" integer primary key autoincrement,"
                + col_SD +" text,"
                + col_ED +" text,"
                + col_user +" text,"
                + col_store +" text,"
                + col_score2 +" integer,"
                + col_s1 +" integer,"
                + col_s2 +" integer,"
                + col_s3 +" integer,"
                + col_s4 +" integer,"
                + col_s5 +" integer,"
                + col_s6 +" integer,"
                + col_s7 +" integer,"
                + col_s8 +" integer,"
                + col_s9 +" integer,"
                + col_s10 +" integer)";
        Log.e("debug_tag", sql2);

        String sql3="create table "+ tb_unit +"("
                + id_unit +" integer primary key,"
                + id_form2 +" integer,"
                + col_officer +" text,"
                + col_store2 +" text,"
                + col_section +" text,"
                + col_q1 +" integer,"
                + col_q2 +" integer,"
                + col_q3 +" integer,"
                + col_q4 +" integer,"
                + col_q5 +" integer,"
                + col_q6 +" integer,"
                + col_q7 +" integer,"
                + col_q8 +" integer,"
                + col_score +" integer)";

        String sql4="create table "+ tb_note +"("
                + id_note +" integer primary key autoincrement,"
                + col_problem +" text,"
                + col_section2 +" text,"
                + col_date1 +" text,"
                + col_check1 +" text,"
                + col_date2 +" text,"
                + col_check2 +" text,"
                + col_note +" text,"
                + col_picture +" text,"
                + col_picture2 +" text)";


        db.execSQL(sql2);
        db.execSQL(sql3);
        db.execSQL(sql4);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("drop table if exists " + tb_user);
//        db.execSQL("drop table if exists "+ tb_unit);
        db.execSQL("drop table if exists "+ tb_note);
        onCreate(db);
    }
//================================================Insert//================================================
    long addForm(String SD, String user, String store) {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values =new ContentValues();
        values.put(col_SD, SD);
        values.put(col_user, user);
        values.put(col_store, store);
        long result=db.insert(tb_form, null, values);
        db.close();
        Log.e("debug_tag", "addForm result: " + Long.toString(result));
        return result;
    }

    long addUnit(int idunit, int idform, String officer, String store, String section, int score) {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values =new ContentValues();
        values.put(id_unit, idunit);
        values.put(id_form2, idform);
        values.put(col_officer, officer);
        values.put(col_store2, store);
        values.put(col_section, section);
        values.put(col_score, score);
        long result=db.insert(tb_unit, null, values);
        db.close();
        return result;
    }

    long addNote(String problem, String section, String date1, String check1
            , String check2, String note, String picture) {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values =new ContentValues();
        values.put(col_problem,  problem);
        values.put(col_section2, section);
        values.put(col_date1, date1);
        values.put(col_check1, check1);
        values.put(col_check2, check2);
        values.put(col_note, note);
        values.put(col_picture, picture);
        long result=db.insert(tb_note, null, values);
        db.close();
        return result;
    }
    //================================================Update//================================================
    long editForm(int score, int s1, int s2, int s3, int s4, int s5
            , int s6, int s7, int s8, int s9, int s10, String whereClause) {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values =new ContentValues();
        values.put(col_score2,  score);
        values.put(col_s1, s1);
        values.put(col_s2, s2);
        values.put(col_s3, s3);
        values.put(col_s4, s4);
        values.put(col_s5, s5);
        values.put(col_s6, s6);
        values.put(col_s7, s7);
        values.put(col_s8, s8);
        values.put(col_s9, s9);
        values.put(col_s10, s10);
        long result=db.update(tb_form, values, whereClause, null);
        db.close();
        return result;
    }

    long editUnit(int score, String whereClause) {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values =new ContentValues();
        if(score!=0)  values.put(col_score, score);
        long result=db.update(tb_unit, values, whereClause, null);
        db.close();
        return result;
    }

    long editNote(String date2, int check2, String note, String whereClause) {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values =new ContentValues();
        values.put(col_date2, date2);
        if(check2!=1)  values.put(col_check2, check2);
        if(note!=null)  values.put(col_note, note);
        long result=db.update(tb_note, values, whereClause, null);
        db.close();
        return result;
    }


    //================================================Select//================================================
    Cursor getForm(String whereClause, String orderBy){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor c=db.query(tb_form
                , new String[]{id_form, col_SD, col_store, col_user, col_score2}
                , whereClause
                , null
                , null, null, orderBy);
        return c;
    }

    Cursor getForm2(String whereClause, String orderBy){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor c=db.query(tb_form
                , new String[]{col_score2, col_s1, col_s2, col_s3, col_s4, col_s5, col_s6, col_s7, col_s8, col_s9, col_s10}
                , whereClause
                , null
                , null, null, orderBy);
        return c;
    }

//    // 取得一筆紀錄
//    Cursor get(long SD) throws SQLException {
//        Cursor cursor = db.query(
//                tb_form,                //資料表名稱
//                new String[]{id_form, col_SD},    //欄位名稱
//                col_SD+ "=" + SD,                //WHERE
//                null, // WHERE 的參數
//                null, // GROUP BY
//                null, // HAVING
//                null, // ORDOR BY
//                null  // 限制回傳的rows數量
//        );
//
//        // 注意：不寫會出錯
//        if (cursor != null) {
//            cursor.moveToFirst();	//將指標移到第一筆資料
//        }
//        return cursor;
//    }

    Cursor getUnit(String whereClause, String orderBy){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor c=db.query(tb_unit,
                new String[]{col_score},
                whereClause, null, null, null, orderBy);
        return c;
    }

    Cursor getNote(String whereClause, String orderBy){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor c=db.query(tb_note
                , new String[]{id_note, col_date1, col_section2
                                    , col_problem, col_note, col_check1, col_picture}
                , whereClause, null, null, null, orderBy);
        return c;
    }

    //================================================Delete//================================================
    int deleteOrder(){
        SQLiteDatabase db=this.getWritableDatabase();
        int result=db.delete(tb_unit, null, null);
        result=result+db.delete(tb_note,null, null);
        db.close();
        return result;
    }

    int deleteOrderProduct(){
        SQLiteDatabase db=this.getWritableDatabase();
        int result=db.delete(tb_note, null, null);
        db.close();
        return result;
    }

    //================================================Special//================================================
    public void onUpgradeOrder(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+ tb_unit);
        onCreate(db);
    }
    public void dropNote(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+ tb_note);
        db.execSQL("drop table if exists "+ tb_unit);
        onCreate(db);
    }

    int Rdelete(String _id){
        SQLiteDatabase db=this.getWritableDatabase();
        int result=db.delete(tb_form, id_form + " =" + _id, null);
        db.close();
        return result;
    }

    int delete(String _id){
        SQLiteDatabase db=this.getWritableDatabase();
        int result=db.delete(tb_user, id_user + " =" + _id, null);
        db.close();
        return result;
    }
}

//    DBAccess access;
//    access=new DBAccess(this, "logintable", null, 1);
//    Cursor c=access.getData(DBAccess.id_user+" ="+1, null);
//    c.moveToFirst();
//    try
//    {
//        System.out.println(c.getString(0));
//        mEmailView.setText(c.getString(1));
//        mPasswordView.setText(c.getString(2));
//    }
//    catch(Exception e)
//    {
//        System.out.println("no");
//        long result=access.add("example@example.com","123");
//        mEmailView.setText("example@example.com");
//        mPasswordView.setText("123");
//    }
//
//    str=new String [getOrderID.getCount()][3];
//    String str2[] = new String [getOrderID.getCount()];
//    int kk=0;
//    while ( getOrderID.moveToNext())
//    {
//        str[kk][0]=getOrderID.getString(0);
//        str[kk][1]=getOrderID.getString(1);
//        str[kk][2]=getOrderID.getString(2);
//        str2[kk]=getOrderID.getString(0)+","+getOrderID.getString(1)+","+getOrderID.getString(2);
//        kk++;
//    }
//    kk=0;


//============================================User Table//============================================
//        String sql="create table "+ tb_user +"("
//                + id_user +" integer primary key,"
//                + col_pass +" text,"
//                + col_ED +" text)";

//        db.execSQL(sql);

//    long add(String Email, String Password) {
//        SQLiteDatabase db=this.getWritableDatabase();
//        ContentValues values =new ContentValues();
//        values.put(col_pass, Email);
//        values.put(col_ED, Password);
//        long result=db.insert(tb_user, null, values);
//        db.close();
//        return result;
//    }


//    long update(String Email, String Password, String whereClause) {
//        SQLiteDatabase db=this.getWritableDatabase();
//        ContentValues values =new ContentValues();
//        if(Email!=null)  values.put(col_pass, Email);
//        if(Password!=null)  values.put(col_ED, Password);
//        long result=db.update(tb_user, values, whereClause, null);
//        db.close();
//        return result;
//    }

//    Cursor getData(String whereClause, String orderBy){
//        SQLiteDatabase db=this.getReadableDatabase();
//        Cursor c=db.query(tb_user,
//                new String[]{id_user, col_pass, col_ED},
//                whereClause, null, null, null, orderBy);
//        return c;
//    }

//=====================================================copy database//=====================================================
//        if(android.os.Build.VERSION.SDK_INT >= 17){
//            DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
//        }
//        else{
//            DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
//        }
//        this.mycontext=context;
//        boolean dbexist = checkdatabase();
//        if (dbexist) {
//            System.out.println("Database exists");
//            opendatabase();
//            try{
//                copydatabase();
//            }catch (Exception e){Log.e("debug_tag", "no nono");}
//
//        } else {
//            System.out.println("Database doesn't exist");
//            try{
//                createdatabase();
//            }catch (IOException e){
//                Log.e("debug_tag", "no copy");
//            }
//
//        }

//    private boolean checkdatabase() {
//        boolean checkdb = false;
//        try {
//            String myPath = DB_PATH + DB_NAME;
//            File dbfile = new File(myPath);
//            checkdb = dbfile.exists();
//            Log.e("debug_tag", "exist");
//        } catch(SQLiteException e) {
//            System.out.println("Database doesn't exist");
//            Log.e("debug_tag", "Database doesn't exist");
//        }
//        return checkdb;
//    }
//
//    public void opendatabase() throws SQLException {
//        //Open the database
//        String mypath = DB_PATH + DB_NAME;
//        myDataBase = SQLiteDatabase.openDatabase(mypath, null, SQLiteDatabase.OPEN_READWRITE);
//        Log.e("debug_tag", "open");
//    }
//
//    public void createdatabase() throws IOException {
//        boolean dbexist = checkdatabase();
//        if(dbexist) {
//            System.out.println(" Database exists.");
//            Log.e("debug_tag", " Database exists.");
//        } else {
//            this.getReadableDatabase();
//            try {
//                copydatabase();
//            } catch(IOException e) {
//                throw new Error("Error copying database");
//            }
//        }
//    }
//
//    private void copydatabase() throws IOException {
//        //Open your local db as the input stream
//        InputStream myinput = mycontext.getResources().getAssets().open("note");
//        Log.e("debug_tag", Environment.getExternalStorageDirectory().getAbsolutePath()+"/note.db");
//        // Path to the just created empty db
//        String outfilename = Environment.getExternalStorageDirectory().getAbsolutePath()+"/note.db";
//        //Open the empty db as the output stream
//        OutputStream myoutput = new FileOutputStream(outfilename);
//        // transfer byte to inputfile to outputfile
//        byte[] buffer = new byte[1024];
//        int length;
//        while ((length = myinput.read(buffer))>0) {
//            myoutput.write(buffer,0,length);
//        }
//        Log.e("debug_tag", "haha");
//        //Close the streams
//        myoutput.flush();
//        myoutput.close();
//        myinput.close();
//    }