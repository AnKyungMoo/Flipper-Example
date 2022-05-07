package com.km.github_example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CoroutineScope(Dispatchers.IO).launch {
            val userInfo = NetworkObject.getGithubApi().getUserInfo("AnKyungMoo")
            userInfo?.let {
                Log.d("github email: ", userInfo.email ?: "")
                Log.d("github location: ", userInfo.location ?: "")
                Log.d("github followers: ", userInfo.followers ?: "")
                Log.d("github following: ", userInfo.following ?: "")

                AppDatabase.getInstance(applicationContext)
                    .userInfoDAO()
                    .insertUserInfo(
                        UserInfoDTO(
                            id = userInfo.id,
                            url = userInfo.url,
                            name = userInfo.name,
                            email = userInfo.email,
                            location = userInfo.location,
                            followers = userInfo.followers,
                            following = userInfo.following
                        )
                    )
            }

            findViewById<Button>(R.id.btn_refresh).setOnClickListener {
                CoroutineScope(Dispatchers.IO).launch {
                    AppDatabase.getInstance(applicationContext)
                        .userInfoDAO()
                        .deleteUserInfoByLocation("Korea")
                }
            }
        }
    }
}
