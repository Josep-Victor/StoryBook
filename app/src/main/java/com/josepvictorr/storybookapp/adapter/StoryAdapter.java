package com.josepvictorr.storybookapp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.josepvictorr.storybookapp.R;
import com.josepvictorr.storybookapp.item.SemuaStoryItem;

import java.util.List;
import java.util.Random;

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.StoryHolder> {
    List<SemuaStoryItem> semuaStoryItemList;
    Context mContext;

    public String[] mColors = {
        "#39add1", // light blue
        "#3079ab", // dark blue
        "#c25975", // mauve
        "#e15258", // red
        "#f9845b", // orange
        "#838cc7", // lavender
        "#7d669e", // purple
        "#53bbb4", // aqua
        "#51b46d", // green
        "#e0ab18", // mustard
        "#637a91", // dark gray
        "#f092b0", // pink
        "#b7c0c7"  // light gray
    };

    public StoryAdapter(Context context, List<SemuaStoryItem> storyList){
        this.mContext = context;
        semuaStoryItemList = storyList;
    }

    @Override
    public StoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_story, parent, false);
        return new StoryHolder(itemView);
    }

    @Override
    public void onBindViewHolder(StoryHolder holder, int position) {
        final SemuaStoryItem semuaStoryItem = semuaStoryItemList.get(position);
        holder.tvUsername.setText(semuaStoryItem.getUsername());
        holder.tvStatus.setText(semuaStoryItem.getStatus());

        String namaUsername = semuaStoryItem.getUsername();
        String firstCharNamaUsername = namaUsername.substring(0, 1);
        TextDrawable drawable = TextDrawable.builder()
                .buildRect(firstCharNamaUsername, getColor());
        holder.ivTextDrawable.setImageDrawable(drawable);
    }

    @Override
    public int getItemCount() {
        return semuaStoryItemList.size();
    }

    public class StoryHolder extends RecyclerView.ViewHolder {
        ImageView ivTextDrawable;
        TextView tvUsername;
        TextView tvStatus;

        public StoryHolder(View itemView) {
            super(itemView);
            ivTextDrawable = itemView.findViewById(R.id.ivTextDrawable);
            tvUsername = itemView.findViewById(R.id.tvUsername);
            tvStatus = itemView.findViewById(R.id.tvStatus);
        }
    }

    public int getColor() {
        String color;

        // Randomly select a fact
        Random randomGenerator = new Random(); // Construct a new Random number generator
        int randomNumber = randomGenerator.nextInt(mColors.length);

        color = mColors[randomNumber];
        int colorAsInt = Color.parseColor(color);

        return colorAsInt;
    }
}
