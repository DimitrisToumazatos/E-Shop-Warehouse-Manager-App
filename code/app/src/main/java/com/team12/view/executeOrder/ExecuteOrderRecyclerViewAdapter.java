package com.team12.view.executeOrder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.team12.warehousemanager.OrderLine;
import com.team12.warehousemanager.R;

import java.util.List;
import java.util.Locale;

/**
 * This class is the Recycler View Adapter when the Manager has selected
 * an Order, is going through its ordered products and wants to decide
 * on whether they want to execute or cancel the Order.
 */
public class ExecuteOrderRecyclerViewAdapter extends RecyclerView.Adapter<ExecuteOrderRecyclerViewAdapter.ViewHolder> {
    private final List<OrderLine> mValues;

    /**
     * Constructor.
     * Sets up the list of items and the listener.
     * @param items The list of Orders
     */
    public ExecuteOrderRecyclerViewAdapter(List<OrderLine> items) {
        this.mValues = items;
    }

    /**
     * This method creates the View Holder Objects.
     * @param parent   The ViewGroup into which the new View will be added after it is bound to
     *                 an adapter position.
     * @param viewType The view type of the new View.
     * @return The ViewHolder of an ordered item
     */
    @NonNull
    @Override
    public ExecuteOrderRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ExecuteOrderRecyclerViewAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_product_overview, parent, false));
    }

    /**
     * This method displays the item from the list.
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull ExecuteOrderRecyclerViewAdapter.ViewHolder holder, int position) {
        final OrderLine currentOrderLine = mValues.get(position);

        String PRODUCT_NAME = currentOrderLine.getName();
        String ORDERED_QUANTITY = String.format(Locale.ENGLISH, "Quantity: %d", currentOrderLine.getOrderedQuantity());
        String PRODUCT_COST = String.format("%s (%s)", currentOrderLine.getSubtotal(), currentOrderLine.getProduct().getPrice());

        holder.productName.setText(PRODUCT_NAME);
        holder.orderedQuantity.setText(ORDERED_QUANTITY);
        holder.productCost.setText(PRODUCT_COST);
    }

    /**
     * Returns the count of the items in the list.
     * @return The count of the items in the list.
     */
    @Override
    public int getItemCount() {
        return mValues.size();
    }

    /**
     * This class is a holder (container) for the order items.
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView productName, orderedQuantity, productCost;

        /**
         * Constructor.
         * @param view The Instance of the current View
         */
        public ViewHolder(@NonNull View view) {
            super(view);
            productName = view.findViewById(R.id.list_item_product_overview_name);
            orderedQuantity = view.findViewById(R.id.list_item_product_overview_quantity);
            productCost = view.findViewById(R.id.list_item_product_overview_price);
        }
    }
}
