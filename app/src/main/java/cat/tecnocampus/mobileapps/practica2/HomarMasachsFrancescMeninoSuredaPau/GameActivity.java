package cat.tecnocampus.mobileapps.practica2.HomarMasachsFrancescMeninoSuredaPau;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class GameActivity extends AppCompatActivity {

    ApplicationViewModel applicationViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        applicationViewModel = new ApplicationViewModel(getApplication());
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    public void pushData(View view) {
        applicationViewModel.insertPlayer("Juan", 23, 24);
    }
}