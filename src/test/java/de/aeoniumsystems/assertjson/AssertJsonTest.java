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

import java.nio.file.Path;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link AssertJson}.
 *
 * @author robert rohm
 */
public class AssertJsonTest {

  public AssertJsonTest() {
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
   * Test of assertThat method, of class AssertJson.
   */
  @Test
  public void testAssertThat_String_blankThrows() {
    System.out.println("assertThat");
    String json = "";

    AssertionError error = assertThrows(AssertionError.class, () -> AssertJson.assertThat(json));
    assertEquals("JSON string is blank.", error.getMessage());
  }

  @Test
  public void testAssertThat_String_nullThrows() {
    System.out.println("assertThat");
    String json = null;

    NullPointerException error = assertThrows(NullPointerException.class, () -> AssertJson.assertThat(json));
    assertEquals("JSON string must not be null.", error.getMessage());
  }

  /**
   * Test of assertThat method, of class AssertJson.
   *
   * @throws java.lang.Exception Any.
   */
  @Test
  public void testAssertThat_Path() throws Exception {
    System.out.println("assertThat");
    Path path = Path.of(System.getProperty("user.dir"), "src", "test", "json", "object_empty.json");
    AssertJson result = AssertJson.assertThat(path);
    assertNotNull(result);
  }

  /**
   * Test of assertThat method, of class AssertJson.
   *
   * @throws java.lang.Exception Any.
   */
  @Test
  public void testAssertThat_Path_Array() throws Exception {
    System.out.println("assertThat");
    Path path = Path.of(System.getProperty("user.dir"), "src", "test", "json", "array_empty.json");
    AssertJson result = AssertJson.assertThat(path);
    assertNotNull(result);
  }

  /**
   * Test of isObject method, of class AssertJson.
   *
   * @throws java.lang.Exception Any.
   */
  @Test
  public void testIsObject() throws Exception {
    System.out.println("isObject");
    Path path = Path.of(System.getProperty("user.dir"), "src", "test", "json", "object_empty.json");
    AssertJson instance = AssertJson.assertThat(path);
    AssertJsonObject result = instance.isObject();
    assertNotNull(result);
  }

  /**
   * Test of isObject method, of class AssertJson.
   *
   * @throws java.lang.Exception Any.
   */
  @Test
  public void testIsObject_array_throws() throws Exception {
    System.out.println("isObject");
    Path path = Path.of(System.getProperty("user.dir"), "src", "test", "json", "array_empty.json");
    AssertJson instance = AssertJson.assertThat(path);

    AssertionError error = assertThrows(AssertionError.class, () -> {
      AssertJsonObject result = instance.isObject();
    });
    assertEquals("JSON is not an object: []", error.getMessage());
  }

  /**
   * Test of assertThat method, of class AssertJson.
   */
  @Test
  public void testAssertThat_String() {
    System.out.println("assertThat");
    String json = "{}";
    AssertJson result = AssertJson.assertThat(json);
    assertNotNull(result);
  }

  /**
   * Test of isArray method, of class AssertJson.
   *
   * @throws java.lang.Exception Any.
   */
  @Test
  public void testIsArray() throws Exception {
    System.out.println("isArray");
    Path path = Path.of(System.getProperty("user.dir"), "src", "test", "json", "array_empty.json");
    AssertJson instance = AssertJson.assertThat(path);
    AssertJsonArray result = instance.isArray();
    assertNotNull(result);
  }

  /**
   * Test of isArray method, of class AssertJson.
   *
   * @throws java.lang.Exception Any.
   */
  @Test
  public void testIsArray_object_throws() throws Exception {
    System.out.println("isArray");
    Path path = Path.of(System.getProperty("user.dir"), "src", "test", "json", "object_empty.json");
    AssertJson instance = AssertJson.assertThat(path);

    AssertionError error = assertThrows(AssertionError.class, () -> {
      AssertJsonArray result = instance.isArray();
    });
    assertEquals("JSON is not an array: {}", error.getMessage());
  }
}
