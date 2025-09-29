package tech.outsource.common.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public enum ETeamsStatus {
    ACTIVE("ACTIVE", "Hoạt động"),
    INACTIVE("INACTIVE", "Không hoạt động"),
    SUSPENDED("SUSPENDED", "Tạm dừng"),
    DELETED("DELETED", "Đã xóa");

    String code;
    String name;
}
