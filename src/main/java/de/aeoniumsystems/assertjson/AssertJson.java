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

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonStructure;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

/**
 * Main class of the AssertJSON toolkit.
 *
 * @author robert rohm
 */
public class AssertJson {

  private final JsonStructure jsonStructure;

  /**
   * Start testing with a given JSON string.
   *
   * @param json A JSON string.
   * @return An AssertJson instance to start testing the JSON content.
   */
  public static AssertJson assertThat(String json) {
    Objects.requireNonNull(json, "JSON string must not be null.");

    if (json.isBlank()) {
      throw new AssertionError("JSON string is blank.");
    }

    return new AssertJson(Json.createReader(new StringReader(json)).read());
  }

  public static AssertJson assertThat(Path path) throws IOException {
    return assertThat(Files.readString(path));
  }

  public AssertJson(JsonStructure jsonStructure) {
    this.jsonStructure = jsonStructure;
  }

  public AssertJsonObject isObject() {
    JsonObject jsonObject = null;
    try {
      jsonObject = this.jsonStructure.asJsonObject();
    } catch (ClassCastException e) {
      throw new AssertionError("JSON is not an object: " + this.jsonStructure.toString(), e);
    }
    return new AssertJsonObject(jsonObject);
  }

  public AssertJsonArray isArray() {
    JsonArray jsonArray = null;
    try {
      jsonArray = this.jsonStructure.asJsonArray();
    } catch (ClassCastException e) {
      throw new AssertionError("JSON is not an array: " + this.jsonStructure.toString(), e);
    }
    return new AssertJsonArray(jsonArray);
  }
}
