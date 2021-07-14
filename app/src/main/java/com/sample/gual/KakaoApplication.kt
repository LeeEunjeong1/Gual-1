package com.sample.gual

import android.app.Application
import com.kakao.sdk.common.KakaoSdk

// Android SDK를 사용하기 위해서는 가장 먼저 네이티브 앱 키로 초기화를 해야 합니다.
// Application을 상속한 클래스를 사용하고 있다면 다음과 같이 초기화할 수 있습니다.
class KakaoApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // 다른 초기화 코드들
        //hi 나는 은정이다

        // Kakako SDK 초기화
        KakaoSdk.init(this, "1c7f38ab5c70002127addff2544b6017")
    }
}

// AndroidManifest.xml의 'application'에도 Kakao SDK 초기화를 수행한 클래스의 이름을 설정해야 합니다.
// 위 예제에서는 GlobalApplication 클래스에서 초기화를 했으므로 아래와 같이 동일한 이름을 설정에 추가합니다.
// <application
//    <!-- android:name 설정 -->
//    android:name=".KakaoApplication"
//    ...
//>