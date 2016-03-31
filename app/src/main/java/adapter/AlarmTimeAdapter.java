package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.app.hanumanji.R;

import java.util.ArrayList;

import data.Time;

/**
 * Created by DELL on 15-02-2016.
 */
public class AlarmTimeAdapter extends ArrayAdapter<Time> {

    ArrayList<Time> arrayListTime;
    private Context context;

    public AlarmTimeAdapter(Context context, int resource,ArrayList<Time> arrayListTime) {
        super(context, resource,arrayListTime);
        this.context = context;
        this.arrayListTime = arrayListTime;
    }


    public static class ViewHolder{

        TextView textViewReminderTime;
        TextView textViewReminderTitle;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        ViewHolder viewHolder = null;
        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.reminder_item, parent, false);

            viewHolder.textViewReminderTime = (TextView)convertView.findViewById(R.id.textViewTime);
            viewHolder.textViewReminderTitle = (TextView)convertView.findViewById(R.id.textViewTimeTitle);

            convertView.setTag(viewHolder);



        }else {

            viewHolder = (ViewHolder)convertView.getTag();

        }



        String [] time = getItem(position).getTime().split("-");
        viewHolder.textViewReminderTime.setText(time[0]);
        viewHolder.textViewReminderTitle.setText(getItem(position).getAlarmTitle());


        return convertView;
    }

    @Override
    public int getCount() {
        return arrayListTime.size();
    }
}
