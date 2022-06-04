package cat.tecnocampus.mobileapps.practica2.HomarMasachsFrancescMeninoSuredaPau;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import cat.tecnocampus.mobileapps.practica2.HomarMasachsFrancescMeninoSuredaPau.Entities.Game;

public class GameListActivity extends AppCompatActivity {

    static GameAdapter gameAdapter;
    RecyclerView recyclerView;
    List<Game> games = new ArrayList<Game>();
    ApplicationViewModel applicationViewModel;
    String nickname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_list);

        recyclerView = findViewById(R.id.gameRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        nickname = getIntent().getStringExtra("nickname");

        if (gameAdapter==null){
            gameAdapter = new GameAdapter(this, games);
        }

        recyclerView.setAdapter(gameAdapter);

        applicationViewModel = new ApplicationViewModel(getApplication());
        applicationViewModel.getGameByNickname(nickname).observe(this, new Observer<List<Game>>() {
            @Override
            public void onChanged(List<Game> games) {
                gameAdapter.setGames(games);
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}