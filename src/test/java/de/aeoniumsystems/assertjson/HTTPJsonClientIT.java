/*
 * Copyright 2023 robert.
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

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Disabled;

/**
 *
 * @author robert
 */
public class HTTPJsonClientIT {
  
  public HTTPJsonClientIT() {
  }
  
  @BeforeAll
  public static void setUpClass() {
  }
  
  @AfterAll
  public static void tearDownClass() {
  }
  
  @BeforeEach
  public void setUp() {
  }
  
  @AfterEach
  public void tearDown() {
  }

  /**
   * Test of GET method, of class HTTPJsonClient.
   */
  @Test
  @Disabled
  public void testGET() {
    System.out.println("GET");
    String url = "";
    HTTPJsonClient expResult = null;
    HTTPJsonClient result = HTTPJsonClient.GET(url);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of POST method, of class HTTPJsonClient.
   */
  @Test
  @Disabled
  public void testPOST() {
    System.out.println("POST");
    String url = "";
    Object data = null;
    HTTPJsonClient expResult = null;
    HTTPJsonClient result = HTTPJsonClient.POST(url, data);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of authenticate method, of class HTTPJsonClient.
   */
  @Test
  @Disabled
  public void testAuthenticate() {
    System.out.println("authenticate");
    String username = "";
    String password = "";
    HTTPJsonClient instance = null;
    HTTPJsonClient expResult = null;
    HTTPJsonClient result = instance.authenticate(username, password);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of data method, of class HTTPJsonClient.
   */
  @Test
  @Disabled
  public void testData() {
    System.out.println("data");
    Object data = null;
    HTTPJsonClient instance = null;
    instance.data(data);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of host method, of class HTTPJsonClient.
   */
  @Test
  @Disabled
  public void testHost() {
    System.out.println("host");
    String host = "";
    HTTPJsonClient instance = null;
    HTTPJsonClient expResult = null;
    HTTPJsonClient result = instance.host(host);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of port method, of class HTTPJsonClient.
   */
  @Test
  @Disabled
  public void testPort() {
    System.out.println("port");
    int port = 0;
    HTTPJsonClient instance = null;
    HTTPJsonClient expResult = null;
    HTTPJsonClient result = instance.port(port);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of send method, of class HTTPJsonClient.
   */
  @Test
  @Disabled
  public void testSend() {
    System.out.println("send");
    HTTPJsonClient instance = null;
    AssertHTTP expResult = null;
    AssertHTTP result = instance.send();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }
  
}
