// Generated by view binder compiler. Do not edit!
package com.example.second_try.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.second_try.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityRegisterBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final EditText emailEditText;

  @NonNull
  public final LinearLayout inputFields;

  @NonNull
  public final Button loginBtn;

  @NonNull
  public final LinearLayout loginButtonLayout;

  @NonNull
  public final TextView loginSubtitle;

  @NonNull
  public final EditText passwordEditText;

  @NonNull
  public final Button registerBtn;

  @NonNull
  public final LinearLayout registerButtonLayout;

  @NonNull
  public final TextView signUpTitle;

  @NonNull
  public final TextView subtitle;

  @NonNull
  public final EditText usernameEditText;

  private ActivityRegisterBinding(@NonNull ConstraintLayout rootView,
      @NonNull EditText emailEditText, @NonNull LinearLayout inputFields, @NonNull Button loginBtn,
      @NonNull LinearLayout loginButtonLayout, @NonNull TextView loginSubtitle,
      @NonNull EditText passwordEditText, @NonNull Button registerBtn,
      @NonNull LinearLayout registerButtonLayout, @NonNull TextView signUpTitle,
      @NonNull TextView subtitle, @NonNull EditText usernameEditText) {
    this.rootView = rootView;
    this.emailEditText = emailEditText;
    this.inputFields = inputFields;
    this.loginBtn = loginBtn;
    this.loginButtonLayout = loginButtonLayout;
    this.loginSubtitle = loginSubtitle;
    this.passwordEditText = passwordEditText;
    this.registerBtn = registerBtn;
    this.registerButtonLayout = registerButtonLayout;
    this.signUpTitle = signUpTitle;
    this.subtitle = subtitle;
    this.usernameEditText = usernameEditText;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityRegisterBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityRegisterBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_register, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityRegisterBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.emailEditText;
      EditText emailEditText = ViewBindings.findChildViewById(rootView, id);
      if (emailEditText == null) {
        break missingId;
      }

      id = R.id.input_fields;
      LinearLayout inputFields = ViewBindings.findChildViewById(rootView, id);
      if (inputFields == null) {
        break missingId;
      }

      id = R.id.loginBtn;
      Button loginBtn = ViewBindings.findChildViewById(rootView, id);
      if (loginBtn == null) {
        break missingId;
      }

      id = R.id.login_button_layout;
      LinearLayout loginButtonLayout = ViewBindings.findChildViewById(rootView, id);
      if (loginButtonLayout == null) {
        break missingId;
      }

      id = R.id.login_subtitle;
      TextView loginSubtitle = ViewBindings.findChildViewById(rootView, id);
      if (loginSubtitle == null) {
        break missingId;
      }

      id = R.id.passwordEditText;
      EditText passwordEditText = ViewBindings.findChildViewById(rootView, id);
      if (passwordEditText == null) {
        break missingId;
      }

      id = R.id.registerBtn;
      Button registerBtn = ViewBindings.findChildViewById(rootView, id);
      if (registerBtn == null) {
        break missingId;
      }

      id = R.id.register_button_layout;
      LinearLayout registerButtonLayout = ViewBindings.findChildViewById(rootView, id);
      if (registerButtonLayout == null) {
        break missingId;
      }

      id = R.id.signUpTitle;
      TextView signUpTitle = ViewBindings.findChildViewById(rootView, id);
      if (signUpTitle == null) {
        break missingId;
      }

      id = R.id.subtitle;
      TextView subtitle = ViewBindings.findChildViewById(rootView, id);
      if (subtitle == null) {
        break missingId;
      }

      id = R.id.usernameEditText;
      EditText usernameEditText = ViewBindings.findChildViewById(rootView, id);
      if (usernameEditText == null) {
        break missingId;
      }

      return new ActivityRegisterBinding((ConstraintLayout) rootView, emailEditText, inputFields,
          loginBtn, loginButtonLayout, loginSubtitle, passwordEditText, registerBtn,
          registerButtonLayout, signUpTitle, subtitle, usernameEditText);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
