package uz.myisystem.lesson2.puzzle15.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import uz.myisystem.lesson2.puzzle15.R;
import uz.myisystem.lesson2.puzzle15.core.BaseActivity;
import uz.myisystem.lesson2.puzzle15.core.MemoryHelper;
import uz.myisystem.lesson2.puzzle15.core.UserData;


public class EndActivity extends BaseActivity {

    private TextView Time;
    private TextView Step;
    private String   name;
    private EditText edit_text;
        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_3);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Time = findViewById(R.id.text_time);
        Step = findViewById(R.id.text_step);
        edit_text = findViewById(R.id.name_input);

        Intent intent = getIntent();

            int time = intent.getIntExtra("time",0);
            int step = intent.getIntExtra("step",0);

            int hour = time /3600;
            int minute = time % 3600 / 60;
            int second = time % 60;

            Time.setText(String.format("Time  %02d:%02d:%02d",hour,minute,second));
            Step.setText(String.format("Steps  %d",step));

            findViewById(R.id.save).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    name = edit_text.getText().toString();

                    if (name.length() == 0) {
                        Toast.makeText(EndActivity.this, "Editor is empty", Toast.LENGTH_SHORT).show();
                    } else {
                        MemoryHelper.getHelper().saveData(new UserData(name, time, step));
                    }
                    finish();
                }
            });
            findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });


        }

    @Override
    public boolean shouldStopMusic() {
        return false;
    }

    @Override
    public void setThemeData() {

    }
}