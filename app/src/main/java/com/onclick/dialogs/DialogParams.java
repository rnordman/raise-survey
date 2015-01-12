package com.onclick.dialogs;

import android.app.Activity;
import android.content.Intent;
import android.provider.Settings;

import com.onclick.raisesurvey.Constants;

/**
 * Created by Ronald T on 1/2/2015.
 */
public class DialogParams {

    String Message;
    String Title;
    boolean ShowPositive;
    String PositiveText;
    int PositiveAction;

    boolean ShowNegative;
    String NegativeText;
    int NegativeAction;

    Activity dialogActivity;

    public DialogParams(Activity mActivity) {
        this.dialogActivity = mActivity;
    }

    public int getPositiveAction() {
        return PositiveAction;
    }

    public void setPositiveAction(int positiveAction) {
        PositiveAction = positiveAction;
    }

    public int getNegativeAction() {
        return NegativeAction;
    }

    public void setNegativeAction(int negativeAction) {
        NegativeAction = negativeAction;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public boolean isShowPositive() {
        return ShowPositive;
    }

    public void setShowPositive(boolean showPositive) {
        ShowPositive = showPositive;
    }

    public boolean isShowNegative() {
        return ShowNegative;
    }

    public void setShowNegative(boolean showNegative) {
        ShowNegative = showNegative;
    }

    public String getPositiveText() {
        return PositiveText;
    }

    public void setPositiveText(String positiveText) {
        PositiveText = positiveText;
    }

    public String getNegativeText() {
        return NegativeText;
    }

    public void setNegativeText(String negativeText) {
        NegativeText = negativeText;
    }

    public void RunPositiveAction(int positiveAction) {
        switch (positiveAction) {
            case (Constants.ACTION_WIRELESS_CONNECT):
                Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
                this.dialogActivity.startActivity(intent);

            default:
                return;
        }
    }

    public void RunNegativeAction(int negativeAction) {
        switch (negativeAction) {
            case (Constants.ACTION_WIRELESS_CONNECT):
                Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
                this.dialogActivity.startActivity(intent);

            default:
                return;


        }
    }
}
