package ca.ualberta_ecocar.ecocar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View view){
                SinchClient sinchClient=Sinch.getSinchclientBuilder()
                        .context(this)
                        .userId("current-user-id")
                        .applicationKey("app-key")
                        .applicationSecret("app-secret")
                        .environmentHost("sandbox.sinch.com")
                        .build();
            }
        });
        setContentView(R.layout.activity_main);
    }

    public void makeCall(View view){
        Toast toast = Toast.makeText(getApplicationContext(),"You are trying to make a call",Toast.LENGTH_LONG);
        toast.show();
        TextView header= (TextView) findViewById(R.id.textView);
        header.setTextColor(getResources().getColor(R.color.colorPrimary));
        header.setTextSize(104);

//        TextView newText=new TextView(this);
//        newText.setText("HELLO HOW U DO?");
//        newText.setTextSize(24);
//        newText.setX(500);
//        newText.setY(500);
//        setContentView(newText);

    }
}
