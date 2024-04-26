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
package de.aeoniumsystems.assertjson.demo;

import de.aeoniumsystems.assertjson.AssertJson;
import org.junit.jupiter.api.Test;

/**
 * Getting started with assertJson - a simple example.
 *
 * @author robert rohm
 */
public class SimpleTest {
  
  @Test
  public void testJSONStrings(){
    
    // The demo input, a plain object
    String someJSONString = "{ \"id\": 2001, \"name\": \"Foo\" }";
    
    // First, assert that the input is JSON at all, 
    AssertJson.assertThat(someJSONString)
            // and: it is an Object (it might have been an array, too),
            .isObject()
            // then: dive into the object's properties
            .has("name", "Foo")
            .hasInt("id");
    
  }

}
