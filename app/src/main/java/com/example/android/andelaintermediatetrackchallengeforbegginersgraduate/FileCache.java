package com.example.android.andelaintermediatetrackchallengeforbegginersgraduate;

import android.content.Context;
import android.os.Environment;

import java.io.File;

import static android.R.attr.id;

/**
 * Created by NORMAL on 8/23/2017.
 */

public class FileCache {
    private File cacheDir;

    public FileCache(Context context) {
        if (android.os.Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED))
            cacheDir = new File(
                    android.os.Environment.getExternalStorageDirectory(),
                    "JsonParseTutorialCache");
        else
            cacheDir = context.getCacheDir();
        if (!cacheDir.exists())
            cacheDir.mkdirs();
    }
    public File getFile(String url) {
        String filename = String.valueOf(url.hashCode());
        File f = new File(cacheDir, filename);
        return f;
    }
    public void clear() {
        File[] files = cacheDir.listFiles();
        if (files == null)
            return;
        for (File f : files)
            f.delete();
    }
}
