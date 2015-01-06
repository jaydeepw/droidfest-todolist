package com.intouchapp.todolistapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Database handler - all db operations handled by this class
 *
 * @author Niket
 */

public class DbHelper extends SQLiteOpenHelper {

    private static DbHelper _instance;
    private static final String TAG = DbHelper.class.getSimpleName();
    private SQLiteDatabase sqLiteDatabase;

    /**
     * Private constructor
     *
     * @param context
     */
    private DbHelper(Context context) {
        super(context, DbConfig.DATABASE_NAME, null, DbConfig.DATABASE_VERSION);
        sqLiteDatabase = this.getWritableDatabase();
    }

    /**
     * Single instance of DB helper through application life.
     *
     * @param context
     * @return - instance of the DB helper class.
     */
    public static DbHelper getInstance(Context context) {
        if (_instance == null) {
            _instance = new DbHelper(context);
        }

		/* if database is closed accidentally. it will reopen it here. */
        if (!_instance.sqLiteDatabase.isOpen()) {
            _instance.sqLiteDatabase = _instance.getWritableDatabase();
        }
        return _instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DbConfig.CREATE_TABLE_SURVEYS);
        Log.w(TAG, "creating tables...");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(TAG, "Upgrading database from version " + oldVersion + " to " + newVersion);
    }

    /**
     * This method is used to begin transaction.
     */
    public void beginTransaction() {
        try {
            sqLiteDatabase.beginTransaction();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is used to end transaction.
     */
    public void endTransaction() {
        try {
            sqLiteDatabase.setTransactionSuccessful();
            sqLiteDatabase.endTransaction();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param tableName
     * @param contentValues
     * @return row id
     */
    public long insert(String tableName, ContentValues contentValues) {
        return sqLiteDatabase.insert(tableName, null, contentValues);
    }

    /**
     * Update the database entry/ies
     *
     * @param table
     * @param values
     * @param whereClause
     * @param whereArgs
     * @return number of rows affected
     */
    public int update(String table, ContentValues values, String whereClause, String[] whereArgs) {
        return sqLiteDatabase.update(table, values, whereClause, whereArgs);
    }

    /**
     * Delete the rows in the mentioned table/ empties the table depending upon
     * the whereClause
     *
     * @param table
     * @param whereClause
     * @param whereArgs
     * @return number of rows affected
     */
    public int delete(String table, String whereClause, String[] whereArgs) {
        return sqLiteDatabase.delete(table, whereClause, whereArgs);
    }

    /**
     * Select query for the db operations.
     *
     * @param table
     * @param columns
     * @param selection
     * @param selectionArgs
     * @param groupBy
     * @param having
     * @param orderBy
     * @return cursor holding the data returned out of query
     */
    public Cursor select(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy) {
        return sqLiteDatabase.query(table, columns, selection, selectionArgs, groupBy, having, orderBy);
    }

    /**
     * Raw query to the Database.
     *
     * @param sql
     */
    public Cursor execSQL(String sql, String[] selectionArgs) {
        return sqLiteDatabase.rawQuery(sql, selectionArgs);
    }

    /**
     * Close database
     */
    public void closeDb() {
        sqLiteDatabase.close();
    }
}
