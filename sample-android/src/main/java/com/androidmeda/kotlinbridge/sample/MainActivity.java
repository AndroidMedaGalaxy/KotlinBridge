package com.androidmeda.kotlinbridge.sample;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.List;

import static com.androidmeda.kotlinbridge.KotlinBridge.*;

public class MainActivity extends AppCompatActivity {

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String name = "   ";
        List<Integer> nums = Arrays.asList(1, 2, 3);

        String report =
                "KotlinBridge smoke test\n\n" +
                "isNullOrBlank(\"   \") = " + isNullOrBlank(name) + "\n" +
                "orEmpty(null) = \"" + orEmpty(null) + "\"\n" +
                "trimToNull(\"   \") = " + trimToNull(name) + "\n" +
                "ellipsize(\"abcd\", 3) = " + ellipsize("abcd", 3) + "\n\n" +

                "firstOrNull([1,2,3]) = " + firstOrNull(nums) + "\n" +
                "lastOrNull([1,2,3]) = " + lastOrNull(nums) + "\n" +
                "getOrNull([1,2,3], 10) = " + getOrNull(nums, 10) + "\n\n" +

                "toIntOrNull(\"123\") = " + toIntOrNull("123") + "\n" +
                "toIntOrNull(\"nope\") = " + toIntOrNull("nope") + "\n" +
                "coerceIn(10,0,5) = " + coerceIn(10, 0, 5) + "\n" +
                "tryOrDefault(0, parseInt(\"nope\")) = " +
                    tryOrDefault(0, () -> Integer.parseInt("nope"));

        TextView tv = new TextView(this);
        tv.setText(report);
        tv.setTextSize(16f);
        int pad = (int) (16 * getResources().getDisplayMetrics().density);
        tv.setPadding(pad, pad, pad, pad);
        setContentView(tv);
    }
}
