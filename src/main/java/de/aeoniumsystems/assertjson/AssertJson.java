/*
 * ----- AEONIUM SOFTWARE SYSTEMS SOURCE CODE LICENSE -----
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 2023 AEONIUM SOFTWARE SYSTEMS - ALL RIGHTS RESERVED.
 *
 * The contents of this file are intellectual property of
 * Aeonium Software Systems, Robert Rohm. All rights reserved. 
 * You must NOT, especially:
 * - redistribute this file in source form,
 * - redistribute this file in binary form,
 * - modify this file,
 * - remove or modify this copyright information,
 * - use this file for your own work
 * WITHOUT WRITTEN PERMISSION.
 *
 * Anyway, we appreciate any interest in our work and knowledge.
 * So, if you wish to use this file for your own purposes, 
 * please contact us:
 * mailto:info@aeonium-systems.de
 * 
 *
 *   2023 aeonium software systems UG (haftungsbeschränkt), Robert Rohm.
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
