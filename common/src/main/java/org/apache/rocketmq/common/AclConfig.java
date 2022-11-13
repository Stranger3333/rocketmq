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
// added
import org.apache.rocketmq.logging.InternalLogger;
import org.apache.rocketmq.logging.InternalLoggerFactory;
import org.apache.rocketmq.common.constant.LoggerName;
import java.util.List;

public class AclConfig {
    private static final InternalLogger log = InternalLoggerFactory.getLogger(LoggerName.COMMON_LOGGER_NAME);

    private List<String> globalWhiteAddrs;

    private List<PlainAccessConfig> plainAccessConfigs;

    // ctest addition 
    private String getStackTrace() {
        String stacktrace = " ";
        for (StackTraceElement element : Thread.currentThread().getStackTrace()) {
            stacktrace = stacktrace.concat(element.getClassName() + "\t");
        }
        return stacktrace;
    }

    public List<String> getGlobalWhiteAddrs() {
        log.warn("[CTEST][GET-PARAM] " + "globalWhiteAddrs"); //CTEST

        return globalWhiteAddrs;
    }

    public void setGlobalWhiteAddrs(List<String> globalWhiteAddrs) {
        log.warn("[CTEST][SET-PARAM] " + "globalWhiteAddrs" + getStackTrace()); //CTEST

        this.globalWhiteAddrs = globalWhiteAddrs;
    }

    public List<PlainAccessConfig> getPlainAccessConfigs() {
        log.warn("[CTEST][GET-PARAM] " + "plainAccessConfigs"); //CTEST

        return plainAccessConfigs;
    }

    public void setPlainAccessConfigs(List<PlainAccessConfig> plainAccessConfigs) {
        log.warn("[CTEST][SET-PARAM] " + "plainAccessConfigs" + getStackTrace()); //CTEST

        this.plainAccessConfigs = plainAccessConfigs;
    }

    @Override
    public String toString() {
        return "AclConfig{" +
            "globalWhiteAddrs=" + globalWhiteAddrs +
            ", plainAccessConfigs=" + plainAccessConfigs +
            '}';
    }
}
