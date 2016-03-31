package tabMala;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.app.hanumanji.R;

/**
 * Created by DELL on 08-03-2016.
 */
public class BeadActivity extends Activity {


    private ImageView imageViewDismiss;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bead_info);
        imageViewDismiss = (ImageView)findViewById(R.id.imageViewDismiss);
        imageViewDismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
    }
}
