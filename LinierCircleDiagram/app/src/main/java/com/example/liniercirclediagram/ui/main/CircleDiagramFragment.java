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

import im.dacer.androidcharts.PieHelper;
import im.dacer.androidcharts.PieView;

public class CircleDiagramFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.circle_diagram_fragment, container, false);
        PieView pieView = (PieView)view.findViewById(R.id.pie_view);
        ArrayList<PieHelper> pieHelperArrayList = new ArrayList<PieHelper>();

        pieHelperArrayList.add(new PieHelper(18.9f, Color.rgb(153, 0, 153)));
        pieHelperArrayList.add(new PieHelper(21.1f, Color.rgb(50, 102, 204)));
        pieHelperArrayList.add(new PieHelper(20f, Color.rgb(220, 56, 18)));
        pieHelperArrayList.add(new PieHelper(21.5f, Color.rgb(254, 153, 0)));
        pieHelperArrayList.add(new PieHelper(18.5f, Color.rgb(16, 150, 25)));

        pieView.setDate(pieHelperArrayList);
        pieView.selectedPie(2); //optional
//        pieView.setOnPieClickListener(listener); //optional
        pieView.showPercentLabel(true); //optional

        return view;
    }
}