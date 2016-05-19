package com.android.volley.toolbox;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;

import org.apache.http.conn.ssl.SSLSocketFactory;

import com.huigou.R;

import android.content.Context;

@SuppressWarnings("deprecation")
public class CustomerSocketFactory extends SSLSocketFactory {

    private static final String PASSWD="yaochizaocan";

    public CustomerSocketFactory(KeyStore truststore) throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException,
        UnrecoverableKeyException {
        super(truststore);
    }

    public static SSLSocketFactory getSocketFactory(Context context) {
        InputStream input=null;
        try {
            input=context.getResources().openRawResource(R.raw.android_user);
            KeyStore trustStore=KeyStore.getInstance("BKS");

            trustStore.load(input, PASSWD.toCharArray());

            SSLSocketFactory factory=new CustomerSocketFactory(trustStore);

            return factory;
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if(input != null) {
                try {
                    input.close();
                } catch(IOException e) {
                    e.printStackTrace();
                }
                input=null;
            }
        }
    }
}
