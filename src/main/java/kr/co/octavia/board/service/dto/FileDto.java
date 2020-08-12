package kr.co.octavia.board.service.dto;

import kr.co.octavia.board.domain.File;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter @ToString
public class FileDto {

    private Long id;
    private String name;
    private String path;
    private long contentSize;
    private String extension;

    public static FileDto toDto(File file) {
        return FileDto.builder()
                .id(file.getId())
                .name(file.getName())
                .path(file.getPath())
                .contentSize(file.getContentSize())
                .extension(file.getExtension())
                .build();
    }
}
