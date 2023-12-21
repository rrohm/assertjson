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
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
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

}
