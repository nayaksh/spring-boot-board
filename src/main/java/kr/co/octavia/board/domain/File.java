package kr.co.octavia.board.domain;

import kr.co.octavia.board.domain.common.BaseEntity;
import kr.co.octavia.board.service.dto.FileDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "file")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class File extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "file_id")
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 100)
    private String path;

    private long contentSize;
    private String extension;

    public static File toEntity(FileDto fileDto) {
        return File.builder()
                .id(fileDto.getId())
                .name(fileDto.getName())
                .path(fileDto.getPath())
                .contentSize(fileDto.getContentSize())
                .extension(fileDto.getExtension())
                .build();
    }
}
