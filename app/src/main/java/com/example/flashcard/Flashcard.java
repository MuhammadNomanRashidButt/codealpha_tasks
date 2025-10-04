package com.example.flashcard;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "flashcards")
public class Flashcard {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String question;
    public String answer;

    public Flashcard(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }
}
