package edu.lasalle.pprog2.ac2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import edu.lasalle.pprog2.ac2.adapter.TascaListViewAdapter;
import edu.lasalle.pprog2.ac2.model.Tasca;

public class MainActivity extends AppCompatActivity {



    private static final String TAG = "MainActivity";

    private EditText nameTask;
    private Spinner priority;
    private ListView listView;
    private boolean ordre;
    private boolean ordre2;

    private ArrayList<Tasca> tasquesArrayList;
    private TascaListViewAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tasquesArrayList = new ArrayList<>();

        listView = (ListView) findViewById(R.id.listview);


        ordre = true;
        ordre2 = true;

        nameTask = (EditText) findViewById(R.id.idtasca);
        priority = (Spinner) findViewById(R.id.spinner);

        adapter = new TascaListViewAdapter(this,tasquesArrayList);

        actualitzaTitol();

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(adapter);
        adapter.notifyDataSetChanged();

    }

    //metode per a netejar el text quan es clica
    public void neteja(View view) {
        nameTask.setText("");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar_menu, menu);
        return true;
    }

    //metode per afegir la tasca un cop es premi el botó ADD
    public void addTask(View view) {

        Tasca tasca = new Tasca();

        tasca.setNomTasca(nameTask.getText().toString());
        tasca.setPrioritat(priority.getSelectedItem().toString());
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");


        Date date = new Date();
        tasca.setData(dateFormat.format(date));


        tasquesArrayList.add(tasca);
        actualitzaTitol();
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sort_date:
                if(ordre) {
                    Collections.sort(tasquesArrayList,Tasca.compareByData1);
                } else {
                    Collections.sort(tasquesArrayList,Tasca.compareByData2);
                }
                ordre = !ordre;
                adapter.notifyDataSetChanged();
                break;
            case R.id.priority:
                if(ordre2) {
                    Collections.sort(tasquesArrayList,Tasca.compareByPriority1);
                } else {
                    Collections.sort(tasquesArrayList,Tasca.compareByPriority2);

                }
                ordre2 = !ordre2;
                adapter.notifyDataSetChanged();
            default:
                break;
        }
        return true;
    }

    //metode per actualitzar el num. de tasques que hi ha pendents
    public void actualitzaTitol() {
        setTitle(getText(R.string.pending_tasks) + " (" + tasquesArrayList.size() + ")");

    }




}
