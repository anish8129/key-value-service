/*
 * Copyright 2022 Play Games24x7 Pvt. Ltd. All Rights Reserved
 */

package com.key.value;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
public class KeyValueController {

    private static final Map<String, Map<String, KeyValueDetails>> keyValueMapByServiceName = new HashMap<>();

    @GetMapping("getAllKeyValueFor/{service}")
    public Collection<KeyValueDetails> getKVByService(@PathVariable String service) {
        return Optional.ofNullable(keyValueMapByServiceName.get(service))
                .map(Map::values)
                .orElse(null);
    }

    @GetMapping("getKeyValueFor/{service}/{key}")
    public KeyValueDetails getKVByService(@PathVariable String service,
                                                @PathVariable String key) {

        return keyValueMapByServiceName.get(service)
                .get(key);
    }


    @PostMapping("addKeyValue")
    public KeyValueDetails getKVByService(@RequestBody KeyValueDetails keyValueDetails) {
        keyValueMapByServiceName.computeIfPresent(
                keyValueDetails.getServiceName(),
                (key, value) -> {
                    value.put(keyValueDetails.getKey(), keyValueDetails);
                    return value;
                }
        );

        keyValueMapByServiceName.computeIfAbsent(keyValueDetails.getServiceName(),
                (key) -> {
                    Map<String, KeyValueDetails> map = new HashMap<>();
                    map.put(keyValueDetails.getKey(), keyValueDetails);
                    return map;
                }
        );

        return keyValueDetails;
    }


}
