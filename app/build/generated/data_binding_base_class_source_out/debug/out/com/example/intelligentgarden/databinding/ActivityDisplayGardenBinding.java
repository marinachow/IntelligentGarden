// Generated by view binder compiler. Do not edit!
package com.example.intelligentgarden.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.intelligentgarden.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityDisplayGardenBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button AddPlantButton;

  @NonNull
  public final Button EditSurfaceButton;

  @NonNull
  public final TableLayout tableLayout;

  private ActivityDisplayGardenBinding(@NonNull ConstraintLayout rootView,
      @NonNull Button AddPlantButton, @NonNull Button EditSurfaceButton,
      @NonNull TableLayout tableLayout) {
    this.rootView = rootView;
    this.AddPlantButton = AddPlantButton;
    this.EditSurfaceButton = EditSurfaceButton;
    this.tableLayout = tableLayout;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityDisplayGardenBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityDisplayGardenBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_display_garden, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityDisplayGardenBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.AddPlantButton;
      Button AddPlantButton = ViewBindings.findChildViewById(rootView, id);
      if (AddPlantButton == null) {
        break missingId;
      }

      id = R.id.EditSurfaceButton;
      Button EditSurfaceButton = ViewBindings.findChildViewById(rootView, id);
      if (EditSurfaceButton == null) {
        break missingId;
      }

      id = R.id.tableLayout;
      TableLayout tableLayout = ViewBindings.findChildViewById(rootView, id);
      if (tableLayout == null) {
        break missingId;
      }

      return new ActivityDisplayGardenBinding((ConstraintLayout) rootView, AddPlantButton,
          EditSurfaceButton, tableLayout);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
