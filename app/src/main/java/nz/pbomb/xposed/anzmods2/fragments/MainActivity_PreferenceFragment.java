package nz.pbomb.xposed.anzmods2.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import nz.pbomb.xposed.anzmods2.R;


public class MainActivity_PreferenceFragment extends PreferenceFragment {
    public MainActivity_PreferenceFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences__main_activity);
    }
}
