#!/bin/sh
cd `dirname $0`
ROOT_PATH=`pwd`
java -Dtalend.component.manager.m2.repository=$ROOT_PATH/../lib -Xms256M -Xmx1024M -Dfile.encoding=UTF-8 -cp .:$ROOT_PATH:$ROOT_PATH/../lib/routines.jar:$ROOT_PATH/../lib/beans.jar:$ROOT_PATH/../lib/log4j-slf4j-impl-2.12.1.jar:$ROOT_PATH/../lib/log4j-api-2.12.1.jar:$ROOT_PATH/../lib/log4j-core-2.12.1.jar:$ROOT_PATH/../lib/commons-collections-3.2.2.jar:$ROOT_PATH/../lib/commons-codec-1.6.jar:$ROOT_PATH/../lib/camel-core-2.24.2.jar:$ROOT_PATH/../lib/aopalliance-1.0.jar:$ROOT_PATH/../lib/spring-beans-5.1.4.RELEASE.jar:$ROOT_PATH/../lib/cxf-core-3.3.4.jar:$ROOT_PATH/../lib/commons-io-2.6.jar:$ROOT_PATH/../lib/jakarta.xml.soap-api.jar:$ROOT_PATH/../lib/commons-httpclient-3.1.jar:$ROOT_PATH/../lib/camel-spring-2.24.2.jar:$ROOT_PATH/../lib/spring-context-5.1.4.RELEASE.jar:$ROOT_PATH/../lib/commons-logging-1.2.0.jar:$ROOT_PATH/../lib/geronimo-ws-metadata_2.0_spec-1.1.3:$ROOT_PATH/../lib/commons-pool-1.6.jar:$ROOT_PATH/../lib/javax.ws.rs-api-2.1.jar:$ROOT_PATH/../lib/spring-core-5.1.4.RELEASE.jar:$ROOT_PATH/../lib/jakarta.xml.soap-api-1.4.1:$ROOT_PATH/../lib/camel-cxf-2.24.2.jar:$ROOT_PATH/../lib/commons-net-3.3.jar:$ROOT_PATH/../lib/spring-expression-5.1.4.RELEASE.jar:$ROOT_PATH/../lib/spring-web-5.1.4.RELEASE.jar:$ROOT_PATH/../lib/commons-lang-2.6.jar:$ROOT_PATH/../lib/spring-aop-5.1.4.RELEASE.jar:$ROOT_PATH/../lib/spring-webmvc-5.1.4.RELEASE.jar:$ROOT_PATH/../lib/camel-blueprint-2.24.2.jar:$ROOT_PATH/../lib/javax.activation-1.2.0.jar:$ROOT_PATH/../lib/spring-tx-5.1.4.RELEASE.jar:$ROOT_PATH/../lib/slf4j-api-1.7.25.jar:$ROOT_PATH/../lib/dom4j-2.1.1.jar:$ROOT_PATH/../lib/crypto-utils.jar:$ROOT_PATH/jmulticast_0_1.jar: esb_webservice.jmulticast_0_1.jMultiCast  "$@"