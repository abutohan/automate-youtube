package pages;

import base.BaseTest;
import org.json.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class PlayYouTubeMusicTest extends BaseTest {


    @Test(testName = "Play YouTube Music", dataProvider = "getYouTubePlaylist")
    public void playYouTubeMusic(JSONObject testData) throws InterruptedException {

        homePage.setTxtSearch(testData.getString("search_title"));
        YouTubeResultPage youTubeResultPage = homePage.clickSearch();
        YouTubeVideoPage youTubeVideoPage = youTubeResultPage.clickVideoTitle(testData.getString("video_title"));
        String videoDurationString = youTubeVideoPage.getVideoDuration();
        int videoDurationInt = convertDurationToSeconds(videoDurationString) * 1000;
        System.out.printf("Now playing: %s | %s%n", testData.getString("search_title"), videoDurationString);

        youTubeVideoPage.playVideo(videoDurationInt);
    }

    private int convertDurationToSeconds(String videoDuration) {
        if (videoDuration == null || videoDuration.isEmpty()) {
            return 0;
        }
        String[] parts = videoDuration.split(":");
        if (parts.length != 2) {
            return 0;
        }
        try {
            int minutes = Integer.parseInt(parts[0]);
            int seconds = Integer.parseInt(parts[1]);
            return (minutes * 60) + seconds;
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    @DataProvider(name = "getYouTubePlaylist")
    private Object[][] getYouTubePlaylist() throws IOException {
        return getTestDataFromJson("youtube.playlist");
    }
}