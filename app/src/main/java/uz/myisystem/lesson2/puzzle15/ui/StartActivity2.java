package uz.myisystem.lesson2.puzzle15.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AlertDialog;

import uz.myisystem.lesson2.puzzle15.R;
import uz.myisystem.lesson2.puzzle15.core.App;
import uz.myisystem.lesson2.puzzle15.core.BaseActivity;
import uz.myisystem.lesson2.puzzle15.core.MemoryHelper;

public class StartActivity2 extends BaseActivity implements View.OnClickListener {

    private Button startButton,resultButton,settingButton,exitButton,InfoButton;
    private RelativeLayout relativeLayout;
    MainActivity mainActivity = new MainActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_1);
        loadView();
        startButton.setOnClickListener(this);
        resultButton.setOnClickListener(this);
        settingButton.setOnClickListener(this);
        exitButton.setOnClickListener(this);
        InfoButton.setOnClickListener(this);
    }
    private void loadView() {
        InfoButton = findViewById(R.id.info_button);
        relativeLayout = findViewById(R.id.content);
        startButton = findViewById(R.id.start_button);
        resultButton = findViewById(R.id.result_button);
        settingButton = findViewById(R.id.setting_button);
        exitButton = findViewById(R.id.exit_button);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.start_button: {

                Intent intent = new Intent(StartActivity2.this, MainActivity.class);
                intent.putExtra("time", mainActivity.time);
                intent.putExtra("step", mainActivity.step);

                startActivity(intent);
                break;
            }
            case R.id.result_button: {
                Intent intent = new Intent(StartActivity2.this, ResultActivity.class);

                startActivity(intent);
                break;
            }
            case R.id.setting_button: {
                Intent intent = new Intent(StartActivity2.this, SettingActivity.class);


                startActivity(intent);
                break;
            }
            case R.id.info_button: {
                Intent intent = new Intent(StartActivity2.this, Info_Activity.class);

                startActivity(intent);
                break;
            }
            case R.id.exit_button: {
                AlertDialog.Builder builder = new AlertDialog.Builder(StartActivity2.this);

                builder.setTitle("\n" +
                        "\n" +
                        "Exit game!!!\n" ).setMessage("Do you want to exit the program\n\nAre you sure!!");
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                        App.stopMusic();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                builder.create().show();
            }
                break;
            }
        }
    @Override
    public void setThemeData() {
        relativeLayout.setBackgroundResource(MemoryHelper.getHelper().getThemeId());
    }
    @Override
    public boolean shouldStopMusic() {
        return false;
    }
}