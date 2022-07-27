package uz.myisystem.lesson2.puzzle15.ui;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import uz.myisystem.lesson2.puzzle15.R;
import uz.myisystem.lesson2.puzzle15.core.App;
import uz.myisystem.lesson2.puzzle15.core.BaseActivity;
import uz.myisystem.lesson2.puzzle15.core.MemoryHelper;

public class SettingActivity extends BaseActivity
{

    private LinearLayout linearLayout;
    private Switch soundSwitch,musicSwitch;
    private Button clearButton;
    private RadioButton theme1,theme2,theme3,theme4,theme5;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        soundSwitch = findViewById(R.id.sound_switch);
        musicSwitch = findViewById(R.id.music_switch);
        theme1 = findViewById(R.id.theme1);
        theme2 = findViewById(R.id.theme2);
        theme3 = findViewById(R.id.theme3);
        theme4 = findViewById(R.id.theme4);
        theme5 = findViewById(R.id.theme5);
        clearButton = findViewById(R.id.clear_data);

        linearLayout = findViewById(R.id.root);

        setLastData();
        setListeners();
    }

    private void setListeners() {
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SettingActivity.this);

                builder.setTitle("\n" +
                        "\n" +
                        "Clear data!!!\n" ).setMessage("Do you clear the data\n\nAre you sure!!");
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        MemoryHelper.getHelper().clearData();
                        Toast.makeText(SettingActivity.this, "Data cleared", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                builder.create().show();
            }
        });
        soundSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                MemoryHelper.getHelper().setSoundState(b);
                setLastData();
            }
        });
        musicSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                MemoryHelper.getHelper().setMusicState(b);
                if (b) {
                    App.startMusic();
                } else {
                    App.stopMusic();
                }
                setLastData();
            }
        });
        theme1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MemoryHelper.getHelper().setThemeId(R.drawable.baba);
                setThemeData();
            }
        });        theme2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MemoryHelper.getHelper().setThemeId(R.drawable.cool);
                setThemeData();

            }
        });        theme3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MemoryHelper.getHelper().setThemeId(R.drawable.splashs);
                setThemeData();

            }
        });        theme4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MemoryHelper.getHelper().setThemeId(R.drawable.wode);
                setThemeData();

            }
        });      theme5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MemoryHelper.getHelper().setThemeId(R.drawable.leaves);
                setThemeData();

            }
        });
    }
    private void setLastData() {
        boolean isSound = MemoryHelper.getHelper().getSoundState();
        soundSwitch.setChecked(isSound);
        if (!isSound) {
            soundSwitch.setText(getString(R.string.Sound_on));
        } else {
            soundSwitch.setText(getString(R.string.Sound_off));
        }
        boolean isMusic = MemoryHelper.getHelper().getMusicState();
        musicSwitch.setChecked(isMusic);
        if (!isMusic) {
            musicSwitch.setText(getString(R.string.Music_on));
        } else {
            musicSwitch.setText(getString(R.string.Music_off));
        }
    }

    @Override
    public boolean shouldStopMusic() {
        return false;
    }

    @Override
    public void setThemeData() {
        linearLayout.setBackgroundResource(MemoryHelper.getHelper().getThemeId());
    }

}