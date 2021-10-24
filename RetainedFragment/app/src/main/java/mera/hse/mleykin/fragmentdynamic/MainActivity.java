package mera.hse.mleykin.fragmentdynamic;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;


public class MainActivity extends Activity {

    Fragment1 frag1;
    Fragment2 frag2;
    Fragment current;
    FragmentTransaction fTrans;
    CheckBox chbStack;
    StringBuilder sb;

    final String LOG_TAG = "myLogs";

    protected void onStart() {
        super.onStart();
        Log.d(LOG_TAG, "MainActivity onStart");
    }

    protected void onResume() {
        super.onResume();
        Log.d(LOG_TAG, "MainActivity onResume");
    }

    protected void onPause() {
        super.onPause();
        Log.d(LOG_TAG, "MainActivity onPause");
    }

    protected void onStop() {
        super.onStop();
        Log.d(LOG_TAG, "MainActivity onStop");
    }

    protected void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "MainActivity onDestroy");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        frag1 = new Fragment1();
        frag2 = new Fragment2();
        Log.d(LOG_TAG, "MainActivity onCreate");
    }

    public void onClick(View v) {
        fTrans = getFragmentManager().beginTransaction();
        Fragment fragment = getFragmentManager().findFragmentByTag("R.id.Fragment1");

        switch (v.getId()) {
            case R.id.btnAdd:
                fTrans.add(R.id.frgmCont, frag1);
                current = frag1;
                fTrans.commit();
                break;
            case R.id.btnRemove:
                fTrans.remove(current);
                fTrans.commit();
                break;
            case R.id.btnReplace:
                fTrans.replace(R.id.frgmCont, frag2);
                current = frag2;
                fTrans.commit();
            default:
                break;

        }
    }
}
