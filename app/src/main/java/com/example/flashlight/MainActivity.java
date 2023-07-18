package com.example.flashlight;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ImageView img ;
    Switch aSwitch ;
    TextView tv_result;
    CameraManager cameramanager;
    String cameraid, result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        aSwitch = findViewById(R.id.switch1);
//        tv_result = findViewById(R.id.toggle);

        img= findViewById(R.id.imageView);

        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean status) {
                torch(status);
            }
        });
    }

    private void torch(boolean status) {
        try {
            cameramanager= (CameraManager) getSystemService(CAMERA_SERVICE);
            cameraid = cameramanager.getCameraIdList()[0];

            result = status ? "ON" : "OFF";
            // image on off toggle...
            if(status)  img.setImageDrawable(getDrawable(R.drawable.torch_on));
            else img.setImageDrawable(getDrawable(R.drawable.torch_off));
//            tv_result.setText(result);

            cameramanager.setTorchMode(cameraid,status);
        } catch (CameraAccessException e) {
            throw new RuntimeException(e);
        }
    }
}