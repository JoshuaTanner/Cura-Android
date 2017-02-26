package com.example.joshua.cura_tablet;

import android.util.Log;

import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;




/**
 * Created by Joshua on 2/26/2017.
 */

public class TokenAuthorisation {

    public static String AccessUri = "https://api.cognitive.microsoft.com/sts/v1.0/issueToken";
    private String apiKey = "eee5fcebc783425692519793b11e99bd";
    private String accessToken;
    private Timer accessTokenRenewer;

    private int RefreshTokenDuration = 9;


    public TokenAuthorisation(){

        //set up renew timer
        accessTokenRenewer = new Timer();
        accessTokenRenewer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

            }
        },RefreshTokenDuration*60*1000,RefreshTokenDuration*60*1000);

    }

    private void RenewAccessToken()
    {
        String newAccessToken = HttpPost(AccessUri, this.apiKey);
        //swap the new token with old one
        //Note: the swap is thread unsafe
        this.accessToken = newAccessToken;
        Log.i("LOG", "Renewed token for user: {0} is: {1}"+
                this.apiKey+
                this.accessToken);
    }


    private String HttpPost(String accessUri, String apiKey)
    {
        // Prepare OAuth request
        /*
        WebRequest webRequest = WebRequest.Create(accessUri);
        webRequest.Method = "POST";
        webRequest.ContentLength = 0;
        webRequest.Headers["Ocp-Apim-Subscription-Key"] = apiKey;

        using (WebResponse webResponse = webRequest.GetResponse())
        {
            using (Stream stream = webResponse.GetResponseStream())
            {
                using (MemoryStream ms = new MemoryStream())
                {
                    byte[] waveBytes = null;
                    int count = 0;
                    do
                    {
                        byte[] buf = new byte[1024];
                        count = stream.Read(buf, 0, 1024);
                        ms.Write(buf, 0, count);
                    } while (stream.CanRead && count > 0);

                    waveBytes = ms.ToArray();

                    return Encoding.UTF8.GetString(waveBytes);
                }
            }
        }
        */
        return "";
    }


    }

















}
