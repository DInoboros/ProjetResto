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
 * Created by android on 10/03/2017.
 */

public class    Vins extends AppCompatActivity {

    ListView maListe ;
    Button reset;
    Button valider;

    List<String> courant = new ArrayList<String>();

    String[] data = new String[]{"Coca Cola   -   3€","Fanta   -   3€","Eau pétillante   -   2€","Vin Rouge   -   35€","Vin Blanc   -   35€","Champagne   -   35€","Biere   -   5€"};
    static Integer[] prixVins = new Integer[]{3,3,2,35,35,35,5};
    static List<String> resumeVins = new ArrayList<String>();
    static List<Integer> resumePrixVins = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vins);

        reset = (Button)findViewById(R.id.reset);
        valider = (Button)findViewById(R.id.valider);
        courant = new ArrayList<String>(Arrays.asList("none"));
        ArrayAdapter<String> adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,data);
        maListe =(ListView)findViewById(R.id.maListe);
        maListe.setAdapter(adapter);
        maListe.setOnItemClickListener(new AdapterView.OnItemClickListener (){
            public void onItemClick(AdapterView<?> arg0 , View v, int position, long id)
            {
                if (courant.contains(Integer.toString(position))) {
                    while (courant.contains(Integer.toString(position))){
                        courant.remove(Integer.toString(position));}
                    v.setBackgroundColor(getResources().getColor(R.color.blanc));
                    System.out.println("if");
                    System.out.println(courant.toString());
                } else {
                    courant.add(Integer.toString(position));
                    v.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

                    System.out.println("else");
                    System.out.println(courant);
                }

            }
        });

        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                while (resumeVins.size()>0){
                    resumeVins.remove(0);
                }
                for(int i = 0; i < courant.size()-1; i++){
                    resumeVins.add(data[Integer.parseInt(courant.get(i+1))]);
                    resumePrixVins.add(prixVins[Integer.parseInt(courant.get(i+1))]);
                }
                finish();
                startActivity(new Intent(Vins.this, Resume.class));
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                maListe.setAdapter(null);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(Vins.this,android.R.layout.simple_list_item_1, data);
                maListe.setAdapter(adapter);
                Log.i("select", String.valueOf(maListe));
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
                startActivity(new Intent(Vins.this, MainActivity.class));
                break;

            case R.id.formules:
                startActivity(new Intent(Vins.this, Formules.class));
                break;

            case R.id.vins:
                startActivity(new Intent(Vins.this, Vins.class));
                break;

            case R.id.addition:
                startActivity(new Intent(Vins.this, Resume.class));
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
