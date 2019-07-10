package com.example.adapters;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import java.text.DateFormatSymbols;

public class NewsListActivity extends ListActivity {

    static final String[] textItems = new String[]{
            "\t\tBarton did feebly change man she afford square add. Want eyes by neat so just must. Past draw tall up face show rent oh mr.", "\t\tIn the field of intelligent transportation systems (ITS), an electronic road sign (ERS) is an important device for giving a real-time traffic-related information.", "\t\tSunflower is an important oilseed crop, as well as a model system for evolutionary studies, but its 3.6 gigabase genome has proven difficult to assemble, in part because of the high repeat content of its genome.", "\t\tContrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old.", "\t\tBarton did feebly change man she afford square add. Want eyes by neat so just must. Past draw tall up face show rent oh mr.", "\t\tIn the field of intelligent transportation systems (ITS), an electronic road sign (ERS) is an important device for giving a real-time traffic-related information.", "\t\tSunflower is an important oilseed crop, as well as a model system for evolutionary studies, but its 3.6 gigabase genome has proven difficult to assemble, in part because of the high repeat content of its genome.", "\t\tContrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old."
    };

    static final int[] imgItems = new int[] {
            R.drawable.field, R.drawable.forest_road, R.drawable.sunflower, R.drawable.sunset, R.drawable.field, R.drawable.forest_road, R.drawable.sunflower, R.drawable.sunset
    };

    static final String[] authorItems = new String[] {
            "Gurdeep Crawford", "Alessia Christian", "Nayan Franco", "Kester Wilks", "Jem Green", "Gurdeep Crawford", "Alessia Christian", "Nayan Franco", "Kester Wilks", "Jem Green"
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ArrayList<newItem> posts = new ArrayList<>();

        TimeZone tz = TimeZone.getTimeZone("Asia/Baku");
        Calendar cal = new GregorianCalendar(tz);

        DateFormat df = new SimpleDateFormat("EEE dd/MMM/yyyy HH:mm");
        String date = df.format(Calendar.getInstance().getTime());

        for (int i = 0; i < textItems.length; ++i) {
//            String monthName = new DateFormatSymbols().getMonths()[cal.get(Calendar.MONTH)];

//            String timeStamp =  cal.get(Calendar.DAY_OF_MONTH) + "/" + monthName + "/" + cal.get(Calendar.YEAR) + " " + cal.get(Calendar.HOUR) + ":" + cal.get(Calendar.MINUTE);

            newItem post = new newItem(imgItems[i], authorItems[i], date, textItems[i]);

            posts.add(post);
        }

        setListAdapter(new NewsArrayAdapter(this, posts));
    }

//    @Override
//    protected void onListItemClick(ListView l, View v, int position, long id) {
//        String selectedValue = (String) getListAdapter().getItem(position);
//        Toast.makeText(this, selectedValue, Toast.LENGTH_SHORT).show();
//    }
}
