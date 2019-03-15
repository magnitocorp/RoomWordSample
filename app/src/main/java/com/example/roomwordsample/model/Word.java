package com.example.roomwordsample.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * To make the Word class meaningful to a Room database, you need to annotate it.
 * Annotations identify how each part of this class relates to an entry in the database. Room uses this information to generate code.
 */

/**
 * Each @Entity class represents an entity in a table. Annotate your class declaration to indicate that it's an entity.
 * Specify the name of the table if you want it to be different from the name of the class.
 */
@Entity(tableName = "word_table")
public class Word {



    //Every entity needs a primary key. To keep things simple, each word acts as its own primary key.
    @PrimaryKey
    //Denotes that a parameter, field, or method return value can never be null.
    @NonNull
    //Specify the name of the column in the table if you want it to be different from the name of the member variable.
    @ColumnInfo(name = "word")
    private String mWord;

    /**
     * Tip: You can autogenerate unique keys by annotating the primary key as follows:
     *     @PrimaryKey(autoGenerate = true)
     *     private int id;
     */

    //Constructor
    public Word(String word) {

        this.mWord = word;
    }

    //Every field that's stored in the database needs to be either public or have a "getter" method. This sample provides a getWord() method.
    public String getWord() {

        return this.mWord;
    }
}
