package com.oneroofit.taskapplication;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.oneroofit.taskapplication.city.CityAdapter;
import com.oneroofit.taskapplication.city.CityModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ALL extends Fragment implements SearchView.OnQueryTextListener {

        private static final String API_main = "https://api.npoint.io/c4683decc7df639018f6";
    private static final String TAG = MainActivity.class.getSimpleName();

    private SearchView searchView;
    RecyclerView recyclerView;
    private CityAdapter mAdapter;
    List<CityModel> cityList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_a_l_l, container, false);

        //whiteNotificationBar(recyclerView);
        cityList = new ArrayList<>();
        recyclerView = (RecyclerView) rootview.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //initializing the productlist
        loadCity();
        CityAdapter adapter = new CityAdapter(getContext(), cityList, getContext());
        //setting adapter to recyclerview
        recyclerView.setAdapter(adapter);

        return rootview;
    }




    public void loadCity() {
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Please Wait..");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, API_main, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {

                    JSONArray array = new JSONArray(response);


                    for (int i = 0; i < array.length(); i++) {
                        JSONObject object =array.getJSONObject(i);
                        String main =object.getString("city");


                        JSONObject city = array.getJSONObject(i);


                        cityList.add(new CityModel(
                                city.getString("id"),
                                city.getString("city"),
                                city.getString("last_name"),
                                city.getString("first_name")
                        ));
                    }
                    //creating adapter object and setting it to recyclerview
                    mAdapter = new CityAdapter(getContext(), cityList, getContext());
                    recyclerView.setAdapter(mAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {


                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), error.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }
                }) {
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);

        final MenuItem item = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(this);


        item.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {

                return true;
            }

            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {

                return true;
            }
        });

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        mAdapter.getFilter().filter(query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        mAdapter.getFilter().filter(newText);
        return false;

    }



}





