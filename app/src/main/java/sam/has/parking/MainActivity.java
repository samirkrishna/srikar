package sam.has.parking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etname,etlatitude,etlongitude;
    private Button save,proceed;

    FirebaseDatabase db;
    DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseApp.initializeApp(this);
        db = FirebaseDatabase.getInstance();

        etname=(EditText)findViewById(R.id.et1);
        etlatitude=(EditText)findViewById(R.id.et2);
        etlongitude=(EditText)findViewById(R.id.et3);
        save=(Button)findViewById(R.id.save);
        proceed=(Button)findViewById(R.id.proceed);

        mDatabase= FirebaseDatabase.getInstance().getReference().child("Users");
        save.setOnClickListener(this);
        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,MapsActivity.class);
                startActivity(i);
            }
        });

    }

    @Override
    public void onClick(View view) {

        if(view==proceed)
        {
            finish();
        }

        if (view==save){
            saveUserInformation();
            etname.getText().clear();
            etlatitude.getText().clear();
            etlongitude.getText().clear();
        }

    }

    private void saveUserInformation(){
        String name =etname.getText().toString().trim();
        double latitude= Double.parseDouble(etlatitude.getText().toString().trim());
        double longitude= Double.parseDouble(etlongitude.getText().toString().trim());
        String id = mDatabase.push().getKey();
        UserInformation userInformation=new UserInformation(id,name,latitude,longitude);
        //mDatabase.child("Users").setValue(userInformation);
        mDatabase.child(id).setValue(userInformation);
        Toast.makeText(this,"Saved",Toast.LENGTH_LONG).show();
    }


}
