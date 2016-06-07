package ca.ualberta_ecocar.ecocar;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

/**
 * Created by Harry on 05-Jun-16.
 */
public class StatsActivity extends Activity {

    EditText message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_monitor_stats);

        message = (EditText) findViewById(R.id.messageToSend);
        final TextView dispText=(TextView) findViewById(R.id.textToDisplay);

        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference myRef=database.getReference("message/newText");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                dispText.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Firebase", "Failed to read value.", error.toException());
            }
        });


    }

    public void deployToFirebase (View view){
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference myRef=database.getReference("message");
        myRef.child(java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime())).child("Speed").setValue(message.getText().toString());

        myRef.child(java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime())).child("voltage").setValue(message.getText().toString());
    }
}
