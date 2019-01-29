package emef4z.gmail.com.e_farm;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by USER on 12-Apr-17.
 */

public class AdminViewAdmin_Tab extends Fragment {

    E_farmDB e_farmDB;
    ListView admin_list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.admin_view_admin_tab,container,false);

        e_farmDB = new E_farmDB(getActivity());
        admin_list = (ListView)view.findViewById(R.id.admin_view_admin_list);


        try {

            Cursor cursor = e_farmDB.allAdminInfoQuery();

            String[] columns = {"_id","first_name","last_name","location","contact_no","email","username","role"};
            int[] txtIDs = {R.id.ID_view,R.id.firstN_view,R.id.lastN_view,R.id.location_view,R.id.cont_no_view,R.id.maxS_et_sto,
                    R.id.usern_view,R.id.tp_tv_sto};


            SimpleCursorAdapter myAdapter = new SimpleCursorAdapter(getActivity(),R.layout.admin_view_farmer_admin_row,
                    cursor, columns, txtIDs);



            admin_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //  String show = prodLV.getItemAtPosition(position).toString();
                    Toast.makeText(getActivity()," Clicked" +position,Toast.LENGTH_SHORT).show();

                }

            });



            admin_list.setAdapter(myAdapter);




        } catch(Exception ex)
        {
            Toast.makeText(getActivity(),"Error!!" + ex.getMessage(),Toast.LENGTH_LONG).show();
        }


        return view;
    }
}
