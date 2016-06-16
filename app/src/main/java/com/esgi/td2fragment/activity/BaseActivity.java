package com.esgi.td2fragment.activity;

import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by maxime on 15/06/16.
 */
public class BaseActivity extends AppCompatActivity {
    private static final int PERMISSIONS_REQUEST_CODE = 0x10;
    private static final String PERMISSIONS_IMPLEMENTATION_ERROR = "You should override this method in your BaseFragment implementation!";


    void onPermissionGranted() throws IllegalAccessException {
        throw new IllegalAccessException();
    }

    void onPermissionNotGranted() throws IllegalAccessException {
        throw new IllegalAccessException();
    }

    void checkPermissions(String... permissions) {
        boolean granted = true;
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                for (String perm : permissions) {
                    if (ContextCompat.checkSelfPermission(this, perm) != PackageManager.PERMISSION_GRANTED) {
                        granted = false;
                        break;
                    }
                }
                if (granted) {
                    onPermissionGranted();
                } else {
                    ActivityCompat.requestPermissions(this, permissions, PERMISSIONS_REQUEST_CODE);
                }
            } else {
                onPermissionGranted();
            }
        } catch (IllegalAccessException iae) {
            Log.e(getClass().getSimpleName(), PERMISSIONS_IMPLEMENTATION_ERROR, iae);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) { // NOPMD
        if (PERMISSIONS_REQUEST_CODE == requestCode) {

            boolean granted = true;
            for (int perm : grantResults) {
                if (perm != PackageManager.PERMISSION_GRANTED) {
                    granted = false;
                    break;
                }
            }
            try {
                if (granted) {
                    onPermissionGranted();
                } else {
                    onPermissionNotGranted();
                }
            } catch (IllegalAccessException iae) {
                Log.e(getClass().getSimpleName(), PERMISSIONS_IMPLEMENTATION_ERROR, iae);
            }
        }
    }
}
