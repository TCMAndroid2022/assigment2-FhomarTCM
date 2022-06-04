package cat.tecnocampus.mobileapps.practica2.HomarMasachsFrancescMeninoSuredaPau.DAOs;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import cat.tecnocampus.mobileapps.practica2.HomarMasachsFrancescMeninoSuredaPau.Entities.Game;
import cat.tecnocampus.mobileapps.practica2.HomarMasachsFrancescMeninoSuredaPau.Entities.Player;

@Dao
public interface ApplicationDAO {

    @Query("SELECT * FROM Game")
    LiveData<List<Game>> getAllGames();

    @Query("SELECT * FROM Game WHERE nickname = :nickname")
    LiveData<List<Game>> findGameByNickname(String nickname);

    @Query("SELECT * FROM Player ORDER BY total_points DESC")
    LiveData<List<Player>> getAllPlayers();

    @Query("SELECT * FROM Player WHERE nickname = :nickname")
    LiveData<Player> findPlayerByNickname(String nickname);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addPlayer(Player player);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addGame(Game game);
}
