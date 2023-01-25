package tworeal.Animalcenter.global.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor @Getter @Builder
public class PageInfo {
    private int page;
    private int size;
    private long totalElements;
    private int totalPages;
}
/**
 * DTO지만 setter를 지양하기 위해 @Builder 사용
 */