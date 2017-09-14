package projet.projetresto;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Iterator;


/**
 * Created by android on 17/03/2017.
 */



public class Resume extends AppCompatActivity {
    ListView listeResumeFormules;
    ListView listeResumeVins;
    Button valider ;
    TextView Total ;
    int total ;
    Iterator<Integer> iterator = Vins.resumePrixVins.iterator();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resume);
        valider=(Button)findViewById(R.id.validerCommande);
        Total=(TextView)findViewById(R.id.total);
        total=0;
        
        while (iterator.hasNext()) {
            total=total+iterator.next();
        }
        iterator = Formules.resumePrixFormules.iterator();
        while (iterator.hasNext()) {
            total=total+iterator.next();
        }

        Total.setText("Total = "+ total);

        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getApplicationContext();
                CharSequence text = "Commande validée , veuillez patienter";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                valider.setEnabled(false);

            }
        });
        listeResumeFormules = (ListView)findViewById(R.id.resumeFormules);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Resume.this,android.R.layout.simple_list_item_1,Formules.resumeFormules);
        listeResumeFormules.setAdapter(adapter);

        listeResumeVins = (ListView)findViewById(R.id.resumeVins);
        adapter = new ArrayAdapter<String>(Resume.this,android.R.layout.simple_list_item_1,Vins.resumeVins);
        listeResumeVins.setAdapter(adapter);
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
                startActivity(new Intent(Resume.this, MainActivity.class));
                break;

            case R.id.formules:
                startActivity(new Intent(Resume.this, Formules.class));
                break;

            case R.id.vins:
                startActivity(new Intent(Resume.this, Vins.class));
                break;

            case R.id.addition:
                startActivity(new Intent(Resume.this, Resume.class));
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
