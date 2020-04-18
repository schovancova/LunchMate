package com.schovancova.lunchmate.app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.schovancova.lunchmate.R;

import java.util.List;
public class ListingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<ListingItemAdapter> mList;
    private Context mContext;
    public ListingAdapter(List<ListingItemAdapter> list, Context context){
        super();
        mList = list;
        mContext = context;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listing_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        ListingItemAdapter itemAdapter = mList.get(position);
        ((ViewHolder) viewHolder).view_name.setText(itemAdapter.name);
        ((ViewHolder) viewHolder).view_image.setImageBitmap(itemAdapter.image);
        ((ViewHolder) viewHolder).view_openness.setText(itemAdapter.open_now);
        ((ViewHolder) viewHolder).view_rating.setRating(itemAdapter.rating);

    }
    @Override
    public int getItemCount() {
        return mList.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        TextView view_name;
        TextView view_openness;
        ImageView view_image;
        RatingBar view_rating;
        public ViewHolder(View itemView) {
            super(itemView);
            view_name = itemView.findViewById(R.id.tv_name);
            view_openness =  itemView.findViewById(R.id.openness);
            view_image =  itemView.findViewById(R.id.img_item);
            view_rating = itemView.findViewById(R.id.ratingBar);
        }
    }
}