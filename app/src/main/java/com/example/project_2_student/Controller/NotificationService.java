package com.example.project_2_student.Controller;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.project_2_student.R;
import com.example.project_2_student.View.LoginActivity;
import com.example.project_2_student.View.MainParent;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.io.IOException;


@SuppressLint("MissingFirebaseInstanceTokenRefresh")
public class NotificationService extends FirebaseMessagingService {
SharedPreferences sharedPreferences;
    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        sharedPreferences =getSharedPreferences(LoginActivity.STUDENT_DATA_DB,MODE_PRIVATE);
        super.onMessageReceived(remoteMessage);
        try {
            notify(remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void notify(String title, String message) throws IOException {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        int numNotification = Integer.parseInt(sharedPreferences.getString(LoginActivity.NUM_NOTIFICATION,"0"));
        numNotification++;
        editor.putString(LoginActivity.NUM_NOTIFICATION,String.valueOf(numNotification));
        editor.apply();
        Intent intent = new Intent(getApplicationContext(), MainParent.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //Bitmap bitmap = Picasso.with(this).load(image).get();
        PendingIntent pendingIntent
                = PendingIntent.getActivity(
                getApplicationContext(), 0, intent,
                PendingIntent.FLAG_ONE_SHOT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), "notification_channel")
                .setSmallIcon(R.drawable.icon_school)
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(true)
                .setVibrate(new long[]{1000, 1000, 1000,
                        1000, 1000})
                .setOnlyAlertOnce(true)
                .setContentIntent(pendingIntent);
        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(getApplicationContext());
        managerCompat.notify(123, builder.build());
    }
}
