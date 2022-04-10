package com.company.notification;

import com.company.jmx.Hello;

import javax.management.Notification;
import javax.management.NotificationListener;

public class HelloLister implements NotificationListener {

    @Override
    public void handleNotification(Notification notification, Object handback) {
        Hello hello = ((Hello) handback);
        hello.helloWorld(notification.getMessage());
    }

}
