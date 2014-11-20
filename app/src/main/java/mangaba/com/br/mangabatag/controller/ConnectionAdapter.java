package mangaba.com.br.mangabatag.controller;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import mangaba.com.br.mangabatag.util.web.ConnectionMethod;
import mangaba.com.br.mangabatag.util.web.interfaces.OnConnectionFinished;

/**
 * Created by GuilhermeLucena on 15/11/2014.
 */
public class ConnectionAdapter extends Thread implements OnConnectionFinished {

    private UserReceiver listener;

    public ConnectionAdapter(UserReceiver listener) {
        this.listener = listener;
    }

    public void requestUser(UserReceiver listener, Context ctx, String enrolment, String password) throws Exception {
        if (!isConected(ctx)) {
            throw new DataExeption();
        } else {
            requestUserJson(enrolment, password);
        }
    }

    private void requestUserJson(String enrolment, String password) throws Exception {
        List<NameValuePair> parameterList = new ArrayList<NameValuePair>();
        parameterList.add(new BasicNameValuePair("enrollment", enrolment));
        parameterList.add(new BasicNameValuePair("password", password));

        ConnectionTask tk = new ConnectionTask(null, this, "http://luish.me:4242/poo/authenticate",
                ConnectionMethod.POST);
        tk.execute(parameterList);
    }

    private boolean isConected(Context ctx) {
        ConnectivityManager comunication = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo i = comunication.getActiveNetworkInfo();
        if (i == null)
            return false;
        if (!i.isConnected())
            return false;
        if (!i.isAvailable())
            return false;
        return true;
    }

    @Override
    public void onConnectionFinished(JSONObject response) {
        if (response != null) {
            this.createUser(response);
        } else {
            listener.onUserReceived(null);
        }
    }

    private void createUser(JSONObject user) {
        JSONBuilder adapter = new JSONBuilder();
        if (adapter.authenticateJSON(user)) {
            Log.e(getClass().getCanonicalName(), user.toString());
            try {
                this.listener.onUserReceived(adapter.parseUser(user));
            } catch (Exception e) {
                this.listener.onUserReceived(null);
            }
        } else {
            this.listener.onUserReceived(null);
        }
    }

}
