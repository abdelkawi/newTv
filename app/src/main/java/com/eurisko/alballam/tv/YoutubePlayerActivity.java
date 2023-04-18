package com.eurisko.alballam.tv;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class YoutubePlayerActivity extends YouTubeFailureRecoveryActivity {

    String videoId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube_player);
        getExtra();
        setupViews();
    }

    private void getExtra() {
        Intent intent = getIntent();
        if (intent != null) {
            String trailer = intent.getStringExtra("trailer");
            videoId = YoutubeUtils.getVideoIdFromYoutubeUrl(trailer);
        }
    }

    private void setupViews() {

        YouTubePlayerView youTubeView = findViewById(R.id.youtube_view);
        youTubeView.initialize(YoutubeUtils.DEVELOPER_KEY, this);

    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player,
                                        boolean wasRestored) {
        if (!wasRestored) {
            player.cueVideo(videoId);
        }
    }

    @Override
    protected YouTubePlayer.Provider getYouTubePlayerProvider() {
        return (YouTubePlayerView) findViewById(R.id.youtube_view);
    }

}
