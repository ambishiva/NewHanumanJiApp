package tabPrayers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.app.hanumanji.R;

import base.AppMainTabActivity;
import base.BaseFragment;


public class TermsOfUse extends BaseFragment {


	View view;
	private ImageView imageViewBack;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

        view   =   inflater.inflate(R.layout.terms_of_use, container, false);
		imageViewBack = (ImageView)view.findViewById(R.id.imageViewBack);
		imageViewBack.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {


				((AppMainTabActivity)getActivity()).onBackPressed();
			}
		});
        return view;
	}

}
