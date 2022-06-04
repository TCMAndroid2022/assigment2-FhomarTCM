package cat.tecnocampus.mobileapps.practica2.HomarMasachsFrancescMeninoSuredaPau;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cat.tecnocampus.mobileapps.practica2.HomarMasachsFrancescMeninoSuredaPau.Entities.Player;

public class RankActivity extends AppCompatActivity implements Adapter.OnNoteListener{

    static Adapter adapter;
    RecyclerView recyclerView;
    static List<Player> players = new ArrayList<Player>();
    ApplicationViewModel applicationViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);

        recyclerView = findViewById(R.id.ranking);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        if (adapter==null){
            adapter = new Adapter(this, players, this);
        }

        recyclerView.setAdapter(adapter);


        applicationViewModel = new ApplicationViewModel(getApplication());
        applicationViewModel.getAllPlayers().observe(this, new Observer<List<Player>>() {
            @Override
            public void onChanged(List<Player> playersChang) {
                adapter.setPlayers(playersChang);
                players = playersChang;
            }
        });




    }

    @Override
    public void onNoteClick(int position) {
        Intent intent = new Intent(this, GameListActivity.class);
        intent.putExtra("nickname", players.get(position).getNickname());
        startActivity(intent);
    }
}