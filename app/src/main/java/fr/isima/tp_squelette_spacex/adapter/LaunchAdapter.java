package fr.isima.tp_squelette_spacex.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import fr.isima.tp_squelette_spacex.R;

public class LaunchAdapter extends ArrayAdapter<Launch> {

    LayoutInflater inflater;
    int layoutResourceId;

    public LaunchAdapter(Activity activity, int layoutResourceId, List<Launch> objects){
        super(activity, layoutResourceId, objects);
        this.layoutResourceId = layoutResourceId;
        inflater = activity.getLayoutInflater();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        if(convertView == null) {
            convertView = inflater.inflate(layoutResourceId, parent, false);
        }

        TextView missionName  = (TextView)convertView.findViewById(R.id.textviewmissionname);
        TextView rocketName = (TextView)convertView.findViewById(R.id.textviewrocketname);
        TextView missionDate = (TextView)convertView.findViewById(R.id.textviewdate);

        Launch launch = getItem(position);

        missionName.setText(launch.mission_name);
        rocketName.setText(launch.rocket.rocket_name);
        Date date = new Date(launch.launch_date_unix);
        SimpleDateFormat formater = new SimpleDateFormat("dd MMMM yyyy hh:mm");
        missionDate.setText(formater.format(date));

        return convertView;

    }
}
