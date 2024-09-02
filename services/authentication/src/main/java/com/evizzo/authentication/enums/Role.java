package com.evizzo.authentication.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.evizzo.authentication.enums.Permission.*;

@RequiredArgsConstructor
public enum Role {
    COMPANY(
            Set.of(
                    COMPANY_READ,
                    COMPANY_UPDATE,
                    COMPANY_CREATE,
                    COMPANY_DELETE
            )
    ),
    CARRIER(
            Set.of(
                    CARRIER_READ,
                    CARRIER_UPDATE,
                    CARRIER_CREATE,
                    CARRIER_DELETE
            )
    ),
    USER(
            Set.of(
                    USER_READ,
                    USER_UPDATE,
                    USER_CREATE,
                    USER_DELETE
            )
    ),
    POST_OFFICE(
            Set.of(
                    POST_OFFICE_READ,
                    POST_OFFICE_UPDATE,
                    POST_OFFICE_CREATE,
                    POST_OFFICE_DELETE
            )
    );

    @Getter
    private final Set<Permission> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}
