package emef4z.gmail.com.e_farm;

import android.app.ProgressDialog;
import android.database.Cursor;
import android.net.Uri;
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

import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.List;

/**
 * Created by USER on 12-Apr-17.
 */

public class AdminViewFarmer_Tab extends Fragment {

    E_farmDB e_farmDB;
    ListView farmer_list;
    String phone_ip = "10.0.2.2";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.admin_view_farmer_tab,container,false);

        e_farmDB = new E_farmDB(getActivity());
        farmer_list = (ListView)view.findViewById(R.id.admin_view_farmer_list);



     /*   final ProgressDialog pd;
        pd = new ProgressDialog(getActivity());
        pd.setMessage("Loading...");
        pd.setCancelable(false);
        pd.show();*/


        try {

            //e_farmDB.DeleteProductData();

           /* final String url =
                    Uri.parse("http://"+phone_ip+"/E_farm_Proj/App_Connection/farmer_data_json.php").buildUpon().build().toString();
            Ion.with(getActivity())
                    .load(url)
                    .progressDialog(pd)
                    .as(new TypeToken<List<Farmer_json>>() {
                    })
                    .setCallback(new FutureCallback<List<Farmer_json>>() {
                        @Override
                        public void onCompleted(Exception e, List<Farmer_json> result) {

                            if (result != null) {
                                try {
                                    e_farmDB = new E_farmDB(getActivity());

                                    for (Farmer_json r : result) {

                                        e_farmDB.registerFarmerMethod(r.profile_photo, r.first_name, r.last_name,
                                                r.location, r.contact_no, r.email, r.username,r.password);
                                    }*/

            Cursor cursor = e_farmDB.allFarmerInfoQuery();

            String[] columns = {"_id","first_name","last_name","location","contact_no","email","username","role"};
            int[] txtIDs = {R.id.ID_view,R.id.firstN_view,R.id.lastN_view,R.id.location_view,R.id.cont_no_view,R.id.maxS_et_sto,
                    R.id.usern_view,R.id.tp_tv_sto};


            SimpleCursorAdapter myAdapter = new SimpleCursorAdapter(getActivity(),R.layout.admin_view_farmer_admin_row,
                    cursor, columns, txtIDs);



            farmer_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //  String show = prodLV.getItemAtPosition(position).toString();
                    Toast.makeText(getActivity()," Clicked" +position,Toast.LENGTH_SHORT).show();

                }

            });



            farmer_list.setAdapter(myAdapter);




        } catch(Exception ex)
        {
            Toast.makeText(getActivity(),"Error!!" + ex.getMessage(),Toast.LENGTH_LONG).show();
        }/*
                            }
                            pd.dismiss();

                        }
                    });

        }

        catch (Exception ex) {
            Toast.makeText(getActivity(), "Error!!" + ex.getMessage(), Toast.LENGTH_LONG).show();
        }
*/


        return view;
    }
}
