// Generated by view binder compiler. Do not edit!
package com.example.countrygeodirectory.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import com.example.countrygeodirectory.R;
import com.google.android.material.card.MaterialCardView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class CountryItemBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ImageView backFlag;

  @NonNull
  public final MaterialCardView card;

  @NonNull
  public final TextView countryCapital;

  @NonNull
  public final TextView countryName;

  @NonNull
  public final ImageView flagIcon;

  @NonNull
  public final ImageView transparent;

  private CountryItemBinding(@NonNull ConstraintLayout rootView, @NonNull ImageView backFlag,
      @NonNull MaterialCardView card, @NonNull TextView countryCapital,
      @NonNull TextView countryName, @NonNull ImageView flagIcon, @NonNull ImageView transparent) {
    this.rootView = rootView;
    this.backFlag = backFlag;
    this.card = card;
    this.countryCapital = countryCapital;
    this.countryName = countryName;
    this.flagIcon = flagIcon;
    this.transparent = transparent;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static CountryItemBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static CountryItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.country_item, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static CountryItemBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.back_flag;
      ImageView backFlag = rootView.findViewById(id);
      if (backFlag == null) {
        break missingId;
      }

      id = R.id.card;
      MaterialCardView card = rootView.findViewById(id);
      if (card == null) {
        break missingId;
      }

      id = R.id.country_capital;
      TextView countryCapital = rootView.findViewById(id);
      if (countryCapital == null) {
        break missingId;
      }

      id = R.id.country_name;
      TextView countryName = rootView.findViewById(id);
      if (countryName == null) {
        break missingId;
      }

      id = R.id.flag_icon;
      ImageView flagIcon = rootView.findViewById(id);
      if (flagIcon == null) {
        break missingId;
      }

      id = R.id.transparent;
      ImageView transparent = rootView.findViewById(id);
      if (transparent == null) {
        break missingId;
      }

      return new CountryItemBinding((ConstraintLayout) rootView, backFlag, card, countryCapital,
          countryName, flagIcon, transparent);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}