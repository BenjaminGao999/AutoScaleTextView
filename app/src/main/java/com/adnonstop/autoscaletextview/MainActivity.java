package com.adnonstop.autoscaletextview;

import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTextView;
    private EditText editText;
    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initListener();
    }


    private void initView() {

        editText = (EditText) findViewById(R.id.edittext);

        mTextView = (TextView) findViewById(R.id.textview1);
    }

    private void initListener() {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {
                String s1 = s.toString();
                mTextView.setText(s1);

                //设置计算好的字体大小
                int widthPixels = getResources().getDisplayMetrics().widthPixels;
                mTextView.setTextSize(getTextSize(mTextView, widthPixels, s1));
            }
        });
    }

    /**
     * 计算textSize
     *
     * @param textView
     * @param width    textview的宽度
     * @param str
     * @return textSize
     */
    public float getTextSize(TextView textView, int width, String str) {
        //字符最大的大小
        float defaultSize = 30.0f;

        for (; ; ) {
            mTextView.setTextSize(defaultSize);
            Paint paint = mTextView.getPaint();
            float wm = paint.measureText(str);
            Log.i("adjust", "getTextSize: count = " + count++);
            if (wm <= width)
                break;
            else
                //每次减小的步长
                defaultSize -= 0.1;
        }
        return defaultSize;
    }
}

