// ============================================================================
//
// Copyright (c) 2006-2015, Talend Inc.
//
// This source code has been automatically generated by_Talend Open Studio for ESB
// / Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
// http://www.apache.org/licenses/LICENSE-2.0
// 
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package esb_projects.cfilehandling_0_1;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.apache.camel.CamelContext;
import routines.*;
import routines.TalendString;
import routines.system.*;
import routines.system.api.TalendESBRoute;

/**
 * Job: cFileHandling Purpose: Handling file proporties during routing<br>
 * Description: Handling file proporties during routing <br>
 * 
 * @author user@talend.com
 * @version 7.3.1.20200219_1130
 * @status
 */

public class cFileHandling extends org.apache.camel.builder.RouteBuilder implements TalendESBRoute {

	private boolean inOSGi = routines.system.BundleUtils.inOSGi();

	private boolean inMS = false;

	private static Object arguments;

	public void setArguments(Object arguments) {
		this.inOSGi = true;
		cFileHandling.arguments = arguments;

		if (null != context && null != context.getProperty("context")) {
			contextStr = (String) context.getProperty("context");
		}

	}

	public <T> Map<String, T> getArguments(Object props, Class c) {
		if (props != null) {
			try {
				java.lang.reflect.Field propertiesField = props.getClass().getDeclaredField("properties");
				propertiesField.setAccessible(true);
				java.util.Dictionary p = (java.util.Dictionary) propertiesField.get(props);
				java.util.Map<String, T> result = new java.util.HashMap<>();

				if (p != null) {
					for (java.util.Enumeration<String> keys = p.keys(); keys.hasMoreElements();) {
						String key = keys.nextElement();
						if (c.equals(String.class)) {
							result.put(key, (T) String.valueOf(p.get(key)));
						} else {
							result.put(key, (T) p.get(key));
						}

					}
				}

				return result;

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	private String propertyToString(Object obj) {
		if (obj != null && obj instanceof java.util.Date) {
			return String.format("yyyy-MM-dd HH:mm:ss;%tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS", obj);
		} else {
			return String.valueOf(obj);
		}
	}

	@Override
	public void configure() throws java.lang.Exception {
		doConfig();
	}

	public void doConfig() throws java.lang.Exception {
		final /* org.apache.camel.model.Model */CamelContext camelContext = getContext();

		final org.apache.camel.impl.SimpleRegistry registry = new org.apache.camel.impl.SimpleRegistry();
		final org.apache.camel.impl.CompositeRegistry compositeRegistry = new org.apache.camel.impl.CompositeRegistry();
		compositeRegistry.addRegistry(camelContext.getRegistry());
		compositeRegistry.addRegistry(registry);
		((org.apache.camel.impl.DefaultCamelContext) camelContext).setRegistry(compositeRegistry);

		if (inOSGi) {

		}

		// read context values
		readContextValues(contextStr);

		from("file://D:/git_repo/Talend/inputFolder/ESB" + "?noop=false" + "&autoCreate=true" + "&flatten=false"
				+ "&bufferSize=128")
						.routeId("cFileHandling_cFile_1")
						.log(org.apache.camel.LoggingLevel.WARN, "cFileHandling.cLog_1",
								"Nom fichier : ${in.header.CamelFileName}")

						.id("cFileHandling_cLog_1");
	}

	private org.apache.camel.main.Main main;

	private void run() throws java.lang.Exception {
		main = new org.apache.camel.main.Main() {

			protected CamelContext createContext() {
				final org.apache.camel.impl.DefaultCamelContext camelContext = new org.apache.camel.spring.SpringCamelContext(
						new org.springframework.context.support.ClassPathXmlApplicationContext(
								"META-INF/spring/cfilehandling.xml"));
				camelContext.setName("cFileHandling");
				// add notifier
				java.util.Collection<org.apache.camel.management.JmxNotificationEventNotifier> jmxEventNotifiers = camelContext
						.getRegistry().findByType(org.apache.camel.management.JmxNotificationEventNotifier.class);
				if (jmxEventNotifiers != null && !jmxEventNotifiers.isEmpty()) {
					camelContext.getManagementStrategy().addEventNotifier(jmxEventNotifiers.iterator().next());
				}
				// add statistics which shows on the connection
				final routines.system.RunStat runStat = new routines.system.RunStat();
				runStat.openSocket(true);
				runStat.setAllPID(pid, pid, pid, "cFileHandling");
				try {
					runStat.startThreadStat(clientHost, portStats);
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
				runStat.updateStatOnJob(routines.system.RunStat.JOBSTART, null);

				final Map<String, String> targetNodeToConnectionMap = new HashMap<String, String>();
				targetNodeToConnectionMap.put("cFileHandling_cLog_1", "route1");
				for (String connection : targetNodeToConnectionMap.values()) {
					runStat.updateStatOnConnection(connection, routines.system.RunStat.BEGIN, 0);
				}
				camelContext.addInterceptStrategy(new org.apache.camel.spi.InterceptStrategy() {
					public org.apache.camel.Processor wrapProcessorInInterceptors(CamelContext context,
							final org.apache.camel.model.ProcessorDefinition<?> node,
							final org.apache.camel.Processor target, org.apache.camel.Processor nextTarget)
							throws Exception {
						return new org.apache.camel.processor.DelegateAsyncProcessor(target) {
							public boolean process(org.apache.camel.Exchange exchange,
									org.apache.camel.AsyncCallback callback) {
								final String connection = targetNodeToConnectionMap.get(node.getId());
								if (null != connection) {
									runStat.updateStatOnConnection(targetNodeToConnectionMap.get(node.getId()),
											routines.system.RunStat.RUNNING, 1);
								}
								return super.process(exchange, callback);
							}
						};
					}
				});
				return camelContext;
			}
		};

		// add route
		main.addRouteBuilder(this);

		main.run();
	}

	public void stop() throws java.lang.Exception {
		if (main != null) {
			main.stop();
		}
	}

	public void shutdown() throws java.lang.Exception {
		if (main != null) {
			main.shutdown();
		}
	}

	private final ContextProperties context = new ContextProperties();

	public static class ContextProperties extends Properties {

		public String getProperty(String key) {

			String rs = null;

			if (arguments != null) {
				try {

					if (arguments instanceof java.util.HashMap) {
						rs = (String) ((java.util.HashMap) arguments).get(key);
					} else {

						java.lang.reflect.Method getProperty = arguments.getClass().getDeclaredMethod("getProperty",
								new Class[] { String.class });
						getProperty.setAccessible(true);

						rs = (String) getProperty.invoke(arguments, key);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			if (rs == null) {
				rs = super.getProperty(key);
			}

			return rs;
		}

		public void synchronizeContext() {
		}

	}

	private static String contextStr = "Default";

	public void setContextName(String contextName) {
		contextStr = contextName;
	}

	private int portStats = -1;
	private String clientHost = "localhost";
	private String pid;

	private final Properties context_param = new Properties();

	public static void main(String[] args) {
		int exitCode = new cFileHandling().runJobInTOS(args);
		if (exitCode != 0) {
			System.exit(exitCode);
		}
	}

	@Override
	public String[][] runJob(String[] args) {
		int exitCode = runJobInTOS(args);
		return new String[][] { { Integer.toString(exitCode) } };
	}

	@Override
	public int runJobInTOS(String[] args) {
		String lastStr = "";
		for (String arg : args) {
			if (arg.equalsIgnoreCase("--context_param")) {
				lastStr = arg;
			} else if (lastStr.equals("")) {
				evalParam(arg);
			} else {
				evalParam(lastStr + " " + arg);
				lastStr = "";
			}
		}

		if (pid == null) {
			pid = TalendString.getAsciiRandomString(6);
		}
		try {
			run();
		} catch (java.lang.Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
			return 1;
		}
		return 0;
	}

	private boolean contextValuesRead = false;

	/**
	 * read context values from specified context
	 * 
	 * @parameter contextName : the name of context while will be used
	 */
	private void readContextValues(String contextName) {
		if (contextValuesRead) {
			return;
		}
		contextValuesRead = true;
		try {
			java.io.InputStream inContext = cFileHandling.class.getClassLoader()
					.getResourceAsStream("esb_projects/cfilehandling_0_1/contexts/" + contextName + ".properties");

			if (inContext != null) {
				context.load(inContext);
				inContext.close();
			} else {
				// print info and job continue to run, for case: context_param is not empty.
				System.err.println("Could not find the context " + contextName);
			}

			if (!context_param.isEmpty()) {
				context.putAll(context_param);
			}
		} catch (java.io.IOException ie) {
			System.err.println("Could not load context " + contextName);
			ie.printStackTrace();
		}
	}

	private void evalParam(String arg) {
		if (arg.startsWith("--context=")) {
			contextStr = arg.substring(10);
		} else if (arg.startsWith("--context_param")) {
			String keyValue = arg.substring(16);
			int index = -1;
			if (keyValue != null && (index = keyValue.indexOf('=')) > -1) {
				context_param.put(keyValue.substring(0, index), keyValue.substring(index + 1));
			}
		} else if (arg.startsWith("--stat_port=")) {
			String portStatsStr = arg.substring(12);
			if (portStatsStr != null && !portStatsStr.equals("null")) {
				portStats = Integer.parseInt(portStatsStr);
			}
		} else if (arg.startsWith("--client_host=")) {
			clientHost = arg.substring(14);
		} else if (arg.startsWith("--pid=")) {
			pid = arg.substring(6);
		}
	}
}
