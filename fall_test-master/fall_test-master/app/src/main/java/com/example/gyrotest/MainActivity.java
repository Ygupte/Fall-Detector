package com.example.gyrotest;

import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements SensorEventListener{

    private TextView x_text,y_text,z_text,r_text,thresh_text,x_rot,y_rot,z_rot;
    private Sensor lin_acc ;
    private SensorManager sm;

    @Override
    public void onSensorChanged(SensorEvent event) {

            float x_acc = event.values[0];
            float y_acc = event.values[1];
            float z_acc = event.values[2];
            x_text.setText("X= " + x_acc);
            y_text.setText("Y= " + y_acc);
            z_text.setText("Z= " + z_acc);
            float min = 0.0f;
            float res_acc = (float) Math.sqrt(Math.pow(x_acc, 2) + Math.pow(y_acc, 2) + Math.pow(z_acc, 2));
            r_text.setText("Resultant= " + res_acc);
            if (res_acc > 2.4) {
                thresh_text.setText("Threshold reached");

            } else {
                thresh_text.setText("");
            }


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        //No need
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Create sensor manager
        sm=(SensorManager)getSystemService(SENSOR_SERVICE);

        //Accelerometer sensor
        lin_acc=sm.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
        //Rotation angle sensor
        //rot_vec=sm.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);

        //Register sensor listener
        sm.registerListener(this,lin_acc,SensorManager.SENSOR_DELAY_NORMAL);
        //sm.registerListener(this,rot_vec,SensorManager.SENSOR_DELAY_NORMAL);

        //Assign textview
        x_text=(TextView)findViewById(R.id.x_gravity);
        y_text=(TextView)findViewById(R.id.y_gravity);
        z_text=(TextView)findViewById(R.id.z_gravity);
        r_text=(TextView)findViewById(R.id.result_text);
        thresh_text=(TextView)findViewById(R.id.thresh_disp);
        x_rot=(TextView)findViewById(R.id.x_ang);
        y_rot=(TextView)findViewById(R.id.y_ang);
        z_rot=(TextView)findViewById(R.id.z_ang);
    }
}
