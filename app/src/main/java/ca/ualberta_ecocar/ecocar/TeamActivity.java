package ca.ualberta_ecocar.ecocar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.sinch.android.rtc.PushPair;
import com.sinch.android.rtc.Sinch;
import com.sinch.android.rtc.SinchClient;
import com.sinch.android.rtc.calling.Call;
import com.sinch.android.rtc.calling.CallClient;
import com.sinch.android.rtc.calling.CallClientListener;
import com.sinch.android.rtc.calling.CallListener;

import java.util.List;

/**
 * Created by Harry on 24-Mar-16.
 */
public class TeamActivity extends Activity {
    private Call call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_call_team);

        final Button button = (Button) findViewById(R.id.callButton);
        //final TextView callState = (TextView) findViewById(R.id.callState);

        final SinchClient sinchClient = Sinch.getSinchClientBuilder()
                .context(getApplicationContext())
                .userId("Ecocar-Team")
                .applicationKey("d64cf6cf-62c3-4620-bf9d-70c95cb0fe55")
                .applicationSecret("HyFot8NHd0Ws4uWhPPSAbw==")
                .environmentHost("sandbox.sinch.com")
                .build();

        sinchClient.setSupportCalling(true);
        sinchClient.startListeningOnActiveConnection();

        sinchClient.getCallClient().addCallClientListener(new SinchCallClientListener());
        sinchClient.start();


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(call==null){
                    call=sinchClient.getCallClient().callUser("Ecocar-Driver");
                    call.addCallListener(new SinchCallListener());
                    Toast toast = Toast.makeText(getApplicationContext(),"Contacting the Driver",Toast.LENGTH_SHORT);
                    toast.show();
                    button.setText("End-Call");
                } else {
                    call.hangup();
                    call=null;
                    button.setText("Call again?");
                }
            }



        });
    }


    private class SinchCallClientListener implements CallClientListener {
        final Button button = (Button) findViewById(R.id.callButton);

        @Override
        public void onIncomingCall(CallClient callClient, Call incomingCall) {
            //Pick up the call!
//            call=incomingCall;
//            call.answer();;
//            call.addCallListener(new SinchCallListener());
//            button.setText("Hang Up");
        }
    }

    private class SinchCallListener implements CallListener {
        final Button button = (Button) findViewById(R.id.callButton);
        //final TextView callState = (TextView) findViewById(R.id.callState);

        @Override
        public void onCallEnded(Call endedCall) {
            //call ended by either party
            call=null;
            button.setText("Call again?");
//            setVolumeControlStream(AudioManager.USE_DEFAULT_STREAM_TYPE);

        }
        @Override
        public void onCallEstablished(Call establishedCall) {
            //incoming call was picked up
            button.setText("Connected to driver");
//            setVolumeControlStream(AudioManager.STREAM_VOICE_CALL);
        }
        @Override
        public void onCallProgressing(Call progressingCall) {
            //call is ringing
            button.setText("Ringing");
        }
        @Override
        public void onShouldSendPushNotification(Call call, List<PushPair> pushPairs) {
            //don't worry about this right now
        }
    }

    public void backButtonClick(View view){
        call=null;
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

}
