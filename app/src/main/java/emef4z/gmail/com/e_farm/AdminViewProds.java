package emef4z.gmail.com.e_farm;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class AdminViewProds extends AppCompatActivity {

    E_farmDB e_farmDB;
    ListView viewprdo_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_prods);


        e_farmDB = new E_farmDB(this);
        viewprdo_list = (ListView) findViewById(R.id.viewProd_lv);


        try {

            Cursor cursor = e_farmDB.allProdQuery();

            String[] columns = {"_id","product_name","product_type","product_quantity","status","product_price",
                    "username","product_description"};

            int[] txtIDs = {R.id.prod_id_tv,R.id.product_name_tv,R.id.prod_type_tv,R.id.prod_qty_tv,
                    R.id.prod_stat_tv,R.id.prod_price_tv,R.id.produsern_tv,R.id.prod_details_tv};


            SimpleCursorAdapter myAdapter = new SimpleCursorAdapter(getBaseContext(),R.layout.dispaly_products_row,
                    cursor, columns, txtIDs);



            viewprdo_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(getBaseContext()," Clicked" +position,Toast.LENGTH_SHORT).show();

                }

            });



            viewprdo_list.setAdapter(myAdapter);




        } catch(Exception ex)
        {
            Toast.makeText(getBaseContext(),"Error!!" + ex.getMessage(),Toast.LENGTH_LONG).show();
        }


    }
}
