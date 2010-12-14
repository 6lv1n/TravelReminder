package com.travelreminder.android22;

import java.util.ArrayList;
import android.content.Context;
import android.graphics.drawable.Drawable;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

public class StepItemizedOverlay extends ItemizedOverlay<OverlayItem> {

	private ArrayList<OverlayItem> mOverlays = new ArrayList<OverlayItem>();
	private Context mContext;

	public StepItemizedOverlay(Drawable defaultMarker) {
		super(boundCenterBottom(defaultMarker));
	}

	public StepItemizedOverlay(Drawable defaultMarker, Context context) {
		super(defaultMarker);
		setmContext(context);
	}

	@Override
	protected OverlayItem createItem(int i) {
		return mOverlays.get(i);
	}

	@Override
	public int size() {
		return mOverlays.size();
	}

	public void addOverlay(OverlayItem overlay) {
		mOverlays.add(overlay);
		populate();
	}

	public void setmContext(Context mContext) {
		this.mContext = mContext;
	}

	public Context getmContext() {
		return mContext;
	}
	
	@Override
	protected boolean onTap(int index) {
		  //OverlayItem item = mOverlays.get(index);
		  //AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
		  //dialog.setTitle(item.getTitle());
		  //dialog.setMessage(item.getSnippet());
		  //dialog.show();
		  return true;

		/*OverlayItem item = mOverlays.get(index);
		AlertDialog dialog = new AlertDialog.Builder(mContext).create();
		dialog.setTitle(item.getTitle());
		dialog.setMessage(item.getSnippet());

		dialog.setButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				return;
			}
		});
		dialog.show();
		return true;*/
	}

}
