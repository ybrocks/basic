package com.beyond.basic.b2_board.post.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PostSearchDto {
    private String title;
    private String contents;
    private String category;
}
