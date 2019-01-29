package emef4z.gmail.com.e_farm;

import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

/**
 * Created by USER on 25-Mar-17.
 */

public class UploadProd_Tab extends Fragment {


    E_farmDB e_farmDB;
    ImageView photo;
    EditText prod_Name,prod_Quantity,prod_Price,prod_desc;
    Spinner prd_typ;
    Button submit,cancel;
    String phone_ip = "10.0.2.2";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.upload_prod_tab,container,false);


        e_farmDB = new E_farmDB(getActivity());

        photo = (ImageView)view.findViewById(R.id.prod_photo_IV);
        prod_Name= (EditText)view.findViewById(R.id.product_Name_et);
        prod_Quantity = (EditText)view.findViewById(R.id.product_Qty_et);
        prd_typ = (Spinner)view.findViewById(R.id.product_Type_sp);
        prod_Price = (EditText)view.findViewById(R.id.prodPrice_et);
        prod_desc = (EditText)view.findViewById(R.id.prodDecription_et);
        submit = (Button)view.findViewById(R.id.submit_btn_prod);
        cancel = (Button)view.findViewById(R.id.cancel_btn_prod);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ProgressDialog pd;
                pd = new ProgressDialog(getActivity());
                pd.setMessage("Sending Data...");
                pd.setCancelable(false);
                pd.show();
                final String url;

                String usern,p_photo,pname,pQty,pTyp,pPrice,pDesc;

                usern = Login.USERNAME_TRACKER;

                p_photo = photo.getDrawable().toString();
                pname = prod_Name.getText().toString();
                pQty = prod_Quantity.getText().toString();
                pTyp = prd_typ.getSelectedItem().toString();
                pPrice = prod_Price.getText().toString();
                pDesc = prod_desc.getText().toString();

                long id = e_farmDB.uploadProductMethod(usern,p_photo,pname,pTyp,pQty,pPrice,pDesc);


                if (id < 0)
                {
                    Message.message(getActivity(), "Unsuccessful");
                }

                else
                {

                    Message.message(getActivity(), "Successfuly inserted a row");

                String stat = "Available";


                    url = Uri.parse("http://"+phone_ip+"/E_farm_Proj/App_Connection/productReg_insertion.php").buildUpon()
                            .appendQueryParameter("usrn",usern)
                            .appendQueryParameter("prod_photo",p_photo)
                            .appendQueryParameter("prod_n",pname)
                            .appendQueryParameter("prod_qty",pQty)
                            .appendQueryParameter("prod_type",pTyp)
                            .appendQueryParameter("prod_price",pPrice)
                            .appendQueryParameter("prod_desc",pDesc)
                            .appendQueryParameter("prod_stat",stat)
                            .build().toString();

                    Ion.with(getActivity())
                            .load(url)
                            .progressDialog(pd)
                            .asString()
                            .setCallback(new FutureCallback<String>() {
                                @Override
                                public void onCompleted(Exception e, String result) {

                                    try
                                    {
                                        Toast.makeText(getActivity(),result,Toast.LENGTH_SHORT).show();
                                    }
                                    catch (Exception ex)
                                    {
                                        Toast.makeText(getActivity(),"Error! " + ex.getMessage() + "No connectivity",Toast.LENGTH_SHORT).show();
                                    }
                                    pd.dismiss();
                                }
                            });




                    prod_Name.setText("");
                    prod_Quantity.setText("");
                    prod_Price.setText("");
                    prod_desc.setText("");

                }
            }
        });


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                prod_Name.setText("");
                prod_Quantity.setText("");
                prod_Price.setText("");
                prod_desc.setText("");
            }
        });

        
        return view;
    }
}
