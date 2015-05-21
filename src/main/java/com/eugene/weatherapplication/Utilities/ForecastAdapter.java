package com.eugene.weatherapplication.Utilities;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.eugene.weatherapplication.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ForecastAdapter extends ArrayAdapter<Forecast> {
    private Context mContext;
    private static List<Forecast> mLogs;
    private TextView mIconText;

    public ForecastAdapter(Context context, int textViewResourceId, List<Forecast> logs) {
        super(context, textViewResourceId);
        mContext = context;
        mLogs = logs;
    }

    // returning 5, skip position 0 (0 = today), starting with position 1
    public int getCount() {
        return 5;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_forecast, parent, false);
        }
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_WEEK, position + 1);
        Date date = calendar.getTime();

        Typeface weatherFont = Typeface.createFromAsset(mContext.getAssets(), "fonts/Weather-Fonts.ttf");
        Typeface robotoRegular = Typeface.createFromAsset(mContext.getAssets(), "fonts/Roboto-Regular.ttf");
        TextView maxTemp = (TextView) view.findViewById(R.id.maxTemp);
        TextView lowTemp = (TextView) view.findViewById(R.id.lowTemp);
        TextView weather1 = (TextView) view.findViewById(R.id.weather1);
        TextView dayOfWeek = (TextView) view.findViewById(R.id.dayOfWeek);
        mIconText = (TextView) view.findViewById(R.id.listIcon);

        maxTemp.setTypeface(robotoRegular);
        lowTemp.setTypeface(robotoRegular);
        dayOfWeek.setTypeface(robotoRegular);
        weather1.setTypeface(robotoRegular);
        mIconText.setTypeface(weatherFont);

        maxTemp.setText("MAX: " + mLogs.get(position + 1).getHighTemp() + (char) 0x00B0);
        lowTemp.setText("MIN: " + mLogs.get(position + 1).getLowTemp() + (char) 0x00B0);
        setWeatherIcon(Integer.valueOf(mLogs.get(position + 1).getWeatherId()));
        weather1.setText(mLogs.get(position + 1).getWeather());
        dayOfWeek.setText(new SimpleDateFormat("EE", Locale.ENGLISH).format(date.getTime()));
        return view;
    }

    private void setWeatherIcon(int actualId) {
        int id = actualId / 100;
        String icon = "";
        if (actualId == 800) {
            icon = mContext.getString(R.string.weather_sunny);
        } else {
            switch (id) {
                case 1:
                    icon = mContext.getString(R.string.weather_sunny);
                    break;
                case 2:
                    icon = mContext.getString(R.string.weather_thunder);
                    break;
                case 3:
                    icon = mContext.getString(R.string.weather_drizzle);
                    break;
                case 7:
                    icon = mContext.getString(R.string.weather_foggy);
                    break;
                case 8:
                    icon = mContext.getString(R.string.weather_cloudy);
                    break;
                case 6:
                    icon = mContext.getString(R.string.weather_snowy);
                    break;
                case 5:
                    icon = mContext.getString(R.string.weather_rainy);
                    break;
            }
        }
        mIconText.setText(icon);
    }
}
