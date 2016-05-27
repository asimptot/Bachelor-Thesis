package com.example.samsung.suggestionsysteminfair;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

public class ChooseActionDialog extends DialogFragment {
    public static String venueId;

    public static ChooseActionDialog newInstance(String venueId) {
        ChooseActionDialog.venueId = venueId;
        ChooseActionDialog chooseActionDialog = new ChooseActionDialog();
        Bundle bundle = new Bundle();
        chooseActionDialog.setArguments(bundle);
        return chooseActionDialog;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View rootView = inflater.inflate(R.layout.dialog_add_water, null);

        ImageView ivComments = (ImageView) rootView.findViewById(R.id.iv_comments);
        ImageView ivPhotos = (ImageView) rootView.findViewById(R.id.iv_photos);
        ImageView ivSuggestions = (ImageView) rootView.findViewById(R.id.iv_suggestions);

        ivSuggestions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FetchNextVenueTask weatherTask = new FetchNextVenueTask(getActivity());
                weatherTask.execute(venueId);
                dismiss();
            }
        });

        ivComments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FetchCommentsTask commentsTask = new FetchCommentsTask(getActivity());
                commentsTask.execute(venueId);
                dismiss();
            }
        });

        ivPhotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FetchPhotosTask photosTask = new FetchPhotosTask(getActivity());
                photosTask.execute(venueId);
                dismiss();
            }
        });

        builder.setView(rootView)
                .setPositiveButton("", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });

        return builder.create();
    }

}
