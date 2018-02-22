//package interview.heruijun.com.androidinterview.view.bottomnavigation;
//
//import android.content.Context;
//import android.graphics.Typeface;
//import android.graphics.drawable.ColorDrawable;
//import android.os.Bundle;
//import android.os.Parcel;
//import android.os.Parcelable;
//import android.support.design.widget.CoordinatorLayout;
//import android.util.AttributeSet;
//import android.util.Log;
//import android.view.View;
//
//import java.lang.ref.SoftReference;
//
//import it.sephiroth.android.library.bottomnavigation.BadgeProvider;
//import it.sephiroth.android.library.bottomnavigation.BottomNavigation;
//import it.sephiroth.android.library.bottomnavigation.ItemsLayoutContainer;
//
//import static android.util.Log.INFO;
//import static it.sephiroth.android.library.bottomnavigation.MiscUtils.log;
//
///**
// * Created by heruijun on 2018/2/18.
// */
//
//public class MyBottomNavigation {
//
//    private static final String TAG = MyBottomNavigation.class.getSimpleName();
//
//    @SuppressWarnings ("checkstyle:staticvariablename")
//    public static boolean DEBUG = false;
//
//    static final int PENDING_ACTION_NONE = 0x0;
//    static final int PENDING_ACTION_EXPANDED = 0x1;
//    static final int PENDING_ACTION_COLLAPSED = 0x2;
//    static final int PENDING_ACTION_ANIMATE_ENABLED = 0x4;
//
//    private static final String WIDGET_PACKAGE_NAME;
//
//    static {
//        final Package pkg = BottomNavigation.class.getPackage();
//        WIDGET_PACKAGE_NAME = pkg != null ? pkg.getName() : null;
//    }
//
//    static final Class<?>[] CONSTRUCTOR_PARAMS = new Class<?>[]{BottomNavigation.class};
//
//    /**
//     * Current pending action (used inside the BottomBehavior instance)
//     */
//    private int mPendingAction = PENDING_ACTION_NONE;
//
//    /**
//     * This is the amount of space we have to cover in case there's a translucent navigation
//     * enabled.
//     */
//    private int bottomInset;
//
//    /**
//     * This is the amount of space we have to cover in case there's a translucent status
//     * enabled.
//     */
//    private int topInset;
//
//    /**
//     * This is the current view height. It does take into account the extra space
//     * used in case we have to cover the navigation translucent area, and neither the shadow height.
//     */
//    private int defaultHeight;
//
//    /**
//     * Same as defaultHeight, but for tablet mode.
//     */
//    private int defaultWidth;
//
//    /**
//     * Shadow is created above the widget background. It simulates the
//     * elevation.
//     */
//    private int shadowHeight;
//
//    /**
//     * Layout container used to create and manage the UI items.
//     * It can be either Fixed or Shifting, based on the widget `mode`
//     */
//    private ItemsLayoutContainer itemsContainer;
//
//    /**
//     * This is where the color animation is happening
//     */
//    private View backgroundOverlay;
//
//    /**
//     * View used to show the press ripple overlay. I don't use the drawable in item view itself
//     * because the ripple background will be clipped inside its bounds
//     */
//    private View rippleOverlay;
//
//    /**
//     * Toggle the ripple background animation on item press
//     */
//    private boolean enabledRippleBackground;
//
//    /**
//     * current menu
//     */
//    MyMenuParser.Menu menu;
//
//    private MyMenuParser.Menu pendingMenu;
//
//    /**
//     * Default selected index.
//     * After the items are populated changing this
//     * won't have any effect
//     */
//    private int defaultSelectedIndex = 0;
//
//    /**
//     * View visible background color
//     */
//    private ColorDrawable backgroundDrawable;
//
//    /**
//     * Animation duration for the background color change
//     */
//    private long backgroundColorAnimation;
//
//    /**
//     * Optional typeface used for the items' text labels
//     */
//    SoftReference<Typeface> typeface;
//
//    /**
//     * Current BottomBehavior assigned from the CoordinatorLayout
//     */
//    private CoordinatorLayout.Behavior mBehavior;
//
//    /**
//     * Menu selection listener
//     */
//    private OnMenuItemSelectionListener listener;
//
//    /**
//     * Menu changed listener
//     */
//    private OnMenuChangedListener menuChangedListener;
//
//    /**
//     * The user defined layout_gravity
//     */
//    private int gravity;
//
//    /**
//     * View is attached
//     */
//    private boolean attached;
//
//    private BadgeProvider badgeProvider;
//
//
//
//
//    public MyBottomNavigation(Context context) {
//        super(context);
//    }
//
//    public MyBottomNavigation(Context context, AttributeSet attrs) {
//        super(context, attrs);
//    }
//
//    public MyBottomNavigation(Context context, AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//    }
//
//    @Override
//    protected Parcelable onSaveInstanceState() {
//        log(TAG, INFO, "onSaveInstanceState");
//        Parcelable parcelable = super.onSaveInstanceState();
//        SavedState savedState = new SavedState(parcelable);
//
//        if (null == menu) {
//            savedState.selectedIndex = 0;
//        } else {
//            // savedState.selectedIndex = Math.max(0, Math.min(getSelectedIndex(), menu.getItemsCount() - 1));
//            savedState.selectedIndex = getSelectedIndex();
//        }
//
//        if (null != badgeProvider) {
//            savedState.badgeBundle = badgeProvider.save();
//        }
//
//        return savedState;
//    }
//
//    @Override
//    protected void onRestoreInstanceState(final Parcelable state) {
//        log(TAG, INFO, "onRestoreInstanceState");
//        SavedState savedState = (SavedState) state;
//        super.onRestoreInstanceState(savedState.getSuperState());
//
//        defaultSelectedIndex = savedState.selectedIndex;
//        log(TAG, Log.DEBUG, "defaultSelectedIndex: %d", defaultSelectedIndex);
//
//        if (null != badgeProvider && null != savedState.badgeBundle) {
//            badgeProvider.restore(savedState.badgeBundle);
//        }
//    }
//
//
//    @Override
//    public void onItemClick(ItemsLayoutContainer parent, View view, int index, boolean animate) {
//        super.onItemClick(parent, view, index, animate);
//    }
//
//    @Override
//    protected void onAttachedToWindow() {
//        super.onAttachedToWindow();
//
//    }
//
//
//    static class SavedState extends BaseSavedState {
//        int selectedIndex;
//        Bundle badgeBundle;
//
//        public SavedState(Parcel in) {
//            super(in);
//            selectedIndex = in.readInt();
//            badgeBundle = in.readBundle();
//        }
//
//        public SavedState(final Parcelable superState) {
//            super(superState);
//        }
//
//        @Override
//        public void writeToParcel(final Parcel out, final int flags) {
//            super.writeToParcel(out, flags);
//            out.writeInt(selectedIndex);
//            out.writeBundle(badgeBundle);
//        }
//
//        @Override
//        public int describeContents() {
//            return super.describeContents();
//        }
//
//        public static final Parcelable.Creator<SavedState> CREATOR
//                = new Parcelable.Creator<SavedState>() {
//            public SavedState createFromParcel(Parcel in) {
//                return new SavedState(in);
//            }
//
//            public SavedState[] newArray(int size) {
//                return new SavedState[size];
//            }
//        };
//    }
//}
