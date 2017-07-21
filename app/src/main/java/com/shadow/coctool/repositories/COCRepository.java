package com.shadow.coctool.repositories;

import com.shadow.coctool.retrofit.Clients.COCClient;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observer;

/**
 * Created by lxf on 2017/7/20.
 */

public class COCRepository extends Repository {
    @Inject
    COCClient cocClient;

    @Inject
    public COCRepository() {

    }

    public void createRoom(String name, String port, Observer observer) {
        Map params = new HashMap();
        params.put("name", name);
        params.put("port", port);

        run(cocClient.newRoom(params), observer);
    }

}
