package uz.myisystem.lesson2.puzzle15.ui;

import androidx.core.text.HtmlCompat;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


import uz.myisystem.lesson2.puzzle15.R;
import uz.myisystem.lesson2.puzzle15.core.BaseActivity;
import uz.myisystem.lesson2.puzzle15.core.MemoryHelper;
import uz.myisystem.lesson2.puzzle15.core.UserData;

public class ResultActivity extends BaseActivity {
    private TextView timeView;
    private TextView stepView;
    private EditText editText;
    private LinearLayout rootGroup;
    private FrameLayout emptyLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resault);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        editText = findViewById(R.id.name_input);
        timeView = findViewById(R.id.step_view);
        stepView = findViewById(R.id.time_view);
        rootGroup = findViewById(R.id.root_layout);
        emptyLayout = findViewById(R.id.empty_screen);

        setData();

    }

    private void setData() {

        ArrayList<UserData> list = MemoryHelper.getHelper().getLastResults();

        if (list.isEmpty()) {
            emptyLayout.setVisibility(View.VISIBLE);
        }else {

            emptyLayout.setVisibility(View.GONE);

            Collections.sort(list, new Comparator<UserData>() {
                @Override
                public int compare(UserData list1, UserData list2) {
                    return list1.getTime() - list2.getTime();
                }
            });
            int i = 1;
            for (UserData data : list) {

                TextView textView = new TextView(this);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(

                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                StringBuilder builder = new StringBuilder();

                builder.append(i+"   \uD83D\uDC64");
                builder.append(data.getName());
                builder.append("   ⏱ ");
                builder.append(getTime(data.getTime()));
                builder.append("  \uD83D\uDC63 ");
                builder.append(data.getStep());

                textView.setText(HtmlCompat.fromHtml(builder.toString(),HtmlCompat.FROM_HTML_MODE_LEGACY));

                textView.setLayoutParams(params);
                int size = getResources().getDimensionPixelSize(R.dimen.textSize);

                textView.setTextSize(size);

                rootGroup.addView(textView);
                i++;
            }
        }
    }

    private String getTime(int n) {
        int hour = n /3600;
        int minute = n % 3600 / 60;
        int second = n % 60;

        String s = String.format("%02d:%02d:%02d",hour,minute,second);
        return s;
    }
    @Override
    public boolean shouldStopMusic() {
        return false;
    }

    @Override
    public void setThemeData() {
    }
}