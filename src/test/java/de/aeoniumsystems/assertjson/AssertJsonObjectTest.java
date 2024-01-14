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

import java.io.IOException;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Disabled;

/**
 * Unit tests for {@link AssertJsonObject}.
 *
 * @author robert rohm
 */
public class AssertJsonObjectTest {

  /**
   * Test of has method, of class AssertJsonObject.
   *
   * @throws java.lang.Exception Any.
   */
  @Test
  public void testHas_propertyNotFound_throws() throws Exception {
    System.out.println("has");
    Path path = Path.of(System.getProperty("user.dir"), "src", "test", "json", "object_empty.json");
    AssertJson json = AssertJson.assertThat(path);
    AssertJsonObject instance = json.isObject();

    String property = "name";

    AssertionError error = assertThrows(AssertionError.class, () -> {
      AssertJsonObject result = instance.has(property, 123);
    });
    assertEquals("JSON object does not contain a property 'name': \n{}", error.getMessage());
  }

  /**
   * Test of has method, of class AssertJsonObject.
   *
   * @throws java.lang.Exception Any.
   */
  @Test
  public void testHas_string() throws Exception {
    System.out.println("has");
    Path path = Path.of(System.getProperty("user.dir"), "src", "test", "json", "object_person.json");
    AssertJson json = AssertJson.assertThat(path);
    AssertJsonObject instance = json.isObject();

    String property = "name";

    assertDoesNotThrow(() -> {
      return instance.has(property, "robert");
    });
  }

  /**
   * Test of has method, of class AssertJsonObject.
   *
   * @throws java.lang.Exception Any.
   */
  @Test
  public void testHas_string_incorrect_throws() throws Exception {
    System.out.println("has");
    Path path = Path.of(System.getProperty("user.dir"), "src", "test", "json", "object_person.json");
    AssertJson json = AssertJson.assertThat(path);
    AssertJsonObject instance = json.isObject();

    String property = "name";

    AssertionError error = assertThrows(AssertionError.class, () -> {
      instance.has(property, "other name");
    });
    assertEquals("JSON property 'name' is expected to be a String of <other name>, but is <robert>.", error.getMessage());
  }

  /**
   * Test of has method, of class AssertJsonObject.
   *
   * @throws java.lang.Exception Any.
   */
  @Test
  public void testHas_string_intValue_throws() throws Exception {
    System.out.println("has");
    Path path = Path.of(System.getProperty("user.dir"), "src", "test", "json", "object_person.json");
    AssertJson json = AssertJson.assertThat(path);
    AssertJsonObject instance = json.isObject();

    String property = "name";

    AssertionError error = assertThrows(AssertionError.class, () -> {
      instance.has(property, 52);
    });
    assertEquals("JSON property 'name' is expected to contain an Integer, but contains a <STRING>.", error.getMessage());
  }

  /**
   * Test of has method, of class AssertJsonObject.
   *
   * @throws java.lang.Exception Any.
   */
  @Test
  public void testHas_int() throws Exception {
    System.out.println("has");
    Path path = Path.of(System.getProperty("user.dir"), "src", "test", "json", "object_person.json");
    AssertJson json = AssertJson.assertThat(path);
    AssertJsonObject instance = json.isObject();

    String property = "age";

    assertDoesNotThrow(() -> {
      return instance.has(property, 52);
    });
  }

  @Test
  public void testHas_int_propertyNotFound_throws() throws Exception {
    System.out.println("has_propertyNotFound_throws");
    Path path = Path.of(System.getProperty("user.dir"), "src", "test", "json", "object_person.json");
    AssertJson json = AssertJson.assertThat(path);
    AssertJsonObject instance = json.isObject();

    String property = "birthday";

    AssertionError error = assertThrows(AssertionError.class, () -> {
      instance.has(property, "52");
    });
    assertEquals("JSON object does not contain a property 'birthday': \n"
            + "{\"name\":\"robert\",\"age\":52,\"smokes\":false,\"loves\":[\"Flowers\",\"Pets\"]}", error.getMessage());
  }

  @Test
  public void testHas_int_asString_throws() throws Exception {
    System.out.println("has");
    Path path = Path.of(System.getProperty("user.dir"), "src", "test", "json", "object_person.json");
    AssertJson json = AssertJson.assertThat(path);
    AssertJsonObject instance = json.isObject();

    String property = "age";

    AssertionError error = assertThrows(AssertionError.class, () -> {
      instance.has(property, "52");
    });
    assertEquals("JSON property 'age' is expected to contain a String, but contains a <NUMBER>.", error.getMessage());
  }

  /**
   * Test of has method, of class AssertJsonObject.
   *
   * @throws java.lang.Exception Any.
   */
  @Test
  public void testHas_int_incorrect_throws() throws Exception {
    System.out.println("has");
    Path path = Path.of(System.getProperty("user.dir"), "src", "test", "json", "object_person.json");
    AssertJson json = AssertJson.assertThat(path);
    AssertJsonObject instance = json.isObject();

    String property = "age";

    AssertionError error = assertThrows(AssertionError.class, () -> {
      instance.has(property, 53);
    });

    assertEquals("JSON property 'age' is expected to be an Integer of <53>, but is <52>.", error.getMessage());
  }

  @Test
  public void testHas_int_incorrectNumberType_throws() throws Exception {
    System.out.println("has");
    Path path = Path.of(System.getProperty("user.dir"), "src", "test", "json", "object_person.json");
    AssertJson json = AssertJson.assertThat(path);
    AssertJsonObject instance = json.isObject();

    String property = "age";

    AssertionError error = assertThrows(AssertionError.class, () -> {
      instance.has(property, 53.3);
    });

    assertEquals("JSON property 'age' is expected to be <53.3>, but is <52>.", error.getMessage());
  }

  /**
   * Test of has method, of class AssertJsonObject.
   */
  @Test
  @Disabled("Template")
  public void testHas() {
    System.out.println("has");
    String property = "";
    Object value = null;
    AssertJsonObject instance = null;
    AssertJsonObject expResult = null;
    AssertJsonObject result = instance.has(property, value);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getArray method, of class AssertJsonObject.
   *
   * @throws java.lang.Exception Any.
   */
  @Test
  public void testGetArray() throws Exception {
    System.out.println("hasArray");
    Path path = Path.of(System.getProperty("user.dir"), "src", "test", "json", "object_person.json");
    AssertJson json = AssertJson.assertThat(path);
    AssertJsonObject instance = json.isObject();
    String property = "loves";

    AssertJsonArray result = instance.getArray(property);
    assertNotNull(result);
  }

  /**
   * Test of hasInt method, of class AssertJsonObject.
   *
   * @throws java.lang.Exception Any.
   */
  @Test
  public void testHasInt() throws Exception {
    System.out.println("hasInt");
    Path path = Path.of(System.getProperty("user.dir"), "src", "test", "json", "object_numbers.json");
    AssertJson json = AssertJson.assertThat(path);
    AssertJsonObject instance = json.isObject();
    String property = "integer";

    AssertJsonObject result = instance.hasInt(property);
    assertNotNull(result);
  }

  /**
   * Test of hasInt method, of class AssertJsonObject.
   *
   * @throws java.lang.Exception Any.
   */
  @Test
  public void testHasInt_long_throws() throws Exception {
    System.out.println("hasInt_long_throws");
    Path path = Path.of(System.getProperty("user.dir"), "src", "test", "json", "object_numbers.json");
    AssertJson json = AssertJson.assertThat(path);
    AssertJsonObject instance = json.isObject();
    String property = "long";

    AssertionError error = assertThrows(AssertionError.class, () -> instance.hasInt(property));
    assertEquals("JSON property 'long' is bigger than Integer.MAX_VALUE: <2147483648>.", error.getMessage());
  }

  @Test
  public void testHasInt_float_throws() throws Exception {
    System.out.println("hasInt_float_throws");
    Path path = Path.of(System.getProperty("user.dir"), "src", "test", "json", "object_numbers.json");
    AssertJson json = AssertJson.assertThat(path);
    AssertJsonObject instance = json.isObject();
    String property = "float";

    AssertionError error = assertThrows(AssertionError.class, () -> instance.hasInt(property));
    assertEquals("JSON property 'float' is not an integer value: <1234.5>.", error.getMessage());
  }

  @Test
  public void testHasInt_NaN_throws() throws Exception {
    System.out.println("hasInt_NaN_throws");
    Path path = Path.of(System.getProperty("user.dir"), "src", "test", "json", "object_numbers.json");
    AssertJson json = AssertJson.assertThat(path);
    AssertJsonObject instance = json.isObject();
    String property = "NaN";

    AssertionError error = assertThrows(AssertionError.class, () -> instance.hasInt(property));
    assertEquals("JSON property 'NaN' is expected to be an Integer, but is of type <OBJECT>.", error.getMessage());
  }
  
  @Test
  public void testHasInt_propertyNotFound_throws() throws Exception {
    System.out.println("hasInt_propertyNotFound_throws");
    Path path = Path.of(System.getProperty("user.dir"), "src", "test", "json", "object_person.json");
    AssertJson json = AssertJson.assertThat(path);
    AssertJsonObject instance = json.isObject();

    String property = "birthday";

    AssertionError error = assertThrows(AssertionError.class, () -> {
      instance.hasInt(property);
    });
    assertEquals("JSON object does not contain a property 'birthday': \n"
            + "{\"name\":\"robert\",\"age\":52,\"smokes\":false,\"loves\":[\"Flowers\",\"Pets\"]}", error.getMessage());
  }

  /**
   * Test of hasLong method, of class AssertJsonObject.
   *
   * @throws java.lang.Exception Any.
   */
  @Test
  public void testHasLong() throws Exception {
    System.out.println("hasLong");
    Path path = Path.of(System.getProperty("user.dir"), "src", "test", "json", "object_numbers.json");
    AssertJson json = AssertJson.assertThat(path);
    AssertJsonObject instance = json.isObject();
    String property = "long";

    AssertJsonObject result = instance.hasLong(property);
    assertNotNull(result);
  }
  

  @Test
  public void testHas_long_propertyNotFound_throws() throws Exception {
    System.out.println("has_long_propertyNotFound_throws");
    Path path = Path.of(System.getProperty("user.dir"), "src", "test", "json", "object_person.json");
    AssertJson json = AssertJson.assertThat(path);
    AssertJsonObject instance = json.isObject();

    String property = "birthday";

    AssertionError error = assertThrows(AssertionError.class, () -> {
      instance.hasLong(property);
    });
    assertEquals("JSON object does not contain a property 'birthday': \n"
            + "{\"name\":\"robert\",\"age\":52,\"smokes\":false,\"loves\":[\"Flowers\",\"Pets\"]}", error.getMessage());
  }

  /**
   * Test of hasNot method, of class AssertJsonObject.
   *
   * @throws java.lang.Exception Any.
   */
  @Test
  public void testHasNot() throws Exception {
    System.out.println("hasNot");
    Path path = Path.of(System.getProperty("user.dir"), "src", "test", "json", "object_empty.json");
    AssertJson json = AssertJson.assertThat(path);
    AssertJsonObject instance = json.isObject();

    String property = "name";
    AssertJsonObject result = instance.hasNot(property);

    assertNotNull(result);
  }

  @Test
  public void testHasNot_throws() throws Exception {
    System.out.println("hasNot_throws");
    Path path = Path.of(System.getProperty("user.dir"), "src", "test", "json", "object_person.json");
    AssertJson json = AssertJson.assertThat(path);
    AssertJsonObject instance = json.isObject();

    String property = "name";

    AssertionError error = assertThrows(AssertionError.class, () -> {
      AssertJsonObject result = instance.hasNot(property);
    });
    assertEquals("JSON object does contain a property 'name, but is expected not to': \n"
            + "{\"name\":\"robert\",\"age\":52,\"smokes\":false,\"loves\":[\"Flowers\",\"Pets\"]}", error.getMessage());
  }
}
