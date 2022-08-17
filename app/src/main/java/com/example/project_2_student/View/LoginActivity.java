package com.example.project_2_student.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project_2_student.Constant.CONSTANT;
import com.example.project_2_student.MainActivity;
import com.example.project_2_student.Models.API;
import com.example.project_2_student.Models.DataLogin;
import com.example.project_2_student.Models.Student;
import com.example.project_2_student.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.messaging.FirebaseMessaging;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {


    Button login_btn;
    TextInputEditText email_et,password_et;
    TextView forgotPassword_tv;
    SharedPreferences sharedPreferences;
    public static final String NUM_NOTIFICATION = "num_notification";
    public static final String STUDENT_DATA_DB = "StudentData";
    public static final String FIRST_NAME="first_name";
    public static final String LAST_NAME="last_name";
    public static final String FATHER_NAME="father_name";
    public static final String AGE="age";
    public static final String USER_NAME="user_name";
    public static final String PASSWORD="password";
    public static final String SIGNIN_DATE="signIn_date";
    public static final String BIRTH_DATE="birth_date";
    public static final String ATTEND_NUMBER="attend_number";
    public static final String ABSENCE_NUMBER="absence_number";
    public static final String NAME_CLASS="name_class";
    public static final String NAME_SEC="name_sec";
    public static final String TOKEN="token";
    public static final String EVALUATE = "evaluate";
    public static final String SECTIONID = "sectionId";
    String tokenMessage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();
       // checkLogin();
        clickButtons();



    }//End of onCreate

    private void checkLogin() {
    if(sharedPreferences.getBoolean("isLogin",false))
    {
        finish();
        MainParent.redirectActivity(LoginActivity.this, MainParent.class);
    }

    }


    public void init()
    {
        login_btn=findViewById(R.id.Login_btn);
        email_et=findViewById(R.id.login_ll_1_et_email);
        password_et=findViewById(R.id.login_ll_1_et_2_password);
        sharedPreferences = getSharedPreferences(LoginActivity.STUDENT_DATA_DB,MODE_PRIVATE);
        if(sharedPreferences.getString(NUM_NOTIFICATION,"").isEmpty()){
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(NUM_NOTIFICATION,"0");
            editor.apply();
        }


        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("notification_channel", "notification_channel", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
            @Override
            public void onComplete(@NonNull Task<String> task) {
                if(!task.isSuccessful()){
                    System.err.println("Error Task Message : " + task.getException());
                }else{
                    tokenMessage = task.getResult();
                }
            }
        });
    }//End of init


    public void clickButtons()
    {
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                insertLogin();

            }
        });
    }


    public void insertLogin()
    {
        API api = CONSTANT.CREATING_CALL();
        Call<Student> dataLoginCall = api.loginStudent(new DataLogin(email_et.getText().toString(),password_et.getText().toString(),tokenMessage));
        dataLoginCall.enqueue(new Callback<Student>() {
            @Override
            public void onResponse(Call<Student> call, Response<Student> response) {
                if(response.isSuccessful())
                {
                    saveIntoSharedPrefrences(response.body());
                    finish();
                    MainParent.redirectActivity(LoginActivity.this, MainParent.class);
                }
                else {
                    try {
                        Toast.makeText(getApplicationContext(),response.errorBody().string(),Toast.LENGTH_LONG).show();
                        System.out.println("error successfully"+response.errorBody().string()+ response.code());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<Student> call, Throwable t) {
                Toast.makeText(getApplicationContext()," No Connection ",Toast.LENGTH_LONG).show();
                System.out.println("error "+ t.getMessage());


            }
        });
    }

    private void saveIntoSharedPrefrences(Student student) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(FIRST_NAME,student.getFirst_name());
        editor.putString(LAST_NAME,student.getLast_name());
        editor.putString(FATHER_NAME,student.getFather_name());
        editor.putInt(AGE,student.getAge());
        editor.putString(USER_NAME,student.getUsername());
        editor.putString(PASSWORD,student.getPassword());
        editor.putString(SIGNIN_DATE,student.getSignInDate().toString());
        editor.putString(BIRTH_DATE,student.getBirthDate().toString());
        editor.putInt(ATTEND_NUMBER,student.getAttend_number());
        editor.putInt(ABSENCE_NUMBER,student.getAbsence_number());
        editor.putInt(NAME_CLASS,student.getName_class());
        editor.putInt(NAME_SEC,student.getName_sec());
        editor.putString(TOKEN,student.getToken());
        editor.putInt(EVALUATE,student.getRank());
        editor.putInt(SECTIONID,student.getSectionId());
        editor.putBoolean("isLogin",true);
        editor.apply();
    }

}