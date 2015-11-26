package lingntao.check3;

import android.app.DatePickerDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class FragStore extends Fragment implements View.OnClickListener{
    Context context;
    TextView tv_date;
    Button bt_next;
    Spinner sp_store;
    ArrayAdapter<String> adapter;
    private static final String[] store ={"農安"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragView = inflater.inflate(R.layout.fraga_store, container, false);
        tv_date=(TextView) fragView.findViewById(R.id.tv_date);
        Date dNow = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        tv_date.setText(df.format(dNow));
        tv_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                showDatePicker();
            }
        });
        adapter = new ArrayAdapter<>(getActivity(),R.layout.cust_spin, store);
        sp_store=(Spinner) fragView.findViewById(R.id.sp_store);
        adapter.setDropDownViewResource(R.layout.cust_spin);
//將adapter 添加到spinner中
        sp_store.setAdapter(adapter);
        sp_store.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView adapterView, View view, int position, long id) {
                if (adapterView.getSelectedItemPosition() != 0) {
                    ActMain.str_set[1]=adapterView.getSelectedItem().toString();
                }
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        bt_next=(Button) fragView.findViewById(R.id.bt_next);
        bt_next.setOnClickListener(this);
        return fragView;
    }

    private void showDatePicker() {
        FragDatePicker date = new FragDatePicker();
        /**
         * Set Up Current Date Into dialog
         */
        Calendar calender = Calendar.getInstance();
        Bundle args = new Bundle();
        args.putInt("year", calender.get(Calendar.YEAR));
        args.putInt("month", calender.get(Calendar.MONTH));
        args.putInt("day", calender.get(Calendar.DAY_OF_MONTH));
        date.setArguments(args);
        /**
         * Set Call back to capture selected date
         */
        date.setCallBack(ondate);
        date.show(getFragmentManager(), "Date Picker");
    }

    DatePickerDialog.OnDateSetListener ondate = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            tv_date.setText(String.valueOf(year) + "/" + String.valueOf(monthOfYear+1)
                    + "/" + String.valueOf(dayOfMonth));
        }
    };

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_next:
                ActMain.str_set[2]=tv_date.getText().toString();

                Cursor fragStore_tb_form=ActMain.dbl.getForm("SD = '"+ActMain.str_set[2]+"'", null);
                Log.e("debug_tag", "fragStore_tb_form: "+String.valueOf(fragStore_tb_form.getCount()));
                if(fragStore_tb_form.getCount()==0){
                    ActMain.dbl.addForm(ActMain.str_set[2], ActMain.str_set[0], ActMain.str_set[1]);
                }

                final FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_addin_linearlayout, new FragWork(), "f_b");
                ft.commit();
//                ((Main)getActivity()).fragB();
                Log.e("debug_tag", "FragStore onClick end");
        }
    }
}
