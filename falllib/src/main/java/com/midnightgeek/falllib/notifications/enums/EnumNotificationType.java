package com.midnightgeek.falllib.notifications.enums;

import java.util.HashMap;
import java.util.Map;

public enum EnumNotificationType {
    TEXT(1),IMAGE(2),MEDIA(3),INBOX(4),BIGTEXT(5),CUSTOMTEXT(1),CUSTOMIMAGE(2),CUSTOMMEDIA(3),CUSTOMINBOX(4),CUSTOMBIGTEXT(5);
    private final int value;
    private static Map<Integer, EnumNotificationType> map = new HashMap<Integer, EnumNotificationType>();

    static {
        for (EnumNotificationType legEnum : EnumNotificationType.values()) {
            map.put(legEnum.value, legEnum);
        }
    }
    public static EnumNotificationType valueOf1(Integer legNo) {
        return map.get(legNo);
    }

    EnumNotificationType(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

}
