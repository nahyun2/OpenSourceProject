package com.seojihoon.boardback.dto.response.exerciseDiary;

import com.seojihoon.boardback.dto.response.ResponseDto;
import com.seojihoon.boardback.entity.ExerciseDiaryEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GetExerciseDiaryResponseDto extends ResponseDto {
    
    private String exerciseDate;
    private String contents;
    private String writeDatetime;

    public GetExerciseDiaryResponseDto(ExerciseDiaryEntity exerciseDiaryEntity) {
        super("SU", "Success");
        
        this.exerciseDate = exerciseDiaryEntity.getExerciseDate();
        this.contents = exerciseDiaryEntity.getContents();
        this.writeDatetime = exerciseDiaryEntity.getWriteDatetime();
    }
} 