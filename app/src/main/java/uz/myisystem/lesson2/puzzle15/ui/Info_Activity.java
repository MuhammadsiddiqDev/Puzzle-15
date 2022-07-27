package uz.myisystem.lesson2.puzzle15.ui;

import android.os.Bundle;


import uz.myisystem.lesson2.puzzle15.R;
import uz.myisystem.lesson2.puzzle15.core.BaseActivity;


public class Info_Activity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

    }

    @Override
    public boolean shouldStopMusic() {
        return false;
    }

    @Override
    public void setThemeData() {

    }
}