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

public class AdminRegOrg_Tab extends Fragment {

    E_farmDB e_farmDB;
    ImageView photo;
    EditText orgN,orgId,opLicId,yrOfEst,orgLoc,orgContact,orgEmail,orgUserN,orgPass,orgRpass;
    Button submit,cancel;
    String phone_ip = "10.0.2.2";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.admin_reg_org_tab,container,false);


        e_farmDB = new E_farmDB(getActivity());

        photo = (ImageView)view.findViewById(R.id.orgProfile_photo_IV);
        orgN = (EditText)view.findViewById(R.id.orgName_et);
        orgId = (EditText)view.findViewById(R.id.orgId_et);
        opLicId = (EditText)view.findViewById(R.id.opLicenceID_et);
        yrOfEst = (EditText)view.findViewById(R.id.yearOfEst_et);
        orgLoc= (EditText)view.findViewById(R.id.orgLocation_et);
        orgContact= (EditText)view.findViewById(R.id.orgContactNo_et);
        orgEmail =(EditText)view.findViewById(R.id.orgEmail_et);
        orgUserN=(EditText)view.findViewById(R.id.orgUserName_et);
        orgPass=(EditText)view.findViewById(R.id.orgPass_et);
        orgRpass=(EditText)view.findViewById(R.id.rOrgPass_et);
        submit=(Button) view.findViewById(R.id.submit_btn_stfrm);
        cancel=(Button) view.findViewById(R.id.cancel_btn_stfrm);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ProgressDialog pd;
                pd = new ProgressDialog(getActivity());
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

                else
                {
                    Message.message(getActivity(),"Passwords not matching");
                    orgPass.setText("");
                    orgRpass.setText("");

                }

            }
        });


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
        });


        return view;
    }
}
