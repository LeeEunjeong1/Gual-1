package com.sample.gual

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.kakao.sdk.user.UserApiClient
import com.sample.gual.databinding.ActivityAddressMainBinding
import com.sample.gual.databinding.ActivityLoginBinding


private const val TAG = "LoginActivity"

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var binding2: ActivityAddressMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        binding2= ActivityAddressMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //로그인 공통 콜백 구성
        binding.login.setOnClickListener {
            if (UserApiClient.instance.isKakaoTalkLoginAvailable(this)) {
                UserApiClient.instance.loginWithKakaoTalk(this) { token, error ->
                    Log.i(TAG, "loginWithKaKaoTalk $token $error")
                    updateLoginInfo()
                }
            } else {
                UserApiClient.instance.loginWithKakaoAccount(this) { token, error ->
                    Log.i(TAG, "loginWithKakaoAccount $token $error")
                    updateLoginInfo()
                }
            }
        }
        updateLoginInfo()
    }

    private fun updateLoginInfo() {
        UserApiClient.instance.me { user, error ->
            user?.let {
                Log.i(
                    TAG,
                    "updateLoginInfo: ${user.id} ${user.kakaoAccount?.email} ${user.kakaoAccount?.profile?.nickname} ${user.kakaoAccount?.profile?.thumbnailImageUrl}"
                )
                setContentView(binding2.root)
                binding2.nickname.text = user.kakaoAccount?.profile?.nickname
                Glide.with(this).load(user.kakaoAccount?.profile?.thumbnailImageUrl).circleCrop()
                    .into(binding2.profile)

            }
            error?.let {
                binding2.nickname.text = null
                binding2.profile.setImageBitmap((null))
            }
        }
    }
}
