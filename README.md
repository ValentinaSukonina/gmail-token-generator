# GmailTokenGenerator

A simple Java-based tool to generate a Gmail API `refresh_token` using OAuth2 authentication.

---

## System Requirements

* Java 21 or later
* Maven 3.8 or later

---

## Google API Setup

1. **Create a Google Cloud Project** and enable the **Gmail API**.

    * Required scope: `https://www.googleapis.com/auth/gmail.send`

2. **Create OAuth 2.0 Credentials**:

    * Go to [Google Cloud Console](https://console.cloud.google.com/)
    * Create an **OAuth 2.0 Client ID** and **Client Secret**
    * Application type: `Web application` or `Desktop app`
    * Authorized redirect URI:

      ```
      http://localhost:8888/Callback
      ```

3. **Set up your environment or properties file** with the following:

   ```properties
   GOOGLE_CLIENT_ID=your_google_client_id
   GOOGLE_CLIENT_SECRET=your_google_client_secret
   GOOGLE_REDIRECT_URI=http://localhost:8888/Callback
   ```

---

## How to Use

1. Open a terminal in the project folder.
2. Run the following commands:

   ```bash
   mvn compile
   mvn exec:java
   ```
3. A browser window will open prompting Gmail login.
4. Upon successful authentication, the terminal will display your `access_token` and `refresh_token`.
5. Copy the `refresh_token` and use it in your application configuration.

---

## Example Spring Boot Configuration

Add the following to your `.env` or `application.yml`:

```yaml
spring:
  gmail:
    client-id: ${GMAIL_CLIENT_ID}
    client-secret: ${GMAIL_CLIENT_SECRET}
    refresh-token: ${GMAIL_REFRESH_TOKEN}
    email-from: ${GMAIL_EMAIL_FROM}
```

---

## Notes

* Keep your client credentials and tokens secure.
* Do not commit `.env` or secrets to version control.
* The refresh token allows long-term API access without requiring repeated logins.

---


