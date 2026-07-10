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
import static de.aeoniumsystems.assertjson.HTTPJsonClient.RequestType.PUT;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.json.bind.JsonbConfig;
import jakarta.json.bind.JsonbException;
import java.io.IOException;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Client utility for fetching JSON content from RESTful services.
 *
 * @author robert rohm
 */
public class HTTPJsonClient {

  private static final System.Logger LOG = System.getLogger(HTTPJsonClient.class.getName());

  public enum RequestType {
    GET, POST, PUT, DELETE
  }

  private final RequestType requestType;
  private Object data;
  private final String url;
  private Authenticator authenticator = null;
  private Map<String, List<String>> headers = null;
  private String contentType = "application/json";

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

  /**
   * Convenience factory method for constructing a new {@link HTTPJsonClient}, for preparing a DELETE request to the
   * given URL. This is equivalent to calling:
   * <pre><code>new HTTPJsonClient("http://some.url", DELETE);</code></pre>
   *
   * @param url The URL to request with this client.
   * @return The client instance, configured with URL and request type.
   */
  @SuppressWarnings("java:S100")
  public static HTTPJsonClient DELETE(String url) {
    return new HTTPJsonClient(url, DELETE);
  }

  /**
   * Convenience factory method for constructing a new {@link HTTPJsonClient}, for preparing a GET request to the given
   * URL. This is equivalent to calling:
   * <pre><code>new HTTPJsonClient("http://some.url", GET);</code></pre>
   *
   * @param url The URL to request with this client.
   * @return The client instance, configured with URL and request type.
   */
  @SuppressWarnings("java:S100")
  public static HTTPJsonClient GET(String url) {
    return new HTTPJsonClient(url, GET);
  }

  /**
   * Convenience factory method for constructing a new {@link HTTPJsonClient}, for preparing a POST request to the given
   * URL. This is equivalent to calling:
   * <pre><code>new HTTPJsonClient("http://some.url", POST);</code></pre>
   *
   * @param url The URL to request with this client.
   * @return The client instance, configured with URL and request type.
   */
  @SuppressWarnings("java:S100")
  public static HTTPJsonClient POST(String url) {
    return new HTTPJsonClient(url, POST);
  }

  /**
   * Convenience factory method for constructing a new {@link HTTPJsonClient}, for preparing a POST request to the given
   * URL, with data to be set as request body.
   *
   * @param url The URL to request with this client.
   * @param data The data object, will be converted to String if it is not already.
   * @return The client instance, configured with URL and request type.
   */
  @SuppressWarnings("java:S100")
  public static HTTPJsonClient POST(String url, Object data) {
    final HTTPJsonClient httpJsonClient = new HTTPJsonClient(url, POST);
    httpJsonClient.data(data);

    return httpJsonClient;
  }

  /**
   * Convenience factory method for constructing a new {@link HTTPJsonClient}, for preparing a PUT request to the given
   * URL. This is equivalent to calling:
   * <pre><code>new HTTPJsonClient("http://some.url", PUT);</code></pre>
   *
   * @param url The URL to request with this client.
   * @return The client instance, configured with URL and request type.
   */
  @SuppressWarnings("java:S100")
  public static HTTPJsonClient PUT(String url) {
    final HTTPJsonClient httpJsonClient = new HTTPJsonClient(url, PUT);

    return httpJsonClient;
  }

  /**
   * Convenience factory method for constructing a new {@link HTTPJsonClient}, for preparing a PUT request to the given
   * URL, with data to be set as request body.
   *
   * @param url The URL to request with this client.
   * @param data The data object, will be converted to String if it is not already.
   * @return The client instance, configured with URL and request type.
   */
  @SuppressWarnings("java:S100")
  public static HTTPJsonClient PUT(String url, Object data) {
    final HTTPJsonClient httpJsonClient = new HTTPJsonClient(url, PUT);
    httpJsonClient.data(data);

    return httpJsonClient;
  }

  /**
   * Convenience factory method for constructing a new {@link HTTPJsonClient}, for preparing a PUT request to the given
   * URL, with a path secification to load string content for the request body.
   *
   * @param url The URL to request with this client.
   * @param dataPath The path specification to load the string content from.
   * @return The client instance, configured with URL and request type.
   * @throws java.io.IOException
   */
  @SuppressWarnings("java:S100")
  public static HTTPJsonClient PUT(String url, Path dataPath) throws IOException {
    final HTTPJsonClient httpJsonClient = new HTTPJsonClient(url, PUT);
    httpJsonClient.data(Files.readString(dataPath));

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

  /**
   * Set the content type for the request. Default is <code>application/json</code>.
   *
   * @param contentType The HTTP content type-
   * @return The <code>HTTPJsonClient</code> instance, for method chaining ("fluent API").
   */
  public HTTPJsonClient contentType(String contentType) {
    this.contentType = contentType;
    return this;
  }

  /**
   * Set the request data. The object is converted to string content via it's <code>toString()</code> method.
   *
   * @param data The data object.
   * @return The <code>HTTPJsonClient</code> instance, for method chaining ("fluent API").
   */
  public HTTPJsonClient data(Object data) {
    this.data = data;
    return this;
  }

  /**
   * Load string data to be sent from a path into the file system.
   *
   * @param dataPath The path spec.
   * @return The <code>HTTPJsonClient</code> instance, for method chaining ("fluent API").
   * @throws IOException In case of failure to read the data file.
   */
  public HTTPJsonClient data(Path dataPath) throws IOException {
    final String content = Files.readString(dataPath);
    LOG.log(System.Logger.Level.INFO, content);
    this.data = content;
    return this;
  }

  /**
   * Set a request header.
   *
   * @param key The header name.
   * @param value The header value.
   * @return The <code>HTTPJsonClient</code> instance, for method chaining ("fluent API").
   */
  public HTTPJsonClient header(String key, String value) {

    if (this.headers == null) {
      this.headers = new HashMap<>();
    }

    if (!this.headers.containsKey(key)) {
      this.headers.put(key, new ArrayList<>());
    }

    List<String> headerContent = this.headers.get(key);
    if (!headerContent.contains(value)) {
      headerContent.add(value);
    }

    return this;
  }

  /**
   * Send the HTTP request and return an {@link AssertJson} object for examining the resonse.
   *
   * @return An {@link AssertJson} object for examining the response.
   */
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
    requestBuilder.setHeader("Content-type", this.contentType);

    if (this.headers != null) {
      for (Map.Entry<String, List<String>> entry : this.headers.entrySet()) {
        for (String value : entry.getValue()) {
          requestBuilder.setHeader(entry.getKey(), value);
        }
      }
    }

    HttpRequest request = null;

    switch (requestType) {
      case GET:
        request = requestBuilder.GET().build();
        break;
      case PUT:
        final String jsonPUTdata = prepareData();
        final HttpRequest.BodyPublisher putBodyPublisher = HttpRequest.BodyPublishers.ofString(jsonPUTdata);
        request = requestBuilder.PUT(putBodyPublisher).build();
        break;
      case POST:
        final String json = prepareData();
        final HttpRequest.BodyPublisher bodyPublisher = HttpRequest.BodyPublishers.ofString(json);
        request = requestBuilder.POST(bodyPublisher).build();
        break;
      case DELETE:
        request = requestBuilder.DELETE().build();
        break;
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
   * Internal preparation of HTTP content: Parse to pretty-printed JSON, if it is not a string anyway.
   *
   * @return String content for the HTTP request.
   * @throws JsonbException If it cannot generate JSON.
   */
  private String prepareData() throws JsonbException {
    if (this.data instanceof String) {
      return (String) this.data;

    } else {
      JsonbConfig config = new JsonbConfig()
              .withNullValues(Boolean.TRUE)
              .withFormatting(Boolean.TRUE);
      try (final Jsonb builder = JsonbBuilder.create(config)) {
        return builder.toJson(this.data);
      } catch (Exception ex) {
        throw new JsonbException("Cannot create JSON from this data: " + this.data, ex);
      }
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
