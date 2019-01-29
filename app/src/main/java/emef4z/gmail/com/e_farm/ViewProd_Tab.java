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
 * Created by USER on 25-Mar-17.
 */

public class ViewProd_Tab extends Fragment {

    E_farmDB e_farmDB;
    ListView myProdLV;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
     View view = inflater.inflate(R.layout.view_my_prods_tab,container,false);


        e_farmDB = new E_farmDB(getActivity());
        myProdLV = (ListView)view.findViewById(R.id.myProds_lv);
String usern = Login.USERNAME_TRACKER;

        try {

            Cursor cursor = e_farmDB.a_ProdQuery(usern);

            String[] columns = {"_id","product_name","product_type","product_quantity","status","product_price","product_description"};

            int[] txtIDs = {R.id.prod_id_tv,R.id.product_name_tv,R.id.prod_type_tv,R.id.prod_qty_tv,
                    R.id.prod_stat_tv,R.id.prod_price_tv,R.id.prod_details_tv};


            SimpleCursorAdapter myAdapter = new SimpleCursorAdapter(getActivity(),R.layout.display_my_prod_row,
                    cursor, columns, txtIDs);



            myProdLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //  String show = prodLV.getItemAtPosition(position).toString();
                    Toast.makeText(getActivity()," Clicked" +position,Toast.LENGTH_SHORT).show();

                }

            });



            myProdLV.setAdapter(myAdapter);




        } catch(Exception ex)
        {
            Toast.makeText(getActivity(),"Error!!" + ex.getMessage(),Toast.LENGTH_LONG).show();
        }




        return view;
    }





}
