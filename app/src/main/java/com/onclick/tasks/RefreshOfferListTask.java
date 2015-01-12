package com.onclick.tasks;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.onclick.apis.RaiseSurvey;
import com.onclick.dialogs.DialogFactory;
import com.onclick.model.DataOut;
import com.onclick.model.Offer;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

import com.onclick.raisesurvey.Constants;
import com.onclick.raisesurvey.OfferListViewAdapter;
import com.onclick.raisesurvey.R;

/**
 * Created by Ronald T on 12/27/2014.
 */
public class RefreshOfferListTask extends AsyncTask<String,Void,DataOut> {

    final static String TAG = SendOfferChoiceTask.class.getSimpleName();

    private Activity tActivity;


  public RefreshOfferListTask(Activity tActivity) {

        this.tActivity = tActivity;
    };



    @Override
    protected void onPreExecute(){

        ListView lvGiftCards = (ListView) this.tActivity.findViewById(R.id.listSurveyCards);
        lvGiftCards.setVisibility(ListView.INVISIBLE);
        TextView tvChooseCards = (TextView) this.tActivity.findViewById(R.id.textPleaseChoose);
        tvChooseCards.setVisibility(View.INVISIBLE);

        TextView tvPleaseWait = (TextView) this.tActivity.findViewById(R.id.textPleaseWait);
        tvPleaseWait.setVisibility(View.VISIBLE);

    }
    @Override
    protected DataOut doInBackground(String... rUrl) {

        RaiseSurvey mRaiseSurvey = new RaiseSurvey();

        DataOut dataOut = new DataOut();

        String result;

        List<Offer> rOffers;

        try {

            result = mRaiseSurvey.queryGetOffers(rUrl[0]);
            try {
                rOffers = mRaiseSurvey.serializeJsonSurveyGiftCardList(result);
                Log.i(TAG,"Offers successfully retrieved.");
                dataOut.put(Constants.KEY_OFFER_LIST,rOffers);
            } catch (JSONException e) {
                dataOut.setErrorMessage(tActivity.getString(R.string.message_error_problem_json_serialization));
                dataOut.setException(e);
                e.printStackTrace();
            }


        } catch (IOException e) {
            dataOut.setErrorMessage(tActivity.getString(R.string.message_error_problem_with_server));
            dataOut.setException(e);
            e.printStackTrace();
        }

        return dataOut;
    }


    protected void onPostExecute(DataOut dataOut) {

        super.onPostExecute(dataOut);
        List<Offer> rOffers;

        if (dataOut.getErrorMessage() == null && dataOut.get(Constants.KEY_OFFER_LIST) != null) {
                rOffers = dataOut.get(Constants.KEY_OFFER_LIST);

                TextView tvPleaseWait = (TextView) this.tActivity.findViewById(R.id.textPleaseWait);
                tvPleaseWait.setVisibility(View.INVISIBLE);

                TextView tvChooseCards = (TextView) this.tActivity.findViewById(R.id.textPleaseChoose);
                tvChooseCards.setVisibility(View.VISIBLE);

                OfferListViewAdapter adapter = new OfferListViewAdapter(
                        this.tActivity, R.layout.offer_list_item, rOffers);

                adapter.notifyDataSetChanged();
                ListView fragmentList = (ListView) this.tActivity.findViewById(R.id.listSurveyCards);
                fragmentList.setAdapter(adapter);

                fragmentList.setVisibility(ListView.VISIBLE);

        }
        else {
                DialogFactory.showProblemSendingChoice(tActivity,dataOut.getErrorMessage());
        }


        return;
    }

}
