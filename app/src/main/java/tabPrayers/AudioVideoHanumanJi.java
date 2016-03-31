package tabPrayers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.app.hanumanji.R;

import java.util.ArrayList;

import adapter.AudioVideoAdapter;
import base.AppMainTabActivity;
import base.BaseFragment;
import data.Audio;
import data.Video;


public class AudioVideoHanumanJi extends BaseFragment {


	View view;

	private ArrayList<Video> arrayListVideo = new ArrayList<Video>();
	private ArrayList<Video> arrayListAudio =  new ArrayList<>();
	private ArrayList<Video> arrayListAll =  new ArrayList<>();

	private ListView listViewAudioVideoHanumanJi;
	private RadioGroup radioGroup1;
	private AudioVideoAdapter audioVideoAdapter;
	private ImageView imageViewBack;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

        view   =   inflater.inflate(R.layout.audiovideo, container, false);
		initialiseViews();
		addVideosData();
		addAudioData();
		addAllData();
		initialiseListeners();
		initialiseAudioData();
        return view;
	}

	private void initialiseAudioData() {

		audioVideoAdapter = new AudioVideoAdapter(getActivity(),R.layout.video_audio_item,arrayListAudio);
		listViewAudioVideoHanumanJi.setAdapter(audioVideoAdapter);
	}

	private void initialiseListeners() {

		radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
		{
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId)
			{
				switch (checkedId)
				{
					case R.id.radioAndroid:
						//Toast.makeText(getActivity(), "Android RadioButton checked", Toast.LENGTH_SHORT).show();

						audioVideoAdapter = new AudioVideoAdapter(getActivity(),R.layout.video_audio_item,arrayListAudio);
						listViewAudioVideoHanumanJi.setAdapter(audioVideoAdapter);
						break;
					case R.id.radioiPhone:
						audioVideoAdapter = new AudioVideoAdapter(getActivity(),R.layout.video_audio_item,arrayListVideo);
						listViewAudioVideoHanumanJi.setAdapter(audioVideoAdapter);
						//Toast.makeText(getActivity(), "iPhone RadioButton checked", Toast.LENGTH_SHORT).show();
						break;
					case R.id.radioWindows:
						audioVideoAdapter = new AudioVideoAdapter(getActivity(),R.layout.video_audio_item,arrayListAll);
						listViewAudioVideoHanumanJi.setAdapter(audioVideoAdapter);
						//Toast.makeText(getActivity(), "windows RadioButton checked", Toast.LENGTH_SHORT).show();
						break;
					default:
						break;
				}
			}
		});

		imageViewBack.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				((AppMainTabActivity)getActivity()).onBackPressed();
			}
		});
	}

	private void initialiseViews() {

		listViewAudioVideoHanumanJi = (ListView)view.findViewById(R.id.listViewAudioVideoHanumanJi);
		imageViewBack = (ImageView)view.findViewById(R.id.imageViewBack);
		radioGroup1 = (RadioGroup)view.findViewById(R.id.radioGroup1);
	}

	private void addAllData() {

		for(int i = 0;i<arrayListAudio.size();i++){
			arrayListAll.add(arrayListAudio.get(i));
		}

		for(int i = 0;i<arrayListVideo.size();i++){
			arrayListAll.add(arrayListVideo.get(i));
		}

	}

	private void addAudioData() {

		Video a1 = new Video("Shri Hanuman Chalisa Bhajans By Hariharan","https://www.youtube.com/watch?v=PlgIlN5gmQw");
		Video a2 = new Video("Hanuman Mantras for Success - Powerful Mantra to be relieved from Troubles","https://www.youtube.com/watch?v=WyGNbUmfpio");
		Video a3 = new Video("Katha Ram Bhakt Hanuman Ki By Hariharan","https://www.youtube.com/watch?v=M1YzJ3cRZpo");
		Video a4 = new Video("Hanuman Gatha By Kumar Vishu","https://www.youtube.com/watch?v=CuJ706umEH8");
		Video a5 = new Video("Hanuman Amritwani By Anuradha Paudwal","https://www.youtube.com/watch?v=ps8AU0BQhaE");
		Video a6 = new Video("Pawansut Hanuman, Shri Hanuman Ji Ki Mahima By Chand Kumar ","https://www.youtube.com/watch?v=eKfmVoilj90");

		arrayListAudio.add(a1);
		arrayListAudio.add(a2);
		arrayListAudio.add(a3);
		arrayListAudio.add(a4);
		arrayListAudio.add(a5);
		arrayListAudio.add(a6);
	}

	private void addVideosData() {

		Video v1 = new Video("Hanuman Mantras","https://www.youtube.com/watch?v=nr07kp_y5XQ");
		Video v2 = new Video("Shree Hanuman Mantra Jaap - 108 times","https://www.youtube.com/watch?v=YpU4NtujBY0");
		Video v3 = new Video("Bhajans By Hariom Sharan, Hariharan, Lata Mangeshkar","https://www.youtube.com/watch?v=ASb9b3pHglU");
		Video v4 = new Video("Bajrang Baan","https://www.youtube.com/watch?v=ASb9b3pHglU");
		Video v5 = new Video("Sankat Mochan","https://www.youtube.com/watch?v=1zS2sgVEjvs");
		Video v6 = new Video("Sunder Kand","https://www.youtube.com/watch?v=d4yKGNg6PzE");

		Video v7 = new Video("Hanuman Chalisa","https://www.youtube.com/watch?v=D8hF5uSP3u8");
		Video v8 = new Video("Ramayanan Full Cartoon Movie for Kids English","https://www.youtube.com/watch?v=UA2BYSivPXA");
		Video v9 = new Video("Ramayanan Full Cartoon Movie for Kids Tamil","https://www.youtube.com/watch?v=XcILIU0yhm8");
		Video v10 = new Video("Ramayanan Full Cartoon Movie for Kids Malyalam"," https://www.youtube.com/watch?v=nqj6AO9CHFo");

		Video v11 = new Video("Hanuman Full Cartoon Movie for Kids English","https://www.youtube.com/watch?v=dlnB8k9Eav8");
		Video v12 = new Video("Hanuman Full Cartoon Movie for Kids Tamil","https://www.youtube.com/watch?v=gntC5VcPmms");
		Video v13 = new Video("Hanuman Full Cartoon Movie for Kids Malyalam","https://www.youtube.com/watch?v=mokons1w-vo");
		Video v14 = new Video("Ramayanan Full Cartoon Movie for Kids Malyalam"," https://www.youtube.com/watch?v=nqj6AO9CHFo");


		arrayListVideo.add(v1);
		arrayListVideo.add(v2);
		arrayListVideo.add(v3);
		arrayListVideo.add(v4);
		arrayListVideo.add(v5);
		arrayListVideo.add(v6);
		arrayListVideo.add(v7);
		arrayListVideo.add(v8);
		arrayListVideo.add(v9);
		arrayListVideo.add(v10);
		arrayListVideo.add(v11);
		arrayListVideo.add(v12);
		arrayListVideo.add(v13);
		arrayListVideo.add(v14);
	}

}
