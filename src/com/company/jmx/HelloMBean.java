package com.company.jmx;

public interface HelloMBean {

    String getName();

    void setName(String name);

    String getAge();

    void setAge(String age);

    void helloWorld();

    void helloWorld(String str);

    void getTelephone();
}
