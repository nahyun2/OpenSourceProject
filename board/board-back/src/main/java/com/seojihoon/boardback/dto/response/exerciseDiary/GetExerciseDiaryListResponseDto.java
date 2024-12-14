package com.seojihoon.boardback.dto.response.exerciseDiary;

import java.util.List;

import com.seojihoon.boardback.dto.response.ResponseDto;
import com.seojihoon.boardback.entity.ExerciseDiaryEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GetExerciseDiaryListResponseDto extends ResponseDto {
    
    private List<ExerciseDiary> exerciseDiaryList;

    public GetExerciseDiaryListResponseDto(List<ExerciseDiaryEntity> exerciseDiaryEntities) {
        super("SU", "Success");
        
        this.exerciseDiaryList = exerciseDiaryEntities.stream()
            .map(ExerciseDiary::new)
            .toList();
    }

    @Getter
    @NoArgsConstructor
    private class ExerciseDiary {
        private String exerciseDate;
        private String contents;
        private String writeDatetime;

        public ExerciseDiary(ExerciseDiaryEntity exerciseDiaryEntity) {
            this.exerciseDate = exerciseDiaryEntity.getExerciseDate();
            this.contents = exerciseDiaryEntity.getContents();
            this.writeDatetime = exerciseDiaryEntity.getWriteDatetime();
        }
    }
} 