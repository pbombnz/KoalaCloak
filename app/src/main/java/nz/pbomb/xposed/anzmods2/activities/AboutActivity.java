package nz.pbomb.xposed.anzmods2.activities;

import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.MenuItem;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import nz.pbomb.xposed.anzmods2.R;

public class AboutActivity extends AppCompatActivity {

    @BindView(R.id.AboutActivity__textView__author_1) protected TextView author1;
    @BindView(R.id.AboutActivity__textView__contributer_text_1) protected TextView contributor1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);

        author1.setMovementMethod(LinkMovementMethod.getInstance());
        contributor1.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
