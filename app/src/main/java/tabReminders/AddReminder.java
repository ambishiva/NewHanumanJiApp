package tabReminders;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;


import com.app.hanumanji.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import adapter.AlarmTimeAdapter;
import base.AppConstants;
import base.BaseFragment;
import data.Time;
import database.DataBaseHelper;


public class AddReminder extends BaseFragment {

    View view;
    DataBaseHelper dataBaseHelper;
    SQLiteDatabase sb;
    private ListView listViewAlarmList;
    private ImageView imageViewAddReminder;
    private HashMap<String,String> arrayListTime;
    private ArrayList<Time> arrayListTimeNew;
    private AlarmTimeAdapter alarmTimeAdapter;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    boolean flag;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

         view  = inflater.inflate(R.layout.addreminder, container, false);

         sharedPreferences = getActivity().getSharedPreferences("AlarmTutorial", Context.MODE_PRIVATE);
         editor = sharedPreferences.edit();
         flag = sharedPreferences.getBoolean("Flag",false);
         dataBaseHelper = new DataBaseHelper(getActivity());
         arrayListTime = dataBaseHelper.getAllCotacts();
         if(arrayListTime.size()==0){

            if(flag){

                showOverLay();
                editor.putBoolean("Flag",false);
                editor.commit();
            }

         }
         initialiseViews();
         initiliseListeners();


         return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        arrayListTime = dataBaseHelper.getAllCotacts();
        if(arrayListTime.size()==0){

            listViewAlarmList.setVisibility(View.INVISIBLE);

        }else{

            arrayListTimeNew = new ArrayList<Time>();
            Iterator it = arrayListTime.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
                System.out.println(pair.getKey() + " = " + pair.getValue());
                String parentId = (String) pair.getKey();
                String time  = (String) pair.getValue();
                String alarmTitle = time.substring(time.lastIndexOf("-") + 1);
                Time time1 = new Time(parentId,time,alarmTitle);
                arrayListTimeNew.add(time1);
            }
            alarmTimeAdapter = new AlarmTimeAdapter(getActivity(),R.layout.reminder_item,arrayListTimeNew);
            listViewAlarmList.setAdapter(alarmTimeAdapter);

            listViewAlarmList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    SharedPreferences sharedPreferences = getActivity().getSharedPreferences("TitleDataClicked",Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("Title","");
                    editor.commit();
                    String title = arrayListTimeNew.get(position).getAlarmTitle();
                    String parentId = arrayListTimeNew.get(position).getParentId();
                    mActivity.pushFragmentsClickedReminders(AppConstants.TAB_REMINDER, new ClickedReminder(), true, true, title,parentId);

                }
            });

        }
    }

    private void showOverLay() {

        final Dialog dialog = new Dialog(getActivity(), android.R.style.Theme_Translucent_NoTitleBar);

        dialog.setContentView(R.layout.transparent);

        RelativeLayout layout = (RelativeLayout) dialog.findViewById(R.id.transparent);

        layout.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View arg0) {

                dialog.dismiss();

            }

        });

        dialog.show();
    }

    private void initialiseViews() {

       listViewAlarmList = (ListView)view.findViewById(R.id.listViewAlarm);
       imageViewAddReminder = (ImageView)view.findViewById(R.id.imageViewAddReminder);


    }

    private void initiliseListeners() {


        imageViewAddReminder.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {


                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("TitleData", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("Title","");
                editor.commit();
                mActivity.pushFragments(AppConstants.TAB_REMINDER, new NewReminder(),true,true);
            }
        });
    }





    private OnClickListener listener =  new OnClickListener(){
        @Override
        public void onClick(View v){
            /* Go to next fragment in navigation stack*/
            mActivity.pushFragments(AppConstants.TAB_REMINDER, new AppTabCSecondFragment(),true,true);
        }
    };

}
