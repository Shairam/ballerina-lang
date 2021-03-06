/*
 * Copyright (c) 2020, WSO2 Inc. (http://wso2.com) All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.ballerinalang.debugadapter.variable.types;

import com.sun.jdi.ArrayReference;
import com.sun.jdi.Value;
import org.ballerinalang.debugadapter.variable.BCompoundVariable;
import org.ballerinalang.debugadapter.variable.BVariableType;
import org.ballerinalang.debugadapter.variable.VariableContext;
import org.ballerinalang.debugadapter.variable.VariableUtils;
import org.eclipse.lsp4j.debug.Variable;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.ballerinalang.debugadapter.variable.VariableUtils.getBType;

/**
 * Ballerina xml variable type.
 */
public class BXmlItemAttributeMap extends BCompoundVariable {

    private static final String FIELD_MAP_DATA = "table";
    private static final String FIELD_MAP_KEY = "key";
    private static final String FIELD_MAP_VALUE = "value";

    public BXmlItemAttributeMap(VariableContext context, Value value, Variable dapVariable) {
        super(context, BVariableType.MAP, value, dapVariable);
    }

    @Override
    public String computeValue() {
        return getBType(jvmValue);
    }

    @Override
    public Map<String, Value> computeChildVariables() {
        Map<String, Value> childVarMap = new HashMap<>();
        try {
            Optional<Value> mapValues = VariableUtils.getFieldValue(jvmValue, FIELD_MAP_DATA);
            if (!mapValues.isPresent()) {
                return childVarMap;
            }
            for (Value map : ((ArrayReference) mapValues.get()).getValues()) {
                if (map != null) {
                    Optional<Value> mapKey = VariableUtils.getFieldValue(map, FIELD_MAP_KEY);
                    Optional<Value> mapValue = VariableUtils.getFieldValue(map, FIELD_MAP_VALUE);
                    if (mapKey.isPresent() && mapValue.isPresent()) {
                        childVarMap.put(VariableUtils.getStringFrom(mapKey.get()), mapValue.get());
                    }
                }
            }
            return childVarMap;
        } catch (Exception ignored) {
            return childVarMap;
        }
    }
}
