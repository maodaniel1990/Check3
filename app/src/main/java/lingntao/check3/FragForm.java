package lingntao.check3;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class FragForm extends Fragment {
    private static Context context;
    public static int[] idform;
    public static int[] totscore;
    public static String[] SD;
    public static String[] user;
    public static String[] store;
    ListView listView;
    List<CustItem> rowItems;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_form, container, false);
        listView = (ListView) view.findViewById(R.id.list);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        rowItems = new ArrayList<>();
        try{
            for (int i = 0; i < SD.length; i++) {
                CustItem item;
                item = new CustItem(idform[i], SD[i], user[i], store[i], totscore[i]);
                rowItems.add(item);
            }

            CustList2 adapter = new CustList2(context, rowItems);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    TextView list_idform = (TextView) view.findViewById(R.id.list_idform);
                    ActMain.temp_idform=Integer.valueOf(list_idform.getText().toString());
                    final FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.replace(R.id.fragment_addin_linearlayout, new FragScore(), "f_s");
                    ft.commit();
                }
            });

        }catch(Exception e){
            Toast.makeText(getActivity(), "查無照片與備註 (FragForm)", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
    }

}



//===================================List onItemClcik//===================================
//                    Toast toast = Toast.makeText(context,
//                            "Item " + (position + 1) + ": " + rowItems.get(position),
//                            Toast.LENGTH_SHORT);
//                    toast.show();