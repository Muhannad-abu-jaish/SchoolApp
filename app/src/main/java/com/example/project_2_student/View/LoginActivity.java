package com.example.project_2_student.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.project_2_student.Constant.CONSTANT;
import com.example.project_2_student.MainActivity;
import com.example.project_2_student.Models.API;
import com.example.project_2_student.Models.DataLogin;
import com.example.project_2_student.Models.Student;
import com.example.project_2_student.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {


    Button login_btn;
    TextInputEditText email_et,password_et;
    TextView forgotPassword_tv;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();
        clickButtons();



    }//End of onCreate


    public void init()
    {
        login_btn=findViewById(R.id.Login_btn);
        email_et=findViewById(R.id.login_ll_1_et_email);
        password_et=findViewById(R.id.login_ll_1_et_2_password);
        forgotPassword_tv=findViewById(R.id.login_ll_1_tv_forgot_password);
        sharedPreferences = getSharedPreferences("StudentData",MODE_PRIVATE);
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
        Call<Student> dataLoginCall = api.loginStudent(new DataLogin(email_et.getText().toString(),password_et.getText().toString()));
        dataLoginCall.enqueue(new Callback<Student>() {
            @Override
            public void onResponse(Call<Student> call, Response<Student> response) {

                if(response.isSuccessful())
                {
                    saveIntoSharedPrefrences(response.body());
                    MainParent.redirectActivity(LoginActivity.this, MainParent.class);
                }
                else {
                    try {
                        System.out.println("error successfully"+response.errorBody().string()+ response.code());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<Student> call, Throwable t) {

                System.out.println("error "+ t.getMessage());


            }
        });
    }

    private void saveIntoSharedPrefrences(Student student) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("first_name",student.getFirst_name());
        editor.putString("last_name",student.getLast_name());
        editor.putString("father_name",student.getFather_name());
        editor.putInt("age",student.getAge());
        editor.putString("user_name",student.getUsername());
        editor.putString("password",student.getPassword());
        editor.putString("signIn_date",student.getSignInDate().toString());
        editor.putString("birth_date",student.getBirthDate().toString());
        editor.putInt("attend_number",student.getAttend_number());
        editor.putInt("absence_number",student.getAbsence_number());
        editor.putInt("name_class",student.getName_class());
        editor.putInt("name_sec",student.getName_sec());
        System.out.println("Name Class :"+ student.getName_class()+"\n"+"Name Sec :" +student.getName_sec() );
        editor.putString("token",student.getToken());

    }
}