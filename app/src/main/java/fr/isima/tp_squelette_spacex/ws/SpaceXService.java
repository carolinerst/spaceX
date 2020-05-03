package fr.isima.tp_squelette_spacex.ws;


import java.util.List;

import fr.isima.tp_squelette_spacex.adapter.Launch;
import retrofit2.Call;
import retrofit2.http.GET;




public interface SpaceXService {

    @GET("launches")
    Call<List<Launch>> listLaunches();
};


