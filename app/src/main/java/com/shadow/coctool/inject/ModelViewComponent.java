package com.shadow.coctool.inject;

import com.shadow.coctool.retrofit.ClientModule;
import com.shadow.coctool.room.modelview.CreateRoomModelView;
import com.shadow.coctool.room.modelview.JoinModelView;
import com.shadow.coctool.room.modelview.OptionModelView;
import com.shadow.coctool.room.modelview.RoomModelView;

import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Created by LXF on 2017/7/24.
 */

@Component (
        modules = {
                AppModule.class,
                ClientModule.class
        }
)
public interface ModelViewComponent {
    CreateRoomModelView createRoomModelView();

    JoinModelView joinModelView();

    OptionModelView optionModelView();

    RoomModelView roomModelView();

}
