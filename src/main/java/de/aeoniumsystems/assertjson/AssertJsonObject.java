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

import jakarta.json.JsonNumber;
import jakarta.json.JsonObject;
import jakarta.json.JsonValue;
import java.math.BigDecimal;

/**
 * Assertions for JSON objects.
 *
 * @author robert rohm
 */
public class AssertJsonObject {

  private final JsonObject jsonObject;

  public AssertJsonObject(JsonObject asJsonObject) {
    this.jsonObject = asJsonObject;
  }

  /**
   * Assert that the given JSON object contains a property of the given name with the given value. Currently supported
   * types are:
   * <ul>
   * <li>String</li>
   * <li>Integer</li>
   * </ul>
   *
   * @param <T> The value type.
   * @param property The property name to test for.
   * @param value Value of any type.
   * @return This object, to allow method chaining.
   */
  public <T> AssertJsonObject has(String property, T value) {
    if (!this.jsonObject.containsKey(property)) {
      throw new AssertionError("JSON object does not contain a property '" + property + "'.");
    }
    if (value instanceof String) {
      String t = (String) value;
      String foundString = null;
      try {
        foundString = this.jsonObject.getString(property);
      } catch (ClassCastException e) {
        throw new AssertionError("JSON property '" + property + "' is expected to contain a String, but contains a <" + this.jsonObject.get(property).getValueType() + ">.", e);
      }

      if (!t.equals(foundString)) {
        throw new AssertionError("JSON property '" + property + "' is expected to be a String of <" + t + ">, but is <" + foundString + ">.");
      }
    } else if (value instanceof Integer) {
      Integer integer = (Integer) value;
      JsonNumber jsonNumber;
      try {
        jsonNumber = this.jsonObject.getJsonNumber(property);
      } catch (ClassCastException e) {
        throw new AssertionError("JSON property '" + property + "' is expected to contain an Integer, but contains a <" + this.jsonObject.get(property).getValueType() + ">.", e);
      }
      int intValue = jsonNumber.intValue();

      if (!integer.equals(intValue)) {
        throw new AssertionError("JSON property '" + property + "' is expected to be an Integer of <" + integer + ">, but is <" + intValue + ">.");
      }
    } else if (value instanceof Number) {
      Number number = (Number) value;
      JsonNumber jsonNumber = this.jsonObject.getJsonNumber(property);
      BigDecimal bigDecimalValue = jsonNumber.bigDecimalValue();

      if (!BigDecimal.valueOf(number.doubleValue()).equals(bigDecimalValue)) {
        throw new AssertionError("JSON property '" + property + "' is expected to be <" + number + ">, but is <" + bigDecimalValue + ">.");
      }
    }
    return this;
  }

  public AssertJsonArray getArray(String property) {
    return new AssertJsonArray(this.jsonObject.getJsonArray(property));
  }

  public AssertJsonObject hasInt(String property) {
    if (!this.jsonObject.containsKey(property)) {
      throw new AssertionError("JSON object does not contain a property '" + property + "'.");
    }
    JsonValue jsonValue = this.jsonObject.get(property);
    JsonValue.ValueType valueType = jsonValue.getValueType();

    if (valueType.equals(JsonValue.ValueType.NUMBER)) {
      JsonNumber jsonNumber;
      try {
        jsonNumber = this.jsonObject.getJsonNumber(property);
      } catch (ClassCastException e) {
        throw new AssertionError("JSON property '" + property + "' is expected to be an Integer, but is a <" + valueType + ">.", e);
      }

      if (!jsonNumber.isIntegral()) {
        throw new AssertionError("JSON property '" + property + "' is not an integer value: <" + jsonNumber.toString() + ">.");
      } else {
        long longValueExact;
        longValueExact = jsonNumber.longValueExact();
        if (longValueExact > Integer.MAX_VALUE) {
          throw new AssertionError("JSON property '" + property + "' is bigger than Integer.MAX_VALUE: <" + longValueExact + ">.");
        } else if (longValueExact < Integer.MIN_VALUE) {
          throw new AssertionError("JSON property '" + property + "' is smaller than Integer.MIN_VALUE: <" + longValueExact + ">.");
        }
      }

    } else {
      throw new AssertionError("JSON property '" + property + "' is expected to be an Integer, but is a <" + valueType + ">.");
    }

    return this;
  }

  public AssertJsonObject hasLong(String property) {
    if (!this.jsonObject.containsKey(property)) {
      throw new AssertionError("JSON object does not contain a property '" + property + "'.");
    }
    JsonValue jsonValue = this.jsonObject.get(property);
    JsonValue.ValueType valueType = jsonValue.getValueType();

    if (valueType.equals(JsonValue.ValueType.NUMBER)) {
      JsonNumber jsonNumber;
      try {
        jsonNumber = this.jsonObject.getJsonNumber(property);
      } catch (ClassCastException e) {
        throw new AssertionError("JSON property '" + property + "' is expected to be a Long, but is a <" + valueType + ">.", e);
      }

      if (!jsonNumber.isIntegral()) {
        throw new AssertionError("JSON property '" + property + "' is not a long value: <" + jsonNumber.toString() + ">.");
      } else {
        try {
          long longValueExact = jsonNumber.longValueExact();
        } catch (ArithmeticException e) {
          throw new AssertionError("JSON property '" + property + "' is not a long value: <" + jsonNumber.toString() + ">.");
        }
      }

    } else {
      throw new AssertionError("JSON property '" + property + "' is expected to be an Integer, but is a <" + valueType + ">.");
    }

    return this;
  }
}
