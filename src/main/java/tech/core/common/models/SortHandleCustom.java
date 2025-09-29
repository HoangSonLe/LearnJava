package tech.core.common.models;

import org.springframework.data.domain.Sort;

public record SortHandleCustom(Sort sort) {
    public SortHandleCustom(Sort sort) {
        this.sort = sort;
    }

    public static Sort of(String sorter) {
        String[] sorterSplit = sorter.split("_");
        if (sorterSplit.length == 2) {
            if (sorterSplit[1].equals("ascend")) {
                return Sort.by(Sort.Direction.ASC, sorterSplit[0]);
            } else if (sorterSplit[1].equals("descend")) {
                return Sort.by(Sort.Direction.DESC, sorterSplit[0]);
            }
        }
        return Sort.by(Sort.Direction.DESC, "createdAt");
    }

    public Sort sort() {
        return sort;
    }
}
