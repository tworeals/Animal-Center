package tworeal.Animalcenter.domain.article.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tworeal.Animalcenter.global.dto.PageInfoDto;
import tworeal.Animalcenter.global.dto.SingleResDto;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {

    /**
     * Mock Post
     * @return 200
     */
    @PostMapping
    public ResponseEntity<SingleResDto<String>> postArticle() {

        return new ResponseEntity<>(new SingleResDto<>("Post Success"), HttpStatus.CREATED);
    }


    /**
     * Mock Patch
     * @return 200
     */
    @PatchMapping("/edit")
    public ResponseEntity<SingleResDto<String>> patchArticle() {

        return new ResponseEntity<>(new SingleResDto<>("Patch Success"), HttpStatus.OK);
    }

    /**
     * Mock Delete (상태패턴 사용을 위해 실제로는 Patch로 할 것임
     * @return 200 OK로 변경하기
     */
    @PatchMapping("/remove")
    public ResponseEntity<SingleResDto<String>> deleteArticle() {
        // 상태변환 필요
        return new ResponseEntity<>(new SingleResDto<>("Delete Success"), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<SingleResDto<String>> getArticle() {

        return new ResponseEntity<>(new SingleResDto<>("Get Success"), HttpStatus.OK);
    }

    /**
     *
     * @return 객체 ResponseDto를 Pageable로 감싸서 반환
     */
    @GetMapping("/all")
    public ResponseEntity<Page> getArticles(Pageable pageable) {
        List<String> mockList = new ArrayList<>();
        int count = 0;
        while(count++<10) mockList.add("Mock"+count);
        Page<String> page = new PageImpl(mockList, pageable, mockList.size());
//        Page<String> dtoPage = page.map(String::new);
//        Page<ArticleResDto> dtoPage = page.map(ArticleResDto::new);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }
}
