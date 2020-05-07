package com.example.trainpnrsubmit.ui.login;

import android.app.Activity;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.trainpnrsubmit.R;
import com.example.trainpnrsubmit.ui.login.LoginViewModel;
import com.example.trainpnrsubmit.ui.login.LoginViewModelFactory;

public class LoginActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final Context currentContext = this.getApplicationContext();

        final EditText usernameEditText = findViewById(R.id.pnr_no);
        final Button loginButton = findViewById(R.id.submitPnr);
        loginButton.setActivated(true);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(currentContext , PnrActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("pnrNo",usernameEditText.getText().toString());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

}
