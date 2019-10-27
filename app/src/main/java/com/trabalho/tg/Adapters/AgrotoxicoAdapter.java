package com.trabalho.tg.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;
import com.trabalho.tg.Model.Reg_Agrotoxico;
import com.trabalho.tg.R;

import java.util.ArrayList;
import java.util.List;

public class AgrotoxicoAdapter  extends ArrayAdapter<Reg_Agrotoxico> {

        Context context;
        int resource, textViewResourceId;

        List<Reg_Agrotoxico> items, tempItems, suggestions;

        public AgrotoxicoAdapter(Context context, int resource, int textViewResourceId, List<Reg_Agrotoxico> items) {
            super(context, resource, textViewResourceId, items);
            this.context = context;
            this.resource = resource;
            this.textViewResourceId = textViewResourceId;
            this.items = items;
            tempItems = new ArrayList<Reg_Agrotoxico>(items); // this makes the difference.
            suggestions = new ArrayList<Reg_Agrotoxico>();
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater)
                        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.row_layout, parent, false);
            }
            Reg_Agrotoxico agro = items.get(position);
            if (agro != null) {
                TextView lblName = (TextView) view.findViewById(R.id.txtAgrNome_RowLayout);
                if (lblName != null)
                    lblName.setText(agro.getReg_nomecom());
            }
            return view;
        }
        @Override
        public Filter getFilter() {
            return nameFilter;
        }
        /**
         * Custom Filter implementation for custom suggestions we provide.
         */
        Filter nameFilter = new Filter() {
            @Override
            public CharSequence convertResultToString(Object resultValue) {
                String str = ((Reg_Agrotoxico) resultValue).getReg_nomecom();
                return str;
            }
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                if (constraint != null) {
                    suggestions.clear();
                    for (Reg_Agrotoxico agro : tempItems) {
                        if (agro.getReg_nomecom().toLowerCase().contains(constraint.toString().toLowerCase())) {
                            suggestions.add(agro);
                        }
                    }
                    FilterResults filterResults = new FilterResults();
                    filterResults.values = suggestions;
                    filterResults.count = suggestions.size();
                    return filterResults;
                } else {
                    return new FilterResults();
                }
            }
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                List<Reg_Agrotoxico> filterList = (ArrayList<Reg_Agrotoxico>) results.values;
                if (results != null && results.count > 0) {
                    clear();
                    for (Reg_Agrotoxico agro : filterList) {
                        add(agro);
                        notifyDataSetChanged();
                    }
                }
            }
        };
}
