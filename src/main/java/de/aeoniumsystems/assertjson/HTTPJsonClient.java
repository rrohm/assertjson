/*
 * Copyright 2024 robert rohm.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.aeoniumsystems.assertjson;

import static de.aeoniumsystems.assertjson.HTTPJsonClient.RequestType.DELETE;
import static de.aeoniumsystems.assertjson.HTTPJsonClient.RequestType.GET;
import static de.aeoniumsystems.assertjson.HTTPJsonClient.RequestType.POST;
import jakarta.json.bind.JsonbBuilder;
import jakarta.json.bind.JsonbConfig;
import java.io.IOException;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.logging.Logger;

/**
 * Client utility for fetching JSON content from RESTful services.
 *
 * @author robert rohm
 */
public class HTTPJsonClient {

  public enum RequestType {
    GET, POST, PUT, DELETE
  }

  private final RequestType requestType;
  private Object data;
  private final String url;
  private String host = null;
  private int port = 0;
  private Authenticator authenticator = null;

  /**
   * Creates a new instance of {@link HTTPJsonClient}, for the given URL with the given request type. The request will
   * be send when calling {@link HTTPJsonClient#send() }.
   *
   * @param url The URL to request with this client.
   * @param requestType The request Type.
   */
  public HTTPJsonClient(String url, RequestType requestType) {
    this.url = url;
    this.requestType = requestType;
  }

  public static HTTPJsonClient DELETE(String url) {
    return new HTTPJsonClient(url, DELETE);
  }
  
  /**
   * Convenience facotry method for constructing a new {@link HTTPJsonClient}, for issuing a GET request to the given
   * URL. This is equivalent to calling:
   * <pre><code>new HTTPJsonClient("http://some.url", GET);</code></pre>
   *
   * @param url The URL to request with this client.
   * @return The client instance, configured with URL and request type.
   */
  public static HTTPJsonClient GET(String url) {
    return new HTTPJsonClient(url, GET);
  }

  public static HTTPJsonClient POST(String url, Object data) {
    final HTTPJsonClient httpJsonClient = new HTTPJsonClient(url, POST);
    httpJsonClient.data(data);

    return httpJsonClient;
  }

  public HTTPJsonClient authenticate(String username, String password) {
    this.authenticator = new Authenticator() {
      @Override
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(username, password.toCharArray());
      }
    };
    return this;
  }

  public void data(Object data) {
    this.data = data;
  }

  /**
   *
   * @param host
   * @return
   */
  public HTTPJsonClient host(String host) {
    this.host = host;
    return this;
  }

  public HTTPJsonClient port(int port) {
    this.port = port;
    return this;
  }

  public AssertHTTP send() {
    HttpClient.Builder clientBuilder = HttpClient.newBuilder();
    if (this.authenticator != null) {
      clientBuilder = clientBuilder.authenticator(authenticator);
    }

    HttpClient client = clientBuilder.build();

    HttpRequest.Builder requestBuilder = HttpRequest.newBuilder();
    try {
      requestBuilder = requestBuilder.uri(new URI(this.url));
    } catch (URISyntaxException ex) {
      Logger.getLogger(HTTPJsonClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
      throw new RuntimeException("Invalid URL: " + this.url, ex);
    }
    requestBuilder.setHeader("Content-type", "application/json");

    HttpRequest request;

    JsonbConfig config = new JsonbConfig()
            .withNullValues(Boolean.TRUE)
            .withFormatting(Boolean.TRUE);

    switch (requestType) {
      case GET:
        request = requestBuilder.GET().build();
        break;
      case POST:
        final String json = JsonbBuilder.create(config).toJson(this.data);
        final HttpRequest.BodyPublisher bodyPublisher = HttpRequest.BodyPublishers.ofString(json);
        request = requestBuilder.POST(bodyPublisher).build();
        break;
      default:
        throw new RuntimeException("Unsupported request type: " + this.requestType);
    }

    HttpResponse<String> response;
    try {
      response = client.send(request, HttpResponse.BodyHandlers.ofString());
      return new AssertHTTP(response);
    } catch (IOException | InterruptedException ex) {
      Logger.getLogger(HTTPJsonClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
      throw new RuntimeException("Cannot get JSON data.", ex);
    }
  }

  /**
   * The request type, assigned when constructing a client, read-only.
   *
   * @return The request type.
   */
  public RequestType getRequestType() {
    return requestType;
  }

  /**
   * The URL string, assigned when constructing a client, read-only.
   *
   * @return The URL string
   */
  public String getUrl() {
    return url;
  }
}
