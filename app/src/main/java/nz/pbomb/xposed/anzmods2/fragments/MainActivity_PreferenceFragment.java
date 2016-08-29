package nz.pbomb.xposed.anzmods2.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;

import nz.pbomb.xposed.anzmods2.R;
import nz.pbomb.xposed.anzmods2.activities.NestedPreferenceActivity;
import nz.pbomb.xposed.anzmods2.preferences.PreferencesSettings;


public class MainActivity_PreferenceFragment extends PreferenceFragment implements Preference.OnPreferenceClickListener {
    public MainActivity_PreferenceFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences__main);

        findPreference(PreferencesSettings.KEYS.MAIN.ANZ_MOBILE_PAY).setOnPreferenceClickListener(this);
        findPreference(PreferencesSettings.KEYS.MAIN.ANZ_SHIELD).setOnPreferenceClickListener(this);
        findPreference(PreferencesSettings.KEYS.MAIN.WESTPAC).setOnPreferenceClickListener(this);
    }

    @Override
    public boolean onPreferenceClick(Preference preference) {
        Intent intent = null;
        switch (preference.getKey()) {
            case PreferencesSettings.KEYS.MAIN.ANZ_MOBILE_PAY:
                intent = new Intent(getActivity(), NestedPreferenceActivity.class);
                intent.putExtra("id", R.xml.preferences__anz_mobile_pay);
                intent.putExtra("title", preference.getTitle());
                //intent.putExtra("preference", preference.getKey());
                break;
            case PreferencesSettings.KEYS.MAIN.ANZ_SHIELD:
                intent = new Intent(getActivity(), NestedPreferenceActivity.class);
                intent.putExtra("id", R.xml.preferences__anz_shield);
                intent.putExtra("title", preference.getTitle());
                //intent.putExtra("preference", preference.getKey());
                break;
            case PreferencesSettings.KEYS.MAIN.WESTPAC:
                intent = new Intent(getActivity(), NestedPreferenceActivity.class);
                intent.putExtra("id", R.xml.preferences__westpac);
                intent.putExtra("title", preference.getTitle());
                //intent.putExtra("preference", preference.getKey());
                break;
            /*case PREFERENCES.KEYS.MAIN.DEBUG:
                CheckBoxPreference checkBoxPreference = (CheckBoxPreference) preference;
                if(!GLOBAL.DEBUG) {
                    if (checkBoxPreference.isChecked()) {
                        getActivity().setTitle(getResources().getString(R.string.app_name) + " (Debug Mode)");
                    } else {
                        getActivity().setTitle(getResources().getString(R.string.app_name));
                    }
                }

                SharedPreferences sharedPref = getActivity().getSharedPreferences(PREFERENCES.SHARED_PREFS_FILE_NAME, Context.MODE_WORLD_READABLE);
                SharedPreferences.Editor sharedPrefEditor = sharedPref.edit();
                sharedPrefEditor.putBoolean(PREFERENCES.KEYS.MAIN.DEBUG, checkBoxPreference.isChecked());
                sharedPrefEditor.apply();

                return true;*/
        }
        startActivity(intent);
        return true;
    }
}
