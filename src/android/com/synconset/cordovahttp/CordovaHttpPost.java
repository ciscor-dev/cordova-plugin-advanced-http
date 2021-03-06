/**
 * A HTTP plugin for Cordova / Phonegap
 */
package com.synconset.cordovahttp;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import org.apache.cordova.CallbackContext;
import org.json.JSONException;
import org.json.JSONObject;

import javax.net.ssl.SSLHandshakeException;

import com.github.kevinsawicki.http.HttpRequest;
import com.github.kevinsawicki.http.HttpRequest.HttpRequestException;

class CordovaHttpPost extends CordovaHttp implements Runnable {
    public CordovaHttpPost(String urlString, Object params, String serializerName, JSONObject headers, int timeout, boolean rawBody, CallbackContext callbackContext) {
        super(urlString, params, serializerName, headers, timeout, rawBody, callbackContext);
    }

    @Override
    public void run() {
        try {
            HttpRequest request = HttpRequest.post(this.getUrlString());

            this.setupDataSerializer(request);
            this.prepareRequest(request);
            this.prepareRequestBody(request);
            this.returnResponseObject(request);
        } catch (HttpRequestException e) {
            this.handleHttpRequestException(e);
        } catch (Exception e) {
            this.respondWithError(e.getMessage());
        }
    }
}
