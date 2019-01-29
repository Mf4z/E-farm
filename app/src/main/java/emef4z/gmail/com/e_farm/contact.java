package emef4z.gmail.com.e_farm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class contact extends AppCompatActivity {

    E_farmDB e_farmDB;
    EditText name,email,contact,mesg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        e_farmDB = new E_farmDB(this);

        name = (EditText) findViewById(R.id.contact_name_et);
        contact = (EditText) findViewById(R.id.contact_phone_et);
        email = (EditText) findViewById(R.id.contact_email_et);
        mesg = (EditText) findViewById(R.id.contact_message_et);
    }

    public void submitMsgInsertMethod(View view)
    {
        String sender_name,cont,sender_email,msg;

        sender_name = name.getText().toString();
         cont = contact.getText().toString();
        sender_email = email.getText().toString();
        msg = mesg.getText().toString();

        long id = e_farmDB.contactUsMethod(sender_name,sender_email,cont,msg);

        if (id < 0)
        {
            Message.message(this, "Unsuccessful");
        }
        else
        {

            Message.message(this, "Successfuly inserted a row");

            name.setText("");
            contact.setText("");
            email.setText("");
            mesg.setText("");

            Intent myIntent = new Intent(getBaseContext(),Farmer_Control_Panel.class);
            startActivity(myIntent);
            finish();


        }

    }

    //Functionality for Cancel button
    public void contactUsCancelMethod(View view)
    {
        name.setText("");
        contact.setText("");
        email.setText("");
        mesg.setText("");
    }

}
