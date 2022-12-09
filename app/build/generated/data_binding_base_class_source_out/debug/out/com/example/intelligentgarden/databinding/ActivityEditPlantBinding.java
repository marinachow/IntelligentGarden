// Generated by view binder compiler. Do not edit!
package com.example.intelligentgarden.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
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

public final class ActivityEditPlantBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button buttonCancel;

  @NonNull
  public final Button buttonOk;

  @NonNull
  public final EditText editTextName;

  @NonNull
  public final EditText editTextQty;

  @NonNull
  public final TableLayout tableLayout;

  @NonNull
  public final TextView textViewEnemies;

  @NonNull
  public final TextView textViewId;

  @NonNull
  public final TextView textViewIdLabel;

  @NonNull
  public final TextView textViewName;

  @NonNull
  public final TextView textViewNum;

  private ActivityEditPlantBinding(@NonNull ConstraintLayout rootView, @NonNull Button buttonCancel,
      @NonNull Button buttonOk, @NonNull EditText editTextName, @NonNull EditText editTextQty,
      @NonNull TableLayout tableLayout, @NonNull TextView textViewEnemies,
      @NonNull TextView textViewId, @NonNull TextView textViewIdLabel,
      @NonNull TextView textViewName, @NonNull TextView textViewNum) {
    this.rootView = rootView;
    this.buttonCancel = buttonCancel;
    this.buttonOk = buttonOk;
    this.editTextName = editTextName;
    this.editTextQty = editTextQty;
    this.tableLayout = tableLayout;
    this.textViewEnemies = textViewEnemies;
    this.textViewId = textViewId;
    this.textViewIdLabel = textViewIdLabel;
    this.textViewName = textViewName;
    this.textViewNum = textViewNum;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityEditPlantBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityEditPlantBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_edit_plant, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityEditPlantBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.buttonCancel;
      Button buttonCancel = ViewBindings.findChildViewById(rootView, id);
      if (buttonCancel == null) {
        break missingId;
      }

      id = R.id.buttonOk;
      Button buttonOk = ViewBindings.findChildViewById(rootView, id);
      if (buttonOk == null) {
        break missingId;
      }

      id = R.id.editTextName;
      EditText editTextName = ViewBindings.findChildViewById(rootView, id);
      if (editTextName == null) {
        break missingId;
      }

      id = R.id.editTextQty;
      EditText editTextQty = ViewBindings.findChildViewById(rootView, id);
      if (editTextQty == null) {
        break missingId;
      }

      id = R.id.tableLayout;
      TableLayout tableLayout = ViewBindings.findChildViewById(rootView, id);
      if (tableLayout == null) {
        break missingId;
      }

      id = R.id.textViewEnemies;
      TextView textViewEnemies = ViewBindings.findChildViewById(rootView, id);
      if (textViewEnemies == null) {
        break missingId;
      }

      id = R.id.textViewId;
      TextView textViewId = ViewBindings.findChildViewById(rootView, id);
      if (textViewId == null) {
        break missingId;
      }

      id = R.id.textViewIdLabel;
      TextView textViewIdLabel = ViewBindings.findChildViewById(rootView, id);
      if (textViewIdLabel == null) {
        break missingId;
      }

      id = R.id.textViewName;
      TextView textViewName = ViewBindings.findChildViewById(rootView, id);
      if (textViewName == null) {
        break missingId;
      }

      id = R.id.textViewNum;
      TextView textViewNum = ViewBindings.findChildViewById(rootView, id);
      if (textViewNum == null) {
        break missingId;
      }

      return new ActivityEditPlantBinding((ConstraintLayout) rootView, buttonCancel, buttonOk,
          editTextName, editTextQty, tableLayout, textViewEnemies, textViewId, textViewIdLabel,
          textViewName, textViewNum);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
