package com.team12.view.draftOrders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.team12.warehousemanager.AbstractOrder;
import com.team12.warehousemanager.R;

import java.util.List;

public class DraftOrdersRecyclerViewAdapter extends RecyclerView.Adapter<DraftOrdersRecyclerViewAdapter.ViewHolder> {
    private final List<AbstractOrder> mValues;
    private final ItemSelectionListener listener;

    public DraftOrdersRecyclerViewAdapter(List<AbstractOrder> items, DraftOrdersRecyclerViewAdapter.ItemSelectionListener listener) {
        this.mValues = items;
        this.listener = listener;
    }

    @NonNull
    @Override
    public DraftOrdersRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DraftOrdersRecyclerViewAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_order_inbox, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DraftOrdersRecyclerViewAdapter.ViewHolder holder, int position) {
        final AbstractOrder currentOrder = mValues.get(position);
        holder.orderDate.setText(currentOrder.getDateOfSubmission().toString());
        holder.orderId.setText(String.format("Order ID: %s", currentOrder.getOrderID()));
        holder.orderCost.setText(currentOrder.getTotalCost().toString());

        holder.viewOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.viewOrder(currentOrder.getOrderID());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public interface ItemSelectionListener {
        /**
         * User clicked the "View Order"
         * button for an order
         */
        void viewOrder(String orderId);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView orderDate, orderId, orderCost;
        public final Button viewOrderButton;

        public ViewHolder(@NonNull View view) {
            super(view);
            orderDate = view.findViewById(R.id.order_inbox_customer_name);
            orderId = view.findViewById(R.id.order_inbox_order_id);
            orderCost = view.findViewById(R.id.order_inbox_total_cost);
            viewOrderButton = view.findViewById(R.id.order_inbox_view_order_button);
        }
    }
}
