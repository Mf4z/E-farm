package emef4z.gmail.com.e_farm;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

public class Storage_form extends AppCompatActivity {
    E_farmDB e_farmDB;
    EditText prodName,prodQuantity,stoDuration,specStoragepSace;
    Spinner select_duration,select_sto_space,sel_tp,prd_typ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage_form);

        e_farmDB = new E_farmDB(this);

        prodName= (EditText) findViewById(R.id.prod_name_et);
        prodQuantity = (EditText) findViewById(R.id.prod_qty_et);
        prd_typ = (Spinner) findViewById(R.id.product_Type_sto);
        stoDuration = (EditText) findViewById(R.id.prodDuration_et);
        select_duration = (Spinner)findViewById(R.id.duration_spinner);
        specStoragepSace = (EditText) findViewById(R.id.storage_space_et);
        select_sto_space = (Spinner) findViewById(R.id.space_spinner);
        sel_tp = (Spinner) findViewById(R.id.tp_spinner);


    }


    //Functionality for Submit button
    public void storeProdInsertMethod(View view)
    {
        String orgIdFk,farmerUserN,prod_Name,prod_qty,prod_typ,stodur,dur,stoSpace,sel_space,tp_sel;

        orgIdFk ="SELECT organisation_id FROM farmer";
        farmerUserN ="";

        prod_Name = prodName.getText().toString();
        prod_qty = prodQuantity.getText().toString();
        prod_typ =prd_typ.getSelectedItem().toString();
        dur = select_duration.getSelectedItem().toString();
        stodur = stoDuration.getText().toString() + dur;
        sel_space = select_sto_space.getSelectedItem().toString();
        stoSpace = specStoragepSace.getText().toString() + sel_space;
        tp_sel = sel_tp.getSelectedItem().toString();

            long id = e_farmDB.registerFarmer_Storage_Method(orgIdFk,farmerUserN,prod_Name,prod_typ,prod_qty,stodur,stoSpace,tp_sel);

            if (id < 0)
            {
                Message.message(this, "Unsuccessful");
            }
            else
            {

                Message.message(this, "Successfuly inserted a row");

                prodName.setText("");
                prodQuantity.setText("");
                stoDuration.setText("");
                specStoragepSace.setText("");

            }
    }

    public void  storeProdcancelMethod(View view)
    {
        prodName.setText("");
        prodQuantity.setText("");
        stoDuration.setText("");
        specStoragepSace.setText("");
    }
}
