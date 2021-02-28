package com.oneroofit.taskapplication.city;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.oneroofit.taskapplication.R;
import java.util.ArrayList;
import java.util.List;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.CityViewHolder> implements Filterable{
    private Context context;
    private List<CityModel> cityList;
    private List<CityModel> cityListFiltered;
    private CityAdapterListener listener;



    public interface CityAdapterListener {
        boolean onCreateOptionsMenu(Menu menu);

        void onCitySelected(CityModel cityModel);
    }

    public class CityViewHolder extends RecyclerView.ViewHolder {
        public TextView id, city,last_name,first_name;

        public CityViewHolder(View view) {
            super(view);
            id = view.findViewById(R.id.id);
            city = view.findViewById(R.id.city);
            last_name = view.findViewById(R.id.last_name);
            first_name=view.findViewById(R.id.first_name);


        }
    }

    public CityAdapter(Context context, List<CityModel> cityList, Context applicationContext) {
        this.context = context;
        this.cityList = cityList;
        this.cityListFiltered = cityList;
        this.listener = listener;
    }


    @Override
    public CityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.city_items, parent, false);


        return new CityViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CityViewHolder holder, int position) {
        final CityModel cityModel = cityListFiltered.get(position);
        holder.id.setText(cityModel.getId());
        holder.city.setText(cityModel.getCity());
        holder.last_name.setText(cityModel.getLast_name());
        holder.first_name.setText(cityModel.getFirst_name());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, cityModel.first_name + " "+ cityModel.getLast_name(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return cityListFiltered.size();
    }
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    cityListFiltered = cityList;
                } else {
                    List<CityModel> filteredList = new ArrayList<>();
                    for (CityModel row : cityList) {


                        if (row.getFirst_name().toLowerCase().trim().contains(charString.toLowerCase().trim()) || row.getCity().toLowerCase().contains(charString.toLowerCase().trim()) || row.getLast_name().toLowerCase().contains(charString.toLowerCase().trim())) {
                            filteredList.add(row);
                        }
                    }

                    cityListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = cityListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                cityListFiltered = (ArrayList<CityModel>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public interface ContactsAdapterListener {
        void onContactSelected(CityModel cityModel);
    }
}


