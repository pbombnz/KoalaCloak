package nz.pbomb.xposed.anzmods2.activities;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import butterknife.ButterKnife;
import butterknife.OnClick;
import nz.pbomb.xposed.anzmods2.R;

public class ContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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

    @OnClick(R.id.ContactActivity_TwitterButton)
    public void onTwitterButtonClicked() {
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("twitter://user?user_id=528317895")));
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/PBombNZ")));
        }
    }

    @OnClick(R.id.ContactActivity_EmailButton)
    public void onEmailButtonClicked() {
        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:pbomb.nz@gmail.com"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "[XPOSED][ANZMPRB] General Feedback");
        intent.putExtra(Intent.EXTRA_TEXT,
                "Build Fingerprint: "+ Build.FINGERPRINT + "\n" +
                        "Build Manufacturer: "+ Build.MANUFACTURER + "\n" +
                        "Build Brand: "+ Build.BRAND + "\n" +
                        "Build Model: "+ Build.MODEL + "\n" +
                        "Build Product: "+ Build.PRODUCT + "\n" +
                        "\n" +
                        "Android OS Information: " + Build.VERSION.RELEASE + "\n" +
                        "\n" +
                        "Additional Text (Insert Feedback/Bug Report/Feature Request Here):\n");

        intent = Intent.createChooser(intent, "Chooser Email Client");
        startActivity(intent);
    }

    @OnClick(R.id.ContactActivity_XDAThreadButton)
    public void onXDAButtonClicked() {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.XDAThread))));
    }
}
