package com.example.instagram.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.instagram.databinding.ActivityDetailsPostBinding;
import com.example.instagram.models.Post;
import com.parse.ParseFile;
import com.parse.ParseUser;

import org.parceler.Parcels;

public class DetailsPostActivity extends AppCompatActivity {

    private Post mPost;

    private ImageView ivImage, ivProfile, ivBack;
    private TextView tvUsername, tvDescription, tvDate, tvLikes, tvText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Binding library
        ActivityDetailsPostBinding binding = ActivityDetailsPostBinding.inflate(getLayoutInflater());
        // Layout of activity is stored in a special property called root
        View view = binding.getRoot();
        setContentView(view);

        // Bindings
        ivProfile = binding.ivPicProfile;
        tvUsername = binding.tvUsername;
        ivImage = binding.ivImage;
        tvDescription = binding.tvDescription;
        tvDate = binding.tvDate;
        tvLikes = binding.tvLikesCount;
        ivBack = binding.ivBack;
        tvText = binding.tvText;

        // Unwrap the tweet passed in via intent, using its simple name as a key
        mPost = (Post) Parcels.unwrap(getIntent().getParcelableExtra(Post.class.getSimpleName()));

        // Set the toolbar
        Toolbar toolbar = binding.tbDetails;
        setSupportActionBar(toolbar);

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tvText.setText("Posts");

        // Picture profile
        ParseUser currentUser = mPost.getUser();
        ParseFile profile = (ParseFile) currentUser.getParseFile("profile");
        if (profile != null) {
            ivProfile.setVisibility(View.VISIBLE);
            Glide.with(this).
                    load(profile.getUrl())
                    .apply(RequestOptions.circleCropTransform())
                    .into(ivProfile);
        } else {
            ivProfile.setVisibility(View.GONE);
        }

        tvUsername.setText(mPost.getUser().getUsername());
        tvDescription.setText(mPost.getDescription());

        // Picture post
        ParseFile image = mPost.getImage();
        if (image != null) {
            ivImage.setVisibility(View.VISIBLE);
            Glide.with(this).
                    load(mPost.getImage().getUrl())
                    .into(ivImage);

        } else {
            ivImage.setVisibility(View.GONE);
        }

        tvDate.setText(Post.calculateTimeAgo(mPost.getCreatedAt()));
        tvLikes.setText(String.valueOf(mPost.getLikesCount()) + " likes");
    }
}