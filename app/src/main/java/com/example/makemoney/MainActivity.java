package com.example.makemoney;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.hjq.toast.ToastUtils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener, View.OnClickListener {

    private Spinner mSpinner;
    private TextView mTvMinValue;
    private EditText mEdMinValue;
    private TextView mTvMaxValue;
    private EditText mEdMaxValue;
    private Button mBtn;
    private TextView mTvShow;
    private double zje = 1600;
    private double maxKs = 30;
    private Spinner spinnerCl;
    private Spinner spinnerTime;
    private Button btnTime;
    private String year, month, day, hour, minute;
    private EditText edStart;
    private EditText edZhisun;

    private SQLManager sqlManager;
    private Button btnNext;
    private int initialCapital = 1600;
    //private Spinner spinnerSf;
    private EditText edJizhi;
    private Spinner spinnerPz;
    private Button btnClean;
    //private double riskRatio=0.03;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        sqlManager = new SQLManager(getApplicationContext());
        //sqlManager.deleteAll();
    }

    private void initView() {
        mSpinner = (Spinner) findViewById(R.id.spinner_lx);
        mTvMinValue = (TextView) findViewById(R.id.tv_minValue);
        mEdMinValue = (EditText) findViewById(R.id.ed_minValue);
        mTvMaxValue = (TextView) findViewById(R.id.tv_maxValue);
        mEdMaxValue = (EditText) findViewById(R.id.ed_maxValue);
        mBtn = (Button) findViewById(R.id.btn);
        mTvShow = (TextView) findViewById(R.id.tv_show);
        spinnerCl = (Spinner) findViewById(R.id.spinner_cl);
        spinnerTime = (Spinner) findViewById(R.id.spinner_time);
        btnTime = (Button) findViewById(R.id.btn_time);
        mBtn.setOnClickListener(this);
        btnTime.setOnClickListener(this);
        spinnerCl.setSelection(1);
        spinnerTime.setSelection(1);

        edStart = (EditText) findViewById(R.id.ed_start);
        edZhisun = (EditText) findViewById(R.id.ed_zhisun);
        btnNext = (Button) findViewById(R.id.btn_next);

        btnNext.setOnClickListener(this);
        //spinnerSf = (Spinner) findViewById(R.id.spinner_sf);
        edJizhi = (EditText) findViewById(R.id.ed_jizhi);
        spinnerPz = (Spinner) findViewById(R.id.spinner_pz);
        btnClean = (Button) findViewById(R.id.btn_clean);
        btnClean.setOnClickListener(this);
    }


    private void initData() {

        //策略
        String celue = (String) spinnerCl.getSelectedItem();
        //品种
        String pinzhong = (String) spinnerPz.getSelectedItem();
        //周期
        String zhouqi = (String) spinnerTime.getSelectedItem();
        //类型
        String leixing = (String) mSpinner.getSelectedItem();
        //挂单
        String guadan = edStart.getText().toString();
        //止损
        String zhisun = edZhisun.getText().toString();
        //胜负
        //String shengfu = (String) spinnerSf.getSelectedItem();
        //极值
        String jizhi = edJizhi.getText().toString();

        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(btnTime.getText().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Transactions transactions = new Transactions();
        transactions.setTradingStrategies(celue);
        transactions.setVarieties(pinzhong);
        transactions.setCycle(zhouqi);
        transactions.setSellOrBuy(leixing);
        transactions.setStartPosition(Double.parseDouble(guadan));
        transactions.setStopLoss(Double.parseDouble(zhisun));
        transactions.setDate(date);

        BigDecimal big_guadan = new BigDecimal(guadan);
        BigDecimal big_zhisun = new BigDecimal(zhisun);

        BigDecimal dVaule;

        if (leixing.equals("buy")) {
            dVaule = big_guadan.subtract(big_zhisun);
            BigDecimal big_yingli = dVaule.add(big_guadan);
            transactions.setStopProfit(big_yingli.doubleValue());
            if (jizhi!=null&&!jizhi.isEmpty()) {
                BigDecimal big_jizhi = new BigDecimal(jizhi);
                if (big_jizhi.doubleValue() > big_yingli.doubleValue()) {
                    transactions.setState(2);
                } else {
                    transactions.setState(3);
                }
            }else {
                transactions.setState(0);
            }
        } else {
            dVaule = big_zhisun.subtract(big_guadan);
            BigDecimal big_yingli = big_guadan.subtract(dVaule);
            transactions.setStopProfit(big_yingli.doubleValue());
            if (jizhi!=null&&!jizhi.isEmpty()) {
                BigDecimal big_jizhi = new BigDecimal(jizhi);
                if (big_jizhi.doubleValue() < big_yingli.doubleValue()) {
                    transactions.setState(2);
                } else {
                    transactions.setState(3);
                }
            }else {
                transactions.setState(0);
            }
        }

        transactions.setdVaule(dVaule.doubleValue());
        transactions.setProfitOrLossM(30);
        long index = sqlManager.insert(transactions);
        if (index != 0) {
            ToastUtils.show("添加成功");
        }


//        BigDecimal min = new BigDecimal(mEdMinValue.getText().toString());
//        BigDecimal max = new BigDecimal(mEdMaxValue.getText().toString());
//        BigDecimal dValue = max.subtract(min);
//        String item = (String) mSpinner.getSelectedItem();
//        BigDecimal shoushu = new BigDecimal((30 / (dValue.doubleValue() * 100000 + 7))).setScale(1, RoundingMode.UP);
//        if (item.equals("buy")) {
//            mTvShow.setText("buyStop点:" + max + "\n"
//                    + "止盈点:" + (max.add(dValue)) + "\n"
//                    + "止损点:" + min + "\n"
//                    + "手数:" + shoushu.doubleValue());
//        } else {
//            mTvShow.setText("sellStop点:" + min + "\n"
//                    + "止盈点:" + (min.subtract(dValue)) + "\n"
//                    + "止损点:" + max + "\n"
//                    + "手数:" + shoushu.doubleValue());
//        }
        //挂单点
//        BigDecimal start = new BigDecimal(edStart.getText().toString());
//        //止损点
//        BigDecimal zhisun = new BigDecimal(edZhisun.getText().toString());
//        //选择挂单类型 做多/做空
//        String item = (String) mSpinner.getSelectedItem();
//        //止损大小
//        BigDecimal dValue;
//        //盈利位置
//        BigDecimal ylValue;
//        if(item.equals("buy")){
//             dValue = start.subtract(zhisun);
//             ylValue = start.add(dValue);
//        }else{
//             dValue = zhisun.subtract(start);
//             ylValue = start.subtract(dValue);
//        }


    }

    //日期选择
    private void showDateSelectTime() {

        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();

    }

    //时间选择
    private void showTimeSelectTime() {

        Calendar calendar = Calendar.getInstance();
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, this, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
        timePickerDialog.show();

    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        year = i + "";
        if (i1 >= 9) {
            month = i1 + 1 + "";
        } else {
            month = "0" + (i1 + 1);
        }
        if (i2 > 9) {
            day = i2 + "";
        } else {
            day = "0" + i2;
        }
        showTimeSelectTime();
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {
        if (i > 9) {
            hour = i + "";
        } else {
            hour = "0" + i;
        }
        if (i1 > 9) {
            minute = i1 + "";
        } else {
            minute = "0" + i1;
        }
        Log.e(minute, minute);
        btnTime.setText(year + "-" + month + "-" + day + " " + hour + ":" + minute + ":00");
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btn:
                initData();
                //startActivity(new Intent(MainActivity.this,OrderActivity.class));
                break;

            case R.id.btn_time:
                showDateSelectTime();
                break;

            case R.id.btn_next:
                startActivity(new Intent(MainActivity.this, OrderActivity.class));
                break;

            case R.id.btn_clean:
                sqlManager.deleteAll();
                break;


        }

    }
}