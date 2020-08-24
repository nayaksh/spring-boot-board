package kr.co.octavina.board.service.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Setter @Getter
@ToString
@Deprecated
public class PageRequestDto {
    private final int DEFAULT_SIZE = 10;
    private final int MAX_SIZE = 50;

    private int page;
    private int size;
    private List<OrderDto> orderDtos = new ArrayList<>();

    public void setPage(int page) {
        this.page = page <= 0 ?  1 : page;
    }

    public void setSize(int size) {
        this.size = size > MAX_SIZE ? DEFAULT_SIZE : size;
    }

    public org.springframework.data.domain.PageRequest of() {
        Sort sort = Sort.by(this.getOrders());
        return org.springframework.data.domain.PageRequest.of(this.page-1, this.size, sort);
    }

    public List<Sort.Order> getOrders() {
        return this.orderDtos.stream()
                .map(orderDto -> new Sort.Order(orderDto.direction, orderDto.property))
                .collect(Collectors.toList());
    }

    @Setter @Getter
    @ToString
    static class OrderDto {
        private Sort.Direction direction;
        private String property;
    }
}
