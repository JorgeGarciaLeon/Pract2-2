package com.example.practica2_1.utils;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtils {
    final static String GITHUB_BASE_URL = "https://akabab.github.io/superhero-api/api/";
    final private static String allJson = "all.json";
    final private static String searchId = "/id/";

    public static URL buildUrl(String idSuperHero) {
        Uri buildUri;

        if (idSuperHero.isEmpty()) {
            buildUri = Uri.parse(GITHUB_BASE_URL).buildUpon()
                    .appendPath(allJson)
                    .build();
        } else  {
            buildUri = Uri.parse(GITHUB_BASE_URL).buildUpon()
                    .appendPath(searchId + idSuperHero + ".json")
                    .build();
        }

        URL url = null;

        try {
            url = new URL(buildUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConection = (HttpURLConnection) url.openConnection();
        InputStream in = urlConection.getInputStream();
        Scanner scanner = new Scanner(in);
        scanner.useDelimiter("\\A");

        try {
            boolean hasInput = scanner.hasNext();
            if (hasInput) return scanner.next();
            else return null;
        } finally {
            urlConection.disconnect();
        }
    }
}
