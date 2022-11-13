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
package org.apache.rocketmq.store.config;

import org.apache.rocketmq.common.annotation.ImportantField;
import org.apache.rocketmq.store.ConsumeQueue;
import org.apache.rocketmq.store.queue.BatchConsumeQueue;
// added
import org.apache.rocketmq.logging.InternalLogger;
import org.apache.rocketmq.logging.InternalLoggerFactory;
import org.apache.rocketmq.common.constant.LoggerName;

import java.io.File;

public class MessageStoreConfig {
    // added
    private static final InternalLogger log = InternalLoggerFactory.getLogger(LoggerName.STORE_LOGGER_NAME);

    public static final String MULTI_PATH_SPLITTER = System.getProperty("rocketmq.broker.multiPathSplitter", ",");

    //The root directory in which the log data is kept
    @ImportantField
    private String storePathRootDir = System.getProperty("user.home") + File.separator + "store";

    //The directory in which the commitlog is kept
    @ImportantField
    private String storePathCommitLog = null;

    @ImportantField
    private String storePathDLedgerCommitLog = null;

    //The directory in which the epochFile is kept
    @ImportantField
    private String storePathEpochFile = System.getProperty("user.home") + File.separator + "store"
        + File.separator + "epochFileCheckpoint";

    private String readOnlyCommitLogStorePaths = null;

    // CommitLog file size,default is 1G
    private int mappedFileSizeCommitLog = 1024 * 1024 * 1024;

    // TimerLog file size, default is 100M
    private int mappedFileSizeTimerLog = 100 * 1024 * 1024;

    private int timerPrecisionMs = 1000;

    private int timerRollWindowSlot = 3600 * 24 * 2;
    private int timerFlushIntervalMs = 1000;
    private int timerGetMessageThreadNum = 3;
    private int timerPutMessageThreadNum = 3;

    private boolean timerEnableDisruptor = false;

    private boolean timerEnableCheckMetrics = true;
    private boolean timerInterceptDelayLevel = false;
    private int timerMaxDelaySec = 3600 * 24 * 3;
    private boolean timerWheelEnable = true;

    /**
     * 1. Register to broker after (startTime + disappearTimeAfterStart)
     * 2. Internal msg exchange will start after (startTime + disappearTimeAfterStart)
     *  A. PopReviveService
     *  B. TimerDequeueGetService
     */
    @ImportantField
    private int disappearTimeAfterStart = -1;

    private boolean timerStopEnqueue = false;

    private String timerCheckMetricsWhen = "05";

    private boolean timerSkipUnknownError = false;
    private boolean timerWarmEnable = false;
    private boolean timerStopDequeue = false;
    private int timerCongestNumEachSlot = Integer.MAX_VALUE;

    private int timerMetricSmallThreshold = 1000000;
    private int timerProgressLogIntervalMs = 10 * 1000;

    // ConsumeQueue file size,default is 30W
    private int mappedFileSizeConsumeQueue = 300000 * ConsumeQueue.CQ_STORE_UNIT_SIZE;
    // enable consume queue ext
    private boolean enableConsumeQueueExt = false;
    // ConsumeQueue extend file size, 48M
    private int mappedFileSizeConsumeQueueExt = 48 * 1024 * 1024;
    private int mapperFileSizeBatchConsumeQueue = 300000 * BatchConsumeQueue.CQ_STORE_UNIT_SIZE;
    // Bit count of filter bit map.
    // this will be set by pipe of calculate filter bit map.
    private int bitMapLengthConsumeQueueExt = 64;

    // CommitLog flush interval
    // flush data to disk
    @ImportantField
    private int flushIntervalCommitLog = 500;

    // Only used if TransientStorePool enabled
    // flush data to FileChannel
    @ImportantField
    private int commitIntervalCommitLog = 200;

    private int maxRecoveryCommitlogFiles = 30;

    private int diskSpaceWarningLevelRatio = 90;

    private int diskSpaceCleanForciblyRatio = 85;

    /**
     * introduced since 4.0.x. Determine whether to use mutex reentrantLock when putting message.<br/>
     */
    private boolean useReentrantLockWhenPutMessage = true;

    // Whether schedule flush
    @ImportantField
    private boolean flushCommitLogTimed = true;
    // ConsumeQueue flush interval
    private int flushIntervalConsumeQueue = 1000;
    // Resource reclaim interval
    private int cleanResourceInterval = 10000;
    // CommitLog removal interval
    private int deleteCommitLogFilesInterval = 100;
    // ConsumeQueue removal interval
    private int deleteConsumeQueueFilesInterval = 100;
    private int destroyMapedFileIntervalForcibly = 1000 * 120;
    private int redeleteHangedFileInterval = 1000 * 120;
    // When to delete,default is at 4 am
    @ImportantField
    private String deleteWhen = "04";
    private int diskMaxUsedSpaceRatio = 75;
    // The number of hours to keep a log file before deleting it (in hours)
    @ImportantField
    private int fileReservedTime = 72;
    @ImportantField
    private int deleteFileBatchMax = 10;
    // Flow control for ConsumeQueue
    private int putMsgIndexHightWater = 600000;
    // The maximum size of message body,default is 4M,4M only for body length,not include others.
    private int maxMessageSize = 1024 * 1024 * 4;
    // Whether check the CRC32 of the records consumed.
    // This ensures no on-the-wire or on-disk corruption to the messages occurred.
    // This check adds some overhead,so it may be disabled in cases seeking extreme performance.
    private boolean checkCRCOnRecover = true;
    // How many pages are to be flushed when flush CommitLog
    private int flushCommitLogLeastPages = 4;
    // How many pages are to be committed when commit data to file
    private int commitCommitLogLeastPages = 4;
    // Flush page size when the disk in warming state
    private int flushLeastPagesWhenWarmMapedFile = 1024 / 4 * 16;
    // How many pages are to be flushed when flush ConsumeQueue
    private int flushConsumeQueueLeastPages = 2;
    private int flushCommitLogThoroughInterval = 1000 * 10;
    private int commitCommitLogThoroughInterval = 200;
    private int flushConsumeQueueThoroughInterval = 1000 * 60;
    @ImportantField
    private int maxTransferBytesOnMessageInMemory = 1024 * 256;
    @ImportantField
    private int maxTransferCountOnMessageInMemory = 32;
    @ImportantField
    private int maxTransferBytesOnMessageInDisk = 1024 * 64;
    @ImportantField
    private int maxTransferCountOnMessageInDisk = 8;
    @ImportantField
    private int accessMessageInMemoryMaxRatio = 40;
    @ImportantField
    private boolean messageIndexEnable = true;
    private int maxHashSlotNum = 5000000;
    private int maxIndexNum = 5000000 * 4;
    private int maxMsgsNumBatch = 64;
    @ImportantField
    private boolean messageIndexSafe = false;
    private int haListenPort = 10912;
    private int haSendHeartbeatInterval = 1000 * 5;
    private int haHousekeepingInterval = 1000 * 20;
    /**
     * Maximum size of data to transfer to slave.
     * NOTE: cannot be larger than HAClient.READ_MAX_BUFFER_SIZE
     */
    private int haTransferBatchSize = 1024 * 32;
    @ImportantField
    private String haMasterAddress = null;
    private int haMaxGapNotInSync = 1024 * 1024 * 256;
    @ImportantField
    private volatile BrokerRole brokerRole = BrokerRole.ASYNC_MASTER;
    @ImportantField
    private FlushDiskType flushDiskType = FlushDiskType.ASYNC_FLUSH;
    // Used by GroupTransferService to sync messages from master to slave
    private int syncFlushTimeout = 1000 * 5;
    // Used by PutMessage to wait messages be flushed to disk and synchronized in current broker member group.
    private int putMessageTimeout = 1000 * 8;
    private int slaveTimeout = 3000;
    private String messageDelayLevel = "1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h";
    private long flushDelayOffsetInterval = 1000 * 10;
    @ImportantField
    private boolean cleanFileForciblyEnable = true;
    private boolean warmMapedFileEnable = false;
    private boolean offsetCheckInSlave = false;
    private boolean debugLockEnable = false;
    private boolean duplicationEnable = false;
    private boolean diskFallRecorded = true;
    private long osPageCacheBusyTimeOutMills = 1000;
    private int defaultQueryMaxNum = 32;

    @ImportantField
    private boolean transientStorePoolEnable = false;
    private int transientStorePoolSize = 5;
    private boolean fastFailIfNoBufferInStorePool = false;

    // DLedger message store config
    private boolean enableDLegerCommitLog = false;
    private String dLegerGroup;
    private String dLegerPeers;
    private String dLegerSelfId;
    private String preferredLeaderId;
    private boolean isEnableBatchPush = false;

    private boolean enableScheduleMessageStats = true;

    private boolean enableLmq = false;
    private boolean enableMultiDispatch = false;
    private int maxLmqConsumeQueueNum = 20000;

    private boolean enableScheduleAsyncDeliver = false;
    private int scheduleAsyncDeliverMaxPendingLimit = 2000;
    private int scheduleAsyncDeliverMaxResendNum2Blocked = 3;

    private int maxBatchDeleteFilesNum = 50;
    //Polish dispatch
    private int dispatchCqThreads = 10;
    private int dispatchCqCacheNum = 1024 * 4;
    private boolean enableAsyncReput = true;
    //For recheck the reput
    private boolean recheckReputOffsetFromCq = false;

    // Maximum length of topic
    private int maxTopicLength = 1000;
    private int travelCqFileNumWhenGetMessage = 1;
    // Sleep interval between to corrections
    private int correctLogicMinOffsetSleepInterval = 1;
    // Force correct min offset interval
    private int correctLogicMinOffsetForceInterval = 5 * 60 * 1000;
    // swap
    private boolean mappedFileSwapEnable = true;
    private long commitLogForceSwapMapInterval = 12L * 60 * 60 * 1000;
    private long commitLogSwapMapInterval = 1L * 60 * 60 * 1000;
    private int commitLogSwapMapReserveFileNum = 100;
    private long logicQueueForceSwapMapInterval = 12L * 60 * 60 * 1000;
    private long logicQueueSwapMapInterval = 1L * 60 * 60 * 1000;
    private long cleanSwapedMapInterval = 5L * 60 * 1000;
    private int logicQueueSwapMapReserveFileNum = 20;

    private boolean searchBcqByCacheEnable = true;

    @ImportantField
    private boolean dispatchFromSenderThread = false;

    @ImportantField
    private boolean wakeCommitWhenPutMessage = true;
    @ImportantField
    private boolean wakeFlushWhenPutMessage = false;

    @ImportantField
    private boolean enableCleanExpiredOffset = false;

    private int maxAsyncPutMessageRequests = 5000;

    private int pullBatchMaxMessageCount = 160;

    @ImportantField
    private int totalReplicas = 1;

    /**
     * Each message must be written successfully to at least in-sync replicas.
     * The master broker is considered one of the in-sync replicas, and it's included in the count of total.
     * If a master broker is ASYNC_MASTER, inSyncReplicas will be ignored.
     * If enableControllerMode is true and ackAckInSyncStateSet is true, inSyncReplicas will be ignored.
     */
    @ImportantField
    private int inSyncReplicas = 1;

    /**
     * Will be worked in auto multiple replicas mode, to provide minimum in-sync replicas.
     * It is still valid in controller mode.
     */
    @ImportantField
    private int minInSyncReplicas = 1;

    /**
     * Each message must be written successfully to all replicas in InSyncStateSet.
     */
    @ImportantField
    private boolean allAckInSyncStateSet = false;

    /**
     * Dynamically adjust in-sync replicas to provide higher availability, the real time in-sync replicas
     * will smaller than inSyncReplicas config.
     */
    @ImportantField
    private boolean enableAutoInSyncReplicas = false;

    /**
     * Enable or not ha flow control
     */
    @ImportantField
    private boolean haFlowControlEnable = false;

    /**
     * The max speed for one slave when transfer data in ha
     */
    private long maxHaTransferByteInSecond = 100 * 1024 * 1024;

    /**
     * The max gap time that slave doesn't catch up to master.
     */
    private long haMaxTimeSlaveNotCatchup = 1000 * 15;

    /**
     * Sync flush offset from master when broker startup, used in upgrading from old version broker.
     */
    private boolean syncMasterFlushOffsetWhenStartup = false;

    /**
     * Max checksum range.
     */
    private long maxChecksumRange = 1024 * 1024 * 1024;

    private int replicasPerDiskPartition = 1;

    private double logicalDiskSpaceCleanForciblyThreshold = 0.8;

    private long maxSlaveResendLength = 256 * 1024 * 1024;

    /**
     * Whether sync from lastFile when a new broker replicas(no data) join the master.
     */
    private boolean syncFromLastFile = false;

    private boolean asyncLearner = false;
    // added
    private String getStackTrace() {
        String stacktrace = " ";
        for (StackTraceElement element : Thread.currentThread().getStackTrace()) {
            stacktrace = stacktrace.concat(element.getClassName() + "\t");
        }
        return stacktrace;
    }
    public boolean isDebugLockEnable() {
        log.warn("[CTEST][GET-PARAM] " + "debugLockEnable"); //CTEST

        return debugLockEnable;
    }

    public void setDebugLockEnable(final boolean debugLockEnable) {
        log.warn("[CTEST][SET-PARAM] " + "debugLockEnable" + getStackTrace()); //CTEST

        this.debugLockEnable = debugLockEnable;
    }

    public boolean isDuplicationEnable() {
        log.warn("[CTEST][GET-PARAM] " + "duplicationEnable"); //CTEST

        return duplicationEnable;
    }

    public void setDuplicationEnable(final boolean duplicationEnable) {
        log.warn("[CTEST][SET-PARAM] " + "duplicationEnable" + getStackTrace()); //CTEST

        this.duplicationEnable = duplicationEnable;
    }

    public long getOsPageCacheBusyTimeOutMills() {
        log.warn("[CTEST][GET-PARAM] " + "osPageCacheBusyTimeOutMills"); //CTEST
        return osPageCacheBusyTimeOutMills;
    }

    public void setOsPageCacheBusyTimeOutMills(final long osPageCacheBusyTimeOutMills) {
        log.warn("[CTEST][SET-PARAM] " + "osPageCacheBusyTimeOutMills" + getStackTrace()); //CTEST

        this.osPageCacheBusyTimeOutMills = osPageCacheBusyTimeOutMills;
    }

    public boolean isDiskFallRecorded() {
        log.warn("[CTEST][GET-PARAM] " + "diskFallRecorded"); //CTEST
        return diskFallRecorded;
    }

    public void setDiskFallRecorded(final boolean diskFallRecorded) {
        log.warn("[CTEST][SET-PARAM] " + "diskFallRecorded" + getStackTrace()); //CTEST

        this.diskFallRecorded = diskFallRecorded;
    }

    public boolean isWarmMapedFileEnable() {
        log.warn("[CTEST][GET-PARAM] " + "warmMapedFileEnable"); //CTEST
        return warmMapedFileEnable;
    }

    public void setWarmMapedFileEnable(boolean warmMapedFileEnable) {
        log.warn("[CTEST][SET-PARAM] " + "warmMapedFileEnable" + getStackTrace()); //CTEST

        this.warmMapedFileEnable = warmMapedFileEnable;
    }

    public int getMappedFileSizeCommitLog() {
        log.warn("[CTEST][GET-PARAM] " + "mappedFileSizeCommitLog"); //CTEST
        return mappedFileSizeCommitLog;
    }

    public void setMappedFileSizeCommitLog(int mappedFileSizeCommitLog) {
        log.warn("[CTEST][SET-PARAM] " + "mappedFileSizeCommitLog" + getStackTrace()); //CTEST

        this.mappedFileSizeCommitLog = mappedFileSizeCommitLog;
    }

    public int getMappedFileSizeConsumeQueue() {

        int factor = (int) Math.ceil(this.mappedFileSizeConsumeQueue / (ConsumeQueue.CQ_STORE_UNIT_SIZE * 1.0));
        log.warn("[CTEST][GET-PARAM] " + "mappedFileSizeConsumeQueue"); //CTEST
        return (int) (factor * ConsumeQueue.CQ_STORE_UNIT_SIZE);
    }

    public void setMappedFileSizeConsumeQueue(int mappedFileSizeConsumeQueue) {
        log.warn("[CTEST][SET-PARAM] " + "mappedFileSizeConsumeQueue" + getStackTrace()); //CTEST
        this.mappedFileSizeConsumeQueue = mappedFileSizeConsumeQueue;
    }

    public boolean isEnableConsumeQueueExt() {
        log.warn("[CTEST][GET-PARAM] " + "enableConsumeQueueExt"); //CTEST
        return enableConsumeQueueExt;
    }

    public void setEnableConsumeQueueExt(boolean enableConsumeQueueExt) {
        log.warn("[CTEST][SET-PARAM] " + "enableConsumeQueueExt" + getStackTrace()); //CTEST

        this.enableConsumeQueueExt = enableConsumeQueueExt;
    }

    public int getMappedFileSizeConsumeQueueExt() {
        log.warn("[CTEST][GET-PARAM] " + "mappedFileSizeConsumeQueueExt"); //CTEST
        return mappedFileSizeConsumeQueueExt;
    }

    public void setMappedFileSizeConsumeQueueExt(int mappedFileSizeConsumeQueueExt) {
        log.warn("[CTEST][SET-PARAM] " + "mappedFileSizeConsumeQueueExt" + getStackTrace()); //CTEST

        this.mappedFileSizeConsumeQueueExt = mappedFileSizeConsumeQueueExt;
    }

    public int getBitMapLengthConsumeQueueExt() {
        log.warn("[CTEST][GET-PARAM] " + "bitMapLengthConsumeQueueExt"); //CTEST
        return bitMapLengthConsumeQueueExt;
    }

    public void setBitMapLengthConsumeQueueExt(int bitMapLengthConsumeQueueExt) {
        log.warn("[CTEST][SET-PARAM] " + "bitMapLengthConsumeQueueExt" + getStackTrace()); //CTEST

        this.bitMapLengthConsumeQueueExt = bitMapLengthConsumeQueueExt;
    }

    public int getFlushIntervalCommitLog() {
        log.warn("[CTEST][GET-PARAM] " + "flushIntervalCommitLog"); //CTEST
        return flushIntervalCommitLog;
    }

    public void setFlushIntervalCommitLog(int flushIntervalCommitLog) {
        log.warn("[CTEST][SET-PARAM] " + "flushIntervalCommitLog" + getStackTrace()); //CTEST

        this.flushIntervalCommitLog = flushIntervalCommitLog;
    }

    public int getFlushIntervalConsumeQueue() {
        log.warn("[CTEST][GET-PARAM] " + "flushIntervalConsumeQueue"); //CTEST
        return flushIntervalConsumeQueue;
    }

    public void setFlushIntervalConsumeQueue(int flushIntervalConsumeQueue) {
        log.warn("[CTEST][SET-PARAM] " + "flushIntervalConsumeQueue" + getStackTrace()); //CTEST

        this.flushIntervalConsumeQueue = flushIntervalConsumeQueue;
    }

    public int getPutMsgIndexHightWater() {
        log.warn("[CTEST][GET-PARAM] " + "putMsgIndexHightWater"); //CTEST
        return putMsgIndexHightWater;
    }

    public void setPutMsgIndexHightWater(int putMsgIndexHightWater) {
        log.warn("[CTEST][SET-PARAM] " + "putMsgIndexHightWater" + getStackTrace()); //CTEST

        this.putMsgIndexHightWater = putMsgIndexHightWater;
    }

    public int getCleanResourceInterval() {
        log.warn("[CTEST][GET-PARAM] " + "cleanResourceInterval"); //CTEST
        return cleanResourceInterval;
    }

    public void setCleanResourceInterval(int cleanResourceInterval) {
        log.warn("[CTEST][SET-PARAM] " + "cleanResourceInterval" + getStackTrace()); //CTEST

        this.cleanResourceInterval = cleanResourceInterval;
    }

    public int getMaxMessageSize() {
        log.warn("[CTEST][GET-PARAM] " + "maxMessageSize"); //CTEST
        return maxMessageSize;
    }

    public void setMaxMessageSize(int maxMessageSize) {
        log.warn("[CTEST][SET-PARAM] " + "maxMessageSize" + getStackTrace()); //CTEST

        this.maxMessageSize = maxMessageSize;
    }

    public int getMaxTopicLength() {
        log.warn("[CTEST][GET-PARAM] " + "maxTopicLength"); //CTEST
        return maxTopicLength;
    }

    public void setMaxTopicLength(int maxTopicLength) {
        log.warn("[CTEST][SET-PARAM] " + "maxTopicLength" + getStackTrace()); //CTEST

        this.maxTopicLength = maxTopicLength;
    }

    public int getTravelCqFileNumWhenGetMessage() {
        log.warn("[CTEST][GET-PARAM] " + "travelCqFileNumWhenGetMessage"); //CTEST
        return travelCqFileNumWhenGetMessage;
    }

    public void setTravelCqFileNumWhenGetMessage(int travelCqFileNumWhenGetMessage) {
        log.warn("[CTEST][SET-PARAM] " + "travelCqFileNumWhenGetMessage" + getStackTrace()); //CTEST

        this.travelCqFileNumWhenGetMessage = travelCqFileNumWhenGetMessage;
    }

    public int getCorrectLogicMinOffsetSleepInterval() {
        log.warn("[CTEST][GET-PARAM] " + "correctLogicMinOffsetSleepInterval"); //CTEST
        return correctLogicMinOffsetSleepInterval;
    }

    public void setCorrectLogicMinOffsetSleepInterval(int correctLogicMinOffsetSleepInterval) {
        log.warn("[CTEST][SET-PARAM] " + "correctLogicMinOffsetSleepInterval" + getStackTrace()); //CTEST

        this.correctLogicMinOffsetSleepInterval = correctLogicMinOffsetSleepInterval;
    }

    public int getCorrectLogicMinOffsetForceInterval() {
        log.warn("[CTEST][GET-PARAM] " + "correctLogicMinOffsetForceInterval"); //CTEST
        return correctLogicMinOffsetForceInterval;
    }

    public void setCorrectLogicMinOffsetForceInterval(int correctLogicMinOffsetForceInterval) {
        log.warn("[CTEST][SET-PARAM] " + "correctLogicMinOffsetForceInterval" + getStackTrace()); //CTEST

        this.correctLogicMinOffsetForceInterval = correctLogicMinOffsetForceInterval;
    }

    public boolean isCheckCRCOnRecover() {
        log.warn("[CTEST][GET-PARAM] " + "checkCRCOnRecover"); //CTEST
        return checkCRCOnRecover;
    }

    public boolean getCheckCRCOnRecover() {
        log.warn("[CTEST][GET-PARAM] " + "checkCRCOnRecover"); //CTEST
        return checkCRCOnRecover;
    }

    public void setCheckCRCOnRecover(boolean checkCRCOnRecover) {
        log.warn("[CTEST][SET-PARAM] " + "checkCRCOnRecover" + getStackTrace()); //CTEST

        this.checkCRCOnRecover = checkCRCOnRecover;
    }

    public String getStorePathCommitLog() {
        if (storePathCommitLog == null) {
            return storePathRootDir + File.separator + "commitlog";
        }
        log.warn("[CTEST][GET-PARAM] " + "storePathCommitLog"); //CTEST

        return storePathCommitLog;
    }

    public void setStorePathCommitLog(String storePathCommitLog) {
        log.warn("[CTEST][SET-PARAM] " + "storePathCommitLog" + getStackTrace()); //CTEST

        this.storePathCommitLog = storePathCommitLog;
    }

    public String getStorePathDLedgerCommitLog() {
        log.warn("[CTEST][GET-PARAM] " + "storePathDLedgerCommitLog"); //CTEST
        return storePathDLedgerCommitLog;
    }

    public void setStorePathDLedgerCommitLog(String storePathDLedgerCommitLog) {
        log.warn("[CTEST][SET-PARAM] " + "storePathDLedgerCommitLog" + getStackTrace()); //CTEST

        this.storePathDLedgerCommitLog = storePathDLedgerCommitLog;
    }

    public String getStorePathEpochFile() {
        log.warn("[CTEST][GET-PARAM] " + "storePathEpochFile"); //CTEST
        return storePathEpochFile;
    }

    public void setStorePathEpochFile(String storePathEpochFile) {
        log.warn("[CTEST][SET-PARAM] " + "storePathEpochFile" + getStackTrace()); //CTEST

        this.storePathEpochFile = storePathEpochFile;
    }

    public String getDeleteWhen() {
        log.warn("[CTEST][GET-PARAM] " + "deleteWhen"); //CTEST
        return deleteWhen;
    }

    public void setDeleteWhen(String deleteWhen) {
        log.warn("[CTEST][SET-PARAM] " + "deleteWhen" + getStackTrace()); //CTEST

        this.deleteWhen = deleteWhen;
    }

    public int getDiskMaxUsedSpaceRatio() {
        log.warn("[CTEST][GET-PARAM] " + "diskMaxUsedSpaceRatio"); //CTEST

        if (this.diskMaxUsedSpaceRatio < 10)
            return 10;

        if (this.diskMaxUsedSpaceRatio > 95)
            return 95;
        
        return diskMaxUsedSpaceRatio;
    }

    public void setDiskMaxUsedSpaceRatio(int diskMaxUsedSpaceRatio) {
        log.warn("[CTEST][SET-PARAM] " + "diskMaxUsedSpaceRatio" + getStackTrace()); //CTEST

        this.diskMaxUsedSpaceRatio = diskMaxUsedSpaceRatio;
    }

    public int getDeleteCommitLogFilesInterval() {
        log.warn("[CTEST][GET-PARAM] " + "deleteCommitLogFilesInterval"); //CTEST

        return deleteCommitLogFilesInterval;
    }

    public void setDeleteCommitLogFilesInterval(int deleteCommitLogFilesInterval) {
        log.warn("[CTEST][SET-PARAM] " + "deleteCommitLogFilesInterval" + getStackTrace()); //CTEST

        this.deleteCommitLogFilesInterval = deleteCommitLogFilesInterval;
    }

    public int getDeleteConsumeQueueFilesInterval() {
        log.warn("[CTEST][GET-PARAM] " + "deleteConsumeQueueFilesInterval"); //CTEST

        return deleteConsumeQueueFilesInterval;
    }

    public void setDeleteConsumeQueueFilesInterval(int deleteConsumeQueueFilesInterval) {
        log.warn("[CTEST][SET-PARAM] " + "deleteConsumeQueueFilesInterval" + getStackTrace()); //CTEST

        this.deleteConsumeQueueFilesInterval = deleteConsumeQueueFilesInterval;
    }

    public int getMaxTransferBytesOnMessageInMemory() {
        log.warn("[CTEST][GET-PARAM] " + "maxTransferBytesOnMessageInMemory"); //CTEST

        return maxTransferBytesOnMessageInMemory;
    }

    public void setMaxTransferBytesOnMessageInMemory(int maxTransferBytesOnMessageInMemory) {
        log.warn("[CTEST][SET-PARAM] " + "maxTransferBytesOnMessageInMemory" + getStackTrace()); //CTEST

        this.maxTransferBytesOnMessageInMemory = maxTransferBytesOnMessageInMemory;
    }

    public int getMaxTransferCountOnMessageInMemory() {
        log.warn("[CTEST][GET-PARAM] " + "maxTransferCountOnMessageInMemory"); //CTEST

        return maxTransferCountOnMessageInMemory;
    }

    public void setMaxTransferCountOnMessageInMemory(int maxTransferCountOnMessageInMemory) {
        log.warn("[CTEST][SET-PARAM] " + "maxTransferCountOnMessageInMemory" + getStackTrace()); //CTEST

        this.maxTransferCountOnMessageInMemory = maxTransferCountOnMessageInMemory;
    }

    public int getMaxTransferBytesOnMessageInDisk() {
        log.warn("[CTEST][GET-PARAM] " + "maxTransferBytesOnMessageInDisk"); //CTEST

        return maxTransferBytesOnMessageInDisk;
    }

    public void setMaxTransferBytesOnMessageInDisk(int maxTransferBytesOnMessageInDisk) {
        log.warn("[CTEST][SET-PARAM] " + "maxTransferBytesOnMessageInDisk" + getStackTrace()); //CTEST

        this.maxTransferBytesOnMessageInDisk = maxTransferBytesOnMessageInDisk;
    }

    public int getMaxTransferCountOnMessageInDisk() {
        log.warn("[CTEST][GET-PARAM] " + "maxTransferCountOnMessageInDisk"); //CTEST

        return maxTransferCountOnMessageInDisk;
    }

    public void setMaxTransferCountOnMessageInDisk(int maxTransferCountOnMessageInDisk) {
        log.warn("[CTEST][SET-PARAM] " + "maxTransferCountOnMessageInDisk" + getStackTrace()); //CTEST

        this.maxTransferCountOnMessageInDisk = maxTransferCountOnMessageInDisk;
    }

    public int getFlushCommitLogLeastPages() {
        log.warn("[CTEST][GET-PARAM] " + "flushCommitLogLeastPages"); //CTEST

        return flushCommitLogLeastPages;
    }

    public void setFlushCommitLogLeastPages(int flushCommitLogLeastPages) {
        log.warn("[CTEST][SET-PARAM] " + "flushCommitLogLeastPages" + getStackTrace()); //CTEST

        this.flushCommitLogLeastPages = flushCommitLogLeastPages;
    }

    public int getFlushConsumeQueueLeastPages() {
        log.warn("[CTEST][GET-PARAM] " + "flushConsumeQueueLeastPages"); //CTEST

        return flushConsumeQueueLeastPages;
    }

    public void setFlushConsumeQueueLeastPages(int flushConsumeQueueLeastPages) {
        log.warn("[CTEST][SET-PARAM] " + "flushConsumeQueueLeastPages" + getStackTrace()); //CTEST

        this.flushConsumeQueueLeastPages = flushConsumeQueueLeastPages;
    }

    public int getFlushCommitLogThoroughInterval() {
        log.warn("[CTEST][GET-PARAM] " + "flushCommitLogThoroughInterval"); //CTEST

        return flushCommitLogThoroughInterval;
    }

    public void setFlushCommitLogThoroughInterval(int flushCommitLogThoroughInterval) {
        log.warn("[CTEST][SET-PARAM] " + "flushCommitLogThoroughInterval" + getStackTrace()); //CTEST

        this.flushCommitLogThoroughInterval = flushCommitLogThoroughInterval;
    }

    public int getFlushConsumeQueueThoroughInterval() {
        log.warn("[CTEST][GET-PARAM] " + "flushConsumeQueueThoroughInterval"); //CTEST

        return flushConsumeQueueThoroughInterval;
    }

    public void setFlushConsumeQueueThoroughInterval(int flushConsumeQueueThoroughInterval) {
        log.warn("[CTEST][SET-PARAM] " + "flushConsumeQueueThoroughInterval" + getStackTrace()); //CTEST

        this.flushConsumeQueueThoroughInterval = flushConsumeQueueThoroughInterval;
    }

    public int getDestroyMapedFileIntervalForcibly() {
        log.warn("[CTEST][GET-PARAM] " + "destroyMapedFileIntervalForcibly"); //CTEST

        return destroyMapedFileIntervalForcibly;
    }

    public void setDestroyMapedFileIntervalForcibly(int destroyMapedFileIntervalForcibly) {
        log.warn("[CTEST][SET-PARAM] " + "destroyMapedFileIntervalForcibly" + getStackTrace()); //CTEST

        this.destroyMapedFileIntervalForcibly = destroyMapedFileIntervalForcibly;
    }

    public int getFileReservedTime() {
        log.warn("[CTEST][GET-PARAM] " + "fileReservedTime"); //CTEST

        return fileReservedTime;
    }

    public void setFileReservedTime(int fileReservedTime) {
        log.warn("[CTEST][SET-PARAM] " + "fileReservedTime" + getStackTrace()); //CTEST

        this.fileReservedTime = fileReservedTime;
    }

    public int getRedeleteHangedFileInterval() {
        log.warn("[CTEST][GET-PARAM] " + "redeleteHangedFileInterval"); //CTEST

        return redeleteHangedFileInterval;
    }

    public void setRedeleteHangedFileInterval(int redeleteHangedFileInterval) {
        log.warn("[CTEST][SET-PARAM] " + "redeleteHangedFileInterval" + getStackTrace()); //CTEST

        this.redeleteHangedFileInterval = redeleteHangedFileInterval;
    }

    public int getAccessMessageInMemoryMaxRatio() {
        log.warn("[CTEST][GET-PARAM] " + "accessMessageInMemoryMaxRatio"); //CTEST

        return accessMessageInMemoryMaxRatio;
    }

    public void setAccessMessageInMemoryMaxRatio(int accessMessageInMemoryMaxRatio) {
        log.warn("[CTEST][SET-PARAM] " + "accessMessageInMemoryMaxRatio" + getStackTrace()); //CTEST

        this.accessMessageInMemoryMaxRatio = accessMessageInMemoryMaxRatio;
    }

    public boolean isMessageIndexEnable() {
        log.warn("[CTEST][GET-PARAM] " + "messageIndexEnable"); //CTEST

        return messageIndexEnable;
    }

    public void setMessageIndexEnable(boolean messageIndexEnable) {
        log.warn("[CTEST][SET-PARAM] " + "messageIndexEnable" + getStackTrace()); //CTEST

        this.messageIndexEnable = messageIndexEnable;
    }

    public int getMaxHashSlotNum() {
        log.warn("[CTEST][GET-PARAM] " + "maxHashSlotNum"); //CTEST

        return maxHashSlotNum;
    }

    public void setMaxHashSlotNum(int maxHashSlotNum) {
        log.warn("[CTEST][SET-PARAM] " + "maxHashSlotNum" + getStackTrace()); //CTEST

        this.maxHashSlotNum = maxHashSlotNum;
    }

    public int getMaxIndexNum() {
        log.warn("[CTEST][GET-PARAM] " + "maxIndexNum"); //CTEST

        return maxIndexNum;
    }

    public void setMaxIndexNum(int maxIndexNum) {
        log.warn("[CTEST][SET-PARAM] " + "maxIndexNum" + getStackTrace()); //CTEST

        this.maxIndexNum = maxIndexNum;
    }

    public int getMaxMsgsNumBatch() {
        log.warn("[CTEST][GET-PARAM] " + "maxMsgsNumBatch"); //CTEST

        return maxMsgsNumBatch;
    }

    public void setMaxMsgsNumBatch(int maxMsgsNumBatch) {
        log.warn("[CTEST][SET-PARAM] " + "maxMsgsNumBatch" + getStackTrace()); //CTEST

        this.maxMsgsNumBatch = maxMsgsNumBatch;
    }

    public int getHaListenPort() {
        log.warn("[CTEST][GET-PARAM] " + "haListenPort"); //CTEST

        return haListenPort;
    }

    public void setHaListenPort(int haListenPort) {
        if (haListenPort < 0) {
            this.haListenPort = 0;
            return;
        }
        log.warn("[CTEST][SET-PARAM] " + "haListenPort" + getStackTrace()); //CTEST

        this.haListenPort = haListenPort;
    }

    public int getHaSendHeartbeatInterval() {
        log.warn("[CTEST][GET-PARAM] " + "haSendHeartbeatInterval"); //CTEST

        return haSendHeartbeatInterval;
    }

    public void setHaSendHeartbeatInterval(int haSendHeartbeatInterval) {
        log.warn("[CTEST][SET-PARAM] " + "haSendHeartbeatInterval" + getStackTrace()); //CTEST

        this.haSendHeartbeatInterval = haSendHeartbeatInterval;
    }

    public int getHaHousekeepingInterval() {
        log.warn("[CTEST][GET-PARAM] " + "haHousekeepingInterval"); //CTEST

        return haHousekeepingInterval;
    }

    public void setHaHousekeepingInterval(int haHousekeepingInterval) {
        log.warn("[CTEST][SET-PARAM] " + "haHousekeepingInterval" + getStackTrace()); //CTEST

        this.haHousekeepingInterval = haHousekeepingInterval;
    }

    public BrokerRole getBrokerRole() {
        log.warn("[CTEST][GET-PARAM] " + "brokerRole"); //CTEST

        return brokerRole;
    }

    public void setBrokerRole(BrokerRole brokerRole) {
        log.warn("[CTEST][SET-PARAM] " + "brokerRole" + getStackTrace()); //CTEST

        this.brokerRole = brokerRole;
    }

    public void setBrokerRole(String brokerRole) {
        log.warn("[CTEST][SET-PARAM] " + "brokerRole" + getStackTrace()); //CTEST

        this.brokerRole = BrokerRole.valueOf(brokerRole);
    }

    public int getHaTransferBatchSize() {
        log.warn("[CTEST][GET-PARAM] " + "haTransferBatchSize"); //CTEST

        return haTransferBatchSize;
    }

    public void setHaTransferBatchSize(int haTransferBatchSize) {
        log.warn("[CTEST][SET-PARAM] " + "haTransferBatchSize" + getStackTrace()); //CTEST

        this.haTransferBatchSize = haTransferBatchSize;
    }

    public int getHaMaxGapNotInSync() {
        log.warn("[CTEST][GET-PARAM] " + "haMaxGapNotInSync"); //CTEST

        return haMaxGapNotInSync;
    }

    public void setHaMaxGapNotInSync(int haMaxGapNotInSync) {
        log.warn("[CTEST][SET-PARAM] " + "haMaxGapNotInSync" + getStackTrace()); //CTEST

        this.haMaxGapNotInSync = haMaxGapNotInSync;
    }

    public FlushDiskType getFlushDiskType() {
        log.warn("[CTEST][GET-PARAM] " + "flushDiskType"); //CTEST

        return flushDiskType;
    }

    public void setFlushDiskType(FlushDiskType flushDiskType) {
        log.warn("[CTEST][SET-PARAM] " + "flushDiskType" + getStackTrace()); //CTEST

        this.flushDiskType = flushDiskType;
    }

    public void setFlushDiskType(String type) {
        log.warn("[CTEST][SET-PARAM] " + "flushDiskType" + getStackTrace()); //CTEST

        this.flushDiskType = FlushDiskType.valueOf(type);
    }

    public int getSyncFlushTimeout() {
        log.warn("[CTEST][GET-PARAM] " + "syncFlushTimeout"); //CTEST

        return syncFlushTimeout;
    }

    public void setSyncFlushTimeout(int syncFlushTimeout) {
        log.warn("[CTEST][SET-PARAM] " + "syncFlushTimeout" + getStackTrace()); //CTEST

        this.syncFlushTimeout = syncFlushTimeout;
    }

    public int getPutMessageTimeout() {
        log.warn("[CTEST][GET-PARAM] " + "putMessageTimeout"); //CTEST

        return putMessageTimeout;
    }

    public void setPutMessageTimeout(int putMessageTimeout) {
        log.warn("[CTEST][SET-PARAM] " + "putMessageTimeout" + getStackTrace()); //CTEST

        this.putMessageTimeout = putMessageTimeout;
    }

    public int getSlaveTimeout() {
        log.warn("[CTEST][GET-PARAM] " + "slaveTimeout"); //CTEST

        return slaveTimeout;
    }

    public void setSlaveTimeout(int slaveTimeout) {
        log.warn("[CTEST][SET-PARAM] " + "slaveTimeout" + getStackTrace()); //CTEST

        this.slaveTimeout = slaveTimeout;
    }

    public String getHaMasterAddress() {
        log.warn("[CTEST][GET-PARAM] " + "haMasterAddress"); //CTEST

        return haMasterAddress;
    }

    public void setHaMasterAddress(String haMasterAddress) {
        log.warn("[CTEST][SET-PARAM] " + "haMasterAddress" + getStackTrace()); //CTEST

        this.haMasterAddress = haMasterAddress;
    }

    public String getMessageDelayLevel() {
        log.warn("[CTEST][GET-PARAM] " + "messageDelayLevel"); //CTEST

        return messageDelayLevel;
    }

    public void setMessageDelayLevel(String messageDelayLevel) {
        log.warn("[CTEST][SET-PARAM] " + "messageDelayLevel" + getStackTrace()); //CTEST

        this.messageDelayLevel = messageDelayLevel;
    }

    public long getFlushDelayOffsetInterval() {
        log.warn("[CTEST][GET-PARAM] " + "flushDelayOffsetInterval"); //CTEST

        return flushDelayOffsetInterval;
    }

    public void setFlushDelayOffsetInterval(long flushDelayOffsetInterval) {
        log.warn("[CTEST][SET-PARAM] " + "flushDelayOffsetInterval" + getStackTrace()); //CTEST

        this.flushDelayOffsetInterval = flushDelayOffsetInterval;
    }

    public boolean isCleanFileForciblyEnable() {
        log.warn("[CTEST][GET-PARAM] " + "cleanFileForciblyEnable"); //CTEST

        return cleanFileForciblyEnable;
    }

    public void setCleanFileForciblyEnable(boolean cleanFileForciblyEnable) {
        log.warn("[CTEST][SET-PARAM] " + "cleanFileForciblyEnable" + getStackTrace()); //CTEST

        this.cleanFileForciblyEnable = cleanFileForciblyEnable;
    }

    public boolean isMessageIndexSafe() {
        log.warn("[CTEST][GET-PARAM] " + "messageIndexSafe"); //CTEST

        return messageIndexSafe;
    }

    public void setMessageIndexSafe(boolean messageIndexSafe) {
        log.warn("[CTEST][SET-PARAM] " + "messageIndexSafe" + getStackTrace()); //CTEST

        this.messageIndexSafe = messageIndexSafe;
    }

    public boolean isFlushCommitLogTimed() {
        log.warn("[CTEST][GET-PARAM] " + "flushCommitLogTimed"); //CTEST

        return flushCommitLogTimed;
    }

    public void setFlushCommitLogTimed(boolean flushCommitLogTimed) {
        log.warn("[CTEST][SET-PARAM] " + "flushCommitLogTimed" + getStackTrace()); //CTEST

        this.flushCommitLogTimed = flushCommitLogTimed;
    }

    public String getStorePathRootDir() {
        log.warn("[CTEST][GET-PARAM] " + "storePathRootDir"); //CTEST

        return storePathRootDir;
    }

    public void setStorePathRootDir(String storePathRootDir) {
        log.warn("[CTEST][SET-PARAM] " + "storePathRootDir" + getStackTrace()); //CTEST

        this.storePathRootDir = storePathRootDir;
    }

    public int getFlushLeastPagesWhenWarmMapedFile() {
        log.warn("[CTEST][GET-PARAM] " + "flushLeastPagesWhenWarmMapedFile"); //CTEST

        return flushLeastPagesWhenWarmMapedFile;
    }

    public void setFlushLeastPagesWhenWarmMapedFile(int flushLeastPagesWhenWarmMapedFile) {
        log.warn("[CTEST][SET-PARAM] " + "flushLeastPagesWhenWarmMapedFile" + getStackTrace()); //CTEST

        this.flushLeastPagesWhenWarmMapedFile = flushLeastPagesWhenWarmMapedFile;
    }

    public boolean isOffsetCheckInSlave() {
        log.warn("[CTEST][GET-PARAM] " + "offsetCheckInSlave"); //CTEST

        return offsetCheckInSlave;
    }

    public void setOffsetCheckInSlave(boolean offsetCheckInSlave) {
        log.warn("[CTEST][SET-PARAM] " + "offsetCheckInSlave" + getStackTrace()); //CTEST

        this.offsetCheckInSlave = offsetCheckInSlave;
    }

    public int getDefaultQueryMaxNum() {
        log.warn("[CTEST][GET-PARAM] " + "defaultQueryMaxNum"); //CTEST

        return defaultQueryMaxNum;
    }

    public void setDefaultQueryMaxNum(int defaultQueryMaxNum) {
        log.warn("[CTEST][SET-PARAM] " + "defaultQueryMaxNum" + getStackTrace()); //CTEST

        this.defaultQueryMaxNum = defaultQueryMaxNum;
    }

    /**
     * Enable transient commitLog store pool only if transientStorePoolEnable is true and the FlushDiskType is
     * ASYNC_FLUSH
     *
     * @return <tt>true</tt> or <tt>false</tt>
     */
    public boolean isTransientStorePoolEnable() {
        // log.warn("[CTEST][GET-PARAM] " + "defaultQueryMaxNum"); //CTEST

        return transientStorePoolEnable && BrokerRole.SLAVE != getBrokerRole();
    }

    public void setTransientStorePoolEnable(final boolean transientStorePoolEnable) {
        log.warn("[CTEST][SET-PARAM] " + "transientStorePoolEnable" + getStackTrace()); //CTEST

        this.transientStorePoolEnable = transientStorePoolEnable;
    }

    public int getTransientStorePoolSize() {
        log.warn("[CTEST][GET-PARAM] " + "transientStorePoolSize"); //CTEST

        return transientStorePoolSize;
    }

    public void setTransientStorePoolSize(final int transientStorePoolSize) {
        log.warn("[CTEST][SET-PARAM] " + "transientStorePoolSize" + getStackTrace()); //CTEST

        this.transientStorePoolSize = transientStorePoolSize;
    }

    public int getCommitIntervalCommitLog() {
        log.warn("[CTEST][GET-PARAM] " + "commitIntervalCommitLog"); //CTEST

        return commitIntervalCommitLog;
    }

    public void setCommitIntervalCommitLog(final int commitIntervalCommitLog) {
        log.warn("[CTEST][SET-PARAM] " + "commitIntervalCommitLog" + getStackTrace()); //CTEST

        this.commitIntervalCommitLog = commitIntervalCommitLog;
    }

    public boolean isFastFailIfNoBufferInStorePool() {
        log.warn("[CTEST][GET-PARAM] " + "fastFailIfNoBufferInStorePool"); //CTEST

        return fastFailIfNoBufferInStorePool;
    }

    public void setFastFailIfNoBufferInStorePool(final boolean fastFailIfNoBufferInStorePool) {
        log.warn("[CTEST][SET-PARAM] " + "fastFailIfNoBufferInStorePool" + getStackTrace()); //CTEST

        this.fastFailIfNoBufferInStorePool = fastFailIfNoBufferInStorePool;
    }

    public boolean isUseReentrantLockWhenPutMessage() {
        log.warn("[CTEST][GET-PARAM] " + "useReentrantLockWhenPutMessage"); //CTEST

        return useReentrantLockWhenPutMessage;
    }

    public void setUseReentrantLockWhenPutMessage(final boolean useReentrantLockWhenPutMessage) {
        log.warn("[CTEST][SET-PARAM] " + "useReentrantLockWhenPutMessage" + getStackTrace()); //CTEST

        this.useReentrantLockWhenPutMessage = useReentrantLockWhenPutMessage;
    }

    public int getCommitCommitLogLeastPages() {
        log.warn("[CTEST][GET-PARAM] " + "commitCommitLogLeastPages"); //CTEST

        return commitCommitLogLeastPages;
    }

    public void setCommitCommitLogLeastPages(final int commitCommitLogLeastPages) {
        log.warn("[CTEST][SET-PARAM] " + "commitCommitLogLeastPages" + getStackTrace()); //CTEST

        this.commitCommitLogLeastPages = commitCommitLogLeastPages;
    }

    public int getCommitCommitLogThoroughInterval() {
        log.warn("[CTEST][GET-PARAM] " + "commitCommitLogThoroughInterval"); //CTEST

        return commitCommitLogThoroughInterval;
    }

    public void setCommitCommitLogThoroughInterval(final int commitCommitLogThoroughInterval) {
        log.warn("[CTEST][SET-PARAM] " + "commitCommitLogThoroughInterval" + getStackTrace()); //CTEST

        this.commitCommitLogThoroughInterval = commitCommitLogThoroughInterval;
    }

    public boolean isWakeCommitWhenPutMessage() {
        log.warn("[CTEST][GET-PARAM] " + "wakeCommitWhenPutMessage"); //CTEST

        return wakeCommitWhenPutMessage;
    }

    public void setWakeCommitWhenPutMessage(boolean wakeCommitWhenPutMessage) {
        log.warn("[CTEST][SET-PARAM] " + "wakeCommitWhenPutMessage" + getStackTrace()); //CTEST

        this.wakeCommitWhenPutMessage = wakeCommitWhenPutMessage;
    }

    public boolean isWakeFlushWhenPutMessage() {
        log.warn("[CTEST][GET-PARAM] " + "wakeFlushWhenPutMessage"); //CTEST

        return wakeFlushWhenPutMessage;
    }

    public void setWakeFlushWhenPutMessage(boolean wakeFlushWhenPutMessage) {
        log.warn("[CTEST][SET-PARAM] " + "wakeFlushWhenPutMessage" + getStackTrace()); //CTEST

        this.wakeFlushWhenPutMessage = wakeFlushWhenPutMessage;
    }

    public int getMapperFileSizeBatchConsumeQueue() {
        log.warn("[CTEST][GET-PARAM] " + "mapperFileSizeBatchConsumeQueue"); //CTEST

        return mapperFileSizeBatchConsumeQueue;
    }

    public void setMapperFileSizeBatchConsumeQueue(int mapperFileSizeBatchConsumeQueue) {
        log.warn("[CTEST][SET-PARAM] " + "mapperFileSizeBatchConsumeQueue" + getStackTrace()); //CTEST

        this.mapperFileSizeBatchConsumeQueue = mapperFileSizeBatchConsumeQueue;
    }

    public boolean isEnableCleanExpiredOffset() {
        log.warn("[CTEST][GET-PARAM] " + "enableCleanExpiredOffset"); //CTEST

        return enableCleanExpiredOffset;
    }

    public void setEnableCleanExpiredOffset(boolean enableCleanExpiredOffset) {
        log.warn("[CTEST][SET-PARAM] " + "enableCleanExpiredOffset" + getStackTrace()); //CTEST

        this.enableCleanExpiredOffset = enableCleanExpiredOffset;
    }

    public String getReadOnlyCommitLogStorePaths() {
        log.warn("[CTEST][GET-PARAM] " + "readOnlyCommitLogStorePaths"); //CTEST

        return readOnlyCommitLogStorePaths;
    }

    public void setReadOnlyCommitLogStorePaths(String readOnlyCommitLogStorePaths) {
        log.warn("[CTEST][SET-PARAM] " + "readOnlyCommitLogStorePaths" + getStackTrace()); //CTEST

        this.readOnlyCommitLogStorePaths = readOnlyCommitLogStorePaths;
    }
    public String getdLegerGroup() {
        log.warn("[CTEST][GET-PARAM] " + "dLegerGroup"); //CTEST

        return dLegerGroup;
    }

    public void setdLegerGroup(String dLegerGroup) {
        log.warn("[CTEST][SET-PARAM] " + "dLegerGroup" + getStackTrace()); //CTEST

        this.dLegerGroup = dLegerGroup;
    }

    public String getdLegerPeers() {
        log.warn("[CTEST][GET-PARAM] " + "dLegerPeers"); //CTEST

        return dLegerPeers;
    }

    public void setdLegerPeers(String dLegerPeers) {
        log.warn("[CTEST][SET-PARAM] " + "dLegerPeers" + getStackTrace()); //CTEST

        this.dLegerPeers = dLegerPeers;
    }

    public String getdLegerSelfId() {
        log.warn("[CTEST][GET-PARAM] " + "dLegerSelfId"); //CTEST

        return dLegerSelfId;
    }

    public void setdLegerSelfId(String dLegerSelfId) {
        log.warn("[CTEST][SET-PARAM] " + "dLegerSelfId" + getStackTrace()); //CTEST

        this.dLegerSelfId = dLegerSelfId;
    }

    public boolean isEnableDLegerCommitLog() {
        log.warn("[CTEST][GET-PARAM] " + "enableDLegerCommitLog"); //CTEST

        return enableDLegerCommitLog;
    }

    public void setEnableDLegerCommitLog(boolean enableDLegerCommitLog) {
        log.warn("[CTEST][SET-PARAM] " + "enableDLegerCommitLog" + getStackTrace()); //CTEST

        this.enableDLegerCommitLog = enableDLegerCommitLog;
    }

    public String getPreferredLeaderId() {
        log.warn("[CTEST][GET-PARAM] " + "preferredLeaderId"); //CTEST

        return preferredLeaderId;
    }

    public void setPreferredLeaderId(String preferredLeaderId) {
        log.warn("[CTEST][SET-PARAM] " + "preferredLeaderId" + getStackTrace()); //CTEST

        this.preferredLeaderId = preferredLeaderId;
    }

    public boolean isEnableBatchPush() {
        log.warn("[CTEST][GET-PARAM] " + "isEnableBatchPush"); //CTEST

        return isEnableBatchPush;
    }

    public void setEnableBatchPush(boolean enableBatchPush) {
        log.warn("[CTEST][SET-PARAM] " + "isEnableBatchPush" + getStackTrace()); //CTEST

        isEnableBatchPush = enableBatchPush;
    }

    public boolean isEnableScheduleMessageStats() {
        log.warn("[CTEST][GET-PARAM] " + "enableScheduleMessageStats"); //CTEST

        return enableScheduleMessageStats;
    }

    public void setEnableScheduleMessageStats(boolean enableScheduleMessageStats) {
        log.warn("[CTEST][SET-PARAM] " + "enableScheduleMessageStats" + getStackTrace()); //CTEST

        this.enableScheduleMessageStats = enableScheduleMessageStats;
    }

    public int getMaxAsyncPutMessageRequests() {
        log.warn("[CTEST][GET-PARAM] " + "maxAsyncPutMessageRequests"); //CTEST

        return maxAsyncPutMessageRequests;
    }

    public void setMaxAsyncPutMessageRequests(int maxAsyncPutMessageRequests) {
        log.warn("[CTEST][SET-PARAM] " + "maxAsyncPutMessageRequests" + getStackTrace()); //CTEST

        this.maxAsyncPutMessageRequests = maxAsyncPutMessageRequests;
    }

    public int getMaxRecoveryCommitlogFiles() {
        log.warn("[CTEST][GET-PARAM] " + "maxRecoveryCommitlogFiles"); //CTEST

        return maxRecoveryCommitlogFiles;
    }

    public void setMaxRecoveryCommitlogFiles(final int maxRecoveryCommitlogFiles) {
        log.warn("[CTEST][SET-PARAM] " + "maxRecoveryCommitlogFiles" + getStackTrace()); //CTEST

        this.maxRecoveryCommitlogFiles = maxRecoveryCommitlogFiles;
    }

    public boolean isDispatchFromSenderThread() {
        log.warn("[CTEST][GET-PARAM] " + "dispatchFromSenderThread"); //CTEST

        return dispatchFromSenderThread;
    }

    public void setDispatchFromSenderThread(boolean dispatchFromSenderThread) {
        log.warn("[CTEST][SET-PARAM] " + "dispatchFromSenderThread" + getStackTrace()); //CTEST

        this.dispatchFromSenderThread = dispatchFromSenderThread;
    }

    public int getDispatchCqThreads() {
        log.warn("[CTEST][GET-PARAM] " + "dispatchCqThreads"); //CTEST

        return dispatchCqThreads;
    }

    public void setDispatchCqThreads(final int dispatchCqThreads) {
        log.warn("[CTEST][SET-PARAM] " + "dispatchCqThreads" + getStackTrace()); //CTEST

        this.dispatchCqThreads = dispatchCqThreads;
    }

    public int getDispatchCqCacheNum() {
        log.warn("[CTEST][GET-PARAM] " + "dispatchCqCacheNum"); //CTEST

        return dispatchCqCacheNum;
    }

    public void setDispatchCqCacheNum(final int dispatchCqCacheNum) {
        log.warn("[CTEST][SET-PARAM] " + "dispatchCqCacheNum" + getStackTrace()); //CTEST

        this.dispatchCqCacheNum = dispatchCqCacheNum;
    }

    public boolean isEnableAsyncReput() {
        log.warn("[CTEST][GET-PARAM] " + "enableAsyncReput"); //CTEST

        return enableAsyncReput;
    }

    public void setEnableAsyncReput(final boolean enableAsyncReput) {
        log.warn("[CTEST][SET-PARAM] " + "enableAsyncReput" + getStackTrace()); //CTEST

        this.enableAsyncReput = enableAsyncReput;
    }

    public boolean isRecheckReputOffsetFromCq() {
        log.warn("[CTEST][GET-PARAM] " + "recheckReputOffsetFromCq"); //CTEST

        return recheckReputOffsetFromCq;
    }

    public void setRecheckReputOffsetFromCq(final boolean recheckReputOffsetFromCq) {
        log.warn("[CTEST][SET-PARAM] " + "recheckReputOffsetFromCq" + getStackTrace()); //CTEST

        this.recheckReputOffsetFromCq = recheckReputOffsetFromCq;
    }

    public long getCommitLogForceSwapMapInterval() {
        log.warn("[CTEST][GET-PARAM] " + "commitLogForceSwapMapInterval"); //CTEST

        return commitLogForceSwapMapInterval;
    }

    public void setCommitLogForceSwapMapInterval(long commitLogForceSwapMapInterval) {
        log.warn("[CTEST][SET-PARAM] " + "commitLogForceSwapMapInterval" + getStackTrace()); //CTEST

        this.commitLogForceSwapMapInterval = commitLogForceSwapMapInterval;
    }

    public int getCommitLogSwapMapReserveFileNum() {
        log.warn("[CTEST][GET-PARAM] " + "commitLogSwapMapReserveFileNum"); //CTEST

        return commitLogSwapMapReserveFileNum;
    }

    public void setCommitLogSwapMapReserveFileNum(int commitLogSwapMapReserveFileNum) {
        log.warn("[CTEST][SET-PARAM] " + "commitLogSwapMapReserveFileNum" + getStackTrace()); //CTEST

        this.commitLogSwapMapReserveFileNum = commitLogSwapMapReserveFileNum;
    }

    public long getLogicQueueForceSwapMapInterval() {
        log.warn("[CTEST][GET-PARAM] " + "logicQueueForceSwapMapInterval"); //CTEST

        return logicQueueForceSwapMapInterval;
    }

    public void setLogicQueueForceSwapMapInterval(long logicQueueForceSwapMapInterval) {
        log.warn("[CTEST][SET-PARAM] " + "logicQueueForceSwapMapInterval" + getStackTrace()); //CTEST

        this.logicQueueForceSwapMapInterval = logicQueueForceSwapMapInterval;
    }

    public int getLogicQueueSwapMapReserveFileNum() {
        log.warn("[CTEST][GET-PARAM] " + "logicQueueSwapMapReserveFileNum"); //CTEST

        return logicQueueSwapMapReserveFileNum;
    }

    public void setLogicQueueSwapMapReserveFileNum(int logicQueueSwapMapReserveFileNum) {
        log.warn("[CTEST][SET-PARAM] " + "logicQueueSwapMapReserveFileNum" + getStackTrace()); //CTEST

        this.logicQueueSwapMapReserveFileNum = logicQueueSwapMapReserveFileNum;
    }

    public long getCleanSwapedMapInterval() {
        log.warn("[CTEST][GET-PARAM] " + "cleanSwapedMapInterval"); //CTEST

        return cleanSwapedMapInterval;
    }

    public void setCleanSwapedMapInterval(long cleanSwapedMapInterval) {
        log.warn("[CTEST][SET-PARAM] " + "commitLogSwapMapInterval" + getStackTrace()); //CTEST

        this.cleanSwapedMapInterval = cleanSwapedMapInterval;
    }

    public long getCommitLogSwapMapInterval() {
        log.warn("[CTEST][GET-PARAM] " + "commitLogSwapMapInterval"); //CTEST

        return commitLogSwapMapInterval;
    }

    public void setCommitLogSwapMapInterval(long commitLogSwapMapInterval) {
        log.warn("[CTEST][SET-PARAM] " + "commitLogSwapMapInterval" + getStackTrace()); //CTEST

        this.commitLogSwapMapInterval = commitLogSwapMapInterval;
    }

    public long getLogicQueueSwapMapInterval() {
        log.warn("[CTEST][GET-PARAM] " + "logicQueueSwapMapInterval"); //CTEST

        return logicQueueSwapMapInterval;
    }

    public void setLogicQueueSwapMapInterval(long logicQueueSwapMapInterval) {
        log.warn("[CTEST][SET-PARAM] " + "logicQueueSwapMapInterval" + getStackTrace()); //CTEST

        this.logicQueueSwapMapInterval = logicQueueSwapMapInterval;
    }

    public int getMaxBatchDeleteFilesNum() {
        log.warn("[CTEST][GET-PARAM] " + "maxBatchDeleteFilesNum"); //CTEST

        return maxBatchDeleteFilesNum;
    }

    public void setMaxBatchDeleteFilesNum(int maxBatchDeleteFilesNum) {
        log.warn("[CTEST][SET-PARAM] " + "maxBatchDeleteFilesNum" + getStackTrace()); //CTEST

        this.maxBatchDeleteFilesNum = maxBatchDeleteFilesNum;
    }

    public boolean isSearchBcqByCacheEnable() {
        log.warn("[CTEST][GET-PARAM] " + "searchBcqByCacheEnable"); //CTEST

        return searchBcqByCacheEnable;
    }

    public void setSearchBcqByCacheEnable(boolean searchBcqByCacheEnable) {
        log.warn("[CTEST][SET-PARAM] " + "searchBcqByCacheEnable" + getStackTrace()); //CTEST

        this.searchBcqByCacheEnable = searchBcqByCacheEnable;
    }

    public int getDiskSpaceWarningLevelRatio() {
        log.warn("[CTEST][GET-PARAM] " + "diskSpaceWarningLevelRatio"); //CTEST

        return diskSpaceWarningLevelRatio;
    }

    public void setDiskSpaceWarningLevelRatio(int diskSpaceWarningLevelRatio) {
        log.warn("[CTEST][SET-PARAM] " + "diskSpaceWarningLevelRatio" + getStackTrace()); //CTEST

        this.diskSpaceWarningLevelRatio = diskSpaceWarningLevelRatio;
    }

    public int getDiskSpaceCleanForciblyRatio() {
        log.warn("[CTEST][GET-PARAM] " + "diskSpaceCleanForciblyRatio"); //CTEST

        return diskSpaceCleanForciblyRatio;
    }

    public void setDiskSpaceCleanForciblyRatio(int diskSpaceCleanForciblyRatio) {
        log.warn("[CTEST][SET-PARAM] " + "diskSpaceCleanForciblyRatio" + getStackTrace()); //CTEST

        this.diskSpaceCleanForciblyRatio = diskSpaceCleanForciblyRatio;
    }

    public boolean isMappedFileSwapEnable() {
        log.warn("[CTEST][GET-PARAM] " + "mappedFileSwapEnable"); //CTEST

        return mappedFileSwapEnable;
    }

    public void setMappedFileSwapEnable(boolean mappedFileSwapEnable) {
        log.warn("[CTEST][SET-PARAM] " + "mappedFileSwapEnable" + getStackTrace()); //CTEST

        this.mappedFileSwapEnable = mappedFileSwapEnable;
    }

    public int getPullBatchMaxMessageCount() {
        log.warn("[CTEST][GET-PARAM] " + "pullBatchMaxMessageCount"); //CTEST

        return pullBatchMaxMessageCount;
    }

    public void setPullBatchMaxMessageCount(int pullBatchMaxMessageCount) {
        log.warn("[CTEST][SET-PARAM] " + "pullBatchMaxMessageCount" + getStackTrace()); //CTEST

        this.pullBatchMaxMessageCount = pullBatchMaxMessageCount;
    }

    public int getDeleteFileBatchMax() {
        log.warn("[CTEST][GET-PARAM] " + "deleteFileBatchMax"); //CTEST

        return deleteFileBatchMax;
    }

    public void setDeleteFileBatchMax(int deleteFileBatchMax) {
        log.warn("[CTEST][SET-PARAM] " + "deleteFileBatchMax" + getStackTrace()); //CTEST

        this.deleteFileBatchMax = deleteFileBatchMax;
    }

    public int getTotalReplicas() {
        log.warn("[CTEST][GET-PARAM] " + "totalReplicas"); //CTEST

        return totalReplicas;
    }

    public void setTotalReplicas(int totalReplicas) {
        log.warn("[CTEST][SET-PARAM] " + "totalReplicas" + getStackTrace()); //CTEST

        this.totalReplicas = totalReplicas;
    }

    public int getInSyncReplicas() {
        log.warn("[CTEST][GET-PARAM] " + "inSyncReplicas"); //CTEST

        return inSyncReplicas;
    }

    public void setInSyncReplicas(int inSyncReplicas) {
        log.warn("[CTEST][SET-PARAM] " + "inSyncReplicas" + getStackTrace()); //CTEST

        this.inSyncReplicas = inSyncReplicas;
    }

    public int getMinInSyncReplicas() {
        log.warn("[CTEST][GET-PARAM] " + "minInSyncReplicas"); //CTEST

        return minInSyncReplicas;
    }

    public void setMinInSyncReplicas(int minInSyncReplicas) {
        log.warn("[CTEST][SET-PARAM] " + "minInSyncReplicas" + getStackTrace()); //CTEST

        this.minInSyncReplicas = minInSyncReplicas;
    }

    public boolean isAllAckInSyncStateSet() {
        log.warn("[CTEST][GET-PARAM] " + "allAckInSyncStateSet"); //CTEST

        return allAckInSyncStateSet;
    }

    public void setAllAckInSyncStateSet(boolean allAckInSyncStateSet) {
        log.warn("[CTEST][SET-PARAM] " + "allAckInSyncStateSet" + getStackTrace()); //CTEST

        this.allAckInSyncStateSet = allAckInSyncStateSet;
    }

    public boolean isEnableAutoInSyncReplicas() {
        log.warn("[CTEST][GET-PARAM] " + "enableAutoInSyncReplicas"); //CTEST

        return enableAutoInSyncReplicas;
    }

    public void setEnableAutoInSyncReplicas(boolean enableAutoInSyncReplicas) {
        log.warn("[CTEST][SET-PARAM] " + "haFlowControlEnable" + getStackTrace()); //CTEST

        this.enableAutoInSyncReplicas = enableAutoInSyncReplicas;
    }

    public boolean isHaFlowControlEnable() {
        log.warn("[CTEST][GET-PARAM] " + "haFlowControlEnable"); //CTEST

        return haFlowControlEnable;
    }

    public void setHaFlowControlEnable(boolean haFlowControlEnable) {
        log.warn("[CTEST][SET-PARAM] " + "haFlowControlEnable" + getStackTrace()); //CTEST

        this.haFlowControlEnable = haFlowControlEnable;
    }

    public long getMaxHaTransferByteInSecond() {
        log.warn("[CTEST][GET-PARAM] " + "maxHaTransferByteInSecond"); //CTEST

        return maxHaTransferByteInSecond;
    }

    public void setMaxHaTransferByteInSecond(long maxHaTransferByteInSecond) {
        log.warn("[CTEST][SET-PARAM] " + "maxHaTransferByteInSecond" + getStackTrace()); //CTEST

        this.maxHaTransferByteInSecond = maxHaTransferByteInSecond;
    }

    public long getHaMaxTimeSlaveNotCatchup() {
        log.warn("[CTEST][GET-PARAM] " + "haMaxTimeSlaveNotCatchup"); //CTEST

        return haMaxTimeSlaveNotCatchup;
    }

    public void setHaMaxTimeSlaveNotCatchup(long haMaxTimeSlaveNotCatchup) {
        log.warn("[CTEST][SET-PARAM] " + "haMaxTimeSlaveNotCatchup" + getStackTrace()); //CTEST

        this.haMaxTimeSlaveNotCatchup = haMaxTimeSlaveNotCatchup;
    }

    public boolean isSyncMasterFlushOffsetWhenStartup() {
        log.warn("[CTEST][GET-PARAM] " + "syncMasterFlushOffsetWhenStartup"); //CTEST

        return syncMasterFlushOffsetWhenStartup;
    }

    public void setSyncMasterFlushOffsetWhenStartup(boolean syncMasterFlushOffsetWhenStartup) {
        log.warn("[CTEST][SET-PARAM] " + "syncMasterFlushOffsetWhenStartup" + getStackTrace()); //CTEST

        this.syncMasterFlushOffsetWhenStartup = syncMasterFlushOffsetWhenStartup;
    }

    public long getMaxChecksumRange() {
        log.warn("[CTEST][GET-PARAM] " + "maxChecksumRange"); //CTEST

        return maxChecksumRange;
    }

    public void setMaxChecksumRange(long maxChecksumRange) {
        log.warn("[CTEST][SET-PARAM] " + "maxChecksumRange" + getStackTrace()); //CTEST

        this.maxChecksumRange = maxChecksumRange;
    }

    public int getReplicasPerDiskPartition() {
        log.warn("[CTEST][GET-PARAM] " + "replicasPerDiskPartition"); //CTEST

        return replicasPerDiskPartition;
    }

    public void setReplicasPerDiskPartition(int replicasPerDiskPartition) {
        log.warn("[CTEST][SET-PARAM] " + "replicasPerDiskPartition" + getStackTrace()); //CTEST

        this.replicasPerDiskPartition = replicasPerDiskPartition;
    }

    public double getLogicalDiskSpaceCleanForciblyThreshold() {
        log.warn("[CTEST][GET-PARAM] " + "logicalDiskSpaceCleanForciblyThreshold"); //CTEST

        return logicalDiskSpaceCleanForciblyThreshold;
    }

    public void setLogicalDiskSpaceCleanForciblyThreshold(double logicalDiskSpaceCleanForciblyThreshold) {
        log.warn("[CTEST][SET-PARAM] " + "logicalDiskSpaceCleanForciblyThreshold" + getStackTrace()); //CTEST

        this.logicalDiskSpaceCleanForciblyThreshold = logicalDiskSpaceCleanForciblyThreshold;
    }

    public int getDisappearTimeAfterStart() {
        log.warn("[CTEST][GET-PARAM] " + "disappearTimeAfterStart"); //CTEST

        return disappearTimeAfterStart;
    }

    public void setDisappearTimeAfterStart(int disappearTimeAfterStart) {
        log.warn("[CTEST][SET-PARAM] " + "disappearTimeAfterStart" + getStackTrace()); //CTEST

        this.disappearTimeAfterStart = disappearTimeAfterStart;
    }

    public long getMaxSlaveResendLength() {
        log.warn("[CTEST][GET-PARAM] " + "maxSlaveResendLength"); //CTEST

        return maxSlaveResendLength;
    }

    public void setMaxSlaveResendLength(long maxSlaveResendLength) {
        this.maxSlaveResendLength = maxSlaveResendLength;
    }

    public boolean isSyncFromLastFile() {
        log.warn("[CTEST][GET-PARAM] " + "syncFromLastFile"); //CTEST

        return syncFromLastFile;
    }

    public void setSyncFromLastFile(boolean syncFromLastFile) {
        log.warn("[CTEST][SET-PARAM] " + "syncFromLastFile" + getStackTrace()); //CTEST

        this.syncFromLastFile = syncFromLastFile;
    }

    public boolean isEnableLmq() {
        log.warn("[CTEST][GET-PARAM] " + "enableLmq"); //CTEST

        return enableLmq;
    }

    public void setEnableLmq(boolean enableLmq) {
        log.warn("[CTEST][SET-PARAM] " + "enableLmq" + getStackTrace()); //CTEST

        this.enableLmq = enableLmq;
    }

    public boolean isEnableMultiDispatch() {
        log.warn("[CTEST][GET-PARAM] " + "enableMultiDispatch"); //CTEST

        return enableMultiDispatch;
    }

    public void setEnableMultiDispatch(boolean enableMultiDispatch) {
        log.warn("[CTEST][SET-PARAM] " + "enableMultiDispatch" + getStackTrace()); //CTEST

        this.enableMultiDispatch = enableMultiDispatch;
    }

    public int getMaxLmqConsumeQueueNum() {
        log.warn("[CTEST][GET-PARAM] " + "maxLmqConsumeQueueNum"); //CTEST

        return maxLmqConsumeQueueNum;
    }

    public void setMaxLmqConsumeQueueNum(int maxLmqConsumeQueueNum) {
        log.warn("[CTEST][SET-PARAM] " + "maxLmqConsumeQueueNum" + getStackTrace()); //CTEST

        this.maxLmqConsumeQueueNum = maxLmqConsumeQueueNum;
    }

    public boolean isEnableScheduleAsyncDeliver() {
        log.warn("[CTEST][GET-PARAM] " + "enableScheduleAsyncDeliver"); //CTEST

        return enableScheduleAsyncDeliver;
    }

    public void setEnableScheduleAsyncDeliver(boolean enableScheduleAsyncDeliver) {
        log.warn("[CTEST][SET-PARAM] " + "enableScheduleAsyncDeliver" + getStackTrace()); //CTEST

        this.enableScheduleAsyncDeliver = enableScheduleAsyncDeliver;
    }

    public int getScheduleAsyncDeliverMaxPendingLimit() {
        log.warn("[CTEST][GET-PARAM] " + "scheduleAsyncDeliverMaxPendingLimit"); //CTEST

        return scheduleAsyncDeliverMaxPendingLimit;
    }

    public void setScheduleAsyncDeliverMaxPendingLimit(int scheduleAsyncDeliverMaxPendingLimit) {
        log.warn("[CTEST][SET-PARAM] " + "scheduleAsyncDeliverMaxPendingLimit" + getStackTrace()); //CTEST

        this.scheduleAsyncDeliverMaxPendingLimit = scheduleAsyncDeliverMaxPendingLimit;
    }

    public int getScheduleAsyncDeliverMaxResendNum2Blocked() {
        log.warn("[CTEST][GET-PARAM] " + "scheduleAsyncDeliverMaxResendNum2Blocked"); //CTEST

        return scheduleAsyncDeliverMaxResendNum2Blocked;
    }

    public void setScheduleAsyncDeliverMaxResendNum2Blocked(int scheduleAsyncDeliverMaxResendNum2Blocked) {
        log.warn("[CTEST][SET-PARAM] " + "scheduleAsyncDeliverMaxResendNum2Blocked" + getStackTrace()); //CTEST

        this.scheduleAsyncDeliverMaxResendNum2Blocked = scheduleAsyncDeliverMaxResendNum2Blocked;
    }

    public boolean isAsyncLearner() {
        log.warn("[CTEST][GET-PARAM] " + "asyncLearner"); //CTEST

        return asyncLearner;
    }

    public void setAsyncLearner(boolean asyncLearner) {
        log.warn("[CTEST][SET-PARAM] " + "asyncLearner" + getStackTrace()); //CTEST

        this.asyncLearner = asyncLearner;
    }

    public int getMappedFileSizeTimerLog() {
        log.warn("[CTEST][GET-PARAM] " + "mappedFileSizeTimerLog"); //CTEST

        return mappedFileSizeTimerLog;
    }
    public void setMappedFileSizeTimerLog(final int mappedFileSizeTimerLog) {
        log.warn("[CTEST][SET-PARAM] " + "mappedFileSizeTimerLog" + getStackTrace()); //CTEST

        this.mappedFileSizeTimerLog = mappedFileSizeTimerLog;
    }

    public int getTimerPrecisionMs() {
        log.warn("[CTEST][GET-PARAM] " + "timerPrecisionMs"); //CTEST

        return timerPrecisionMs;
    }
    public void setTimerPrecisionMs(int timerPrecisionMs) {
        int[] candidates = {100, 200, 500, 1000};
        for (int i = 1; i < candidates.length; i++) {
            if (timerPrecisionMs < candidates[i]) {
                this.timerPrecisionMs = candidates[i - 1];
                return;
            }
        }
        log.warn("[CTEST][SET-PARAM] " + "timerPrecisionMs" + getStackTrace()); //CTEST

        this.timerPrecisionMs = candidates[candidates.length - 1];
    }
    public int getTimerRollWindowSlot() {
        log.warn("[CTEST][GET-PARAM] " + "timerRollWindowSlot"); //CTEST

        return timerRollWindowSlot;
    }
    public int getTimerGetMessageThreadNum() {
        log.warn("[CTEST][GET-PARAM] " + "timerGetMessageThreadNum"); //CTEST

        return timerGetMessageThreadNum;
    }

    public void setTimerGetMessageThreadNum(int timerGetMessageThreadNum) {
        log.warn("[CTEST][SET-PARAM] " + "timerGetMessageThreadNum" + getStackTrace()); //CTEST

        this.timerGetMessageThreadNum = timerGetMessageThreadNum;
    }

    public int getTimerPutMessageThreadNum() {
        log.warn("[CTEST][GET-PARAM] " + "timerPutMessageThreadNum"); //CTEST

        return timerPutMessageThreadNum;
    }
    public void setTimerPutMessageThreadNum(int timerPutMessageThreadNum) {
        log.warn("[CTEST][SET-PARAM] " + "timerPutMessageThreadNum" + getStackTrace()); //CTEST

        this.timerPutMessageThreadNum = timerPutMessageThreadNum;
    }
    public boolean isTimerEnableDisruptor() {
        log.warn("[CTEST][GET-PARAM] " + "timerEnableDisruptor"); //CTEST

        return timerEnableDisruptor;
    }

    public boolean isTimerEnableCheckMetrics() {
        log.warn("[CTEST][GET-PARAM] " + "timerEnableCheckMetrics"); //CTEST

        return timerEnableCheckMetrics;
    }

    public void setTimerEnableCheckMetrics(boolean timerEnableCheckMetrics) {
        log.warn("[CTEST][SET-PARAM] " + "timerEnableCheckMetrics" + getStackTrace()); //CTEST

        this.timerEnableCheckMetrics = timerEnableCheckMetrics;
    }
    public boolean isTimerStopEnqueue() {
        log.warn("[CTEST][GET-PARAM] " + "timerStopEnqueue"); //CTEST

        return timerStopEnqueue;
    }
    public void setTimerStopEnqueue(boolean timerStopEnqueue) {
        log.warn("[CTEST][SET-PARAM] " + "timerStopEnqueue" + getStackTrace()); //CTEST

        this.timerStopEnqueue = timerStopEnqueue;
    }
    public String getTimerCheckMetricsWhen() {
        log.warn("[CTEST][GET-PARAM] " + "timerCheckMetricsWhen"); //CTEST

        return timerCheckMetricsWhen;
    }

    public boolean isTimerSkipUnknownError() {
        log.warn("[CTEST][GET-PARAM] " + "timerSkipUnknownError"); //CTEST

        return timerSkipUnknownError;
    }

    public boolean isTimerWarmEnable() {
        log.warn("[CTEST][GET-PARAM] " + "timerWarmEnable"); //CTEST

        return timerWarmEnable;
    }

    public  boolean isTimerWheelEnable() {
        log.warn("[CTEST][GET-PARAM] " + "timerWheelEnable"); //CTEST

        return timerWheelEnable;
    }
    public void setTimerWheelEnable(boolean timerWheelEnable) {
        log.warn("[CTEST][SET-PARAM] " + "timerWheelEnable" + getStackTrace()); //CTEST

        this.timerWheelEnable = timerWheelEnable;
    }

    public boolean isTimerStopDequeue() {
        log.warn("[CTEST][GET-PARAM] " + "timerStopDequeue"); //CTEST

        return timerStopDequeue;
    }

    public int getTimerMetricSmallThreshold() {
        log.warn("[CTEST][GET-PARAM] " + "timerMetricSmallThreshold"); //CTEST

        return timerMetricSmallThreshold;
    }

    public void setTimerMetricSmallThreshold(int timerMetricSmallThreshold) {
        log.warn("[CTEST][SET-PARAM] " + "timerMetricSmallThreshold" + getStackTrace()); //CTEST

        this.timerMetricSmallThreshold = timerMetricSmallThreshold;
    }

    public int getTimerCongestNumEachSlot() {
        log.warn("[CTEST][GET-PARAM] " + "timerCongestNumEachSlot"); //CTEST

        return timerCongestNumEachSlot;
    }
    public void setTimerCongestNumEachSlot(int timerCongestNumEachSlot) {
        // In order to get this value from messageStoreConfig properties file created before v4.4.1.
        log.warn("[CTEST][SET-PARAM] " + "timerCongestNumEachSlot" + getStackTrace()); //CTEST

        this.timerCongestNumEachSlot = timerCongestNumEachSlot;
    }
    public int getTimerFlushIntervalMs() {
        log.warn("[CTEST][GET-PARAM] " + "timerFlushIntervalMs"); //CTEST

        return timerFlushIntervalMs;
    }

    public void setTimerFlushIntervalMs(final int timerFlushIntervalMs) {
        log.warn("[CTEST][SET-PARAM] " + "timerFlushIntervalMs" + getStackTrace()); //CTEST

        this.timerFlushIntervalMs = timerFlushIntervalMs;
    }
    public void setTimerRollWindowSlot(final int timerRollWindowSlot) {
        log.warn("[CTEST][SET-PARAM] " + "timerRollWindowSlot" + getStackTrace()); //CTEST

        this.timerRollWindowSlot = timerRollWindowSlot;
    }
    public int getTimerProgressLogIntervalMs() {
        log.warn("[CTEST][GET-PARAM] " + "timerProgressLogIntervalMs"); //CTEST

        return timerProgressLogIntervalMs;
    }

    public void setTimerProgressLogIntervalMs(final int timerProgressLogIntervalMs) {
        log.warn("[CTEST][SET-PARAM] " + "timerProgressLogIntervalMs" + getStackTrace()); //CTEST

        this.timerProgressLogIntervalMs = timerProgressLogIntervalMs;
    }

    public boolean isTimerInterceptDelayLevel() {
        log.warn("[CTEST][GET-PARAM] " + "timerInterceptDelayLevel"); //CTEST

        return timerInterceptDelayLevel;
    }

    public void setTimerInterceptDelayLevel(boolean timerInterceptDelayLevel) {
        log.warn("[CTEST][SET-PARAM] " + "timerInterceptDelayLevel" + getStackTrace()); //CTEST

        this.timerInterceptDelayLevel = timerInterceptDelayLevel;
    }

    public int getTimerMaxDelaySec() {
        log.warn("[CTEST][GET-PARAM] " + "timerMaxDelaySec"); //CTEST

        return timerMaxDelaySec;
    }
    public void setTimerMaxDelaySec(final int timerMaxDelaySec) {
        log.warn("[CTEST][SET-PARAM] " + "timerMaxDelaySec" + getStackTrace()); //CTEST

        this.timerMaxDelaySec = timerMaxDelaySec;
    }


}
