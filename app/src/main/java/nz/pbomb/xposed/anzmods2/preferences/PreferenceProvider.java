package nz.pbomb.xposed.anzmods2.preferences;

import com.crossbowffs.remotepreferences.RemotePreferenceProvider;

import nz.pbomb.xposed.anzmods2.Common;

public class PreferenceProvider extends RemotePreferenceProvider {
    public PreferenceProvider() {
        super("nz.pbomb.xposed.anzmods2.preferences.provider", new String[] { Common.getInstance().SHARED_PREFS_FILENAME });
    }
}