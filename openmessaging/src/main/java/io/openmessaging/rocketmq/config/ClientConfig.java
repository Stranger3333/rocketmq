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
package io.openmessaging.rocketmq.config;

import io.openmessaging.OMSBuiltinKeys;
import io.openmessaging.rocketmq.domain.NonStandardKeys;
// added
import org.apache.rocketmq.logging.InternalLogger;
import org.apache.rocketmq.logging.InternalLoggerFactory;
import org.apache.rocketmq.common.constant.LoggerName;

public class ClientConfig implements OMSBuiltinKeys, NonStandardKeys {
    // added
    private static final InternalLogger log = InternalLoggerFactory.getLogger(LoggerName.COMMON_LOGGER_NAME);

    private String driverImpl;
    private String accessPoints;
    private String namespace;
    private String producerId;
    private String consumerId;
    private int operationTimeout = 5000;
    private String region;
    private String routingSource;
    private String routingDestination;
    private String routingExpression;
    private String rmqConsumerGroup;
    private String rmqProducerGroup = "__OMS_PRODUCER_DEFAULT_GROUP";
    private int rmqMaxRedeliveryTimes = 16;
    private int rmqMessageConsumeTimeout = 15; //In minutes
    private int rmqMaxConsumeThreadNums = 64;
    private int rmqMinConsumeThreadNums = 20;
    private String rmqMessageDestination;
    private int rmqPullMessageBatchNums = 32;
    private int rmqPullMessageCacheCapacity = 1000;

    // ctest addition 
    private String getStackTrace() {
        String stacktrace = " ";
        for (StackTraceElement element : Thread.currentThread().getStackTrace()) {
            stacktrace = stacktrace.concat(element.getClassName() + "\t");
        }
        return stacktrace;
    }

    public String getDriverImpl() {
        log.warn("[CTEST][GET-PARAM] " + "driverImpl"); //CTEST

        return driverImpl;
    }

    public void setDriverImpl(final String driverImpl) {
        log.warn("[CTEST][SET-PARAM] " + "driverImpl" + getStackTrace()); //CTEST

        this.driverImpl = driverImpl;
    }

    public String getAccessPoints() {
        log.warn("[CTEST][GET-PARAM] " + "accessPoints"); //CTEST

        return accessPoints;
    }

    public void setAccessPoints(final String accessPoints) {
        log.warn("[CTEST][SET-PARAM] " + "accessPoints" + getStackTrace()); //CTEST

        this.accessPoints = accessPoints;
    }

    public String getNamespace() {
        log.warn("[CTEST][GET-PARAM] " + "namespace"); //CTEST

        return namespace;
    }

    public void setNamespace(final String namespace) {
        log.warn("[CTEST][SET-PARAM] " + "namespace" + getStackTrace()); //CTEST

        this.namespace = namespace;
    }

    public String getProducerId() {
        log.warn("[CTEST][GET-PARAM] " + "producerId"); //CTEST

        return producerId;
    }

    public void setProducerId(final String producerId) {
        this.producerId = producerId;
    }

    public String getConsumerId() {
        log.warn("[CTEST][GET-PARAM] " + "consumerId"); //CTEST

        return consumerId;
    }

    public void setConsumerId(final String consumerId) {
        log.warn("[CTEST][SET-PARAM] " + "consumerId" + getStackTrace()); //CTEST

        this.consumerId = consumerId;
    }

    public int getOperationTimeout() {
        log.warn("[CTEST][GET-PARAM] " + "operationTimeout"); //CTEST

        return operationTimeout;
    }

    public void setOperationTimeout(final int operationTimeout) {
        log.warn("[CTEST][SET-PARAM] " + "operationTimeout" + getStackTrace()); //CTEST

        this.operationTimeout = operationTimeout;
    }

    public String getRoutingSource() {
        log.warn("[CTEST][GET-PARAM] " + "routingSource"); //CTEST

        return routingSource;
    }

    public void setRoutingSource(final String routingSource) {
        log.warn("[CTEST][SET-PARAM] " + "routingSource" + getStackTrace()); //CTEST

        this.routingSource = routingSource;
    }

    public String getRmqConsumerGroup() {
        log.warn("[CTEST][GET-PARAM] " + "rmqConsumerGroup"); //CTEST

        return rmqConsumerGroup;
    }

    public void setRmqConsumerGroup(final String rmqConsumerGroup) {
        log.warn("[CTEST][SET-PARAM] " + "rmqConsumerGroup" + getStackTrace()); //CTEST

        this.rmqConsumerGroup = rmqConsumerGroup;
    }

    public String getRmqProducerGroup() {
        log.warn("[CTEST][GET-PARAM] " + "rmqProducerGroup"); //CTEST

        return rmqProducerGroup;
    }

    public void setRmqProducerGroup(final String rmqProducerGroup) {
        log.warn("[CTEST][SET-PARAM] " + "rmqProducerGroup" + getStackTrace()); //CTEST

        this.rmqProducerGroup = rmqProducerGroup;
    }

    public int getRmqMaxRedeliveryTimes() {
        log.warn("[CTEST][GET-PARAM] " + "rmqMaxRedeliveryTimes"); //CTEST

        return rmqMaxRedeliveryTimes;
    }

    public void setRmqMaxRedeliveryTimes(final int rmqMaxRedeliveryTimes) {
        log.warn("[CTEST][SET-PARAM] " + "rmqMaxRedeliveryTimes" + getStackTrace()); //CTEST

        this.rmqMaxRedeliveryTimes = rmqMaxRedeliveryTimes;
    }

    public int getRmqMessageConsumeTimeout() {
        log.warn("[CTEST][GET-PARAM] " + "rmqMessageConsumeTimeout"); //CTEST

        return rmqMessageConsumeTimeout;
    }

    public void setRmqMessageConsumeTimeout(final int rmqMessageConsumeTimeout) {
        log.warn("[CTEST][SET-PARAM] " + "rmqMessageConsumeTimeout" + getStackTrace()); //CTEST

        this.rmqMessageConsumeTimeout = rmqMessageConsumeTimeout;
    }

    public int getRmqMaxConsumeThreadNums() {
        log.warn("[CTEST][GET-PARAM] " + "rmqMaxConsumeThreadNums"); //CTEST

        return rmqMaxConsumeThreadNums;
    }

    public void setRmqMaxConsumeThreadNums(final int rmqMaxConsumeThreadNums) {
        log.warn("[CTEST][SET-PARAM] " + "rmqMaxConsumeThreadNums" + getStackTrace()); //CTEST

        this.rmqMaxConsumeThreadNums = rmqMaxConsumeThreadNums;
    }

    public int getRmqMinConsumeThreadNums() {
        log.warn("[CTEST][GET-PARAM] " + "rmqMinConsumeThreadNums"); //CTEST

        return rmqMinConsumeThreadNums;
    }

    public void setRmqMinConsumeThreadNums(final int rmqMinConsumeThreadNums) {
        log.warn("[CTEST][SET-PARAM] " + "rmqMinConsumeThreadNums" + getStackTrace()); //CTEST

        this.rmqMinConsumeThreadNums = rmqMinConsumeThreadNums;
    }

    public String getRmqMessageDestination() {
        log.warn("[CTEST][GET-PARAM] " + "rmqMessageDestination"); //CTEST

        return rmqMessageDestination;
    }

    public void setRmqMessageDestination(final String rmqMessageDestination) {
        log.warn("[CTEST][SET-PARAM] " + "rmqMessageDestination" + getStackTrace()); //CTEST

        this.rmqMessageDestination = rmqMessageDestination;
    }

    public int getRmqPullMessageBatchNums() {
        log.warn("[CTEST][GET-PARAM] " + "rmqPullMessageBatchNums"); //CTEST

        return rmqPullMessageBatchNums;
    }

    public void setRmqPullMessageBatchNums(final int rmqPullMessageBatchNums) {
        log.warn("[CTEST][SET-PARAM] " + "rmqPullMessageBatchNums" + getStackTrace()); //CTEST

        this.rmqPullMessageBatchNums = rmqPullMessageBatchNums;
    }

    public int getRmqPullMessageCacheCapacity() {
        log.warn("[CTEST][GET-PARAM] " + "rmqPullMessageCacheCapacity"); //CTEST

        return rmqPullMessageCacheCapacity;
    }

    public void setRmqPullMessageCacheCapacity(final int rmqPullMessageCacheCapacity) {
        log.warn("[CTEST][SET-PARAM] " + "rmqPullMessageCacheCapacity" + getStackTrace()); //CTEST

        this.rmqPullMessageCacheCapacity = rmqPullMessageCacheCapacity;
    }

    public String getRegion() {
        log.warn("[CTEST][GET-PARAM] " + "region"); //CTEST

        return region;
    }

    public void setRegion(String region) {
        log.warn("[CTEST][SET-PARAM] " + "region" + getStackTrace()); //CTEST

        this.region = region;
    }

    public String getRoutingDestination() {
        log.warn("[CTEST][GET-PARAM] " + "routingDestination"); //CTEST

        return routingDestination;
    }

    public void setRoutingDestination(String routingDestination) {
        log.warn("[CTEST][SET-PARAM] " + "routingDestination" + getStackTrace()); //CTEST

        this.routingDestination = routingDestination;
    }

    public String getRoutingExpression() {
        log.warn("[CTEST][GET-PARAM] " + "routingExpression"); //CTEST

        return routingExpression;
    }

    public void setRoutingExpression(String routingExpression) {
        log.warn("[CTEST][SET-PARAM] " + "routingExpression" + getStackTrace()); //CTEST

        this.routingExpression = routingExpression;
    }
}
