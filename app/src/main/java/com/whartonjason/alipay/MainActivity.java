package com.whartonjason.alipay;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_main_pay)
    public void onClickPay(){
        PayDialogFragment dialogFragment = PayDialogFragment.newInstance();
        dialogFragment.show(getSupportFragmentManager(),"dialogFragment");
    }

}
