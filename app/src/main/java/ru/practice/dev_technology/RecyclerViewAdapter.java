package ru.practice.dev_technology;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/** Represents Adapter for recyclerView.
 * @author SmokedKoala
 * @version 0.4.0
 * @since 0.4.0
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<CardFiller> mData;
    private LayoutInflater mInflater;

    /**
     * Constructor for RecyclerViewAdapter
     * @param context Context of activity for getting inflater
     * @param data Data for creating cards
     */
    RecyclerViewAdapter(Context context, List<CardFiller> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;

    }


    /**
     * Method for making link between xml and ViewHolder
     * @param parent Special view that can contain other views
     * @param viewType Type of showing view
     * @return new ViewHolder for RecyclerViewAdapter
     */
    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.card, parent, false);
        return new ViewHolder(view);

    }



    /**
     *linking a card to a position
     * @param holder ViewHolder for RecyclerViewAdapter
     * @param position Position of card
     */
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.result.setText(mData.get(position).getResult());
    }


    /**
     *Returns number of cards
     * @return size of List of cards
     */
    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder  {
        TextView result;

        ViewHolder(final View itemView) {
            super(itemView);
            result = itemView.findViewById(R.id.equally);
        }
    }

    public void clearRecyclerView(){
        mData.clear();
        notifyDataSetChanged();
    }

}
