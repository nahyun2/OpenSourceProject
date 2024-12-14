package com.seojihoon.boardback.dto.request.exerciseDiary;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PatchExerciseDiaryRequestDto {
    
    @NotBlank
    private String exerciseDate;

    @NotBlank
    private String contents;
} 