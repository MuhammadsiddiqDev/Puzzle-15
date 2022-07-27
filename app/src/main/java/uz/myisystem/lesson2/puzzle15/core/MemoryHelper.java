package uz.myisystem.lesson2.puzzle15.core;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;

import uz.myisystem.lesson2.puzzle15.R;

public class MemoryHelper
{
    private static MemoryHelper helper;
    private SharedPreferences preferences;
    private MemoryHelper(Context context)
    {
        // SharedPreferences
        preferences = context.getSharedPreferences("puzzle15",Context.MODE_PRIVATE);
    }
    public static MemoryHelper getHelper() {
        return helper;
    }

    public static void init(Context context) {
            if (helper == null) {
                helper = new MemoryHelper(context);
            }
        }
        public void saveData(UserData userData) {

            preferences.edit().putString("name_"+getLastIndex(),userData.getName()).apply();
            preferences.edit().putInt("time_"+getLastIndex(),userData.getTime()).apply();
            preferences.edit().putInt("step_"+getLastIndex(),userData.getStep()).apply();
            saveLastIndex();
        }
        public void saveLastIndex() {
        preferences.edit().putInt("index",getLastIndex()+1).apply();

        }public int getLastIndex() {
            return preferences.getInt("index",0);
        }

        public UserData getData(int index) {
        UserData userData = new UserData(
                preferences.getString("name_"+index,""),
                preferences.getInt("time_"+index,0),
                preferences.getInt("step_"+index,0)
        );
            return userData;
        }

        public ArrayList<UserData> getLastResults() {

        ArrayList<UserData> list = new ArrayList<>();

        int n = getLastIndex();

            for (int i = 0; i < n; i++) {
                list.add(getData(i));
            }
            return list;
        }

    public void clearData() {
        preferences.edit().clear().apply();
    }

    public void setSoundState(boolean sound) {
        preferences.edit().putBoolean("sound_state",sound).apply();
    }
    public boolean getSoundState() {
        return preferences.getBoolean("sound_state",true);
    }
    public void setThemeState(boolean b) {
        preferences.edit().putBoolean("theme_state",b).apply();
    }
    public boolean getThemeState() {
        return preferences.getBoolean("theme_state",false);
    }
    public void setMusicState(boolean music) {preferences.edit().putBoolean("music_state",music).apply();
    }
    public boolean getMusicState() {
        return preferences.getBoolean("music_state",true);
    }
    public void setThemeId(int ThemeId) {
        preferences.edit().putInt("Theme_id",ThemeId).apply();
    }
    public int getThemeId() {
        return preferences.getInt("Theme_id", R.drawable.splashs);
    }
}