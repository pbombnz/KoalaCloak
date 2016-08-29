package nz.pbomb.xposed.anzmods2.activities;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import nz.pbomb.xposed.anzmods2.R;
import nz.pbomb.xposed.anzmods2.fragments.NestedPreferenceActivity_PreferenceFragment;

public class NestedPreferenceActivity extends AppCompatActivity {
    private NestedPreferenceActivity_PreferenceFragment preferenceFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nested_preference);
        getSupportActionBar().setHomeButtonEnabled(true);


        // We only proceed if we came from the Preference intents created in the MainActivity
        Intent i;
        if(getIntent() != null) {
            i = getIntent();
            if (!i.hasExtra("id") && !i.hasExtra("title")) {
                throw new IllegalArgumentException("Wrong Intent Parameters");
            }
        } else {
            throw new IllegalArgumentException("Needs Intent to use Preference Activity");
        }

        // Set the Preference Acitvity's Title and Preferences based on intent
        setTitle(getIntent().getStringExtra("title"));
        preferenceFragment = new NestedPreferenceActivity_PreferenceFragment();
        preferenceFragment.setArguments(getIntent().getExtras());

        // get an instance of FragmentTransaction from your Activity
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        //add a fragment
        fragmentTransaction.add(R.id.NestedPreferenceActivity_FragmentHolderLayout, preferenceFragment);
        fragmentTransaction.commit();
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
