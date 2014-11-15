package mangaba.com.br.mangabatag.controller;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;

import org.apache.http.NameValuePair;
import org.json.JSONObject;

import java.util.List;

import mangaba.com.br.mangabatag.util.web.ConnectionMethod;
import mangaba.com.br.mangabatag.util.web.ServiceHandle;
import mangaba.com.br.mangabatag.util.web.interfaces.OnConnectionFinished;

/**
 * Created by GuilhermeLucena on 20/09/2014.
 */
public class ConnectionTask extends AsyncTask<List<NameValuePair>, JSONObject, JSONObject> {

    private String url;
    private ConnectionMethod method;
    private Context context;
    private AlertDialog aDialog;
    private OnConnectionFinished callback;

    public ConnectionTask(Context context, OnConnectionFinished callback, String url, ConnectionMethod method) {
        this.context = context;
        this.url = url;
        this.method = method;
        this.callback = callback;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (context != null) {
            AlertDialog.Builder adb = new AlertDialog.Builder(context);

            adb.setTitle("Carregando...");
            adb.setCancelable(false);

            this.aDialog = adb.create();
            this.aDialog.show();
        }
    }

    @Override
    protected JSONObject doInBackground(List<NameValuePair>... args) {
        ServiceHandle sh = new ServiceHandle();

        List<NameValuePair> arguments = null;
        if (args.length > 0)
            arguments = args[0];

        String response = sh.makeServiceCall(this.url, this.method, arguments);
        JSONObject job = null;

        try {
            job = new JSONObject(response);

        } catch (Exception e) {
            job = null;

        } finally {

            return job;
        }
    }

    @Override
    protected void onPostExecute(JSONObject jsonObject) {
        super.onPostExecute(jsonObject);

        if (this.aDialog != null) {
            this.aDialog.dismiss();
        }

        if (this.callback != null) {
            try {
                this.callback.onConnectionFinished(jsonObject);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
