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

import org.apache.rocketmq.common.annotation.ImportantField;
import org.apache.rocketmq.common.constant.LoggerName;
import org.apache.rocketmq.common.constant.PermName;
import org.apache.rocketmq.common.message.MessageRequestMode;
import org.apache.rocketmq.common.topic.TopicValidator;
import org.apache.rocketmq.logging.InternalLogger;
import org.apache.rocketmq.logging.InternalLoggerFactory;
import org.apache.rocketmq.remoting.common.RemotingUtil;

public class BrokerConfig extends BrokerIdentity {
    private static final InternalLogger log = InternalLoggerFactory.getLogger(LoggerName.COMMON_LOGGER_NAME);

    private String brokerConfigPath = null;

    private String rocketmqHome = System.getProperty(MixAll.ROCKETMQ_HOME_PROPERTY, System.getenv(MixAll.ROCKETMQ_HOME_ENV));
    @ImportantField
    private String namesrvAddr = System.getProperty(MixAll.NAMESRV_ADDR_PROPERTY, System.getenv(MixAll.NAMESRV_ADDR_ENV));

    /**
     * Listen port for single broker
     */
    @ImportantField
    private int listenPort = 6888;

    @ImportantField
    private String brokerIP1 = RemotingUtil.getLocalAddress();
    private String brokerIP2 = RemotingUtil.getLocalAddress();

    private int brokerPermission = PermName.PERM_READ | PermName.PERM_WRITE;
    private int defaultTopicQueueNums = 8;
    @ImportantField
    private boolean autoCreateTopicEnable = true;

    private boolean clusterTopicEnable = true;

    private boolean brokerTopicEnable = true;
    @ImportantField
    private boolean autoCreateSubscriptionGroup = true;
    private String messageStorePlugIn = "";

    private static final int PROCESSOR_NUMBER = Runtime.getRuntime().availableProcessors();
    @ImportantField
    private String msgTraceTopicName = TopicValidator.RMQ_SYS_TRACE_TOPIC;
    @ImportantField
    private boolean traceTopicEnable = false;
    /**
     * thread numbers for send message thread pool.
     */
    private int sendMessageThreadPoolNums = Math.min(PROCESSOR_NUMBER, 4);
    private int putMessageFutureThreadPoolNums = Math.min(PROCESSOR_NUMBER, 4);
    private int pullMessageThreadPoolNums = 16 + PROCESSOR_NUMBER * 2;
    private int litePullMessageThreadPoolNums = 16 + PROCESSOR_NUMBER * 2;
    private int ackMessageThreadPoolNums = 3;
    private int processReplyMessageThreadPoolNums = 16 + PROCESSOR_NUMBER * 2;
    private int queryMessageThreadPoolNums = 8 + PROCESSOR_NUMBER;

    private int adminBrokerThreadPoolNums = 16;
    private int clientManageThreadPoolNums = 32;
    private int consumerManageThreadPoolNums = 32;
    private int loadBalanceProcessorThreadPoolNums = 32;
    private int heartbeatThreadPoolNums = Math.min(32, PROCESSOR_NUMBER);

    /**
     * Thread numbers for EndTransactionProcessor
     */
    private int endTransactionThreadPoolNums = Math.max(8 + PROCESSOR_NUMBER * 2,
            sendMessageThreadPoolNums * 4);

    private int flushConsumerOffsetInterval = 1000 * 5;

    private int flushConsumerOffsetHistoryInterval = 1000 * 60;

    @ImportantField
    private boolean rejectTransactionMessage = false;
    @ImportantField
    private boolean fetchNamesrvAddrByAddressServer = false;
    private int sendThreadPoolQueueCapacity = 10000;
    private int putThreadPoolQueueCapacity = 10000;
    private int pullThreadPoolQueueCapacity = 100000;
    private int litePullThreadPoolQueueCapacity = 100000;
    private int ackThreadPoolQueueCapacity = 100000;
    private int replyThreadPoolQueueCapacity = 10000;
    private int queryThreadPoolQueueCapacity = 20000;
    private int clientManagerThreadPoolQueueCapacity = 1000000;
    private int consumerManagerThreadPoolQueueCapacity = 1000000;
    private int heartbeatThreadPoolQueueCapacity = 50000;
    private int endTransactionPoolQueueCapacity = 100000;
    private int adminBrokerThreadPoolQueueCapacity = 10000;
    private int loadBalanceThreadPoolQueueCapacity = 100000;

    private int filterServerNums = 0;

    private boolean longPollingEnable = true;

    private long shortPollingTimeMills = 1000;

    private boolean notifyConsumerIdsChangedEnable = true;

    private boolean highSpeedMode = false;

    private int commercialBaseCount = 1;

    private int commercialSizePerMsg = 4 * 1024;

    private boolean accountStatsEnable = true;
    private boolean accountStatsPrintZeroValues = true;

    private boolean transferMsgByHeap = true;
    private int maxDelayTime = 40;

    private String regionId = MixAll.DEFAULT_TRACE_REGION_ID;
    private int registerBrokerTimeoutMills = 24000;

    private int sendHeartbeatTimeoutMillis = 1000;

    private boolean slaveReadEnable = false;

    private boolean disableConsumeIfConsumerReadSlowly = false;
    private long consumerFallbehindThreshold = 1024L * 1024 * 1024 * 16;

    private boolean brokerFastFailureEnable = true;
    private long waitTimeMillsInSendQueue = 200;
    private long waitTimeMillsInPullQueue = 5 * 1000;
    private long waitTimeMillsInLitePullQueue = 5 * 1000;
    private long waitTimeMillsInHeartbeatQueue = 31 * 1000;
    private long waitTimeMillsInTransactionQueue = 3 * 1000;
    private long waitTimeMillsInAckQueue = 3000;

    private long startAcceptSendRequestTimeStamp = 0L;

    private boolean traceOn = true;

    // Switch of filter bit map calculation.
    // If switch on:
    // 1. Calculate filter bit map when construct queue.
    // 2. Filter bit map will be saved to consume queue extend file if allowed.
    private boolean enableCalcFilterBitMap = false;

    //Reject the pull consumer instance to pull messages from broker.
    private boolean rejectPullConsumerEnable = false;

    // Expect num of consumers will use filter.
    private int expectConsumerNumUseFilter = 32;

    // Error rate of bloom filter, 1~100.
    private int maxErrorRateOfBloomFilter = 20;

    //how long to clean filter data after dead.Default: 24h
    private long filterDataCleanTimeSpan = 24 * 3600 * 1000;

    // whether do filter when retry.
    private boolean filterSupportRetry = false;
    private boolean enablePropertyFilter = false;

    private boolean compressedRegister = false;

    private boolean forceRegister = true;

    /**
     * This configurable item defines interval of topics registration of broker to name server. Allowing values are
     * between 10,000 and 60,000 milliseconds.
     */
    private int registerNameServerPeriod = 1000 * 30;

    /**
     * the interval to send heartbeat to name server for liveness detection.
     */
    private int brokerHeartbeatInterval = 1000;

    /**
     * How long the broker will be considered as inactive by nameserver since last heartbeat. Effective only if
     * enableSlaveActingMaster is true
     */
    private long brokerNotActiveTimeoutMillis = 10 * 1000;

    private boolean enableNetWorkFlowControl = false;

    private int popPollingSize = 1024;
    private int popPollingMapSize = 100000;
    // 20w cost 200M heap memory.
    private long maxPopPollingSize = 100000;
    private int reviveQueueNum = 8;
    private long reviveInterval = 1000;
    private long reviveMaxSlow = 3;
    private long reviveScanTime = 10000;
    private boolean enablePopLog = false;
    private boolean enablePopBufferMerge = false;
    private int popCkStayBufferTime = 10 * 1000;
    private int popCkStayBufferTimeOut = 3 * 1000;
    private int popCkMaxBufferSize = 200000;
    private int popCkOffsetMaxQueueSize = 20000;

    private boolean realTimeNotifyConsumerChange = true;

    private boolean litePullMessageEnable = true;

    // The period to sync broker member group from namesrv, default value is 1 second
    private int syncBrokerMemberGroupPeriod = 1000;

    /**
     * the interval of pulling topic information from the named server
     */
    private long loadBalancePollNameServerInterval = 1000 * 30;

    /**
     * the interval of cleaning
     */
    private int cleanOfflineBrokerInterval = 1000 * 30;

    private boolean serverLoadBalancerEnable = true;

    private MessageRequestMode defaultMessageRequestMode = MessageRequestMode.PULL;

    private int defaultPopShareQueueNum = -1;

    /**
     * The minimum time of the transactional message  to be checked firstly, one message only exceed this time interval
     * that can be checked.
     */
    @ImportantField
    private long transactionTimeOut = 6 * 1000;

    /**
     * The maximum number of times the message was checked, if exceed this value, this message will be discarded.
     */
    @ImportantField
    private int transactionCheckMax = 15;

    /**
     * Transaction message check interval.
     */
    @ImportantField
    private long transactionCheckInterval = 60 * 1000;

    /**
     * Acl feature switch
     */
    @ImportantField
    private boolean aclEnable = false;

    private boolean storeReplyMessageEnable = true;

    private boolean enableDetailStat = true;

    private boolean autoDeleteUnusedStats = false;

    /**
     * Whether to distinguish log paths when multiple brokers are deployed on the same machine
     */
    private boolean isolateLogEnable = false;

    private long forwardTimeout = 3 * 1000;

    /**
     * Slave will act master when failover. For example, if master down, timer or transaction message which is expire in slave will
     * put to master (master of the same process in broker container mode or other masters in cluster when enableFailoverRemotingActing is true)
     * when enableSlaveActingMaster is true
     */
    private boolean enableSlaveActingMaster = false;

    private boolean enableRemoteEscape = false;

    private boolean skipPreOnline = false;

    private boolean asyncSendEnable = true;

    private long consumerOffsetUpdateVersionStep = 500;

    private long delayOffsetUpdateVersionStep = 200;

    /**
     * Whether to lock quorum replicas.
     *
     * True: need to lock quorum replicas succeed. False: only need to lock one replica succeed.
     */
    private boolean lockInStrictMode = false;

    private boolean compatibleWithOldNameSrv = true;

    /**
     * Is startup controller mode, which support auto switch broker's role.
     */
    private boolean enableControllerMode = false;

    private String controllerAddr = "";

    private long syncBrokerMetadataPeriod = 5 * 1000;

    private long checkSyncStateSetPeriod = 5 * 1000;

    private long syncControllerMetadataPeriod = 10 * 1000;

    private String getStackTrace() {
        String stacktrace = " ";
        for (StackTraceElement element : Thread.currentThread().getStackTrace()) {
          stacktrace = stacktrace.concat(element.getClassName() + "\t");
        }
        return stacktrace;
    }
    
    public long getMaxPopPollingSize() {
        log.warn("[CTEST][GET-PARAM] " + "maxPopPollingSize"); //CTEST
        return maxPopPollingSize;
    }

    public void setMaxPopPollingSize(long maxPopPollingSize) {
        log.warn("[CTEST][SET-PARAM] " + "maxPopPollingSize" + getStackTrace()); //CTEST
        this.maxPopPollingSize = maxPopPollingSize;
    }

    public int getReviveQueueNum() {
        log.warn("[CTEST][GET-PARAM] " + "reviveQueueNum"); //CTEST
        return reviveQueueNum;
    }

    public void setReviveQueueNum(int reviveQueueNum) {
        log.warn("[CTEST][SET-PARAM] " + "reviveQueueNum" + getStackTrace()); //CTEST
        this.reviveQueueNum = reviveQueueNum;
    }

    public long getReviveInterval() {
        log.warn("[CTEST][GET-PARAM] " + "reviveInterval"); //CTEST
        return reviveInterval;
    }

    public void setReviveInterval(long reviveInterval) {
        log.warn("[CTEST][SET-PARAM] " + "reviveInterval" + getStackTrace()); //CTEST
        this.reviveInterval = reviveInterval;
    }

    public int getPopCkStayBufferTime() {
        log.warn("[CTEST][GET-PARAM] " + "popCkStayBufferTime"); //CTEST
        return popCkStayBufferTime;
    }

    public void setPopCkStayBufferTime(int popCkStayBufferTime) {
        log.warn("[CTEST][SET-PARAM] " + "popCkStayBufferTime" + getStackTrace()); //CTEST
        this.popCkStayBufferTime = popCkStayBufferTime;
    }

    public int getPopCkStayBufferTimeOut() {
        log.warn("[CTEST][GET-PARAM] " + "popCkStayBufferTimeOut"); //CTEST
        return popCkStayBufferTimeOut;
    }

    public void setPopCkStayBufferTimeOut(int popCkStayBufferTimeOut) {
        log.warn("[CTEST][SET-PARAM] " + "popCkStayBufferTimeOut" + getStackTrace()); //CTEST

        this.popCkStayBufferTimeOut = popCkStayBufferTimeOut;
    }

    public int getPopPollingMapSize() {
        log.warn("[CTEST][GET-PARAM] " + "popPollingMapSize"); //CTEST
        return popPollingMapSize;
    }

    public void setPopPollingMapSize(int popPollingMapSize) {
        log.warn("[CTEST][SET-PARAM] " + "popPollingMapSize" + getStackTrace()); //CTEST

        this.popPollingMapSize = popPollingMapSize;
    }

    public long getReviveScanTime() {
        log.warn("[CTEST][GET-PARAM] " + "reviveScanTime"); //CTEST
        return reviveScanTime;
    }

    public void setReviveScanTime(long reviveScanTime) {
        log.warn("[CTEST][SET-PARAM] " + "reviveScanTime" + getStackTrace()); //CTEST
        this.reviveScanTime = reviveScanTime;
    }

    public long getReviveMaxSlow() {
        log.warn("[CTEST][GET-PARAM] " + "reviveMaxSlow"); //CTEST
        return reviveMaxSlow;
    }

    public void setReviveMaxSlow(long reviveMaxSlow) {
        log.warn("[CTEST][SET-PARAM] " + "reviveMaxSlow" + getStackTrace()); //CTEST

        this.reviveMaxSlow = reviveMaxSlow;
    }

    public int getPopPollingSize() {
        log.warn("[CTEST][GET-PARAM] " + "popPollingSize"); //CTEST
        return popPollingSize;
    }

    public void setPopPollingSize(int popPollingSize) {
        log.warn("[CTEST][SET-PARAM] " + "popPollingSize" + getStackTrace()); //CTEST

        this.popPollingSize = popPollingSize;
    }

    public boolean isEnablePopBufferMerge() {
        log.warn("[CTEST][GET-PARAM] " + "enablePopBufferMerge"); //CTEST
        return enablePopBufferMerge;
    }

    public void setEnablePopBufferMerge(boolean enablePopBufferMerge) {
        log.warn("[CTEST][SET-PARAM] " + "enablePopBufferMerge" + getStackTrace()); //CTEST

        this.enablePopBufferMerge = enablePopBufferMerge;
    }

    public int getPopCkMaxBufferSize() {
        log.warn("[CTEST][GET-PARAM] " + "popCkMaxBufferSize"); //CTEST
        return popCkMaxBufferSize;
    }

    public void setPopCkMaxBufferSize(int popCkMaxBufferSize) {
        log.warn("[CTEST][SET-PARAM] " + "popCkMaxBufferSize" + getStackTrace()); //CTEST

        this.popCkMaxBufferSize = popCkMaxBufferSize;
    }

    public int getPopCkOffsetMaxQueueSize() {
        log.warn("[CTEST][GET-PARAM] " + "popCkOffsetMaxQueueSize"); //CTEST
        return popCkOffsetMaxQueueSize;
    }

    public void setPopCkOffsetMaxQueueSize(int popCkOffsetMaxQueueSize) {
        log.warn("[CTEST][SET-PARAM] " + "popCkOffsetMaxQueueSize" + getStackTrace()); //CTEST

        this.popCkOffsetMaxQueueSize = popCkOffsetMaxQueueSize;
    }

    public boolean isEnablePopLog() {
        log.warn("[CTEST][GET-PARAM] " + "enablePopLog"); //CTEST
        return enablePopLog;
    }

    public void setEnablePopLog(boolean enablePopLog) {
        log.warn("[CTEST][SET-PARAM] " + "enablePopLog" + getStackTrace()); //CTEST

        this.enablePopLog = enablePopLog;
    }

    public boolean isTraceOn() {
        log.warn("[CTEST][GET-PARAM] " + "traceOn"); //CTEST
        return traceOn;
    }

    public void setTraceOn(final boolean traceOn) {
        log.warn("[CTEST][SET-PARAM] " + "traceOn" + getStackTrace()); //CTEST

        this.traceOn = traceOn;
    }

    public long getStartAcceptSendRequestTimeStamp() {
        log.warn("[CTEST][GET-PARAM] " + "startAcceptSendRequestTimeStamp"); //CTEST
        return startAcceptSendRequestTimeStamp;
    }

    public void setStartAcceptSendRequestTimeStamp(final long startAcceptSendRequestTimeStamp) {
        log.warn("[CTEST][SET-PARAM] " + "startAcceptSendRequestTimeStamp" + getStackTrace()); //CTEST

        this.startAcceptSendRequestTimeStamp = startAcceptSendRequestTimeStamp;
    }

    public long getWaitTimeMillsInSendQueue() {
        log.warn("[CTEST][GET-PARAM] " + "waitTimeMillsInSendQueue"); //CTEST
        return waitTimeMillsInSendQueue;
    }

    public void setWaitTimeMillsInSendQueue(final long waitTimeMillsInSendQueue) {
        log.warn("[CTEST][SET-PARAM] " + "waitTimeMillsInSendQueue" + getStackTrace()); //CTEST

        this.waitTimeMillsInSendQueue = waitTimeMillsInSendQueue;
    }

    public long getConsumerFallbehindThreshold() {
        log.warn("[CTEST][GET-PARAM] " + "consumerFallbehindThreshold"); //CTEST
        return consumerFallbehindThreshold;
    }

    public void setConsumerFallbehindThreshold(final long consumerFallbehindThreshold) {
        log.warn("[CTEST][SET-PARAM] " + "consumerFallbehindThreshold" + getStackTrace()); //CTEST

        this.consumerFallbehindThreshold = consumerFallbehindThreshold;
    }

    public boolean isBrokerFastFailureEnable() {
        log.warn("[CTEST][GET-PARAM] " + "brokerFastFailureEnable"); //CTEST
        return brokerFastFailureEnable;
    }

    public void setBrokerFastFailureEnable(final boolean brokerFastFailureEnable) {
        log.warn("[CTEST][SET-PARAM] " + "brokerFastFailureEnable" + getStackTrace()); //CTEST

        this.brokerFastFailureEnable = brokerFastFailureEnable;
    }

    public long getWaitTimeMillsInPullQueue() {
        log.warn("[CTEST][GET-PARAM] " + "waitTimeMillsInPullQueue"); //CTEST
        return waitTimeMillsInPullQueue;
    }

    public void setWaitTimeMillsInPullQueue(final long waitTimeMillsInPullQueue) {
        log.warn("[CTEST][SET-PARAM] " + "waitTimeMillsInPullQueue" + getStackTrace()); //CTEST

        this.waitTimeMillsInPullQueue = waitTimeMillsInPullQueue;
    }

    public boolean isDisableConsumeIfConsumerReadSlowly() {
        log.warn("[CTEST][GET-PARAM] " + "disableConsumeIfConsumerReadSlowly"); //CTEST
        return disableConsumeIfConsumerReadSlowly;
    }

    public void setDisableConsumeIfConsumerReadSlowly(final boolean disableConsumeIfConsumerReadSlowly) {
        log.warn("[CTEST][SET-PARAM] " + "disableConsumeIfConsumerReadSlowly" + getStackTrace()); //CTEST

        this.disableConsumeIfConsumerReadSlowly = disableConsumeIfConsumerReadSlowly;
    }

    public boolean isSlaveReadEnable() {
        log.warn("[CTEST][GET-PARAM] " + "slaveReadEnable"); //CTEST
        return slaveReadEnable;
    }

    public void setSlaveReadEnable(final boolean slaveReadEnable) {
        log.warn("[CTEST][SET-PARAM] " + "slaveReadEnable" + getStackTrace()); //CTEST

        this.slaveReadEnable = slaveReadEnable;
    }

    public int getRegisterBrokerTimeoutMills() {
        log.warn("[CTEST][GET-PARAM] " + "registerBrokerTimeoutMills"); //CTEST
        return registerBrokerTimeoutMills;
    }

    public void setRegisterBrokerTimeoutMills(final int registerBrokerTimeoutMills) {
        log.warn("[CTEST][SET-PARAM] " + "registerBrokerTimeoutMills" + getStackTrace()); //CTEST

        this.registerBrokerTimeoutMills = registerBrokerTimeoutMills;
    }

    public String getRegionId() {
        log.warn("[CTEST][GET-PARAM] " + "regionId"); //CTEST
        return regionId;
    }

    public void setRegionId(final String regionId) {
        log.warn("[CTEST][SET-PARAM] " + "regionId" + getStackTrace()); //CTEST

        this.regionId = regionId;
    }

    public boolean isTransferMsgByHeap() {
        log.warn("[CTEST][GET-PARAM] " + "transferMsgByHeap"); //CTEST
        return transferMsgByHeap;
    }

    public void setTransferMsgByHeap(final boolean transferMsgByHeap) {
        log.warn("[CTEST][SET-PARAM] " + "transferMsgByHeap" + getStackTrace()); //CTEST

        this.transferMsgByHeap = transferMsgByHeap;
    }

    public String getMessageStorePlugIn() {
        log.warn("[CTEST][GET-PARAM] " + "messageStorePlugIn"); //CTEST
        return messageStorePlugIn;
    }

    public void setMessageStorePlugIn(String messageStorePlugIn) {
        log.warn("[CTEST][SET-PARAM] " + "messageStorePlugIn" + getStackTrace()); //CTEST

        this.messageStorePlugIn = messageStorePlugIn;
    }

    public boolean isHighSpeedMode() {
        log.warn("[CTEST][GET-PARAM] " + "highSpeedMode"); //CTEST
        return highSpeedMode;
    }

    public void setHighSpeedMode(final boolean highSpeedMode) {
        log.warn("[CTEST][SET-PARAM] " + "highSpeedMode" + getStackTrace()); //CTEST

        this.highSpeedMode = highSpeedMode;
    }

    public int getBrokerPermission() {
        log.warn("[CTEST][GET-PARAM] " + "brokerPermission"); //CTEST
        return brokerPermission;
    }

    public void setBrokerPermission(int brokerPermission) {
        log.warn("[CTEST][SET-PARAM] " + "brokerPermission" + getStackTrace()); //CTEST

        this.brokerPermission = brokerPermission;
    }

    public int getDefaultTopicQueueNums() {
        log.warn("[CTEST][GET-PARAM] " + "defaultTopicQueueNums"); //CTEST
        return defaultTopicQueueNums;
    }

    public void setDefaultTopicQueueNums(int defaultTopicQueueNums) {
        log.warn("[CTEST][SET-PARAM] " + "defaultTopicQueueNums" + getStackTrace()); //CTEST

        this.defaultTopicQueueNums = defaultTopicQueueNums;
    }

    public boolean isAutoCreateTopicEnable() {
        log.warn("[CTEST][GET-PARAM] " + "autoCreateTopicEnable"); //CTEST
        return autoCreateTopicEnable;
    }

    public void setAutoCreateTopicEnable(boolean autoCreateTopic) {
        log.warn("[CTEST][SET-PARAM] " + "autoCreateTopic" + getStackTrace()); //CTEST

        this.autoCreateTopicEnable = autoCreateTopic;
    }

    public String getBrokerIP1() {
        log.warn("[CTEST][GET-PARAM] " + "brokerIP1"); //CTEST
        return brokerIP1;
    }

    public void setBrokerIP1(String brokerIP1) {
        log.warn("[CTEST][SET-PARAM] " + "brokerIP1" + getStackTrace()); //CTEST

        this.brokerIP1 = brokerIP1;
    }

    public String getBrokerIP2() {
        log.warn("[CTEST][GET-PARAM] " + "brokerIP2"); //CTEST
        return brokerIP2;
    }

    public void setBrokerIP2(String brokerIP2) {
        log.warn("[CTEST][SET-PARAM] " + "brokerIP2" + getStackTrace()); //CTEST

        this.brokerIP2 = brokerIP2;
    }

    public int getSendMessageThreadPoolNums() {
        log.warn("[CTEST][GET-PARAM] " + "sendMessageThreadPoolNums"); //CTEST
        return sendMessageThreadPoolNums;
    }

    public void setSendMessageThreadPoolNums(int sendMessageThreadPoolNums) {
        log.warn("[CTEST][SET-PARAM] " + "sendMessageThreadPoolNums" + getStackTrace()); //CTEST

        this.sendMessageThreadPoolNums = sendMessageThreadPoolNums;
    }

    public int getPutMessageFutureThreadPoolNums() {
        log.warn("[CTEST][GET-PARAM] " + "putMessageFutureThreadPoolNums"); //CTEST
        return putMessageFutureThreadPoolNums;
    }

    public void setPutMessageFutureThreadPoolNums(int putMessageFutureThreadPoolNums) {
        log.warn("[CTEST][SET-PARAM] " + "putMessageFutureThreadPoolNums" + getStackTrace()); //CTEST

        this.putMessageFutureThreadPoolNums = putMessageFutureThreadPoolNums;
    }

    public int getPullMessageThreadPoolNums() {
        log.warn("[CTEST][GET-PARAM] " + "pullMessageThreadPoolNums"); //CTEST
        return pullMessageThreadPoolNums;
    }

    public void setPullMessageThreadPoolNums(int pullMessageThreadPoolNums) {
        log.warn("[CTEST][SET-PARAM] " + "pullMessageThreadPoolNums" + getStackTrace()); //CTEST

        this.pullMessageThreadPoolNums = pullMessageThreadPoolNums;
    }

    public int getAckMessageThreadPoolNums() {
        log.warn("[CTEST][GET-PARAM] " + "ackMessageThreadPoolNums"); //CTEST
        return ackMessageThreadPoolNums;
    }

    public void setAckMessageThreadPoolNums(int ackMessageThreadPoolNums) {
        log.warn("[CTEST][SET-PARAM] " + "ackMessageThreadPoolNums" + getStackTrace()); //CTEST

        this.ackMessageThreadPoolNums = ackMessageThreadPoolNums;
    }

    public int getProcessReplyMessageThreadPoolNums() {
        log.warn("[CTEST][GET-PARAM] " + "processReplyMessageThreadPoolNums"); //CTEST
        return processReplyMessageThreadPoolNums;
    }

    public void setProcessReplyMessageThreadPoolNums(int processReplyMessageThreadPoolNums) {
        log.warn("[CTEST][SET-PARAM] " + "processReplyMessageThreadPoolNums" + getStackTrace()); //CTEST

        this.processReplyMessageThreadPoolNums = processReplyMessageThreadPoolNums;
    }

    public int getQueryMessageThreadPoolNums() {
        log.warn("[CTEST][GET-PARAM] " + "queryMessageThreadPoolNums"); //CTEST
        return queryMessageThreadPoolNums;
    }

    public void setQueryMessageThreadPoolNums(final int queryMessageThreadPoolNums) {
        log.warn("[CTEST][SET-PARAM] " + "queryMessageThreadPoolNums" + getStackTrace()); //CTEST

        this.queryMessageThreadPoolNums = queryMessageThreadPoolNums;
    }

    public int getAdminBrokerThreadPoolNums() {
        log.warn("[CTEST][GET-PARAM] " + "adminBrokerThreadPoolNums"); //CTEST
        return adminBrokerThreadPoolNums;
    }

    public void setAdminBrokerThreadPoolNums(int adminBrokerThreadPoolNums) {
        log.warn("[CTEST][SET-PARAM] " + "adminBrokerThreadPoolNums" + getStackTrace()); //CTEST

        this.adminBrokerThreadPoolNums = adminBrokerThreadPoolNums;
    }

    public int getFlushConsumerOffsetInterval() {
        log.warn("[CTEST][GET-PARAM] " + "flushConsumerOffsetInterval"); //CTEST
        return flushConsumerOffsetInterval;
    }

    public void setFlushConsumerOffsetInterval(int flushConsumerOffsetInterval) {
        log.warn("[CTEST][SET-PARAM] " + "flushConsumerOffsetInterval" + getStackTrace()); //CTEST

        this.flushConsumerOffsetInterval = flushConsumerOffsetInterval;
    }

    public int getFlushConsumerOffsetHistoryInterval() {
        log.warn("[CTEST][GET-PARAM] " + "flushConsumerOffsetHistoryInterval"); //CTEST
        return flushConsumerOffsetHistoryInterval;
    }

    public void setFlushConsumerOffsetHistoryInterval(int flushConsumerOffsetHistoryInterval) {
        log.warn("[CTEST][SET-PARAM] " + "flushConsumerOffsetHistoryInterval" + getStackTrace()); //CTEST

        this.flushConsumerOffsetHistoryInterval = flushConsumerOffsetHistoryInterval;
    }

    public boolean isClusterTopicEnable() {
        log.warn("[CTEST][GET-PARAM] " + "clusterTopicEnable"); //CTEST
        return clusterTopicEnable;
    }

    public void setClusterTopicEnable(boolean clusterTopicEnable) {
        log.warn("[CTEST][SET-PARAM] " + "clusterTopicEnable" + getStackTrace()); //CTEST

        this.clusterTopicEnable = clusterTopicEnable;
    }

    public String getNamesrvAddr() {
        log.warn("[CTEST][GET-PARAM] " + "namesrvAddr"); //CTEST
        return namesrvAddr;
    }

    public void setNamesrvAddr(String namesrvAddr) {
        log.warn("[CTEST][SET-PARAM] " + "namesrvAddr" + getStackTrace()); //CTEST

        this.namesrvAddr = namesrvAddr;
    }

    public boolean isAutoCreateSubscriptionGroup() {
        log.warn("[CTEST][GET-PARAM] " + "autoCreateSubscriptionGroup"); //CTEST
        return autoCreateSubscriptionGroup;
    }

    public void setAutoCreateSubscriptionGroup(boolean autoCreateSubscriptionGroup) {
        log.warn("[CTEST][SET-PARAM] " + "autoCreateSubscriptionGroup" + getStackTrace()); //CTEST

        this.autoCreateSubscriptionGroup = autoCreateSubscriptionGroup;
    }

    public String getBrokerConfigPath() {
        log.warn("[CTEST][GET-PARAM] " + "brokerConfigPath"); //CTEST
        return brokerConfigPath;
    }

    public void setBrokerConfigPath(String brokerConfigPath) {
        log.warn("[CTEST][SET-PARAM] " + "brokerConfigPath" + getStackTrace()); //CTEST

        this.brokerConfigPath = brokerConfigPath;
    }

    public String getRocketmqHome() {
        log.warn("[CTEST][GET-PARAM] " + "rocketmqHome"); //CTEST
        return rocketmqHome;
    }

    public void setRocketmqHome(String rocketmqHome) {
        log.warn("[CTEST][SET-PARAM] " + "rocketmqHome" + getStackTrace()); //CTEST

        this.rocketmqHome = rocketmqHome;
    }

    public int getListenPort() {
        log.warn("[CTEST][GET-PARAM] " + "listenPort"); //CTEST
        return listenPort;
    }

    public void setListenPort(int listenPort) {
        log.warn("[CTEST][SET-PARAM] " + "listenPort" + getStackTrace()); //CTEST

        this.listenPort = listenPort;
    }

    public int getLitePullMessageThreadPoolNums() {
        log.warn("[CTEST][GET-PARAM] " + "litePullMessageThreadPoolNums"); //CTEST
        return litePullMessageThreadPoolNums;
    }

    public void setLitePullMessageThreadPoolNums(int litePullMessageThreadPoolNums) {
        log.warn("[CTEST][SET-PARAM] " + "litePullMessageThreadPoolNums" + getStackTrace()); //CTEST

        this.litePullMessageThreadPoolNums = litePullMessageThreadPoolNums;
    }

    public int getLitePullThreadPoolQueueCapacity() {
        log.warn("[CTEST][GET-PARAM] " + "litePullThreadPoolQueueCapacity"); //CTEST
        return litePullThreadPoolQueueCapacity;
    }

    public void setLitePullThreadPoolQueueCapacity(int litePullThreadPoolQueueCapacity) {
        log.warn("[CTEST][SET-PARAM] " + "litePullThreadPoolQueueCapacity" + getStackTrace()); //CTEST

        this.litePullThreadPoolQueueCapacity = litePullThreadPoolQueueCapacity;
    }

    public int getAdminBrokerThreadPoolQueueCapacity() {
        log.warn("[CTEST][GET-PARAM] " + "adminBrokerThreadPoolQueueCapacity"); //CTEST
        return adminBrokerThreadPoolQueueCapacity;
    }

    public void setAdminBrokerThreadPoolQueueCapacity(int adminBrokerThreadPoolQueueCapacity) {
        log.warn("[CTEST][SET-PARAM] " + "adminBrokerThreadPoolQueueCapacity" + getStackTrace()); //CTEST

        this.adminBrokerThreadPoolQueueCapacity = adminBrokerThreadPoolQueueCapacity;
    }

    public int getLoadBalanceThreadPoolQueueCapacity() {
        log.warn("[CTEST][GET-PARAM] " + "loadBalanceThreadPoolQueueCapacity"); //CTEST
        return loadBalanceThreadPoolQueueCapacity;
    }

    public void setLoadBalanceThreadPoolQueueCapacity(int loadBalanceThreadPoolQueueCapacity) {
        log.warn("[CTEST][SET-PARAM] " + "loadBalanceThreadPoolQueueCapacity" + getStackTrace()); //CTEST

        this.loadBalanceThreadPoolQueueCapacity = loadBalanceThreadPoolQueueCapacity;
    }

    public int getSendHeartbeatTimeoutMillis() {
        log.warn("[CTEST][GET-PARAM] " + "sendHeartbeatTimeoutMillis"); //CTEST
        return sendHeartbeatTimeoutMillis;
    }

    public void setSendHeartbeatTimeoutMillis(int sendHeartbeatTimeoutMillis) {
        log.warn("[CTEST][SET-PARAM] " + "sendHeartbeatTimeoutMillis" + getStackTrace()); //CTEST

        this.sendHeartbeatTimeoutMillis = sendHeartbeatTimeoutMillis;
    }

    public long getWaitTimeMillsInLitePullQueue() {
        log.warn("[CTEST][GET-PARAM] " + "waitTimeMillsInLitePullQueue"); //CTEST
        return waitTimeMillsInLitePullQueue;
    }

    public void setWaitTimeMillsInLitePullQueue(long waitTimeMillsInLitePullQueue) {
        log.warn("[CTEST][SET-PARAM] " + "waitTimeMillsInLitePullQueue" + getStackTrace()); //CTEST

        this.waitTimeMillsInLitePullQueue = waitTimeMillsInLitePullQueue;
    }

    public boolean isLitePullMessageEnable() {
        log.warn("[CTEST][GET-PARAM] " + "litePullMessageEnable"); //CTEST
        return litePullMessageEnable;
    }

    public void setLitePullMessageEnable(boolean litePullMessageEnable) {
        log.warn("[CTEST][SET-PARAM] " + "litePullMessageEnable" + getStackTrace()); //CTEST

        this.litePullMessageEnable = litePullMessageEnable;
    }

    public int getSyncBrokerMemberGroupPeriod() {
        log.warn("[CTEST][GET-PARAM] " + "syncBrokerMemberGroupPeriod"); //CTEST
        return syncBrokerMemberGroupPeriod;
    }

    public void setSyncBrokerMemberGroupPeriod(int syncBrokerMemberGroupPeriod) {
        log.warn("[CTEST][SET-PARAM] " + "syncBrokerMemberGroupPeriod" + getStackTrace()); //CTEST

        this.syncBrokerMemberGroupPeriod = syncBrokerMemberGroupPeriod;
    }

    public boolean isRejectTransactionMessage() {
        log.warn("[CTEST][GET-PARAM] " + "rejectTransactionMessage"); //CTEST
        return rejectTransactionMessage;
    }

    public void setRejectTransactionMessage(boolean rejectTransactionMessage) {
        log.warn("[CTEST][SET-PARAM] " + "rejectTransactionMessage" + getStackTrace()); //CTEST

        this.rejectTransactionMessage = rejectTransactionMessage;
    }

    public boolean isFetchNamesrvAddrByAddressServer() {
        log.warn("[CTEST][GET-PARAM] " + "fetchNamesrvAddrByAddressServer"); //CTEST
        return fetchNamesrvAddrByAddressServer;
    }

    public void setFetchNamesrvAddrByAddressServer(boolean fetchNamesrvAddrByAddressServer) {
        log.warn("[CTEST][SET-PARAM] " + "fetchNamesrvAddrByAddressServer" + getStackTrace()); //CTEST

        this.fetchNamesrvAddrByAddressServer = fetchNamesrvAddrByAddressServer;
    }

    public int getSendThreadPoolQueueCapacity() {
        log.warn("[CTEST][GET-PARAM] " + "sendThreadPoolQueueCapacity"); //CTEST
        return sendThreadPoolQueueCapacity;
    }

    public void setSendThreadPoolQueueCapacity(int sendThreadPoolQueueCapacity) {
        log.warn("[CTEST][SET-PARAM] " + "sendThreadPoolQueueCapacity" + getStackTrace()); //CTEST

        this.sendThreadPoolQueueCapacity = sendThreadPoolQueueCapacity;
    }

    public int getPutThreadPoolQueueCapacity() {
        log.warn("[CTEST][GET-PARAM] " + "putThreadPoolQueueCapacity"); //CTEST
        return putThreadPoolQueueCapacity;
    }

    public void setPutThreadPoolQueueCapacity(int putThreadPoolQueueCapacity) {
        log.warn("[CTEST][SET-PARAM] " + "putThreadPoolQueueCapacity" + getStackTrace()); //CTEST

        this.putThreadPoolQueueCapacity = putThreadPoolQueueCapacity;
    }

    public int getPullThreadPoolQueueCapacity() {
        log.warn("[CTEST][GET-PARAM] " + "pullThreadPoolQueueCapacity"); //CTEST
        return pullThreadPoolQueueCapacity;
    }

    public void setPullThreadPoolQueueCapacity(int pullThreadPoolQueueCapacity) {
        log.warn("[CTEST][SET-PARAM] " + "pullThreadPoolQueueCapacity" + getStackTrace()); //CTEST

        this.pullThreadPoolQueueCapacity = pullThreadPoolQueueCapacity;
    }

    public int getAckThreadPoolQueueCapacity() {
        log.warn("[CTEST][GET-PARAM] " + "ackThreadPoolQueueCapacity"); //CTEST
        return ackThreadPoolQueueCapacity;
    }

    public void setAckThreadPoolQueueCapacity(int ackThreadPoolQueueCapacity) {
        log.warn("[CTEST][SET-PARAM] " + "ackThreadPoolQueueCapacity" + getStackTrace()); //CTEST

        this.ackThreadPoolQueueCapacity = ackThreadPoolQueueCapacity;
    }

    public int getReplyThreadPoolQueueCapacity() {
        log.warn("[CTEST][GET-PARAM] " + "replyThreadPoolQueueCapacity"); //CTEST
        return replyThreadPoolQueueCapacity;
    }

    public void setReplyThreadPoolQueueCapacity(int replyThreadPoolQueueCapacity) {
        log.warn("[CTEST][SET-PARAM] " + "replyThreadPoolQueueCapacity" + getStackTrace()); //CTEST

        this.replyThreadPoolQueueCapacity = replyThreadPoolQueueCapacity;
    }

    public int getQueryThreadPoolQueueCapacity() {
        log.warn("[CTEST][GET-PARAM] " + "queryThreadPoolQueueCapacity"); //CTEST
        return queryThreadPoolQueueCapacity;
    }

    public void setQueryThreadPoolQueueCapacity(final int queryThreadPoolQueueCapacity) {
        log.warn("[CTEST][SET-PARAM] " + "queryThreadPoolQueueCapacity" + getStackTrace()); //CTEST

        this.queryThreadPoolQueueCapacity = queryThreadPoolQueueCapacity;
    }

    public boolean isBrokerTopicEnable() {
        log.warn("[CTEST][GET-PARAM] " + "brokerTopicEnable"); //CTEST
        return brokerTopicEnable;
    }

    public void setBrokerTopicEnable(boolean brokerTopicEnable) {
        log.warn("[CTEST][SET-PARAM] " + "brokerTopicEnable" + getStackTrace()); //CTEST

        this.brokerTopicEnable = brokerTopicEnable;
    }

    public int getFilterServerNums() {
        log.warn("[CTEST][GET-PARAM] " + "filterServerNums"); //CTEST
        return filterServerNums;
    }

    public void setFilterServerNums(int filterServerNums) {
        log.warn("[CTEST][SET-PARAM] " + "filterServerNums" + getStackTrace()); //CTEST

        this.filterServerNums = filterServerNums;
    }

    public boolean isLongPollingEnable() {
        log.warn("[CTEST][GET-PARAM] " + "longPollingEnable"); //CTEST
        return longPollingEnable;
    }

    public void setLongPollingEnable(boolean longPollingEnable) {
        log.warn("[CTEST][SET-PARAM] " + "longPollingEnable" + getStackTrace()); //CTEST

        this.longPollingEnable = longPollingEnable;
    }

    public boolean isNotifyConsumerIdsChangedEnable() {
        log.warn("[CTEST][GET-PARAM] " + "notifyConsumerIdsChangedEnable"); //CTEST
        return notifyConsumerIdsChangedEnable;
    }

    public void setNotifyConsumerIdsChangedEnable(boolean notifyConsumerIdsChangedEnable) {
        log.warn("[CTEST][SET-PARAM] " + "notifyConsumerIdsChangedEnable" + getStackTrace()); //CTEST

        this.notifyConsumerIdsChangedEnable = notifyConsumerIdsChangedEnable;
    }

    public long getShortPollingTimeMills() {
        log.warn("[CTEST][GET-PARAM] " + "shortPollingTimeMills"); //CTEST
        return shortPollingTimeMills;
    }

    public void setShortPollingTimeMills(long shortPollingTimeMills) {
        log.warn("[CTEST][SET-PARAM] " + "shortPollingTimeMills" + getStackTrace()); //CTEST

        this.shortPollingTimeMills = shortPollingTimeMills;
    }

    public int getClientManageThreadPoolNums() {
        log.warn("[CTEST][GET-PARAM] " + "clientManageThreadPoolNums"); //CTEST
        return clientManageThreadPoolNums;
    }

    public void setClientManageThreadPoolNums(int clientManageThreadPoolNums) {
        log.warn("[CTEST][SET-PARAM] " + "clientManageThreadPoolNums" + getStackTrace()); //CTEST

        this.clientManageThreadPoolNums = clientManageThreadPoolNums;
    }

    public int getMaxDelayTime() {
        log.warn("[CTEST][GET-PARAM] " + "maxDelayTime"); //CTEST
        return maxDelayTime;
    }

    public void setMaxDelayTime(final int maxDelayTime) {
        log.warn("[CTEST][SET-PARAM] " + "maxDelayTime" + getStackTrace()); //CTEST

        this.maxDelayTime = maxDelayTime;
    }

    public int getClientManagerThreadPoolQueueCapacity() {
        log.warn("[CTEST][GET-PARAM] " + "clientManagerThreadPoolQueueCapacity"); //CTEST
        return clientManagerThreadPoolQueueCapacity;
    }

    public void setClientManagerThreadPoolQueueCapacity(int clientManagerThreadPoolQueueCapacity) {
        log.warn("[CTEST][SET-PARAM] " + "clientManagerThreadPoolQueueCapacity" + getStackTrace()); //CTEST

        this.clientManagerThreadPoolQueueCapacity = clientManagerThreadPoolQueueCapacity;
    }

    public int getConsumerManagerThreadPoolQueueCapacity() {
        log.warn("[CTEST][GET-PARAM] " + "consumerManagerThreadPoolQueueCapacity"); //CTEST
        return consumerManagerThreadPoolQueueCapacity;
    }

    public void setConsumerManagerThreadPoolQueueCapacity(int consumerManagerThreadPoolQueueCapacity) {
        log.warn("[CTEST][SET-PARAM] " + "consumerManagerThreadPoolQueueCapacity" + getStackTrace()); //CTEST

        this.consumerManagerThreadPoolQueueCapacity = consumerManagerThreadPoolQueueCapacity;
    }

    public int getConsumerManageThreadPoolNums() {
        log.warn("[CTEST][GET-PARAM] " + "consumerManageThreadPoolNums"); //CTEST
        return consumerManageThreadPoolNums;
    }

    public void setConsumerManageThreadPoolNums(int consumerManageThreadPoolNums) {
        log.warn("[CTEST][SET-PARAM] " + "consumerManageThreadPoolNums" + getStackTrace()); //CTEST

        this.consumerManageThreadPoolNums = consumerManageThreadPoolNums;
    }

    public int getCommercialBaseCount() {
        log.warn("[CTEST][GET-PARAM] " + "commercialBaseCount"); //CTEST
        return commercialBaseCount;
    }

    public void setCommercialBaseCount(int commercialBaseCount) {
        log.warn("[CTEST][SET-PARAM] " + "commercialBaseCount" + getStackTrace()); //CTEST

        this.commercialBaseCount = commercialBaseCount;
    }

    public boolean isEnableCalcFilterBitMap() {
        log.warn("[CTEST][GET-PARAM] " + "enableCalcFilterBitMap"); //CTEST
        return enableCalcFilterBitMap;
    }

    public void setEnableCalcFilterBitMap(boolean enableCalcFilterBitMap) {
        log.warn("[CTEST][SET-PARAM] " + "enableCalcFilterBitMap" + getStackTrace()); //CTEST

        this.enableCalcFilterBitMap = enableCalcFilterBitMap;
    }

    public int getExpectConsumerNumUseFilter() {
        log.warn("[CTEST][GET-PARAM] " + "expectConsumerNumUseFilter"); //CTEST
        return expectConsumerNumUseFilter;
    }

    public void setExpectConsumerNumUseFilter(int expectConsumerNumUseFilter) {
        log.warn("[CTEST][SET-PARAM] " + "expectConsumerNumUseFilter" + getStackTrace()); //CTEST

        this.expectConsumerNumUseFilter = expectConsumerNumUseFilter;
    }

    public int getMaxErrorRateOfBloomFilter() {
        log.warn("[CTEST][GET-PARAM] " + "maxErrorRateOfBloomFilter"); //CTEST
        return maxErrorRateOfBloomFilter;
    }

    public void setMaxErrorRateOfBloomFilter(int maxErrorRateOfBloomFilter) {
        log.warn("[CTEST][SET-PARAM] " + "maxErrorRateOfBloomFilter" + getStackTrace()); //CTEST

        this.maxErrorRateOfBloomFilter = maxErrorRateOfBloomFilter;
    }

    public long getFilterDataCleanTimeSpan() {
        log.warn("[CTEST][GET-PARAM] " + "filterDataCleanTimeSpan"); //CTEST
        return filterDataCleanTimeSpan;
    }

    public void setFilterDataCleanTimeSpan(long filterDataCleanTimeSpan) {
        log.warn("[CTEST][SET-PARAM] " + "filterDataCleanTimeSpan" + getStackTrace()); //CTEST

        this.filterDataCleanTimeSpan = filterDataCleanTimeSpan;
    }

    public boolean isFilterSupportRetry() {
        log.warn("[CTEST][GET-PARAM] " + "filterSupportRetry"); //CTEST
        return filterSupportRetry;
    }

    public void setFilterSupportRetry(boolean filterSupportRetry) {
        log.warn("[CTEST][SET-PARAM] " + "filterSupportRetry" + getStackTrace()); //CTEST

        this.filterSupportRetry = filterSupportRetry;
    }

    public boolean isEnablePropertyFilter() {
        log.warn("[CTEST][GET-PARAM] " + "enablePropertyFilter"); //CTEST
        return enablePropertyFilter;
    }

    public void setEnablePropertyFilter(boolean enablePropertyFilter) {
        log.warn("[CTEST][SET-PARAM] " + "enablePropertyFilter" + getStackTrace()); //CTEST

        this.enablePropertyFilter = enablePropertyFilter;
    }

    public boolean isCompressedRegister() {
        log.warn("[CTEST][GET-PARAM] " + "compressedRegister"); //CTEST
        return compressedRegister;
    }

    public void setCompressedRegister(boolean compressedRegister) {
        log.warn("[CTEST][SET-PARAM] " + "compressedRegister" + getStackTrace()); //CTEST

        this.compressedRegister = compressedRegister;
    }

    public boolean isForceRegister() {
        log.warn("[CTEST][GET-PARAM] " + "forceRegister"); //CTEST
        return forceRegister;
    }

    public void setForceRegister(boolean forceRegister) {
        log.warn("[CTEST][SET-PARAM] " + "forceRegister" + getStackTrace()); //CTEST

        this.forceRegister = forceRegister;
    }

    public int getHeartbeatThreadPoolQueueCapacity() {
        log.warn("[CTEST][GET-PARAM] " + "heartbeatThreadPoolQueueCapacity"); //CTEST
        return heartbeatThreadPoolQueueCapacity;
    }

    public void setHeartbeatThreadPoolQueueCapacity(int heartbeatThreadPoolQueueCapacity) {
        log.warn("[CTEST][SET-PARAM] " + "heartbeatThreadPoolQueueCapacity" + getStackTrace()); //CTEST

        this.heartbeatThreadPoolQueueCapacity = heartbeatThreadPoolQueueCapacity;
    }

    public int getHeartbeatThreadPoolNums() {
        log.warn("[CTEST][GET-PARAM] " + "heartbeatThreadPoolNums"); //CTEST
        return heartbeatThreadPoolNums;
    }

    public void setHeartbeatThreadPoolNums(int heartbeatThreadPoolNums) {
        log.warn("[CTEST][SET-PARAM] " + "heartbeatThreadPoolNums" + getStackTrace()); //CTEST

        this.heartbeatThreadPoolNums = heartbeatThreadPoolNums;
    }

    public long getWaitTimeMillsInHeartbeatQueue() {
        log.warn("[CTEST][GET-PARAM] " + "waitTimeMillsInHeartbeatQueue"); //CTEST
        return waitTimeMillsInHeartbeatQueue;
    }

    public void setWaitTimeMillsInHeartbeatQueue(long waitTimeMillsInHeartbeatQueue) {
        log.warn("[CTEST][SET-PARAM] " + "waitTimeMillsInHeartbeatQueue" + getStackTrace()); //CTEST

        this.waitTimeMillsInHeartbeatQueue = waitTimeMillsInHeartbeatQueue;
    }

    public int getRegisterNameServerPeriod() {
        log.warn("[CTEST][GET-PARAM] " + "registerNameServerPeriod"); //CTEST
        return registerNameServerPeriod;
    }

    public void setRegisterNameServerPeriod(int registerNameServerPeriod) {
        log.warn("[CTEST][SET-PARAM] " + "registerNameServerPeriod" + getStackTrace()); //CTEST

        this.registerNameServerPeriod = registerNameServerPeriod;
    }

    public long getTransactionTimeOut() {
        log.warn("[CTEST][GET-PARAM] " + "transactionTimeOut"); //CTEST
        return transactionTimeOut;
    }

    public void setTransactionTimeOut(long transactionTimeOut) {
        log.warn("[CTEST][SET-PARAM] " + "transactionTimeOut" + getStackTrace()); //CTEST

        this.transactionTimeOut = transactionTimeOut;
    }

    public int getTransactionCheckMax() {
        log.warn("[CTEST][GET-PARAM] " + "transactionCheckMax"); //CTEST
        return transactionCheckMax;
    }

    public void setTransactionCheckMax(int transactionCheckMax) {
        log.warn("[CTEST][SET-PARAM] " + "transactionCheckMax" + getStackTrace()); //CTEST

        this.transactionCheckMax = transactionCheckMax;
    }

    public long getTransactionCheckInterval() {
        log.warn("[CTEST][GET-PARAM] " + "transactionCheckInterval"); //CTEST
        return transactionCheckInterval;
    }

    public void setTransactionCheckInterval(long transactionCheckInterval) {
        log.warn("[CTEST][SET-PARAM] " + "transactionCheckInterval" + getStackTrace()); //CTEST

        this.transactionCheckInterval = transactionCheckInterval;
    }

    public int getEndTransactionThreadPoolNums() {
        log.warn("[CTEST][GET-PARAM] " + "endTransactionThreadPoolNums"); //CTEST
        return endTransactionThreadPoolNums;
    }

    public void setEndTransactionThreadPoolNums(int endTransactionThreadPoolNums) {
        log.warn("[CTEST][SET-PARAM] " + "endTransactionThreadPoolNums" + getStackTrace()); //CTEST

        this.endTransactionThreadPoolNums = endTransactionThreadPoolNums;
    }

    public int getEndTransactionPoolQueueCapacity() {
        log.warn("[CTEST][GET-PARAM] " + "endTransactionPoolQueueCapacity"); //CTEST
        return endTransactionPoolQueueCapacity;
    }

    public void setEndTransactionPoolQueueCapacity(int endTransactionPoolQueueCapacity) {
        log.warn("[CTEST][SET-PARAM] " + "endTransactionPoolQueueCapacity" + getStackTrace()); //CTEST

        this.endTransactionPoolQueueCapacity = endTransactionPoolQueueCapacity;
    }

    public long getWaitTimeMillsInTransactionQueue() {
        log.warn("[CTEST][GET-PARAM] " + "waitTimeMillsInTransactionQueue"); //CTEST
        return waitTimeMillsInTransactionQueue;
    }

    public void setWaitTimeMillsInTransactionQueue(long waitTimeMillsInTransactionQueue) {
        log.warn("[CTEST][SET-PARAM] " + "waitTimeMillsInTransactionQueue" + getStackTrace()); //CTEST

        this.waitTimeMillsInTransactionQueue = waitTimeMillsInTransactionQueue;
    }

    public String getMsgTraceTopicName() {
        log.warn("[CTEST][GET-PARAM] " + "msgTraceTopicName"); //CTEST
        return msgTraceTopicName;
    }

    public void setMsgTraceTopicName(String msgTraceTopicName) {
        log.warn("[CTEST][SET-PARAM] " + "msgTraceTopicName" + getStackTrace()); //CTEST

        this.msgTraceTopicName = msgTraceTopicName;
    }

    public boolean isTraceTopicEnable() {
        log.warn("[CTEST][GET-PARAM] " + "traceTopicEnable"); //CTEST
        return traceTopicEnable;
    }

    public void setTraceTopicEnable(boolean traceTopicEnable) {
        log.warn("[CTEST][SET-PARAM] " + "traceTopicEnable" + getStackTrace()); //CTEST

        this.traceTopicEnable = traceTopicEnable;
    }

    public boolean isAclEnable() {
        log.warn("[CTEST][GET-PARAM] " + "aclEnable"); //CTEST
        return aclEnable;
    }

    public void setAclEnable(boolean aclEnable) {
        log.warn("[CTEST][SET-PARAM] " + "aclEnable" + getStackTrace()); //CTEST

        this.aclEnable = aclEnable;
    }

    public boolean isStoreReplyMessageEnable() {
        log.warn("[CTEST][GET-PARAM] " + "storeReplyMessageEnable"); //CTEST
        return storeReplyMessageEnable;
    }

    public void setStoreReplyMessageEnable(boolean storeReplyMessageEnable) {
        this.storeReplyMessageEnable = storeReplyMessageEnable;
    }

    public boolean isEnableDetailStat() {
        log.warn("[CTEST][GET-PARAM] " + "enableDetailStat"); //CTEST
        return enableDetailStat;
    }

    public void setEnableDetailStat(boolean enableDetailStat) {
        this.enableDetailStat = enableDetailStat;
    }

    public boolean isAutoDeleteUnusedStats() {
        log.warn("[CTEST][GET-PARAM] " + "autoDeleteUnusedStats"); //CTEST
        return autoDeleteUnusedStats;
    }

    public void setAutoDeleteUnusedStats(boolean autoDeleteUnusedStats) {
        log.warn("[CTEST][SET-PARAM] " + "autoDeleteUnusedStats" + getStackTrace()); //CTEST

        this.autoDeleteUnusedStats = autoDeleteUnusedStats;
    }

    public long getLoadBalancePollNameServerInterval() {
        log.warn("[CTEST][GET-PARAM] " + "loadBalancePollNameServerInterval"); //CTEST
        return loadBalancePollNameServerInterval;
    }

    public void setLoadBalancePollNameServerInterval(long loadBalancePollNameServerInterval) {
        log.warn("[CTEST][SET-PARAM] " + "loadBalancePollNameServerInterval" + getStackTrace()); //CTEST

        this.loadBalancePollNameServerInterval = loadBalancePollNameServerInterval;
    }

    public int getCleanOfflineBrokerInterval() {
        log.warn("[CTEST][GET-PARAM] " + "cleanOfflineBrokerInterval"); //CTEST
        return cleanOfflineBrokerInterval;
    }

    public void setCleanOfflineBrokerInterval(int cleanOfflineBrokerInterval) {
        log.warn("[CTEST][SET-PARAM] " + "cleanOfflineBrokerInterval" + getStackTrace()); //CTEST

        this.cleanOfflineBrokerInterval = cleanOfflineBrokerInterval;
    }

    public int getLoadBalanceProcessorThreadPoolNums() {
        log.warn("[CTEST][GET-PARAM] " + "loadBalanceProcessorThreadPoolNums"); //CTEST
        return loadBalanceProcessorThreadPoolNums;
    }

    public void setLoadBalanceProcessorThreadPoolNums(int loadBalanceProcessorThreadPoolNums) {
        log.warn("[CTEST][SET-PARAM] " + "loadBalanceProcessorThreadPoolNums" + getStackTrace()); //CTEST

        this.loadBalanceProcessorThreadPoolNums = loadBalanceProcessorThreadPoolNums;
    }

    public boolean isServerLoadBalancerEnable() {
        log.warn("[CTEST][GET-PARAM] " + "serverLoadBalancerEnable"); //CTEST
        return serverLoadBalancerEnable;
    }

    public void setServerLoadBalancerEnable(boolean serverLoadBalancerEnable) {
        log.warn("[CTEST][SET-PARAM] " + "serverLoadBalancerEnable" + getStackTrace()); //CTEST

        this.serverLoadBalancerEnable = serverLoadBalancerEnable;
    }

    public MessageRequestMode getDefaultMessageRequestMode() {
        log.warn("[CTEST][GET-PARAM] " + "defaultMessageRequestMode"); //CTEST
        return defaultMessageRequestMode;
    }

    public void setDefaultMessageRequestMode(String defaultMessageRequestMode) {
        log.warn("[CTEST][SET-PARAM] " + "defaultMessageRequestMode" + getStackTrace()); //CTEST

        this.defaultMessageRequestMode = MessageRequestMode.valueOf(defaultMessageRequestMode);
    }

    public int getDefaultPopShareQueueNum() {
        log.warn("[CTEST][GET-PARAM] " + "defaultPopShareQueueNum"); //CTEST
        return defaultPopShareQueueNum;
    }

    public void setDefaultPopShareQueueNum(int defaultPopShareQueueNum) {
        log.warn("[CTEST][SET-PARAM] " + "defaultPopShareQueueNum" + getStackTrace()); //CTEST

        this.defaultPopShareQueueNum = defaultPopShareQueueNum;
    }

    public long getForwardTimeout() {
        log.warn("[CTEST][GET-PARAM] " + "forwardTimeout"); //CTEST
        return forwardTimeout;
    }

    public void setForwardTimeout(long timeout) {
        log.warn("[CTEST][SET-PARAM] " + "forwardTimeout" + getStackTrace()); //CTEST

        this.forwardTimeout = timeout;
    }

    public int getBrokerHeartbeatInterval() {
        log.warn("[CTEST][GET-PARAM] " + "brokerHeartbeatInterval"); //CTEST
        return brokerHeartbeatInterval;
    }

    public void setBrokerHeartbeatInterval(int brokerHeartbeatInterval) {
        log.warn("[CTEST][SET-PARAM] " + "brokerHeartbeatInterval" + getStackTrace()); //CTEST

        this.brokerHeartbeatInterval = brokerHeartbeatInterval;
    }

    public long getBrokerNotActiveTimeoutMillis() {
        log.warn("[CTEST][GET-PARAM] " + "brokerNotActiveTimeoutMillis"); //CTEST
        return brokerNotActiveTimeoutMillis;
    }

    public void setBrokerNotActiveTimeoutMillis(long brokerNotActiveTimeoutMillis) {
        log.warn("[CTEST][SET-PARAM] " + "brokerNotActiveTimeoutMillis" + getStackTrace()); //CTEST

        this.brokerNotActiveTimeoutMillis = brokerNotActiveTimeoutMillis;
    }

    public boolean isEnableNetWorkFlowControl() {
        log.warn("[CTEST][GET-PARAM] " + "enableNetWorkFlowControl"); //CTEST
        return enableNetWorkFlowControl;
    }

    public void setEnableNetWorkFlowControl(boolean enableNetWorkFlowControl) {
        log.warn("[CTEST][SET-PARAM] " + "enableNetWorkFlowControl" + getStackTrace()); //CTEST

        this.enableNetWorkFlowControl = enableNetWorkFlowControl;
    }

    public boolean isRealTimeNotifyConsumerChange() {
        log.warn("[CTEST][GET-PARAM] " + "realTimeNotifyConsumerChange"); //CTEST
        return realTimeNotifyConsumerChange;
    }

    public void setRealTimeNotifyConsumerChange(boolean realTimeNotifyConsumerChange) {
        log.warn("[CTEST][SET-PARAM] " + "realTimeNotifyConsumerChange" + getStackTrace()); //CTEST

        this.realTimeNotifyConsumerChange = realTimeNotifyConsumerChange;
    }

    public boolean isEnableSlaveActingMaster() {
        log.warn("[CTEST][GET-PARAM] " + "enableSlaveActingMaster"); //CTEST
        return enableSlaveActingMaster;
    }

    public void setEnableSlaveActingMaster(boolean enableSlaveActingMaster) {
        log.warn("[CTEST][SET-PARAM] " + "enableSlaveActingMaster" + getStackTrace()); //CTEST

        this.enableSlaveActingMaster = enableSlaveActingMaster;
    }

    public boolean isEnableRemoteEscape() {
        log.warn("[CTEST][GET-PARAM] " + "enableRemoteEscape"); //CTEST
        return enableRemoteEscape;
    }

    public void setEnableRemoteEscape(boolean enableRemoteEscape) {
        log.warn("[CTEST][SET-PARAM] " + "enableRemoteEscape" + getStackTrace()); //CTEST

        this.enableRemoteEscape = enableRemoteEscape;
    }

    public boolean isSkipPreOnline() {
        log.warn("[CTEST][GET-PARAM] " + "skipPreOnline"); //CTEST
        return skipPreOnline;
    }

    public void setSkipPreOnline(boolean skipPreOnline) {
        log.warn("[CTEST][SET-PARAM] " + "skipPreOnline" + getStackTrace()); //CTEST

        this.skipPreOnline = skipPreOnline;
    }

    public boolean isAsyncSendEnable() {
        log.warn("[CTEST][GET-PARAM] " + "asyncSendEnable"); //CTEST
        return asyncSendEnable;
    }

    public void setAsyncSendEnable(boolean asyncSendEnable) {
        log.warn("[CTEST][SET-PARAM] " + "asyncSendEnable" + getStackTrace()); //CTEST

        this.asyncSendEnable = asyncSendEnable;
    }

    public long getConsumerOffsetUpdateVersionStep() {
        log.warn("[CTEST][GET-PARAM] " + "consumerOffsetUpdateVersionStep"); //CTEST
        return consumerOffsetUpdateVersionStep;
    }

    public void setConsumerOffsetUpdateVersionStep(long consumerOffsetUpdateVersionStep) {
        log.warn("[CTEST][SET-PARAM] " + "consumerOffsetUpdateVersionStep" + getStackTrace()); //CTEST

        this.consumerOffsetUpdateVersionStep = consumerOffsetUpdateVersionStep;
    }

    public long getDelayOffsetUpdateVersionStep() {
        log.warn("[CTEST][GET-PARAM] " + "delayOffsetUpdateVersionStep"); //CTEST
        return delayOffsetUpdateVersionStep;
    }

    public void setDelayOffsetUpdateVersionStep(long delayOffsetUpdateVersionStep) {
        log.warn("[CTEST][SET-PARAM] " + "delayOffsetUpdateVersionStep" + getStackTrace()); //CTEST

        this.delayOffsetUpdateVersionStep = delayOffsetUpdateVersionStep;
    }

    public int getCommercialSizePerMsg() {
        log.warn("[CTEST][GET-PARAM] " + "commercialSizePerMsg"); //CTEST
        return commercialSizePerMsg;
    }

    public void setCommercialSizePerMsg(int commercialSizePerMsg) {
        log.warn("[CTEST][SET-PARAM] " + "commercialSizePerMsg" + getStackTrace()); //CTEST

        this.commercialSizePerMsg = commercialSizePerMsg;
    }

    public long getWaitTimeMillsInAckQueue() {
        log.warn("[CTEST][GET-PARAM] " + "waitTimeMillsInAckQueue"); //CTEST
        return waitTimeMillsInAckQueue;
    }

    public void setWaitTimeMillsInAckQueue(long waitTimeMillsInAckQueue) {
        log.warn("[CTEST][SET-PARAM] " + "waitTimeMillsInAckQueue" + getStackTrace()); //CTEST

        this.waitTimeMillsInAckQueue = waitTimeMillsInAckQueue;
    }

    public boolean isRejectPullConsumerEnable() {
        log.warn("[CTEST][GET-PARAM] " + "rejectPullConsumerEnable"); //CTEST
        return rejectPullConsumerEnable;
    }

    public void setRejectPullConsumerEnable(boolean rejectPullConsumerEnable) {
        log.warn("[CTEST][SET-PARAM] " + "rejectPullConsumerEnable" + getStackTrace()); //CTEST

        this.rejectPullConsumerEnable = rejectPullConsumerEnable;
    }

    public boolean isAccountStatsEnable() {
        log.warn("[CTEST][GET-PARAM] " + "accountStatsEnable"); //CTEST
        return accountStatsEnable;
    }

    public void setAccountStatsEnable(boolean accountStatsEnable) {
        log.warn("[CTEST][SET-PARAM] " + "accountStatsEnable" + getStackTrace()); //CTEST

        this.accountStatsEnable = accountStatsEnable;
    }

    public boolean isAccountStatsPrintZeroValues() {
        log.warn("[CTEST][GET-PARAM] " + "accountStatsPrintZeroValues"); //CTEST
        return accountStatsPrintZeroValues;
    }

    public void setAccountStatsPrintZeroValues(boolean accountStatsPrintZeroValues) {
        log.warn("[CTEST][SET-PARAM] " + "accountStatsPrintZeroValues" + getStackTrace()); //CTEST

        this.accountStatsPrintZeroValues = accountStatsPrintZeroValues;
    }

    public boolean isLockInStrictMode() {
        log.warn("[CTEST][GET-PARAM] " + "lockInStrictMode"); //CTEST
        return lockInStrictMode;
    }

    public void setLockInStrictMode(boolean lockInStrictMode) {
        log.warn("[CTEST][SET-PARAM] " + "lockInStrictMode" + getStackTrace()); //CTEST

        this.lockInStrictMode = lockInStrictMode;
    }

    public boolean isIsolateLogEnable() {
        log.warn("[CTEST][GET-PARAM] " + "isolateLogEnable"); //CTEST
        return isolateLogEnable;
    }

    public void setIsolateLogEnable(boolean isolateLogEnable) {
        log.warn("[CTEST][SET-PARAM] " + "isolateLogEnable" + getStackTrace()); //CTEST

        this.isolateLogEnable = isolateLogEnable;
    }

    public boolean isCompatibleWithOldNameSrv() {
        log.warn("[CTEST][GET-PARAM] " + "compatibleWithOldNameSrv"); //CTEST
        return compatibleWithOldNameSrv;
    }

    public void setCompatibleWithOldNameSrv(boolean compatibleWithOldNameSrv) {
        log.warn("[CTEST][SET-PARAM] " + "compatibleWithOldNameSrv" + getStackTrace()); //CTEST

        this.compatibleWithOldNameSrv = compatibleWithOldNameSrv;
    }

    public boolean isEnableControllerMode() {
        log.warn("[CTEST][GET-PARAM] " + "enableControllerMode"); //CTEST
        return enableControllerMode;
    }

    public void setEnableControllerMode(boolean enableControllerMode) {
        log.warn("[CTEST][SET-PARAM] " + "enableControllerMode" + getStackTrace()); //CTEST

        this.enableControllerMode = enableControllerMode;
    }

    public String getControllerAddr() {
        log.warn("[CTEST][GET-PARAM] " + "controllerAddr"); //CTEST
        return controllerAddr;
    }

    public void setControllerAddr(String controllerAddr) {
        log.warn("[CTEST][SET-PARAM] " + "controllerAddr" + getStackTrace()); //CTEST

        this.controllerAddr = controllerAddr;
    }

    public long getSyncBrokerMetadataPeriod() {
        log.warn("[CTEST][GET-PARAM] " + "syncBrokerMetadataPeriod"); //CTEST
        return syncBrokerMetadataPeriod;
    }

    public void setSyncBrokerMetadataPeriod(long syncBrokerMetadataPeriod) {
        log.warn("[CTEST][SET-PARAM] " + "syncBrokerMetadataPeriod" + getStackTrace()); //CTEST

        this.syncBrokerMetadataPeriod = syncBrokerMetadataPeriod;
    }

    public long getCheckSyncStateSetPeriod() {
        log.warn("[CTEST][GET-PARAM] " + "checkSyncStateSetPeriod"); //CTEST
        return checkSyncStateSetPeriod;
    }

    public void setCheckSyncStateSetPeriod(long checkSyncStateSetPeriod) {
        log.warn("[CTEST][SET-PARAM] " + "checkSyncStateSetPeriod" + getStackTrace()); //CTEST

        this.checkSyncStateSetPeriod = checkSyncStateSetPeriod;
    }

    public long getSyncControllerMetadataPeriod() {
        log.warn("[CTEST][GET-PARAM] " + "syncControllerMetadataPeriod"); //CTEST
        return syncControllerMetadataPeriod;
    }

    public void setSyncControllerMetadataPeriod(long syncControllerMetadataPeriod) {
        log.warn("[CTEST][SET-PARAM] " + "syncControllerMetadataPeriod" + getStackTrace()); //CTEST

        this.syncControllerMetadataPeriod = syncControllerMetadataPeriod;
    }
}
