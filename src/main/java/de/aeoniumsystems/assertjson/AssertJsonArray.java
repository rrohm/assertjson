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

import jakarta.json.JsonArray;

/**
 * Assertions for JSON arrays.
 *
 * @author robert rohm
 */
public class AssertJsonArray {

  private final JsonArray jsonArray;

  public AssertJsonArray(JsonArray jsonArray) {
    this.jsonArray = jsonArray;
  }

  /**
   * Assert that the JSON array is empty.
   *
   * @return The same {@link AssertJsonArray} instance.
   */
  public AssertJsonArray isEmpty() {
    if (!this.jsonArray.isEmpty()) {
      throw new AssertionError("JSON array is expected to be empty, but is not empty.");
    }
    return this;
  }

  /**
   * Assert that the JSON array is not empty.
   *
   * @return The same {@link AssertJsonArray} instance.
   */
  public AssertJsonArray isNotEmpty() {
    if (this.jsonArray.isEmpty()) {
      throw new AssertionError("JSON array is expected not to be empty, but is empty.");
    }
    return this;
  }

  /**
   * Assert that the JSON array has a given size.
   *
   * @param size The size to test for.
   * @return The same {@link AssertJsonArray} instance.
   */
  public AssertJsonArray sizeOf(int size) {
    if (this.jsonArray.size() != size) {
      throw new AssertionError("JSON array is expected to has a size of <" + size + ">, but has a size of <" + this.jsonArray.size() + ">.");
    }
    return this;
  }

  /**
   * Assert that the JSON array has a JSON object at the given index and return an {@link AssertJsonObject} instance for
   * further inspection of this object.
   *
   * @param index The index to test.
   * @return A {@link AssertJsonObject} instance for inspecting the object at the given position.
   */
  public AssertJsonObject getObjectAt(int index) {
    return new AssertJsonObject(this.jsonArray.getJsonObject(index));
  }
}
