package com.onclick.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.onclick.dialogs.DialogFactory;

import com.onclick.raisesurvey.Constants;

/**
 * Created by Ronald T on 1/2/2015.
 */
public class Preferences {

    final static int BONUS_TIER = 500;

    public static int getStoredBonusPoints(Context mContext) {

    SharedPreferences spRaiseSurvey = mContext.getSharedPreferences(Constants.SHARED_PREFERENCE_RAISE_SURVEY, 0);
   int spBonusPoints = spRaiseSurvey.getInt(Constants.SP_TOTAL_BONUS_POINTS, 0);

    return spBonusPoints;

}


    private static void putStoredBonusPoints(Context mContext, int mBonusPoints) {

        SharedPreferences spRaiseSurvey = mContext.getSharedPreferences(Constants.SHARED_PREFERENCE_RAISE_SURVEY, 0);
        SharedPreferences.Editor settingsEditor = spRaiseSurvey.edit();

        settingsEditor.putInt(Constants.SP_TOTAL_BONUS_POINTS, mBonusPoints);
        settingsEditor.commit();
    }

    public static int TotalBonusPoints(Context tActivity, Integer bonusPoints) {
        int mBonusPoints = Preferences.getStoredBonusPoints(tActivity);
        int newBonusPoints =  mBonusPoints + bonusPoints.intValue();
        Preferences.putStoredBonusPoints(tActivity, newBonusPoints);
        return newBonusPoints;
    }

    public static void checkBonusPoints(Activity tActivity, int totalBonusPoints) {

        if (totalBonusPoints % BONUS_TIER == 0) {
            DialogFactory.showCongratulationsDialog(tActivity, totalBonusPoints);
        }
    }
}
