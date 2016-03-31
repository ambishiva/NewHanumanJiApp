package tabReminders;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.app.hanumanji.R;

import base.BaseFragment;


public class AppTabCSecondFragment extends BaseFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.app_tab_b_second_screen, container, false);
        return view;
	}
}
