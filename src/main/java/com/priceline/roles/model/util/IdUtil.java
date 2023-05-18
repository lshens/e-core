package com.priceline.roles.model.util;

import java.util.UUID;

public final class IdUtil {

    public static String generate() {
        return UUID.randomUUID().toString();
    }
}
