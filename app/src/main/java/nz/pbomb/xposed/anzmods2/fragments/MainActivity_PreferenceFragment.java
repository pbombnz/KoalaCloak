package nz.pbomb.xposed.anzmods2.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;

import nz.pbomb.xposed.anzmods2.Common;
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
        findPreference(PreferencesSettings.KEYS.MAIN.DEBUG).setOnPreferenceClickListener(this);
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
            case PreferencesSettings.KEYS.MAIN.DEBUG:
                CheckBoxPreference checkBoxPreference = (CheckBoxPreference) preference;
                if(!Common.getInstance().DEBUG) {
                    if (checkBoxPreference.isChecked()) {
                        getActivity().setTitle(getResources().getString(R.string.app_name) + " (Debug Mode)");
                    } else {
                        getActivity().setTitle(getResources().getString(R.string.app_name));
                    }
                }

                SharedPreferences sharedPref = getActivity().getSharedPreferences(Common.getInstance().SHARED_PREFS_FILENAME, Context.MODE_WORLD_READABLE);
                SharedPreferences.Editor sharedPrefEditor = sharedPref.edit();
                sharedPrefEditor.putBoolean(PreferencesSettings.KEYS.MAIN.DEBUG, checkBoxPreference.isChecked());
                sharedPrefEditor.apply();
                return true;
        }
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
        return true;
    }
}
