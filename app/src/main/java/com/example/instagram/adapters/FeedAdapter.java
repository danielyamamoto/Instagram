package com.example.instagram.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Parcel;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.instagram.R;
import com.example.instagram.databinding.ItemFeedBinding;
import com.example.instagram.utils.Post;
import com.parse.ParseFile;

import java.util.List;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.ViewHolder> {

    private Context context;
    private List<Post> posts;

    public FeedAdapter(Context context, List<Post> posts) {
        this.context = context;
        this.posts = posts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_feed, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedAdapter.ViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.bind(post);
    }

    @Override
    public int getItemCount() { return posts.size(); }

    // Clean all elements of the recycler
    public void clear() {
        posts.clear();
        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public void addAll(List<Post> list) {
        posts.addAll(list);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ItemFeedBinding binding;
        private ImageView ivPicFeed;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ItemFeedBinding.bind(itemView);

            ivPicFeed = binding.ivFeedPic;
        }

        public void bind(Post post) {
            // Picture post
            ParseFile image = post.getImage();
            if (image != null) {
                ivPicFeed.setVisibility(View.VISIBLE);
                Glide.with(context).
                        load(post.getImage().getUrl())
                        .into(ivPicFeed);

            } else {
                ivPicFeed.setVisibility(View.GONE);
            }

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Intent intent = new Intent(context, DetailsActivity.class);
                    //intent.putExtra("postDetails", Parcel.wrap(post));
                    //context.startActivity(intent);
                }
            });
        }
    }
}
