package com.company.notification;

import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;

public class Jack extends NotificationBroadcasterSupport implements JackMBean {

    private int seq = 0;

    @Override
    public void hi() {
        Notification notify = new Notification("jack.hi", this, ++seq, System.currentTimeMillis(), "jack");
        this.sendNotification(notify);
    }
}
