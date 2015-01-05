package com.onclick.dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

import onclick.com.raisesurvey.Constants;
import onclick.com.raisesurvey.R;


/**
 * Created by Ronald T on 12/30/2014.
 */


public class  DialogFactory {

    public static void showNoConnectionDialog(Activity context) {

        DialogParams noConnectDialog = new DialogParams(context);
        noConnectDialog.setMessage(context.getString(R.string.message_dialog_try_to_reconnect));
        noConnectDialog.setTitle(context.getString(R.string.title_dialog_problem_with_network));
        noConnectDialog.setShowNegative(true);
        noConnectDialog.setNegativeText(context.getString(R.string.label_dialog_no));

        noConnectDialog.setPositiveText(context.getString(R.string.label_dialog_yes));
        noConnectDialog.setShowPositive(true);
                noConnectDialog.setPositiveAction(Constants.ACTION_WIRELESS_CONNECT);

        ShowDialog(noConnectDialog);

    }

    public static void showProblemSendingChoice(Activity tActivity, String errorMessage) {

        DialogParams problemDialog = new DialogParams(tActivity);
        problemDialog.setMessage(tActivity.getString(R.string.message_dialog_please_try_again_later));
        problemDialog.setTitle(errorMessage);

        problemDialog.setShowNegative(true);
        problemDialog.setNegativeText(tActivity.getString(R.string.label_dialog_ok));

        problemDialog.setShowPositive(false);

        ShowDialog(problemDialog);
    }
    public static void showProblemSendingChoice(Activity tActivity) {
        String errorMessageDefault = tActivity.getString(R.string.message_dialog_please_try_again_later);
        showProblemSendingChoice(tActivity,errorMessageDefault);

    }

    public static void showCongratulationsDialog(Activity context, int bonusPoints) {

        DialogParams noConnectDialog = new DialogParams(context);
        noConnectDialog.setMessage("You have earned " + String.valueOf(bonusPoints) + " bonus points.");
        noConnectDialog.setTitle("Congratulations!");
        noConnectDialog.setShowNegative(true);
        noConnectDialog.setNegativeText(context.getString(R.string.label_dialog_ok));

        noConnectDialog.setShowPositive(false);

        ShowDialog(noConnectDialog);

    }


    private static void ShowDialog(final DialogParams dialogParams) {
        final Activity mActivity = dialogParams.dialogActivity;

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                mActivity);

        // set title
        alertDialogBuilder.setTitle(dialogParams.getTitle());

        // set dialog message
        alertDialogBuilder.setMessage(dialogParams.getMessage());
        alertDialogBuilder.setCancelable(false);
        if (dialogParams.isShowPositive()) {
            alertDialogBuilder.setPositiveButton(dialogParams.getPositiveText(), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                     dialogParams.RunPositiveAction(Constants.ACTION_WIRELESS_CONNECT);

                }
            });
        }
        if (dialogParams.isShowNegative()) {
            alertDialogBuilder.setNegativeButton(dialogParams.getNegativeText(), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {

                    dialog.cancel();
                }
            });
        }
        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }


}
