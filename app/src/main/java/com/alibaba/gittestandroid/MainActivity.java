package com.alibaba.gittestandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //添加些注释
        //制造些冲突
        setContentView(R.layout.activity_main);
        //有冲突了
    }
}
