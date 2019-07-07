package com.example.adapters.ui.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.support.annotation.Nullable;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;

import com.example.adapters.MainActivity;
import com.example.adapters.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A placeholder fragment containing a simple view.
 */
public class contactListFragment extends Fragment {

    final String NAME = "name";
    final String NUMBER = "number";
    final String AVATAR = "avatar";

    String[] Names = { "Carolina Chan", "Ryan Glass", "Rajveer Campos", "Annabell Cox", "Kaylen Blackmore" };
    String[] PhoneNumbers = { "(515) 723-2952", "(756) 263-5243", "(832) 973-7973", "(524) 410-0603", "(584) 886-9512" };
    int img = R.drawable.contact_image;

    SimpleAdapter sAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ArrayList<Map<String, Object>> data = new ArrayList<Map<String, Object>>(Names.length);
        Map<String, Object> m;

        for (int i = 0; i < Names.length; i++) {
            m = new HashMap<String, Object>();
            m.put(NAME, Names[i]);
            m.put(NUMBER, PhoneNumbers[i]);
            m.put(AVATAR, img);
            data.add(m);
        }

        String[] from = { NAME, NUMBER, AVATAR };

        int[] to = { R.id.contactNameSur, R.id.contactNumber, R.id.contactImg};

        sAdapter = new SimpleAdapter(getActivity(), data, R.layout.contact_item, from, to);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.contact_list, container, false);

        final ListView contacts = root.findViewById(R.id.contacts);
        contacts.setAdapter(sAdapter);

        return root;
    }
}