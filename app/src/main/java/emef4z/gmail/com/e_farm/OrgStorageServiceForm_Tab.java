package emef4z.gmail.com.e_farm;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by USER on 25-Mar-17.
 */

public class OrgStorageServiceForm_Tab extends Fragment {

    E_farmDB e_farmDB;
    EditText perishablePrice,nonperishablePrice,minWeight,maxWeight,minSto,maxSto,tpPrice;
    Button submit,cancel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.storage_provider_form_tab,container,false);



        e_farmDB = new E_farmDB(getActivity());

        perishablePrice = (EditText)view.findViewById(R.id.perishable_et);
        nonperishablePrice = (EditText)view.findViewById(R.id.nonPerishable_et);
        minWeight = (EditText)view.findViewById(R.id.minWeight_et);
        maxWeight = (EditText)view.findViewById(R.id.maxWeight_et);
        minSto = (EditText)view.findViewById(R.id.smallestSpace_et);
        maxSto = (EditText)view.findViewById(R.id.largestSpace_et);
        tpPrice = (EditText)view.findViewById(R.id.tpPrice_et);
        submit= (Button) view.findViewById(R.id.submit_btn_stoProv);
        cancel= (Button) view.findViewById(R.id.cancel_btn_stoProv);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String org_id,perPrice,nonPerPrice,minW,maxW,minSp,maxSp,tp;

                org_id ="SELECT organisation_id";

                perPrice = perishablePrice.getText().toString();
                nonPerPrice = nonperishablePrice.getText().toString();
                minW = minWeight.getText().toString();
                maxW= maxWeight.getText().toString();
                minSp = minSto.getText().toString();
                maxSp = maxSto.getText().toString();
                tp = tpPrice.getText().toString();


                long id = e_farmDB.registerStoServiceMethod(org_id,perPrice,nonPerPrice,minW,maxW,minSp,maxSp,tp);

                if (id < 0)
                {
                    Message.message(getActivity(), "Unsuccessful");
                }
                else
                {
                    Message.message(getActivity(), "Successfuly inserted a row");

                    perishablePrice.setText("");
                    nonperishablePrice.setText("");
                    minWeight.setText("");
                    maxWeight.setText("");
                    minSto.setText("");
                    maxSto.setText("");
                    tpPrice.setText("");
                }

            }
        });


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                perishablePrice.setText("");
                nonperishablePrice.setText("");
                minWeight.setText("");
                maxWeight.setText("");
                minSto.setText("");
                maxSto.setText("");
                tpPrice.setText("");
            }
        });

        return view;
    }
}
