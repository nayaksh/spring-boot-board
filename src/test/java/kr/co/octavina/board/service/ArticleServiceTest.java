package kr.co.octavina.board.service;

import kr.co.octavina.board.domain.Article;
import kr.co.octavina.board.domain.common.Status;
import kr.co.octavina.board.repository.ArticleRepository;
import kr.co.octavina.board.service.dto.ArticleDto;
import kr.co.octavina.board.service.dto.ArticleSearchCondition;
import kr.co.octavina.board.service.dto.ArticleSearchDto;
import kr.co.octavina.board.service.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestConstructor;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@SpringBootTest
@Slf4j
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@RequiredArgsConstructor
//@Transactional
class ArticleServiceTest {

    private final MemberService memberService;
    private final ArticleService articleService;

    private final ArticleRepository articleRepository;

    @Mock
    HttpServletRequest request;

    @Test
    @Rollback(false)
    public void 글_등록() throws Exception {
        //given
        MemberDto loginMember = MemberDto.builder().loginId("rarity").password("1234").build();

        String loginId = memberService.login(loginMember, request.getRemoteAddr());

        for (int i = 0; i < 1; i++) {
            String str = UUID.randomUUID().toString();

            ArticleDto articleDto = ArticleDto.builder()
                    .title("제목 " + str)
                    .content("내용입니다 " + str)
                    .status(Status.CREATED)
                    .build();

            //when
            TimeUnit.SECONDS.sleep(1L);
            Article savedArticle = articleRepository.save(articleDto.toEntity());

            //then
            assertThat(articleDto.getTitle()).isEqualTo(savedArticle.getTitle());
            assertThat(articleDto.getContent()).isEqualTo(savedArticle.getContent());
        }

    }

//    @Test
//    @Rollback(true)
//    public void 글_신규등록() {
//        //given
//        MemberDto member = memberService.getMember("pinkamena");
//
//        ArticleDto articleDto = ArticleDto.builder()
//                .title("신규 제목")
//                .content("신규등록 내용입니다.")
//                .memberDto(member)
//                .status(Status.CREATED)
//                .build();
//
//        //when
//        Long articleId = 0L;
//
//        try {
//            articleId = articleService.createArticle(articleDto);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        //then
//        log.info("저장 글 아이디 : " + articleId);
//        assertThat(articleId).isGreaterThan(-1);
//    }

//    @Test
//    @Rollback(false)
//    public void 글수정() throws Exception {
//        MemberDto loginMember = MemberDto.builder().id(1L).loginId("rarity").password("1234").build();
//        String loginId = memberService.login(loginMember, request.getRemoteAddr());
//
//        ArticleDto articleDto = articleService.getArticle(2L);
//
//        String title = "제목수정22";
//        String content = "내용수정22";
//
//        Article article = articleDto.toEntity(); // 준영속 상태이므로 업데이트가 되지 않는다.
//        article.update(title, content);
//
//        assertThat(article.getTitle()).isEqualTo(title);
//        assertThat(article.getContent()).isEqualTo(content);
//    }

    @Test
    @Rollback(false)
    public void 글수정2() throws Exception {
        MemberDto loginMember = MemberDto.builder().loginId("applejack").password("1234").build();
        String loginId = memberService.login(loginMember, request.getRemoteAddr());

        ArticleDto articleDto = articleService.getArticle(2L);
        articleDto.setTitle("제목수정33");
        articleDto.setContent("내용수정33");
        ArticleDto updatedArticle = articleService.modify(articleDto);
        log.info(updatedArticle.toString());
    }

//    @Test
//    public void 글_조회() throws Exception {
//        Article article = articleRepository.findArticleById(15L);
//
//        log.info(article.getTitle());
//
//        article.getFiles().forEach(f -> {
//            System.out.println("f.getName() = " + f.getName());
//        });
//    }

//    @Test
//    @Rollback(false)
//    public void 글수정_Auditing() throws Exception {
//        MemberDto loginMember = MemberDto.builder().loginId("rarity").password("1234").build();
//        String loginId = memberService.login(loginMember);
//
//        ArticleDto articleDto = articleService.getArticle(40L);
//        articleDto.setTitle("제목수정2233");
//        articleDto.setContent("내용수정2233");
//        ArticleDto updatedArticle = articleService.modifyArticle(articleDto);
//        log.info(updatedArticle.toString());
//    }

    @Test
//    @Rollback(false)
    public void 글제거() throws Exception {
        Long articleId = 42L;

        articleService.eraseArticle(articleId);

        assertThatExceptionOfType(RuntimeException.class).isThrownBy(() ->{
            ArticleDto articleDto = articleService.getArticle(42L);
        });
    }

//    @Test
//    public void 검색() throws Exception {
//        ArticleSearchCondition condition = new ArticleSearchCondition();
//        condition.setTitle("zzz");
//        List<ArticleSearchDto> articleDtos = articleService.searchArticlesByPagination(condition);
//
//        articleDtos.forEach(a -> {
//            System.out.println("a = " + a);
//        });
//    }

    @Test
    public void 목록_기본_조회() throws Exception {
//        PageRequestDto pageRequestDto = new PageRequestDto();
//        pageRequestDto.setPage(1);
//        pageRequestDto.setSize(20);
//        Sort.Order order1 = new Sort.Order(Sort.Direction.DESC,"createdDate");
//        Sort.Order order2 = new Sort.Order(Sort.Direction.ASC,"title");
//        List<Sort.Order> orders = new ArrayList<>();
//        orders.add(order1);
//        orders.add(order2);
//        pageRequestDto.setOrders(orders);
//
//
//        String jsonStr = new ObjectMapper().writeValueAsString(pageRequestDto);
//        log.info(jsonStr);
//
//        articleRepository.findAll(pageRequestDto.of());
    }

    @Test
    public void 검색_페이징() throws Exception {
        ArticleSearchCondition condition = new ArticleSearchCondition();
        condition.setTitle("제목");
        condition.setCreatorName("래리티");

        Sort.Order order1 = new Sort.Order(Sort.Direction.DESC, "createdDate");
        Sort.Order order2 = new Sort.Order(Sort.Direction.ASC, "title");
        Sort sort = Sort.by(order1, order2);

        Pageable pageable = PageRequest.of(0, 5, sort);

        //when
        Page<ArticleSearchDto> result = articleService.searchArticlesByPagination(condition, pageable);

        //then
        System.out.println("result.getNumber() = " + result.getNumber());
        System.out.println("result.getTotalPages() = " + result.getTotalPages());
        System.out.println("result.getTotalElements() = " + result.getTotalElements());
        System.out.println("result.getSize() = " + result.getSize());
        System.out.println("result.getNumberOfElements() = " + result.getNumberOfElements());
        System.out.println("result.getSort() = " + result.getSort());

        result.getContent()
                .forEach(a -> System.out.println("a = " + a));
    }

    @Test
    public void 검색_페이징2() throws Exception {
        ArticleSearchCondition condition = new ArticleSearchCondition();
        condition.setTitle("제목");
        condition.setCreatorName("래리티");

        Sort.Order order1 = new Sort.Order(Sort.Direction.DESC, "createdDate");
        Sort.Order order2 = new Sort.Order(Sort.Direction.ASC, "creatorName");
        Sort sort = Sort.by(order1, order2);

        Pageable pageable = PageRequest.of(0, 5, sort);

        //when
        Page<ArticleSearchDto> result = articleRepository.findArticlesWithCreatorByPagination(condition, pageable);

        //then
        System.out.println("result.getNumber() = " + result.getNumber());
        System.out.println("result.getTotalPages() = " + result.getTotalPages());
        System.out.println("result.getTotalElements() = " + result.getTotalElements());
        System.out.println("result.getSize() = " + result.getSize());
        System.out.println("result.getNumberOfElements() = " + result.getNumberOfElements());
        System.out.println("result.getSort() = " + result.getSort());

        result.getContent()
                .forEach(a -> System.out.println("a = " + a));
    }

    @Test
    public void 파일같이_조회() throws Exception {
//        Article article = articleRepository.findAllWithFilesById(6L);
    }

}