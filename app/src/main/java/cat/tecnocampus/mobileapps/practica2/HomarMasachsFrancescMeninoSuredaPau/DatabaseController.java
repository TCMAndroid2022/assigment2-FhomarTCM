package cat.tecnocampus.mobileapps.practica2.HomarMasachsFrancescMeninoSuredaPau;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import cat.tecnocampus.mobileapps.practica2.HomarMasachsFrancescMeninoSuredaPau.DAOs.ApplicationDAO;
import cat.tecnocampus.mobileapps.practica2.HomarMasachsFrancescMeninoSuredaPau.Entities.Game;
import cat.tecnocampus.mobileapps.practica2.HomarMasachsFrancescMeninoSuredaPau.Entities.Player;

public class DatabaseController {
    private ApplicationDAO applicationDAO;
    private LiveData<List<Game>> allGames, gameByNickname;
    private LiveData<List<Player>> allPlayers, playerByNickname;

    public DatabaseController(Application application) {
        ApplicationDatabase db = ApplicationDatabase.getDatabase(application);
        applicationDAO = db.applicationDAO();
        allGames = applicationDAO.getAllGames();
        allPlayers = applicationDAO.getAllPlayers();
    }

    public LiveData<List<Game>> getAllGames() {
        return allGames;
    }
    public LiveData<List<Game>> getGameByNickname(String nickname) {
        return applicationDAO.findGameByNickname(nickname);
    }
    public LiveData<List<Player>> getAllPlayers() {
        return allPlayers;
    }
    public LiveData<Player> getPlayerByNickname(String nickname) {
        return applicationDAO.findPlayerByNickname(nickname);
    }


    public void setGame(String nickname, int points) {
        Game game = new Game();
        game.setNickname(nickname);
        game.setPoints(points);
        new insertAsync(applicationDAO).execute(game);
    }

    public void setPlayer(String nickname, int games, int totalPoints) {
        Player player = new Player();
        player.setNickname(nickname);
        player.setGames(games);
        player.setTotalPoints(totalPoints);
        new insertAsync(applicationDAO).execute(player);
    }

    /*private static class insertAsyncTask extends AsyncTask<Todo, Void, Void> {
        private TodoDao asyncDao;

        insertAsyncTask(TodoDao dao) {
            asyncDao = dao;
        }

        @Override
        protected Void doInBackground(Todo... todos) {
            asyncDao.insertTodo(todos[0]);
            return null;
        }
    }*/

    private static class insertAsync {
        private ApplicationDAO asyncDao;
        private Executor executor = Executors.newSingleThreadExecutor();

        insertAsync(ApplicationDAO dao) {
            asyncDao = dao;
        }

        public void execute(Game game) {
            this.doInBackground(game);
        }
        public void execute(Player player) {
            this.doInBackground(player);
        }

        private void doInBackground(final Game game) {
            this.executor.execute(new Runnable() {
                @Override
                public void run() {
                    asyncDao.addGame(game);
                }
            });
        }
        private void doInBackground(final Player player) {
            this.executor.execute(new Runnable() {
                @Override
                public void run() {
                    asyncDao.addPlayer(player);
                }
            });
        }
    }

}
