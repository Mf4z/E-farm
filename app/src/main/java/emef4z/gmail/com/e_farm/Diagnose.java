package emef4z.gmail.com.e_farm;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import static emef4z.gmail.com.e_farm.Login.USERNAME_TRACKER;
import static emef4z.gmail.com.e_farm.Login.USERNAME_TRACKER;

public class Diagnose extends AppCompatActivity {

    String DISEASE_ID_TRACKER = "";

    E_farmDB e_farmDB;
    Spinner selCrop,selShape,selColor,selLocation,selAffpart,selNature,selArrg;
    Button diagnose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnose);

        e_farmDB = new E_farmDB(this);



        selCrop = (Spinner)findViewById(R.id.spinnerAffCrop);
        selShape = (Spinner)findViewById(R.id.spinnerDefShape);
        selColor = (Spinner)findViewById(R.id.spinnerDefCol);
        selLocation = (Spinner)findViewById(R.id.spinnerDefLoc);
        selAffpart = (Spinner)findViewById(R.id.spinnerAffPart);
        selNature = (Spinner)findViewById(R.id.spinnerDefNature);
        selArrg = (Spinner)findViewById(R.id.spinnerDefArrg);


        diagnose = (Button)findViewById(R.id.diagnose_btn);

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

                //String disease_id = e_farmDB.diagnoseMethod(dCrop,dShape,dColor,dLocation,dAffpart,dNature,dArgg);
                Cursor cr = e_farmDB.diagnosisMethod(e_farmDB);
                cr.moveToFirst();
                boolean diagnosis_stat = false;
                String Des_id = "";

                do
                {


                    if((dCrop.equals(cr.getString(2)))&&(dShape.equals(cr.getString(3)))&&(dColor.equals(cr.getString(4)))
                            &&(dLocation.equals(cr.getString(5)))&&(dNature.equals(cr.getString(6)))
                            &&(dAffpart.equals(cr.getString(7)))&&(dArgg.equals(cr.getString(8))))
                    {
                        diagnosis_stat = true;
                        Des_id = cr.getString(1);
                        DISEASE_ID_TRACKER = Des_id;

                    }


                }while (cr.moveToNext());



                if(diagnosis_stat)
                {
                    Toast.makeText(getBaseContext(),"Successful "+Login.USERNAME_TRACKER+" "+DISEASE_ID_TRACKER,Toast.LENGTH_LONG).show();

                }

                else
                {
                    Toast.makeText(getBaseContext(),"UnSuccessful "+DISEASE_ID_TRACKER,Toast.LENGTH_LONG).show();
                }

                //DISEASE_ID_TRACKER = disease_id;

                //   Message.message(getActivity(),"Disease id : "+disease_id);

/*
                Cursor cursor = e_farmDB.viewDiagnosisReport(DISEASE_ID_TRACKER);

                //Cursor cursor = e_farmDB.diagnoseMethodAlt(dCrop,dShape,dColor,dLocation,dAffpart,dNature,dArgg);
                if(cursor.getCount() == 0)
                {
                    showMessage("Error","No Diagnosis Found");
                    //Message.message(getActivity(),"Diagnosis unsucessful");
                }

                StringBuffer buffer = new StringBuffer();

                while (cursor.moveToNext())
                {
                    buffer.append("Crop Name : "+cursor.getString(1)+"\n");
                    buffer.append("Disease Name : "+cursor.getString(2)+"\n");
                    buffer.append("Disease Cure : "+cursor.getString(3)+"\n");
                    buffer.append("Disease Prevention : "+cursor.getString(4)+"\n");

                    showMessage("Diagnosis Report",buffer.toString());
                }
*/
            }
        });

    }


    public void showMessage(String title,String message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
