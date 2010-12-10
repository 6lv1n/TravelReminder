package com.travelreminder.android22;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.TreeSet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Environment;
import android.util.Log;

public class TravelReminderStorage {

	private String STORAGE_FILENAME;
	private String STORAGE_DIR;
	private String STORAGE_FULL_PATH;
	private FileInputStream storageFileIn;
	private FileOutputStream storageFileOut;
	
	private static final String TAG = "TravelReminderStorage";
	
	/** Called when the activity is first created. */
	// @Override
	/*
	 * protected void onCreate(Bundle savedInstanceState) {
	 * super.onCreate(savedInstanceState); }
	 */

	public TravelReminderStorage(String StorageFileName) {
		STORAGE_FILENAME = StorageFileName;
		//FIXME create intermediary directory
		//STORAGE_DIR = Environment.getExternalStorageDirectory() + "/TR/";
		STORAGE_DIR = Environment.getExternalStorageDirectory() + "/";
		STORAGE_FULL_PATH = STORAGE_DIR + STORAGE_FILENAME;
	}

	public boolean isSdCardAvailable() {
		String state = Environment.getExternalStorageState();
		if (Environment.MEDIA_MOUNTED.equals(state)) {
			return true;
		} else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isSdCardWritable() {
		String state = Environment.getExternalStorageState();
		if (Environment.MEDIA_MOUNTED.equals(state)) {
			return true;
		} else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
			return false;
		} else {
			return false;
		}
	}

	public void initStorage() {
		if (isSdCardAvailable()) {
			try {
				File storageFile = new File(STORAGE_FULL_PATH);
				if (!storageFile.exists()) {
					if (isSdCardWritable())
						if (!storageFile.createNewFile())
							throw new IOException();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public TreeSet<Travel> readStorage() {
		StringBuffer contents = new StringBuffer();
		BufferedReader input = null;
		try {
			input = new BufferedReader(new FileReader(STORAGE_FULL_PATH), 1);
			int i;
			String x = "";
			String line = null;
			while ((line = input.readLine()) != null) {
				contents.append(line);
				contents.append(System.getProperty("line.separator"));
			}
			String jsontext = contents.toString();
			JSONArray entries = new JSONArray(jsontext);
			x = "JSON parsed.\nThere are [" + entries.length() + "]\n\n";
			for (i = 0; i < entries.length(); i++) {
				JSONObject post = entries.getJSONObject(i);
				x += "------------\n";
				JSONObject post2 = post.getJSONObject("menu");
				x += "id:" + post2.getString("id") + "\n";
				x += "value" + post2.getString("value") + "\n\n";
			}
			x += "------------\n";
			Log.v(TAG, x);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return new TreeSet<Travel>();
	}

	public void writeStorage(String storageData) {
		BufferedWriter buf = null;
		
		try {
			buf = new BufferedWriter(new FileWriter(STORAGE_FULL_PATH));
			buf.write(storageData, 0, storageData.length());
			buf.flush();
			buf.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
		}
	}

	public void setStorageFileOut(FileOutputStream trStorageFileOut) {
		this.storageFileOut = trStorageFileOut;
	}

	public FileOutputStream getStorageFileOut() {
		return storageFileOut;
	}

	public void setStorageFileIn(FileInputStream trStorageFileIn) {
		this.storageFileIn = trStorageFileIn;
	}

	public FileInputStream getStorageFileIn() {
		return storageFileIn;
	}
}
