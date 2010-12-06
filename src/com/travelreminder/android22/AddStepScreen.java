package com.travelreminder.android22;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AddStepScreen extends Activity {
   public void onCreate(Bundle icicle) {
      super.onCreate(icicle);
      setContentView(R.layout.addstepscreen);
      Button b = (Button) findViewById(R.id.btnClick2);
      b.setOnClickListener(new View.OnClickListener() {
         public void onClick(View arg0) {
         setResult(RESULT_OK);
         finish();
         }
      });
   }
}