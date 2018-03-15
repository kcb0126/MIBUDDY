package com.kcb0126.developer.mibuddy.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by developer on 3/15/2018.
 */

public class ConfirmDialog {
    public static void showConfirmDialog(Context context, String title, String message, final Runnable yesAction, final Runnable noAction) {
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        if(yesAction != null) {
                            yesAction.run();
                        }
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                if(noAction != null) {
                    noAction.run();
                }
            }
        }).show();
    }

    public static void showConfirmDialog(Context context, String title, String message, Runnable yesAction) {
        showConfirmDialog(context, title, message, yesAction, null);
    }
}
