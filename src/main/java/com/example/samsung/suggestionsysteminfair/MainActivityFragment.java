package com.example.samsung.suggestionsysteminfair;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    ExpandableListView expandableListView;
    android.widget.ExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    HashMap<String, List<Venue>> expandableListDetail;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        expandableListView = (ExpandableListView) view.findViewById(R.id.expandableListView);
        expandableListDetail = new ExpandableListDataPump().getData();
        setExpandableList();
        return view;
    }

    private void setExpandableList() {

        expandableListTitle = new ArrayList<>(expandableListDetail.keySet());
        expandableListAdapter = new ExpandableListAdapter(getActivity(), expandableListTitle, expandableListDetail);
        expandableListView.setAdapter(expandableListAdapter);

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                Venue venue = (Venue) expandableListAdapter.getChild(groupPosition, childPosition);
                DialogFragment tipsDialog = ChooseActionDialog.newInstance(venue.getId());
                tipsDialog.show(getActivity().getFragmentManager(), "ChooseAction");
                return false;
            }
        });
    }
}
