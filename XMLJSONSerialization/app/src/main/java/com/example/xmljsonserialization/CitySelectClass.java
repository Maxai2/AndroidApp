package com.example.xmljsonserialization;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;

public class CitySelectClass extends Dialog {

    Activity activity;
    Dialog dialog;
    EditText cityName;
    ListView cityList;
    Button btnSelect;

    String[] cityNames = new String[] {
            "Москва", "С.-Петербург", "Абакан", "Архангельск", "Астрахань", "Барнаул", "Белгород",
            "Благовещенск", "Брянск", "Владивосток", "Владикавказ", "Владимир", "Волгоград",
            "Вологда", "Воронеж", "Горно-Алтайск", "Грозный", "Дербент", "Екатеринбург", "Иваново",
            "Ижевск", "Йошкар-Ола", "Иркутск", "Казань", "Калининград", "Калуга", "Карачаевск",
            "Кемерово", "Киров", "Кострома", "Краснодар", "Красноярск", "Кудымкар", "Курган",
            "Курск", "Кызыл", "Липецк", "Магадан", "Майкоп", "Махачкала", "Мин.Воды", "Мурманск",
            "Нальчик", "Набер.Челны", "Назрань", "Нефтеюганск", "Нижневартовск", "Н.Новгород",
            "Новгород", "Новороссийск", "Новосибирск", "Норильск", "Омск", "Орел", "Оренбург",
            "Пенза", "Пермь", "Петрозаводск", "Петроп.-Камч", "Псков", "Пятигорск", "Ростов-на-Д",
            "Рязань", "Самара", "Саранск", "Саратов", "Смоленск", "Сочи", "Ставрополь", "Сургут",
            "Сыктывкар", "Тамбов", "Тверь", "Тольятти", "Томск", "Тула", "Тюмень", "Улан-Удэ",
            "Ульяновск", "Уфа", "Хабаровск", "Ханты-Манс", "Чебоксары", "Челябинск", "Чита",
            "Элиста", "Ю.-Сахалинск", "Якутск", "Ярославль", "Алма-Ата", "Астана", "Ашхабад",
            "Баку", "Бишкек", "Вильнюс", "Душанбе", "Ереван", "Киев", "Кишинев", "Минск", "Рига",
            "Таллинн", "Ташкент", "Тбилиси"
    };

    int[] cityCode = new int[] {
            27611, 26062, 29865, 22550, 34880, 29838, 34214, 31510, 26898, 31960, 37228, 27532,
            34560, 27037, 34122, 36052, 37235, 37470, 28440, 27347, 28411, 99990, 30710, 27595,
            26702, 27703, 37116, 29642, 27196, 27333, 34929, 29574, 28116, 28661, 34009, 36096,
            27930, 25913, 37021, 37472, 37054, 22113, 37212, 28506, 99946, 23848, 23471, 27553,
            26179, 37006, 29634, 23078, 28698, 27906, 35121, 27962, 28225, 22820, 32540, 26258,
            37050, 34731, 27731, 28900, 27760, 34172, 26781, 37099, 34949, 23849, 23804, 27947,
            27402, 27890, 29430, 27719, 28367, 30823, 27786, 28722, 31735, 23933, 27581, 28642,
            30758, 34861, 32150, 24959, 27331, 36870, 35188, 38880, 37850, 38353, 26730, 38836,
            37789, 33345, 33815, 26850, 26422, 26038, 38457, 37549
    };


    public CitySelectClass(Activity activity) {
        super(activity);

        this.activity = activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.city_select_dialog);

        cityName = findViewById(R.id.cityName);
        cityList = findViewById(R.id.cityList);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, cityNames);
        cityList.setAdapter(adapter);

        btnSelect = findViewById(R.id.btnSelect);
    }
}
