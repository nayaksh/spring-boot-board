package kr.co.octavina.board.service;

import kr.co.octavina.board.domain.Article;
import kr.co.octavina.board.domain.common.Status;
import kr.co.octavina.board.repository.ArticleRepository;
import kr.co.octavina.board.service.dto.ArticleDto;
import kr.co.octavina.board.service.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestConstructor;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@Slf4j
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@RequiredArgsConstructor
@Transactional
class ArticleServiceTest {

    private final MemberService memberService;
    private final ArticleService articleService;

    private final ArticleRepository articleRepository;

//    @Test
//    @Rollback(false)
//    public void 글_등록() throws Exception {
//        //given
//        MemberDto loginMember = MemberDto.builder().id(39L).loginId("rarity").password("1234").build();
//        String loginId = memberService.login(loginMember);
//
//        ArticleDto articleDto = ArticleDto.builder()
//                .title("제목133")
//                .content("내용입니다.33")
//                .memberDto(loginMember)
//                .status(Status.CREATED)
//                .build();
//
//        //when
//        Article article = Article.toEntity(articleDto);
//        Article savedArticle = articleRepository.save(article);
//
//        //then
//        assertThat(articleDto.getTitle()).isEqualTo(savedArticle.getTitle());
//        assertThat(articleDto.getContent()).isEqualTo(savedArticle.getContent());
//    }

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

    @Test
    @Rollback(false)
    public void 글수정() throws Exception {
        ArticleDto articleDto = articleService.getArticle(15L);

        String title = "제목수정22";
        String content = "내용수정22";

        Article article = Article.toEntity(articleDto);
        article.update(title, content);

        assertThat(article.getTitle()).isEqualTo(title);
        assertThat(article.getContent()).isEqualTo(content);
    }

    @Test
    @Rollback(false)
    public void 글수정2() throws Exception {
        ArticleDto articleDto = articleService.getArticle(15L);
        articleDto.setTitle("제목수정22");
        articleDto.setContent("내용수정22");
        ArticleDto updatedArticle = articleService.modifyArticle(articleDto);
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
}