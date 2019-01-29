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
import android.widget.Toast;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

/**
 * Created by USER on 12-Apr-17.
 */

public class AdminRegAdmin_Tab extends Fragment {

    E_farmDB e_farmDB;
    ImageView photo;
    EditText firstN,lastN,location,contact,email,userN,pass,rpass;
    Button submit,cancel;
    String phone_ip = "10.0.2.2";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.admin_reg_admin_tab,container,false);

        e_farmDB = new E_farmDB(getActivity());

        photo = (ImageView)view.findViewById(R.id.adminprofile_photo_IV);
        firstN = (EditText)view.findViewById(R.id.adminFname_et);
        lastN = (EditText)view.findViewById(R.id.adminLname_et);
        location= (EditText)view.findViewById(R.id.adminlocation_et);
        contact= (EditText)view.findViewById(R.id.admincontactNo_et);
        email =(EditText)view.findViewById(R.id.adminEmail_et);
        userN=(EditText)view.findViewById(R.id.adminuserName_et);
        pass=(EditText)view.findViewById(R.id.adminpass_et);
        rpass=(EditText)view.findViewById(R.id.adminrpass_et);
        submit=(Button) view.findViewById(R.id.adminsubmit_btn_stfrm);
        cancel=(Button) view.findViewById(R.id.admincancel_btn_stfrm);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ProgressDialog pd;
                pd = new ProgressDialog(getActivity());
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
                    long id = e_farmDB.registerAdminMethod(profile_photo, first_name, last_name, loc, cont, user_email, userName, password);


                    url = Uri.parse("http://"+phone_ip+"/E_farm_Proj/App_Connection/adminReg_insertion.php").buildUpon()
                            .appendQueryParameter("fname",first_name)
                            .appendQueryParameter("lname",last_name)
                            .appendQueryParameter("location",loc)
                            .appendQueryParameter("contact_no",cont)
                            .appendQueryParameter("email",user_email)
                            .appendQueryParameter("username",userName)
                            .appendQueryParameter("password",password)
                            .appendQueryParameter("role",E_farmDB.ADMIN_ROLE)
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
                    }

                    firstN.setText("");
                    lastN.setText("");
                    location.setText("");
                    contact.setText("");
                    email.setText("");
                    userN.setText("");
                    pass.setText("");
                    rpass.setText("");


                }

                else
                {
                    Message.message(getActivity(),"Passwords not matching");
                    pass.setText("");
                    rpass.setText("");

                }
            }
        });


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                firstN.setText("");
                lastN.setText("");
                location.setText("");
                contact.setText("");
                email.setText("");
                userN.setText("");
                pass.setText("");
                rpass.setText("");

            }
        });

        return view;
    }
}
