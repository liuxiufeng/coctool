package com.shadow.coctool.retrofit;

import com.shadow.coctool.COCToolApplication;
import com.shadow.coctool.R;
import com.shadow.coctool.retrofit.Clients.COCClient;

import dagger.Module;
import dagger.Provides;

/**
 * Created by lxf on 2017/7/4.
 */

@Module
public class ClientModule {

    @Provides
    public static COCClient provideCOCClient() {
        return (COCClient) ClientFactory.getInstance().build(COCToolApplication.getContext().getString(R.string.cocClient), COCClient.class);
    }
}
