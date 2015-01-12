package com.onclick.apis;

import com.onclick.model.Offer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ronald T on 12/27/2014.
 */
public class RaiseSurvey {

    final static String GIFT_CARD_LIST_JSON_ARRAY = "offer";
    final static String GIFT_CARD_LIST_BRAND = "brand";
    final static String GIFT_CARD_LIST_VALUE = "value";
    final static String GIFT_CARD_LIST_COST = "cost";

    final static String RESPONSE_BONUS_POINTS = "bonus";
    final static int CONNECTION_TIMEOUT = 15000;
    final static int READ_TIMEOUT = 10000;

public String queryGetOffers(String rUrl) throws IOException {

    byte[] giftCards;
    giftCards = getOffersBytes(rUrl);


    return new String(giftCards);

}

    private byte[] getOffersBytes(String urlSpec) throws IOException {

        URL url = new URL(urlSpec);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("Content-Type", "application/json");

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {

            InputStream in = connection.getInputStream();

            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                return null;
            }

            int bytesRead = 0;

            byte[] buffer = new byte[1024];
            while ((bytesRead = in.read(buffer)) > 0 ) {
                out.write(buffer, 0, bytesRead);
            }
            out.close();

        } catch (IOException e) {

            e.printStackTrace();

        }

        finally {

            connection.disconnect();
        }
        return out.toByteArray();

    }

    public String postOfferChoice(String rUrl, String sBrand) throws IOException {

        byte[] bonusPoints;
        bonusPoints = postOfferChoiceBytes(rUrl, sBrand);

        return new String(bonusPoints);

    }
    private byte[] postOfferChoiceBytes(String urlSpec, String sBrand) throws IOException {

        HttpURLConnection urlConnection = null;
        ByteArrayOutputStream outArray = new ByteArrayOutputStream();

        try {
            URL url = new URL(urlSpec);
            urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.setDoOutput(true);
            urlConnection.setRequestMethod("POST");
            urlConnection.setUseCaches(false);
            urlConnection.setConnectTimeout(CONNECTION_TIMEOUT);
            urlConnection.setReadTimeout(READ_TIMEOUT);
            urlConnection.setRequestProperty("Content-Type", "application/json");

            urlConnection.connect();

            //Create JSONObject here
            JSONObject jsonParam = new JSONObject();
            jsonParam.put("brand", sBrand);

            OutputStreamWriter out = new OutputStreamWriter(urlConnection.getOutputStream());
            out.write(jsonParam.toString());
            out.close();

            try {

                InputStream in = urlConnection.getInputStream();

                if (urlConnection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                    return null;
                }

                int bytesRead = 0;

                byte[] buffer = new byte[1024];
                while ((bytesRead = in.read(buffer)) > 0) {
                    outArray.write(buffer, 0, bytesRead);
                }
                outArray.close();

            } catch (IOException e) {

                e.printStackTrace();

            } finally {

                urlConnection.disconnect();
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return outArray.toByteArray();

    }
        public List<Offer> serializeJsonSurveyGiftCardList(String dataJson) throws JSONException {

        JSONObject jData = new JSONObject(dataJson);
        JSONArray jsonArray = jData.getJSONArray(GIFT_CARD_LIST_JSON_ARRAY);

        List<Offer> mListOffers = new ArrayList<Offer>();

        Offer mOffer;
        int iGiftCards = jsonArray.length();

        for (int i = 0; i < iGiftCards; i++) {

            JSONObject jGiftCards = jsonArray.getJSONObject(i);
            mOffer = new Offer();

            mOffer.setCardBrand(jGiftCards.getString(GIFT_CARD_LIST_BRAND));
            mOffer.setCardValue((float) jGiftCards.getDouble(GIFT_CARD_LIST_VALUE));
            mOffer.setCardCost((float) jGiftCards.getDouble(GIFT_CARD_LIST_COST));

            mListOffers.add(mOffer);

        }

        return mListOffers;

    }

    public int serializeJsonBonusPoints(String dataJson) throws JSONException {

        JSONObject jData = new JSONObject(dataJson);

        int bonusPoints = jData.getInt(RESPONSE_BONUS_POINTS);


        return bonusPoints;

    }


}
