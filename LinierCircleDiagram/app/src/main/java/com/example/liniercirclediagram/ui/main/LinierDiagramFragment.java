package com.example.liniercirclediagram.ui.main;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.liniercirclediagram.R;

import java.util.ArrayList;

import im.dacer.androidcharts.LineView;

public class LinierDiagramFragment extends Fragment {


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.linier_diagram_fragment, container, false);
        ArrayList<String> bottomStringList = new ArrayList<String>();
        ArrayList<ArrayList<Integer>> dataLists = new ArrayList<>();

        bottomStringList.add("January");
        bottomStringList.add("February");
        bottomStringList.add("March");
        bottomStringList.add("April");
        bottomStringList.add("May");
        bottomStringList.add("June");
        bottomStringList.add("July");
        bottomStringList.add("August");
        bottomStringList.add("September");
        bottomStringList.add("October");
        bottomStringList.add("November");
        bottomStringList.add("December");

        ArrayList<Integer> ints = new ArrayList<Integer>();

        ints.add(1);
        ints.add(2);
        ints.add(3);
        ints.add(4);
        ints.add(5);
        ints.add(6);

        dataLists.add(ints);

        ints.clear();

        ints.add(10);
        ints.add(20);
        ints.add(30);
        ints.add(40);
        ints.add(50);
        ints.add(60);

        dataLists.add(ints);

        ints.clear();

        ints.add(100);
        ints.add(200);
        ints.add(300);
        ints.add(400);
        ints.add(500);
        ints.add(600);

        dataLists.add(ints);

        LineView lineView = (LineView)view.findViewById(R.id.line_view);
        lineView.setDrawDotLine(false); //optional
        lineView.setShowPopup(LineView.SHOW_POPUPS_MAXMIN_ONLY); //optional
        lineView.setBottomTextList(bottomStringList);
        lineView.setColorArray(new int[]{Color.BLACK, Color.GREEN, Color.GRAY, Color.CYAN});
        lineView.setDataList(dataLists); //or lineView.setFloatDataList(floatDataLists)

        return view;
    }
}
