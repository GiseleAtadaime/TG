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
        void onClick(View view, int adapterPosition, int tipo);
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

        public LoteViewHolder(@NonNull View itemView, final AdapterListener listener) {
            super(itemView);

            loteImagem = itemView.findViewById(R.id.imgAreaAdapter);
            nomeText = itemView.findViewById(R.id.txtNome_AreaAdapter);
            delImagem = itemView.findViewById(R.id.imgbtnDelete_AreaAdapter);
            addImagem = itemView.findViewById(R.id.imgbtnNovo_AreaAdapter);
            editImagem = itemView.findViewById(R.id.imgbtnAlterar_AreaAdapter);

            this.listener = listener;

            loteImagem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(v,getAdapterPosition(),0);
                }
            });
            editImagem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(v,getAdapterPosition(),1);
                }
            });
            delImagem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(v,getAdapterPosition(),2);
                }
            });
            addImagem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(v,getAdapterPosition(),3);
                }
            });
        }

        @Override
        public void onClick(View view){
            listener.onClick(view, getAdapterPosition(), 0);
        }

    }
}
