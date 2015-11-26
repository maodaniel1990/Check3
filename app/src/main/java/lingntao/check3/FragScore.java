package lingntao.check3;

import android.app.FragmentTransaction;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class FragScore extends Fragment {
    Context context;
    TextView tv_score;
    Spinner sp_score1,sp_score2,sp_score3,sp_score4,sp_score5,sp_score6,sp_score7,sp_score8,sp_score9,sp_score10;
    ArrayAdapter<String> adapter;
    private static final String[] score ={"選擇分數","1","2","3","4","5"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragView = inflater.inflate(R.layout.frag_score, container, false);
        tv_score=(TextView) fragView.findViewById(R.id.tv_score);
        Drawable tv_box=FragScore.this.getResources().getDrawable(R.drawable.tv_box);
        Cursor tb_score =ActMain.dbl.getForm2("idform="+ActMain.temp_idform, null);
        tb_score.moveToFirst();
        tv_score.setBackgroundDrawable(tv_box);
        adapter = new ArrayAdapter<>(getActivity(),R.layout.cust_spin2, score);
        adapter.setDropDownViewResource(R.layout.cust_spin2);
        sp_score1=(Spinner) fragView.findViewById(R.id.sp_score1);
        Log.e("debug_tag", tb_score.getString(1));
        sp_score2=(Spinner) fragView.findViewById(R.id.sp_score2);
        sp_score3=(Spinner) fragView.findViewById(R.id.sp_score3);
        sp_score4=(Spinner) fragView.findViewById(R.id.sp_score4);
        sp_score5=(Spinner) fragView.findViewById(R.id.sp_score5);
        sp_score6=(Spinner) fragView.findViewById(R.id.sp_score6);
        sp_score7=(Spinner) fragView.findViewById(R.id.sp_score7);
        sp_score8=(Spinner) fragView.findViewById(R.id.sp_score8);
        sp_score9=(Spinner) fragView.findViewById(R.id.sp_score9);
        sp_score10=(Spinner) fragView.findViewById(R.id.sp_score10);
        tv_score.setText(tb_score.getString(0));
        sp_score1.setAdapter(adapter);
        sp_score2.setAdapter(adapter);
        sp_score3.setAdapter(adapter);
        sp_score4.setAdapter(adapter);
        sp_score5.setAdapter(adapter);
        sp_score6.setAdapter(adapter);
        sp_score7.setAdapter(adapter);
        sp_score8.setAdapter(adapter);
        sp_score9.setAdapter(adapter);
        sp_score10.setAdapter(adapter);
        sp_score1.setSelection(Integer.valueOf(tb_score.getString(1)));
        sp_score1.setSelection(3);
        sp_score2.setSelection(Integer.valueOf(tb_score.getString(2)));
        sp_score3.setSelection(Integer.valueOf(tb_score.getString(3)));
        sp_score4.setSelection(Integer.valueOf(tb_score.getString(4)));
        sp_score5.setSelection(Integer.valueOf(tb_score.getString(5)));
        sp_score6.setSelection(Integer.valueOf(tb_score.getString(6)));
        sp_score7.setSelection(Integer.valueOf(tb_score.getString(7)));
        sp_score8.setSelection(Integer.valueOf(tb_score.getString(8)));
        sp_score9.setSelection(Integer.valueOf(tb_score.getString(9)));
        sp_score10.setSelection(Integer.valueOf(tb_score.getString(10)));
        tv_score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tot=0;
                try{
                    tot=Integer.valueOf(sp_score1.getSelectedItem().toString())
                            +Integer.valueOf(sp_score2.getSelectedItem().toString())
                            +Integer.valueOf(sp_score3.getSelectedItem().toString())
                            +Integer.valueOf(sp_score4.getSelectedItem().toString())
                            +Integer.valueOf(sp_score5.getSelectedItem().toString())
                            +Integer.valueOf(sp_score6.getSelectedItem().toString())
                            +Integer.valueOf(sp_score7.getSelectedItem().toString())
                            +Integer.valueOf(sp_score8.getSelectedItem().toString())
                            +Integer.valueOf(sp_score9.getSelectedItem().toString())
                            +Integer.valueOf(sp_score10.getSelectedItem().toString());
                    tv_score.setText(String.valueOf(tot));
                    ActMain.dbl.editForm(Integer.valueOf(tv_score.getText().toString())
                            , Integer.valueOf(sp_score1.getSelectedItem().toString())
                            , Integer.valueOf(sp_score2.getSelectedItem().toString())
                            , Integer.valueOf(sp_score3.getSelectedItem().toString())
                            , Integer.valueOf(sp_score4.getSelectedItem().toString())
                            , Integer.valueOf(sp_score5.getSelectedItem().toString())
                            , Integer.valueOf(sp_score6.getSelectedItem().toString())
                            , Integer.valueOf(sp_score7.getSelectedItem().toString())
                            , Integer.valueOf(sp_score8.getSelectedItem().toString())
                            , Integer.valueOf(sp_score9.getSelectedItem().toString())
                            , Integer.valueOf(sp_score10.getSelectedItem().toString()), "idform = " + ActMain.temp_idform);
                    Toast.makeText(context, "更新成功", Toast.LENGTH_SHORT).show();
                }catch (Exception e){

                }
            }
        });
        return fragView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    @Override
    public void onPause() {
        super.onPause();
    }
}
