package cat.tecnocampus.mobileapps.practica2.HomarMasachsFrancescMeninoSuredaPau;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import cat.tecnocampus.mobileapps.practica2.HomarMasachsFrancescMeninoSuredaPau.Entities.Player;

public class RankActivity extends AppCompatActivity implements Adapter.OnNoteListener{

    static Adapter adapter;
    RecyclerView recyclerView;
    List<Player> players;
    ApplicationViewModel applicationViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);

        applicationViewModel = new ApplicationViewModel(getApplication());
        recyclerView = findViewById(R.id.ranking);
        players = new ArrayList<Player>();

        if (adapter==null){
            adapter = new Adapter(this, players, this);
        }

        recyclerView.setAdapter(adapter);


    }

    @Override
    public void onNoteClick(int position) {

    }
}