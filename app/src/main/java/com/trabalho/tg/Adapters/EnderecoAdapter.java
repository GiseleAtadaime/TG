package com.trabalho.tg.Adapters;

import android.content.Context;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.trabalho.tg.Model.Area;
import com.trabalho.tg.Model.Endereco;
import com.trabalho.tg.R;

import java.util.List;

public class EnderecoAdapter extends RecyclerView.Adapter{

    private Context c;
    List<Endereco> endereco;
    AdapterListener listener;
    Boolean tipo;
    public int mSelectedItem = 0;

    public interface AdapterListener {
        void onClick(View view, int adapterPosition, Integer tipo);
    }


    public EnderecoAdapter(List<Endereco> endereco, Context c, AdapterListener listener, Boolean tipo) {
        this.endereco = endereco;
        this.c = c;
        this.listener = listener;
        this.tipo = tipo;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.endereco_view_adapter, viewGroup,false);

        return new EnderecoViewHolder(itemView, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, int i ) {
        final EnderecoViewHolder enderecoViewHolder = (EnderecoViewHolder) viewHolder;
        int pos = enderecoViewHolder.getAdapterPosition();


        enderecoViewHolder.nomeText.setText( "Endereço " + (pos + 1));

        enderecoViewHolder.logradText.setText("Logradouro: " + endereco.get(pos).getEnd_logradouro());
        enderecoViewHolder.bairroText.setText("Bairro: " +endereco.get(pos).getEnd_bairro());
        enderecoViewHolder.cidadeText.setText("Cidade: " +endereco.get(pos).getEnd_Cidade());
        enderecoViewHolder.ufText.setText("UF: " +endereco.get(pos).getEnd_uf());
        enderecoViewHolder.cepText.setText("CEP: " +endereco.get(pos).getEnd_cep().toString());
        enderecoViewHolder.latText.setText("Latitude: " +endereco.get(pos).getEnd_catx().toString());
        enderecoViewHolder.longText.setText("Longitude: " +endereco.get(pos).getEnd_carty().toString());

        enderecoViewHolder.radioButtonEnd.setChecked(pos == mSelectedItem);
    }

    @Override
    public int getItemCount() {
        return endereco.size();
    }

    public class EnderecoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private AdapterListener listener;

        protected TextView nomeText;
        protected TextView logradText;
        protected TextView bairroText;
        protected TextView cidadeText;
        protected TextView ufText;
        protected TextView cepText;
        protected TextView latText;
        protected TextView longText;
        protected ImageButton delImagem;
        protected ImageButton editImagem;
        protected RadioButton radioButtonEnd;

        public EnderecoViewHolder(@NonNull View itemView, final AdapterListener listener) {
            super(itemView);

            nomeText = itemView.findViewById(R.id.txtEnderecoNome_EnderecoViewAdapter);
            logradText = itemView.findViewById(R.id.txtLog_EnderecoViewAdapter);
            bairroText = itemView.findViewById(R.id.txtBairro_EnderecoViewAdapter);
            cidadeText = itemView.findViewById(R.id.txtCidade_EnderecoViewAdapter);
            ufText = itemView.findViewById(R.id.txtUF_EnderecoViewAdapter);
            cepText = itemView.findViewById(R.id.txtCep_EnderecoViewAdapter);
            latText = itemView.findViewById(R.id.txtLat_EnderecoViewAdapter);
            longText = itemView.findViewById(R.id.txtLong_EnderecoViewAdapter);
            delImagem = itemView.findViewById(R.id.imgbtnDelete_EnderecoViewAdapter);
            editImagem = itemView.findViewById(R.id.imgbtnAlterar_EnderecoViewAdapter);
            radioButtonEnd = itemView.findViewById(R.id.rdbtnEnd_EnderecoViewAdapter);

            this.listener = listener;

            if(tipo){


                delImagem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.onClick(v, getAdapterPosition(), 1);
                    }
                });

                editImagem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.onClick(v, getAdapterPosition(), 0);
                    }
                });
            }
            else{
                delImagem.setVisibility(View.GONE);
                editImagem.setVisibility(View.GONE);
                radioButtonEnd.setVisibility(View.VISIBLE);


                radioButtonEnd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        notifyItemChanged(mSelectedItem); // to update last selected item.
                        mSelectedItem = getAdapterPosition();
                        notifyItemChanged(mSelectedItem); // to update last selected item.
                        listener.onClick(v, getAdapterPosition(), 2);
                    }
                });
            }


        }

        @Override
        public void onClick(View view){
            listener.onClick(view,getAdapterPosition(), 0);
        }

    }
}
