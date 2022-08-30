package com.example.makemoney;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderHolder> {

    Context context;
    List<Transactions> transactionsList;



    public OrderAdapter(Context context, List<Transactions> transactionsList) {
        this.context = context;
        this.transactionsList = transactionsList;
    }


    @Override
    public OrderHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.oraer_item, parent, false);
        OrderHolder orderHolder = new OrderHolder(view);
        return orderHolder;
    }

    @Override
    public void onBindViewHolder(OrderHolder holder, int position) {
        holder.tvPingzhong.setText(transactionsList.get(position).getVarieties());
        if (transactionsList.get(position).getState() == 2) {
            holder.btnYingli.setVisibility(View.GONE);
            holder.btnKuisun.setVisibility(View.GONE);
            holder.tvYingli.setText(transactionsList.get(position).getProfitOrLossM() + "");
        } else if (transactionsList.get(position).getState() == 3) {
            holder.btnYingli.setVisibility(View.GONE);
            holder.btnKuisun.setVisibility(View.GONE);
            holder.tvYingli.setText("-" + transactionsList.get(position).getProfitOrLossM());
        } else if (transactionsList.get(position).getState() == 0) {
            holder.btnYingli.setVisibility(View.VISIBLE);
            holder.btnKuisun.setVisibility(View.VISIBLE);
            holder.tvYingli.setText("交易中");
        }
        holder.mDetailsPz.setText("品种:"+transactionsList.get(position).getVarieties());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        holder.mDetailsDate.setText("时间:"+simpleDateFormat.format(transactionsList.get(position).getDate()));
        holder.mDetailsCl.setText("策略:"+transactionsList.get(position).getTradingStrategies());
        holder.mDetailsZq.setText("周期:"+transactionsList.get(position).getCycle());
        holder.mDetailsZy.setText("止盈:"+transactionsList.get(position).getStopProfit());
        holder.mDetailsZs.setText("止损:"+transactionsList.get(position).getStopLoss());
        holder.mDetailsJylx.setText("交易类型:"+transactionsList.get(position).getSellOrBuy());
        holder.mDetailsDv.setText("盈亏值:"+transactionsList.get(position).getdVaule());


        holder.btnKuisun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                transactionsList.get(position).setState(3);
                notifyDataSetChanged();
            }
        });

        holder.btnYingli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                transactionsList.get(position).setState(2);
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return transactionsList.size();
    }

    private void initView() {

    }

    class OrderHolder extends RecyclerView.ViewHolder {

        private LinearLayout linSimple;
        private TextView tvPingzhong;
        private TextView tvYingli;
        private Button btnQuxiao;
        private Button btnYingli;
        private Button btnKuisun;
        private ConstraintLayout linDetails;

        private TextView mDetailsPz;
        private TextView mDetailsDate;
        private TextView mDetailsCl;
        private TextView mDetailsZq;
        private TextView mDetailsZy;
        private TextView mDetailsZs;
        private TextView mDetailsJylx;
        private TextView mDetailsDv;

        public OrderHolder(View itemView) {
            super(itemView);
            linSimple = (LinearLayout) itemView.findViewById(R.id.lin_simple);
            tvPingzhong = (TextView) itemView.findViewById(R.id.tv_pingzhong);
            tvYingli = (TextView) itemView.findViewById(R.id.tv_yingli);
            btnQuxiao = (Button) itemView.findViewById(R.id.btn_quxiao);
            btnYingli = (Button) itemView.findViewById(R.id.btn_yingli);
            btnKuisun = (Button) itemView.findViewById(R.id.btn_kuisun);
            linDetails = (ConstraintLayout) itemView.findViewById(R.id.lin_details);

            mDetailsPz = (TextView) itemView.findViewById(R.id.details_pz);
            mDetailsDate = (TextView) itemView.findViewById(R.id.details_date);
            mDetailsCl = (TextView) itemView.findViewById(R.id.details_cl);
            mDetailsZq = (TextView) itemView.findViewById(R.id.details_zq);
            mDetailsZy = (TextView) itemView.findViewById(R.id.details_zy);
            mDetailsZs = (TextView) itemView.findViewById(R.id.details_zs);
            mDetailsJylx = (TextView) itemView.findViewById(R.id.details_jylx);
            mDetailsDv = (TextView) itemView.findViewById(R.id.details_dv);
            linSimple.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(linDetails.getVisibility()==View.GONE){
                        linDetails.setVisibility(View.VISIBLE);
                    } else if(linDetails.getVisibility()==View.VISIBLE){
                        linDetails.setVisibility(View.GONE);
                    }
                }
            });
        }


    }

}
