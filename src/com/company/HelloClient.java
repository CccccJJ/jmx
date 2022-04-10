package com.company;

import com.company.jmx.HelloMBean;

import javax.management.Attribute;
import javax.management.MBeanServerConnection;
import javax.management.MBeanServerInvocationHandler;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

public class HelloClient {

    public static void main(String[] args) throws Exception {
        JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:9999/jmxrmi");
        JMXConnector connect = JMXConnectorFactory.connect(url, null);

        MBeanServerConnection mbsc = connect.getMBeanServerConnection();
        String[] domains = mbsc.getDomains();

        System.out.println("Domains .......");
        for (String domain : domains) {
            System.out.println(domain);
        }

        System.out.println("MBean count = " + mbsc.getMBeanCount());

        ObjectName mbeanName = new ObjectName("jmxBean:name=hello");
        mbsc.setAttribute(mbeanName, new Attribute("Name", "杭州"));
        mbsc.setAttribute(mbeanName, new Attribute("Age", "1990"));
        System.out.println("Age: " + mbsc.getAttribute(mbeanName, "Age"));
        System.out.println("Name: " + mbsc.getAttribute(mbeanName, "Name"));

        HelloMBean proxy = MBeanServerInvocationHandler.newProxyInstance(mbsc, mbeanName, HelloMBean.class, false);
        proxy.helloWorld();
        proxy.helloWorld("migu");
        proxy.getTelephone();
        mbsc.invoke(mbeanName, "getTelephone", null, null);
        mbsc.invoke(mbeanName, "helloWorld", new String[]{"i'll connect to JMX Server via Client2"},
                new String[]{"java.lang.String"});
        mbsc.invoke(mbeanName, "helloWorld", null, null);
    }
}
