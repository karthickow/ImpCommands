@echo off

REM echo def qcf(jms/mdpSampleQCF) qmgr(QM.FOR.MDP) TRANSPORT(CLIENT) channel(MY_SVR_CON) > ivtsetup.log
echo define qcf(jms/mdpSampleQCF) qmgr(QM.FOR.MDP) TRANSPORT(CLIENT) CHANNEL(MY_SVR_CON) host(localhost) port(1415) > ivtsetup.log
echo def q(jms/mdpSampleQueue) qmgr(QM.FOR.MDP) qu(REQUEST.QUEUE.FOR.MDP)  >> ivtsetup.log
echo end >> ivtsetup.log

echo Calling JMSAdmin ......
java -Djava.library.path="%MQ_JAVA_LIB_PATH%"  -DMQJMS_LOG_DIR="%MQ_JAVA_DATA_PATH%"\log -DMQJMS_TRACE_DIR="%MQ_JAVA_DATA_PATH%"\errors -DMQJMS_INSTALL_PATH="%MQ_JAVA_INSTALL_PATH%" com.ibm.mq.jms.admin.JMSAdmin < ivtsetup.log

echo JMS Administration completed