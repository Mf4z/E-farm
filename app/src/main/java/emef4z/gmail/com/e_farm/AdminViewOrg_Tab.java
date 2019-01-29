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

public class AdminViewOrg_Tab extends Fragment {

    E_farmDB e_farmDB;
    ListView org_list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.admin_view_org_tab,container,false);

        e_farmDB = new E_farmDB(getActivity());
        org_list = (ListView)view.findViewById(R.id.admin_view_org_list);

        try {

            Cursor cursor = e_farmDB.allOrgInfoQuery();

            String[] columns = {"_id","organisation_id","organisation_name","operational_id","year_of_est","location",
                    "contact_no","email","username","role"};
            int[] txtIDs = {R.id.id_view,R.id.orgID_tv_sto,R.id.orgname_tv_sto,R.id.perPrice_et_sto,R.id.nonPerPrice_et_sto,R.id.maxW_et_sto,R.id.minW_et_sto,
                    R.id.maxS_et_sto,R.id.minS_et_sto,R.id.tp_tv_sto};


            SimpleCursorAdapter myAdapter = new SimpleCursorAdapter(getActivity(),R.layout.admin_view_org_row,
                    cursor, columns, txtIDs);



            org_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //  String show = prodLV.getItemAtPosition(position).toString();
                    Toast.makeText(getActivity()," Clicked" +position,Toast.LENGTH_SHORT).show();

                }

            });

            org_list.setAdapter(myAdapter);


        } catch(Exception ex)
        {
            Toast.makeText(getActivity(),"Error!!" + ex.getMessage(),Toast.LENGTH_LONG).show();
        }

        return view;


    }
}
