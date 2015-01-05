package com.onclick.network;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;


public class CheckNetwork {

    public CheckNetwork() {

    }

    public static boolean isWifiMobileConnected(Context tContext) {

        boolean isConnected = true;

        if (!WifiOn(tContext) && !MobileOn(tContext)) {

            isConnected = false;

        }


        return isConnected;

    }

    public static boolean WifiOn(Context tContext)  {

        ConnectivityManager conman = (ConnectivityManager) tContext.getSystemService(Context.CONNECTIVITY_SERVICE);

        boolean wifi = conman.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnected();

        return wifi;

    }

    public static boolean MobileOn(Context tContext)  {

        ConnectivityManager conman = (ConnectivityManager) tContext.getSystemService(Context.CONNECTIVITY_SERVICE);

        boolean mobile = conman.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnected();

        return mobile;

    }

    public void WifiNetworkInfo(Intent wIntent, Context wContext) {


        //boolean noConnection = wIntent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, false);

		/*boolean nFailover = wIntent.getBooleanExtra(ConnectivityManager.EXTRA_IS_FAILOVER, false);

		String nTypeChange = wIntent.getStringExtra(ConnectivityManager.EXTRA_EXTRA_INFO);

		String nReason = wIntent.getStringExtra(ConnectivityManager.EXTRA_REASON);

		NetworkInfo nOtherNetwork = wIntent.getParcelableExtra(ConnectivityManager.EXTRA_OTHER_NETWORK_INFO);

		ConnectivityManager conman = (ConnectivityManager) wContext.getSystemService(Context.CONNECTIVITY_SERVICE);

		NetworkInfo nActiveNetwork = conman.getActiveNetworkInfo();

		String iState = nActiveNetwork.getState().toString();

		String nActiveName = nActiveNetwork.getTypeName();

		boolean iConnect = nActiveNetwork.isConnected();
		boolean iConnecting = nActiveNetwork.isConnectedOrConnecting();
		boolean iAvaialble = nActiveNetwork.isAvailable();
		boolean iFailover = nActiveNetwork.isFailover();
		boolean iRoaming = nActiveNetwork.isRoaming();



		NetworkInfo[] nAllNetworks = conman.getAllNetworkInfo();

		NetworkInfo nNetwork = conman.getNetworkInfo(conman.getNetworkPreference());

		int nPrefer = conman.getNetworkPreference();*/

        //conman.setNetworkPreference(nPrefer);

        //boolean validNetworkType = conman.isNetworkTypeValid(nPrefer);

    }
}