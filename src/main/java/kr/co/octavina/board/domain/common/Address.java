package kr.co.octavina.board.domain.common;

import kr.co.octavina.board.service.dto.AddressDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
@Getter
public class Address {
    private String zipCode;
    private String address1;
    private String address2;

    public AddressDto toDto(){
        return AddressDto.builder()
                .zipCode(this.getZipCode())
                .address1(this.getAddress1())
                .address2(this.getAddress2())
                .build();
    }
}
