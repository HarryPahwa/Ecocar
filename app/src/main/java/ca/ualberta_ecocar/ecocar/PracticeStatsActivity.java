package ca.ualberta_ecocar.ecocar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Harry on 22-Mar-16.
 */
public class PracticeStatsActivity extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_stats);
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        //DatabaseReference myRef=database.getReference("message/newText");
        //DatabaseReference emergencyStatus = database.getReference("EmergencyStatus");
        DatabaseReference fuelCellReadings=database.getReference("readings");



        fuelCellReadings.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String value = dataSnapshot.child("data").getValue(String.class);
                StatsListFragment statFragment = (StatsListFragment) getFragmentManager().findFragmentById(R.id.fragment);
                statFragment.change(value.split(","));
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


        DatabaseReference aliceSpeedReadings=database.getReference("AliceSpeed");

        aliceSpeedReadings.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String speed = dataSnapshot.child("Speed").getValue(String.class);
                TextView speedView=(TextView)findViewById(R.id.speedText);
                speedView.setText(speed.toString());
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
    }

    public void backButtonClick(View view){
//        call=null;
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


}
