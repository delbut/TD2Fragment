package com.esgi.td2fragment.activity;

import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.esgi.td2fragment.R;
import com.esgi.td2fragment.models.User;
import com.esgi.td2fragment.network.GitHubAPIService;
import com.esgi.td2fragment.utils.PreferenceHelper;
import com.esgi.td2fragment.utils.SessionData;
import com.pixplicity.easyprefs.library.Prefs;

/**
 * Created by maxime on 15/06/16.
 */
public class ChooseAccountActivity extends BaseActivity {

    private EditText etUsername;
    private Button bSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_account);

        new Prefs.Builder()
                .setContext(getApplication())
                .setMode(ContextWrapper.MODE_PRIVATE)
                .setPrefsName(getPackageName())
                .setUseDefaultSharedPreference(true)
                .build();

        initView();

    }

    private void initView() {
        this.etUsername = (EditText) this.findViewById(R.id.et_username);
        this.bSubmit = (Button) this.findViewById(R.id.b_submit);

        this.etUsername.setText(PreferenceHelper.getPreviousLogin() == null ? "" : PreferenceHelper.getPreviousLogin());

        if(this.bSubmit != null) {
            this.bSubmit.setOnClickListener(onClickListener);
        }
    }

    private final View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(etUsername.getText() != null && !etUsername.getText().toString().isEmpty()) {
                GitHubAPIService.getInstance().getUser(etUsername.getText().toString(), new GitHubAPIService.ApiResult<User>() {
                    @Override
                    public void success(User res) {
                        SessionData.getInstance().setCurrentUser(res);
                        displayNextActivity();
                    }

                    @Override
                    public void error(int code, String message) {
                        Log.d("----Error------", message);
                    }
                });
            }
        }
    };

    private void displayNextActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        this.finish();
    }
}
