package com.ttrip.api.controller;

import com.ttrip.api.dto.ApplyReqDto;
import com.ttrip.api.dto.DataResDto;
import com.ttrip.api.dto.NewArticleReqDto;
import com.ttrip.api.dto.SearchReqDto;
import com.ttrip.api.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Api(tags = "유저 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/articles")
public class ArticleController {
    private final ArticleService articleService;
//  dto쓸려고 post요청
    @ApiResponses({
            @ApiResponse(code = 200, message = "게시글 조회 성공"),
            @ApiResponse(code = 400, message = "게시글 조회 실패")
    })
    @ApiOperation(value = "게시글 목록 조회 API", httpMethod = "POST")
    @PostMapping("/")
    public DataResDto<?> search(@RequestBody SearchReqDto searchReqDto) {
        return articleService.search(searchReqDto);
    }

    @ApiResponses({
            @ApiResponse(code = 200, message = "게시글 생성 성공"),
            @ApiResponse(code = 400, message = "게시글 생성 실패")
    })
    @ApiOperation(value = "게시글 생성 API", httpMethod = "POST")
    @PostMapping("/new")
    public DataResDto<?> newArticle(@RequestBody NewArticleReqDto newArticleReqDto) {
        return articleService.newArticle(newArticleReqDto);
    }

    @ApiResponses({
            @ApiResponse(code = 200, message = "게시글 상세 조회 성공"),
            @ApiResponse(code = 400, message = "게시글 상세 조회 실패")
    })
    @ApiOperation(value = "게시글 상세 조회 API", httpMethod = "GET")
    @GetMapping("/{articleId}/{memberUuid}")
    public DataResDto<?> searchDetail(@PathVariable("articleId") Integer articleId, @PathVariable("memberUuid") UUID memberUuid) {
        return articleService.searchDetail(articleId, memberUuid);
    }
    @ApiResponses({
            @ApiResponse(code = 200, message = "게시글 삭제 성공"),
            @ApiResponse(code = 400, message = "게시글 삭제 실패")
    })
    @ApiOperation(value = "게시글 삭제 API", httpMethod = "Delete")
    @DeleteMapping("/{articleId}/{memberUuid}")
    public DataResDto<?> ereaseArticle(@PathVariable("articleId") Integer articleId, @PathVariable("uuid") UUID memberUuid) {
        return articleService.ereaseArticle(articleId, memberUuid);
    }
    @ApiResponses({
            @ApiResponse(code = 200, message = "신청이 완료되었습니다."),
            @ApiResponse(code = 400, message = "매칭 참여 신청 실패")
    })
    @ApiOperation(value = "매칭 참여 신청 API", httpMethod = "POST")
    @PostMapping("/newApply")
    public DataResDto<?> newApply(@RequestBody ApplyReqDto applyReqDto) {
        return articleService.newApply(applyReqDto);
    }
    @ApiResponses({
            @ApiResponse(code = 200, message = "신청한 유저 목록이 조회되었습니다"),
            @ApiResponse(code = 400, message = "신청한 유저 목록이 조회 실패")
    })
    @ApiOperation(value = "게시글 상세 조회 API", httpMethod = "GET")
    @GetMapping("/{articleId}/applyArticle/{memberUuid}")
    public DataResDto<?> searchApply(@PathVariable("articleId") Integer articleId, @PathVariable("uuid") UUID memberUuid) {
        return articleService.searchApply(articleId, memberUuid);
    }
    @ApiResponses({
            @ApiResponse(code = 200, message = "모집이 종료되었습니다."),
            @ApiResponse(code = 400, message = "모집 종료 실패")
    })
    @ApiOperation(value = "모집 종료 신청 API", httpMethod = "POST")
    @PostMapping("/{articleId}/end/{memberUuid}")
    public DataResDto<?> endArticle(@PathVariable("articleId") Integer articleId, @PathVariable("memberUuid") UUID memberUuid) {
        return articleService.endArticle(articleId, memberUuid);
    }
}
