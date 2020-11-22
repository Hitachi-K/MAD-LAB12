package Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "labsheet12.db";
    
    //SQL ENTRIES FOR TABLE USER
    private static final String SQL_CREATE_ENTRIES_USER =
            "CREATE TABLE " + Users.UsersInfo.TABLE_NAME + " (" +
                    Users.UsersInfo._ID + " INTEGER PRIMARY KEY," +
                    Users.UsersInfo.COLUMN_NAME_USERNAME + " TEXT," +
                    Users.UsersInfo.COLUMN_NAME_PASSWORD + " TEXT," +
                    Users.UsersInfo.COLUMN_NAME_TYPE + " TEXT)";

    private static final String SQL_DELETE_ENTRIES_USER =
            "DROP TABLE IF EXISTS " + Users.UsersInfo.TABLE_NAME;

    //SQL ENTRIES FOR TABLE MESSAGE
    private static final String SQL_CREATE_ENTRIES_MESSAGE =
            "CREATE TABLE " + Message.MessageData.TABLE_NAME + " (" +
                    Message.MessageData._ID + " INTEGER PRIMARY KEY,"  +
                    Message.MessageData.COLUMN_NAME_USERNAME + " TEXT," +
                    Message.MessageData.COLUMN_NAME_SUBJECT + " TEXT," +
                    Message.MessageData.COLUMN_NAME_MESSAGE + " TEXT)";

    private static final String SQL_DELETE_ENTRIES_MESSAGE =
            "DROP TABLE IF EXISTS " + Message.MessageData.TABLE_NAME;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES_USER);
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES_MESSAGE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES_USER);
        sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES_MESSAGE);
        onCreate(sqLiteDatabase);
    }

    // method to add user
    public long addUserData(String Username, String Password, String type) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        //create a new map of values, where column names are the keys
        ContentValues contentValues = new ContentValues();
        contentValues.put(Users.UsersInfo.COLUMN_NAME_USERNAME, Username);
        contentValues.put(Users.UsersInfo.COLUMN_NAME_PASSWORD, Password);
        contentValues.put(Users.UsersInfo.COLUMN_NAME_TYPE, type);

        long newRowID = sqLiteDatabase.insert(Users.UsersInfo.TABLE_NAME, null, contentValues);
        sqLiteDatabase.close();
        return newRowID;
    }

    // method to add messages
    public long addMessage(String username, String subject, String message) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Message.MessageData.COLUMN_NAME_USERNAME, username);
        contentValues.put(Message.MessageData.COLUMN_NAME_SUBJECT, subject);
        contentValues.put(Message.MessageData.COLUMN_NAME_MESSAGE, message);

        long newRowID = sqLiteDatabase.insert(Message.MessageData.TABLE_NAME, null, contentValues);
        sqLiteDatabase.close();
        return newRowID;
    }

    //method to validate login credentials
    public int validateUser(String username, String password) {

        String[] projection = {
                Users.UsersInfo._ID
        };

        SQLiteDatabase db = this.getReadableDatabase();

        //selection criteria
        String selectionTeacher = Users.UsersInfo.COLUMN_NAME_USERNAME + " = ?"
                + " AND " + Users.UsersInfo.COLUMN_NAME_PASSWORD + " = ?"
                + " AND " + Users.UsersInfo.COLUMN_NAME_TYPE + " = ?";

        String selectionStudent = Users.UsersInfo.COLUMN_NAME_USERNAME + " = ?"
                + " AND " + Users.UsersInfo.COLUMN_NAME_PASSWORD + " = ?"
                + " AND " + Users.UsersInfo.COLUMN_NAME_TYPE + " = ?";

        String [] selectionArgsTeacher = {username, password, "Teacher"};
        String [] selectionArgsStudent = {username, password, "Student"};

        Cursor cursorTeacher = db.query(Users.UsersInfo.TABLE_NAME,
                projection,
                selectionTeacher,
                selectionArgsTeacher,
                null,
                null,
                null);

        Cursor cursorStudent = db.query(Users.UsersInfo.TABLE_NAME,
                projection,
                selectionStudent,
                selectionArgsStudent,
                null,
                null,
                null);

        int cursorCountTeacher = cursorTeacher.getCount();
        int cursorCountStudent = cursorStudent.getCount();

        cursorTeacher.close();
        cursorStudent.close();
        db.close();

        if (cursorCountTeacher > 0) {
            return 1;
        }
        else if (cursorCountStudent > 0) {
            return 2;
        }
        else {
            return 0;
        }
    }

    //method to retrieve all the messages
    public List<Message> getAllmsgs() {
        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {
                BaseColumns._ID,
                Message.MessageData.COLUMN_NAME_SUBJECT,
                Message.MessageData.COLUMN_NAME_MESSAGE,
                Message.MessageData.COLUMN_NAME_USERNAME
        };

        // sorting the messages in descending order by their primary key
        String sortOrder = Message.MessageData._ID + " DESC";

        Cursor cursor = db.query(
                Message.MessageData.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                sortOrder
        );

        List messages = new ArrayList<>();

        while (cursor.moveToNext()) {
            Message msg = new Message();
            msg.setMessageID(Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(Message.MessageData._ID))));
            msg.setUn(cursor.getString(cursor.getColumnIndexOrThrow(Message.MessageData.COLUMN_NAME_USERNAME)));
            msg.setSubject(cursor.getString(cursor.getColumnIndexOrThrow(Message.MessageData.COLUMN_NAME_SUBJECT)));
            msg.setMessage(cursor.getString(cursor.getColumnIndexOrThrow(Message.MessageData.COLUMN_NAME_MESSAGE)));
            messages.add(msg);
        }
        cursor.close();
        db.close();

        return messages;
    }


}
