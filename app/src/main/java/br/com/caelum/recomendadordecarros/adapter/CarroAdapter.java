package br.com.caelum.recomendadordecarros.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import br.com.caelum.recomendadordecarros.R;
import br.com.caelum.recomendadordecarros.event.CarroSelecionadoEvent;
import br.com.caelum.recomendadordecarros.event.CarroSelecionadoLongEvent;
import br.com.caelum.recomendadordecarros.model.Carro;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;

/**
 * Created by matheusbrandino on 2/8/17.
 */
public class CarroAdapter extends RecyclerView.Adapter {

    private List<Carro> carros;

    public CarroAdapter(List<Carro> carros) {
        this.carros = carros;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_carro, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        ViewHolder holder = (ViewHolder) viewHolder;
        Carro carro = carros.get(position);

        holder.ano.setText(carro.getAno().toString());
        holder.cor.setText(carro.getCor());
        holder.marca.setText(carro.getMarca());
        holder.modelo.setText(carro.getModelo());

    }

    @Override
    public int getItemCount() {
        return carros.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_ano)
        TextView ano;

        @BindView(R.id.item_cor)
        TextView cor;

        @BindView(R.id.item_marca)
        TextView marca;

        @BindView(R.id.item_modelo)
        TextView modelo;


        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        @OnClick(R.id.item)
        public void clickNoCard() {
            EventBus.getDefault().post(new CarroSelecionadoEvent(carros.get(getAdapterPosition())));
        }

        @OnLongClick(R.id.item)
        public boolean clickLongoNoCard(){

            EventBus.getDefault().post(new CarroSelecionadoLongEvent(carros.get(getAdapterPosition())));

            return true;
        }
    }
}
