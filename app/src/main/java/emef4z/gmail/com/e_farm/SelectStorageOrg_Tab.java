package emef4z.gmail.com.e_farm;

import android.content.Intent;
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
 * Created by USER on 25-Mar-17.
 */

public class SelectStorageOrg_Tab extends Fragment {

    E_farmDB e_farmDB;
    ListView ViewOrg_list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.select_sto_org_tab,container,false);


        e_farmDB = new E_farmDB(getActivity());
        ViewOrg_list = (ListView)view.findViewById(R.id.selOrg_lv);

        try {


            Cursor cursor = e_farmDB.selectOrgMethod();

            String[] columns = {"_id","organisation_id","organisation_name","price_per_kg_perishable","price_per_kg_nonperishable","min_weight_accepted",
                    "max_weight_accepted","min_space_available","max_space_available","location","tp_price",
                    "contact_no","email","username"};




            int[] txtIDs = {R.id.id_view_sto,R.id.orgID_tv_sto,R.id.orgname_tv_sto,R.id.perPrice_et_sto,R.id.nonPerPrice_et_sto,
                    R.id.minW_et_sto,R.id.maxW_et_sto,R.id.maxS_et_sto,R.id.minS_et_sto,R.id.tp_tv_sto,
                    R.id.orgLoc_tv_sto,R.id.orgCont_tv_sto,R.id.orgEmail_tv_sto,R.id.orgusern_tv_sto};


            SimpleCursorAdapter myAdapter = new SimpleCursorAdapter(getActivity(),R.layout.select_storage_row,
                    cursor, columns, txtIDs);





            ViewOrg_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //  String show = prodLV.getItemAtPosition(position).toString();
                    Toast.makeText(getActivity()," Clicked" +position,Toast.LENGTH_SHORT).show();
                    Intent myIntent = new Intent(getActivity(),Storage_form.class);
                    startActivity(myIntent);

                }

            });

            ViewOrg_list.setAdapter(myAdapter);


        } catch(Exception ex)
        {
            Toast.makeText(getActivity(),"Error!!" + ex.getMessage(),Toast.LENGTH_LONG).show();
        }


        return view;
    }
}
