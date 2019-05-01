package com.example.slimguy.projectkinda.Frags;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.slimguy.projectkinda.R;

public class eventsfrag extends Fragment{

    ListView lv;
    SearchView searchView;
    ArrayAdapter<String> adapter;

    String [] data ={"This is Event 1","This is Event 1","This is Event 2","This is Event 3","This is Event 4","This is Event 5","This is Event 6","This is Event 7"
    };
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.eventsfra, container,false);

        lv = (ListView) view.findViewById(R.id.idlistview);
        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, data);
        lv.setAdapter(adapter);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Events");


    }
}
