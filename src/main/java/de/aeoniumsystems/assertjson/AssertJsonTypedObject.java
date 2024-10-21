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

import jakarta.json.JsonObject;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;

/**
 *
 * @author robert
 * @param <T> Type to match 
 */
public class AssertJsonTypedObject<T> {
  
  private final Class<T> type;
  private final T object;
    
  private JsonObject jsonObject;

  @FunctionalInterface
  public interface Getter{
    Object get();
  }
  
  public AssertJsonTypedObject(Class<T> type, String json) {
    this.type = type;
//    this.jsonObject = jsonObject;
    
    Jsonb jsonb = JsonbBuilder.create();
    this.object = jsonb.fromJson(json, type);
  }
  
  public AssertJsonTypedObject(Class<T> type, JsonObject jsonObject) {
    this.type = type;
    this.jsonObject = jsonObject;
    
    Jsonb jsonb = JsonbBuilder.create();
    this.object = jsonb.fromJson(jsonObject.toString(), type);
  }
  
  public AssertJsonTypedObject<T> assertEquals(Getter getter, int intValue){
    
    if (intValue != Integer.parseInt(getter.get().toString())) {
      throw new AssertionError();
    }
    return this;
  }
  
  public T get(){
    return this.object;
  }
  
  
}
