package adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.app.hanumanji.R;
import java.util.ArrayList;
import data.PearlsData;
import tabMala.Mala;

/**
 * Created by shiva on 11/2/16.
 */
public class PearlsAdapter extends ArrayAdapter<PearlsData> {


    private ArrayList<PearlsData> pearlsDataArrayList;
    private Context context;
    int malaCount;
    int beadCount;
    private Mala mala;

    public PearlsAdapter(Context context, int resource, ArrayList<PearlsData> pearlsDataArrayList, Mala mala) {
        super(context, resource, pearlsDataArrayList);
        this.context = context;
        this.pearlsDataArrayList = pearlsDataArrayList;
        this.mala = mala;
    }


    @Override
    public int getCount() {
        return pearlsDataArrayList.size();
    }

    static class ViewHolder {

        ImageView imageViewPearl, imageViewThread;

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(R.layout.ball_item, parent, false);

            holder = new ViewHolder();
            holder.imageViewPearl = (ImageView) convertView.findViewById(R.id.imageViewMiddle);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


       if (getItem(position).getPearlsCount()%2==0){

           holder.imageViewPearl.setImageDrawable(context.getResources().getDrawable(R.mipmap.bead_middle));
       }else{
           holder.imageViewPearl.setImageDrawable(context.getResources().getDrawable(R.mipmap.bead));
       }

       holder. imageViewPearl.setOnTouchListener(new View.OnTouchListener() {
           @Override
           public boolean onTouch(View v, MotionEvent event) {

               if(event.getAction() == MotionEvent.ACTION_DOWN){

                 //  Toast.makeText(context,"Ball Touch Pressed",Toast.LENGTH_SHORT).show();
                   beadCount ++;
                   mala.beadCount(beadCount);
                   if(beadCount==108){

                       malaCount++;
                       mala.malaCount(malaCount);
                       beadCount = 0;
                   }
               }
               return false;
           }
       });

        return  convertView;
    }
}
