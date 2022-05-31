package cat.tecnocampus.mobileapps.practica2.HomarMasachsFrancescMeninoSuredaPau.Entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Game {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int id;

    @ColumnInfo(name = "nickname")
    public String nickname;

    @ColumnInfo(name = "points")
    public int points;

    public void setId(int id) {
        this.id = id;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
