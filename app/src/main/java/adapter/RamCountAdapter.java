package adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.app.hanumanji.R;

import java.util.ArrayList;

import data.PearlsData;
import tabMala.Mala;

/**
 * Created by shiva on 11/2/16.
 */
public class RamCountAdapter extends ArrayAdapter<Bitmap> {


    private ArrayList<Bitmap> ramCount;
    private Context context;
    int malaCount;
    int beadCount;
    private Mala mala;

    public RamCountAdapter(Context context, int resource, ArrayList<Bitmap> ramCount) {
        super(context, resource, ramCount);
        this.context = context;
        this.ramCount = ramCount;

    }


    @Override
    public int getCount() {
        return ramCount.size();
    }

    static class ViewHolder {

        ImageView imageViewRamJi;

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(R.layout.ram_item, parent, false);

            holder = new ViewHolder();
            holder.imageViewRamJi = (ImageView) convertView.findViewById(R.id.imageViewRamJi);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.imageViewRamJi.setImageBitmap(getItem(position));

        return  convertView;
    }
}
