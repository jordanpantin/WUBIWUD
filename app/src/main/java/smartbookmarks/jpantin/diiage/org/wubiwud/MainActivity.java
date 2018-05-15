package smartbookmarks.jpantin.diiage.org.wubiwud;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AsyncTask<URL, Integer, ArrayList<Beer>> task = new AsyncTask<URL, Integer, ArrayList<Beer>>()
        {
            ArrayList<Beer> beers = new ArrayList<>();

            @Override
            protected ArrayList<Beer> doInBackground(URL... urls)
            {
                // Initialisation du helper
                WUBIWUDDbHelber helper = new WUBIWUDDbHelber(MainActivity.this);

                SQLiteDatabase db = helper.getWritableDatabase();

                try
                {
                    db.beginTransaction();

                    // Delete de l'entity
                    //db.delete(WUBIWUDDbHelber.Name_Table_Beer, "id = ?", new String[] { String.valueOf(idChouffe) });

                    if (true)
                    {
                        throw  new Exception("Impossible de supprimer la bière");
                    }

                    db.setTransactionSuccessful();

                }  catch (Exception e) {
                    //Error in between database transaction
                    e.printStackTrace();
                }  finally {
                    db.endTransaction();
                }

                // Get all beers from database.
                Cursor cursor = db.query(WUBIWUDDbHelber.Name_Table_Beer
                        , new String[]{"*"}
                        , null
                        , null, null,null, null);


                beers = Beer.fromCursor(cursor);

                // Fermer la connection au cursor et à la base
                cursor.close();
                db.close();

                return beers;
            }

            @Override
            protected void onPostExecute(ArrayList<Beer> beers)
            {
                super.onPostExecute(beers);

                ListView listView = findViewById(R.id.listBeers);

                listView.setAdapter(new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, beers));
            }
        }.execute();
    }
}