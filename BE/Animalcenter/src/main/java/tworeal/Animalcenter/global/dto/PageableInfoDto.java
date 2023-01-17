package tworeal.Animalcenter.global.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageableInfoDto<T> {

    private List<T> data;  // 감싸는 리스트 이름

    private PageInfo pageInfo;

    public PageableInfoDto(Page<T> page) {
        data = page.getContent();  // 조회할 데이터
        PageInfo pageableInfo = PageInfo.builder()
                .page(page.getPageable().getPageNumber())  // 페이지 번호 확인
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .build();
        pageInfo = pageableInfo;
    }
}