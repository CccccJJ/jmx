package com.company;

import com.company.jmx.Hello;

import javax.management.*;
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXConnectorServerFactory;
import javax.management.remote.JMXServiceURL;
import java.lang.management.ManagementFactory;
import java.rmi.registry.LocateRegistry;

public class HelloAgent {

    public static void main(String[] args) throws MalformedObjectNameException, NotCompliantMBeanException, InstanceAlreadyExistsException, MBeanRegistrationException, InterruptedException {
        MBeanServer server = ManagementFactory.getPlatformMBeanServer();

        server.registerMBean(new Hello(), new ObjectName("jmxBean:name=hello"));

//        Thread.sleep(60 * 60 * 100);

//        HtmlAdaptorServer adapter = new HtmlAdaptorServer();
//        server.registerMBean(adapter, new ObjectName("HelloAgent:name=htmladapter,port=8082"));
//        adapter.start();

        try {
            LocateRegistry.createRegistry(9999);
            JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:9999/jmxrmi");
            JMXConnectorServer jmxConnectorServer = JMXConnectorServerFactory.newJMXConnectorServer(url, null, server);
            jmxConnectorServer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
