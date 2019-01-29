package emef4z.gmail.com.e_farm;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.List;

import static android.R.attr.id;
import static emef4z.gmail.com.e_farm.E_farmDB.FARMER_ROLE;

public class Login extends AppCompatActivity {

    E_farmDB e_farmDB;
    EditText userN,passW;
    String admin_role,farmer_role,org_role,user_name,passWord;
    Button loginBtn;
    public  static String USERNAME_TRACKER = "";
    public  static String ORG_ID_TRACKER = "";
    String phone_ip = "10.0.2.2";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        e_farmDB = new E_farmDB(this);
        userN = (EditText) findViewById(R.id.username_editText);
        passW = (EditText) findViewById(R.id.pass_editText);
        loginBtn = (Button) findViewById(R.id.login_button);

        admin_role = "admin";
        farmer_role = "farmer";
        org_role = "organisation";


        //getRemoteFarmerData();


    }


        public void loginMethod(View view)
    {
        user_name = userN.getText().toString();
        passWord = passW.getText().toString();
        e_farmDB = new E_farmDB(this);
        Cursor cr = e_farmDB.loginCredentialsMethodFarmer(e_farmDB);
        Cursor cr_org = e_farmDB.loginCredentialsMethodOrg(e_farmDB);
        Cursor cr_adm = e_farmDB.loginCredentialsMethodAdmin(e_farmDB);
        cr.moveToFirst();
        cr_org.moveToFirst();
        cr_adm.moveToFirst();

        boolean login_stat = false;
        String Name = "";
        String Ord_id = "";

        do
        {
            if((user_name.equals(cr.getString(1)))&& (passWord.equals(cr.getString(2))) && (E_farmDB.FARMER_ROLE.equals(cr.getString(3))))
            {
                login_stat = true;
                Name = cr.getString(1);
                USERNAME_TRACKER = Name;
               // String userdata = userN.getText().toString();

               Intent myIntent = new Intent(getBaseContext(),Farmer_Control_Panel.class);
              //myIntent.putExtra("userdata",USERNAME_TRACKER);
                startActivity(myIntent);
                finish();

            }


        }while (cr.moveToNext());


        do{

        if((user_name.equals(cr_org.getString(2)))&& (passWord.equals(cr_org.getString(3))) && (E_farmDB.ORG_ROLE.equals(cr_org.getString(4))))
    {

        login_stat = true;
        Ord_id = cr_org.getString(1);
        Name = cr_org.getString(2);
        USERNAME_TRACKER = Name;
        ORG_ID_TRACKER = Ord_id;
        //String userdata = userN.getText().toString();
        //String orgID =ORG_ID_TRACKER;

        Intent myIntent = new Intent(getBaseContext(),Organisation_controlPanel.class);
        startActivity(myIntent);
        finish();

    }
        }while (cr_org.moveToNext());


        do
        {
            if((user_name.equals(cr_adm.getString(1)))&& (passWord.equals(cr_adm.getString(2))) && (E_farmDB.ADMIN_ROLE.equals(cr_adm.getString(3))))
    {
        login_stat = true;
        Name = cr_adm.getString(1);
        USERNAME_TRACKER = Name;

        Intent myIntent = new Intent(getBaseContext(),Admin_Control_Panel.class);
        startActivity(myIntent);
        finish();

    }
        }while (cr_adm.moveToNext());



        if(login_stat)
        {
            Toast.makeText(getBaseContext(),"Welcome "+USERNAME_TRACKER+" "+Ord_id,Toast.LENGTH_LONG).show();

        }

        else
        {
            Toast.makeText(getBaseContext(),"Login UnSuccessful "+Name,Toast.LENGTH_LONG).show();
        }





    }


    public void signupAsUser(View view)
    {
        Intent myIntent = new Intent(getBaseContext(),Signup.class);
        startActivity(myIntent);

    }

    public void signupAsOrganisation(View view)
    {
        Intent myIntent = new Intent(getBaseContext(),Organisation_signup.class);
        startActivity(myIntent);

    }

    public void getRemoteFarmerData()
    {

        //e_farmDB.DeleteFarmerData();
        final ProgressDialog pd;
        pd = new ProgressDialog(Login.this);
        pd.setMessage("Loading...");
        pd.setCancelable(false);
        pd.show();

        final String url =
                Uri.parse("http://"+phone_ip+"/E_farm_Proj/App_Connection/farmer_data_json.php").buildUpon().build().toString();
        Ion.with(getBaseContext())
                .load(url)
                .progressDialog(pd)
                .as(new TypeToken<List<Farmer_json>>() {})
                .setCallback(new FutureCallback<List<Farmer_json>>() {
                    @Override
                    public void onCompleted(Exception e, List<Farmer_json> result) {

                        try{

                        if (result != null)
                        {
                            try {
                                e_farmDB = new E_farmDB(getBaseContext());
                                //SampleDB DB = new SampleDB(getBaseContext());

                                for (Farmer_json r : result) {

                                    //e_farmDB.registerFarmerMethod(profile_photo, first_name, last_name, loc, cont, user_email, userName, password)
                                    long id = e_farmDB.registerFarmerMethod(r.profile_photo, r.first_name, r.last_name,
                                            r.location,r.contact_no,r.email,r.username,r.password);
                                    Toast.makeText(getBaseContext()," "+id,Toast.LENGTH_LONG).show();

                                }


                            } catch (Exception ex) {

                                Toast.makeText(getApplicationContext(), "Error!!" + ex.getMessage(),
                                        Toast.LENGTH_LONG).show();
                            }
                        }
                        pd.dismiss();

                        }catch (Exception ex) {

                            Toast.makeText(getApplicationContext(), "Error!!" + ex.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }

                    }
                });
    }


}
