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
import android.widget.Toast;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

/**
 * Created by USER on 26-Mar-17.
 */

public class AdminRegisterDiseaseInfo_Tab extends Fragment {
    E_farmDB e_farmDB;
    EditText crop_name,desId,desName,desCure,desPrev;
    Button submit,cancel;
    String phone_ip = "10.0.2.2";



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.admin_register_disease_tab,container,false);
       e_farmDB =  new E_farmDB(getActivity());

        crop_name = (EditText)view.findViewById(R.id.crop_name_et);
        desId = (EditText)view.findViewById(R.id.pk_diseaseID_et);
        desName = (EditText)view.findViewById(R.id.des_name_et);
        desCure = (EditText)view.findViewById(R.id.disease_cure_et);
        desPrev = (EditText)view.findViewById(R.id.disease_prev_et);
        submit = (Button)view.findViewById(R.id.submit_disease_btn);
        cancel = (Button)view.findViewById(R.id.cancel_btn_disease);


      submit.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {

              final ProgressDialog pd;
              pd = new ProgressDialog(getActivity());
              pd.setMessage("Sending Data...");
              pd.setCancelable(false);
              pd.show();
              final String url;

              String usern,crp_nm,d_ID,d_name,d_cure,d_prev;

              usern = Login.USERNAME_TRACKER;
              crp_nm = crop_name.getText().toString();
              d_ID = desId.getText().toString();
              d_name = desName.getText().toString();
              d_cure = desCure.getText().toString();
              d_prev = desPrev.getText().toString();

              long id = e_farmDB.registerDiseaseMethod(usern,crp_nm,d_ID,d_name,d_cure,d_prev);


              //For Testing Insert Error
              /*  long id = 0;
              try {

                  id = e_farmDB.registerDiseaseMethod(usern,crp_nm,d_ID,d_name,d_cure,d_prev);

              }
              catch (SQLException e){

                  Log.e("Exception","SQLException"+String.valueOf(e.getMessage()));
                  e.printStackTrace();
              }*/


              if (id < 0)
              {

               Message.message(getActivity(), "Unsuccessful");

              }
              else
              {
                  Message.message(getActivity(), "Successfuly inserted a row");




                  url = Uri.parse("http://"+phone_ip+"/E_farm_Proj/App_Connection/diagnosisReg_insertion.php").buildUpon()
                          .appendQueryParameter("username",usern)
                          .appendQueryParameter("crop_name",crp_nm)
                          .appendQueryParameter("disease_id",d_ID)
                          .appendQueryParameter("disease_name",d_name)
                          .appendQueryParameter("disease_cure",d_cure)
                          .appendQueryParameter("disease_prevention",d_prev)
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

                  crop_name.setText("");
                  desId.setText("");
                  desName.setText("");
                  desCure.setText("");
                  desPrev.setText("");


              }

          }
      });


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                crop_name.setText("");
                desId.setText("");
                desName.setText("");
                desCure.setText("");
                desPrev.setText("");
            }
        });

        return  view;//inflater.inflate(R.layout.admin_register_disease_tab,container,false);
    }

}

