package com.app.shanindu.news.model

import com.j256.ormlite.dao.Dao
import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable
import io.ivan.demo.ormlite.db.DatabaseHelper

@DatabaseTable(tableName = "user")
data class User(

        @DatabaseField(generatedId = true)
        var id: Int? = null,

        @DatabaseField
        var firstName: String = "",

        @DatabaseField
        var lastName: String = "",

        @DatabaseField
        var username: String = ""


)

class UserDao {

    companion object {
        lateinit var dao: Dao<User, Int>
    }

    init {
        dao = DatabaseHelper.getDao(User::class.java)
    }

    fun add(table: User) = dao.createOrUpdate(table)

    fun update(table: User) = dao.update(table)

    fun delete(table: User) = dao.delete(table)

    fun queryForAll() = dao.queryForAll()

    fun getUser() = dao.queryForId(1)

    fun removeAll() {
        for (table in queryForAll()) {
            dao.delete(table)
        }
    }

}