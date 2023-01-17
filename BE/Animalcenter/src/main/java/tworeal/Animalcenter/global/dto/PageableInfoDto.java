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

    private int size;

    private int totalPages;

    private long totalElements;

    private boolean first;  // 첫 페이지인지

    private boolean last;   // 마지막 페이지인지

    private boolean sorted; // 정렬 조건이 있는지

    private int pageNumber;

}