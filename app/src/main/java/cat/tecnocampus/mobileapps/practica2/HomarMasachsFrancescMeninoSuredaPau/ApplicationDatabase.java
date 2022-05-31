package cat.tecnocampus.mobileapps.practica2.HomarMasachsFrancescMeninoSuredaPau;

import android.app.Application;
import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import cat.tecnocampus.mobileapps.practica2.HomarMasachsFrancescMeninoSuredaPau.DAOs.ApplicationDAO;
import cat.tecnocampus.mobileapps.practica2.HomarMasachsFrancescMeninoSuredaPau.Entities.Game;
import cat.tecnocampus.mobileapps.practica2.HomarMasachsFrancescMeninoSuredaPau.Entities.Player;

@Database(entities = {Game.class, Player.class}, version = 1)
public abstract class ApplicationDatabase extends RoomDatabase {
    private static ApplicationDatabase INSTANCE;

    public abstract ApplicationDAO applicationDAO();

    public static ApplicationDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), ApplicationDatabase.class, "database-test")
                    // .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}
