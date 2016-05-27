package com.example.samsung.suggestionsysteminfair;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class MainActivity2Fragment extends Fragment {
    public List<String> list;
    public String type;

    public MainActivity2Fragment() {
    }

    public static MainActivity2Fragment newInstance(List<String> list, String type) {
        MainActivity2Fragment fragment = new MainActivity2Fragment();
        fragment.list = list;
        fragment.type = type;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_main2, container, false);

        final ArrayAdapter<String> mForecastAdapter =
                new ArrayAdapter<>(
                        getActivity(),
                        R.layout.list_item_venue,
                        R.id.list_item_venue_textview,
                        list);

        ListView listView = (ListView) rootView.findViewById(R.id.list_view_active_lists);
        listView.setAdapter(mForecastAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Venue venue = new ExpandableListDataPump().getData().get(type).get(position);
                DialogFragment tipsDialog = ChooseActionDialog.newInstance(venue.getId());
                tipsDialog.show(getActivity().getFragmentManager(), "ChooseAction");
            }
        });

        return rootView;
    }
}