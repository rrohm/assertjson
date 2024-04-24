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

import java.io.IOException;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link AssertJsonArray}.
 *
 * @author robert rohm
 */
public class AssertJsonArrayTest {

  public AssertJsonArrayTest() {
  }

  /**
   * Test of sizeOf method, of class AssertJsonArray.
   *
   * @throws java.lang.Exception Any.
   */
  @Test
  public void testSizeOf() throws Exception {
    System.out.println("sizeOf");
    int size = 3;
    Path path = Path.of(System.getProperty("user.dir"), "src", "test", "json", "array_strings.json");
    AssertJson json = AssertJson.assertThat(path);
    AssertJsonArray instance = json.isArray();

    assertDoesNotThrow(() -> instance.sizeOf(size));
  }

  /**
   * Test of sizeOf method, of class AssertJsonArray.
   *
   * @throws java.lang.Exception Any.
   */
  @Test
  public void testSizeOf_throws() throws Exception {
    System.out.println("sizeOf");
    int size = 3;
    Path path = Path.of(System.getProperty("user.dir"), "src", "test", "json", "array_empty.json");
    AssertJson json = AssertJson.assertThat(path);
    AssertJsonArray instance = json.isArray();

    AssertionError error = assertThrows(AssertionError.class, () -> instance.sizeOf(size));
    assertEquals("JSON array is expected to has a size of <3>, but has a size of <0>.", error.getMessage());
  }

  /**
   * Test of getObjectAt method, of class AssertJsonArray.
   *
   * @throws java.lang.Exception Any.
   */
  @Test
  public void testGetObjectAt() throws Exception {
    System.out.println("hasObjectAt");
    int index = 0;
    Path path = Path.of(System.getProperty("user.dir"), "src", "test", "json", "array_objects.json");
    AssertJson json = AssertJson.assertThat(path);
    AssertJsonArray instance = json.isArray();

    AssertJsonObject result = instance.getObjectAt(index);
    assertNotNull(result);
  }

  /**
   * Test of isEmpty method, of class AssertJsonArray.
   *
   * @throws java.lang.Exception any
   */
  @Test
  public void testAssertEmtpy() throws Exception {
    System.out.println("assertEmtpy");
    Path path = Path.of(System.getProperty("user.dir"), "src", "test", "json", "array_empty.json");
    AssertJsonArray instance = AssertJson.assertThat(path).isArray();
    AssertJsonArray result = instance.isEmpty();
    assertNotNull(result);
  }

  @Test
  public void testAssertEmtpy_throws() throws IOException {
    System.out.println("assertEmtpy_throws");
    Path path = Path.of(System.getProperty("user.dir"), "src", "test", "json", "array_strings.json");
    AssertJsonArray instance = AssertJson.assertThat(path).isArray();
    AssertionError error = assertThrows(AssertionError.class, () -> instance.isEmpty());
    assertNotNull(error);
    assertEquals("JSON array is expected to be empty, but is not empty.", error.getMessage());
  }

  /**
   * Test of isNotEmpty method, of class AssertJsonArray.
   *
   * @throws java.lang.Exception any
   */
  @Test
  public void testAssertNotEmtpy() throws Exception {
    System.out.println("assertNotEmtpy");
    Path path = Path.of(System.getProperty("user.dir"), "src", "test", "json", "array_strings.json");
    AssertJsonArray instance = AssertJson.assertThat(path).isArray();
    AssertJsonArray result = instance.isNotEmpty();
    assertNotNull(result);
  }

  @Test
  public void testAssertNotEmtpy_throws() throws Exception {
    System.out.println("assertNotEmtpy_throws");
    Path path = Path.of(System.getProperty("user.dir"), "src", "test", "json", "array_empty.json");
    AssertJsonArray instance = AssertJson.assertThat(path).isArray();
    AssertionError error = assertThrows(AssertionError.class, () -> instance.isNotEmpty());
    assertNotNull(error);
    assertEquals("JSON array is expected not to be empty, but is empty.", error.getMessage());
  }

}
