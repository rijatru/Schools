package com.b1gdigital.schools.di.components;

import com.b1gdigital.schools.App;
import com.b1gdigital.schools.MainActivity;
import com.b1gdigital.schools.di.modules.GeneralModule;
import com.b1gdigital.schools.di.scopes.GeneralScope;
import com.b1gdigital.schools.fragments.FragmentTest;

import dagger.Component;

@GeneralScope
@Component(dependencies={NetComponent.class}, modules={GeneralModule.class})
public interface GeneralComponent {

    void inject(MainActivity activity);

    void inject(App app);

    void inject(FragmentTest fragment);
}
