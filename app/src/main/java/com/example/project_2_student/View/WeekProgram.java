package com.example.project_2_student.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project_2_student.Constant.CONSTANT;
import com.example.project_2_student.Models.API;
import com.example.project_2_student.Models.Program;
import com.example.project_2_student.R;

import java.io.IOException;
import java.util.ArrayList;

import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.model.TableColumnWeightModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeekProgram extends AppCompatActivity {
    Button sunday_1 , sunday_2,sunday_3,sunday_4,sunday_5,sunday_6,sunday_7;
    Button monday_1 , monday_2,monday_3,monday_4,monday_5,monday_6,monday_7;
    Button tuesday_1 , tuesday_2,tuesday_3,tuesday_4,tuesday_5,tuesday_6,tuesday_7;
    Button wednesday_1, wednesday_2,wednesday_3,wednesday_4,wednesday_5,wednesday_6,wednesday_7;
    Button thursday_1 , thursday_2,thursday_3,thursday_4,thursday_5,thursday_6,thursday_7;
    int sectionId;
    SharedPreferences sharedPreferences;
    TextView name_tool_bar ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.week_program_test);
        init();
        getDataWeekProgram();
    }

    private void getDataWeekProgram() {

        API api = CONSTANT.CREATING_CALL();
        Call<ArrayList<Program>> programs = api.show_week_program(sectionId);
        programs.enqueue(new Callback<ArrayList<Program>>() {
            @Override
            public void onResponse(Call<ArrayList<Program>> call, Response<ArrayList<Program>> response) {
              if(response.isSuccessful()){
                  generateWeekProgram(response.body());
              }else{
                  try {
                      Toast.makeText(getApplicationContext(),response.errorBody().string(),Toast.LENGTH_LONG).show();
                      System.err.println("Error successfully : " + response.errorBody().string());
                  } catch (IOException e) {
                      e.printStackTrace();
                  }
              }
            }
            @Override
            public void onFailure(Call<ArrayList<Program>> call, Throwable t) {
                System.err.println("Error : "+ t.getMessage());
                Toast.makeText(getApplicationContext()," NO Connection ",Toast.LENGTH_LONG).show();
            }
        });
    }

    private void generateWeekProgram(ArrayList<Program>program) {
        for(int i = 0 ;i  <program.size();i++){
            if(program.get(i).getDay().equals("Sunday")){
                sunday_1.setText(""+program.get(i).getFirst());
                sunday_2.setText(""+program.get(i).getSecond());
                sunday_3.setText(""+program.get(i).getThird());
                sunday_4.setText(""+program.get(i).getForth());
                sunday_5.setText(""+program.get(i).getFifth());
                sunday_6.setText(""+program.get(i).getSixth());
                sunday_7.setText(""+program.get(i).getSeventh());
            }else if (program.get(i).getDay().equals("Monday")){
                monday_1.setText(""+program.get(i).getFirst());
                monday_2.setText(""+program.get(i).getSecond());
                monday_3.setText(""+program.get(i).getThird());
                monday_4.setText(""+program.get(i).getForth());
                monday_5.setText(""+program.get(i).getFifth());
                monday_6.setText(""+program.get(i).getSixth());
                monday_7.setText(""+program.get(i).getSeventh());
            }else if(program.get(i).getDay().equals("Tuesday")){
                tuesday_1.setText(""+program.get(i).getFirst());
                tuesday_2.setText(""+program.get(i).getSecond());
                tuesday_3.setText(""+program.get(i).getThird());
                tuesday_4.setText(""+program.get(i).getForth());
                tuesday_5.setText(""+program.get(i).getFifth());
                tuesday_6.setText(""+program.get(i).getSixth());
                tuesday_7.setText(""+program.get(i).getSeventh());
            }else if (program.get(i).getDay().equals("Wednesday")){
                wednesday_1.setText(""+program.get(i).getFirst());
                wednesday_2.setText(""+program.get(i).getSecond());
                wednesday_3.setText(""+program.get(i).getThird());
                wednesday_4.setText(""+program.get(i).getForth());
                wednesday_5.setText(""+program.get(i).getFifth());
                wednesday_6.setText(""+program.get(i).getSixth());
                wednesday_7.setText(""+program.get(i).getSeventh());
            }else if (program.get(i).getDay().equals("Thursday")){
                thursday_1.setText(""+program.get(i).getFirst());
                thursday_2.setText(""+program.get(i).getSecond());
                thursday_3.setText(""+program.get(i).getThird());
                thursday_4.setText(""+program.get(i).getForth());
                thursday_5.setText(""+program.get(i).getFifth());
                thursday_6.setText(""+program.get(i).getSixth());
                thursday_7.setText(""+program.get(i).getSeventh());
            }
          }
    }

    private void init(){
       /* name_tool_bar = findViewById(R.id.main_toolbar_activity_name_tv) ;
        name_tool_bar.setText(" "+R.string.WEEK_PROGRAM);*/
        sharedPreferences = getSharedPreferences(LoginActivity.STUDENT_DATA_DB,MODE_PRIVATE);
        sectionId = sharedPreferences.getInt(LoginActivity.SECTIONID,-1);
        sunday_1 = findViewById(R.id.sunday_1);
        sunday_2 = findViewById(R.id.sunday_2);
        sunday_3 = findViewById(R.id.sunday_3);
        sunday_4 = findViewById(R.id.sunday_4);
        sunday_5 = findViewById(R.id.sunday_5);
        sunday_6 = findViewById(R.id.sunday_6);
        sunday_7 = findViewById(R.id.sunday_7);
        monday_1 = findViewById(R.id.monday_1);
        monday_2 = findViewById(R.id.monday_2);
        monday_3 = findViewById(R.id.monday_3);
        monday_4 = findViewById(R.id.monday_4);
        monday_5 = findViewById(R.id.monday_5);
        monday_6 = findViewById(R.id.monday_6);
        monday_7 = findViewById(R.id.monday_7);
        tuesday_1 = findViewById(R.id.tuesday_1);
        tuesday_2 = findViewById(R.id.tuesday_2);
        tuesday_3 = findViewById(R.id.tuesday_3);
        tuesday_4 = findViewById(R.id.tuesday_4);
        tuesday_5 = findViewById(R.id.tuesday_5);
        tuesday_6 = findViewById(R.id.tuesday_6);
        tuesday_7 = findViewById(R.id.tuesday_7);
        wednesday_1 = findViewById(R.id.wednesday_1);
        wednesday_2 = findViewById(R.id.wednesday_2);
        wednesday_3 = findViewById(R.id.wednesday_3);
        wednesday_4 = findViewById(R.id.wednesday_4);
        wednesday_5 = findViewById(R.id.wednesday_5);
        wednesday_6 = findViewById(R.id.wednesday_6);
        wednesday_7 = findViewById(R.id.wednesday_7);
        thursday_1 = findViewById(R.id.thursday_1);
        thursday_2 = findViewById(R.id.thursday_2);
        thursday_3 = findViewById(R.id.thursday_3);
        thursday_4 = findViewById(R.id.thursday_4);
        thursday_5 = findViewById(R.id.thursday_5);
        thursday_6 = findViewById(R.id.thursday_6);
        thursday_7 = findViewById(R.id.thursday_7);
    }
}