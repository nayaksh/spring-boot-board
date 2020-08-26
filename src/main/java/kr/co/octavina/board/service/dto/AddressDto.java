package kr.co.octavina.board.service.dto;

import kr.co.octavina.board.domain.common.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@AllArgsConstructor
@EqualsAndHashCode
@Setter @Getter @ToString
public class AddressDto {
    private String zipCode;
    private String address1;
    private String address2;

    public Address toEntity() {
        return Address.builder()
                .zipCode(this.getZipCode())
                .address1(this.getAddress1())
                .address2(this.getAddress2())
                .build();
    }
}
