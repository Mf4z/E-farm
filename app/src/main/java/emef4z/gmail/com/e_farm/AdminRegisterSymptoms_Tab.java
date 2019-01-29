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

public class AdminRegisterSymptoms_Tab extends Fragment {


    E_farmDB e_farmDB;
    Button submit,cancel;
    EditText disease_FkId,affected_crop,def_s,def_c,def_loc,def_n,aff_part,def_arrg;
    String phone_ip = "10.0.2.2";



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.register_symptoms_form_tab,container,false);

        e_farmDB = new E_farmDB(getActivity());

        disease_FkId = (EditText)view.findViewById(R.id.fk_diseaseID_et);
        affected_crop = (EditText)view.findViewById(R.id.affected_crop_et);
        def_s = (EditText)view.findViewById(R.id.defectShape_et);
        def_c = (EditText)view.findViewById(R.id.defect_color_et);
        def_loc = (EditText)view.findViewById(R.id.defectLoc_et);
        def_n = (EditText)view.findViewById(R.id.defectNature_et);
        aff_part = (EditText)view.findViewById(R.id.affectedPart_et);
        def_arrg = (EditText)view.findViewById(R.id.defect_arrangement_et);
        submit = (Button)view.findViewById(R.id.submit_btn_symp);
        cancel = (Button)view.findViewById(R.id.cancel_btn_symp);




        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ProgressDialog pd;
                pd = new ProgressDialog(getActivity());
                pd.setMessage("Loading...");
                pd.setCancelable(false);
                pd.show();
                final String url;

                String des_id,aff_crop,def_sh,def_cl,def_location,def_nat,aff_prt,def_arng;

                des_id = disease_FkId.getText().toString();
                aff_crop = affected_crop.getText().toString();
                def_sh = def_s.getText().toString();
                def_cl = def_c.getText().toString();
                def_location = def_loc.getText().toString();
                def_nat = def_n.getText().toString();
                aff_prt = aff_part.getText().toString();
                def_arng = def_arrg.getText().toString();

                long id = e_farmDB.registerSymptomsMethod(des_id,aff_crop,def_sh,def_cl,def_location,def_nat,aff_prt,def_arng);


                url = Uri.parse("http://"+phone_ip+"/E_farm_Proj/App_Connection/symptomReg_insertion.php").buildUpon()
                        .appendQueryParameter("disease_id",des_id)
                        .appendQueryParameter("affected_crop",aff_crop)
                        .appendQueryParameter("defect_shape",def_sh)
                        .appendQueryParameter("defect_color",def_cl)
                        .appendQueryParameter("defect_location",def_location)
                        .appendQueryParameter("defect_nature",def_nat)
                        .appendQueryParameter("affected_part_of_plant",aff_prt)
                        .appendQueryParameter("defect_arrangement",def_arng)
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


                if (id < 0)
                {
                    Message.message(getActivity(), "Unsuccessful");
                }
                else
                {


                    Message.message(getActivity(), "Successfuly inserted a row");




                    disease_FkId.setText("");
                    affected_crop.setText("");
                    def_s.setText("");
                    def_c.setText("");
                    def_loc.setText("");
                    def_n.setText("");
                    aff_part.setText("");
                    def_arrg.setText("");

                }

            }
        });


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                disease_FkId.setText("");
                affected_crop.setText("");
                def_s.setText("");
                def_c.setText("");
                def_loc.setText("");
                def_n.setText("");
                aff_part.setText("");
                def_arrg.setText("");
            }
        });

        return view;//inflater.inflate(R.layout.register_symptoms_form_tab,container,false);


    }

}
