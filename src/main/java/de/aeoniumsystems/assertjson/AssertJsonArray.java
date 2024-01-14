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
