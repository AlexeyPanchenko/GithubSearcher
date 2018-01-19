package ru.alexeyp.data.db;


import android.arch.persistence.room.Room;
import android.content.Context;

public class DBHelper {

    public static UserDatabase createUserDatabase(Context context) {
        return Room.databaseBuilder(context, UserDatabase.class, "users-db").build();
    }
}
