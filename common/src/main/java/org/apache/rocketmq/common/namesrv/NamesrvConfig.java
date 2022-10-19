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

/**
 * $Id: NamesrvConfig.java 1839 2013-05-16 02:12:02Z vintagewang@apache.org $
 */
package org.apache.rocketmq.common.namesrv;

import java.io.File;
import org.apache.rocketmq.common.MixAll;
import org.apache.rocketmq.logging.InternalLogger;
import org.apache.rocketmq.logging.InternalLoggerFactory;
import org.apache.rocketmq.common.constant.LoggerName;

public class NamesrvConfig {
    private static final InternalLogger log = InternalLoggerFactory.getLogger(LoggerName.COMMON_LOGGER_NAME);

    private String rocketmqHome = System.getProperty(MixAll.ROCKETMQ_HOME_PROPERTY, System.getenv(MixAll.ROCKETMQ_HOME_ENV));
    private String kvConfigPath = System.getProperty("user.home") + File.separator + "namesrv" + File.separator + "kvConfig.json";
    private String configStorePath = System.getProperty("user.home") + File.separator + "namesrv" + File.separator + "namesrv.properties";
    private String productEnvName = "center";
    private boolean clusterTest = false;
    private boolean orderMessageEnable = false;
    private boolean returnOrderTopicConfigToBroker = true;

    /**
     * Indicates the nums of thread to handle client requests, like GET_ROUTEINTO_BY_TOPIC.
     */
    private int clientRequestThreadPoolNums = 8;
    /**
     * Indicates the nums of thread to handle broker or operation requests, like REGISTER_BROKER.
     */
    private int defaultThreadPoolNums = 16;
    /**
     * Indicates the capacity of queue to hold client requests.
     */
    private int clientRequestThreadPoolQueueCapacity = 50000;
    /**
     * Indicates the capacity of queue to hold broker or operation requests.
     */
    private int defaultThreadPoolQueueCapacity = 10000;
    /**
     * Interval of periodic scanning for non-active broker;
     */
    private long scanNotActiveBrokerInterval = 5 * 1000;

    private int unRegisterBrokerQueueCapacity = 3000;

    /**
     * Support acting master or not.
     *
     * The slave can be an acting master when master node is down to support following operations:
     * 1. support lock/unlock message queue operation.
     * 2. support searchOffset, query maxOffset/minOffset operation.
     * 3. support query earliest msg store time.
     */
    private boolean supportActingMaster = false;

    private volatile boolean enableAllTopicList = true;


    private volatile boolean enableTopicList = true;

    private volatile boolean notifyMinBrokerIdChanged = false;

    /**
     * Is startup the controller in this name-srv
     */
    private boolean enableControllerInNamesrv = false;

    private String getStackTrace() {
        String stacktrace = " ";
        for (StackTraceElement element : Thread.currentThread().getStackTrace()) {
          stacktrace = stacktrace.concat(element.getClassName() + "\t");
        }
        return stacktrace;
    }
    
    public boolean isOrderMessageEnable() {
        log.warn("[CTEST][GET-PARAM] " + "orderMessageEnable"); //CTEST

        return orderMessageEnable;
    }

    public void setOrderMessageEnable(boolean orderMessageEnable) {
        log.warn("[CTEST][SET-PARAM] " + "orderMessageEnable" + getStackTrace()); //CTEST

        this.orderMessageEnable = orderMessageEnable;
    }

    public String getRocketmqHome() {
        log.warn("[CTEST][GET-PARAM] " + "rocketmqHome"); //CTEST

        return rocketmqHome;
    }

    public void setRocketmqHome(String rocketmqHome) {
        log.warn("[CTEST][SET-PARAM] " + "rocketmqHome" + getStackTrace()); //CTEST

        this.rocketmqHome = rocketmqHome;
    }

    public String getKvConfigPath() {
        log.warn("[CTEST][GET-PARAM] " + "kvConfigPath"); //CTEST

        return kvConfigPath;
    }

    public void setKvConfigPath(String kvConfigPath) {
        log.warn("[CTEST][SET-PARAM] " + "kvConfigPath" + getStackTrace()); //CTEST

        this.kvConfigPath = kvConfigPath;
    }

    public String getProductEnvName() {
        log.warn("[CTEST][GET-PARAM] " + "productEnvName"); //CTEST

        return productEnvName;
    }

    public void setProductEnvName(String productEnvName) {
        log.warn("[CTEST][SET-PARAM] " + "productEnvName" + getStackTrace()); //CTEST
        this.productEnvName = productEnvName;
    }

    public boolean isClusterTest() {
        log.warn("[CTEST][GET-PARAM] " + "clusterTest"); //CTEST

        return clusterTest;
    }

    public void setClusterTest(boolean clusterTest) {
        log.warn("[CTEST][SET-PARAM] " + "clusterTest" + getStackTrace()); //CTEST

        this.clusterTest = clusterTest;
    }

    public String getConfigStorePath() {
        log.warn("[CTEST][GET-PARAM] " + "configStorePath"); //CTEST

        return configStorePath;
    }

    public void setConfigStorePath(final String configStorePath) {
        log.warn("[CTEST][SET-PARAM] " + "configStorePath" + getStackTrace()); //CTEST

        this.configStorePath = configStorePath;
    }

    public boolean isReturnOrderTopicConfigToBroker() {
        log.warn("[CTEST][GET-PARAM] " + "returnOrderTopicConfigToBroker"); //CTEST

        return returnOrderTopicConfigToBroker;
    }

    public void setReturnOrderTopicConfigToBroker(boolean returnOrderTopicConfigToBroker) {
        log.warn("[CTEST][SET-PARAM] " + "returnOrderTopicConfigToBroker" + getStackTrace()); //CTEST

        this.returnOrderTopicConfigToBroker = returnOrderTopicConfigToBroker;
    }

    public int getClientRequestThreadPoolNums() {
        log.warn("[CTEST][GET-PARAM] " + "clientRequestThreadPoolNums"); //CTEST

        return clientRequestThreadPoolNums;
    }

    public void setClientRequestThreadPoolNums(final int clientRequestThreadPoolNums) {
        log.warn("[CTEST][SET-PARAM] " + "clientRequestThreadPoolNums" + getStackTrace()); //CTEST

        this.clientRequestThreadPoolNums = clientRequestThreadPoolNums;
    }

    public int getDefaultThreadPoolNums() {
        log.warn("[CTEST][GET-PARAM] " + "defaultThreadPoolNums"); //CTEST

        return defaultThreadPoolNums;
    }

    public void setDefaultThreadPoolNums(final int defaultThreadPoolNums) {
        log.warn("[CTEST][SET-PARAM] " + "defaultThreadPoolNums" + getStackTrace()); //CTEST

        this.defaultThreadPoolNums = defaultThreadPoolNums;
    }

    public int getClientRequestThreadPoolQueueCapacity() {
        log.warn("[CTEST][GET-PARAM] " + "clientRequestThreadPoolQueueCapacity"); //CTEST

        return clientRequestThreadPoolQueueCapacity;
    }

    public void setClientRequestThreadPoolQueueCapacity(final int clientRequestThreadPoolQueueCapacity) {
        log.warn("[CTEST][SET-PARAM] " + "clientRequestThreadPoolQueueCapacity" + getStackTrace()); //CTEST

        this.clientRequestThreadPoolQueueCapacity = clientRequestThreadPoolQueueCapacity;
    }

    public int getDefaultThreadPoolQueueCapacity() {
        log.warn("[CTEST][GET-PARAM] " + "defaultThreadPoolQueueCapacity"); //CTEST

        return defaultThreadPoolQueueCapacity;
    }

    public void setDefaultThreadPoolQueueCapacity(final int defaultThreadPoolQueueCapacity) {
        log.warn("[CTEST][SET-PARAM] " + "defaultThreadPoolQueueCapacity" + getStackTrace()); //CTEST

        this.defaultThreadPoolQueueCapacity = defaultThreadPoolQueueCapacity;
    }

    public long getScanNotActiveBrokerInterval() {
        log.warn("[CTEST][GET-PARAM] " + "scanNotActiveBrokerInterval"); //CTEST

        return scanNotActiveBrokerInterval;
    }

    public void setScanNotActiveBrokerInterval(long scanNotActiveBrokerInterval) {
        log.warn("[CTEST][SET-PARAM] " + "scanNotActiveBrokerInterval" + getStackTrace()); //CTEST

        this.scanNotActiveBrokerInterval = scanNotActiveBrokerInterval;
    }

    public int getUnRegisterBrokerQueueCapacity() {
        log.warn("[CTEST][GET-PARAM] " + "unRegisterBrokerQueueCapacity"); //CTEST

        return unRegisterBrokerQueueCapacity;
    }

    public void setUnRegisterBrokerQueueCapacity(final int unRegisterBrokerQueueCapacity) {
        log.warn("[CTEST][SET-PARAM] " + "unRegisterBrokerQueueCapacity" + getStackTrace()); //CTEST

        this.unRegisterBrokerQueueCapacity = unRegisterBrokerQueueCapacity;
    }

    public boolean isSupportActingMaster() {
        log.warn("[CTEST][GET-PARAM] " + "supportActingMaster"); //CTEST

        return supportActingMaster;
    }

    public void setSupportActingMaster(final boolean supportActingMaster) {
        log.warn("[CTEST][SET-PARAM] " + "supportActingMaster" + getStackTrace()); //CTEST

        this.supportActingMaster = supportActingMaster;
    }

    public boolean isEnableAllTopicList() {
        log.warn("[CTEST][GET-PARAM] " + "enableAllTopicList"); //CTEST

        return enableAllTopicList;
    }

    public void setEnableAllTopicList(boolean enableAllTopicList) {
        log.warn("[CTEST][SET-PARAM] " + "enableAllTopicList" + getStackTrace()); //CTEST

        this.enableAllTopicList = enableAllTopicList;
    }

    public boolean isEnableTopicList() {
        log.warn("[CTEST][GET-PARAM] " + "enableTopicList"); //CTEST

        return enableTopicList;
    }

    public void setEnableTopicList(boolean enableTopicList) {
        log.warn("[CTEST][SET-PARAM] " + "enableTopicList" + getStackTrace()); //CTEST

        this.enableTopicList = enableTopicList;
    }

    public boolean isNotifyMinBrokerIdChanged() {
        log.warn("[CTEST][GET-PARAM] " + "notifyMinBrokerIdChanged"); //CTEST

        return notifyMinBrokerIdChanged;
    }

    public void setNotifyMinBrokerIdChanged(boolean notifyMinBrokerIdChanged) {
        log.warn("[CTEST][SET-PARAM] " + "notifyMinBrokerIdChanged" + getStackTrace()); //CTEST

        this.notifyMinBrokerIdChanged = notifyMinBrokerIdChanged;
    }

    public boolean isEnableControllerInNamesrv() {
        log.warn("[CTEST][GET-PARAM] " + "enableControllerInNamesrv"); //CTEST

        return enableControllerInNamesrv;
    }

    public void setEnableControllerInNamesrv(boolean enableControllerInNamesrv) {
        log.warn("[CTEST][SET-PARAM] " + "enableControllerInNamesrv" + getStackTrace()); //CTEST

        this.enableControllerInNamesrv = enableControllerInNamesrv;
    }
}
