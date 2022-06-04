package cat.tecnocampus.mobileapps.practica2.HomarMasachsFrancescMeninoSuredaPau;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import cat.tecnocampus.mobileapps.practica2.HomarMasachsFrancescMeninoSuredaPau.Entities.Game;
import cat.tecnocampus.mobileapps.practica2.HomarMasachsFrancescMeninoSuredaPau.Entities.Player;

public class ApplicationViewModel extends AndroidViewModel {
    private DatabaseController repository;
    private LiveData<List<Game>> allGames;
    private LiveData<List<Player>> allPlayers;

    public ApplicationViewModel(@NonNull Application application){
        super(application);
        repository = new DatabaseController(application);
        allGames = repository.getAllGames();
        allPlayers = repository.getAllPlayers();
    }

    LiveData<List<Game>> getAllGames(){return allGames;}

    LiveData<List<Game>> getGameByNickname(String nickname){
        return repository.getGameByNickname(nickname);
    }
    LiveData<List<Player>> getAllPlayers(){return allPlayers;}
    LiveData<Player> getPlayerByNickname(String nickname){
        return repository.getPlayerByNickname(nickname);
    }

    void insertGame(String nickname, int points){
        repository.setGame(nickname, points);
    }
    void insertPlayer(String nickname, int games, double totalPoints){
        repository.setPlayer(nickname, games, totalPoints);
    }
}
