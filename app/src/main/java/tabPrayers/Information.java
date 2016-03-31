package tabPrayers;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


import com.app.hanumanji.R;

import base.AppConstants;
import base.AppMainTabActivity;
import base.BaseFragment;


public class Information extends BaseFragment  implements View.OnClickListener{


	View view;

	private RelativeLayout linearLayoutAboutUs;
	private RelativeLayout linearLayoutTermsOfUse;
	private RelativeLayout linearLayoutContactUs;
	private RelativeLayout linearLayoutShare;
	private RelativeLayout linearLayoutHelp;
	private RelativeLayout linearLayoutPrivacyPolicy;
	private RelativeLayout relativeLayoutHelp;
	private ImageView imageViewBack;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

        view  = inflater.inflate(R.layout.prayers_menu, container, false);
		initialiseViews();
		initialiseListeners();
        return view;
	}


	private void initialiseViews() {

		linearLayoutAboutUs = (RelativeLayout)view.findViewById(R.id.lienarLayoutAboutUs);
		linearLayoutTermsOfUse = (RelativeLayout)view.findViewById(R.id.linearLayoutTermsOfUse);
		linearLayoutContactUs = (RelativeLayout)view.findViewById(R.id.linearLayoutContactUs);
		linearLayoutShare = (RelativeLayout)view.findViewById(R.id.linearLayoutShare);
		imageViewBack = (ImageView)view.findViewById(R.id.imageViewBack);
		linearLayoutPrivacyPolicy = (RelativeLayout)view.findViewById(R.id.linearLayoutPrivacyPolicy);
		relativeLayoutHelp = (RelativeLayout)view.findViewById(R.id.relativeLayoutHelp);
	}


	private void initialiseListeners() {

		linearLayoutAboutUs.setOnClickListener(this);
		linearLayoutTermsOfUse.setOnClickListener(this);
		linearLayoutContactUs.setOnClickListener(this);
		linearLayoutShare.setOnClickListener(this);
		imageViewBack.setOnClickListener(this);
		linearLayoutPrivacyPolicy.setOnClickListener(this);
		relativeLayoutHelp.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()){
			case R.id.lienarLayoutAboutUs:
				mActivity.pushFragments(AppConstants.TAB_PRAYERS, new AboutUs(),true,true);
				break;
			case R.id.linearLayoutTermsOfUse:
				mActivity.pushFragments(AppConstants.TAB_PRAYERS, new TermsOfUse(),true,true);
				break;
			case R.id.linearLayoutContactUs:
				Intent intent = new Intent (Intent.ACTION_VIEW , Uri.parse("mailto:" + "hi@awesomeactually.com"));
				intent.putExtra(Intent.EXTRA_SUBJECT, "Hello Hanuman - FeedBack");
				intent.putExtra(Intent.EXTRA_TEXT, "Sent from Android");
				startActivity(intent);
				break;
			case R.id.linearLayoutShare:
				mActivity.pushFragments(AppConstants.TAB_PRAYERS, new ShareYourLove(),true,true);
				break;
			case R.id.linearLayoutHelp:
				break;
			case R.id.linearLayoutPrivacyPolicy:
				mActivity.pushFragments(AppConstants.TAB_PRAYERS, new PrivacyPolicy(),true,true);
				break;
			case R.id.relativeLayoutHelp:
				mActivity.pushFragments(AppConstants.TAB_PRAYERS, new Help(),true,true);
				break;
			case R.id.imageViewBack:
				((AppMainTabActivity)getActivity()).onBackPressed();
								break;

		}
	}
}
