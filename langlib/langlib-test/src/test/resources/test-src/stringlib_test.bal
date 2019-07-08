// Copyright (c) 2019 WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
//
// WSO2 Inc. licenses this file to you under the Apache License,
// Version 2.0 (the "License"); you may not use this file except
// in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing,
// software distributed under the License is distributed on an
// "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
// KIND, either express or implied.  See the License for the
// specific language governing permissions and limitations
// under the License.

import ballerina/'lang\.string as strings;

function testToLower() returns string {
    string str = "Hello Ballerina!";
    return str.toLowerAscii();
}

function testLength() returns int {
    return "Hello Ballerina!".length();
}

function testSubString() returns string {
    return "Hello Ballerina!".substring(6, 9);
}

//function testIterator() returns string[] {
//    string str = "Foo Bar";
//
//    abstract object {
//         public function next() returns record {| string value; |}?;
//    } itr = str.iterator();
//
//    string[] chars = [];
//    int i = 0;
//    record {| string value; |}|() elem = itr.next();
//
//    while (elem is record {| string value; |}) {
//        chars[i] = elem.value;
//        elem = itr.next();
//        i += 1;
//    }
//
//    return chars;
//}

function testConcat() returns string {
    return strings:concat("Hello ", "from ", "Ballerina");
}