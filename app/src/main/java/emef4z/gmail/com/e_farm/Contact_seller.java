package emef4z.gmail.com.e_farm;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Contact_seller extends AppCompatActivity {

    TextView phone;
    EditText message;
    Button ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_seller);

        phone = (TextView) findViewById(R.id.textView14);
        message = (EditText)findViewById(R.id.editText);
        ok = (Button)findViewById(R.id.button);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(),"Message sent!",Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }


    public void makeCall(View v) {
        String phone_no = phone.getText().toString();
        Intent myIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+ phone_no));
        startActivity(myIntent);
    }


    public void back(View v) {
     finish();
    }
}
