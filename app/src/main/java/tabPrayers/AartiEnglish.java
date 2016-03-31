package tabPrayers;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.hanumanji.R;
import com.example.shiva.hanumanji.SimpleGestureFilter;

import base.AppMainTabActivity;
import base.BaseFragment;

public class AartiEnglish extends BaseFragment implements SimpleGestureFilter.SimpleGestureListener {

	public static final String EXTRA_MESSAGE = "EXTRA_MESSAGE";
	View v;
	private ImageView imageViewBack;
	private LinearLayout linearLayoutAarti;
	private SimpleGestureFilter detector;
	private SimpleGestureFilter.SimpleGestureListener simpleGestureListener;
	private ScrollView scrollView;
	private float x1, x2;


	private TextView textViewFirstDoha;
	private TextView textViewSecondDoha;
	private TextView textViewThirdDoha;
	private TextView textViewFourthDoha;
	private TextView textViewFifthDoha;
	private TextView textViewSixthDoha;
	private TextView textViewSeventhDoha;
	private TextView textViewEigthDoha;
	private TextView textViewNinthDoha;
	private TextView textViewTenthDoha;
	private TextView textViewEleventhDoha;
	private TextView textViewTwelvethDoha;

	private TextView imageViewChangeAarti;


	
	public static final AartiEnglish newInstance(String message)
	{
		AartiEnglish f = new AartiEnglish();
		Bundle bdl = new Bundle(1);
	    bdl.putString(EXTRA_MESSAGE, message);
	    f.setArguments(bdl);
	    return f;
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
		//String message = getArguments().getString(EXTRA_MESSAGE);
		v = inflater.inflate(R.layout.aarti_english, container, false);
		// Detect touched area
		//simpleGestureListener = (SimpleGestureFilter.SimpleGestureListener) getActivity();
		//detector = new SimpleGestureFilter(getActivity(),simpleGestureListener);
		initialiseViews();
		initialiseListeners();
        return v;
    }



	private void initialiseViews() {

		imageViewBack = (ImageView)v.findViewById(R.id.imageViewBack);
		linearLayoutAarti = (LinearLayout)v.findViewById(R.id.linearLayoutAarti);
		scrollView = (ScrollView)v.findViewById(R.id.scrollViewDoha);

		textViewFirstDoha = (TextView)v.findViewById(R.id.textViewFirstDoha);
		textViewSecondDoha = (TextView)v.findViewById(R.id.textViewSecondDoha);
		textViewThirdDoha = (TextView)v.findViewById(R.id.textViewThirdDoha);
		textViewFourthDoha = (TextView)v.findViewById(R.id.textViewFourthDoha);
		textViewFifthDoha = (TextView)v.findViewById(R.id.textViewFifthDoha);
		textViewSixthDoha = (TextView)v.findViewById(R.id.textViewSixthDoha);
		textViewSeventhDoha = (TextView)v.findViewById(R.id.textViewSeventhDoha);
		textViewEigthDoha = (TextView)v.findViewById(R.id.textViewEigthDoha);
		textViewNinthDoha = (TextView)v.findViewById(R.id.textViewNinthDoha);
		textViewTenthDoha = (TextView)v.findViewById(R.id.textViewTenthDoha);
		textViewEleventhDoha = (TextView)v.findViewById(R.id.textViewEleventhDoha);
		textViewTwelvethDoha = (TextView)v.findViewById(R.id.textViewTwelvethDoha);

		imageViewChangeAarti = (TextView)v.findViewById(R.id.imageViewChangeAarti);
	}

	private void initialiseListeners() {

		imageViewBack.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				((AppMainTabActivity)getActivity()).onBackPressed();

			}
		});


		linearLayoutAarti.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {


				getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(
						R.anim.slide_in_right_new, 0, 0, R.anim.slide_out_right_new
				).add(R.id.container, new AartiHindi()).addToBackStack("animation").commit();

			}
		});

		linearLayoutAarti.setOnTouchListener(

				new View.OnTouchListener() {
					@Override
					public boolean onTouch(View v, MotionEvent event) {
						// TODO Auto-generated method stub

						switch (event.getAction()) {

							case MotionEvent.ACTION_DOWN:
								//scrollView.requestDisallowInterceptTouchEvent(true);
								x1 = event.getX();
								break;
							case MotionEvent.ACTION_UP:
								//scrollView.requestDisallowInterceptTouchEvent(true);
								x2 = event.getX();
								float deltaX = x2 - x1;
								if (deltaX < 0) {
									//scrollView.requestDisallowInterceptTouchEvent(true);
//									Toast.makeText(getActivity(),
//											"Right to Left swipe",
//											Toast.LENGTH_SHORT).show();
//									getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(
//											R.anim.slide_in_right_new, 0, 0, R.anim.slide_out_right_new
//									).add(R.id.container, new AartiHindi()).addToBackStack("animation").commit();
								} else if (deltaX > 0) {
//									Toast.makeText(getActivity(),
//											"Left to Right swipe",
//											Toast.LENGTH_SHORT).show();
//									getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(
//											R.anim.slide_in_left_new, 0, 0, R.anim.slide_out_left_new
//									).add(R.id.container, new AartiEnglish()).addToBackStack("animation").commit();
								}
								break;
						}

						return false;
					}
				});

		imageViewChangeAarti.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				textViewFirstDoha.setText(getActivity().getResources().getString(R.string.first_doha_hindi));
				textViewSecondDoha.setText(getActivity().getResources().getString(R.string.second_doha_hindi));
				textViewThirdDoha.setText(getActivity().getResources().getString(R.string.third_doha_hindi));
				textViewFourthDoha.setText(getActivity().getResources().getString(R.string.fourth_doha_hindi));
				textViewFifthDoha.setText(getActivity().getResources().getString(R.string.fifth_doha_hindi));
				textViewSixthDoha.setText(getActivity().getResources().getString(R.string.sixth_doha_hindi));
				textViewSeventhDoha.setText(getActivity().getResources().getString(R.string.seventh_doha_hindi));
				textViewEigthDoha.setText(getActivity().getResources().getString(R.string.eighth_doha_hindi));
				textViewNinthDoha.setText(getActivity().getResources().getString(R.string.ninth_doha_hindi));
				textViewTenthDoha.setText(getActivity().getResources().getString(R.string.tenth_doha_hindi));
				textViewEleventhDoha.setText(getActivity().getResources().getString(R.string.eleventh_doha_hindi));
				textViewTwelvethDoha.setText(getActivity().getResources().getString(R.string.twelth_doha_hindi));

			}
		});
	}

	@Override
	public void onViewStateRestored(Bundle savedInstanceState) {
		super.onViewStateRestored(savedInstanceState);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
	}

	@Override
	public void onResume() {
		super.onResume();

	}

	@Override
	public void onSwipe(int direction) {
		String str = "";

		switch (direction) {

			case SimpleGestureFilter.SWIPE_RIGHT : str = "Swipe Right";
				break;
			case SimpleGestureFilter.SWIPE_LEFT :  str = "Swipe Left";
				break;
			case SimpleGestureFilter.SWIPE_DOWN :  str = "Swipe Down";
				break;
			case SimpleGestureFilter.SWIPE_UP :    str = "Swipe Up";
				break;

		}
		Toast.makeText(getActivity(), str, Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onDoubleTap() {

	}


}
