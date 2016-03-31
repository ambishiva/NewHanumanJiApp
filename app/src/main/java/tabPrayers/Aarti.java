package tabPrayers;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.app.hanumanji.R;
import com.example.shiva.hanumanji.ZoomView;

import base.AppMainTabActivity;
import base.BaseFragment;


public class Aarti extends BaseFragment {


	View view;
	ImageView imageViewBack;
	private ScrollView scrollViewDoha;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

        view   =   inflater.inflate(R.layout.aarti, container, false);
		initialiseViews();
		imageViewBack.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				((AppMainTabActivity)getActivity()).onBackPressed();

			}
		});
        return view;
	}



	private void initialiseViews() {

		imageViewBack = (ImageView)view.findViewById(R.id.imageViewBack);
		scrollViewDoha = (ScrollView)view.findViewById(R.id.scrollViewDoha);

	}

}
