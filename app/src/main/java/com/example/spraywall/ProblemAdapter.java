package com.example.spraywall;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;


public class ProblemAdapter extends ArrayAdapter<Problem> implements Filterable {

    public ArrayList<Problem> fullList;

    public ProblemAdapter(Context context, ArrayList<Problem> list) {
        super(context, 0 , list);
        this.fullList = new ArrayList<>(list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Problem new_problem = getItem(position);
        if(convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.table_item,parent,false);

        TextView name = (TextView) convertView.findViewById(R.id.problem_name);
        name.setText(new_problem.getId());

        return convertView;
    }

    // Override getFilter in order to implement searching by problem name
    @NonNull
    @Override
    public Filter getFilter() {
        return problemFilter;
    }

    private Filter problemFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            FilterResults results = new FilterResults(); // initialize results variable
            ArrayList<Problem> filteredList = new ArrayList<>(); // create empty ArrayList for the filtered problems
            System.out.println("Length: " + fullList.size());
            // check if search term is empty. Therefore show all results
            if (constraint == null || constraint.length() == 0){
                filteredList.addAll(fullList);
            }

            // if the search term is not empty, filter the results and update the ArrayList
            else{
                String filterString = constraint.toString().toLowerCase().trim();
                for (Problem problem : fullList){
                    if(problem.getName().toLowerCase().contains(filterString)){
                        filteredList.add(problem);
                    }
                }
            }

            results.values = filteredList;
            results.count = filteredList.size();

            return results;
        }

        // function that updates the adapter values
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            clear(); // clear the current values of the adapter
            addAll((ArrayList<Problem>) results.values); // add new values to the adapter
            notifyDataSetChanged(); // tell the adapter to update the values on the screen
        }
    };

}

