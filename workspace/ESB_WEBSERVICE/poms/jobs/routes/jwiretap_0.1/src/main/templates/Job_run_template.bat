%~d0
cd %~dp0
java -Dtalend.component.manager.m2.repository="%cd%/../lib" -Xms256M -Xmx1024M -Dfile.encoding=UTF-8 -cp .;../lib/routines.jar;../lib/beans.jar;../lib/log4j-slf4j-impl-2.12.1.jar;../lib/log4j-api-2.12.1.jar;../lib/log4j-core-2.12.1.jar;../lib/camel-spring-2.24.2.jar;../lib/javax.activation-1.2.0.jar;../lib/javax.ws.rs-api-2.1.jar;../lib/camel-core-2.24.2.jar;../lib/spring-aop-5.1.4.RELEASE.jar;../lib/spring-beans-5.1.4.RELEASE.jar;../lib/spring-context-5.1.4.RELEASE.jar;../lib/spring-tx-5.1.4.RELEASE.jar;../lib/geronimo-ws-metadata_2.0_spec-1.1.3;../lib/spring-web-5.1.4.RELEASE.jar;../lib/aopalliance-1.0.jar;../lib/camel-cxf-2.24.2.jar;../lib/cxf-core-3.3.4.jar;../lib/spring-webmvc-5.1.4.RELEASE.jar;../lib/jakarta.xml.soap-api-1.4.1;../lib/spring-expression-5.1.4.RELEASE.jar;../lib/camel-blueprint-2.24.2.jar;../lib/commons-logging-1.2.0.jar;../lib/spring-core-5.1.4.RELEASE.jar;../lib/slf4j-api-1.7.25.jar;../lib/dom4j-2.1.1.jar;../lib/crypto-utils.jar;jwiretap_0_1.jar; esb_webservice.jwiretap_0_1.jWireTap  %*