package adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.hanumanji.R;

import java.util.ArrayList;

import data.MoreModel;
import data.PearlsData;
import lazyloader.ImageLoader;
import tabMala.Mala;

/**
 * Created by shiva on 11/2/16.
 */
public class MoreAdapter extends ArrayAdapter<MoreModel> {


    private ArrayList<MoreModel> moreModelArrayList;
    private Context context;
    int malaCount;
    int beadCount;
    private Mala mala;
    private ImageLoader imageLoader;

    public MoreAdapter(Context context, int resource, ArrayList<MoreModel> moreModelArrayList) {
        super(context, resource, moreModelArrayList);
        this.context = context;
        this.moreModelArrayList = moreModelArrayList;
        imageLoader =  new ImageLoader(context);
    }


    @Override
    public int getCount() {
        return moreModelArrayList.size();
    }

    static class ViewHolder {

        ImageView imageViewLink;
        TextView textViewLink;
        TextView textViewMainLink;
        TextView textViewOpen;

    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(R.layout.more_item, parent, false);

            holder = new ViewHolder();
            holder.imageViewLink = (ImageView) convertView.findViewById(R.id.imageViewLink);
            holder.textViewLink = (TextView)convertView.findViewById(R.id.textViewLink);
            holder.textViewMainLink = (TextView)convertView.findViewById(R.id.textViewMainLink);
            holder.textViewOpen = (TextView)convertView.findViewById(R.id.textViewOpen);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }



        //holder.textViewLink.setText(moreModelArrayList.get(position).getLinks());
        String link = moreModelArrayList.get(position).getLinks();
        String mainLink = link.substring(link.indexOf("https://") + 8 , link.length());
        holder.textViewMainLink.setText(mainLink);
        imageLoader.DisplayImage(moreModelArrayList.get(position).getLinkImage(), holder.imageViewLink, 65, R.drawable.new_pic);

        holder.textViewOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url = moreModelArrayList.get(position).getLinks();
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                context.startActivity(i);
            }
        });



        return  convertView;
    }
}
