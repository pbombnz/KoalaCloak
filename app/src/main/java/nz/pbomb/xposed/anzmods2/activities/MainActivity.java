package nz.pbomb.xposed.anzmods2.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.preference.PreferenceFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import nz.pbomb.xposed.anzmods2.Common;
import nz.pbomb.xposed.anzmods2.R;
import nz.pbomb.xposed.anzmods2.preferences.PreferencesSettings;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences mSharedPreferences;
    protected PreferenceFragment preferenceFragment;

    @SuppressLint("WorldReadableFiles")
    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSharedPreferences = getSharedPreferences(Common.getInstance().SHARED_PREFS_FILENAME, Context.MODE_WORLD_READABLE);

        preferenceFragment = (PreferenceFragment) getFragmentManager().findFragmentById(R.id.MainActivity_PreferenceFragment);

        new ValidationASyncTask().execute();
    }

    /**
     * Validates operations when the application is created. Firstly check that the
     * SharedPreferences exist and if it doesn't then create the SharedPreferences accordingly.
     * Also checks whether either ANZ or Semble (any version) is installed
     */
    protected class ValidationASyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            SharedPreferences.Editor sharedPrefEditor = mSharedPreferences.edit();

            // Create the SharedPreferences and set the defaults if they aren't already created
            if (!mSharedPreferences.contains(PreferencesSettings.KEYS.ANZ_MOBILE_PAY.SYSTEM_INTEGRITY)) {
                sharedPrefEditor.putBoolean(PreferencesSettings.KEYS.ANZ_MOBILE_PAY.SYSTEM_INTEGRITY, PreferencesSettings.DEFAULT_VALUES.ANZ_MOBILE_PAY.SYSTEM_INTEGRITY);
            }
            if (!mSharedPreferences.contains(PreferencesSettings.KEYS.ANZ_MOBILE_PAY.DEVELOPER_SETTINGS)) {
                sharedPrefEditor.putBoolean(PreferencesSettings.KEYS.ANZ_MOBILE_PAY.DEVELOPER_SETTINGS, PreferencesSettings.DEFAULT_VALUES.ANZ_MOBILE_PAY.DEVELOPER_SETTINGS);
            }
            if (!mSharedPreferences.contains(PreferencesSettings.KEYS.ANZ_SHIELD.DEVELOPER_SETTINGS)) {
                sharedPrefEditor.putBoolean(PreferencesSettings.KEYS.ANZ_SHIELD.DEVELOPER_SETTINGS, PreferencesSettings.DEFAULT_VALUES.ANZ_SHIELD.DEVELOPER_SETTINGS);
            }
            if (!mSharedPreferences.contains(PreferencesSettings.KEYS.WESTPAC.ROOT_DETECTION)) {
                sharedPrefEditor.putBoolean(PreferencesSettings.KEYS.WESTPAC.ROOT_DETECTION, PreferencesSettings.DEFAULT_VALUES.WESTPAC.ROOT_DETECTION);
            }
            if (!mSharedPreferences.contains(PreferencesSettings.KEYS.COMMBANK.ROOT_DETECTION)) {
                sharedPrefEditor.putBoolean(PreferencesSettings.KEYS.COMMBANK.ROOT_DETECTION, PreferencesSettings.DEFAULT_VALUES.COMMBANK.ROOT_DETECTION);
            }
            if (!mSharedPreferences.contains(PreferencesSettings.KEYS.MAIN.DEBUG)) {
                sharedPrefEditor.putBoolean(PreferencesSettings.KEYS.MAIN.DEBUG, PreferencesSettings.DEFAULT_VALUES.MAIN.DEBUG);
            }
            if (mSharedPreferences.getBoolean(PreferencesSettings.KEYS.MAIN.DEBUG, PreferencesSettings.DEFAULT_VALUES.MAIN.DEBUG) || Common.getInstance().DEBUG) {
                setTitle(getTitle() + " (Debug Mode)");
            }
            sharedPrefEditor.apply();

            if(Common.getInstance().DEBUG) {
                preferenceFragment.findPreference(PreferencesSettings.KEYS.MAIN.DEBUG).setEnabled(false);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            if (!isApplicationInstalled(Common.getInstance().PACKAGE_ANZ_AU_MOBILE_PAY)) {
                preferenceFragment.findPreference(PreferencesSettings.KEYS.MAIN.ANZ_MOBILE_PAY).setEnabled(false);
            }

            if (!isApplicationInstalled(Common.getInstance().PACKAGE_ANZ_AU_SHIELD)) {
                preferenceFragment.findPreference(PreferencesSettings.KEYS.MAIN.ANZ_SHIELD).setEnabled(false);
            }

            if (!isApplicationInstalled(Common.getInstance().PACKAGE_WESTPAC)) {
                preferenceFragment.findPreference(PreferencesSettings.KEYS.MAIN.WESTPAC).setEnabled(false);
            }

            if (!isApplicationInstalled(Common.getInstance().PACKAGE_COMMBANK)) {
                preferenceFragment.findPreference(PreferencesSettings.KEYS.MAIN.COMMBANK).setEnabled(false);
            }
        }
    }


    /**
     * Determines if an application is installed or not
     *
     * @param uri The package name of the application
     * @return True, if the package is installed, otherwise false
     */
    private boolean isApplicationInstalled(String uri) {
        PackageManager pm = getPackageManager();
        boolean appInstalled;
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            appInstalled = true;
        }
        catch (PackageManager.NameNotFoundException e) {
            appInstalled = false;
        }
        return appInstalled;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);  // Creates menu
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Determine which MenuItem was pressed and act accordingly based on the button pressed
        Intent intent = null;
        switch (item.getItemId()) {
            case R.id.MainActivity__menu_main__Donate:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.Paypal)));
                break;
            case R.id.MainActivity__menu_main__Contact:
                intent = new Intent(getApplicationContext(), ContactActivity.class);
                break;
            case R.id.MainActivity___menu_main__SourceCode:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.SourceCode)));
                break;
            case R.id.MainActivity__menu_main__XDAThread:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.XDAThread)));
                break;
            case R.id.MainActivity__menu_main__About:
                intent = new Intent(getApplicationContext(), AboutActivity.class);
                break;
        }
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.nothing);
        return true;
    }
    
    
}
