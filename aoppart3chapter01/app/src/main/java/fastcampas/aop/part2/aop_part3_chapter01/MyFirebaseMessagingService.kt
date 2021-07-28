package fastcampas.aop.part2.aop_part3_chapter01

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService: FirebaseMessagingService() {
    /*
    새 기기에서 앱복원
    사용자가 앱삭제/ 재설치  ==> 토큰이 초기화 될 수 있기때문에
    사용자가 앱데이터 소거       토큰이 갱신될때마다 서버에 토큰을 갱신해 줘야함
     */
// 이때 onNewToken을 씀
    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
    }
}