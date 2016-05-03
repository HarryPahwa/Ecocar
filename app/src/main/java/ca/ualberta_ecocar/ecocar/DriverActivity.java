package ca.ualberta_ecocar.ecocar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
public class DriverActivity extends Activity {
    private Call call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_call_driver);

        final TextView callStatus = (TextView) findViewById(R.id.callStatus);

        final SinchClient sinchClient = Sinch.getSinchClientBuilder()
                .context(getApplicationContext())
                .userId("Ecocar-Driver")
                .applicationKey("d64cf6cf-62c3-4620-bf9d-70c95cb0fe55")
                .applicationSecret("HyFot8NHd0Ws4uWhPPSAbw==")
                .environmentHost("sandbox.sinch.com")
                .build();

        sinchClient.setSupportCalling(true);
        sinchClient.startListeningOnActiveConnection();

        sinchClient.getCallClient().addCallClientListener(new SinchCallClientListener());
        sinchClient.start();

    }



    private class SinchCallClientListener implements CallClientListener {
        final TextView callStatus = (TextView) findViewById(R.id.callStatus);

        @Override
        public void onIncomingCall(CallClient callClient, Call incomingCall) {
            //Pick up the call!
            call=incomingCall;
            call.answer();;
            callStatus.setText("Team is trying to contact");
            call.addCallListener(new SinchCallListener());
            callStatus.setText("Connected to team from onIncomingCall");
        }
    }

    private class SinchCallListener implements CallListener {
        final Button button = (Button) findViewById(R.id.callButton);
        final TextView callStatus = (TextView) findViewById(R.id.callStatus);

        @Override
        public void onCallEnded(Call endedCall) {
            //call ended by either party
            call=null;
            callStatus.setText("Call ended");
//            setVolumeControlStream(AudioManager.USE_DEFAULT_STREAM_TYPE);

        }
        @Override
        public void onCallEstablished(Call establishedCall) {
            //incoming call was picked up
            callStatus.setText("Connected to team");
//            setVolumeControlStream(AudioManager.STREAM_VOICE_CALL);
        }
        @Override
        public void onCallProgressing(Call progressingCall) {
            //call is ringing
            callStatus.setText("Ringing");
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
