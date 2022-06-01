package cat.tecnocampus.mobileapps.practica2.HomarMasachsFrancescMeninoSuredaPau;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        nicknameText = findViewById(R.id.nicknamePlainText);
        playButton = findViewById(R.id.playButton);





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


    /*String url = "https://palabras-aleatorias-public-api.herokuapp.com/random";
    TextView jsonResult;
    RequestQueue queue;


    queue = Volley.newRequestQueue(getApplicationContext());

    jsonResult = findViewById(R.id.jsonResult);
    callApiWord();

    void callApiWord(){
        JsonObjectRequest jsonArrayRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject firstUser = response;
                            jsonResult.setText(firstUser.getJSONObject("body").getString("Word"));
                        } catch (Exception ex) {
                            Log.d("SwA", "Error parsing json array");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("SwA", "Error in request ");
                    }
                }
        );
        queue.add(jsonArrayRequest);
    }*/

}