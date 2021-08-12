package com.boticario.model.login

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "login")
data class Login(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "username")
    val userName: String,
    @ColumnInfo(name = "password")
    val password: String,
    @ColumnInfo(name = "name")
    val name: String
) : Serializable
