package ca.ualberta_ecocar.ecocar;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by Harry on 13-Jun-16.
 */
public class LocationActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Context context;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_speed);


        final TextView speedText = (TextView) findViewById(R.id.speedText);
        // Acquire a reference to the system Location Manager
        LocationManager locationManager = (LocationManager) this
                .getSystemService(Context.LOCATION_SERVICE);




        // Define a listener that responds to location updates
        LocationListener locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                location.getLatitude();
                //Toast.makeText(getApplicationContext(), "Current speed:" + location.getSpeed(),Toast.LENGTH_SHORT).show();
                speedText.setText("Current speed:" + Math.round(location.getSpeed()*3.6 * 100.0) / 100.0 +" km/hr");

            }

            public void onStatusChanged(String provider, int status,
                                        Bundle extras) {
            }

            public void onProviderEnabled(String provider) {

            }

            public void onProviderDisabled(String provider) {
            }
        };

            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);

    }

}
