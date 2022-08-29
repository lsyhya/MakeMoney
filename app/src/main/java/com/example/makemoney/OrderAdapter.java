package com.example.makemoney;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderHolder> {

    Context context;
    List<Transactions> transactionsList;


    public OrderAdapter(Context context, List<Transactions> transactionsList){
           this.context=context;
           this.transactionsList=transactionsList;
    }


    @Override
    public OrderHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.oraer_item, parent,false);
        OrderHolder orderHolder = new OrderHolder(view);
        return orderHolder;
    }

    @Override
    public void onBindViewHolder(OrderHolder holder, int position) {
        holder.tvPingzhong.setText(transactionsList.get(position).getVarieties());
        if(transactionsList.get(position).getState()==2){
            holder.tvYingli.setText(transactionsList.get(position).getProfitOrLossM()+"");
        }else if(transactionsList.get(position).getState()==3){
            holder.tvYingli.setText("-"+transactionsList.get(position).getProfitOrLossM());
        }

    }

    @Override
    public int getItemCount() {
        return transactionsList.size();
    }

    class OrderHolder extends RecyclerView.ViewHolder {

        private LinearLayout linSimple;
        private TextView tvPingzhong;
        private TextView tvYingli;
        private Button btnQuxiao;
        private Button btnYingli;
        private Button btnKuisun;
        private LinearLayout linDetails;

        public OrderHolder(View itemView) {
            super(itemView);
            linSimple = (LinearLayout) itemView.findViewById(R.id.lin_simple);
            tvPingzhong = (TextView) itemView.findViewById(R.id.tv_pingzhong);
            tvYingli = (TextView) itemView.findViewById(R.id.tv_yingli);
            btnQuxiao = (Button) itemView.findViewById(R.id.btn_quxiao);
            btnYingli = (Button) itemView.findViewById(R.id.btn_yingli);
            btnKuisun = (Button) itemView.findViewById(R.id.btn_kuisun);
            linDetails = (LinearLayout) itemView.findViewById(R.id.lin_details);

            linSimple.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context,"123",Toast.LENGTH_LONG).show();
                }
            });
        }
    }


}
