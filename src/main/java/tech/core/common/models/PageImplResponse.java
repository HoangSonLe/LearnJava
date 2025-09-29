package tech.core.common.models;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.NonNull;

import java.io.Serializable;
import java.util.List;

public record PageImplResponse<T>(@NonNull List<T> data,
                                  @Schema(example = "true", requiredMode = Schema.RequiredMode.REQUIRED) boolean success,
                                  @Schema(example = "100", requiredMode = Schema.RequiredMode.REQUIRED) @NonNull Long total,
                                  @Schema(example = "1", requiredMode = Schema.RequiredMode.REQUIRED) @NonNull Integer currentPage,
                                  @Schema(example = "20", requiredMode = Schema.RequiredMode.REQUIRED) @NonNull Integer pageSize) implements Serializable {
    public PageImplResponse(@NonNull List<T> data, boolean success, @NonNull Long total, @NonNull Integer currentPage, @NonNull Integer pageSize) {
        if (data == null) {
            throw new IllegalArgumentException("Data list must not be null");
        } else if (total == null || total < 0) {
            throw new IllegalArgumentException("Total must not be null or negative");
        } else if (currentPage == null || currentPage < 1) {
            throw new IllegalArgumentException("Current page must not be null or less than 1");
        } else if (pageSize == null || pageSize < 1) {
            throw new IllegalArgumentException("Page size must not be null or less than 1");
        } else {
            this.data = data;
            this.success = success;
            this.total = total;
            this.currentPage = currentPage;
            this.pageSize = pageSize;
        }
    }

    public @NonNull List<T> data() {
        return data;
    }
}
