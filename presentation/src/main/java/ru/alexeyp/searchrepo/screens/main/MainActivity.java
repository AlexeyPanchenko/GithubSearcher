package ru.alexeyp.searchrepo.screens.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.alexeyp.data.settings.AppPreferences;
import ru.alexeyp.searchrepo.R;
import ru.alexeyp.searchrepo.di.Scopes;
import ru.alexeyp.searchrepo.screens.start.StartActivity;
import toothpick.Toothpick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_clear)
    Button button;

    @OnClick(R.id.btn_clear)
    void onCl() {
        new AppPreferences(getApplicationContext()).removeUser();
        Intent intent = new Intent(this, StartActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        ButterKnife.bind(this);
        Log.d("SCOPES", "MAIN" + Toothpick.openScope(Scopes.APP_SCOPE));
    }
}
