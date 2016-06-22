package ca.ualberta_ecocar.ecocar;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

/**
 * Created by Harry on 05-Jun-16.
 */
public class StatsActivity extends Activity {

    EditText message;
    Integer count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_monitor_stats);

        message = (EditText) findViewById(R.id.messageToSend);
        final TextView dispText=(TextView) findViewById(R.id.textToDisplay);

        final TextView countText=(TextView) findViewById(R.id.countText);

        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference myRef=database.getReference("message/newText");
        DatabaseReference emergencyStatus = database.getReference("EmergencyStatus");
        DatabaseReference fuelCellReadings=database.getReference("readings");



        fuelCellReadings.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String value = dataSnapshot.child("data").getValue(String.class);
                dispText.setText(value);
                count=count+1;
                countText.setText(Integer.toString(count));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

//        fuelCellReadings.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                // This method is called once with the initial value and again
//                // whenever data at this location is updated.
//                String value = dataSnapshot.getValue(String.class);
//                dispText.setText(value);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//                // Failed to read value
//                Log.w("Firebase", "Failed to read value.", error.toException());
//            }
//        });
//        emergencyStatus.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
////                Integer value=dataSnapshot.getValue(Integer.class);
////                if (value==1){
////                    //Set screen to red and make emergency stuff happen
////                    Log.v("Firebase","Its an emergency yo");
////                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });


    }

    public void deployToFirebase (View view){
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference myRef=database.getReference("message");
        myRef.child(java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime())).child("Speed").setValue(message.getText().toString());

        myRef.child(java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime())).child("voltage").setValue(message.getText().toString());
    }
}
