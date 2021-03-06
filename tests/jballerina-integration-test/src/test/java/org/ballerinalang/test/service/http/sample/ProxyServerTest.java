/*
 *  Copyright (c) 2018, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  WSO2 Inc. licenses this file to you under the Apache License,
 *  Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */

package org.ballerinalang.test.service.http.sample;

import org.ballerinalang.test.context.BMainInstance;
import org.ballerinalang.test.context.LogLeecher;
import org.ballerinalang.test.service.http.HttpBaseTest;
import org.testng.annotations.Test;

import java.io.File;

/**
 * A test case for http proxy scenario.
 */
@Test(groups = "http-test")
public class ProxyServerTest extends HttpBaseTest {

    private BMainInstance ballerinaClient;

    @Test(description = "Test proxy server")
    public void testHttpProxy() throws Exception {
        String serverResponse = "Backend server sent response";

        String balFile = new File("src" + File.separator + "test" + File.separator + "resources"
                + File.separator + "proxy" + File.separator + "proxyClient.bal").getAbsolutePath();
    
        ballerinaClient = new BMainInstance(balServer);
        LogLeecher clientLeecher = new LogLeecher(serverResponse);
        ballerinaClient.runMain(balFile, new String[]{}, new String[]{}, null, new String[]{},
                new LogLeecher[]{clientLeecher});
        clientLeecher.waitForText(20000);
    }

}

