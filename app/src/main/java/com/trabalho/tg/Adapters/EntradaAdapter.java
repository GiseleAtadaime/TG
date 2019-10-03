package com.trabalho.tg.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.trabalho.tg.Helper.Utils_TG;
import com.trabalho.tg.Model.Entrada;
import com.trabalho.tg.R;

import java.util.List;

public class EntradaAdapter extends RecyclerView.Adapter{
    private Context c;
    List<Entrada> entrada;
    AdapterListener listener;

    public interface AdapterListener {
        void onClick(View view, int adapterPosition);
    }

    public EntradaAdapter(List<Entrada> entrada, Context c, AdapterListener listener) {
        this.entrada = entrada;
        this.c = c;
        this.listener = listener;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.entrada_view_adapter, viewGroup,false);

        return new EntradaViewHolder(itemView, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, int i ) {
        final EntradaViewHolder entradaViewHolder = (EntradaViewHolder) viewHolder;
        int pos = entradaViewHolder.getAdapterPosition();

        entradaViewHolder.nomeText.setText(entrada.get(pos).getEnt_desc());
        entradaViewHolder.dataText.setText(new Utils_TG().formatDate(entrada.get(pos).getEnt_data(),true));
    }


    @Override
    public int getItemCount() {
        return entrada.size();
    }

    public class EntradaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private AdapterListener listener;
        protected ImageView imgEntrada;
        protected TextView nomeText;
        protected TextView dataText;


        public EntradaViewHolder(@NonNull View itemView, AdapterListener listener) {
            super(itemView);

            imgEntrada = itemView.findViewById(R.id.imgEntrada_EntViewAdapter);
            nomeText = itemView.findViewById(R.id.txtTitulo_EntViewAdapter);
            dataText = itemView.findViewById(R.id.txtData_EntViewAdapter);

            this.listener = listener;
            itemView.setOnClickListener(this);



        }

        @Override
        public void onClick(View view){
            listener.onClick(view, getAdapterPosition());
        }

    }
}
