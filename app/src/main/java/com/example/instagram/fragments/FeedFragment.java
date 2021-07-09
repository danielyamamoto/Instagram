package com.example.instagram.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.instagram.R;
import com.example.instagram.activities.LoginActivity;
import com.example.instagram.adapters.FeedAdapter;
import com.example.instagram.databinding.FragmentFeedBinding;
import com.example.instagram.models.Post;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class FeedFragment extends Fragment {

    public static final String TAG = "ProfileFragment";

    private FragmentFeedBinding binding;
    private SwipeRefreshLayout swipeContainer;
    private FeedAdapter adapter;
    private List<Post> allPosts;
    private RecyclerView rvPosts;
    private ImageView ivProfile;
    private TextView tvUsername, tvTextname;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentFeedBinding.inflate(getLayoutInflater(), container, false);
        // Layout of activity is stored in a special property called root
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Find the recycler view
        rvPosts = binding.rvPostsFeed;

        // Set the toolbar
        Toolbar toolbar = binding.tbFeed;
        toolbar.inflateMenu(R.menu.menu_feed_navigation);
            toolbar.setOnMenuItemClickListener(item -> {
                switch (item.getItemId()) {
                    case R.id.action_settings: logout(); break;
                    default: return false;
                }
                return true;
            });

        // Init the list of posts and adapter
        allPosts = new ArrayList<>();
        adapter = new FeedAdapter(getContext(), allPosts);

        // Recycler view setup: layout manager and the adapter
        rvPosts.setAdapter(adapter);
        rvPosts.setLayoutManager(new GridLayoutManager(getContext(), 3));

        // Lookup the swipe container view
        swipeContainer = binding.swipeContainer;
        // Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                adapter.clear();
                // Get all the post
                queryPost();
                swipeContainer.setRefreshing(false);
            }
        });
        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        tvUsername = binding.tvUsername;
        tvUsername.setText(ParseUser.getCurrentUser().getUsername());

        tvTextname = binding.tvText;
        tvTextname.setText(ParseUser.getCurrentUser().getUsername());

        ivProfile = binding.ivPicProfile;

        // Picture profile
        ParseUser currentUser = ParseUser.getCurrentUser();
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

        queryPost();
    }

    private void queryPost() {
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
        query.include(Post.KEY_USER);
        query.whereEqualTo(Post.KEY_USER, ParseUser.getCurrentUser());
        query.setLimit(20);
        query.addDescendingOrder(Post.KEY_CREATED_AT);
        query.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> posts, ParseException e) {
                if(e != null) {
                    Log.e(TAG, "Issue with getting posts", e);
                    return;
                }
                for (Post post : posts) {
                    Log.i(TAG, "Post" + post.getDescription());
                }
                allPosts.addAll(posts);
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void logout() {
        ParseUser.logOut();
        ParseUser currentUser = ParseUser.getCurrentUser();
        getActivity().finish();
        Intent intent = new Intent(getContext(), LoginActivity.class);
        startActivity(intent);
    }
}
