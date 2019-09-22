package com.huseyinaltuntas.mvvmgithubrepoproject.data;

import com.huseyinaltuntas.mvvmgithubrepoproject.App;
import com.huseyinaltuntas.mvvmgithubrepoproject.data.db.database.LogDatabase;
import com.huseyinaltuntas.mvvmgithubrepoproject.data.network.services.RepoService;
import com.preference.PowerPreference;
import com.preference.Preference;

public class DataManager {

    private static DataManager sInstance;

    private DataManager() {
    }

    public static synchronized DataManager getInstance() {
        if (sInstance == null) {
            sInstance = new DataManager();
        }
        return sInstance;
    }

    public Preference getPrefs() {
        return PowerPreference.defult();
    }

    public LogDatabase getLogDatabse() {
        return LogDatabase.getInstance(App.getInstance());
    }

    public RepoService getRepoService() {
        return RepoService.getInstance();
    }
}
