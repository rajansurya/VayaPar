package com.example.rdx.vayparexpress;

import android.content.Context;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by stpl on 06-Jun-16.
 */
public class HttpConnection {

    private static final String LOGCAT_TAG = HttpConnection.class.getSimpleName();

    /**
     * This method creates POST connection.
     *
     * @param requestURL     URL to hit.
     * @param postDataParams Parameters in HashMap.
     */
    public static String performPostCall(String requestURL, JSONObject postDataParams, Context context) {
        Log.d(LOGCAT_TAG, "RequestUrl: " + requestURL.toString());
        Log.d(LOGCAT_TAG, "Json: " + postDataParams.toString());
        URL url;
        String response = "";
        try {
            url = new URL(requestURL);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(30000);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
//            conn.setRequestProperty("X-device-token", CommonFunction.getXDeviceToken(context));
//            conn.setRequestProperty("Authorization", CommonFunction.getAuthToken(context));
//            conn.setRequestProperty("X-Client-Ip", CommonFunction.getIPAddress(true));
            conn.setDoOutput(true);

            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os));
            writer.write(postDataParams.toString());

            writer.flush();
            writer.close();
            os.close();

            int responseCode = conn.getResponseCode();
            Log.d(LOGCAT_TAG, "responseCode:" + responseCode);
            if (responseCode == HttpsURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_CREATED) {
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line = br.readLine()) != null) {
                    response += line;
                }
            } else if (responseCode == HttpURLConnection.HTTP_UNAUTHORIZED) {
                response = "401";
                return response;
            } else {// if (responseCode == HttpsURLConnection.HTTP_BAD_REQUEST)
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(conn.getErrorStream()));
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    response += inputLine;
                }
                in.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d(LOGCAT_TAG, "Response: " + response);
        return response;
    }

    public static String performGetCall(String requestURL, Context context) {
        Log.d(LOGCAT_TAG, "RequestUrl: " + requestURL.toString());
        URL url;
        String response = "";
        try {
            url = new URL(requestURL);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //  conn.setReadTimeout(30000);
            conn.setConnectTimeout(30000);
            conn.setRequestMethod("GET");
//            conn.setRequestProperty("X-device-token", CommonFunction.getXDeviceToken(context));
//            conn.setRequestProperty("Authorization", CommonFunction.getAuthToken(context));
//            conn.setRequestProperty("X-Client-Ip", CommonFunction.getIPAddress(true));
            conn.connect();

            int responseCode = conn.getResponseCode();
            Log.d(LOGCAT_TAG, "responseCode:" + responseCode);

            if (responseCode == HttpsURLConnection.HTTP_OK) {
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line = br.readLine()) != null) {
                    response += line;
                }
            } else if (responseCode == HttpURLConnection.HTTP_UNAUTHORIZED) {
                response = "401";
                return response;
            } else {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    response += inputLine;
                }
                in.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d(LOGCAT_TAG, "Response: " + response);
        return response;
    }

    public static String performPutCall(String requestURL, JSONObject postDataParams, Context context) {
        Log.d(LOGCAT_TAG, "RequestUrl: " + requestURL.toString());
        Log.d(LOGCAT_TAG, "Json: " + postDataParams.toString());
//        Log.d(LOGCAT_TAG, "X-device-token: " + CommonFunction.getXDeviceToken(context));
//        Log.d(LOGCAT_TAG, "Authorization: " + CommonFunction.getAuthToken(context));
        URL url;
        String response = "";
        try {
            url = new URL(requestURL);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(30000);
            conn.setRequestMethod("PUT");
            conn.setRequestProperty("Content-Type", "application/json");
//            conn.setRequestProperty("X-device-token", CommonFunction.getXDeviceToken(context));
//            conn.setRequestProperty("Authorization", CommonFunction.getAuthToken(context));
//            conn.setRequestProperty("X-Client-Ip", CommonFunction.getIPAddress(true));
            conn.setDoOutput(true);

            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os));
            writer.write(postDataParams.toString());

            writer.flush();
            writer.close();
            os.close();

            int responseCode = conn.getResponseCode();
            Log.d(LOGCAT_TAG, "responseCode:" + responseCode);
            if (responseCode == HttpsURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_CREATED) {
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line = br.readLine()) != null) {
                    response += line;
                }
            } else if (responseCode == HttpURLConnection.HTTP_UNAUTHORIZED) {
                response = "401";
                return response;
            } else {// if (responseCode == HttpsURLConnection.HTTP_BAD_REQUEST)
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(conn.getErrorStream()));
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    response += inputLine;
                }
                in.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d(LOGCAT_TAG, "Response: " + response);
        return response;
    }


    /*public static String performPostCall(String requestURL, JSONObject postDataParams) {
        Log.d("Request", "RequestUrl:" + requestURL.toString());
        Log.d("Request", "Json:" + postDataParams.toString());
        URL url;
        String response = "";
        try {
            url = new URL(requestURL);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //  conn.setReadTimeout(30000);
            conn.setConnectTimeout(30000);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            //String token = Md5Encryption.md5(CommonVar.API_PASSWORD) + Md5Encryption.md5(getCurrentDate()) + Base64Encoding.encodeBase64(CommonVar.API_USERNAME);
            String token = Md5Encryption.md5(CommonVar.API_PASSWORD) + CommonFunction.newMd5(getCurrentDate()) + Base64Encoding.encodeBase64(CommonVar.API_USERNAME);
            conn.setRequestProperty("token", token);
            conn.setRequestProperty("mtoken", "PW");
            //conn.setDoInput(true);
            conn.setDoOutput(true);
            
            OutputStream os = conn.getOutputStream();
//            BufferedWriter writer = new BufferedWriter(
//                    new OutputStreamWriter(os, "UTF-8"));
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os));
            writer.write(postDataParams.toString());

            writer.flush();
            writer.close();
            os.close();

            int responseCode = conn.getResponseCode();
            Log.d("Response", "responseCode:" + responseCode);
            if (responseCode == HttpsURLConnection.HTTP_OK) {
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line = br.readLine()) != null) {
                    response += line;
                }
            } else {
                response = "";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }*/

    private static String getCurrentDate() {
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd", Locale.US);
            String date = df.format(Calendar.getInstance().getTime());
            Log.d(LOGCAT_TAG, "Formated Current Date: " + date);
            if (date != null)
                return date.trim();
            else
                return "";
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * @param urlString - request URL
     * @return res - response from URL
     * @throws IOException
     */
    public String OpenHttpConnection(String urlString) throws IOException {
        HttpURLConnection httpConn = null;
        InputStream in = null;
        int response = -1;
        urlString = urlString.replaceAll(" ", "+");
        URL url = new URL(urlString);
        URLConnection conn = url.openConnection();
        if (!(conn instanceof HttpURLConnection))
            throw new IOException("Not an HTTP connection");
        try {

            httpConn = (HttpURLConnection) conn;
            httpConn.setAllowUserInteraction(false);
            httpConn.setInstanceFollowRedirects(true);
            httpConn.setConnectTimeout(10000);
            httpConn.setRequestMethod("GET");
            response = httpConn.getResponseCode();
            Log.d("Connection", "Response Code: " + response);
            if (response == HttpURLConnection.HTTP_OK) {
                in = httpConn.getInputStream();
            }
            String res = new String("");
            byte resp[] = new byte[256];
            int ret = in.read(resp);
            while (ret != -1) {
                String s = new String(resp, 0, ret);
                res += s;
                ret = in.read(resp);
            }
            in.close();
            return res;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new IOException("Error In Connection");
        } finally {
            if (httpConn != null)
                httpConn.disconnect();
        }
    }
}
