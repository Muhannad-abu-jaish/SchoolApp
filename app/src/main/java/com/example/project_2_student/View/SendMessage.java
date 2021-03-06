package com.example.project_2_student.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Toast;

import com.example.project_2_student.Constant.CONSTANT;
import com.example.project_2_student.Models.API;
import com.example.project_2_student.Models.NoteTo;
import com.example.project_2_student.Models.Student;
import com.example.project_2_student.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SendMessage extends AppCompatActivity {


    TextInputEditText textInputEditText_content ;
    MaterialButton materialButton_send ;
    DrawerLayout drawerLayout ;
    SharedPreferences sharedPreferences ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_message);
        initialize();
        clickSendToInstructor();

    }

    public void initialize()
    {
        textInputEditText_content = findViewById(R.id.send_message_content_text_input_et) ;
        materialButton_send = findViewById(R.id.send_message_send_btn) ;
        drawerLayout = findViewById(R.id.send_message_drawer_layout) ;
        sharedPreferences = getSharedPreferences("StudentData" ,MODE_PRIVATE);

    }

    public void clickSendToInstructor()
    {
        materialButton_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(textInputEditText_content.getText().toString().isEmpty()){
                    Toast toast = Toast.makeText(SendMessage.this,"The Field is Empty!!",Toast.LENGTH_LONG);
                    toast.getView().setBackground(getResources().getDrawable(R.color.Pink));
                    toast.show();
                }

                else {
                    String studentToken = sharedPreferences.getString(LoginActivity.TOKEN , null) ;
                    API api = CONSTANT.CREATING_CALL();
                    NoteTo noteToInstructor = new NoteTo(textInputEditText_content.getText().toString());
                    Call<ResponseBody> responseBodyCall = api.SEND_MESSAGE_TO_INSTRUCTOR(studentToken,noteToInstructor) ;
                    responseBodyCall.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                            if(response.isSuccessful()){
                                Toast toast = Toast.makeText(SendMessage.this,"The Message is sent !!",Toast.LENGTH_LONG);
                                toast.getView().setBackground(getResources().getDrawable(R.color.Pink));
                                toast.show();
                                textInputEditText_content.getText().clear();

                            }else{
                                try {
                                    System.out.println("Error successfully : " + response.errorBody().string());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            System.out.println("error is" + t.getMessage());

                        }
                    });
                }
            }
        });
    }


    public void ClickMenu(View view)
    {
        //Open drawer
        MainParent.openDrawer(drawerLayout);
    }//End of ClickMenu


    public void ClickLogo(View view)
    {
        //Close drawer
        MainParent.closeDrawer(drawerLayout);
    }//end of ClickLogo

    public void ClickHome(View view)
    {
        //Redirect activity to home
        MainParent.redirectActivity(this , MainParent.class);
        this.finish();
    }//End of ClickHome


    public void ClickAboutUs(View view)
    {
        //Recreate activity
        MainParent.redirectActivity(this , AboutUs.class);
        this.finish();
    }//End of ClickAboutUs

    public void ClickPersonalProfile(View view)
    {
        //Redirect activity to dashboard
        MainParent.redirectActivity(this , PersonalProfile.class);
        this.finish();
    }//End of ClickDashboard

    public void ClickLogOut(View view)
    {
        //Close app
        MainParent.logout(this);
    }//End of ClickLogout


    @Override
    protected void onPause() {
        super.onPause();
        //Close drawer
        MainParent.closeDrawer(drawerLayout);
    }
}