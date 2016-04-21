//package ca.ualberta_ecocar.ecocar;
//
//import android.app.Activity;
//import android.app.ProgressDialog;
//import android.content.Intent;
//import android.os.Bundle;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import com.sinch.android.rtc.AudioController;
//import com.sinch.android.rtc.PushPair;
//import com.sinch.android.rtc.SinchError;
//import com.sinch.android.rtc.calling.Call;
//import com.sinch.android.rtc.calling.CallEndCause;
//import com.sinch.android.rtc.calling.CallState;
//import com.sinch.android.rtc.video.VideoCallListener;
//import com.sinch.android.rtc.video.VideoController;
//
///**
// * Created by Harry on 10-Apr-16.
// */
//public class VideoTrialActivity extends Activity implements SinchService.StartFailedListener {
//    private ProgressDialog mSpinner;
//
//    @Override
//    public void onCreate (Bundle savedInstanceState){
//        super.onCreate(savedInstanceState);
//
//        setContentView(R.layout.activity_videotrial);
//    }
//
//    @Override
//    public void onStartFailed(SinchError error) {
//        Toast.makeText(this, error.toString(), Toast.LENGTH_LONG).show();
//        if (mSpinner != null) {
//            mSpinner.dismiss();
//        }
//    }
//
//    @Override
//    public void onStarted() {
//        openPlaceCallActivity();
//    }
//
//    private void loginClicked() {
//        EditText mLoginName = (EditText) findViewById(R.id.loginName);
//        String userName = mLoginName.getText().toString();
//        if (userName.isEmpty()) {
//            Toast.makeText(this, "Please enter a name", Toast.LENGTH_LONG).show();
//            return;
//        }
//
//        if (!getSinchServiceInterface().isStarted()) {
//            getSinchServiceInterface().startClient(userName);
//
//            showSpinner();
//        } else {
//            openPlaceCallActivity();
//        }
//    }
//
//    private void openPlaceCallActivity() {
//        Intent mainActivity = new Intent(this, PlaceCallActivity.class);
//        startActivity(mainActivity);
//    }
//    private void showSpinner() {
//        mSpinner = new ProgressDialog(this);
//        mSpinner.setTitle("Logging in");
//        mSpinner.setMessage("Please wait...");
//        mSpinner.show();
//    }
//}
