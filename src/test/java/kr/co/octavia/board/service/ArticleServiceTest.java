package kr.co.octavia.board.service;

import kr.co.octavia.board.domain.Article;
import kr.co.octavia.board.domain.common.Status;
import kr.co.octavia.board.repository.ArticleRepository;
import kr.co.octavia.board.service.dto.ArticleDto;
import kr.co.octavia.board.service.dto.MemberDto;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestConstructor;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@RequiredArgsConstructor
@Transactional
class ArticleServiceTest {

    private final MemberService memberService;
    private final ArticleService articleService;

    private final ArticleRepository articleRepository;

    @Test
    @Rollback(true)
    public void 글_등록() {
        //given
        MemberDto member = memberService.getMember("rarity");

        ArticleDto articleDto = ArticleDto.builder()
                .title("제목1")
                .content("내용입니다.")
                .memberDto(member)
                .status(Status.CREATED)
                .build();

        //when
        Article article = Article.toEntity(articleDto);
        Article savedArticle = articleRepository.save(article);

        //then
        assertThat(articleDto.getTitle()).isEqualTo(savedArticle.getTitle());
        assertThat(articleDto.getContent()).isEqualTo(savedArticle.getContent());
    }

    @Test
    @Rollback(true)
    public void 글_신규등록() {
        //given
        MemberDto member = memberService.getMember("pinkamena");

        ArticleDto articleDto = ArticleDto.builder()
                .title("신규 제목")
                .content("신규등록 내용입니다.")
                .memberDto(member)
                .status(Status.CREATED)
                .build();

        //when
        Long articleId = 0L;

        try {
            articleId = articleService.createArticle(articleDto);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //then
        log.info("저장 글 아이디 : " + articleId);
        assertThat(articleId).isGreaterThan(-1);
    }

    @Test
    @Rollback(false)
    public void 글수정() throws Exception {
        ArticleDto articleDto = articleService.getArticle(2L);

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
        ArticleDto articleDto = articleService.getArticle(2L);
        articleDto.setTitle("제목수정");
        articleDto.setContent("내용수정");
        ArticleDto updatedArticle = articleService.modifyArticle(articleDto);
        log.info(updatedArticle.toString());
    }
}