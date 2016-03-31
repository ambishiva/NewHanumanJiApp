package adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.app.hanumanji.R;
import com.example.shiva.hanumanji.YouTubeActivity;

import java.util.ArrayList;

import data.Time;
import data.Video;

/**
 * Created by DELL on 15-02-2016.
 */
public class AudioVideoAdapter extends ArrayAdapter<Video> {

    ArrayList<Video> arrayListVideo;
    private Context context;

    public AudioVideoAdapter(Context context, int resource, ArrayList<Video> arrayListVideo) {
        super(context, resource,arrayListVideo);
        this.context = context;
        this.arrayListVideo = arrayListVideo;
    }


    public static class ViewHolder{

        TextView textViewTitle;
        TextView textViewReminderTitle;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {


        ViewHolder viewHolder = null;
        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.video_audio_item, parent, false);

            viewHolder.textViewTitle = (TextView)convertView.findViewById(R.id.textViewAudioVideoTitle);
            //viewHolder.textViewReminderTitle = (TextView)convertView.findViewById(R.id.textViewTimeTitle);

            convertView.setTag(viewHolder);



        }else {

            viewHolder = (ViewHolder)convertView.getTag();

        }


        viewHolder.textViewTitle.setText(getItem(position).getVideoTitle());
        //viewHolder.textViewReminderTitle.setText(getItem(position).getAlarmTitle());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent youTubeActivity = new Intent(context,YouTubeActivity.class);
                String videoUrl = getItem(position).getVideoUrl();
                String subVideoUrl = videoUrl.substring(videoUrl.lastIndexOf("=") + 1);
                youTubeActivity.putExtra("VideoCode",subVideoUrl);
                context.startActivity(youTubeActivity);
            }
        });
        return convertView;
    }

    @Override
    public int getCount() {
        return arrayListVideo.size();
    }
}
