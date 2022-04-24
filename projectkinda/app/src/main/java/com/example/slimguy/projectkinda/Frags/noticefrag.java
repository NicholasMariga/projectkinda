package com.example.slimguy.projectkinda.Frags;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.slimguy.projectkinda.R;

public class noticefrag extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       return inflater.inflate(R.layout.noticefra, container,false);

      /*  View rootView = inflater.inflate(R.layout.noticefra, container, false);
        // 1. get a reference to recyclerView
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.list);

        // 2. set layoutManger
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // this is data fro recycler view
        ItemData itemsData[] = {
                new ItemData("Indigo", R.drawable.circle),
                new ItemData("Red", R.drawable.color_ic_launcher),
                new ItemData("Blue", R.drawable.indigo),
                new ItemData("Green", R.drawable.circle),
                new ItemData("Amber", R.drawable.color_ic_launcher),
                new ItemData("Deep Orange", R.drawable.indigo)
        };


        // 3. create an adapter
        MyAdapter mAdapter = new MyAdapter(itemsData);
        // 4. set adapter
        recyclerView.setAdapter(mAdapter);
        // 5. set item animator to DefaultAnimator
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        return rootView;
    }
        */
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Notices");
    }
}
