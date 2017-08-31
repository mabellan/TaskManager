package edu.lasalle.pprog2.ac2.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

import edu.lasalle.pprog2.ac2.MainActivity;
import edu.lasalle.pprog2.ac2.R;
import edu.lasalle.pprog2.ac2.model.Tasca;

/**
 * Clase TascaListViewAdapter s'encarrega de carregar la informació de cada listView a la
 * part "grafica". Com el seu propi nom indica, la seva funció es d'adaptar la List a la View
 *
 * @Author Miguel Abellán Donoso
 *
 *
 * Versió 2.0
 */

public class TascaListViewAdapter extends BaseAdapter implements AdapterView.OnItemClickListener {

    private Context context;
    private List<Tasca> tasques;
    private TascaListViewAdapter adatper;


    public TascaListViewAdapter(Context contex, List<Tasca> tasques) {
        this.context = contex;
        this.tasques = tasques;

    }

    @Override
    public int getCount() {
        return tasques.size();
    }

    @Override
    public Object getItem(int position) {
        return tasques.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.task_layout,parent, false);

        Tasca tasca = tasques.get(position);
        ImageView imatge = (ImageView) itemView.findViewById(R.id.listview_cell_image);

        if(tasca.getPrioritat().equals(context.getString(R.string.asap2))) {
            imatge.setImageResource(R.drawable.ic_asap);
        } else if(tasca.getPrioritat().equals(context.getString(R.string.low2))) {
            imatge.setImageResource(R.drawable.ic_action_name);
        } else if(tasca.getPrioritat().equals(context.getString(R.string.important2))) {
            imatge.setImageResource(R.drawable.ic_action_importante);
        } else if(tasca.getPrioritat().equals(context.getString(R.string.normal2))) {
            imatge.setImageResource(R.drawable.ic_action_norma);
        }


        TextView text = (TextView) itemView.findViewById(R.id.description);
        text.setText(tasca.getNomTasca());

        TextView priority = (TextView) itemView.findViewById(R.id.priority);
        priority.setText(tasca.getPrioritat());

        TextView data = (TextView) itemView.findViewById(R.id.date);
        data.setText(tasca.getData());




        return itemView;

    }

    //métode que s'encarrega d'eleminar o no una tasca que s'hagi premut
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        final MainActivity mainActivity = (MainActivity)context;
        final int  pos = position;
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle(R.string.sure);
        alertDialogBuilder.setMessage(tasques.get(pos).getNomTasca() + System.lineSeparator()
                                        + tasques.get(pos).getPrioritat());
        alertDialogBuilder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                tasques.remove(pos);
                mainActivity.actualitzaTitol();
                notifyDataSetChanged();
            }
        });
        alertDialogBuilder.setNegativeButton(R.string.no, null);


        alertDialogBuilder.create();
        alertDialogBuilder.show();



    }


}
