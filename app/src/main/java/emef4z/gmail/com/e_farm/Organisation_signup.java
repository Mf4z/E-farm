package emef4z.gmail.com.e_farm;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

public class Organisation_signup extends AppCompatActivity {

    E_farmDB e_farmDB;
    ImageView photo;
    EditText orgN,orgId,opLicId,yrOfEst,orgLoc,orgContact,orgEmail,orgUserN,orgPass,orgRpass;
    String phone_ip = "10.0.2.2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organisation_signup);

        e_farmDB = new E_farmDB(this);

        photo = (ImageView) findViewById(R.id.orgProfile_photo_IV);
        orgN = (EditText) findViewById(R.id.orgName_et);
        orgId = (EditText) findViewById(R.id.orgId_et);
        opLicId = (EditText) findViewById(R.id.opLicenceID_et);
        yrOfEst = (EditText) findViewById(R.id.yearOfEst_et);
        orgLoc= (EditText) findViewById(R.id.orgLocation_et);
        orgContact= (EditText) findViewById(R.id.orgContactNo_et);
        orgEmail =(EditText) findViewById(R.id.orgEmail_et);
        orgUserN=(EditText) findViewById(R.id.orgUserName_et);
        orgPass=(EditText) findViewById(R.id.orgPass_et);
        orgRpass=(EditText) findViewById(R.id.rOrgPass_et);
    }



    //Organisation Insert Method

    public void orgInsertMethod(View view)
    {

        //String phone_ip = "10.0.2.2";
        final ProgressDialog pd;
        pd = new ProgressDialog(Organisation_signup.this);
        pd.setMessage("Sending Data...");
        pd.setCancelable(false);
        pd.show();
        final String url;

        String profile_photo,org_name,org_id,op_id,yoe,loc,cont,org_email,orgUserName,password,rpass;

        profile_photo = photo.getDrawable().toString();
        org_name = orgN.getText().toString();
        org_id = orgId.getText().toString();
        op_id = opLicId.getText().toString();
        yoe = yrOfEst.getText().toString();
        loc = orgLoc.getText().toString();
        cont = orgContact.getText().toString();
        org_email = orgEmail.getText().toString();
        orgUserName = orgUserN.getText().toString();
        password = orgPass.getText().toString();
        rpass = orgRpass.getText().toString();


        if(password.equals(rpass))
        {
            long id = e_farmDB.registerOrgMethod(profile_photo, org_name, org_id, op_id, yoe, loc, cont, org_email, orgUserName, password);


            url = Uri.parse("http://"+phone_ip+"/E_farm_Proj/App_Connection/orgReg_insertion.php").buildUpon()
                    .appendQueryParameter("org_name",org_name)
                    .appendQueryParameter("org_id",org_id)
                    .appendQueryParameter("op_id",op_id)
                    .appendQueryParameter("yoe",yoe)
                    .appendQueryParameter("location",loc)
                    .appendQueryParameter("contact_no",cont)
                    .appendQueryParameter("email",org_email)
                    .appendQueryParameter("username",orgUserName)
                    .appendQueryParameter("password",password)
                    .appendQueryParameter("role",E_farmDB.ORG_ROLE)
                    .build().toString();

            Ion.with(getBaseContext())
                    .load(url)
                    .progressDialog(pd)
                    .asString()
                    .setCallback(new FutureCallback<String>() {
                        @Override
                        public void onCompleted(Exception e, String result) {

                            try
                            {
                                Toast.makeText(getBaseContext(),result,Toast.LENGTH_SHORT).show();
                            }
                            catch (Exception ex)
                            {
                                Toast.makeText(getBaseContext(),"Error! " + ex.getMessage() + "No connectivity",Toast.LENGTH_SHORT).show();
                            }
                            pd.dismiss();
                        }
                    });


            if (id < 0)
            {
                Message.message(this, "Unsuccessful");
            }
            else
            {

                Message.message(this, "Successfuly inserted a row");
            }


            orgN.setText("");
            orgId.setText("");
            opLicId.setText("");
            yrOfEst.setText("");
            orgLoc.setText("");
            orgContact.setText("");
            orgEmail.setText("");
            orgUserN.setText("");
            orgPass.setText("");
            orgRpass.setText("");


            Intent myIntent = new Intent(getBaseContext(),Login.class);
            startActivity(myIntent);
            finish();



        }

        else
        {
            Message.message(this,"Passwords not matching");
            orgPass.setText("");
            orgRpass.setText("");

        }
    }


    //Functionality for Cancel button
    public void orgCancelMethod(View view)
    {
        orgN.setText("");
        orgId.setText("");
        opLicId.setText("");
        yrOfEst.setText("");
        orgLoc.setText("");
        orgContact.setText("");
        orgEmail.setText("");
        orgUserN.setText("");
        orgPass.setText("");
        orgRpass.setText("");
    }
}
