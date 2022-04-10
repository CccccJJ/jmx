package com.company.notification;

import com.company.jmx.Hello;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

public class NotificationAgent {

    public static void main(String[] args) throws Exception {
        MBeanServer server = ManagementFactory.getPlatformMBeanServer();

        Hello hello = new Hello();
        server.registerMBean(hello, new ObjectName("yunge:name=Hello"));

        Jack jack = new Jack();
        server.registerMBean(jack, new ObjectName("jack:name=Jack"));
        jack.addNotificationListener(new HelloLister(), null, hello);
        Thread.sleep(10 * 60 * 1000);

    }
}
