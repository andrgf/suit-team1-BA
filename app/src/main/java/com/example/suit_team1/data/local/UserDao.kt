package com.example.suit_team1.data.local

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.example.suit_team1.data.model.User


@Dao
interface UserDao {
    @Insert(onConflict = REPLACE)
    fun insert(user: User): Long

    @Delete
    fun delete(user: User): Int

    @Update
    fun update(user: User): Int

    @Query("select * from user")
    fun getUsers(): MutableList<User>

    @Query("select * from user where email = (:email) and password = (:password)")
    fun getLogin(email: String, password: String): Boolean
}