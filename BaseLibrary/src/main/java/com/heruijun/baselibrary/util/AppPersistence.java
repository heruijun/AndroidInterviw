package com.heruijun.baselibrary.util;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by heruijun on 2018/2/18.
 */

public class AppPersistence {
    public final static String FILE_NAME = "AppPersistence";
    protected HashMap<String, Object> settings = null;
    protected FilePersistence filePersistence;

    public AppPersistence(Context context) {
        this.filePersistence = new FilePersistence(context);
    }

    public void set(String key, Object value) {
        loadSettings();
        settings.put(key, value);
    }

    public void set(Map<String, Object> map) {
        loadSettings();
        Iterator<Map.Entry<String, Object>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Object> entry = (Map.Entry<String, Object>) iterator.next();
            settings.put(entry.getKey(), entry.getValue());
        }
    }

    public Object get(String key) {
        loadSettings();
        return settings.get(key);
    }

    @SuppressWarnings("unchecked")
    protected void loadSettings() {
        if (null != settings) {
            settings.clear();
        }
        settings = (HashMap<String, Object>) filePersistence.readObject(FILE_NAME);
        if (settings == null) {
            settings = new HashMap<String, Object>();
        }
    }

    public void saveSettings() {
        filePersistence.writerObject(FILE_NAME, settings);
        settings = null;
    }

    public void clearPersist() {
        settings.clear();
        filePersistence.writerObject(FILE_NAME, settings);
    }

    public static class FilePersistence {
        private Context context;

        public FilePersistence(Context context) {
            this.context = context;
        }

        public void writerObject(String fileName, Object object) {
            FileOutputStream outStream = null;
            ObjectOutputStream objectOutputStream = null;
            // Write
            try {
                outStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
                objectOutputStream = new ObjectOutputStream(outStream);
                objectOutputStream.writeObject(object);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (objectOutputStream != null) {
                    try {
                        objectOutputStream.close();
                    } catch (IOException e) {
                    }
                }
                if (outStream != null) {
                    try {
                        outStream.close();
                    } catch (IOException e) {
                    }
                }
            }
        }

        public Object readObject(String fileName) {
            FileInputStream freader = null;
            ObjectInputStream objectInputStream = null;
            try {
                freader = context.openFileInput(fileName);
                objectInputStream = new ObjectInputStream(freader);
                return objectInputStream.readObject();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (objectInputStream != null) {
                    try {
                        objectInputStream.close();
                    } catch (IOException e) {
                    }
                }
                if (freader != null) {
                    try {
                        freader.close();
                    } catch (IOException e) {
                    }
                }
            }
            return null;
        }
    }
}