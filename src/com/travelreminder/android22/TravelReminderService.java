package com.travelreminder.android22;

import java.util.Timer;

import android.app.NotificationManager;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Binder;
import android.os.IBinder;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;


public class TravelReminderService extends Service implements ServiceConnection {

	private Timer timer;
	private NotificationManager mNM;
	private TravelReminderService serviceBinder;

	// This is the object that receives interactions from clients. See
	// RemoteService for a more complete example.
	private final IBinder trBinder = new TravelReminderBinder();

	/**
	 * Class for clients to access. Because we know this service always runs in
	 * the same process as its clients, we don't need to deal with IPC.
	 */
	public class TravelReminderBinder extends Binder {
		TravelReminderService getService() {
			return TravelReminderService.this;
		}
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return trBinder;
	}
	
	
	public void onServiceConnected(ComponentName name, IBinder service) {
		serviceBinder = ((TravelReminderService.TravelReminderBinder) service).getService();
		Log.i("TRService", "Connexion du service");
	}

	public void onServiceDisconnected(ComponentName name) {
		serviceBinder = null;
		Log.i("TRService", "Deconnexion du service");
	}

	public void onStart(Intent intent, int startId) {
		String txtToast = "TR started ! " + startId;
		Toast toast = Toast.makeText(getApplicationContext(), txtToast, Toast.LENGTH_SHORT);
		toast.show();
	}
		// ---utilisation de la class LocationManager pour le gps---
		/*LocationManager objgps = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

		// *************ecouteur ou listener*********************
		Myobjlistener objlistener = new Myobjlistener();
		objgps.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0,
				objlistener);/
	/*
	 * @Override public void onCreate() { super.onCreate(); timer = new Timer();
	 * Log.d(this.getClass().getName(), "onCreate"); mNM =
	 * (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
	 * 
	 * // Display a notification about us starting. We put an icon in the status
	 * bar. showNotification();
	 * 
	 * }
	 * 
	 * @Override public int onStartCommand(Intent intent, int flags, int
	 * startId) { Log.d(this.getClass().getName(), "Received start id " +
	 * startId + ": " + intent); timer.scheduleAtFixedRate(new TimerTask() {
	 * public void run() { // Executer de votre tâche } }, 0, 60000); return
	 * START_STICKY; }
	 * 
	 * @Override public IBinder onBind(Intent intent) { return mBinder; }
	 */

	/*
	 * @Override public void onDestroy() { Log.d(this.getClass().getName(),
	 * "onDestroy"); this.timer.cancel();
	 * 
	 * // Cancel the persistent notification.
	 * mNM.cancel(R.string.local_service_started);
	 * 
	 * // Tell the user we stopped. Toast.makeText(this,
	 * R.string.local_service_stopped, Toast.LENGTH_SHORT).show();
	 * 
	 * }
	 */

	/*
	 * /** Show a notification while this service is running.
	 */
	/*
	 * private void showNotification() { // In this sample, we'll use the same
	 * text for the ticker and the expanded notification CharSequence text =
	 * getText(R.string.local_service_started);
	 * 
	 * // Set the icon, scrolling text and timestamp Notification notification =
	 * new Notification(R.drawable.icon, text, System.currentTimeMillis());
	 * 
	 * // The PendingIntent to launch our activity if the user selects this
	 * notification PendingIntent contentIntent =
	 * PendingIntent.getActivity(this, 0, new Intent(this,
	 * TravelReminder.class), 0);
	 * 
	 * // Set the info for the views that show in the notification panel.
	 * notification.setLatestEventInfo(this,
	 * getText(R.string.local_service_started), text, contentIntent);
	 * 
	 * // Send the notification. // We use a layout id because it is a unique
	 * number. We use it later to cancel.
	 * mNM.notify(R.string.local_service_started, notification); }
	 */

}

	