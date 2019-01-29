package emef4z.gmail.com.e_farm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Test_Activity extends AppCompatActivity {

    TextView tester;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_);

        tester = (TextView)findViewById(R.id.test);
        Intent intent = getIntent();

        String name = intent.getStringExtra("username");
        tester.setText(name);

        //a_FarmerInfoQuery

    }
}
