package com.example.williammontiel.willmontiel.fragments;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.app.Fragment;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import com.android.volley.VolleyError;
import com.example.williammontiel.willmontiel.R;
import com.example.williammontiel.willmontiel.misc.VolleyErrorHandler;
import com.example.williammontiel.willmontiel.resources.Cons;

/**
 * Created by Will Montiel on 02/09/2017.
 */

public class FragmentBase extends Fragment {
    View progress;
    View layout;

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    public void showProgress(final boolean show, final View view, final View progress) {
        if(isAdded()){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
                int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

                view.setVisibility(show ? View.GONE : View.VISIBLE);
                view.animate().setDuration(shortAnimTime).alpha(
                        show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        view.setVisibility(show ? View.GONE : View.VISIBLE);
                    }
                });

                progress.setVisibility(show ? View.VISIBLE : View.GONE);
                progress.animate().setDuration(shortAnimTime).alpha(
                        show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        progress.setVisibility(show ? View.VISIBLE : View.GONE);
                    }
                });
            } else {
                progress.setVisibility(show ? View.VISIBLE : View.GONE);
                view.setVisibility(show ? View.GONE : View.VISIBLE);
            }
        }
    }

    protected void validateErrorResponse(VolleyError error) {
        VolleyErrorHandler voleyErrorHandler = new VolleyErrorHandler();
        voleyErrorHandler.setVolleyError(error);
        voleyErrorHandler.process();
        String msg = voleyErrorHandler.getMessage();
        String message = (TextUtils.isEmpty(msg) ? Cons.SERVER_ERROR : msg);
        setErrorSnackBar(layout, message);
        showProgress(false, layout, progress);
    }

    protected void setErrorSnackBar(View view, String message) {
        if(isAdded()){
            Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG);
            snackbar.show();
            View snackBarView = snackbar.getView();
            snackBarView.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            TextView txtv = (TextView) snackBarView.findViewById(android.support.design.R.id.snackbar_text);
            txtv.setGravity(Gravity.CENTER);
        }
    }
}
