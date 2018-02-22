//package interview.heruijun.com.androidinterview.view.bottomnavigation;
//
///**
// * Created by heruijun on 2018/2/19.
// */
//
////
//// Source code recreated from a .class file by IntelliJ IDEA
//// (powered by Fernflower decompiler)
////
//
//import android.animation.Animator;
//import android.animation.Animator.AnimatorListener;
//import android.annotation.SuppressLint;
//import android.annotation.TargetApi;
//import android.app.Activity;
//import android.content.Context;
//import android.content.ContextWrapper;
//import android.content.res.ColorStateList;
//import android.graphics.drawable.ColorDrawable;
//import android.graphics.drawable.Drawable;
//import android.graphics.drawable.RippleDrawable;
//import android.os.Build.VERSION;
//import android.support.annotation.AttrRes;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.support.v4.graphics.drawable.DrawableCompat;
//import android.support.v4.view.ViewCompat;
//import android.support.v4.view.ViewPropertyAnimatorCompat;
//import android.support.v4.view.ViewPropertyAnimatorListener;
//import android.util.Log;
//import android.util.TypedValue;
//import android.view.View;
//import android.view.ViewAnimationUtils;
//import android.view.animation.DecelerateInterpolator;
//
//import it.sephiroth.android.library.bottomnavigation.BottomNavigation;
//
//public final class MiscUtils {
//    private MiscUtils() {
//    }
//
//    public static int getDimensionPixelSize(Context context, int dp) {
//        return (int)(context.getResources().getDisplayMetrics().density * (float)dp);
//    }
//
//    @TargetApi(19)
//    public static boolean hasTranslucentStatusBar(@Nullable Activity activity) {
//        return null == activity?false:(VERSION.SDK_INT >= 19?(activity.getWindow().getAttributes().flags & 67108864) == 67108864:false);
//    }
//
//    @TargetApi(19)
//    public static boolean hasTranslucentNavigation(@Nullable Activity activity) {
//        return null == activity?false:(VERSION.SDK_INT >= 19?(activity.getWindow().getAttributes().flags & 134217728) == 134217728:false);
//    }
//
//    protected static int getColor(Context context, @AttrRes int color) {
//        TypedValue tv = new TypedValue();
//        context.getTheme().resolveAttribute(color, tv, true);
//        return tv.data;
//    }
//
//    @TargetApi(21)
//    protected static void setDrawableColor(@NonNull Drawable drawable, int color) {
//        if(VERSION.SDK_INT >= 21) {
//            if(RippleDrawable.class.isInstance(drawable)) {
//                RippleDrawable rippleDrawable = (RippleDrawable)drawable;
//                rippleDrawable.setColor(ColorStateList.valueOf(color));
//            }
//        } else {
//            DrawableCompat.setTint(drawable, color);
//        }
//
//    }
//
//    @SuppressLint({"RtlHardcoded"})
//    static boolean isGravitiyLeft(int gravity) {
//        return gravity == 3;
//    }
//
//    @SuppressLint({"RtlHardcoded"})
//    static boolean isGravityRight(int gravity) {
//        return gravity == 5;
//    }
//
//    static boolean isGravityBottom(int gravity) {
//        return gravity == 80;
//    }
//
//    protected static void switchColor(BottomNavigation navigation, View v, View backgroundOverlay, ColorDrawable backgroundDrawable, int newColor) {
//        backgroundOverlay.clearAnimation();
//        if(VERSION.SDK_INT >= 21) {
//            Animator currentAnimator = (Animator)backgroundOverlay.getTag(it.sephiroth.android.library.bottonnavigation.R.id.bbn_backgroundOverlay_animator);
//            if(null != currentAnimator) {
//                currentAnimator.cancel();
//            }
//        }
//
//        backgroundDrawable.setColor(newColor);
//        backgroundOverlay.setVisibility(4);
//        ViewCompat.setAlpha(backgroundOverlay, 1.0F);
//    }
//
//    protected static void animate(BottomNavigation navigation, View v, final View backgroundOverlay, final ColorDrawable backgroundDrawable, final int newColor, long duration) {
//        int centerX = (int)(ViewCompat.getX(v) + (float)(v.getWidth() / 2));
//        int centerY = navigation.getPaddingTop() + v.getHeight() / 2;
//        backgroundOverlay.clearAnimation();
//        Object animator;
//        Animator animator1;
//        if(VERSION.SDK_INT >= 21) {
//            animator1 = (Animator)backgroundOverlay.getTag(it.sephiroth.android.library.bottonnavigation.R.id.bbn_backgroundOverlay_animator);
//            if(null != animator1) {
//                animator1.cancel();
//            }
//
//            float startRadius = 10.0F;
//            float finalRadius = centerX > navigation.getWidth() / 2?(float)centerX:(float)(navigation.getWidth() - centerX);
//            animator = ViewAnimationUtils.createCircularReveal(backgroundOverlay, centerX, centerY, 10.0F, finalRadius);
//            backgroundOverlay.setTag(it.sephiroth.android.library.bottonnavigation.R.id.bbn_backgroundOverlay_animator, animator);
//        } else {
//            ViewCompat.setAlpha(backgroundOverlay, 0.0F);
//            animator = ViewCompat.animate(backgroundOverlay).alpha(1.0F);
//        }
//
//        backgroundOverlay.setBackgroundColor(newColor);
//        backgroundOverlay.setVisibility(0);
//        if(ViewPropertyAnimatorCompat.class.isInstance(animator)) {
//            ((ViewPropertyAnimatorCompat)animator).setListener(new ViewPropertyAnimatorListener() {
//                boolean cancelled;
//
//                public void onAnimationStart(View view) {
//                }
//
//                public void onAnimationEnd(View view) {
//                    if(!this.cancelled) {
//                        backgroundDrawable.setColor(newColor);
//                        backgroundOverlay.setVisibility(4);
//                        ViewCompat.setAlpha(backgroundOverlay, 1.0F);
//                    }
//
//                }
//
//                public void onAnimationCancel(View view) {
//                    this.cancelled = true;
//                }
//            }).setDuration(duration).start();
//        } else {
//            animator1 = (Animator)animator;
//            animator1.setDuration(duration);
//            animator1.setInterpolator(new DecelerateInterpolator());
//            animator1.addListener(new AnimatorListener() {
//                boolean cancelled;
//
//                public void onAnimationStart(Animator animation) {
//                }
//
//                public void onAnimationEnd(Animator animation) {
//                    if(!this.cancelled) {
//                        backgroundDrawable.setColor(newColor);
//                        backgroundOverlay.setVisibility(4);
//                        ViewCompat.setAlpha(backgroundOverlay, 1.0F);
//                    }
//
//                }
//
//                public void onAnimationCancel(Animator animation) {
//                    this.cancelled = true;
//                }
//
//                public void onAnimationRepeat(Animator animation) {
//                }
//            });
//            animator1.start();
//        }
//
//    }
//
//    public static void log(String tag, int level, String message, Object... arguments) {
//        if(BottomNavigation.DEBUG) {
//            Log.println(level, tag, String.format(message, arguments));
//        }
//
//    }
//
//    @Nullable
//    static Activity getActivity(@Nullable Context context) {
//        return context == null?null:(context instanceof Activity?(Activity)context:(context instanceof ContextWrapper?getActivity(((ContextWrapper)context).getBaseContext()):null));
//    }
//}
