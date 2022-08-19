package com.example.project_2_student.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.TextView;

import com.example.project_2_student.Constant.CONSTANT;
import com.example.project_2_student.Models.API;
import com.example.project_2_student.Models.Limpidityie;
import com.example.project_2_student.R;
import com.github.barteksc.pdfviewer.PDFView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FinalResult extends AppCompatActivity {
  PDFView pdfView;
  String token;
  SharedPreferences sharedPreferences;
  TextView name_tool_bar ;
    String pdfurl = "https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf";

  ProgressBar progressBar;
  Button Retry;
  View noConnection;
  DrawerLayout drawerLayout ;
  @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_result);

        init();
        getData();
        RetryConnection();
    }
    private void RetryConnection(){
        Retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                noConnection.setVisibility(View.GONE);
                getData();
            }
        });
    }
    private void getData() {
        API api = CONSTANT.CREATING_CALL();
        Call<Limpidityie> limpidityieCall = api.getLimpidityie(token);
        limpidityieCall.enqueue(new Callback<Limpidityie>() {
            @Override
            public void onResponse(Call<Limpidityie> call, Response<Limpidityie> response) {
                if(response.isSuccessful()){
                    progressBar.setVisibility(View.GONE);
                    if(response.body()!=null){
                        noConnection.setVisibility(View.VISIBLE);
                    }
                    else
                    {
                        pdfView.setVisibility(View.VISIBLE);
                        new RetrivePDFfromUrl().execute(response.body().getLimpidityies());
                    }
                }
                else{
                    progressBar.setVisibility(View.GONE);
                    try {
                        Toast.makeText(getApplicationContext(),response.errorBody().string(),Toast.LENGTH_LONG).show();
                        System.out.println("Error Successfully : " + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<Limpidityie> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                System.out.println("Error : " +  t.getMessage());
                pdfView.setVisibility(View.GONE);
                noConnection.setVisibility(View.VISIBLE);

            }
        });
    }

    private void init(){
       // pdfView = findViewById(R.id.idPDFView);
        name_tool_bar = findViewById(R.id.main_toolbar_activity_name_tv) ;
        name_tool_bar.setText(R.string.MAIN_FINAL_RESULT);

        drawerLayout = findViewById(R.id.final_result_drawer) ;
        progressBar = findViewById(R.id.progress_final_result);
        Retry = findViewById(R.id.retry_connection);
        noConnection = findViewById(R.id.view_NoConnection);
        pdfView = findViewById(R.id.idPDFView);
        sharedPreferences = getSharedPreferences(LoginActivity.STUDENT_DATA_DB,MODE_PRIVATE);
        token = sharedPreferences.getString(LoginActivity.TOKEN,"");
    }
    class RetrivePDFfromUrl extends AsyncTask<String, Void, InputStream> {
        @Override
        protected InputStream doInBackground(String... strings) {
            // we are using inputstream
            // for getting out PDF.
            System.out.println("RESULT = " + strings[0]);
            InputStream inputStream = null;
            try {
                URL url = new URL(strings[0]);
                // below is the step where we are
                // creating our connection.
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                if (urlConnection.getResponseCode() == 200) {
                    // response is success.
                    // we are getting input stream from url
                    // and storing it in our variable.
                    inputStream = new BufferedInputStream(urlConnection.getInputStream());
                }

            } catch (IOException e) {
                // this is the method
                // to handle errors.
                e.printStackTrace();
                return null;
            }
            return inputStream;
        }

        @Override
        protected void onPostExecute(InputStream inputStream) {
            // after the execution of our async
            // task we are loading our pdf in our pdf view.
            pdfView.fromStream(inputStream).load();
        }
    }


    public void ClickAbsenceWarning(View view)
    {
        finish();
        MainParent.redirectActivity(this , Absence_Warning.class);
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
    public void ClickContactUs(View view){
        finish();
      MainParent.redirectActivity(this,Contact_us.class);
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


    @Override
    protected void onPause() {
        MainParent.closeDrawer(drawerLayout);
        super.onPause();
        //Close drawer
    }

}