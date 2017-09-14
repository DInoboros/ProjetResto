package projet.projetresto;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by mvalier on 10/03/2017.
 */

public class Formules extends AppCompatActivity {

    ListView listeFormules;
    Button valider;
    Button reset;
    List<String> courant = new ArrayList<String>();
    String[] formules = new String[]{"Entrée - Plat - Dessert   -   35€","Entrée - Plat   -   25€","Plat - Dessert   -   25€","Menu Enfant   -   15€","Entrée   -   10€","Plat   -   20€","Dessert   -   5€"};
    static Integer[] prixFormules = new Integer[]{35,25,25,15,10,20,5};
    static List<String> resumeFormules = new ArrayList<String>();
    static List<Integer> resumePrixFormules = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formules);

        listeFormules = (ListView)findViewById(R.id.liste_formules);
        courant = new ArrayList<String>(Arrays.asList("none"));
        valider = (Button)findViewById(R.id.valider);
        reset = (Button)findViewById(R.id.reset);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Formules.this,android.R.layout.simple_list_item_1, formules);
        listeFormules.setAdapter(adapter);
        listeFormules.setOnItemClickListener(new AdapterView.OnItemClickListener (){
            public void onItemClick(AdapterView<?> arg0 , View v, int position, long id)
            {
                if (courant.contains(Integer.toString(position))) {
                    while (courant.contains(Integer.toString(position))){
                        courant.remove(Integer.toString(position));}
                    v.setBackgroundColor(getResources().getColor(R.color.blanc));
                } else {
                    courant.add(Integer.toString(position));
                    v.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

                }
            }
        });

        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                while (resumeFormules.size()>0){
                    resumeFormules.remove(0);
                }
                for(int i = 0; i < courant.size()-1; i++){
                    resumeFormules.add(formules[Integer.parseInt(courant.get(i+1))]);
                    resumePrixFormules.add(prixFormules[Integer.parseInt(courant.get(i+1))]);
                }
                finish();
                startActivity(new Intent(Formules.this, Vins.class));
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listeFormules.setAdapter(null);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(Formules.this,android.R.layout.simple_list_item_1, formules);
                listeFormules.setAdapter(adapter);
                while (courant.size()>1){
                    courant.remove(1);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.accueil:
                startActivity(new Intent(Formules.this, MainActivity.class));
                break;

            case R.id.formules:
                startActivity(new Intent(Formules.this, Formules.class));
                break;

            case R.id.vins:
                startActivity(new Intent(Formules.this, Vins.class));
                break;

            case R.id.addition:
                startActivity(new Intent(Formules.this, Resume.class));
                break;

            case R.id.quitter:
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
                break;
        }
        return true;
    }


}
