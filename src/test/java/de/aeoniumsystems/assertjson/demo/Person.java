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
package de.aeoniumsystems.assertjson.demo;

import java.util.ArrayList;
import java.util.List;

/**
 * Demo class for person data.
 *
 * @author robert
 */
public class Person {
  
  private String name;
  private int age;
  private boolean smokes;
  private List<String> loves = new ArrayList<>();

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public boolean getSmokes() {
    return smokes;
  }

  public void setSmokes(boolean smokes) {
    this.smokes = smokes;
  }

  public List<String> getLoves() {
    return loves;
  }

  public void setLoves(List<String> loves) {
    this.loves = loves;
  }
  
}
