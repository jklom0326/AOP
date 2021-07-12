package fastcampas.aop.part2.aop_part2_chapter_04

import androidx.room.Database
import androidx.room.RoomDatabase
import fastcampas.aop.part2.aop_part2_chapter_04.dao.HistoryDao
import fastcampas.aop.part2.aop_part2_chapter_04.model.History

@Database(entities = [History::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun historyDao(): HistoryDao

}