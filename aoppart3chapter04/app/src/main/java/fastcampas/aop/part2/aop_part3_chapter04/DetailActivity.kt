package fastcampas.aop.part2.aop_part3_chapter04

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.bumptech.glide.Glide
import fastcampas.aop.part2.aop_part3_chapter04.databinding.ActivityDetailBinding
import fastcampas.aop.part2.aop_part3_chapter04.model.Book
import fastcampas.aop.part2.aop_part3_chapter04.model.Review

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDetailBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "historyDB"
        ).build()

        val bookModel = intent.getParcelableExtra<Book>("bookModel")

        binding.titleTextView.text = bookModel?.title.orEmpty()

        Glide
            .with(binding.coverImageView.context)
            .load(bookModel?.coverSmallUrl.orEmpty())
            .into(binding.coverImageView)

        binding.descripstionTextView.text = bookModel?.description.orEmpty()

        Thread{
            val review = db.reviewDao().getOne(bookModel?.id.toString())
            runOnUiThread{
                binding.reviewEditText.setText(review.review.orEmpty())
            }
        }.start()

        binding.saveButton.setOnClickListener {
            Thread {
                db.reviewDao().saveReview(
                    Review(
                        bookModel?.id.toString(),
                        binding.reviewEditText.text.toString()
                    )
                )

            }.start()
        }
    }

}