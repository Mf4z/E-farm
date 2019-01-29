package emef4z.gmail.com.e_farm;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.List;

public class SplashScreen extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent i = new Intent(getBaseContext(),Login.class);
                startActivity(i);
                finish();
            }
        },1000);



    }


}
