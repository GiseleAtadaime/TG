package com.trabalho.tg.Adapters;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.trabalho.tg.Model.Lote_Fechado;
import com.trabalho.tg.R;

import java.io.File;
import java.util.List;

public class LoteFechadoAdapter extends RecyclerView.Adapter {

    private Context c;
    List<Lote_Fechado> lote;
    LoteFechadoAdapter.AdapterListener listener;

    public interface AdapterListener {
        void onClick(View view, int adapterPosition, int tipo);
    }

    public LoteFechadoAdapter(List<Lote_Fechado> lote, Context c, LoteFechadoAdapter.AdapterListener listener) {
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

        return new LoteFechadoAdapter.LoteViewHolder(itemView, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, int i ) {
        final LoteFechadoAdapter.LoteViewHolder loteViewHolder = (LoteFechadoAdapter.LoteViewHolder) viewHolder;
        int pos = loteViewHolder.getAdapterPosition();
        loteViewHolder.nomeText.setText(lote.get(pos).getLot_nome());

        if(lote.get(pos).getLot_imagem() != null){
            ContextWrapper cw = new ContextWrapper(this.c);
            File file = new File(lote.get(pos).getLot_imagem());
            if (file.exists()) {
                loteViewHolder.loteImagem.setImageBitmap(BitmapFactory.decodeFile(file.toString()));
            }
        }
    }



    @Override
    public int getItemCount() {
        return lote.size();
    }

    public class LoteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private LoteFechadoAdapter.AdapterListener listener;
        protected ImageView loteImagem;
        protected TextView nomeText;
        protected ImageButton delImagem;
        protected ImageButton addImagem;
        protected ImageButton editImagem;

        public LoteViewHolder(@NonNull View itemView, final LoteFechadoAdapter.AdapterListener listener) {
            super(itemView);

            loteImagem = itemView.findViewById(R.id.imgAreaAdapter);
            nomeText = itemView.findViewById(R.id.txtNome_AreaAdapter);
            delImagem = itemView.findViewById(R.id.imgbtnDelete_AreaAdapter);
            addImagem = itemView.findViewById(R.id.imgbtnNovo_AreaAdapter);
            editImagem = itemView.findViewById(R.id.imgbtnAlterar_AreaAdapter);

            this.listener = listener;


            editImagem.setVisibility(View.GONE);
            delImagem.setVisibility(View.GONE);
            addImagem.setVisibility(View.GONE);

            loteImagem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(v,getAdapterPosition(),0);
                }
            });
        }

        @Override
        public void onClick(View view){
            listener.onClick(view, getAdapterPosition(), 0);
        }

    }
    
}
