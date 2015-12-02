package me.ccrama.redditslide.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

import me.ccrama.redditslide.Adapters.SettingsSubAdapter;
import me.ccrama.redditslide.R;
import me.ccrama.redditslide.SettingValues;
import me.ccrama.redditslide.SubredditStorage;
import me.ccrama.redditslide.Visuals.Palette;


/**
 * Created by ccrama on 3/5/2015.
 */
public class SettingsSubreddit extends BaseActivity {

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 2) {
            Intent i = new Intent(SettingsSubreddit.this, SettingsSubreddit.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(i);
            overridePendingTransition(0, 0);

            finish();
            overridePendingTransition(0, 0);


        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        applyColorTheme();
        setContentView(R.layout.activity_settings_subreddit);
        setupAppBar(R.id.toolbar, R.string.title_subreddit_settings, true);

        ListView l = (ListView) findViewById(R.id.subslist);
        ArrayList<String> done = new ArrayList<>();
        for (String s : SubredditStorage.alphabeticalSubscriptions) {
            if (Palette.getColor(s) != Palette.getDefaultColor()) {
                done.add(s);
            } else if (SettingValues.prefs.contains("PRESET" + s)) {
                done.add(s);
            }
        }
        final SettingsSubAdapter adapter = new SettingsSubAdapter(this, done);
        l.setAdapter(adapter);
    }

}