package com.example.inspiron.myapplication;

import android.content.res.TypedArray;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by inspiron on 13/6/2017.
 */

public class Fragment1 extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";
    static TypedArray image;
    static String[] name;
    private static ListView listView;
    private static List<ItemModel> list;
    private View view;

    private int mPage;

    public static Fragment1 newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        Fragment1 fragment = new Fragment1();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_page1, container, false);

        list = new ArrayList<>();
        image = view.getResources().obtainTypedArray(R.array.image_set);
        name = view.getResources().getStringArray(R.array.name_set);

        for (int i = 0; i < name.length; i++) {
            ItemModel item = new ItemModel(image.getResourceId(i, -1), name[i]);
            Log.e("name", name[i]);
            list.add(item);
        }

        listView = (ListView) view.findViewById(R.id.list);
        ListViewAdapter adapter = new ListViewAdapter(this.getContext(), list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        return view;
    }
}