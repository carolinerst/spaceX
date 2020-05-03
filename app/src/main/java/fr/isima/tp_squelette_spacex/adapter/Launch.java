package fr.isima.tp_squelette_spacex.adapter;

import java.io.Serializable;

public class Launch implements Serializable {
    String mission_name;
    Integer launch_date_unix;
    LaunchInfoRocket rocket;
    public Links link;
}
