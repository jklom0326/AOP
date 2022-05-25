package fastcampas.aop.part2.aoppart3chapter06

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import fastcampas.aop.part2.aoppart3chapter06.chatlist.ChatListFragment
import fastcampas.aop.part2.aoppart3chapter06.home.HomeFragment
import fastcampas.aop.part2.aoppart3chapter06.mypage.MyPageFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val homeFragment = HomeFragment()
        val chatlistFragment = ChatListFragment()
        val myPageFragment = MyPageFragment()

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        //초기는 홈 프레그먼트
        replaceFragment(homeFragment)
        // 네비게이션뷰에 따라 변경
        bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> replaceFragment(homeFragment)
                R.id.chatList -> replaceFragment(chatlistFragment)
                R.id.myPage -> replaceFragment(myPageFragment)
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container_view, fragment)
            commit()
        }
    }
}