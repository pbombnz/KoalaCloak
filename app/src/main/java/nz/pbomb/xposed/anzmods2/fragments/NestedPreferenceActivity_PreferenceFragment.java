package nz.pbomb.xposed.anzmods2.fragments;


import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import nz.pbomb.xposed.anzmods2.R;

public class NestedPreferenceActivity_PreferenceFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(getArguments().getInt("id"));
    }
}
