package ca.ualberta_ecocar.ecocar;

import android.app.Activity;
import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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
    String[] primaryVals = new String[]{"0","0","1","0","0","0","0","62","0","0","0","0","0","0","0","0","0","0","0","0","0"};

    public class FuelCellData {
        String label;
        String value;

        public FuelCellData(String l, String v){
            label = l;
            value = v;
        }


    }

    FuelCellData[] ourData = new FuelCellData[primaryVals.length];
    WeatherAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.list_fragment, container, false);
        fillDataIntoClass(valString, primaryVals);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getActivity(),
                R.layout.stat_layout, R.id.fc_label, valString);
        adapter = new WeatherAdapter(getActivity(), R.layout.stat_layout, ourData);

        setListAdapter(adapter);
        adapter.notifyDataSetChanged();

        return view;

    }
//    @Override
//    public void onListItemClick(ListView l, View v, int position, long id) {
//        TextFragment txt = (TextFragment)getFragmentManager().findFragmentById(R.id.fragment2);
//        txt.change(AndroidOS[position],"Version : "+Version[position]);
//        getListView().setSelector(android.R.color.holo_blue_dark);
//    }

    public void fillDataIntoClass(String[] valString, String[] primaryVals){
        for( int i=0; i<primaryVals.length;i++){
            ourData[i]=new FuelCellData(valString[i],primaryVals[i]);
        }
    }
    public void change(String[] data)
    {
        primaryVals=data;
        fillDataIntoClass(valString,primaryVals);
        adapter.notifyDataSetChanged();

    }

    public class WeatherAdapter extends ArrayAdapter<FuelCellData>{

        Context context;
        int layoutResourceId;
        FuelCellData data[] = null;

        public WeatherAdapter(Context context, int layoutResourceId, FuelCellData[] data) {
            super(context, layoutResourceId, data);
            this.layoutResourceId = layoutResourceId;
            this.context = context;
            this.data = data;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View row = convertView;
            WeatherHolder holder = null;

            if(row == null)
            {
                LayoutInflater inflater = ((Activity)context).getLayoutInflater();
                row = inflater.inflate(layoutResourceId, parent, false);

                holder = new WeatherHolder();
                holder.txtLabel = (TextView) row.findViewById(R.id.fc_label);
                holder.txtValue = (TextView)row.findViewById(R.id.fc_value);

                row.setTag(holder);
            }
            else
            {
                holder = (WeatherHolder)row.getTag();
            }

            FuelCellData weather = data[position];
            holder.txtValue.setText(weather.value);
            holder.txtLabel.setText(weather.label);

            return row;
        }

        class WeatherHolder
        {
            TextView txtLabel;
            TextView txtValue;
        }
    }
}
