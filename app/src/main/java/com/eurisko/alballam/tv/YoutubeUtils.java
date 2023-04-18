package com.eurisko.alballam.tv;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeStandalonePlayer;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class YoutubeUtils {


    /**
     * Please replace this with a valid API key which is enabled for the
     * YouTube Data API v3 service. Go to the
     * <a href="https://console.developers.google.com/">Google Developers Console</a>
     * to register a new developer key.
     */
    public static final String DEVELOPER_KEY = "AIzaSyALoHahVi9lAnvNe7AKHSmQPBssaemfqjU";

    // private static final String VIDEO_ID = "cdgQpa1pUUE";
    public static final int REQ_START_STANDALONE_PLAYER = 1;
    public static final int REQ_RESOLVE_SERVICE_MISSING = 2;

    public static void openYoutubePlayer(Activity activity, String youtubeUrl, int startTimeMillis, boolean autoplay, boolean popupMode) {
        Intent intent = null;
        intent = YouTubeStandalonePlayer.createVideoIntent(
                activity, DEVELOPER_KEY, getVideoIdFromYoutubeUrl(youtubeUrl), startTimeMillis, autoplay, popupMode);

        if (intent != null) {
            if (canResolveIntent(intent, activity)) {
                activity.startActivityForResult(intent, REQ_START_STANDALONE_PLAYER);
            } else {
                // Could not resolve the intent - must need to install or update the YouTube API service.
                YouTubeInitializationResult.SERVICE_MISSING
                        .getErrorDialog(activity, REQ_RESOLVE_SERVICE_MISSING).show();
            }
        }
    }

    private static boolean canResolveIntent(Intent intent, Activity activity) {
        List<ResolveInfo> resolveInfo = activity.getPackageManager().queryIntentActivities(intent, 0);
        return resolveInfo != null && !resolveInfo.isEmpty();
    }

    public static String getVideoIdFromYoutubeUrl(String url) {
        String videoId = "";
        String regex = "http(?:s)?:\\/\\/(?:m.)?(?:www\\.)?youtu(?:\\.be\\/|be\\.com\\/(?:watch\\?(?:feature=youtu.be\\&)?v=|v\\/|embed\\/|user\\/(?:[\\w#]+\\/)+))([^&#?\\n]+)";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(url);
        if (matcher.find()) {
            videoId = matcher.group(1);
        }
        return videoId;
    }
}
