package com.example.foodplanner.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.LayoutInflater;
import android.view.View;

import com.example.foodplanner.R;

public class Connection {
    public static boolean isInternetAvailable(Context context) {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static void checkConnectionAndAlert(final Context context) {

        if (!isInternetAvailable(context)) {
            showNoInternetDialog(context);
        }
//        if (!isInternetAvailable(context)) {
//            AlertDialog.Builder builder = new AlertDialog.Builder(context);
//            builder.setTitle("No Internet Connection")
//                    .setMessage("You can still access your planned meals and favorite meals.")
//                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int id) {
//                            // User clicked OK button
//                        }
//                    });
//            builder.create().show();
        }

    private static void showNoInternetDialog(Context context) {
        // Inflate the custom layout
        LayoutInflater inflater = LayoutInflater.from(context);
        View customView = inflater.inflate(R.layout.dialog_custom_alert, null);

        // Build the AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(customView);

        // Add a button to the dialog
        builder.setPositiveButton("OK", (dialog, which) -> {
            dialog.dismiss();
        });

        // Create and show the dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}
