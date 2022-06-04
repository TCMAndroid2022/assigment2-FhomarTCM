package cat.tecnocampus.mobileapps.practica2.HomarMasachsFrancescMeninoSuredaPau;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {


    private EditText nicknameText;
    private Button playButton;
    private ApplicationViewModel applicationViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        applicationViewModel = new ApplicationViewModel(getApplication());
        applicationViewModel.insertPlayer("Juan", 23, 24);
        applicationViewModel.getPlayerByNickname("Juan");

        nicknameText = findViewById(R.id.nicknamePlainText);
        playButton = findViewById(R.id.playButton);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.actionbar, menu);

        return super.onCreateOptionsMenu(menu);
    }

    public void openRanking(MenuItem item) {
        Intent intent = new Intent(this, RankActivity.class);
        startActivity(intent);
    }

    public void readNickname(View view) {
        String nickname = nicknameText.getText().toString();
        if(nickname.equals("")){
            Toast.makeText(this, "No nickname.", Toast.LENGTH_SHORT).show();
        }

        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("nickname", nickname);
        startActivity(intent);
    }




}