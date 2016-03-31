package tabReminders;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.app.hanumanji.R;
import com.example.shiva.hanumanji.AlarmReceiver;
import com.example.shiva.hanumanji.MyApplication;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

import base.AppConstants;
import base.AppMainTabActivity;
import base.BaseFragment;
import constant.Constant;
import data.Time;
import database.DataBaseHelper;


public class ClickedReminder extends BaseFragment implements OnClickListener {

    View view;
    CheckBox checkBoxSunday;
    CheckBox checkBoxMonday;
    CheckBox checkBoxTuesday;
    CheckBox checkBoxWednesday;
    CheckBox checkBoxThursday;
    CheckBox checkBoxFriday;
    CheckBox checkBoxSaturday;
    DataBaseHelper dataBaseHelper;
    private ImageView imageViewSetReminder;
    private TimePicker timePicker;
    SQLiteDatabase sb;
    private PendingIntent pendingIntent;
    Calendar calendar;
    AlarmManager alarmManager;
    protected MyApplication app;

    private TextView textViewReminderTitle;
    String title;
    String titleEdit;
    private Button imageViewDeleteReminder;
    private String parentId="";
    private ImageView imageViewBack;
    private ArrayList<Time> malarmTime = new ArrayList<>();
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

         view  = inflater.inflate(R.layout.clickedreminder, container, false);
         app = (MyApplication)getActivity().getApplicationContext();
         calendar = Calendar.getInstance();
         dataBaseHelper = new DataBaseHelper(getActivity());
         alarmManager = (AlarmManager)getActivity().getSystemService(getActivity().ALARM_SERVICE);
         sb = dataBaseHelper.getWritableDatabase();
         title = getArguments().getString("Title");
//         SharedPreferences sharedPreferences = getActivity().getSharedPreferences("TitleDataClicked", Context.MODE_PRIVATE);
//         SharedPreferences.Editor editor = sharedPreferences.edit();
//         editor.putString("Title",title);
//         editor.commit();
         parentId = getArguments().getString("ParentId");
         initialiseViews();
         initiliseListeners();
         setDataAccordingToTitle();
         sharedPreferences = getActivity().getSharedPreferences("TitleDataClicked", Context.MODE_PRIVATE);
         editor = sharedPreferences.edit();
         return view;
    }


    private void setDataAccordingToTitle() {


        ArrayList<Time> timeArrayList = dataBaseHelper.getDataAccordingToTile(parentId);

        for(int i = 0;i<timeArrayList.size();i++){

            initialiseCheckBoxAccordingToData(timeArrayList.get(i).getWeekDay());

            int hour = Integer.parseInt(timeArrayList.get(i).getHours());
            int minutes = Integer.parseInt(timeArrayList.get(i).getMinutes());

            timePicker.setCurrentHour(hour);
            timePicker.setCurrentMinute(minutes);




        }

    }

    private void initialiseCheckBoxAccordingToData(String weekDay) {


        if(weekDay.equalsIgnoreCase("1")){

            checkBoxSunday.setChecked(true);
        }

        if(weekDay.equalsIgnoreCase("2")){
            checkBoxMonday.setChecked(true);
        }

        if(weekDay.equalsIgnoreCase("3")){
            checkBoxTuesday.setChecked(true);
        }

        if(weekDay.equalsIgnoreCase("4")){
            checkBoxWednesday.setChecked(true);
        }
        if(weekDay.equalsIgnoreCase("5")){
            checkBoxThursday.setChecked(true);
        }
        if(weekDay.equalsIgnoreCase("6")){
            checkBoxFriday.setChecked(true);
        }
        if(weekDay.equalsIgnoreCase("7")){
            checkBoxSaturday.setChecked(true);
        }

    }

    @Override
    public void onResume() {
        super.onResume();


        titleEdit = sharedPreferences.getString("Title","");
        if(titleEdit.equalsIgnoreCase("")){

            textViewReminderTitle.setText(title);

        }else {

            textViewReminderTitle.setText(titleEdit);
            title = textViewReminderTitle.getText().toString();

        }
    }

    @Override
    public void onPause() {
        super.onPause();
        editor.clear();


    }

    private void initialiseViews() {

        checkBoxSunday = (CheckBox)view.findViewById(R.id.checkBoxSunday);
        checkBoxMonday = (CheckBox)view.findViewById(R.id.checkBoxMonday);
        checkBoxTuesday = (CheckBox)view.findViewById(R.id.checkBoxTuesday);
        checkBoxWednesday = (CheckBox)view.findViewById(R.id.checkBoxWednesday);
        checkBoxThursday = (CheckBox)view.findViewById(R.id.checkBoxThursday);
        checkBoxFriday = (CheckBox)view.findViewById(R.id.checkBoxFriday);
        checkBoxSaturday = (CheckBox)view.findViewById(R.id.checkBoxSaturday);
        imageViewSetReminder = (ImageView)view.findViewById(R.id.imageViewSetReminder);
        timePicker = (TimePicker)view.findViewById(R.id.timePicker);
        textViewReminderTitle = (TextView)view.findViewById(R.id.textViewReminderTitle);
        textViewReminderTitle.setText(title);
        imageViewDeleteReminder = (Button)view.findViewById(R.id.imageViewDeleteReminder);
        imageViewBack = (ImageView)view.findViewById(R.id.imageViewBack);

    }

    private void initiliseListeners() {


        imageViewSetReminder.setOnClickListener(this);
        textViewReminderTitle.setOnClickListener(this);
        imageViewDeleteReminder.setOnClickListener(this);
        imageViewBack.setOnClickListener(this);
    }





    private OnClickListener listener =  new OnClickListener(){
        @Override
        public void onClick(View v){
            /* Go to next fragment in navigation stack*/
            mActivity.pushFragments(AppConstants.TAB_REMINDER, new AppTabCSecondFragment(),true,true);
        }
    };

    @Override
    public void onClick(View view) {

        switch (view.getId())
        {


            case R.id.imageViewSetReminder:

                ArrayList<Time> arrayListTime = dataBaseHelper.getDataAccordingToTile(parentId);
                for(int i = 0;i<arrayListTime.size();i++){

                    deletePendingIntent(arrayListTime.get(i));
                }
                dataBaseHelper.deleteContact(parentId);

                String parentIdClicked =  textViewReminderTitle.getText().toString() + String.valueOf((int)calendar.getTimeInMillis());


                if(textViewReminderTitle.getText().toString().equalsIgnoreCase("")){

                    Toast.makeText(getActivity(),"Provide Title for Alarm",Toast.LENGTH_SHORT).show();


                }else{



                    boolean setAlarm = false;
                    String currentHour = String.valueOf(timePicker.getCurrentHour());
                    String currentMinutes = String.valueOf(timePicker.getCurrentMinute());


                    Time time;
                    if(checkBoxSunday.isChecked()){

                      //  time = new Time("1",currentHour,currentMinutes,title);
                       // dataBaseHelper.insertTime(time);
                        setAlarm = true;
                        Random random = new Random(System.nanoTime());
                        int id = random.nextInt(1000000000);
                        time = new Time("1",currentHour,currentMinutes,title,id,parentIdClicked);
                        dataBaseHelper.insertTime(time);
                        malarmTime.add(time);
                    }
                    else{



                    }
                    if(checkBoxMonday.isChecked()){
                       // time = new Time("2",currentHour,currentMinutes,title);
                      //  dataBaseHelper.insertTime(time);
                        setAlarm = true;
                        Random random = new Random(System.nanoTime());
                        int id = random.nextInt(1000000000);
                        time = new Time("2",currentHour,currentMinutes,title,id,parentIdClicked);
                        dataBaseHelper.insertTime(time);
                        setAlarm = true;
                        malarmTime.add(time);
                    }else{


                    }
                    if(checkBoxTuesday.isChecked()){

                        Random random = new Random(System.nanoTime());
                        int id = random.nextInt(1000000000);
                        time = new Time("3",currentHour,currentMinutes,title,id,parentIdClicked);
                        dataBaseHelper.insertTime(time);
                        setAlarm = true;
                        malarmTime.add(time);

                    }else{

                    }
                    if(checkBoxWednesday.isChecked()){

                        Random random = new Random(System.nanoTime());
                        int id = random.nextInt(1000000000);
                        time = new Time("4",currentHour,currentMinutes,title,id,parentIdClicked);
                        dataBaseHelper.insertTime(time);
                        setAlarm = true;
                        malarmTime.add(time);
                    }else{

                    }
                    if(checkBoxThursday.isChecked()){

                        Random random = new Random(System.nanoTime());
                        int id = random.nextInt(1000000000);
                        time = new Time("5",currentHour,currentMinutes,title,id,parentIdClicked);
                        dataBaseHelper.insertTime(time);
                        setAlarm = true;

                    }else{

                    }
                    if(checkBoxFriday.isChecked()){

                        Random random = new Random(System.nanoTime());
                        int id = random.nextInt(1000000000);
                        time = new Time("6",currentHour,currentMinutes,title,id,parentIdClicked);
                        dataBaseHelper.insertTime(time);
                        setAlarm = true;
                        malarmTime.add(time);

                    }else{

                    }
                    if(checkBoxSaturday.isChecked()){

                        Random random = new Random(System.nanoTime());
                        int id = random.nextInt(1000000000);
                        time = new Time("7",currentHour,currentMinutes,title,id,parentIdClicked);
                        dataBaseHelper.insertTime(time);
                        setAlarm = true;
                        malarmTime.add(time);

                    }else {

                    }

                    if(setAlarm){

                        ((AppMainTabActivity)getActivity()).onBackPressed();
                    }else{

                        Toast.makeText(getActivity(),"Please Select Day",Toast.LENGTH_SHORT).show();
                    }



                    setAlarmAccordingToParentId(parentIdClicked);


                }




                break;

            case R.id.textViewReminderTitle:
                mActivity.pushFragments(AppConstants.TAB_REMINDER, new EditTitleClickedReminders(),true,true);
                break;
            case R.id.imageViewDeleteReminder:
                // mActivity.pushFragments(AppConstants.TAB_REMINDER, new EditTitle(),true,true);

                ArrayList<Time> arrayListTimeFromDataBase = dataBaseHelper.getDataAccordingToTile(parentId);
                for(int i = 0;i<arrayListTimeFromDataBase.size();i++){


                    deletePendingIntent(arrayListTimeFromDataBase.get(i));
                }

                dataBaseHelper.deleteContact(parentId);

                ((AppMainTabActivity)getActivity()).onBackPressed();
                break;
            case R.id.imageViewBack:
                ((AppMainTabActivity)getActivity()).onBackPressed();
            default:
                break;
        }

    }

    private void setAlarmAccordingToParentId(String parentIdClicked) {

        ArrayList<Time> timeArrayList = dataBaseHelper.getDataAccordingToTile(parentIdClicked);

        for(int i = 0;i<timeArrayList.size();i++){
            setAlarmAccordingToTime(timeArrayList.get(i));
        }


    }

    private void deletePendingIntent(Time time) {

        Intent intent = new Intent(getActivity(), AlarmReceiver.class);
        PendingIntent sender = PendingIntent.getBroadcast(getActivity(), time.getId(), intent, 0);
        AlarmManager alarmManager = (AlarmManager)getActivity().getSystemService(getActivity().ALARM_SERVICE);
        alarmManager.cancel(sender);
    }

    private void setAlarm() {

        calendar = Calendar.getInstance();
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        if(dayOfWeek == 1){


            String day = Constant.sunday;
            Time time = dataBaseHelper.getData(day);
            if(time!=null){

                setAlarmAccordingToTime(time);
            }



        }else if(dayOfWeek == 2){

            String day = Constant.monday;
            Time time = dataBaseHelper.getData(day);
            if(time!=null){

                setAlarmAccordingToTime(time);
            }

        }else if(dayOfWeek == 3){

            String day = Constant.tuesday;
            Time time = dataBaseHelper.getData(day);
            if(time!=null){

                setAlarmAccordingToTime(time);
            }

        }else if(dayOfWeek == 4){

            String day = Constant.wednesday;
            Time time = dataBaseHelper.getData(day);
            if(time!=null){

                setAlarmAccordingToTime(time);
            }


        }else if(dayOfWeek == 5){

            String day = Constant.thursday;
            Time time = dataBaseHelper.getData(day);
            if(time!=null){

                setAlarmAccordingToTime(time);
            }

        }else if(dayOfWeek == 6){

            String day = Constant.friday;
            Time time = dataBaseHelper.getData(day);
            if(time!=null){

                setAlarmAccordingToTime(time);
            }

        }else if(dayOfWeek == 7){

            String day = Constant.saturday;
            Time time = dataBaseHelper.getData(day);
            if(time!=null){

                setAlarmAccordingToTime(time);
            }
        }


    }

    private void setAlarmAccordingToTime(Time time) {


        //Current Time which is Running
        Timestamp currentTimeStamp = new Timestamp(calendar.getTimeInMillis());

        // Toast.makeText(getActivity(), "Current Hour Selected" + timePicker.getCurrentHour(), Toast.LENGTH_SHORT).show();
        // Toast.makeText(getActivity(),"Current Minute Selected" + timePicker.getCurrentMinute(),Toast.LENGTH_SHORT).show();

        //This is i am Setting the data in calendar
        // Calendar calendar1 = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(time.getHours()));
        calendar.set(Calendar.MINUTE, Integer.parseInt(time.getMinutes()));
        calendar.set(Calendar.DAY_OF_WEEK,Integer.parseInt(time.getWeekDay()));
        Timestamp userSelectedTime = new Timestamp(calendar.getTimeInMillis());
        Intent myIntent = new Intent(getActivity(), AlarmReceiver.class);
        myIntent.putExtra("Parent_ID",time.getParentId());
        pendingIntent = PendingIntent.getBroadcast(getActivity(),time.getId(), myIntent, 0);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY * 7, pendingIntent);
//        if(userSelectedTime.after(currentTimeStamp)){
//
//            Intent myIntent = new Intent(getActivity(), AlarmReceiver.class);
//            pendingIntent = PendingIntent.getBroadcast(getActivity(), 0, myIntent, 0);
//            alarmManager.set(AlarmManager.RTC, calendar.getTimeInMillis(), pendingIntent);
//            //Toast.makeText(getActivity(),"Alarm set at " + time,Toast.LENGTH_SHORT).show();
//            //setAlarmText("Alarm set at " + calendar.getTime());
//
//            app.setDayId(time.getDay());
//
//        }else{
//
//            Toast.makeText(getActivity(),"Select Time After That",Toast.LENGTH_SHORT).show();
//           // int day = Integer.parseInt(time.getDay());
//            boolean checkForSunday = true;
//            boolean checkForMonday = true;
//            boolean checkForTuesday = true;
//            boolean checkForWednesday = true;
//            boolean checkForThursday = true;
//            boolean checkForFriday = true;
//            boolean checkForSaturday = true;
//
//            if(time.getDay().equalsIgnoreCase(Constant.sunday)){
//
//                checkForSunday = false;
//
//            }else if(time.getDay().equalsIgnoreCase(Constant.monday)){
//
//                checkForMonday = false;
//
//            }else if(time.getDay().equalsIgnoreCase(Constant.tuesday)){
//                checkForTuesday = false;
//
//            }else if(time.getDay().equalsIgnoreCase(Constant.wednesday)){
//
//                checkForWednesday = false;
//
//            }else if(time.getDay().equalsIgnoreCase(Constant.thursday)){
//
//                checkForThursday = false;
//
//            }else if(time.getDay().equalsIgnoreCase(Constant.friday)){
//
//                checkForFriday = false;
//
//            }else if(time.getDay().equalsIgnoreCase(Constant.saturday)){
//
//                checkForSaturday = false;
//            }
//
//            if(checkForSunday){
//
//
//                String day = Constant.sunday;
//                Time timeFromDatabase = dataBaseHelper.getData(day);
//                if(timeFromDatabase!=null){
//
//                    setAlarmAccordingToTime(timeFromDatabase);
//                }
//
//
//
//            }else if(checkForMonday){
//
//                String day = Constant.monday;
//                Time timeFromDatabase = dataBaseHelper.getData(day);
//                if(timeFromDatabase!=null){
//
//                    setAlarmAccordingToTime(timeFromDatabase);
//                }
//
//            }else if(checkForTuesday){
//
//                String day = Constant.tuesday;
//                Time timeFromDatabase = dataBaseHelper.getData(day);
//                if(timeFromDatabase!=null){
//
//                    setAlarmAccordingToTime(timeFromDatabase);
//                }
//
//            }else if(checkForWednesday){
//
//                String day = Constant.wednesday;
//                Time timeFromDatabase = dataBaseHelper.getData(day);
//                if(timeFromDatabase!=null){
//
//                    setAlarmAccordingToTime(timeFromDatabase);
//                }
//
//
//            }else if(checkForThursday){
//
//                String day = Constant.thursday;
//                Time timeFromDatabase = dataBaseHelper.getData(day);
//                if(timeFromDatabase!=null){
//
//                    setAlarmAccordingToTime(timeFromDatabase);
//                }
//
//            }else if(checkForFriday){
//
//                String day = Constant.friday;
//                Time timeFromDatabase = dataBaseHelper.getData(day);
//                if(timeFromDatabase!=null){
//
//                    setAlarmAccordingToTime(timeFromDatabase);
//                }
//
//            }else if(checkForSaturday){
//
//                String day = Constant.saturday;
//                Time timeFromDatabase = dataBaseHelper.getData(day);
//                if(timeFromDatabase!=null){
//
//                    setAlarmAccordingToTime(timeFromDatabase);
//                }
//            }
//
    }

}
