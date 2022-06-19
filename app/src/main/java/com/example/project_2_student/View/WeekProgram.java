package com.example.project_2_student.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.project_2_student.R;

import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.model.TableColumnWeightModel;

public class WeekProgram extends AppCompatActivity {
 TableView<String>tableView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        setContentView(R.layout.week_program_test);
    }
    private void init(){
        tableView =(TableView<String>) findViewById(R.id.tableView);
    }
}