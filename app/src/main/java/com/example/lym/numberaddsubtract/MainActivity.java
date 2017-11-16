package com.example.lym.numberaddsubtract;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private AddSubtractView mAddSubtractView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAddSubtractView = findViewById(R.id.as_view);

        mAddSubtractView.setLimit(500,200000,30)
                .setListener(new AddSubtractView.OnAddSubtractListener() {
                   

                    @Override
                    public void onMoreMax() {
                        Log.d(TAG, "onMoreMax: ");   
                    }

                    @Override
                    public void onLessMin() {
                        Log.d(TAG, "onLessMin: ");
                    }

                    @Override
                    public void onNumberChange(int number) {
                        Log.d(TAG, "onNumberChange: "+number);
                    }

                   
                });
    }
}
