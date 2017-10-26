package com.test.santiago.condorlabs.view.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;

import com.spotify.sdk.android.authentication.AuthenticationClient;
import com.spotify.sdk.android.authentication.AuthenticationRequest;
import com.spotify.sdk.android.authentication.AuthenticationResponse;
import com.test.santiago.condorlabs.R;
import com.test.santiago.condorlabs.util.Constants;
import com.test.santiago.condorlabs.view.interfaces.IHomeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Santiago on 10/26/17.
 */

public class DialogLogin extends DialogFragment {

    @BindView(R.id.btnLogin)
    Button btnLogin;
    private IHomeView iHomeView;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_login);
        dialog.setCanceledOnTouchOutside(false);
        Window window = dialog.getWindow();
        window.setLayout(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        ButterKnife.bind(this, dialog.getWindow().getDecorView());
        iHomeView = (IHomeView) getActivity();
        return dialog;
    }

    @OnClick(R.id.btnLogin)
    public void login() {
        AuthenticationRequest.Builder builder = new AuthenticationRequest.Builder(Constants.CLIENT_ID,
                AuthenticationResponse.Type.TOKEN,
                Constants.REDIRECT_URI);
        builder.setScopes(new String[]{"user-read-private", "streaming"});
        AuthenticationRequest request = builder.build();

        AuthenticationClient.openLoginActivity((Activity) getContext(),
                Constants.REQUEST_CODE_LOGIN_SPOTIFY, request);
        dismiss();
    }

}
