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
import com.trabalho.tg.Model.Lote;
import com.trabalho.tg.R;

import java.util.List;

public class LoteAdapter extends RecyclerView.Adapter{

    private Context c;
    List<Lote> lote;
    AdapterListener listener;

    public interface AdapterListener {
        void onClick(View view, int adapterPosition);
    }

    public LoteAdapter(List<Lote> lote, Context c, AdapterListener listener) {
        this.lote = lote;
        this.c = c;
        this.listener = listener;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.area_view_adapter, viewGroup,false);

        return new LoteViewHolder(itemView, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, int i ) {
        final LoteViewHolder loteViewHolder = (LoteViewHolder) viewHolder;
        int pos = loteViewHolder.getAdapterPosition();
        loteViewHolder.nomeText.setText(lote.get(pos).getLot_nome());


        loteViewHolder.delImagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        loteViewHolder.addImagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        loteViewHolder.editImagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }



    @Override
    public int getItemCount() {
        return lote.size();
    }

    public class LoteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private AdapterListener listener;
        protected ImageView loteImagem;
        protected TextView nomeText;
        protected ImageButton delImagem;
        protected ImageButton addImagem;
        protected ImageButton editImagem;

        public LoteViewHolder(@NonNull View itemView, AdapterListener listener) {
            super(itemView);

            loteImagem = itemView.findViewById(R.id.imgAreaAdapter);
            nomeText = itemView.findViewById(R.id.txtNome_AreaAdapter);
            delImagem = itemView.findViewById(R.id.imgbtnDelete_AreaAdapter);
            addImagem = itemView.findViewById(R.id.imgbtnNovo_AreaAdapter);
            editImagem = itemView.findViewById(R.id.imgbtnNovo_AreaAdapter);

            this.listener = listener;

            loteImagem.setOnClickListener(this);
        }

        @Override
        public void onClick(View view){
            listener.onClick(view, lote.get(getAdapterPosition()).getLot_id());
        }

    }
}
