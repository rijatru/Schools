package com.b1gdigital.schools;

import android.app.Application;
import android.util.Log;

import com.b1gdigital.schools.di.components.DaggerGeneralComponent;
import com.b1gdigital.schools.di.components.DaggerGradeComponent;
import com.b1gdigital.schools.di.components.DaggerNetComponent;
import com.b1gdigital.schools.di.components.DaggerSchoolComponent;
import com.b1gdigital.schools.di.components.GeneralComponent;
import com.b1gdigital.schools.di.components.GradeComponent;
import com.b1gdigital.schools.di.components.NetComponent;
import com.b1gdigital.schools.di.components.SchoolComponent;
import com.b1gdigital.schools.di.modules.AppModule;
import com.b1gdigital.schools.di.modules.GeneralModule;
import com.b1gdigital.schools.di.modules.GradeModule;
import com.b1gdigital.schools.di.modules.NetModule;
import com.b1gdigital.schools.di.modules.SchoolModule;
import com.b1gdigital.schools.models.Message;
import com.b1gdigital.schools.workers.BusWorker;
import com.squareup.otto.Subscribe;

import javax.inject.Inject;

public class App extends Application {

    private NetComponent netComponent;
    private GeneralComponent generalComponent;

    private GradeComponent gradeComponent;
    private SchoolComponent schoolComponent;

    @Inject
    BusWorker busWorker;

    @Inject
    public App() {

    }

    @Override
    public void onCreate() {
        super.onCreate();

        gradeComponent = DaggerGradeComponent.builder()
                .appModule(new AppModule(this))
                .gradeModule(new GradeModule())
                .build();

        schoolComponent = DaggerSchoolComponent.builder()
                .gradeComponent(gradeComponent)
                .schoolModule(new SchoolModule())
                .build();

        netComponent = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule())
                .build();

        generalComponent = DaggerGeneralComponent.builder()
                .netComponent(netComponent)
                .generalModule(new GeneralModule())
                .build();

        generalComponent.inject(this);

        busWorker.register(this);
    }

    @Subscribe
    public void recievedMessage(Message message) {

        Log.d("Dagger", "recievedMessage App: " + message.getMessage());
    }

    public SchoolComponent getSchoolComponent() {

        return schoolComponent;
    }

    public GeneralComponent getGeneralComponent() {

        return generalComponent;
    }

    public NetComponent getNetComponent() {

        return netComponent;
    }
}
