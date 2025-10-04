package com.example.flashcard;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Flashcard.class}, version = 1)
public abstract class FlashcardDatabase extends RoomDatabase {
    private static FlashcardDatabase instance;

    public abstract FlashcardDao flashcardDao();

    public static synchronized FlashcardDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            FlashcardDatabase.class, "flashcard_database")
                    .allowMainThreadQueries() // for simplicity (not recommended for production)
                    .build();
        }
        return instance;
    }
}
