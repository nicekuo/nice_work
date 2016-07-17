package com.core.map;

/**
 * Created by nice on 16/4/22.
 */
public class MapModel  {

    private String map_name;
    private int map_image_resource;
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getMap_name() {
        return map_name;
    }

    public void setMap_name(String map_name) {
        this.map_name = map_name;
    }

    public int getMap_image_resource() {
        return map_image_resource;
    }

    public void setMap_image_resource(int map_image_resource) {
        this.map_image_resource = map_image_resource;
    }
}
