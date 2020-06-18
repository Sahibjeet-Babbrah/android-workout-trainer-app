package com.example.exercisedatabase.user_table_information;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.exercisedatabase.R;

import java.util.ArrayList;

public class UsersAdapter extends ArrayAdapter<User> {
    public UsersAdapter(Context context, ArrayList<User> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        User user = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_user, parent, false);
        }
        // Lookup view for data population
        TextView userName = (TextView) convertView.findViewById(R.id.userName);
        TextView DoB = (TextView) convertView.findViewById(R.id.DoB);
        TextView gender = (TextView) convertView.findViewById(R.id.Gender);
        // Populate the data into the template view using the data object
        userName.setText(user.get_name());
        DoB.setText(user.get_date_of_birth());
        gender.setText(user.get_gender());
        // Return the completed view to render on screen
        return convertView;
    }
}
