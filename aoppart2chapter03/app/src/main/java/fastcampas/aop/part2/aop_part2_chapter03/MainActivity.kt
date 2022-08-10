package fastcampas.aop.part2.aop_part2_chapter03

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.NumberPicker
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.edit

class MainActivity : AppCompatActivity() {

    private val numberPicker1: NumberPicker by lazy {
        findViewById<NumberPicker>(R.id.numberPicker1)
            .apply {
                minValue = 0
                maxValue = 9
            }
    }
    private val numberPicker2: NumberPicker by lazy {
        findViewById<NumberPicker>(R.id.numberPicker2)
            .apply {
                minValue = 0
                maxValue = 9
            }
    }
    private val numberPicker3: NumberPicker by lazy {
        findViewById<NumberPicker>(R.id.numberPicker3)
            .apply {
                minValue = 0
                maxValue = 9
            }
    }

    private val openButton: AppCompatButton by lazy {
        findViewById(R.id.openButton)
    }

    private val changePasswordButton: AppCompatButton by lazy {
        findViewById(R.id.changePasswordButton)
    }

    private var changePasswordMode = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        numberPicker1
        numberPicker2
        numberPicker3

        openButton.setOnClickListener {
            // SharedPreferences 사용
            if (changePasswordMode) {
                Toast.makeText(this, "비밀번호 변경 중입니다", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val passwordPreference = getSharedPreferences("password", Context.MODE_PRIVATE)

            val passwordFromUser =
                "${numberPicker1.value}${numberPicker2.value}${numberPicker3.value}"

            if (passwordPreference.getString("password", "000").equals(passwordFromUser)) {
                startActivity(Intent(this, DiaryActivity::class.java))
            } else {
                showErrorAlerDialog()
            }
        }
        changePasswordButton.setOnClickListener {

            val passwordPreference = getSharedPreferences("password", Context.MODE_PRIVATE)
            val passwordFromUser = "${numberPicker1.value}${numberPicker2.value}${numberPicker3.value}"

            if (changePasswordMode) {
                // 번호를 저장하는 기능
                val passwordPreferences = getSharedPreferences("password", Context.MODE_PRIVATE)
//                 원래 commit을 해줘야해ㅔㅆ는데 ktx로 바뀌면서 함수로 받을수 있음

//                passwordPreferences.edit {
//                    val passwordFromUser =
//                        "${numberPicker1.value}${numberPicker2.value}${numberPicker3.value}"
//                    putString("password",passwordFromUser)
//                    commit()
//                }

                passwordPreferences.edit(true){
                    putString("password",passwordFromUser)
                }

                changePasswordMode =false
                changePasswordButton.setBackgroundColor(Color.BLACK)

            } else {
                // changepasswordMode가 활성화 :: 비밀번호가 맞는지를 체크

                if (passwordPreference.getString("password", "000").equals(passwordFromUser)) {
                    changePasswordMode = true
                    Toast.makeText(this, "변경할 패스워드를 입력해 주세요", Toast.LENGTH_SHORT).show()
                    changePasswordButton.setBackgroundColor(Color.RED)
                } else {
                    showErrorAlerDialog()
                }
            }
        }
    }

    private fun showErrorAlerDialog(){
        AlertDialog.Builder(this)
            .setTitle("실패 !!")
            .setMessage("비밀번호가 잘못되었습니다.")
            .setPositiveButton("닫 기") { _, _ -> }
            .create()
            .show()
    }
}