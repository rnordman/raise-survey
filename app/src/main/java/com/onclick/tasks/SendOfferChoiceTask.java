package com.onclick.tasks;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.onclick.apis.RaiseSurvey;
import com.onclick.dialogs.DialogFactory;
import com.onclick.model.DataIn;
import com.onclick.model.DataOut;
import com.onclick.utils.Preferences;

import org.json.JSONException;

import java.io.IOException;

import onclick.com.raisesurvey.Constants;
import onclick.com.raisesurvey.R;

/**
 * Created by Ronald T on 12/20/2014.
 */
public class SendOfferChoiceTask extends AsyncTask<DataIn,Void,DataOut> {

    final static String TAG = SendOfferChoiceTask.class.getSimpleName();

    private Activity tActivity;
    private ListView fragmentList;

  public SendOfferChoiceTask(Activity tActivity) {
        this.tActivity = tActivity;
    };


    @Override
    protected void onPreExecute(){



    }
    @Override
    protected DataOut doInBackground(DataIn... dataIn) {

        Integer bonusPoints = 0;
        DataOut dataOut = new DataOut();

        String rUrl = null;
        if (dataIn[0] != null)
            rUrl = dataIn[0].get(Constants.KEY_RAISE_URL);

        String rOfferBrandChosen = null;
        if (dataIn[0] != null)
            rOfferBrandChosen = dataIn[0].get(Constants.KEY_BRAND_CHOSEN);

        RaiseSurvey mRaiseSurvey = new RaiseSurvey();

        String result;

        try {
            result = mRaiseSurvey.postOfferChoice(rUrl,rOfferBrandChosen);
            try {
                bonusPoints = mRaiseSurvey.serializeJsonBonusPoints(result);
                Log.i(TAG,"Bonus points retrieved: " + String.valueOf(bonusPoints));

            } catch (JSONException e) {
                dataOut.setErrorMessage(tActivity.getString(R.string.message_error_problem_serializing));
                dataOut.setException(e);
                e.printStackTrace();
            }

        } catch (IOException e) {
            dataOut.setErrorMessage(tActivity.getString(R.string.message_error_problem_posting_offer_choice));
            dataOut.setException(e);
            e.printStackTrace();
        }

        int newBonusPoints = Preferences.TotalBonusPoints(tActivity,bonusPoints);
        dataOut.put(Constants.KEY_BONUS_POINTS,newBonusPoints);

        return dataOut;
    }


    protected void onPostExecute(DataOut dataOut) {
        super.onPostExecute(dataOut);

        int bonusPoints = 0;
        if (dataOut.getErrorMessage() == null) {
            if (dataOut.get(Constants.KEY_BONUS_POINTS) != null)
                bonusPoints = dataOut.get(Constants.KEY_BONUS_POINTS);

            TextView tvBonusPoints = (TextView) tActivity.findViewById(R.id.textTotalPointsEarned);
            tvBonusPoints.setText(String.valueOf(bonusPoints));

            TextView tvChooseCards = (TextView) this.tActivity.findViewById(R.id.textPleaseChoose);
            tvChooseCards.setVisibility(View.INVISIBLE);

            Button newOfferButton = (Button) tActivity.findViewById(R.id.buttonRefresh);
            newOfferButton.setVisibility(View.VISIBLE);

            ListView lvCardChoice = (ListView) tActivity.findViewById(R.id.listSurveyCards);
            lvCardChoice.setVisibility(ListView.INVISIBLE);

            Preferences.checkBonusPoints(tActivity, bonusPoints);
        }
        else {
            Log.i(TAG, dataOut.getException().getMessage());
            DialogFactory.showProblemSendingChoice(tActivity,dataOut.getErrorMessage());
        }

        return;
    }

}
