package com.example.mbhatia4336.simplecare;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.provider.AlarmClock;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SendData {
    private ArrayAdapter<String> listAdapter;
    SendData sendData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sendData = this;
        AlarmManager alarmMgr = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlarmReceiver.class);
        PendingIntent alarmIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
// set for 30 seconds later
        alarmMgr.set(AlarmManager.RTC, Calendar.getInstance().getTimeInMillis() + 5000, alarmIntent);
//        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
//        mBuilder.setSmallIcon(R.drawable.ic_stat_name);
//        mBuilder.setContentTitle("Notification Alert, Click Me!");
//        mBuilder.setContentText("Hi, This is Android Notification Detail!");
//        NotificationManager mNotifyMgr =
//                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//// Builds the notification and issues it.
//        mNotifyMgr.notify(1, mBuilder.build());
        //pushSearchToActivity(list, tasks);
        //new HTTPRequestTask().execute("http://192.168.43.53:8080/tasks");
        //String hello  = "hello";

    }

    public void pushSearchToActivity(Task [] tasks) {
        //L fragment = (WordList) getFragmentManager().findFragmentById(R.id.list);
            ListView list = (ListView) findViewById(android.R.id.list);
            CustomArrayAdapter adapter = new CustomArrayAdapter(this, tasks);
            list.setAdapter(adapter);
    }


    private class HTTPRequestTask extends AsyncTask<String, String, Task[]> {
        @Override
        protected Task [] doInBackground(String... params) {
            Task [] tasks = null;
            try {

                final String url = params[0];
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                tasks = restTemplate.getForObject(url, Task[].class);
                //pushSearchToActivity(getApplicationContext(), list, tasks);
                //sendData.pushSearchToActivity(tasks);
            } catch (Exception e) {

                Log.e("MainActivity", e.getMessage(), e);
            }
            return tasks;
        }
        @Override
        protected void onPostExecute(Task [] tasks ) {
            sendData.pushSearchToActivity(tasks);

        }
    }
}
