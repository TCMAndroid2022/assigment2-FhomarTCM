package cat.tecnocampus.mobileapps.practica2.HomarMasachsFrancescMeninoSuredaPau;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import java.text.Collator;

import org.json.JSONObject;

import cat.tecnocampus.mobileapps.practica2.HomarMasachsFrancescMeninoSuredaPau.Entities.Player;

public class GameActivity extends AppCompatActivity {

    ApplicationViewModel applicationViewModel;
    String url = "https://palabras-aleatorias-public-api.herokuapp.com/random";
    TextView jsonResult, winnerTV, pointsTV;
    RequestQueue queue;
    String word, nickname;
    String[] wordArr;
    String[] secretWord;
    int introducedChars;
    EditText enterSolutionET;
    boolean fullWordFailed = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        applicationViewModel = new ApplicationViewModel(getApplication());

        queue = Volley.newRequestQueue(getApplicationContext());

        jsonResult = findViewById(R.id.jsonResult);
        enterSolutionET = findViewById(R.id.enterSolutionET);
        winnerTV = findViewById(R.id.winnerTV);
        pointsTV = findViewById(R.id.points);

        getData();
        callApiWord();

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    public void enterChar(View view) {
        System.out.println(word);
        String enteredWord = enterSolutionET.getText().toString();
        if(enteredWord.length()>1){
            checkFullWord(enteredWord);
        }else if(enteredWord.equals("")){
            Toast.makeText(this, "Please introduce something", Toast.LENGTH_SHORT).show();
        }else{
            introducedChars++;
            checkOneChar(enteredWord.charAt(0));
            enterSolutionET.setText("");
        }

        if(fullWordFailed){
            winnerTV.setText(R.string.failText);
            winnerTV.setVisibility(View.VISIBLE);
            pointsTV.setText(String.valueOf(checkPoints()));
            pointsTV.setVisibility(View.VISIBLE);
            jsonResult.setText(arrayToString(wordArr));
            setData();
        }

        if(checkIfWinner()){
            winnerTV.setText(R.string.winnerText);
            winnerTV.setVisibility(View.VISIBLE);
            pointsTV.setText(String.valueOf(checkPoints()));
            pointsTV.setVisibility(View.VISIBLE);
            setData();
        }


    }

    void callApiWord() {
        JsonObjectRequest jsonArrayRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject firstUser = response;
                            word = firstUser.getJSONObject("body").getString("Word");
                            wordToSecret(word);
                            jsonResult.setText(arrayToString(secretWord));

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
    }

    private void getData(){
        if(getIntent().hasExtra("nickname")){
            nickname = getIntent().getStringExtra("nickname");
        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    private void setData(){
        Player pl;
        LiveData<Player> playerLD = applicationViewModel.getPlayerByNickname(nickname);
        if (playerLD.getValue() != null){
            pl = playerLD.getValue();
            System.out.println("Yo ya existia");
        }else{
            pl = new Player();
            System.out.println("Y no existia");
        }

        pl.setNickname(nickname);
        pl.setGames(pl.getGames() + 1);
        pl.setTotalPoints(pl.getTotalPoints() + checkPoints());

        applicationViewModel.insertPlayer(pl.getNickname(), pl.getGames(), pl.getTotalPoints());

    }

    void wordToSecret(String word){
        wordArr = new String[word.length()];
        secretWord = new String[word.length()];
        for (int i = 0; i < word.length(); i++){
            wordArr[i] = String.valueOf(word.charAt(i));
            if(String.valueOf(word.charAt(i)).equals(" ")){
                secretWord[i] = "  ";
            }else{
                secretWord[i] = "_ ";
            }

        }
    }

    String arrayToString(String[] array){
        String word = "";
        for (int i = 0; i < array.length; i++){
            word += array[i];
        }
        return word;
    }

    void checkOneChar(char character){
        final Collator collator = Collator.getInstance();
        collator.setStrength(Collator.NO_DECOMPOSITION);
        for (int i = 0; i < wordArr.length; i++){
            if (collator.compare(wordArr[i], String.valueOf(character)) == 0){
                secretWord[i] = wordArr[i];
            }
        }
        jsonResult.setText(arrayToString(secretWord));
    }


    private void checkFullWord(String word) {

        final Collator collator = Collator.getInstance();
        collator.setStrength(Collator.NO_DECOMPOSITION);
        for (int i = 0; i < wordArr.length; i++){
            if (wordArr.length == word.length()){
                if (collator.compare(wordArr[i], String.valueOf(word.charAt(i))) == 0){
                    secretWord[i] = wordArr[i];
                }else{
                    introducedChars = 999;
                    fullWordFailed = true;
                }
            }else{
                introducedChars = 999;
                fullWordFailed = true;
            }

        }
        jsonResult.setText(arrayToString(secretWord));
    }

    private boolean checkIfWinner(){
        boolean isWinner = true;
        for (int i = 0; i < secretWord.length; i++){
            if(secretWord[i] == "_ "){
                isWinner = false;
                break;
            }
        }
        return isWinner;
    }

    private double checkPoints(){
        if((((double)secretWord.length - (double)introducedChars)/ (double)secretWord.length) * 10 < 0){
            return 0;
        }else{
            return (((double)secretWord.length - (double)introducedChars)/ (double)secretWord.length) * 10;
        }

    }

}