/**
 * Licensed to jclouds, Inc. (jclouds) under one or more
 * contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  jclouds licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.jclouds.cloudfiles.blobstore.integration;

import org.jclouds.cloudfiles.CloudFilesApiMetadata;
import org.jclouds.cloudfiles.CloudFilesClient;
import org.jclouds.openstack.swift.blobstore.integration.SwiftBlobLiveTest;
import org.testng.annotations.Test;

import java.util.UUID;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

/**
 * @author Adrian Cole
 */
@Test(groups = {"live"})
public class CloudFilesBlobLiveTest extends SwiftBlobLiveTest {
   public CloudFilesBlobLiveTest() {
      provider = "cloudfiles";
   }

   public void testGetAndSetTemporaryUrlKey() {
      CloudFilesClient client = view.unwrap(CloudFilesApiMetadata.CONTEXT_TOKEN).getApi();

      String currentSecretKey = client.getTemporaryUrlKey();
      assertNotNull(currentSecretKey);
      try {
         String testKey = UUID.randomUUID().toString();
         client.setTemporaryUrlKey(testKey);
         assertEquals(client.getTemporaryUrlKey(), testKey);

      } finally {
         client.setTemporaryUrlKey(currentSecretKey);
      }
   }
}
