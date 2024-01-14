/*
 * Copyright 2024 robert.
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

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLSession;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link AssertHTTP}.
 *
 * @author robert rohm
 */
public class AssertHTTPTest {

  public AssertHTTPTest() {
  }

  /**
   * Test of assertJSON method, of class AssertHTTP.
   */
  @Test
  public void testAssertJSON() {
    System.out.println("assertJSON");
    AssertHTTP instance = new AssertHTTP(createHttpResponse());
    AssertJson result = instance.assertJSON();
    assertNotNull(result);
  }

  /**
   * Test of assertNotNull method, of class AssertHTTP.
   */
  @Test
  public void testAssertNotNull() {
    System.out.println("assertNotNull");
    AssertHTTP instance = new AssertHTTP(createHttpResponse());
    AssertHTTP result = instance.assertNotNull();
    assertNotNull(result);
  }

  @Test
  public void testAssertNotNull_throws() {
    System.out.println("assertNotNull_throws");
    AssertHTTP instance = new AssertHTTP(null);
    
    AssertionError error = assertThrows(AssertionError.class, () -> {
      instance.assertNotNull();
    });
    assertNotNull(error);
    assertEquals("Response is null.", error.getMessage());
  }


  /**
   * Test of assertStatus method, of class AssertHTTP.
   */
  @Test
  public void testAssertStatus() {
    System.out.println("assertStatus");
    int statusCode = 200;
    AssertHTTP instance = new AssertHTTP(createHttpResponse());
    AssertHTTP result = instance.assertStatus(statusCode);
    assertNotNull(result);
  }

  @Test
  public void testAssertStatus_throws() {
    System.out.println("assertStatus_throws");
    int statusCode = 999;
    AssertHTTP instance = new AssertHTTP(createHttpResponse());
    AssertionError error = assertThrows(AssertionError.class, () -> {
      instance.assertStatus(statusCode);
    });
    assertNotNull(error);
    assertEquals("HTTP Status should be <999>, but is <200>.", error.getMessage());
  }

  private HttpResponse<String> createHttpResponse() {
    return new HttpResponse<String>() {
      @Override
      public int statusCode() {
        return 200;
      }

      @Override
      public HttpRequest request() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
      }

      @Override
      public Optional<HttpResponse<String>> previousResponse() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
      }

      @Override
      public HttpHeaders headers() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
      }

      @Override
      public String body() {

        Path path = Path.of(System.getProperty("user.dir"), "src", "test", "json", "array_strings.json");
        try {
          return Files.readString(path);
        } catch (IOException ex) {
          Logger.getLogger(AssertHTTPTest.class.getName()).log(Level.SEVERE, null, ex);
          throw new RuntimeException(ex);
        }
      }

      @Override
      public Optional<SSLSession> sslSession() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
      }

      @Override
      public URI uri() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
      }

      @Override
      public HttpClient.Version version() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
      }
    };
  }
}
