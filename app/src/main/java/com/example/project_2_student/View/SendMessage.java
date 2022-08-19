package com.example.project_2_student.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
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
    TextView num_notification , name_tool_bar;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_message);
        initialize();
        clickSendToInstructor();

    }

    public void initialize()
    {
        name_tool_bar = findViewById(R.id.main_toolbar_activity_name_tv) ;
        name_tool_bar.setText(R.string.MAIN_SEND_NOTE_TO_THE_INSTRUCTOR);
        progressBar = findViewById(R.id.progress_send_message);
        textInputEditText_content = findViewById(R.id.send_message_content_text_input_et) ;
        materialButton_send = findViewById(R.id.send_message_send_btn) ;
        drawerLayout = findViewById(R.id.send_message_drawer_layout) ;
        sharedPreferences = getSharedPreferences(LoginActivity.STUDENT_DATA_DB ,MODE_PRIVATE);
        num_notification = findViewById(R.id.num_notification);
        if(!sharedPreferences.getString(LoginActivity.NUM_NOTIFICATION,"").equals("0")){
            num_notification.setVisibility(View.VISIBLE);
            num_notification.setText(sharedPreferences.getString(LoginActivity.NUM_NOTIFICATION,""));
        }

    }

    public void ClickAbsenceWarning (View view)
    {
        MainParent.redirectActivity(this , Absence_Warning.class);
    }
    public void clickSendToInstructor()
    {
        materialButton_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                if(textInputEditText_content.getText().toString().isEmpty()){
                    Toast toast = Toast.makeText(SendMessage.this,"The Field is Empty!!",Toast.LENGTH_LONG);
                    progressBar.setVisibility(View.GONE);
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
                                progressBar.setVisibility(View.GONE);
                                Toast toast = Toast.makeText(SendMessage.this,"The Message is sent !!",Toast.LENGTH_LONG);
                                toast.getView().setBackground(getResources().getDrawable(R.color.Pink));
                                toast.show();
                                textInputEditText_content.getText().clear();
                            }else{
                                try {
                                    progressBar.setVisibility(View.GONE);
                                    Toast.makeText(getApplicationContext(),response.errorBody().string(),Toast.LENGTH_LONG).show();
                                    System.out.println("Error successfully : " + response.errorBody().string());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            progressBar.setVisibility(View.GONE);
                            System.out.println("error is" + t.getMessage());
                            Toast.makeText(getApplicationContext()," No connection ",Toast.LENGTH_LONG).show();

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
        finish();
        MainParent.redirectActivity(this , MainParent.class);

    }//End of ClickHome


    public void ClickAboutUs(View view)
    {
        //Recreate activity
        finish();
        MainParent.redirectActivity(this , AboutUs.class);

    }//End of ClickAboutUs
    public void ClickGallery(View view){
        finish();
        MainParent.redirectActivity(this,Gallery_image.class);
    }
    public void ClickPersonalProfile(View view)
    {
        //Redirect activity to dashboard
        finish();
        MainParent.redirectActivity(this , PersonalProfile.class);

    }//End of ClickDashboard

    public void ClickLogOut(View view)
    {
        //Close app
        MainParent.logout(this);
    }//End of ClickLogout
    public void ClickContactUs(View view){
        finish();
        MainParent.redirectActivity(this,Contact_us.class);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //Close drawer
        MainParent.closeDrawer(drawerLayout);
    }
}