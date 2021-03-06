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
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.ballerinalang.test.types.service;

import org.ballerinalang.model.types.BServiceType;
import org.ballerinalang.model.values.BMap;
import org.ballerinalang.model.values.BValue;
import org.ballerinalang.test.services.testutils.HTTPTestRequest;
import org.ballerinalang.test.services.testutils.MessageUtils;
import org.ballerinalang.test.services.testutils.Services;
import org.ballerinalang.test.util.BCompileUtil;
import org.ballerinalang.test.util.BRunUtil;
import org.ballerinalang.test.util.CompileResult;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.wso2.transport.http.netty.message.HttpCarbonMessage;

/**
 * Test cases for Service Type.
 *
 * @since 0.965.0
 */
@Test
public class ServiceTypeTest {
    private CompileResult compileResult;

    private static final int MOCK_ENDPOINT_PORT = 9090;

    @BeforeClass
    public void setup() {
        compileResult = BCompileUtil.compile("test-src/types/service/service_test.bal");
    }

    @Test
    public void testServiceType() {
        BValue[] returns = BRunUtil.invoke(compileResult, "testServiceType");
        Assert.assertTrue(returns[0] instanceof BMap);
        Assert.assertTrue(returns[0].getType() instanceof BServiceType);
        Assert.assertTrue(returns[0].getType().getName().startsWith("HelloWorld"));
    }

    @Test(description = "Test resource returning error")
    public void testAccessingChangedGlobalVarInAnotherResourceInAnotherService() {
        HTTPTestRequest cMsg = MessageUtils.generateHTTPMessage("/hello/returnError", "GET");
        HttpCarbonMessage response = Services.invoke(MOCK_ENDPOINT_PORT, cMsg);
        Assert.assertNotNull(response);
        Assert.assertEquals(response.getHttpStatusCode().intValue(), 500);
    }
}
