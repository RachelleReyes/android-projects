package com.example.bitsandpizzas;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class StoreFragment extends Fragment {


    public StoreFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView storeRecycler = (RecyclerView)inflater.inflate(
                R.layout.fragment_stores, container, false);

        String[] storeNames = new String[Store.stores.length];
        for (int i = 0; i < storeNames.length; i++) {
            storeNames[i] = Store.stores[i].getName();
        }

        int[] storeImages = new int[Store.stores.length];
        for (int i = 0; i < storeImages.length; i++) {
            storeImages[i] = Store.stores[i].getImageResourceId();
        }

        CaptionedImagesAdapter adapter = new CaptionedImagesAdapter(storeNames, storeImages);
        storeRecycler.setAdapter(adapter);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        storeRecycler.setLayoutManager(layoutManager);

        adapter.setListener(new CaptionedImagesAdapter.Listener() {
            public void onClick(int position) {
                Intent intent = new Intent(getActivity(), StoreDetailActivity.class);
                intent.putExtra(StoreDetailActivity.EXTRA_STORE_ID, position);
                getActivity().startActivity(intent);
            }
        });

        return storeRecycler;

    }

}
