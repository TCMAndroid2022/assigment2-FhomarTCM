package cat.tecnocampus.mobileapps.practica2.HomarMasachsFrancescMeninoSuredaPau;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cat.tecnocampus.mobileapps.practica2.HomarMasachsFrancescMeninoSuredaPau.Entities.Game;
import cat.tecnocampus.mobileapps.practica2.HomarMasachsFrancescMeninoSuredaPau.Entities.Player;

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.ViewHolder>{

    Context context;
    List<Game> games;

    public GameAdapter(Context context, List<Game> games) {
        this.context = context;
        this.games = games;
    }

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.row_game, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.id.setText(String.valueOf(games.get(position).getId()));
        holder.points.setText(String.valueOf(games.get(position).getPoints()));
        holder.nickname.setText(games.get(position).getNickname());
    }

    @Override
    public int getItemCount() {
        return games.size();
    }

    public void setGames(List<Game> games){
        this.games = games;
        notifyDataSetChanged();;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView id, points, nickname;
        ConstraintLayout mainLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.gameID);
            points = itemView.findViewById(R.id.gamePoints);
            nickname = itemView.findViewById(R.id.gameNick);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }

    }
}
