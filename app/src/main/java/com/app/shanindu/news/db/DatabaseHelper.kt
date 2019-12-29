package io.ivan.demo.ormlite.db

import android.database.sqlite.SQLiteDatabase
import com.app.shanindu.news.app.App
import com.app.shanindu.news.model.User
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper
import com.j256.ormlite.support.ConnectionSource
import com.j256.ormlite.table.TableUtils


object DatabaseHelper : OrmLiteSqliteOpenHelper(App.instance, "news.db", null, 1) {

    override fun onCreate(database: SQLiteDatabase?, connectionSource: ConnectionSource?) {
        TableUtils.createTableIfNotExists(connectionSource, User::class.java)
    }

    override fun onUpgrade(database: SQLiteDatabase?, connectionSource: ConnectionSource?, oldVersion: Int, newVersion: Int) {
        TableUtils.dropTable<User, Any>(connectionSource, User::class.java, true)
        onCreate(database, connectionSource)
    }

}