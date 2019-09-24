package com.trabalho.tg.Adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.trabalho.tg.Helper.Contrato;
import com.trabalho.tg.Model.Area;
import com.trabalho.tg.R;

import java.util.List;

public class AreaAdapter extends RecyclerView.Adapter{

    private Context c;
    List<Area> area;
    AdapterListener listener;

    public interface AdapterListener {
        void onClick(View view, int adapterPosition, Integer tipo);
    }


    public AreaAdapter(List<Area> area, Context c, AdapterListener listener) {
        this.area = area;
        this.c = c;
        this.listener = listener;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.area_view_adapter, viewGroup,false);

        return new AreaViewHolder(itemView, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, int i ) {
        final AreaViewHolder areaViewHolder = (AreaViewHolder) viewHolder;
        int pos = areaViewHolder.getAdapterPosition();
        areaViewHolder.nomeText.setText(area.get(pos).getAr_nome());
    }



    @Override
    public int getItemCount() {
        return area.size();
    }

    public class AreaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private AdapterListener listener;

        protected ImageView areaImagem;
        protected TextView nomeText;
        protected ImageButton delImagem;
        protected ImageButton addImagem;
        protected ImageButton editImagem;

        public AreaViewHolder(@NonNull View itemView, final AdapterListener listener) {
            super(itemView);

            areaImagem = itemView.findViewById(R.id.imgAreaAdapter);
            nomeText = itemView.findViewById(R.id.txtNome_AreaAdapter);
            delImagem = itemView.findViewById(R.id.imgbtnDelete_AreaAdapter);
            addImagem = itemView.findViewById(R.id.imgbtnNovo_AreaAdapter);
            editImagem = itemView.findViewById(R.id.imgbtnAlterar_AreaAdapter);

            this.listener = listener;

            areaImagem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(v, area.get(getAdapterPosition()).getAr_id(), 0);
                }
            });

            editImagem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(v, area.get(getAdapterPosition()).getAr_id(), 1);
                }
            });

            delImagem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(v, area.get(getAdapterPosition()).getAr_id(), 2);
                }
            });

            addImagem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(v, area.get(getAdapterPosition()).getAr_id(), 3);
                }
            });

        }

        @Override
        public void onClick(View view){
            listener.onClick(view, area.get(getAdapterPosition()).getAr_id(), 0);
        }



    }
}
