package cat.tecnocampus.mobileapps.practica2.HomarMasachsFrancescMeninoSuredaPau;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cat.tecnocampus.mobileapps.practica2.HomarMasachsFrancescMeninoSuredaPau.Entities.Player;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>{

    Context context;
    List<Player> players;
    OnNoteListener onNoteListener;

    public Adapter(Context context, List<Player> players) {
        this.context = context;
        this.players = players;
    }

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.row_rank, parent, false);
        return new ViewHolder(view, onNoteListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.players.setText(players.get(position).getNickname());
        holder.points.setText(players.get(position).getTotalPoints());
        holder.games.setText(players.get(position).getGames());
    }

    @Override
    public int getItemCount() {
        return players.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView players, points, games;
        ConstraintLayout mainLayout;
        OnNoteListener onNoteListener;

        public ViewHolder(@NonNull View itemView, OnNoteListener onNoteListener) {
            super(itemView);
            players = itemView.findViewById(R.id.PlayerName);
            points = itemView.findViewById(R.id.Points);
            games = itemView.findViewById(R.id.GamesPlayed);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            this.onNoteListener = onNoteListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            //onNoteListener.onNoteClick(getBindingAdapterPosition());
        }
    }

    public interface OnNoteListener{
        void onNoteClick(int position);
    }
}
