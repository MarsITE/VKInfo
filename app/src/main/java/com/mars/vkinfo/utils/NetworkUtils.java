package com.mars.vkinfo.utils;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Scanner;

public class NetworkUtils {

    private static final String VK_API_BASE_URL = "https://api.vk.com/";
    private static final String VK_USERS_GET = "method/users.get";
    private static final String PARAM_USER_ID = "user_ids";
    private static final String PARAM_VERSION = "v";
    private static final String PARAM_VERSION_NUMBER = "5.103";
    private static final String FIELD = "fields";
    private static final String FIELD_NAME = "bdate";
    private static final String ACCESS_TOKEN = "access_token";
    private static final String ACCESS_TOKEN_NUMBER = "1f0882641f0882641f088264331f78d88b11f081f08826441795613b8eed074061e43c3";

    public static URL generateURL(String userIds) {
        Uri builtUri = Uri.parse(VK_API_BASE_URL + VK_USERS_GET).buildUpon()
                .appendQueryParameter(PARAM_USER_ID, userIds).appendQueryParameter(FIELD, FIELD_NAME)
                .appendQueryParameter(ACCESS_TOKEN, ACCESS_TOKEN_NUMBER)
                .appendQueryParameter(PARAM_VERSION, PARAM_VERSION_NUMBER).build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    public static String getResponseFromURL(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();

            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        }
        catch (UnknownHostException e){
            return null;
        } finally {
            urlConnection.disconnect();
        }
    }

}
