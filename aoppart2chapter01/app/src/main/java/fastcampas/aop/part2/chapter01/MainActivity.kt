package fastcampas.aop.part2.chapter01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //에디트텍스트
        //명시적
        val heightEditText: EditText = findViewById(R.id.heightEditText)
        //추론적
        val weightEditText = findViewById<EditText>(R.id.weightEditText)

        //버튼
        val resultButton = findViewById<Button>(R.id.resultButton)

        resultButton.setOnClickListener {
            Log.d("MainActivity","ResultButton이 클릭되었습니다.")

            // 에디트텍스트가 비었을때 예외처리
            if (heightEditText.text.isEmpty() || weightEditText.text.isEmpty()){
                Toast.makeText(this, "빈 값이 있습니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            // 이 아래로는 절대 빈 값이 올 수 없음
            val height: Int = heightEditText.text.toString().toInt()
            val weight: Int = weightEditText.text.toString().toInt()

            val intent = Intent(this, ResultActivity::class.java)

            intent.putExtra("height",height)
            intent.putExtra("weight",weight)

            startActivity(intent)

        }
    }
}