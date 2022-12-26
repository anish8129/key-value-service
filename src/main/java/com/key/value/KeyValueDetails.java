/*
 * Copyright 2022 Play Games24x7 Pvt. Ltd. All Rights Reserved
 */

package com.key.value;

public class KeyValueDetails {
    private String serviceName;
    private String key;
    private String value;

    public KeyValueDetails(String serviceName, String key, String value) {
        this.serviceName = serviceName;
        this.key = key;
        this.value = value;
    }

    public String getServiceName() {
        return serviceName;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
