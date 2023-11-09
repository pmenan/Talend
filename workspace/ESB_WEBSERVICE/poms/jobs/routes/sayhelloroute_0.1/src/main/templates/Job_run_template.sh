#!/bin/sh
cd `dirname $0`
ROOT_PATH=`pwd`
java -Dtalend.component.manager.m2.repository=$ROOT_PATH/../lib -Xms256M -Xmx1024M -Dfile.encoding=UTF-8 -cp .:$ROOT_PATH:$ROOT_PATH/../lib/routines.jar:$ROOT_PATH/../lib/beans.jar:$ROOT_PATH/../lib/log4j-slf4j-impl-2.12.1.jar:$ROOT_PATH/../lib/log4j-api-2.12.1.jar:$ROOT_PATH/../lib/log4j-core-2.12.1.jar:$ROOT_PATH/../lib/cxf-rt-transports-http-jetty-3.3.4.jar:$ROOT_PATH/../lib/geronimo-ws-metadata_2.0_spec-1.1.3:$ROOT_PATH/../lib/camel-http-common-2.24.2.jar:$ROOT_PATH/../lib/camel-blueprint-2.24.2.jar:$ROOT_PATH/../lib/camel-cxf-transport-2.24.2.jar:$ROOT_PATH/../lib/spring-web-5.1.4.RELEASE.jar:$ROOT_PATH/../lib/cxf-rt-wsdl-3.3.4.jar:$ROOT_PATH/../lib/jakarta.xml.bind-api-2.3.2:$ROOT_PATH/../lib/jakarta.xml.soap-api-1.4.1:$ROOT_PATH/../lib/aopalliance-1.0.jar:$ROOT_PATH/../lib/commons-logging-1.2.0.jar:$ROOT_PATH/../lib/camel-spring-2.24.2.jar:$ROOT_PATH/../lib/cxf-rt-bindings-soap-3.3.4.jar:$ROOT_PATH/../lib/cxf-rt-features-logging-3.3.4.jar:$ROOT_PATH/../lib/cxf-rt-ws-policy-3.3.4.jar:$ROOT_PATH/../lib/spring-expression-5.1.4.RELEASE.jar:$ROOT_PATH/../lib/cxf-rt-rs-service-description-3.3.4.jar:$ROOT_PATH/../lib/cxf-rt-frontend-jaxrs-3.3.4.jar:$ROOT_PATH/../lib/spring-aop-5.1.4.RELEASE.jar:$ROOT_PATH/../lib/spring-context-5.1.4.RELEASE.jar:$ROOT_PATH/../lib/spring-webmvc-5.1.4.RELEASE.jar:$ROOT_PATH/../lib/camel-cxf-2.24.2.jar:$ROOT_PATH/../lib/spring-core-5.1.4.RELEASE.jar:$ROOT_PATH/../lib/jetty-all-9.4.18.v20190429-uber.jar:$ROOT_PATH/../lib/cxf-rt-rs-client-3.3.4.jar:$ROOT_PATH/../lib/jakarta.xml.bind-api.jar:$ROOT_PATH/../lib/jettison-1.4.0.jar:$ROOT_PATH/../lib/cxf-rt-rs-extension-providers-3.3.4.jar:$ROOT_PATH/../lib/cxf-rt-bindings-xml-3.3.4.jar:$ROOT_PATH/../lib/javax.ws.rs-api-2.1.jar:$ROOT_PATH/../lib/cxf-rt-databinding-jaxb-3.3.4.jar:$ROOT_PATH/../lib/cxf-core-3.3.4.jar:$ROOT_PATH/../lib/cxf-rt-frontend-jaxws-3.3.4.jar:$ROOT_PATH/../lib/javax.wsdl_1.6.2.v201012040545.jar:$ROOT_PATH/../lib/cxf-rt-ws-addr-3.3.4.jar:$ROOT_PATH/../lib/org.apache.xml.resolver_1.2.0.v201005080400.jar:$ROOT_PATH/../lib/javax.activation-1.2.0.jar:$ROOT_PATH/../lib/camel-core-2.24.2.jar:$ROOT_PATH/../lib/cxf-rt-frontend-simple-3.3.4.jar:$ROOT_PATH/../lib/xmlschema-core-2.2.4.jar:$ROOT_PATH/../lib/javax.annotation_1.2.0.v201602091430.jar:$ROOT_PATH/../lib/stax-api-1.0-2.jar:$ROOT_PATH/../lib/spring-beans-5.1.4.RELEASE.jar:$ROOT_PATH/../lib/woodstox-core-asl-4.4.1.jar:$ROOT_PATH/../lib/stax2-api-3.1.4.jar:$ROOT_PATH/../lib/cxf-rt-transports-http-3.3.4.jar:$ROOT_PATH/../lib/spring-tx-5.1.4.RELEASE.jar:$ROOT_PATH/../lib/neethi-3.1.1.jar:$ROOT_PATH/../lib/slf4j-api-1.7.25.jar:$ROOT_PATH/../lib/jakarta.xml.ws-api.jar:$ROOT_PATH/../lib/dom4j-2.1.1.jar:$ROOT_PATH/../lib/geronimo-ws-metadata_2.0_spec.jar:$ROOT_PATH/../lib/crypto-utils.jar:$ROOT_PATH/sayhelloroute_0_1.jar: esb_webservice.sayhelloroute_0_1.SayHelloRoute  "$@"