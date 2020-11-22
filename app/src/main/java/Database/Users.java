package Database;

import android.provider.BaseColumns;

public final class Users {

    private Users() {}

    public static class UsersInfo implements BaseColumns {
        public static final String TABLE_NAME = "User";
        public static final String COLUMN_NAME_USERNAME = "Username";
        public static final String COLUMN_NAME_PASSWORD = "Password";
        public static final String COLUMN_NAME_TYPE = "Type";
    }
}
