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

import java.net.http.HttpResponse;

/**
 * Assertions for HTTP responses.
 *
 * @author robert rohm
 */
public class AssertHTTP {

  private final HttpResponse<String> response;

  public AssertHTTP(HttpResponse<String> response) {
    this.response = response;
  }
  
  public AssertJson assertJSON(){
    return AssertJson.assertThat(this.response.body());
  }
  
  public AssertHTTP assertNotNull(){
    if (this.response == null) {
      throw new AssertionError("Response is null.");
    }
    return this;
  }
  
  public AssertHTTP assertStatus(int statusCode){
    if (response.statusCode() != statusCode) {
      throw new AssertionError("HTTP Status should be <" + statusCode + ">, but is <" + this.response.statusCode() + ">.");
    }
    return this;
  }

}
