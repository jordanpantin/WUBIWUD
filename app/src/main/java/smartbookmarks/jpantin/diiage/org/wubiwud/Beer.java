package smartbookmarks.jpantin.diiage.org.wubiwud;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.annotation.Nullable;

import java.util.ArrayList;

public class Beer
{
    public Beer(String nom, String desc, Double alc, Double price) {
        this.nom = nom;
        this.desc = desc;
        this.alc = alc;
        this.price = price;
    }

    public Beer()
    {

    }

    long id;
    String nom;
    String desc;
    Double alc;
    Double price;

    /**
     * Obtient un objet Beer à partir d'un cursor.
     * @param cursor
     * @return
     */
    public static ArrayList<Beer> fromCursor(Cursor cursor)
    {
        ArrayList<Beer> beers = new ArrayList<>();

        while(cursor.moveToNext())
        {
            Beer beer = new Beer();

            // Convertion Cursor to Beer.
            beer.id = cursor.getLong(cursor.getColumnIndex("id"));
            beer.nom = cursor.getString(cursor.getColumnIndex("nom"));
            beer.desc = cursor.getString(cursor.getColumnIndex("desc"));
            beer.alc = cursor.getDouble(cursor.getColumnIndex("alc"));
            beer.price = cursor.getDouble(cursor.getColumnIndex("price"));

            beers.add(beer);
        }

        return beers;
    }

    /**
     * Convertir l'objet Beer en ContentValues
     * @return
     */
    public ContentValues toContentValues()
    {
        ContentValues cv = new ContentValues();

        if (nom != null)
        {
            cv.put("nom", nom);
        }

        if (desc != null)
        {
            cv.put("desc", desc);
        }

        if (alc != null)
        {
            cv.put("alc", alc);
        }

        if (price != null)
        {
            cv.put("price", price);
        }

        return cv;
    }

    public String toString()
    {
        return this.nom;
    }
}
