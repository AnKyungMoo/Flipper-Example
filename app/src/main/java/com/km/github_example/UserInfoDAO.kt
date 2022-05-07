package com.km.github_example

import androidx.room.*

@Dao
interface UserInfoDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUserInfo(userInfo: UserInfoDTO)

    @Query("delete from userinfo where location = :location")
    suspend fun deleteUserInfoByLocation(location: String)
}
