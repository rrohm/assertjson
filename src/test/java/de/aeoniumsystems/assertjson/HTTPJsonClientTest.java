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

import com.sun.net.httpserver.HttpServer;
import de.aeoniumsystems.assertjson.demo.Person;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.file.Path;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;

/**
 * Unit tests for {@link HTTPJsonClient}.
 *
 * @author robert rohm
 */
public class HTTPJsonClientTest {

  private static final String URL_OK = "http://127.0.0.1:9999/ok";
  private HttpServer server;

  @BeforeEach
  public void setUp() throws IOException {

    server = HttpServer.create(new InetSocketAddress(9999), 0);
    server.createContext("/ok", httpExchange -> {
      String response = "OK.";

      httpExchange.sendResponseHeaders(200, response.getBytes().length);
      httpExchange.getResponseHeaders().add("Content-Type", "text/html");

      try (OutputStream os = httpExchange.getResponseBody()) {
        os.write(response.getBytes());
      }
    });
    server.setExecutor(null); // creates a default executor
    server.start();
  }

  @AfterEach
  public void tearDown(){
    this.server.stop(0);
  }
  
  /**
   * Test of GET method, of class HTTPJsonClient.
   */
  @Test
  public void testGET() {
    System.out.println("GET");
    String url = URL_OK;

    HTTPJsonClient result = HTTPJsonClient.GET(url);

    assertNotNull(result);
    assertEquals(HTTPJsonClient.RequestType.GET, result.getRequestType());
    assertEquals(URL_OK, result.getUrl());
  }

  /**
   * Test of POST method, of class HTTPJsonClient.
   */
  @Test
  public void testPOST() {
    System.out.println("POST");
    String url = URL_OK;
    Object data = new Object();

    HTTPJsonClient result = HTTPJsonClient.POST(url, data);

    assertNotNull(result);
    assertEquals(HTTPJsonClient.RequestType.POST, result.getRequestType());
    assertEquals(URL_OK, result.getUrl());
  }

  /**
   * Test of authenticate method, of class HTTPJsonClient.
   */
  @Test
  public void testAuthenticate() {
    System.out.println("authenticate");
    String username = "";
    String password = "";
    String url = URL_OK;

    HTTPJsonClient instance = HTTPJsonClient.GET(url);
    HTTPJsonClient result = instance.authenticate(username, password);

    assertNotNull(result);
  }

  /**
   * Test of send method, of class HTTPJsonClient.
   */
  @Test
  public void testSend() {
    System.out.println("send");
    String url = URL_OK;

    HTTPJsonClient instance = HTTPJsonClient.GET(url);
    AssertHTTP result = instance.send();

    assertNotNull(result);
  }

  @Test
  public void testSend_uri_throws() {
    System.out.println("send");
    String url = "foo, baz";

    HTTPJsonClient instance = HTTPJsonClient.GET(url);

    assertThrows(RuntimeException.class, instance::send);
  }

  @Test
  public void testSend_header() {
    System.out.println("send_header");
    String url = URL_OK;

    HTTPJsonClient instance = HTTPJsonClient.GET(url)
            .header("foo", "baz");

    AssertHTTP result = instance.send();

    assertNotNull(result);
  }

  @Test
  public void testSend_POST_string() {
    System.out.println("send_POST_string");
    String url = URL_OK;

    HTTPJsonClient instance = HTTPJsonClient.POST(url, "some data");
    AssertHTTP result = instance.send();

    assertNotNull(result);
  }

  @Test
  public void testSend_POST_Person() {
    System.out.println("send_POST_Person");
    String url = URL_OK;

    HTTPJsonClient instance = HTTPJsonClient.POST(url, new Person());
    AssertHTTP result = instance.send();

    assertNotNull(result);
  }

  @Test
  @Disabled("TODO")
  public void testSend_DELETE() {
    System.out.println("send");
    String url = URL_OK;

    HTTPJsonClient instance = HTTPJsonClient.DELETE(url);
    AssertHTTP result = instance.send();

    assertNotNull(result);
  }

  @Test
  public void testSend_POST() {
    System.out.println("send");
    String url = URL_OK;
    Object data = "Test";

    HTTPJsonClient instance = HTTPJsonClient.POST(url, data);
    AssertHTTP result = instance.send();

    assertNotNull(result);
  }

  @Test
  public void testSend_PUT() {
    System.out.println("send");
    String url = URL_OK;
    Object data = "Test";

    HTTPJsonClient instance = HTTPJsonClient.PUT(url, data);
    AssertHTTP result = instance.send();

    assertNotNull(result);
  }

  /**
   * Test of DELETE method, of class HTTPJsonClient.
   */
  @Test
  public void testDELETE() {
    System.out.println("DELETE");
    String url = URL_OK;

    HTTPJsonClient result = HTTPJsonClient.DELETE(url);

    assertNotNull(result);
    assertEquals(url, result.getUrl());
    assertEquals(HTTPJsonClient.RequestType.DELETE, result.getRequestType());
  }

  /**
   * Test of POST method, of class HTTPJsonClient.
   */
  @Test
  public void testPOST_String() {
    System.out.println("POST");
    String url = URL_OK;

    HTTPJsonClient result = HTTPJsonClient.POST(url);

    assertNotNull(result);
    assertEquals(url, result.getUrl());
    assertEquals(HTTPJsonClient.RequestType.POST, result.getRequestType());
  }

  /**
   * Test of POST method, of class HTTPJsonClient.
   */
  @Test
  public void testPOST_String_Object() {
    System.out.println("POST");
    String url = URL_OK;
    Object data = null;

    HTTPJsonClient result = HTTPJsonClient.POST(url, data);

    assertNotNull(result);
    assertEquals(url, result.getUrl());
    assertEquals(HTTPJsonClient.RequestType.POST, result.getRequestType());
  }

  /**
   * Test of PUT method, of class HTTPJsonClient.
   */
  @Test
  public void testPUT_String() {
    System.out.println("PUT");
    String url = URL_OK;

    HTTPJsonClient result = HTTPJsonClient.PUT(url);

    assertNotNull(result);
    assertEquals(url, result.getUrl());
    assertEquals(HTTPJsonClient.RequestType.PUT, result.getRequestType());
  }

  /**
   * Test of PUT method, of class HTTPJsonClient.
   */
  @Test
  public void testPUT_String_Object() {
    System.out.println("PUT");
    String url = URL_OK;
    Object data = null;

    HTTPJsonClient result = HTTPJsonClient.PUT(url, data);

    assertNotNull(result);
    assertEquals(url, result.getUrl());
    assertEquals(HTTPJsonClient.RequestType.PUT, result.getRequestType());
  }

  /**
   * Test of PUT method, of class HTTPJsonClient.
   */
  @Test
  public void testPUT_String_Path() throws Exception {
    System.out.println("PUT");
    String url = "";
    Path dataPath = Path.of("src", "test", "json", "object_empty.json");

    HTTPJsonClient result = HTTPJsonClient.PUT(url, dataPath);

    assertNotNull(result);
    assertEquals(url, result.getUrl());
    assertEquals(HTTPJsonClient.RequestType.PUT, result.getRequestType());
  }

  /**
   * Test of contentType method, of class HTTPJsonClient.
   */
  @Test
  public void testContentType() {
    System.out.println("contentType");
    String contentType = "text/plain";

    HTTPJsonClient instance = HTTPJsonClient.GET(URL_OK);
    HTTPJsonClient result = instance.contentType(contentType);

    assertNotNull(result);
  }

  /**
   * Test of data method, of class HTTPJsonClient.
   */
  @Test
  public void testData_Object() {
    System.out.println("data");
    Object data = "Some string";

    HTTPJsonClient instance = HTTPJsonClient.POST(URL_OK);

    HTTPJsonClient result = instance.data(data);

    assertNotNull(result);
  }

  /**
   * Test of data method, of class HTTPJsonClient.
   *
   * @throws java.lang.Exception any
   */
  @Test
  public void testData_Path() throws Exception {
    System.out.println("data");
    Path dataPath = Path.of("src", "test", "json", "object_empty.json");
    HTTPJsonClient instance = HTTPJsonClient.POST(URL_OK);
    HTTPJsonClient result = instance.data(dataPath);
    assertNotNull(result);
  }

  /**
   * Test of header method, of class HTTPJsonClient.
   */
  @Test
  public void testHeader() {
    System.out.println("header");
    String key = "";
    String value = "";
    HTTPJsonClient instance = HTTPJsonClient.GET(URL_OK);

    HTTPJsonClient result = instance.header(key, value);
    assertNotNull(result);
  }

  /**
   * Test of getRequestType method, of class HTTPJsonClient.
   */
  @Test
  public void testGetRequestType() {
    System.out.println("getRequestType");
    HTTPJsonClient.RequestType requestType = HTTPJsonClient.RequestType.POST;

    HTTPJsonClient instance = new HTTPJsonClient(URL_OK, requestType);

    HTTPJsonClient.RequestType result = instance.getRequestType();
    assertEquals(requestType, result);
  }

  /**
   * Test of getUrl method, of class HTTPJsonClient.
   */
  @Test
  public void testGetUrl() {
    System.out.println("getUrl");
    HTTPJsonClient.RequestType requestType = HTTPJsonClient.RequestType.POST;

    HTTPJsonClient instance = new HTTPJsonClient(URL_OK, requestType);
    String result = instance.getUrl();
    assertEquals(URL_OK, result);
  }

}
