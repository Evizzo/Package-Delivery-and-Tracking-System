package com.evizzo.post_office.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PacketSize {
    SMALL(1),
    MEDIUM(3),
    LARGE(5);

    private final int size;
}
