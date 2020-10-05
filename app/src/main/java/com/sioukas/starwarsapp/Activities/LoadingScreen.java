package com.sioukas.starwarsapp.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.sioukas.starwarsapp.R;

import static android.Manifest.permission.ACCESS_NETWORK_STATE;
import static android.Manifest.permission.INTERNET;

public class LoadingScreen extends AppCompatActivity {

    private static final int REQUEST_PERMISSIONS = 100;
    private boolean isPermissionGrantedShown = false;
    private SharedPreferences sharedPrefs;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        sharedPrefs = getSharedPreferences("APP_PREFERENCES", MODE_PRIVATE);
        isPermissionGrantedShown = sharedPrefs.getBoolean("PERMISSIONS_GRANTED_SHOWN", false);

//        Check if permissions are granted from shared preferences
        if (isPermissionGrantedShown) {
//            execute init() after 1 sec
            new Handler().postDelayed(() -> {
                init();
            }, 1000);
        } else {
//            check for permissions
            checkPermissions();
        }
    }

    private void checkPermissions() {
        if (!isPermissionGrantedShown) {
//            Request Permissions from User, Because Internet permission is in the safe zone Android does not prompt it!
            ActivityCompat.requestPermissions(LoadingScreen.this, new String[]{
                    INTERNET,
                    ACCESS_NETWORK_STATE
            }, REQUEST_PERMISSIONS);
        } else {
//            check if permissions are granted execute init()
            init();
        }
    }

    private void init() {
        // start MainActivity and clear stack.
        Intent intent = new Intent(LoadingScreen.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_PERMISSIONS:
                if (grantResults.length > 0) {
//                    check permissions are granted from prompt
                    boolean InternetPermission = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean AccessNetworkPermission = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    if (InternetPermission && AccessNetworkPermission) {
                        if (!isPermissionGrantedShown) {
//                            set isPermissionGrantedShown to true for next startup to avoid asking user again and again.
                            isPermissionGrantedShown = true;
//                            display status of permissions to user!
                            Toast.makeText(LoadingScreen.this, "Permission Granted", Toast.LENGTH_LONG).show();
                            sharedPrefs.edit().putBoolean("PERMISSIONS_GRANTED_SHOWN", isPermissionGrantedShown).apply();
                        }
                        init();

                    } else {
//                        Display Denied permissions message.
                        Toast.makeText(LoadingScreen.this, "Permission Denied. Exiting application.", Toast.LENGTH_LONG).show();
                        finish();
                    }
                    break;
                }
            default:
                init();
                break;
        }
    }
}
