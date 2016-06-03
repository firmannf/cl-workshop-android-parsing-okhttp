package me.firmannizammudin.workshopparsing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import me.firmannizammudin.workshopparsing.adapter.GithubAdapter;
import me.firmannizammudin.workshopparsing.model.GithubModel;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvGithub;
    private GithubAdapter githubAdapter;
    private ArrayList<GithubModel> githubList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configRecylerView();
        prepareData();
    }

    private void configRecylerView() {
        rvGithub = (RecyclerView) findViewById(R.id.recyclerview_github);
        rvGithub.setHasFixedSize(true);
        rvGithub.setLayoutManager(new LinearLayoutManager(this));
        rvGithub.setItemAnimator(new DefaultItemAnimator());

        githubAdapter = new GithubAdapter(githubList);
        rvGithub.setAdapter(githubAdapter);
    }

    private void prepareData() {
        OkHttpClient client = new OkHttpClient();
        Call call = getData("https://api.github.com/users/jokersayhi/repos", client);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    try {
                        JSONArray jsonArray = new JSONArray(response.body().string());
                        GithubModel github;
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            github = new GithubModel(
                                    jsonObject.getInt("id"),
                                    jsonObject.getString("name"),
                                    jsonObject.getString("full_name")
                            );
                            githubList.add(github);
                        }

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                githubAdapter.notifyDataSetChanged();
                            }
                        });
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private Call getData(String url, OkHttpClient client) {
        Request request = new Request.Builder()
                .url(url)
                .build();

        return client.newCall(request);
    }
}