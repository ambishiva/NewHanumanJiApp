package adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.hanumanji.R;
import com.example.shiva.hanumanji.YouTubeActivity;

import java.util.ArrayList;

import data.Tutorial;
import data.Video;

/**
 * Created by DELL on 15-02-2016.
 */
public class TutorialAdapter extends ArrayAdapter<Tutorial> {

    ArrayList<Tutorial> arrayListTutorial;
    private Context context;

    public TutorialAdapter(Context context, int resource, ArrayList<Tutorial> arrayListTutorial) {
        super(context, resource,arrayListTutorial);
        this.context = context;
        this.arrayListTutorial = arrayListTutorial;
    }


    public static class ViewHolder{

       ImageView imageViewTutorial;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {


        ViewHolder viewHolder = null;
        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.help_item, parent, false);
            convertView.setTag(viewHolder);
        }else {

            viewHolder = (ViewHolder)convertView.getTag();

        }


       // viewHolder.imageViewTutorial = (ImageView)convertView.findViewById(R.id.imageViewTutorial);
        //viewHolder.imageViewTutorial.setImageDrawable(context.getResources().getDrawable(R.mipmap.splashawesome));

        return convertView;
    }

    @Override
    public int getCount() {
        return arrayListTutorial.size();
    }
}
