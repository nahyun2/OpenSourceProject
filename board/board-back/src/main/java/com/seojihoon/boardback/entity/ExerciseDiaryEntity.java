package com.seojihoon.boardback.entity;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="exercise_diary")
@Table(name="exercise_diary")
public class ExerciseDiaryEntity {
    
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int diaryNumber;
    private String userEmail;
    private String exerciseDate;
    private String contents;
    private String writeDatetime;

    // 운동 일지 작성을 위한 생성자
    public ExerciseDiaryEntity(String userEmail, String exerciseDate, String contents) {
        Date now = Date.from(Instant.now());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String writeDatetime = simpleDateFormat.format(now);

        this.userEmail = userEmail;
        this.exerciseDate = exerciseDate;
        this.contents = contents;
        this.writeDatetime = writeDatetime;
    }

    // 운동 일지 수정을 위한 메서드
    public void updateContents(String contents) {
        this.contents = contents;
        
        Date now = Date.from(Instant.now());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.writeDatetime = simpleDateFormat.format(now);
    }
} 