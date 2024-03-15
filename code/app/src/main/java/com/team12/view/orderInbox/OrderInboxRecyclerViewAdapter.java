package com.team12.view.orderInbox;

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

/**
 * This class is the Recycler View Adapter for the Manager's Order Inbox.
 * There they are able to go through their pending orders and decide
 * which order they wish to execute or cancel.
 */
public class OrderInboxRecyclerViewAdapter extends RecyclerView.Adapter<OrderInboxRecyclerViewAdapter.ViewHolder> {
    private final List<AbstractOrder> mValues;
    private final ItemSelectionListener listener;

    /**
     * Constructor.
     * Sets up the list of items and the listener.
     * @param items The list of Orders
     * @param listener The ItemSelectionListener
     */
    public OrderInboxRecyclerViewAdapter(List<AbstractOrder> items, ItemSelectionListener listener) {
        this.mValues = items;
        this.listener = listener;
    }

    /**
     * This method creates the View Holder Objects.
     * @param parent   The ViewGroup into which the new View will be added after it is bound to
     *                 an adapter position.
     * @param viewType The view type of the new View.
     * @return The ViewHolder of an Order
     */
    @NonNull
    @Override
    public OrderInboxRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_order_inbox, parent, false));
    }

    /**
     * This method displays the item from the list.
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull OrderInboxRecyclerViewAdapter.ViewHolder holder, int position) {
        final AbstractOrder currentOrder = mValues.get(position);
        holder.customerName.setText(currentOrder.getUserAccount().getName());
        holder.orderId.setText(String.format("Order ID: %s", currentOrder.getOrderID()));
        holder.orderCost.setText(currentOrder.getTotalCost().toString());

        holder.viewOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.viewOrder(currentOrder.getOrderID());
            }
        });
    }

    /**
     * Returns the count of the items in the list.
     * @return The count of the items in the list.
     */
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

    /**
     * This class is a holder (container) for the order items.
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView customerName, orderId, orderCost;
        public final Button viewOrderButton;

        /**
         * Constructor.
         * @param view The Instance of the current View
         */
        public ViewHolder(@NonNull View view) {
            super(view);
            customerName = view.findViewById(R.id.order_inbox_customer_name);
            orderId = view.findViewById(R.id.order_inbox_order_id);
            orderCost = view.findViewById(R.id.order_inbox_total_cost);
            viewOrderButton = view.findViewById(R.id.order_inbox_view_order_button);
        }
    }
}
