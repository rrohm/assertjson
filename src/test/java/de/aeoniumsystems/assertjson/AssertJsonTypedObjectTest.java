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

import de.aeoniumsystems.assertjson.demo.Person;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Disabled;

/**
 *
 * @author robert
 */
public class AssertJsonTypedObjectTest {
  
  @Test
  public void testConstruct() throws Exception {
    System.out.println("construct");
    
    Path path = Path.of(System.getProperty("user.dir"), "src", "test", "json", "object_person.json");
    AssertJson json = AssertJson.assertThat(path);
    
    
    int intValue = 52;
    AssertJsonTypedObject<Person> instance = new AssertJsonTypedObject(Person.class, Files.readString(path));
    
    Person person = instance.get();
    assertNotNull(person);
  }
  
  @Test
  @SuppressWarnings("ResultOfObjectAllocationIgnored")
  public void testConstruct_throws() {
    System.out.println("construct_throws");
    
    assertThrows(RuntimeException.class, () -> {
      new AssertJsonTypedObject(Person.class, "some foo");
    });
  }
  
  
  @Test
  @Disabled("TODO")
  @SuppressWarnings("ResultOfObjectAllocationIgnored")
  public void testConstruct_jsonObj_throws() {
    System.out.println("construct_jsonObj_throws");
    
    final JsonObject jsonObj = Json.createObjectBuilder().add("foo", "baz").build();
    assertThrows(RuntimeException.class, () -> {
      new AssertJsonTypedObject(Person.class, (JsonObject) jsonObj);
    });
  }
  
  /**
   * Test of assertEquals method, of class AssertJsonTypedObject.
   * @throws java.lang.Exception any
   */
  @Test
  public void testGet() throws Exception {
    System.out.println("assertEquals");
    
    Path path = Path.of(System.getProperty("user.dir"), "src", "test", "json", "object_person.json");
    AssertJson json = AssertJson.assertThat(path);

    AssertJsonTypedObject instance = json.isObject().is(Person.class);
    
    Object result = instance.get();
    assertNotNull(result);
  }

  /**
   * Test of assertEquals method, of class AssertJsonTypedObject.
   * @throws java.lang.Exception any
   */
  @Test
  public void testAssertEquals() throws Exception {
    System.out.println("assertEquals");
    Path path = Path.of(System.getProperty("user.dir"), "src", "test", "json", "object_person.json");
    AssertJson json = AssertJson.assertThat(path);
    AssertJsonTypedObject<Class<Person>> instance = json.isObject().is(Person.class);
    
    int intValue = 52;
    AssertJsonTypedObject<Class<Person>> result = instance.assertEquals(target -> ((Person)target).getAge(), intValue);
    assertNotNull(result);
  }
  
  @Test
  public void testAssertEquals_assert_false() throws Exception {
    System.out.println("assertEquals_assert_false");
    Path path = Path.of(System.getProperty("user.dir"), "src", "test", "json", "object_person.json");
    AssertJson json = AssertJson.assertThat(path);
    AssertJsonTypedObject<Class<Person>> instance = json.isObject().is(Person.class);
    
    int intValue = 51;
    assertThrows(AssertionError.class, () -> {
      instance.assertEquals(target -> ((Person)target).getAge(), intValue);
    });
  }
  
}
