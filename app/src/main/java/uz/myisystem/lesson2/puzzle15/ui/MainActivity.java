package uz.myisystem.lesson2.puzzle15.ui;

import androidx.annotation.NonNull;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;


import uz.myisystem.lesson2.puzzle15.R;
import uz.myisystem.lesson2.puzzle15.core.BaseActivity;
import uz.myisystem.lesson2.puzzle15.core.MemoryHelper;


public class MainActivity extends BaseActivity
{
    private ArrayList<Integer> numbers = new ArrayList<>();

    private MediaPlayer mediaPlayer;
    private TextView timeView;
    private TextView stepView;
    private Timer timer;
    private Button[][] buttons = new Button[4][4];
    private RelativeLayout buttonGroup;
    private Button pauseButton;
    private Button restartButton;
    private FrameLayout frameLayout,layout;
    private int emptyI = 3;
    private int emptyJ = 3;
    public  int step = 0;
    public  int time = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_4);

        loadView();

        if (savedInstanceState != null)
        {
            loadLastData(savedInstanceState);
        } else {
            loadNumbers();
            setDataToView();
        }
        createTimer();
    }
    private void loadLastData(Bundle bundle)
    {
        time = bundle.getInt("time");
        setTime(time);

        step = bundle.getInt("step");
        setStep();

        emptyI = bundle.getInt("emptyI");
        emptyJ = bundle.getInt("emptyJ");

        buttons[3][3].setBackgroundResource(R.drawable.fill_btn);
        buttons[emptyI][emptyJ].setBackgroundResource(R.drawable.empty_button);

        ArrayList<String> buttonText = bundle.getStringArrayList("buttonText");

        for (int i = 0; i < 16; i++)
        {
            buttons[i / 4][i % 4].setText(buttonText.get(i));
        }
    }
    private void createTimer()
    {
        timer = null;
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask()
        {
            @Override
            public void run()
            {
                runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        time++;
                        setTime(time);
                    }
                });
            }
        }, 1000, 1000);
    }
    private void setDataToView()
    {
        setStep();
        for (int i = 0; i < 15; i++)
        {
            Button button = buttons[i/4][i%4];
            button.setText(String.valueOf(numbers.get(i)));
            button.setBackgroundResource(R.drawable.fill_btn);
        }
        buttons[emptyI][emptyJ].setBackgroundResource(R.drawable.empty_button);
    }
    private void loadView()
    {
        layout = findViewById(R.id.content);
        frameLayout = findViewById(R.id.paused_view);
        restartButton = findViewById(R.id.restart);
        pauseButton = findViewById(R.id.pause);
        timeView = findViewById(R.id.time_view);
        stepView = findViewById(R.id.step_view);
        buttonGroup = findViewById(R.id.button_group);

        for (int i = 0; i < 16; i++)
        {
            buttons[i/4][i%4] = (Button)buttonGroup.getChildAt(i);
        }
    }
    private void loadNumbers()
    {
        numbers.clear();
        for (int i = 1; i < 16; i++)
        {
            numbers.add(i);
        }
        Collections.shuffle(numbers);
    }
    public void buttonClick(View view)
    {
        Button button = (Button) view;
        String tag = button.getTag().toString();
        String indexes[] = tag.split(":");

        int i = Integer.parseInt(indexes[0]);
        int j = Integer.parseInt(indexes[1]);

        int deltaI = Math.abs(i - emptyI);
        int deltaJ = Math.abs(j - emptyJ);

        if ((deltaI == 1 && deltaJ == 0) || (deltaI == 0 && deltaJ == 1))
        {
            buttons[emptyI][emptyJ].setText(button.getText());
            buttons[emptyI][emptyJ].setBackground(button.getBackground());

            button.setText("");
            button.setBackgroundResource(R.drawable.empty_button);
            emptyI = i;
            emptyJ = j;
            step++;
            setStep();

            if (MemoryHelper.getHelper().getSoundState()) {
                startMusic();
            }
            else {
                stopMusic();
            }
            if (emptyI == 3 && emptyJ == 3)
            {
                checkToWin();
            }
        }
    }
    private void startMusic() {
        if (mediaPlayer != null) {
            stopMusic();
        }
        mediaPlayer = MediaPlayer.create(this, R.raw.ogg);
        mediaPlayer.start();
    }
    private void stopMusic() {
        if(mediaPlayer != null){
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;}
    }
    private void setStep()
    {

        stepView.setText(String.format("   Step:   \n\uD83D\uDC63 %d",step));
    }
    private void setTime(int time)
    {
        int hour = time /3600;
        int minute = time % 3600 / 60;
        int second = time % 60;

        timeView.setText(String.format("⏱ %02d:%02d:%02d",hour,minute,second));
    }
    private void checkToWin()
    {
        boolean isWin = true;
        for (int i = 0; i < 15; i++)
        {
            Button button = (Button) buttonGroup.getChildAt(i);

            if (!button.getText().toString().equals(i + 1 + "")) {
                isWin = false;
            }
        }
        if (isWin) {
            showAlert();
            finish();
        }
    }
    private void showAlert()
        {
            timer.cancel();
            Intent intent = new Intent(MainActivity.this, EndActivity.class);
            intent.putExtra("time",time);
            intent.putExtra("step",step);
            startActivity(intent);
        }
    private void restartGame()
    {
        emptyJ = 3;
        emptyI = 3;
        time = 0;
        step = 0;

        timer.cancel();
        setTime(time);
        setStep();
        createTimer();
        loadNumbers();
        setDataToView();
    }
    public void onClick(View view)
    {
                restartGame();
            }
    boolean isPaused;
    public void onClickPause(View view)
    {
        method1();
    }
    private void method1()
    {
        if (!isPaused)
        {
            frameLayout.setVisibility(View.VISIBLE);
            buttonGroup.setVisibility(View.GONE);
            timer.cancel();
        } else {
            frameLayout.setVisibility(View.GONE);
            buttonGroup.setVisibility(View.VISIBLE);
            createTimer();
        }
        pauseButton.setText((isPaused)? "   Pause   " : "   Play   ");
        isPaused = !isPaused;
    }
    private void method2()
    {
        if (!isPaused) {
            for (int i = 0; i < 16; i++) {
                buttons[i/4][i%4].setEnabled(false);
            }
            timer.cancel();
        } else {
            createTimer();
            for (int i = 0; i < 16; i++) {
                buttons[i/4][i%4].setEnabled(true);
            }
        }
        pauseButton.setText((isPaused)? "   Pause   " : "   Play   ");
        isPaused = !isPaused;
    }
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState)
    {
        outState.putInt("step",step);
        outState.putInt("time",time);
        outState.putInt("emptyI",emptyI);
        outState.putInt("emptyJ",emptyJ);

        ArrayList<String> buttonText = new ArrayList<>();

        for (int i = 0; i < 16; i++)
        {
            buttonText.add(buttons[i / 4][i % 4].getText().toString());
        }
        outState.putStringArrayList("buttonText",buttonText);
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean shouldStopMusic() {
        return false;
    }

    @Override
    public void setThemeData() {
        layout.setBackgroundResource(MemoryHelper.getHelper().getThemeId());
    }

}

//            activity_main code