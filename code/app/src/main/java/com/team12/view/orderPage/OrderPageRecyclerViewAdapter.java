package com.team12.view.orderPage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.team12.warehousemanager.OrderLine;
import com.team12.warehousemanager.R;

import java.util.List;
import java.util.Locale;

/**
 * This class is the Recycler View Adapter for the Customer's Order Page.
 * There they are able to go Create/Save Orders.
 */
public class OrderPageRecyclerViewAdapter extends RecyclerView.Adapter<OrderPageRecyclerViewAdapter.ViewHolder> {
    private final List<OrderLine> mValues;
    private final ItemSelectionListener listener;

    /**
     * Constructor.
     * Sets up the list of items and the listener.
     * @param items The list of Orders
     * @param listener The ItemSelectionListener
     */
    public OrderPageRecyclerViewAdapter(List<OrderLine> items, ItemSelectionListener listener) {
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
    public OrderPageRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_order_product, parent, false));
    }

    /**
     * This method displays the item from the list.
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull OrderPageRecyclerViewAdapter.ViewHolder holder, int position) {
        final OrderLine currentOrderLine = mValues.get(position);
        holder.productName.setText(currentOrderLine.getName());
        holder.productEOF.setText(currentOrderLine.getEOF());

        String quantity = String.format(Locale.ENGLISH, "Quantity %d", currentOrderLine.getOrderedQuantity());
        holder.orderedQuantity.setText(quantity);

        String price = String.format(Locale.ENGLISH, "%s (%.2f)", currentOrderLine.getSubtotal().toString(), currentOrderLine.getProduct().getPrice());
        holder.price.setText(price);

        holder.removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.removeOrderLine(currentOrderLine);
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
         * User clicked the "Remove"
         * on an list item
         */
        void removeOrderLine(OrderLine orderLine);
    }

    /**
     * This class is a holder (container) for the order items.
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView productName, productEOF, orderedQuantity, price;
        public final Button removeButton;

        /**
         * Constructor.
         * @param view The Instance of the current View
         */
        public ViewHolder(@NonNull View view) {
            super(view);
            productName = view.findViewById(R.id.list_item_order_product_name);
            productEOF = view.findViewById(R.id.list_item_order_product_eof);
            orderedQuantity = view.findViewById(R.id.list_item_order_product_quantity);
            price = view.findViewById(R.id.list_item_order_product_price);
            removeButton = view.findViewById(R.id.list_item_order_product_edit_button);
        }
    }
}
