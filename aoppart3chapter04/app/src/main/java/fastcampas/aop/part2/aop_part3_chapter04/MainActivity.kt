package fastcampas.aop.part2.aop_part3_chapter04

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.MotionEvent
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import fastcampas.aop.part2.aop_part3_chapter04.apdapter.BookAdapter
import fastcampas.aop.part2.aop_part3_chapter04.apdapter.HistoryAdapter
import fastcampas.aop.part2.aop_part3_chapter04.api.BookService
import fastcampas.aop.part2.aop_part3_chapter04.databinding.ActivityMainBinding
import fastcampas.aop.part2.aop_part3_chapter04.model.History
import fastcampas.aop.part2.aop_part3_chapter04.model.SearchBookDTO
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: BookAdapter
    private lateinit var historyAdapter: HistoryAdapter
    private lateinit var bookService: BookService
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initBookRecyclerView()
        initHistoryRecyclerView()
        initSearchEditText()

        db = getAppDatabase(this)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://openapi.naver.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        bookService = retrofit.create(BookService::class.java)

//        bookService.getBestSellerBooks(getString(R.string.interparkAPIKey))
//            .enqueue(object : Callback<BestSellerDTO> {
//                override fun onResponse(
//                    call: Call<BestSellerDTO>,
//                    response: Response<BestSellerDTO>
//                ) {
//                    // 성공 처리
//                    if (response.isSuccessful.not()) {
//                        return
//                    }
//                    response.body()?.let {
//                        Log.d(TAG, it.toString())
//
//                        it.books.forEach { book ->
//                            Log.d(TAG, book.toString())
//                        }
//                        adapter.submitList(it.books)
//                    }
//                }
//
//                override fun onFailure(call: Call<BestSellerDTO>, t: Throwable) {
//                    // 실패 처리
//                }
//            })
    }


    private fun search(keyword: String) {
        bookService.getBookByName(getString(R.string.naverClient_ID),(getString(R.string.naverClient_Secret)), keyword)
            .enqueue(object : Callback<SearchBookDTO> {
                override fun onResponse(
                    call: Call<SearchBookDTO>,
                    response: Response<SearchBookDTO>
                ) {
                    // 성공 처리
                    hideHistoryView()
                    saveSearchKeyword(keyword)

                    if (response.isSuccessful.not()) {
                        return
                    }
                    adapter.submitList(response.body()?.books.orEmpty())

                    response.body()?.let {
                        Log.d(TAG, it.toString())

                        it.books.forEach { book ->
                            Log.d(TAG, book.toString())
                        }
                    }
                }

                override fun onFailure(call: Call<SearchBookDTO>, t: Throwable) {
                    // 실패 처리
                    hideHistoryView()
                }
            })
    }

    private fun initBookRecyclerView() {
        adapter = BookAdapter(itemClickedListener = {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("bookModel", it)
            startActivity(intent)
        })
        binding.bookRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.bookRecyclerView.adapter = adapter
    }

    private fun initHistoryRecyclerView() {
        historyAdapter = HistoryAdapter(historyDeleteClickedListener = {
            deleteSearchKeyword(it)
        })
        binding.historyRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.historyRecyclerView.adapter = historyAdapter
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initSearchEditText(){
        binding.searchEditText.setOnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == MotionEvent.ACTION_DOWN) {
                search(binding.searchEditText.text.toString())
                return@setOnKeyListener true
            }
            return@setOnKeyListener false
        }

        binding.searchEditText.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN){
                showHistoryView()
            }
            return@setOnTouchListener false
        }
    }

    private fun showHistoryView() {
        binding.historyRecyclerView.isVisible = true
        Thread {
            val keywords = db.historyDao().getAll().reversed()
            runOnUiThread {
                binding.historyRecyclerView.isVisible = true
                historyAdapter.submitList(keywords.orEmpty())
            }
        }.start()
    }

    private fun hideHistoryView() {
        binding.historyRecyclerView.isVisible = false
    }

    private fun saveSearchKeyword(keyword: String) {
        Thread {
            db.historyDao().insertHistory(History(null, keyword))
        }.start()
    }

    private fun deleteSearchKeyword(keyword: String) {
        Thread {
            db.historyDao().delete(keyword)
            //todo view 갱신
            showHistoryView()
        }.start()
    }


    companion object {
        private const val TAG = "MainActivity"
    }
}