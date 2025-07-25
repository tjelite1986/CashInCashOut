// Generated by view binder compiler. Do not edit!
package com.example.budgetapp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.budgetapp.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentAnalyticsBinding implements ViewBinding {
  @NonNull
  private final SwipeRefreshLayout rootView;

  @NonNull
  public final MaterialButton buttonGenerateInsights;

  @NonNull
  public final MaterialCardView cardFinancialHealth;

  @NonNull
  public final ImageView iconCriticalWarning;

  @NonNull
  public final ImageView iconHighWarning;

  @NonNull
  public final MaterialCardView layoutGoalsProgress;

  @NonNull
  public final ProgressBar progressBarGoalsOverall;

  @NonNull
  public final ProgressBar progressBarHealthScore;

  @NonNull
  public final RecyclerView recyclerViewInsights;

  @NonNull
  public final SwipeRefreshLayout swipeRefreshLayout;

  @NonNull
  public final TextView textViewActiveGoals;

  @NonNull
  public final TextView textViewAverageProgress;

  @NonNull
  public final TextView textViewCompletedGoals;

  @NonNull
  public final TextView textViewCriticalInsights;

  @NonNull
  public final TextView textViewHealthScore;

  @NonNull
  public final TextView textViewHealthStatus;

  @NonNull
  public final TextView textViewHighInsights;

  @NonNull
  public final TextView textViewInsightsHeader;

  @NonNull
  public final TextView textViewMediumInsights;

  @NonNull
  public final TextView textViewNoInsights;

  @NonNull
  public final TextView textViewTotalInsights;

  private FragmentAnalyticsBinding(@NonNull SwipeRefreshLayout rootView,
      @NonNull MaterialButton buttonGenerateInsights, @NonNull MaterialCardView cardFinancialHealth,
      @NonNull ImageView iconCriticalWarning, @NonNull ImageView iconHighWarning,
      @NonNull MaterialCardView layoutGoalsProgress, @NonNull ProgressBar progressBarGoalsOverall,
      @NonNull ProgressBar progressBarHealthScore, @NonNull RecyclerView recyclerViewInsights,
      @NonNull SwipeRefreshLayout swipeRefreshLayout, @NonNull TextView textViewActiveGoals,
      @NonNull TextView textViewAverageProgress, @NonNull TextView textViewCompletedGoals,
      @NonNull TextView textViewCriticalInsights, @NonNull TextView textViewHealthScore,
      @NonNull TextView textViewHealthStatus, @NonNull TextView textViewHighInsights,
      @NonNull TextView textViewInsightsHeader, @NonNull TextView textViewMediumInsights,
      @NonNull TextView textViewNoInsights, @NonNull TextView textViewTotalInsights) {
    this.rootView = rootView;
    this.buttonGenerateInsights = buttonGenerateInsights;
    this.cardFinancialHealth = cardFinancialHealth;
    this.iconCriticalWarning = iconCriticalWarning;
    this.iconHighWarning = iconHighWarning;
    this.layoutGoalsProgress = layoutGoalsProgress;
    this.progressBarGoalsOverall = progressBarGoalsOverall;
    this.progressBarHealthScore = progressBarHealthScore;
    this.recyclerViewInsights = recyclerViewInsights;
    this.swipeRefreshLayout = swipeRefreshLayout;
    this.textViewActiveGoals = textViewActiveGoals;
    this.textViewAverageProgress = textViewAverageProgress;
    this.textViewCompletedGoals = textViewCompletedGoals;
    this.textViewCriticalInsights = textViewCriticalInsights;
    this.textViewHealthScore = textViewHealthScore;
    this.textViewHealthStatus = textViewHealthStatus;
    this.textViewHighInsights = textViewHighInsights;
    this.textViewInsightsHeader = textViewInsightsHeader;
    this.textViewMediumInsights = textViewMediumInsights;
    this.textViewNoInsights = textViewNoInsights;
    this.textViewTotalInsights = textViewTotalInsights;
  }

  @Override
  @NonNull
  public SwipeRefreshLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentAnalyticsBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentAnalyticsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_analytics, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentAnalyticsBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.buttonGenerateInsights;
      MaterialButton buttonGenerateInsights = ViewBindings.findChildViewById(rootView, id);
      if (buttonGenerateInsights == null) {
        break missingId;
      }

      id = R.id.cardFinancialHealth;
      MaterialCardView cardFinancialHealth = ViewBindings.findChildViewById(rootView, id);
      if (cardFinancialHealth == null) {
        break missingId;
      }

      id = R.id.iconCriticalWarning;
      ImageView iconCriticalWarning = ViewBindings.findChildViewById(rootView, id);
      if (iconCriticalWarning == null) {
        break missingId;
      }

      id = R.id.iconHighWarning;
      ImageView iconHighWarning = ViewBindings.findChildViewById(rootView, id);
      if (iconHighWarning == null) {
        break missingId;
      }

      id = R.id.layoutGoalsProgress;
      MaterialCardView layoutGoalsProgress = ViewBindings.findChildViewById(rootView, id);
      if (layoutGoalsProgress == null) {
        break missingId;
      }

      id = R.id.progressBarGoalsOverall;
      ProgressBar progressBarGoalsOverall = ViewBindings.findChildViewById(rootView, id);
      if (progressBarGoalsOverall == null) {
        break missingId;
      }

      id = R.id.progressBarHealthScore;
      ProgressBar progressBarHealthScore = ViewBindings.findChildViewById(rootView, id);
      if (progressBarHealthScore == null) {
        break missingId;
      }

      id = R.id.recyclerViewInsights;
      RecyclerView recyclerViewInsights = ViewBindings.findChildViewById(rootView, id);
      if (recyclerViewInsights == null) {
        break missingId;
      }

      SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) rootView;

      id = R.id.textViewActiveGoals;
      TextView textViewActiveGoals = ViewBindings.findChildViewById(rootView, id);
      if (textViewActiveGoals == null) {
        break missingId;
      }

      id = R.id.textViewAverageProgress;
      TextView textViewAverageProgress = ViewBindings.findChildViewById(rootView, id);
      if (textViewAverageProgress == null) {
        break missingId;
      }

      id = R.id.textViewCompletedGoals;
      TextView textViewCompletedGoals = ViewBindings.findChildViewById(rootView, id);
      if (textViewCompletedGoals == null) {
        break missingId;
      }

      id = R.id.textViewCriticalInsights;
      TextView textViewCriticalInsights = ViewBindings.findChildViewById(rootView, id);
      if (textViewCriticalInsights == null) {
        break missingId;
      }

      id = R.id.textViewHealthScore;
      TextView textViewHealthScore = ViewBindings.findChildViewById(rootView, id);
      if (textViewHealthScore == null) {
        break missingId;
      }

      id = R.id.textViewHealthStatus;
      TextView textViewHealthStatus = ViewBindings.findChildViewById(rootView, id);
      if (textViewHealthStatus == null) {
        break missingId;
      }

      id = R.id.textViewHighInsights;
      TextView textViewHighInsights = ViewBindings.findChildViewById(rootView, id);
      if (textViewHighInsights == null) {
        break missingId;
      }

      id = R.id.textViewInsightsHeader;
      TextView textViewInsightsHeader = ViewBindings.findChildViewById(rootView, id);
      if (textViewInsightsHeader == null) {
        break missingId;
      }

      id = R.id.textViewMediumInsights;
      TextView textViewMediumInsights = ViewBindings.findChildViewById(rootView, id);
      if (textViewMediumInsights == null) {
        break missingId;
      }

      id = R.id.textViewNoInsights;
      TextView textViewNoInsights = ViewBindings.findChildViewById(rootView, id);
      if (textViewNoInsights == null) {
        break missingId;
      }

      id = R.id.textViewTotalInsights;
      TextView textViewTotalInsights = ViewBindings.findChildViewById(rootView, id);
      if (textViewTotalInsights == null) {
        break missingId;
      }

      return new FragmentAnalyticsBinding((SwipeRefreshLayout) rootView, buttonGenerateInsights,
          cardFinancialHealth, iconCriticalWarning, iconHighWarning, layoutGoalsProgress,
          progressBarGoalsOverall, progressBarHealthScore, recyclerViewInsights, swipeRefreshLayout,
          textViewActiveGoals, textViewAverageProgress, textViewCompletedGoals,
          textViewCriticalInsights, textViewHealthScore, textViewHealthStatus, textViewHighInsights,
          textViewInsightsHeader, textViewMediumInsights, textViewNoInsights,
          textViewTotalInsights);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
