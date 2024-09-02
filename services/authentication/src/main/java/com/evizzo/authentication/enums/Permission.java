package com.evizzo.authentication.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {
    COMPANY_READ("company:read"),
    COMPANY_UPDATE("company:update"),
    COMPANY_CREATE("company:create"),
    COMPANY_DELETE("company:delete"),

    CARRIER_READ("carrier:read"),
    CARRIER_UPDATE("carrier:update"),
    CARRIER_CREATE("carrier:create"),
    CARRIER_DELETE("carrier:delete"),

    USER_READ("user:read"),
    USER_UPDATE("user:update"),
    USER_CREATE("user:create"),
    USER_DELETE("user:delete"),

    POST_OFFICE_READ("post_office:read"),
    POST_OFFICE_UPDATE("post_office:update"),
    POST_OFFICE_CREATE("post_office:create"),
    POST_OFFICE_DELETE("post_office:delete");

    @Getter
    private final String permission;
}
