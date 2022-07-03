package com.example.capstoneprojectkassem;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RemoteViews;

import static android.content.Context.MODE_PRIVATE;

public class AppWidgetProviderKassem extends AppWidgetProvider {


    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.example_widget);

        Intent intent = new Intent(context, ClickIntentService.class);
        intent.setAction(ClickIntentService.ACTION_CLICK);
        PendingIntent pendingIntent = PendingIntent.getService(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        views.setOnClickPendingIntent(R.id.textView, pendingIntent);

        int clicks = context.getSharedPreferences("sp", MODE_PRIVATE).getInt("clicks", 0);
        Bundle options = appWidgetManager.getAppWidgetOptions(appWidgetId);

        int width = options.getInt(AppWidgetManager.OPTION_APPWIDGET_MIN_WIDTH);

        String clicksStr;

        if (width < 200) {
            clicksStr = clicks + "s";
        } else {
            clicksStr = clicks + "l";
        }

        views.setTextViewText(R.id.textView, clicksStr);
        views.setTextViewText(R.id.textView, String.valueOf(clicks));
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
    }

    @Override
    public void onDisabled(Context context) {
    }
}
