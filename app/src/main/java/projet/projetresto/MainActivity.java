package projet.projetresto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import static android.R.attr.value;
import static projet.projetresto.R.layout.formules;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Intent myIntent = null;
                if(position == 0){
                    myIntent = new Intent(MainActivity.this, Formules.class);
                }

                else if(position == 1){
                    myIntent = new Intent(MainActivity.this, Vins.class);
                }

                MainActivity.this.startActivity(myIntent);
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
                startActivity(new Intent(MainActivity.this, MainActivity.class));
                break;

            case R.id.formules:
                startActivity(new Intent(MainActivity.this, Formules.class));
                break;

            case R.id.vins:
                startActivity(new Intent(MainActivity.this, Vins.class));
                break;

            case R.id.addition:
                startActivity(new Intent(MainActivity.this, Resume.class));
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