package tabPrayers;

import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.app.hanumanji.R;

import base.AppConstants;
import base.BaseFragment;


public class PrayersFragment extends BaseFragment implements View.OnClickListener {


    private ImageView imageViewAudioVideoHanumanJi;
    private ImageView imageViewReadAboutHanumanJi;
    private ImageView imageViewInformation;
    private RelativeLayout relativeLayoutAudioVideoMain;
    private  ImageView imageViewBell;
    private RelativeLayout relativeLayoutReadAboutHanumanJi;

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        view  =  inflater.inflate(R.layout.prayers, container, false);

        initialiseViews();
        intialiseListeners();

        return view;
    }


    private void initialiseViews() {

       imageViewInformation = (ImageView)view.findViewById(R.id.imageViewInformation);
       relativeLayoutAudioVideoMain = (RelativeLayout)view.findViewById(R.id.relativeLayoutAudioVideoMain);
       imageViewBell  = (ImageView)view.findViewById(R.id.imageViewBell);
       relativeLayoutReadAboutHanumanJi = (RelativeLayout)view.findViewById(R.id.relativeLayoutReadAboutHanumanJi);
    }

    private void intialiseListeners() {


        imageViewInformation.setOnClickListener(this);
        relativeLayoutAudioVideoMain.setOnClickListener(this);
        imageViewBell.setOnClickListener(this);
        relativeLayoutReadAboutHanumanJi.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){


            case R.id.imageViewInformation:
                mActivity.pushFragments(AppConstants.TAB_PRAYERS, new Information(),true,true);
                break;

            case R.id.relativeLayoutAudioVideoMain:
                mActivity.pushFragments(AppConstants.TAB_PRAYERS, new AudioVideoHanumanJi(),true,true);
                break;
            case R.id.imageViewBell:
                mActivity.pushFragments(AppConstants.TAB_PRAYERS, new Bell(), true, true);
                break;
            case R.id.relativeLayoutReadAboutHanumanJi:
                mActivity.pushFragments(AppConstants.TAB_PRAYERS, new AartiEnglish(), true, true);
                break;
            default:
                break;
        }
    }

    private void playSound() {


        try {
            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone r = RingtoneManager.getRingtone(getActivity().getApplicationContext(), notification);
            r.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
