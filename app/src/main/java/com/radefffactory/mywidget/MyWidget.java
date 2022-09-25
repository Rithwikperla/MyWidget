package com.radefffactory.mywidget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.RemoteViews;

public class MyWidget extends AppWidgetProvider {

    void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_design);

        //get the widget value
        SharedPreferences preferences = context.getSharedPreferences("PREFS", 0);
        int value = preferences.getInt("value", 1);

        //set the value in the textview
        //views.setTextViewText(R.id.coursename, "" + value);
        //views.setTextViewText(R.id.time, "" + value);
        //views.setTextViewText(R.id.professorname, "" + value);


        //update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);

        //reschedule the widget refresh
        AlarmHandler alarmHandler = new AlarmHandler(context);
        alarmHandler.cancelAlarmManager();
        alarmHandler.setAlarmManager();

        Log.d("WIDGET", "Widget updated!");
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId: appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onDisabled(Context context) {
        //stop updating the widget
        AlarmHandler alarmHandler = new AlarmHandler(context);
        alarmHandler.cancelAlarmManager();

        Log.d("WIDGET", "Widget removed!");
    }
}