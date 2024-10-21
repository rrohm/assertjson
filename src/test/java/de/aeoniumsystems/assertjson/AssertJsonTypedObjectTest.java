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
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author robert
 */
public class AssertJsonTypedObjectTest {
  
  public AssertJsonTypedObjectTest() {
  }

  @Test
  public void testConstruct() throws Exception {
    System.out.println("construct");
    
    Path path = Path.of(System.getProperty("user.dir"), "src", "test", "json", "object_person.json");
    AssertJson json = AssertJson.assertThat(path);
    
    
    int intValue = 52;
    AssertJsonTypedObject<Person> instance = new AssertJsonTypedObject(Person.class, Files.readString(path));
    
    Person person = instance.get();
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
    int intValue = 52;
    AssertJsonTypedObject instance = json.isObject().is(Person.class);
    
    Object result = instance.get();
  }
  
}
