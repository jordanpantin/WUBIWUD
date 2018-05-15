package smartbookmarks.jpantin.diiage.org.wubiwud;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class WUBIWUDDbHelber extends SQLiteOpenHelper
{

    public static final String Name_Table_Beer = "beer";
    public static final String Name_Table_Collection = "collection";
    public static final String Name_Table_Ingredient = "ingredient";

    private final int VERSION = 3;

    public WUBIWUDDbHelber(Context context)
    {
        super(context, "wybiwyd", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        for (int i = 1; i < VERSION; i++)
        {
            upgradeTo(db, i);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        // Première solution
        for (int i = oldVersion; i < newVersion; i++)
        {
            upgradeTo(db, i);
        }

    }

    /**
     * Update la base selon la version voulue.
     * @param db la base de données.
     * @param numVersion le numéro de version.
     */
    private void upgradeTo(SQLiteDatabase db, int numVersion)
    {
        switch (numVersion)
        {
            // Version 1
            case 1 :
                db.execSQL("CREATE TABLE " + Name_Table_Beer + " ( 'id' INTEGER PRIMARY KEY AUTOINCREMENT, 'nom' TEXT, 'desc' TEXT, 'alc' NUMERIC, 'price' NUMERIC )");
                db.execSQL("CREATE TABLE " + Name_Table_Collection + " ( 'id' INTEGER PRIMARY KEY AUTOINCREMENT, 'nom' TEXT,'desc' TEXT)");
                db.execSQL("CREATE TABLE " + Name_Table_Ingredient + " ( 'id' INTEGER PRIMARY KEY AUTOINCREMENT, 'nom' TEXT, 'alc' NUMERIC, 'price' NUMERIC )");

                // Insert
                Beer chouffe = new Beer("Chouffe", "Bière blonde belge", 8.4, 3.25);
                db.insert(WUBIWUDDbHelber.Name_Table_Beer, null, chouffe.toContentValues());

                Beer heineken = new Beer("Heineken", "Bière blonde", (double) 5, 1.80);
                db.insert(WUBIWUDDbHelber.Name_Table_Beer, null, heineken.toContentValues());

                break;

            // Version 2
            case 2 :
                db.execSQL("CREATE TABLE GAME ( 'id' INTEGER PRIMARY KEY AUTOINCREMENT, 'nom' TEXT, 'desc' TEXT)");
                break;

            case 3 :
                ContentValues blonde = new ContentValues();
                blonde.put("nom", "blonde");

                ContentValues brune = new ContentValues();
                blonde.put("nom", "brune");

                ContentValues rousse = new ContentValues();
                blonde.put("nom", "rousse");

                db.insert("Game", null, blonde);
                db.insert("Game", null, brune);
                db.insert("Game", null, rousse);
                break;
        }
    }
}