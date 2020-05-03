package fr.isima.tp_squelette_spacex.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import fr.isima.tp_squelette_spacex.adapter.Launch;
import fr.isima.tp_squelette_spacex.adapter.LaunchAdapter;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


import androidx.annotation.Nullable;

import fr.isima.tp_squelette_spacex.R;
import fr.isima.tp_squelette_spacex.ws.WsManager;

import static android.content.Intent.ACTION_VIEW;


public class LaunchesActivity extends Activity implements AdapterView.OnItemClickListener, Callback<List<Launch>> {

    ProgressBar progressbar;
    ListView listview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launches);
        progressbar = (ProgressBar)findViewById(R.id.progressbar);
        listview = (ListView)findViewById(R.id.listview);
        listview.setOnItemClickListener(this);
        loadLaunches();

    }

    public void loadLaunches(){
        progressbar.setVisibility(View.VISIBLE);
        WsManager.getSpaceXService().listLaunches().enqueue(this);

    }

    @Override
    public void onResponse(Call<List<Launch>> call, Response<List<Launch>> response) {
        progressbar.setVisibility(View.GONE);
        listview.setAdapter(new LaunchAdapter(this, R.layout.launchadapter, response.body()));
    }

    @Override
    public void onFailure(Call<List<Launch>> call, Throwable t) {
        progressbar.setVisibility(View.GONE);
        Toast.makeText(this, "Erreur", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent;
        Launch launch = (Launch)parent.getItemAtPosition(position);
        if(launch.link.article_link == null){
            Toast.makeText(this,"Erreur de lien", Toast.LENGTH_SHORT).show();
        }

        else{
            if(launch.link.article_link.contains("https")){
                intent = new Intent(this, LaunchesActivity.class).putExtra("Launch", launch);
            }

            else{
                intent = new Intent(ACTION_VIEW, Uri.parse(launch.link.article_link));
            }
            startActivity(intent);
        }

    }


}
