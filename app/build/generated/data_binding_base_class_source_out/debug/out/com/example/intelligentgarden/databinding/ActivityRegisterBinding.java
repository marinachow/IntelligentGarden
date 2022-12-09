// Generated by view binder compiler. Do not edit!
package com.example.intelligentgarden.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.intelligentgarden.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityRegisterBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final EditText Email;

  @NonNull
  public final EditText FullName;

  @NonNull
  public final EditText Password;

  @NonNull
  public final EditText Phone;

  @NonNull
  public final Button RegisterBtn;

  @NonNull
  public final TextView authentificatorApp;

  @NonNull
  public final Button button2;

  @NonNull
  public final TextView createNewAccount;

  @NonNull
  public final TextView createText2;

  @NonNull
  public final ProgressBar progressBar;

  private ActivityRegisterBinding(@NonNull ConstraintLayout rootView, @NonNull EditText Email,
      @NonNull EditText FullName, @NonNull EditText Password, @NonNull EditText Phone,
      @NonNull Button RegisterBtn, @NonNull TextView authentificatorApp, @NonNull Button button2,
      @NonNull TextView createNewAccount, @NonNull TextView createText2,
      @NonNull ProgressBar progressBar) {
    this.rootView = rootView;
    this.Email = Email;
    this.FullName = FullName;
    this.Password = Password;
    this.Phone = Phone;
    this.RegisterBtn = RegisterBtn;
    this.authentificatorApp = authentificatorApp;
    this.button2 = button2;
    this.createNewAccount = createNewAccount;
    this.createText2 = createText2;
    this.progressBar = progressBar;
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
      id = R.id.Email;
      EditText Email = ViewBindings.findChildViewById(rootView, id);
      if (Email == null) {
        break missingId;
      }

      id = R.id.FullName;
      EditText FullName = ViewBindings.findChildViewById(rootView, id);
      if (FullName == null) {
        break missingId;
      }

      id = R.id.Password;
      EditText Password = ViewBindings.findChildViewById(rootView, id);
      if (Password == null) {
        break missingId;
      }

      id = R.id.Phone;
      EditText Phone = ViewBindings.findChildViewById(rootView, id);
      if (Phone == null) {
        break missingId;
      }

      id = R.id.RegisterBtn;
      Button RegisterBtn = ViewBindings.findChildViewById(rootView, id);
      if (RegisterBtn == null) {
        break missingId;
      }

      id = R.id.authentificator_app;
      TextView authentificatorApp = ViewBindings.findChildViewById(rootView, id);
      if (authentificatorApp == null) {
        break missingId;
      }

      id = R.id.button2;
      Button button2 = ViewBindings.findChildViewById(rootView, id);
      if (button2 == null) {
        break missingId;
      }

      id = R.id.create_new_account;
      TextView createNewAccount = ViewBindings.findChildViewById(rootView, id);
      if (createNewAccount == null) {
        break missingId;
      }

      id = R.id.createText2;
      TextView createText2 = ViewBindings.findChildViewById(rootView, id);
      if (createText2 == null) {
        break missingId;
      }

      id = R.id.progressBar;
      ProgressBar progressBar = ViewBindings.findChildViewById(rootView, id);
      if (progressBar == null) {
        break missingId;
      }

      return new ActivityRegisterBinding((ConstraintLayout) rootView, Email, FullName, Password,
          Phone, RegisterBtn, authentificatorApp, button2, createNewAccount, createText2,
          progressBar);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}