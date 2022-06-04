package cat.tecnocampus.mobileapps.practica2.HomarMasachsFrancescMeninoSuredaPau.Entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Player {


    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "nickname")
    public String nickname;

    @ColumnInfo(name = "games")
    public int games;

    @ColumnInfo(name = "total_points")
    public int totalPoints;

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setGames(int games) {
        this.games = games;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    @NonNull
    public String getNickname() {
        return nickname;
    }

    public int getGames() {
        return games;
    }

    public int getTotalPoints() {
        return totalPoints;
    }
}
