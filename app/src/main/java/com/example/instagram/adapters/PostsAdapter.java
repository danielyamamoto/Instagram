package com.example.instagram.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.instagram.R;
import com.example.instagram.databinding.ItemPostBinding;
import com.example.instagram.models.Post;
import com.parse.ParseFile;
import com.parse.ParseUser;

import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {

    private Context context;
    private List<Post> posts;

    public PostsAdapter(Context context, List<Post> posts) {
        this.context = context;
        this.posts = posts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostsAdapter.ViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.bind(post);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

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

    // Define a view holder
    class ViewHolder extends RecyclerView.ViewHolder {

        private ItemPostBinding binding;
        private ImageView ivImage, ivProfile;
        private TextView tvUsername, tvDescription, tvDate, tvLikes;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ItemPostBinding.bind(itemView);

            ivProfile = binding.ivPicProfile;
            tvUsername = binding.tvUsername;
            ivImage = binding.ivImage;
            tvDescription = binding.tvDescription;
            tvDate = binding.tvDate;
            tvLikes = binding.tvLikesCount;
        }

        // Bind the post data to the view elements
        public void bind(Post post) {
            // Picture profile
            ParseUser currentUser = post.getUser();
            ParseFile profile = (ParseFile) currentUser.getParseFile("profile");
            if (profile != null) {
                ivProfile.setVisibility(View.VISIBLE);
                Glide.with(context).
                        load(profile.getUrl())
                        .apply(RequestOptions.circleCropTransform())
                        .into(ivProfile);
            } else {
                ivProfile.setVisibility(View.GONE);
            }

            tvUsername.setText(post.getUser().getUsername());
            tvDescription.setText(post.getDescription());

            // Picture post
            ParseFile image = post.getImage();
            if (image != null) {
                ivImage.setVisibility(View.VISIBLE);
                Glide.with(context).
                        load(post.getImage().getUrl())
                        .into(ivImage);

            } else {
                ivImage.setVisibility(View.GONE);
            }

            tvDate.setText(Post.calculateTimeAgo(post.getCreatedAt()));
            tvLikes.setText(String.valueOf(post.getLikesCount()) + " likes");
        }
    }
}
