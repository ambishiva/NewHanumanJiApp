package tabReminders;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.app.hanumanji.R;

import base.AppConstants;
import base.AppMainTabActivity;
import base.BaseFragment;


public class EditTitleClickedReminders extends BaseFragment {

    View view;
    private EditText editTextTitle;
    private TextView textViewDone;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

         view  = inflater.inflate(R.layout.edit_title, container, false);
         initialiseViews();
         initiliseListeners();


         return view;
    }


    private void initialiseViews() {

       editTextTitle = (EditText)view.findViewById(R.id.editTextTitle);
       textViewDone = (TextView)view.findViewById(R.id.textViewDone);

    }

    private void initiliseListeners() {


//        imageViewAddReminder.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                mActivity.pushFragments(AppConstants.TAB_REMINDER, new Reminder(),true,true);
//            }
//        });

        textViewDone.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {


                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("TitleDataClicked", Context.MODE_PRIVATE);

                SharedPreferences.Editor editor = sharedPreferences.edit();
                String title = editTextTitle.getText().toString();
                //Toast.makeText(getActivity(),"Tile "+ title,Toast.LENGTH_SHORT).show();
                editor.putString("Title", editTextTitle.getText().toString());
                editor.commit();
                ((AppMainTabActivity)getActivity()).onBackPressed();
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
