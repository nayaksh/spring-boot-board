package kr.co.octavina.board;

import kr.co.octavina.board.config.AppResource;
import kr.co.octavina.board.util.FileUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OctavinaSampleApplicationTests {

    @Autowired
    AppResource appResource;

    @Test
    void contextLoads() throws Exception {
        Assertions.assertNotNull(appResource.getStoragePath());
        System.out.println("storagePath : " + appResource.getStoragePath());

        String savePath = FileUtil.createPath();
        System.out.println(savePath);
        Assertions.assertNotNull(savePath);

        System.out.println(appResource.getStoragePath() + FileUtil.createPath());
    }

}
