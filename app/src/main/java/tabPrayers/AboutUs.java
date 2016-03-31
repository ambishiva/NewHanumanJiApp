package tabPrayers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.app.hanumanji.R;

import base.AppMainTabActivity;
import base.BaseFragment;


public class AboutUs extends BaseFragment {


	View view;
	ImageView imageViewBack;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

        view   =   inflater.inflate(R.layout.about_us_new, container, false);
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
	}

}
