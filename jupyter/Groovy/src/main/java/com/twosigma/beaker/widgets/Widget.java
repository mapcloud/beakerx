/*
 *  Copyright 2014 TWO SIGMA OPEN SOURCE, LLC
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.twosigma.beaker.widgets;

import com.twosigma.beaker.jupyter.Comm;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

public abstract class Widget {

  public abstract Comm getComm();

  public void sendUpdate(String propertyName, Object value) {
    HashMap<String, Serializable> content = new HashMap<>();
    content.put("method", "update");
    HashMap<Object, Object> state = new HashMap<>();
    state.put(propertyName, value);
    content.put("state", state);
    getComm().setData(content);
    try {
      getComm().send();
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException(e);
    }
  }
}