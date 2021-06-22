package com.dynamicdevz.dynamicstorageapp.model.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.dynamicdevz.dynamicstorageapp.model.data.Comic;

import java.util.ArrayList;
import java.util.List;

import static com.dynamicdevz.dynamicstorageapp.util.Logger.logDebug;

public class ComicDBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "comic.db";
    public static int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "Comic";
    public static final String COLUMN_PUBLISH_YEAR = "publish_year";
    public static final String COLUMN_RATING = "rating";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_ISSUE = "issue";
    public static final String COLUMN_PUBLISHER = "publisher";
    public static final String COLUMN_COMIC_ID = "comic_id";

    public ComicDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String createCommand = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_COMIC_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_PUBLISH_YEAR + " INTEGER, " +
                COLUMN_RATING + " REAL, " +
                COLUMN_TITLE + " TEXT, " +
                COLUMN_ISSUE + " INTEGER," +
                COLUMN_PUBLISHER + " TEXT)";

        sqLiteDatabase.execSQL(createCommand);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String upgrade = "DROP TABLE IF EXISTS " + TABLE_NAME;
        sqLiteDatabase.execSQL(upgrade);
        onCreate(sqLiteDatabase);
    }

    public void insertComic(Comic comic) {
        logDebug("Inserting new comic: "+comic.getTitle());
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_ISSUE, comic.getIssue());
        contentValues.put(COLUMN_RATING, comic.getRating());
        contentValues.put(COLUMN_TITLE, comic.getTitle());
        contentValues.put(COLUMN_PUBLISH_YEAR, comic.getPublishYear());
        contentValues.put(COLUMN_PUBLISHER, comic.getPublisher().name());

        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_NAME, null, contentValues);
    }

    public List<Comic> getAllComics() {
        logDebug("Reading from database.");

        List<Comic> comics = new ArrayList<>();
        String getQuery = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = getReadableDatabase().rawQuery(getQuery, null);
        cursor.move(-1);

        //Comic(int comicId, int publishYear, Publisher publisher, double rating, String title, int issue)
        while (cursor.moveToNext()) {
            Comic comic = new Comic(
                    cursor.getInt(cursor.getColumnIndex(COLUMN_COMIC_ID)),
                    cursor.getInt(cursor.getColumnIndex(COLUMN_PUBLISH_YEAR)),
                    Comic.Publisher.valueOf(cursor.getString(cursor.getColumnIndex(COLUMN_PUBLISHER))),
                    cursor.getDouble(cursor.getColumnIndex(COLUMN_RATING)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_TITLE)),
                    cursor.getInt(cursor.getColumnIndex(COLUMN_ISSUE))
            );
            comics.add(comic);
        }
        return comics;
    }


    public void deleteComic(Comic comic){
        String deleteQuery = "DELETE * FROM "+TABLE_NAME+ " WHERE "+COLUMN_COMIC_ID + " = "+comic.getComicId();
        getWritableDatabase().rawQuery(deleteQuery, new String[]{COLUMN_PUBLISHER, COLUMN_ISSUE, COLUMN_TITLE}, null);
    }

}










