package tabPrayers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.app.hanumanji.R;

import base.BaseFragment;


public class ReadAboutHanumanJi extends BaseFragment {


	View view;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

        view   =   inflater.inflate(R.layout.readabouthanumanji, container, false);
        return view;
	}

}
