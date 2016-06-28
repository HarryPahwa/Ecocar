package ca.ualberta_ecocar.ecocar;

import android.app.Activity;
import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by Harry on 21-Jun-16.
 */
public class StatsListFragment extends ListFragment {
    String[] valString=new String[]{"fc_error",
            "fc_state",
            "fc_purge_count",
            "fc_time_between_last_purges",
            "fc_energy_since_last_purge",
            "fc_total_energy",
            "fc_charge_since_last_purge",
            "fc_total_charge",
            "fc_volt",
            "fc_curr",
            "fc_capvolt",
            "fc_temp",
            "fc_opttemp",
            "fc_pres",
            "fc_fan_speed",
            "fc_start_relay",
            "fc_res_relay",
            "fc_cap_relay",
            "fc_motor_relay",
            "fc_purge_valve",
            "fc_h2_valve",
    };
    String[] primaryVals = new String[]{"9","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"};

    public class FuelCellData {
        String label;
        String value;

        public FuelCellData(String l, String v){
            label = l;
            value = v;
        }


    }

    FuelCellData[] ourData = new FuelCellData[primaryVals.length];
    DataAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.list_fragment, container, false);
        fillDataIntoClass(valString, primaryVals);
//        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getActivity(),
//                R.layout.stat_layout, R.id.fc_label, valString);
        adapter = new DataAdapter(getActivity(), R.layout.stat_layout, ourData);

        setListAdapter(adapter);
        adapter.notifyDataSetChanged();

        return view;

    }
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
//        StatsListFragment graph = (StatsListFragment) getFragmentManager().findFragmentById(R.id.fragment);
//        graph.change(valString[position],"Value : "+primaryVals[position]);
        getListView().setSelector(android.R.color.holo_blue_dark);
//        getListView().setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,600));
    }

    public void fillDataIntoClass(String[] valString, String[] primaryVals){
        for( int i=0; i<ourData.length;i++){
            if(i>=primaryVals.length){
                break;
            }

            ourData[i]=new FuelCellData(valString[i],primaryVals[i]);

        }
    }
    public void change(String[] data)
    {
        primaryVals=data;
        fillDataIntoClass(valString,primaryVals);
        adapter.notifyDataSetChanged();
    }

    public class DataAdapter extends ArrayAdapter<FuelCellData>{

        Context context;
        int layoutResourceId;
        FuelCellData data[] = null;

        public DataAdapter(Context context, int layoutResourceId, FuelCellData[] data) {
            super(context, layoutResourceId, data);
            this.layoutResourceId = layoutResourceId;
            this.context = context;
            this.data = data;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View row = convertView;
            DataHolder holder = null;

            if(row == null)
            {
                LayoutInflater inflater = ((Activity)context).getLayoutInflater();
                row = inflater.inflate(layoutResourceId, parent, false);

                holder = new DataHolder();
                holder.txtLabel = (TextView) row.findViewById(R.id.fc_label);
                holder.txtValue = (TextView)row.findViewById(R.id.fc_value);

                row.setTag(holder);
            }
            else
            {
                holder = (DataHolder)row.getTag();
            }

            FuelCellData fcData = data[position];
            holder.txtValue.setText(fcData.value);
            holder.txtLabel.setText(fcData.label);

            return row;
        }

        class DataHolder
        {
            TextView txtLabel;
            TextView txtValue;
        }
    }
}
