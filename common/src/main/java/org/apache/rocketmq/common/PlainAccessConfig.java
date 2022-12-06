/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.rocketmq.common;
// ADDED
import org.apache.rocketmq.logging.InternalLogger;
import org.apache.rocketmq.logging.InternalLoggerFactory;
import org.apache.rocketmq.common.constant.LoggerName;

import java.util.List;

public class PlainAccessConfig {
    // added
    private static final InternalLogger log = InternalLoggerFactory.getLogger(LoggerName.COMMON_LOGGER_NAME);

    private String accessKey;

    private String secretKey;

    private String whiteRemoteAddress;

    private boolean admin;

    private String defaultTopicPerm;

    private String defaultGroupPerm;

    private List<String> topicPerms;

    private List<String> groupPerms;

    // ctest addition 
    private String getStackTrace() {
        String stacktrace = " ";
        for (StackTraceElement element : Thread.currentThread().getStackTrace()) {
            stacktrace = stacktrace.concat(element.getClassName() + "\t");
        }
        return stacktrace;
    }

    public String getAccessKey() {
        log.warn("[CTEST][GET-PARAM] " + "accessKey"); //CTEST

        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        log.warn("[CTEST][SET-PARAM] " + "accessKey" + getStackTrace()); //CTEST

        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        log.warn("[CTEST][GET-PARAM] " + "secretKey"); //CTEST

        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        log.warn("[CTEST][SET-PARAM] " + "secretKey" + getStackTrace()); //CTEST

        this.secretKey = secretKey;
    }

    public String getWhiteRemoteAddress() {
        log.warn("[CTEST][GET-PARAM] " + "whiteRemoteAddress"); //CTEST

        return whiteRemoteAddress;
    }

    public void setWhiteRemoteAddress(String whiteRemoteAddress) {
        log.warn("[CTEST][SET-PARAM] " + "whiteRemoteAddress" + getStackTrace()); //CTEST

        this.whiteRemoteAddress = whiteRemoteAddress;
    }

    public boolean isAdmin() {
        log.warn("[CTEST][GET-PARAM] " + "admin" + "| admin value:" + admin + "|"); //CTEST

        return admin;
    }

    public void setAdmin(boolean admin) {
        log.warn("[CTEST][SET-PARAM] " + "admin" + getStackTrace()); //CTEST

        this.admin = admin;
    }

    public String getDefaultTopicPerm() {
        log.warn("[CTEST][GET-PARAM] " + "defaultTopicPerm"); //CTEST

        return defaultTopicPerm;
    }

    public void setDefaultTopicPerm(String defaultTopicPerm) {
        log.warn("[CTEST][SET-PARAM] " + "defaultTopicPerm" + getStackTrace()); //CTEST

        this.defaultTopicPerm = defaultTopicPerm;
    }

    public String getDefaultGroupPerm() {
        log.warn("[CTEST][GET-PARAM] " + "defaultGroupPerm"); //CTEST
        log.warn("default group perm is:" + defaultGroupPerm);
        return defaultGroupPerm;
    }

    public void setDefaultGroupPerm(String defaultGroupPerm) {
        log.warn("[CTEST][SET-PARAM] " + "defaultGroupPerm" + getStackTrace()); //CTEST
        this.defaultGroupPerm = defaultGroupPerm;
        
    }

    public List<String> getTopicPerms() {
        log.warn("[CTEST][GET-PARAM] " + "topicPerms"); //CTEST
        return topicPerms;
    }

    public void setTopicPerms(List<String> topicPerms) {
        log.warn("[CTEST][SET-PARAM] " + "topicPerms" + getStackTrace()); //CTEST

        this.topicPerms = topicPerms;
    }

    public List<String> getGroupPerms() {
        log.warn("[CTEST][GET-PARAM] " + "groupPerms"); //CTEST

        return groupPerms;
    }

    public void setGroupPerms(List<String> groupPerms) {
        log.warn("[CTEST][SET-PARAM] " + "groupPerms" + getStackTrace()); //CTEST

        this.groupPerms = groupPerms;
    }

    @Override
    public String toString() {
        return "PlainAccessConfig{" +
            "accessKey='" + accessKey + '\'' +
            ", whiteRemoteAddress='" + whiteRemoteAddress + '\'' +
            ", admin=" + admin +
            ", defaultTopicPerm='" + defaultTopicPerm + '\'' +
            ", defaultGroupPerm='" + defaultGroupPerm + '\'' +
            ", topicPerms=" + topicPerms +
            ", groupPerms=" + groupPerms +
            '}';
    }
}
