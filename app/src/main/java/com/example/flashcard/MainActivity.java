package com.example.flashcard;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView textQuestion, textAnswer;
    private Button btnShowAnswer, btnNext, btnPrev, btnAdd, btnEdit, btnDelete;
    private List<Flashcard> flashcards;
    private int currentIndex = 0;
    private FlashcardDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        dao = FlashcardDatabase.getInstance(this).flashcardDao();

        // Sample card if empty
        if (dao.getAllFlashcards().isEmpty()) {
            dao.insert(new Flashcard("What is Java?", "A programming language."));
        }

        flashcards = dao.getAllFlashcards();

        textQuestion = findViewById(R.id.textQuestion);
        textAnswer = findViewById(R.id.textAnswer);
        btnShowAnswer = findViewById(R.id.btnShowAnswer);
        btnNext = findViewById(R.id.btnNext);
        btnPrev = findViewById(R.id.btnPrev);
        btnAdd = findViewById(R.id.btnAdd);
        btnEdit = findViewById(R.id.btnEdit);
        btnDelete = findViewById(R.id.btnDelete);

        updateFlashcard();

        btnShowAnswer.setOnClickListener(v -> {
            if (textAnswer.getVisibility() == View.GONE) {
                textAnswer.setVisibility(View.VISIBLE);
            } else {
                textAnswer.setVisibility(View.GONE);
            }
        });

        btnNext.setOnClickListener(v -> {
            if (!flashcards.isEmpty()) {
                currentIndex = (currentIndex + 1) % flashcards.size();
                updateFlashcard();
            }
        });

        btnPrev.setOnClickListener(v -> {
            if (!flashcards.isEmpty()) {
                currentIndex = (currentIndex - 1 + flashcards.size()) % flashcards.size();
                updateFlashcard();
            }
        });

        btnAdd.setOnClickListener(v -> {
            dao.insert(new Flashcard("New Question", "New Answer"));
            flashcards = dao.getAllFlashcards();
            currentIndex = flashcards.size() - 1;
            updateFlashcard();
        });

        btnEdit.setOnClickListener(v -> {
            if (!flashcards.isEmpty()) {
                Flashcard card = flashcards.get(currentIndex);
                card.answer = "Edited Answer";
                dao.update(card);
                flashcards = dao.getAllFlashcards();
                updateFlashcard();
            }
        });

        btnDelete.setOnClickListener(v -> {
            if (!flashcards.isEmpty()) {
                dao.delete(flashcards.get(currentIndex));
                flashcards = dao.getAllFlashcards();
                if (currentIndex >= flashcards.size()) currentIndex = flashcards.size() - 1;
                updateFlashcard();
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    private void updateFlashcard() {
        if (flashcards.isEmpty()) {
            textQuestion.setText("No flashcards yet");
            textAnswer.setText("");
            textAnswer.setVisibility(View.GONE);
        } else {
            Flashcard current = flashcards.get(currentIndex);
            textQuestion.setText(current.question);
            textAnswer.setText(current.answer);
            textAnswer.setVisibility(View.GONE);
        }
    }
}