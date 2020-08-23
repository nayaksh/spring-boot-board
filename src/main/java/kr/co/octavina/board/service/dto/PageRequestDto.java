package kr.co.octavina.board.service.dto;

import lombok.Getter;
import lombok.ToString;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
public class PageRequestDto {
    private final int DEFAULT_SIZE = 10;
    private final int MAX_SIZE = 50;

    private int page;
    private int size;
    private List<Sort.Order> orders = new ArrayList<>();

    public void setPage(int page) {
        this.page = page <= 0 ?  1 : page;
    }

    public void setSize(int size) {
        this.size = size > MAX_SIZE ? DEFAULT_SIZE : size;
    }

    public void setOrders(List<Sort.Order> orders) {
        this.orders = orders;
    }

    public org.springframework.data.domain.PageRequest of() {
        Sort sort = Sort.by(this.orders);
        return org.springframework.data.domain.PageRequest.of(this.page-1, this.size, sort);
    }
}
