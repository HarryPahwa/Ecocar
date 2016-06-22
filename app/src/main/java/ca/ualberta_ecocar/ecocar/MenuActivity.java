package ca.ualberta_ecocar.ecocar;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Harry on 22-Mar-16.
 */
public class MenuActivity extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_stats);//activity_menu);

    }

    public void change2(View view){

        StatsListFragment txt = (StatsListFragment) getFragmentManager().findFragmentById(R.id.fragment);
        txt.change(new String[] {"1","1233","1","0","0","0","0","62","0","0","0","0","0","0","0","0","0","0","0","0","0"});
    }



}
