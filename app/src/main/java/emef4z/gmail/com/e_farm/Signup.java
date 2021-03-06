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

public class Signup extends AppCompatActivity {

    E_farmDB e_farmDB;
    ImageView photo;
    EditText firstN,lastN,location,contact,email,userN,pass,rpass;
    String phone_ip = "10.0.2.2";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        e_farmDB = new E_farmDB(this);

        photo = (ImageView) findViewById(R.id.profile_photo_IV);
        firstN = (EditText) findViewById(R.id.Fname_et);
        lastN = (EditText) findViewById(R.id.Lname_et);
        location= (EditText) findViewById(R.id.userlocation_et);
        contact= (EditText) findViewById(R.id.contactNo_et);
        email =(EditText) findViewById(R.id.userEmail_et);
        userN=(EditText) findViewById(R.id.userName_et);
        pass=(EditText) findViewById(R.id.pass_et);
        rpass=(EditText) findViewById(R.id.rpass_et);
    }



    //Functionality for Submit button
    public void insertFarmerMethod(View view)
    {

       // String phone_ip = "10.0.2.2";
        final ProgressDialog pd;
        pd = new ProgressDialog(Signup.this);
        pd.setMessage("Sending Data...");
        pd.setCancelable(false);
        pd.show();
        final String url;

        String profile_photo,first_name,last_name,loc,cont,user_email,userName,password,r_pass;

        profile_photo = photo.getDrawable().toString();
        first_name = firstN.getText().toString();
        last_name = lastN.getText().toString();
        loc = location.getText().toString();
        cont = contact.getText().toString();
        user_email= email.getText().toString();
        userName = userN.getText().toString();
        password = pass.getText().toString();
        r_pass = rpass.getText().toString();


        if(password.equals(r_pass))
        {
            long id = e_farmDB.registerFarmerMethod(profile_photo, first_name, last_name, loc, cont, user_email, userName, password);


            url = Uri.parse("http://"+phone_ip+"/E_farm_Proj/App_Connection/farmerReg_insertion.php").buildUpon()
                    .appendQueryParameter("fname",first_name)
                    .appendQueryParameter("lname",last_name)
                    .appendQueryParameter("location",loc)
                    .appendQueryParameter("contact_no",cont)
                    .appendQueryParameter("email",user_email)
                    .appendQueryParameter("username",userName)
                    .appendQueryParameter("password",password)
                    .appendQueryParameter("role", E_farmDB.FARMER_ROLE)
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

            firstN.setText("");
            lastN.setText("");
            location.setText("");
            contact.setText("");
            email.setText("");
            userN.setText("");
            pass.setText("");
            rpass.setText("");


            Intent myIntent = new Intent(getBaseContext(),Login.class);
            startActivity(myIntent);
            finish();

        }

        else
        {
            Message.message(this,"Passwords not matching");
            pass.setText("");
            rpass.setText("");

        }

    }

    //Functionality for Cancel button
    public void farmerCancelMethod(View view)
    {
        firstN.setText("");
        lastN.setText("");
        location.setText("");
        contact.setText("");
        email.setText("");
        userN.setText("");
        pass.setText("");
        rpass.setText("");

    }
}
