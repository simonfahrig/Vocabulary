package com.fahrig.familie.vocabulary;

import androidx.preference.PreferenceFragmentCompat;
import android.os.Bundle;

public class MySettingsFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);
    }
}