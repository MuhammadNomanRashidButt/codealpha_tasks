package com.example.randomquoteapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView textQuote, textAuthor;
    private Button btnNewQuote;
    private String[][] quotes = {
            {"The only way to do great work is to love what you do.", "Steve Jobs"},
            {"In the middle of every difficulty lies opportunity.", "Albert Einstein"},
            {"Success is not final, failure is not fatal: It is the courage to continue that counts.", "Winston Churchill"},
            {"Don’t watch the clock; do what it does. Keep going.", "Sam Levenson"},
            {"Believe you can and you're halfway there.", "Theodore Roosevelt"},
            {"The best revenge is massive success.", "Frank Sinatra"},
            {"If you want to lift yourself up, lift up someone else.", "Booker T. Washington"}
    };
    private Random random = new Random();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        textQuote = findViewById(R.id.textQuote);
        textAuthor = findViewById(R.id.textAuthor);
        btnNewQuote = findViewById(R.id.btnNewQuote);

        showRandomQuote();

        btnNewQuote.setOnClickListener(v -> showRandomQuote());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    private void showRandomQuote() {
        int index = random.nextInt(quotes.length);
        textQuote.setText("\"" + quotes[index][0] + "\"");
        textAuthor.setText("- " + quotes[index][1]);
    }
}