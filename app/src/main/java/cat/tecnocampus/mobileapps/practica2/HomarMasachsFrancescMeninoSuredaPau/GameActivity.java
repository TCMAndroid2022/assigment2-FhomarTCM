package cat.tecnocampus.mobileapps.practica2.HomarMasachsFrancescMeninoSuredaPau;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}