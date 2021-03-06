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

import com.sun.jdi.Field;
import com.sun.jdi.ObjectReference;
import com.sun.jdi.Value;
import org.ballerinalang.debugadapter.variable.BCompoundVariable;
import org.ballerinalang.debugadapter.variable.BVariableType;
import org.ballerinalang.debugadapter.variable.VariableContext;
import org.eclipse.lsp4j.debug.Variable;

import java.util.HashMap;
import java.util.Map;

import static org.ballerinalang.debugadapter.variable.VariableUtils.UNKNOWN_VALUE;
import static org.ballerinalang.debugadapter.variable.VariableUtils.getBType;

/**
 * Ballerina record variable type.
 */
public class BRecord extends BCompoundVariable {

    public BRecord(VariableContext context, Value value, Variable dapVariable) {
        super(context, BVariableType.RECORD, value, dapVariable);
    }

    @Override
    public String computeValue() {
        try {
            return isAnonymous() ? "anonymous" : getBType(jvmValue);
        } catch (Exception e) {
            return UNKNOWN_VALUE;
        }
    }

    @Override
    public Map<String, Value> computeChildVariables() {
        try {
            if (!(jvmValue instanceof ObjectReference)) {
                return new HashMap<>();
            }
            ObjectReference jvmValueRef = (ObjectReference) jvmValue;
            Map<Field, Value> fieldValueMap = jvmValueRef.getValues(jvmValueRef.referenceType().allFields());

            // Uses ballerina record type name/anonymous record identifier to filter ballerina record fields from its
            // jvm reference.
            String balRecordFiledIdentifier = isAnonymous() ? "$value$" : this.computeValue() + ".";
            Map<String, Value> recordFields = new HashMap<>();
            fieldValueMap.forEach((field, value) -> {
                if (field.toString().contains(balRecordFiledIdentifier)) {
                    recordFields.put(field.name(), value);
                }
            });
            return recordFields;
        } catch (Exception ignored) {
            return new HashMap<>();
        }
    }

    /**
     * Returns whether this record instance is anonymous.
     *
     * @return whether the given record instance is anonymous.
     */
    private boolean isAnonymous() {
        String bType = getBType(jvmValue);
        return bType.startsWith("$");
    }
}
