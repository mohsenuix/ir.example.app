package ir.example.app.data.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import ir.example.app.data.db.entity.DataEntity;

@Dao
public interface DataDao {
    @Insert
    void insertOnlySingleMovie(DataEntity entities);

    @Insert
    void insertMultipleMovies(List<DataEntity> moviesList);

    @Query("SELECT * FROM DataEntity WHERE id =:id")
    DataEntity findById(int id);

    @Update
    void updateMovie(DataEntity entity);

    @Delete
    void deleteMovie(DataEntity entity);
}