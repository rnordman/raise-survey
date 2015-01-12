package com.onclick.raisesurvey;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.onclick.dialogs.DialogFactory;
import com.onclick.model.DataIn;
import com.onclick.network.CheckNetwork;
import com.onclick.tasks.RefreshOfferListTask;
import com.onclick.tasks.SendOfferChoiceTask;
import com.onclick.utils.Preferences;

/**
 * Created by Ronald T on 12/28/2014.
 */
public class ListCardsFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {


    public ListCardsFragment() {
    }

    Button btnNewOfferList;
    View rootView;
    ListView listOffers;
    TextView bonusPoints;

    RefreshOfferListTask refreshOfferListTask;
    SendOfferChoiceTask sendOfferChoiceTask;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_main, container, false);

        btnNewOfferList = (Button) rootView.findViewById(R.id.buttonRefresh);
        btnNewOfferList.setOnClickListener(this);
        btnNewOfferList.setVisibility(View.INVISIBLE);

        listOffers = (ListView) rootView.findViewById(R.id.listSurveyCards);
        listOffers.setOnItemClickListener(this);

        int tBonusPoints = Preferences.getStoredBonusPoints(getActivity());
        bonusPoints = (TextView) rootView.findViewById(R.id.textTotalPointsEarned);
        bonusPoints.setText(String.valueOf(tBonusPoints));

        return rootView;

    }

    @Override
    public void onResume() {
        super.onResume();

        if (!findNetworkConnection())
            displayNoConnectionDialog();
        else
            listGiftCards();
    }

    private void displayNoConnectionDialog() {

        DialogFactory.showNoConnectionDialog(getActivity());

    }

    private boolean findNetworkConnection() {

        if (!CheckNetwork.isWifiMobileConnected(getActivity()))
            return false;
        else
            return true;
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.buttonRefresh) {
            btnNewOfferList.setVisibility(View.INVISIBLE);
            listGiftCards();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        TextView tBrands = (TextView) view.findViewById(R.id.listItemBrand);
        String sBrands = tBrands.getText().toString();
        sendOfferChoice(sBrands);

    }

    private void sendOfferChoice(String sBrands) {

        DataIn dataIn = new DataIn();
        dataIn.put(Constants.KEY_RAISE_URL, Constants.RAISE_SURVEY_URL);
        dataIn.put(Constants.KEY_BRAND_CHOSEN, sBrands);

        sendOfferChoiceTask = new SendOfferChoiceTask(getActivity());
        sendOfferChoiceTask.execute(dataIn);

    }

    private void listGiftCards() {
        refreshOfferListTask = new RefreshOfferListTask(getActivity());
        refreshOfferListTask.execute(Constants.RAISE_SURVEY_URL);

    }

    @Override
    public void onPause() {
        super.onPause();

        if (refreshOfferListTask != null)
            refreshOfferListTask.cancel(true);

        if (sendOfferChoiceTask != null)
            sendOfferChoiceTask.cancel(true);

    }
}

