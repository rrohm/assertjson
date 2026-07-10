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
 * @author robert rohm
 * @param <T> Type to match
 */
public class AssertJsonTypedObject<T> {

  private final Class<T> type;
  private final T object;

  private JsonObject jsonObject;

  @FunctionalInterface
  public interface Getter<T> {

    Object get(Object target);
  }

  /**
   * Create a new assertion instance for the given JSON string.
   *
   * @param type The object type.
   * @param json The JSON string.
   */
  public AssertJsonTypedObject(Class<T> type, String json) {
    this.type = type;

    try (Jsonb jsonb = JsonbBuilder.create()) {
      this.object = jsonb.fromJson(json, this.type);
    } catch (Exception ex) {
      throw new RuntimeException("Cannot construct JSON from string: " + json, ex);
    }
  }

  /**
   * Create a new assertion instance for the given JSON object.
   *
   * @param type The object type.
   * @param jsonObject The JSON object.
   */
  public AssertJsonTypedObject(Class<T> type, JsonObject jsonObject) {
    this.type = type;
    this.jsonObject = jsonObject;

    try (Jsonb jsonb = JsonbBuilder.create()) {
      this.object = jsonb.fromJson(this.jsonObject.toString(), this.type);

    } catch (Exception ex) {
      throw new RuntimeException("Cannot construct JSON from object: " + jsonObject, ex);
    }
  }

  /**
   * Assert that the integer property equals the given integer value.
   *
   * @param getter Getter for the property
   * @param intValue Value to compare to-
   * @return This object, for method chaining.
   */
  public AssertJsonTypedObject<T> assertEquals(Getter<T> getter, int intValue) {

    if (intValue != Integer.parseInt(getter.get(this.object).toString())) {
      throw new AssertionError();
    }
    return this;
  }

  /**
   * Get the object to assert on.
   *
   * @return The wrapped object.
   */
  public T get() {
    return this.object;
  }

}
