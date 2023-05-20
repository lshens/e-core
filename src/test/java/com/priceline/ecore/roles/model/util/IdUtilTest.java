package com.priceline.ecore.roles.model.util;

import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

class IdUtilTest {
    private static final Pattern UUID_REGEX =
            Pattern.compile("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$");

    @Test
    void generateUUID() {
        String actual = IdUtil.generate();

        assertTrue(UUID_REGEX.matcher(actual).matches());
    }

}