package emef4z.gmail.com.e_farm;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.List;

import static emef4z.gmail.com.e_farm.Login.USERNAME_TRACKER;

/**
 * Created by USER on 26-Mar-17.
 */

public class AdminMakeDiagnosis_Tab extends Fragment {
    String DISEASE_ID_TRACKER = "";

    E_farmDB e_farmDB;
    Spinner selCrop,selShape,selColor,selLocation,selAffpart,selNature,selArrg;
    Button diagnose;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.admin_make_diagnosis_tab,container,false);

        getRemoteDataSymptom();
        e_farmDB = new E_farmDB(getActivity());


        selCrop = (Spinner)view.findViewById(R.id.spinnerAffCrop);
        selShape = (Spinner)view.findViewById(R.id.spinnerDefShape);
        selColor = (Spinner)view.findViewById(R.id.spinnerDefCol);
        selLocation = (Spinner)view.findViewById(R.id.spinnerDefLoc);
        selAffpart = (Spinner)view.findViewById(R.id.spinnerAffPart);
        selNature = (Spinner)view.findViewById(R.id.spinnerDefNature);
        selArrg = (Spinner)view.findViewById(R.id.spinnerDefArrg);


        diagnose = (Button)view.findViewById(R.id.diagnose_btn);

        diagnose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dCrop,dShape,dColor,dLocation,dAffpart,dNature,dArgg;

                dCrop = selCrop.getSelectedItem().toString();
                dShape = selShape.getSelectedItem().toString();
                dColor = selColor.getSelectedItem().toString();
                dLocation = selLocation.getSelectedItem().toString();
                dAffpart = selAffpart.getSelectedItem().toString();
                dNature = selNature.getSelectedItem().toString();
                dArgg = selArrg.getSelectedItem().toString();

           /*     Cursor diag = e_farmDB.diagnosisMethod(e_farmDB);

                boolean login_stat = false;
                String des_id = "";
                diag.moveToFirst();

                do
                {
                    Toast.makeText(getActivity(),dCrop+" \n"+dShape+" \n"
                            +dColor+" \n"+dLocation+" \n"+dAffpart+" \n"+dNature+" \n"+dArgg,Toast.LENGTH_LONG).show();






                    if((dCrop.equals(diag.getString(2)))&& (dShape.equals(diag.getString(3))) && (dColor.equals(diag.getString(4)))
                            && (dLocation.equals(diag.getString(5)))&& (dAffpart.equals(diag.getString(6)))
                            && (dNature.equals(diag.getString(7)))&& (dArgg.equals(diag.getString(8))) )
                    {
                        login_stat = true;
                        des_id = diag.getString(1);
                        DISEASE_ID_TRACKER = des_id;

                    }


                }while (diag.moveToNext());






                if(login_stat)
                {
                    Toast.makeText(getActivity(),"Disease ID : "+DISEASE_ID_TRACKER,Toast.LENGTH_SHORT).show();

                }

                else
                {
                    Toast.makeText(getActivity(),"Disease ID NOT Found!"+DISEASE_ID_TRACKER,Toast.LENGTH_SHORT).show();
                }

*/

                Cursor cursorDesID = e_farmDB.diagnoseMethodAlt(dCrop,dShape,dColor,dLocation,dAffpart,dNature,dArgg);

                String desID =cursorDesID.toString();


                DISEASE_ID_TRACKER = desID;

                Toast.makeText(getActivity(),"Disease ID : "+DISEASE_ID_TRACKER,Toast.LENGTH_SHORT).show();

                Cursor cursor = e_farmDB.viewDiagnosisReport("DES-002");

                //String cursor = e_farmDB.diagnoseMethodAlt(dCrop,dShape,dColor,dLocation,dAffpart,dNature,dArgg);
                if(cursor.getCount() == 0)
                {
                    showMessage("Error","No Diagnosis Found");
                    //Message.message(getActivity(),"Diagnosis unsucessful");
                }

                StringBuffer buffer = new StringBuffer();

                while (cursor.moveToNext()) {
                    buffer.append("Crop Name : " + cursor.getString(1) + "\n");
                    buffer.append("Disease Name : " + cursor.getString(2) + "\n");
                    buffer.append("Disease Cure : " + cursor.getString(3) + "\n");
                    buffer.append("Disease Prevention : " + cursor.getString(4) + "\n");

                }
                showMessage("Diagnosis Report",buffer.toString());




                // diagnosisMethod(dCrop,dShape,dColor,dLocation,dAffpart,dNature,dArgg);

                //DISEASE_ID_TRACKER = disease_id;

             //   Message.message(getActivity(),"Disease id : "+disease_id);

                //Cursor cursor = e_farmDB.viewDiagnosisReport(disease_id);

                //Cursor cursor = e_farmDB.diagnoseMethodAlt(dCrop,dShape,dColor,dLocation,dAffpart,dNature,dArgg);


               /*
                if(cursor.getCount() == 0)
                {
                    showMessage("Error","No Diagnosis Found");
                    //Message.message(getActivity(),"Diagnosis unsucessful");
                }

                StringBuffer buffer = new StringBuffer();

                while (cursor.moveToNext()) {
                    buffer.append("Crop Name : " + cursor.getString(1) + "\n");
                    buffer.append("Disease Name : " + cursor.getString(2) + "\n");
                    buffer.append("Disease Cure : " + cursor.getString(3) + "\n");
                    buffer.append("Disease Prevention : " + cursor.getString(4) + "\n");

                }
                    showMessage("Diagnosis Report",buffer.toString());



*/
            }
        });


        return view;
    }


    public void showMessage(String title,String message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }


    public void getRemoteDataSymptom ()
    {
        String phone_ip = "10.0.2.2";

        e_farmDB = new E_farmDB(getActivity());

        final ProgressDialog pd;
        pd = new ProgressDialog(getActivity());
        pd.setMessage("Loading...");
        pd.setCancelable(false);
        pd.show();


        try {

            // e_farmDB.DeleteProductData();

            final String url =
                    Uri.parse("http://"+phone_ip+"/E_farm_Proj/App_Connection/symptom_data_json.php").buildUpon().build().toString();
            Ion.with(getActivity())
                    .load(url)
                    .progressDialog(pd)
                    .as(new TypeToken<List<Symptom_json>>() {
                    })
                    .setCallback(new FutureCallback<List<Symptom_json>>() {
                        @Override
                        public void onCompleted(Exception e, List<Symptom_json> result) {

                            if (result != null) {
                                try {
                                    e_farmDB = new E_farmDB(getActivity());

                                    for (Symptom_json r : result) {

                                        e_farmDB.registerSymptomsMethod(r.disease_id, r.affected_crop, r.defect_shape,
                                                r.defect_color, r.defect_location, r.defect_nature, r.affected_part_of_plant,
                                                r.defect_arrangement);
                                    }

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

    }
}
