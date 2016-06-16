package co.androidninja.popularmovies.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Aditya Mehta on 16/06/16.
 */
public class NetworkUtil {
    private ConnectivityManager mConnectivityManager;

    public NetworkUtil(Context context) {
        mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    public boolean isNetworkAvailable() {
        NetworkInfo nwInfo = mConnectivityManager.getActiveNetworkInfo();
        if (nwInfo != null && nwInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }

}
