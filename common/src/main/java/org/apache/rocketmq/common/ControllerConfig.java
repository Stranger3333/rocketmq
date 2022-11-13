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
import java.io.File;

public class ControllerConfig {
    // added
    private static final InternalLogger log = InternalLoggerFactory.getLogger(LoggerName.COMMON_LOGGER_NAME);

    private String rocketmqHome = System.getProperty(MixAll.ROCKETMQ_HOME_PROPERTY, System.getenv(MixAll.ROCKETMQ_HOME_ENV));
    private String configStorePath = System.getProperty("user.home") + File.separator + "controller" + File.separator + "controller.properties";

    /**
     * Interval of periodic scanning for non-active broker;
     */
    private long scanNotActiveBrokerInterval = 5 * 1000;

    /**
     * Indicates the nums of thread to handle broker or operation requests, like REGISTER_BROKER.
     */
    private int controllerThreadPoolNums = 16;

    /**
     * Indicates the capacity of queue to hold client requests.
     */
    private int controllerRequestThreadPoolQueueCapacity = 50000;

    private String controllerDLegerGroup;
    private String controllerDLegerPeers;
    private String controllerDLegerSelfId;
    private int mappedFileSize = 1024 * 1024 * 1024;
    private String controllerStorePath = System.getProperty("user.home") + File.separator + "DledgerController";

    /**
     * Whether the controller can elect a master which is not in the syncStateSet.
     */
    private boolean enableElectUncleanMaster = false;

    /**
     * Whether process read event
     */
    private boolean isProcessReadEvent = false;

    /**
     * Whether notify broker when its role changed
     */
    private volatile boolean notifyBrokerRoleChanged = true;
    // ctest addition 
    private String getStackTrace() {
        String stacktrace = " ";
        for (StackTraceElement element : Thread.currentThread().getStackTrace()) {
            stacktrace = stacktrace.concat(element.getClassName() + "\t");
        }
        return stacktrace;
    }
    
    public String getRocketmqHome() {
        log.warn("[CTEST][GET-PARAM] " + "rocketmqHome"); //CTEST
        return rocketmqHome;
    }

    public void setRocketmqHome(String rocketmqHome) {
        log.warn("[CTEST][SET-PARAM] " + "rocketmqHome" + getStackTrace()); //CTEST

        this.rocketmqHome = rocketmqHome;
    }

    public String getConfigStorePath() {
        log.warn("[CTEST][GET-PARAM] " + "configStorePath"); //CTEST
        return configStorePath;
    }

    public void setConfigStorePath(String configStorePath) {
        log.warn("[CTEST][SET-PARAM] " + "configStorePath" + getStackTrace()); //CTEST

        this.configStorePath = configStorePath;
    }

    public long getScanNotActiveBrokerInterval() {
        log.warn("[CTEST][GET-PARAM] " + "scanNotActiveBrokerInterval"); //CTEST
        return scanNotActiveBrokerInterval;
    }

    public void setScanNotActiveBrokerInterval(long scanNotActiveBrokerInterval) {
        log.warn("[CTEST][SET-PARAM] " + "scanNotActiveBrokerInterval" + getStackTrace()); //CTEST

        this.scanNotActiveBrokerInterval = scanNotActiveBrokerInterval;
    }

    public int getControllerThreadPoolNums() {
        log.warn("[CTEST][GET-PARAM] " + "controllerThreadPoolNums"); //CTEST
        return controllerThreadPoolNums;
    }

    public void setControllerThreadPoolNums(int controllerThreadPoolNums) {
        log.warn("[CTEST][SET-PARAM] " + "controllerThreadPoolNums" + getStackTrace()); //CTEST

        this.controllerThreadPoolNums = controllerThreadPoolNums;
    }

    public int getControllerRequestThreadPoolQueueCapacity() {
        log.warn("[CTEST][GET-PARAM] " + "controllerRequestThreadPoolQueueCapacity"); //CTEST
        return controllerRequestThreadPoolQueueCapacity;
    }

    public void setControllerRequestThreadPoolQueueCapacity(int controllerRequestThreadPoolQueueCapacity) {
        log.warn("[CTEST][SET-PARAM] " + "controllerRequestThreadPoolQueueCapacity" + getStackTrace()); //CTEST

        this.controllerRequestThreadPoolQueueCapacity = controllerRequestThreadPoolQueueCapacity;
    }

    public String getControllerDLegerGroup() {
        log.warn("[CTEST][GET-PARAM] " + "controllerDLegerGroup"); //CTEST
        return controllerDLegerGroup;
    }

    public void setControllerDLegerGroup(String controllerDLegerGroup) {
        log.warn("[CTEST][SET-PARAM] " + "controllerDLegerGroup" + getStackTrace()); //CTEST

        this.controllerDLegerGroup = controllerDLegerGroup;
    }

    public String getControllerDLegerPeers() {
        log.warn("[CTEST][GET-PARAM] " + "controllerDLegerPeers"); //CTEST

        return controllerDLegerPeers;
    }

    public void setControllerDLegerPeers(String controllerDLegerPeers) {
        log.warn("[CTEST][SET-PARAM] " + "controllerDLegerPeers" + getStackTrace()); //CTEST

        this.controllerDLegerPeers = controllerDLegerPeers;
    }

    public String getControllerDLegerSelfId() {
        log.warn("[CTEST][GET-PARAM] " + "controllerDLegerSelfId"); //CTEST

        return controllerDLegerSelfId;
    }

    public void setControllerDLegerSelfId(String controllerDLegerSelfId) {
        log.warn("[CTEST][SET-PARAM] " + "controllerDLegerSelfId" + getStackTrace()); //CTEST

        this.controllerDLegerSelfId = controllerDLegerSelfId;
    }

    public int getMappedFileSize() {
        log.warn("[CTEST][GET-PARAM] " + "mappedFileSize"); //CTEST

        return mappedFileSize;
    }

    public void setMappedFileSize(int mappedFileSize) {
        log.warn("[CTEST][SET-PARAM] " + "mappedFileSize" + getStackTrace()); //CTEST

        this.mappedFileSize = mappedFileSize;
    }

    public String getControllerStorePath() {
        log.warn("[CTEST][GET-PARAM] " + "controllerStorePath"); //CTEST

        return controllerStorePath;
    }

    public void setControllerStorePath(String controllerStorePath) {
        this.controllerStorePath = controllerStorePath;
    }

    public boolean isEnableElectUncleanMaster() {
        log.warn("[CTEST][GET-PARAM] " + "enableElectUncleanMaster"); //CTEST

        return enableElectUncleanMaster;
    }

    public void setEnableElectUncleanMaster(boolean enableElectUncleanMaster) {
        this.enableElectUncleanMaster = enableElectUncleanMaster;
    }

    public boolean isProcessReadEvent() {
        log.warn("[CTEST][GET-PARAM] " + "isProcessReadEvent"); //CTEST

        return isProcessReadEvent;
    }

    public void setProcessReadEvent(boolean processReadEvent) {
        log.warn("[CTEST][SET-PARAM] " + "isProcessReadEvent" + getStackTrace()); //CTEST

        isProcessReadEvent = processReadEvent;
    }

    public boolean isNotifyBrokerRoleChanged() {
        log.warn("[CTEST][GET-PARAM] " + "notifyBrokerRoleChanged"); //CTEST

        return notifyBrokerRoleChanged;
    }

    public void setNotifyBrokerRoleChanged(boolean notifyBrokerRoleChanged) {
        log.warn("[CTEST][SET-PARAM] " + "notifyBrokerRoleChanged" + getStackTrace()); //CTEST

        this.notifyBrokerRoleChanged = notifyBrokerRoleChanged;
    }
}
