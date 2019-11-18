package com.example.got.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import androidx.annotation.NonNull;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

public class SignInAdapter {

    FirebaseUser user;

    public boolean checkIfSignedIn() {
        user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            return true;
        } else {
            return false;
        }
    }

    public Intent getSignInIntent() {
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build());

        Intent signInIntent = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                //.setLogo
                .build();
        return signInIntent;
    }

    public void signOut(Context context) {
        AuthUI.getInstance()
                .signOut(context)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    public void onComplete(@NonNull Task<Void> task) {

                    }
                });
    }

    public String getName() {
        if (checkIfSignedIn()) {
            return user.getDisplayName();
        }
        return null;
    }

    public String getEmail() {
        if (checkIfSignedIn()) {
            return user.getEmail();
        }
        return null;
    }



}
