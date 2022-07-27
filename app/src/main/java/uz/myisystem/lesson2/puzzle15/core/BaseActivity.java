package uz.myisystem.lesson2.puzzle15.core;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HomeWatcher mHomeWatcher = new HomeWatcher(this);
        mHomeWatcher.setOnHomePressedListener(new OnHomePressedListener() {
            @Override
            public void onHomePressed() {
                App.stopMusic();
            }

            @Override
            public void onHomeLongPressed() {
            }
        });
        mHomeWatcher.startWatch();
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
    }

    @Override
    protected void onPause() {

        super.onPause();
    }
    @Override
    protected void onStop() {
        if (shouldStopMusic())
            App.stopMusic();
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setThemeData();
        if (MemoryHelper.getHelper().getMusicState()) {
            App.startMusic();
        }else {
            App.stopMusic();
        }
    }

    public abstract boolean shouldStopMusic();

    public abstract void setThemeData();

}
