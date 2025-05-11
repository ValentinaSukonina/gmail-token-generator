package com.example.gmailtool;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.*;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.MemoryDataStoreFactory;
import io.github.cdimascio.dotenv.Dotenv;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

public class GmailTokenGenerator {

    static Dotenv dotenv = Dotenv.load();
    static String CLIENT_ID = dotenv.get("GOOGLE_CLIENT_ID");
    static String CLIENT_SECRET = dotenv.get("GOOGLE_CLIENT_SECRET");
    static String REDIRECT_URI = dotenv.get("GOOGLE_REDIRECT_URI");

    private static final String SCOPE = "https://www.googleapis.com/auth/gmail.send";

    public static void main(String[] args) throws IOException, GeneralSecurityException {
        GoogleClientSecrets clientSecrets = new GoogleClientSecrets()
                .setInstalled(new GoogleClientSecrets.Details()
                        .setClientId(CLIENT_ID)
                        .setClientSecret(CLIENT_SECRET)
                        .setRedirectUris(Collections.singletonList(REDIRECT_URI)));

        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                GsonFactory.getDefaultInstance(),
                clientSecrets,
                Collections.singletonList(SCOPE)
        )
        .setAccessType("offline")
        .setApprovalPrompt("force")
        .setDataStoreFactory(new MemoryDataStoreFactory())
        .build();

        LocalServerReceiver receiver = new LocalServerReceiver.Builder()
                .setPort(8000)
                .setCallbackPath("/oauth2callback")
                .build();

        Credential credential = new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");

        System.out.println("✅ Access Token: " + credential.getAccessToken());
        System.out.println("✅ Refresh Token: " + credential.getRefreshToken());
        System.out.println("🔒 Store this refresh token securely and configure it in your app.");
    }
}
