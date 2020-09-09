package com.example.acceptance2.utils;

import android.app.DatePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Locale;

public class DatePickerDialogUtils {

    public static void setData(TextView textView, Context context,IDatePickerDialog iDatePickerDialog){
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        new DatePickerDialog(context, 0, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                textView.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                iDatePickerDialog.setData(year + "-" + (month + 1) + "-" + dayOfMonth);
            }
        }, calendar.get(Calendar.YEAR)
                , calendar.get(Calendar.MONTH)
                , calendar.get(Calendar.DAY_OF_MONTH)).show();
    }


    public interface  IDatePickerDialog{
        void setData(String data);
    }
    public static IDatePickerDialog iDatePickerDialog;

    public void setiDatePickerDialog(IDatePickerDialog iDatePickerDialog) {
        this.iDatePickerDialog = iDatePickerDialog;
    }
}
