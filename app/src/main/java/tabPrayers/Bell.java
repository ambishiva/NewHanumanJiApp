package tabPrayers;

import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;

import com.app.hanumanji.R;
import com.squareup.seismic.ShakeDetector;

import java.util.ArrayList;

import adapter.AudioVideoAdapter;
import base.AppMainTabActivity;
import base.BaseFragment;
import data.Video;


public class Bell extends BaseFragment  implements ShakeDetector.Listener{


	View view;


	private ImageView imageViewBack;
	private ImageView imageViewBell;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

        view   =   inflater.inflate(R.layout.bell, container, false);
		SensorManager sensorManager = (SensorManager)getActivity().getSystemService(getActivity().SENSOR_SERVICE);
		ShakeDetector sd = new ShakeDetector(this);
		sd.start(sensorManager);
		initialiseViews();
		initialiseListeners();

        return view;
	}



	private void initialiseListeners() {


		imageViewBack.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				((AppMainTabActivity) getActivity()).onBackPressed();
			}
		});

		imageViewBell.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				playSound();

			}
		});
	}

	private void initialiseViews() {


		imageViewBack = (ImageView)view.findViewById(R.id.imageViewBack);
		imageViewBell = (ImageView)view.findViewById(R.id.imageViewBell);

	}
	private void playSound() {
		try {

			MediaPlayer mp = MediaPlayer.create(getActivity(), R.raw.temple_bell_2426);
			if(mp!=null ){
				mp.start();
				mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
					@Override
					public void onCompletion(MediaPlayer mp) {

						mp.release();
					}
				});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void hearShake() {
		playSound();
	}
}
