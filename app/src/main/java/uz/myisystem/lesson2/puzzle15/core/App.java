package uz.myisystem.lesson2.puzzle15.core;

import android.app.Application;
import android.content.Context;
import android.media.MediaPlayer;

import uz.myisystem.lesson2.puzzle15.R;


public class App extends Application {
    private static MediaPlayer musicPlayer;
    private static Context appContext;

    public static void startMusic() {

        if (musicPlayer == null) {
            musicPlayer = MediaPlayer.create(appContext, R.raw.mon);
            musicPlayer.setLooping(true);
        }
        if (musicPlayer != null) {
            musicPlayer.start();
        }
    }

    public static void stopMusic() {
        if (musicPlayer != null) {
            musicPlayer.stop();
            musicPlayer.release();
            musicPlayer = null;
        }
    }

    @Override
    public void onCreate() {
        appContext = this;
        super.onCreate();
        MemoryHelper.init(this);
        if (MemoryHelper.getHelper().getMusicState())
            startMusic();
    }

    @Override
    public void onTerminate() {
        startMusic();
        musicPlayer = null;
        super.onTerminate();
    }
}

