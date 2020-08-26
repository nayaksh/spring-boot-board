package kr.co.octavina.board.domain.common;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

//import static org.assertj.core.api.Assertions.assertThat;
//import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@SpringBootTest
class AddressTest {

    @Test
    void 값타입비교() throws Exception {
        Address address1 =new Address("123123","제주도","서귀포");
        Address address2 =new Address("123123","제주도","서귀포");

        assertThat(address1 == address2).isEqualTo(false);
        assertThat(address1).isEqualTo(address2);
    }
}