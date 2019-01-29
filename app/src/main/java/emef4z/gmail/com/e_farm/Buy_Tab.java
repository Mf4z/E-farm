package emef4z.gmail.com.e_farm;


import android.app.ProgressDialog;
import android.content.Intent;
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
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.List;

import static android.R.attr.id;

/**
 * Created by USER on 25-Mar-17.
 */

public class Buy_Tab extends Fragment {
    E_farmDB e_farmDB;
    ListView prodLV;
    long the_id;
    int the_pos;
    String phone_ip = "10.0.2.2";




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.buy_prod_tab,container,false);



        e_farmDB = new E_farmDB(getActivity());
      prodLV = (ListView)view.findViewById(R.id.viewAll_product_lv);

        final ProgressDialog pd;
        pd = new ProgressDialog(getActivity());
        pd.setMessage("Loading...");
        pd.setCancelable(false);
        pd.show();


        try {

            e_farmDB.DeleteProductData();

            final String url =
                    Uri.parse("http://"+phone_ip+"/E_farm_Proj/App_Connection/product_data_json.php").buildUpon().build().toString();
            Ion.with(getActivity())
                    .load(url)
                    .progressDialog(pd)
                    .as(new TypeToken<List<Product_json>>() {
                    })
                    .setCallback(new FutureCallback<List<Product_json>>() {
                        @Override
                        public void onCompleted(Exception e, List<Product_json> result) {

                            if (result != null) {
                                try {
                                    e_farmDB = new E_farmDB(getActivity());

                                    for (Product_json r : result) {

                                        e_farmDB.uploadProductMethod(r.username, r.product_photo, r.product_name,
                                                r.product_type, r.product_quantity, r.product_price, r.product_description);
                                    }





                                    String[] columns = {"_id", "product_name", "product_type", "product_quantity", "status", "product_price",
                                            "username", "product_description"};

                                    int[] txtIDs = {R.id.prod_id_tv, R.id.product_name_tv, R.id.prod_type_tv, R.id.prod_qty_tv,
                                            R.id.prod_stat_tv, R.id.prod_price_tv, R.id.produsern_tv, R.id.prod_details_tv};

                                   final Cursor cursor = e_farmDB.allProdQuery();


                                    SimpleCursorAdapter myAdapter = new SimpleCursorAdapter(getActivity(), R.layout.dispaly_products_row,
                                            cursor, columns, txtIDs);


                                    prodLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                            //String usern = cursor.(8);

                                            Cursor cursorHelper = (Cursor) prodLV.getItemAtPosition(position);
                                            String str =cursorHelper.getString(1);

                                            Toast.makeText(getActivity(), " Clicked : " + position+" Item : "+str, Toast.LENGTH_SHORT).show();
                                            the_pos = position;
                                            the_id = id;


                                            Intent myIntent = new Intent(getActivity(),Contact_seller.class);
                                            //myIntent.putExtra("username",Login.USERNAME_TRACKER);
                                            startActivity(myIntent);



                                        }

                                    });


                                    prodLV.setAdapter(myAdapter);


                                } catch (Exception ex) {
                                    Toast.makeText(getActivity(), "Error!!" + ex.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            }
                            pd.dismiss();

                        }
                    });

        }
        catch (Exception ex) {
            Toast.makeText(getActivity(), "Error!!" + ex.getMessage(), Toast.LENGTH_LONG).show();
        }
        return view;
    }


}
