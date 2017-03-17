package com.sunil.examplesofandroid3;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView tvDisplayDate;
    private Button btnChangeDate;
    private DatePicker dpResult;
    private int year;
    private int month;
    private int day;

    private WebView webView;
    private Button button;

    static final int DATE_DIALOG_ID = 999;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView=(WebView)findViewById(R.id.webview);
        button=(Button)findViewById(R.id.button2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WebSettings webSettings = webView.getSettings();
                webSettings.setJavaScriptEnabled(true);
                webView.loadUrl("http://gmail.com");
                /*Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.gmail.com"));
                startActivity(intent);*/
            }
        });

        setCurrentDateOnView();
        aadListenerOnButton();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void setCurrentDateOnView() {
        tvDisplayDate=(TextView)findViewById(R.id.textView);
        dpResult=(DatePicker)findViewById(R.id.datePicker);

        final Calendar c = Calendar.getInstance();
        year=c.get(Calendar.YEAR);
        month=c.get(Calendar.MONTH);
        day=c.get(Calendar.DAY_OF_MONTH);

        tvDisplayDate.setText(new StringBuilder().append(month+1).append("-").append(day).append("-").append(year).append(" "));
        dpResult.init(year,month,day,null);
    }
    private void aadListenerOnButton() {
        btnChangeDate=(Button)findViewById(R.id.button);
        btnChangeDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID);
            }
        });
    }
    @Override
    protected Dialog onCreateDialog(int id){
        switch (id){
            case DATE_DIALOG_ID:

                return new DatePickerDialog(this,datePickerListener,year,month,day);
        }
        return null;
    }
    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener(){

        @Override
        public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {
            year = selectedYear;
            month= selectedMonth;
            day= selectedDay;
            tvDisplayDate.setText(new StringBuilder().append(month+1).append("-").append(day).append("-").append(year).append(" "));
            dpResult.init(year,month,day,null);
        }
    };
}