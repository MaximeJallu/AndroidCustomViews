package com.android.jmaxime.views.adapters;

import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public abstract class DrawableClickListener implements View.OnTouchListener {

    /* PUBLIC CONSTANTS */
    /**
     * This represents the left drawable.
     */
    private static final int DRAWABLE_INDEX_LEFT = 0;
    /**
     * This represents the top drawable.
     */
    private static final int DRAWABLE_INDEX_TOP = 1;
    /**
     * This represents the right drawable.
     */
    private static final int DRAWABLE_INDEX_RIGHT = 2;
    /**
     * This represents the bottom drawable.
     */
    private static final int DRAWABLE_INDEX_BOTTOM = 3;
    /**
     * This stores the default value to be used for the
     * {@link DrawableClickListener#fuzz}.
     */
    private static final int DEFAULT_FUZZ = 10;

    /* PRIVATE VARIABLES */
    /**
     * This stores the number of pixels of &quot;fuzz&quot; that should be
     * included to account for the size of a finger.
     */
    private final int fuzz;
    /**
     * This will store a reference to the {@link Drawable}.
     */
    private Drawable drawable = null;

    /* CONSTRUCTORS */

    /**
     * This will create a new instance of a {@link DrawableClickListener}
     * object.
     *
     * @param view          The {@link TextView} that this {@link DrawableClickListener}
     *                      is associated with.
     * @param drawableIndex The index of the drawable that this
     *                      {@link DrawableClickListener} pertains to.
     *                      <br />
     *                      <i>use one of the values:
     *                      <b>DrawableOnTouchListener.DRAWABLE_INDEX_*</b></i>
     */
    public DrawableClickListener(final TextView view, final int drawableIndex) {
        this(view, drawableIndex, DrawableClickListener.DEFAULT_FUZZ);
    }

    /**
     * This will create a new instance of a {@link DrawableClickListener}
     * object.
     *
     * @param view          The {@link TextView} that this {@link DrawableClickListener}
     *                      is associated with.
     * @param drawableIndex The index of the drawable that this
     *                      {@link DrawableClickListener} pertains to.
     *                      <br />
     *                      <i>use one of the values:
     *                      <b>DrawableOnTouchListener.DRAWABLE_INDEX_*</b></i>
     * @param fuzz          Override
     *                      The number of pixels of &quot;fuzz&quot; that should be
     *                      included to account for the size of a finger.
     */
    public DrawableClickListener(final TextView view, final int drawableIndex, final int fuzz) {
        super();
        this.fuzz = fuzz;
        final Drawable[] drawables = view.getCompoundDrawables();
        if (drawables.length == 4) {
            this.drawable = drawables[drawableIndex];
        }
    }

    /* OVERRIDDEN PUBLIC METHODS */
    @Override
    public boolean onTouch(final View v, final MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN && drawable != null) {
            final int x = (int) event.getX();
            final int y = (int) event.getY();
            final Rect bounds = drawable.getBounds();
            if (this.isClickOnDrawable(x, y, v, bounds, this.fuzz)) {
                return this.onDrawableClick();
            }
        }
        return false;
    }

    /* PUBLIC METHODS */

    /**
     *
     * */
    protected abstract boolean isClickOnDrawable(final int x, final int y, final View view, final Rect drawableBounds, final int fuzz);

    /**
     * This method will be fired when the drawable is touched/clicked.
     *
     * @return <code>true</code> if the mTextChangedListener has consumed the event;
     * <code>false</code> otherwise.
     */
    public abstract boolean onDrawableClick();

    /* PUBLIC CLASSES */

    /**
     * This class can be used to define a mTextChangedListener for a <b>LEFT</b> compound
     * drawable.
     */
    public static abstract class LeftDrawableClickListener extends DrawableClickListener {

        /* CONSTRUCTORS */

        /**
         * This will create a new instance of a
         * {@link LeftDrawableClickListener} object.
         *
         * @param view The {@link TextView} that this
         *             {@link LeftDrawableClickListener} is associated with.
         */
        public LeftDrawableClickListener(final TextView view) {
            super(view, DrawableClickListener.DRAWABLE_INDEX_LEFT);
        }

        /**
         * This will create a new instance of a
         * {@link LeftDrawableClickListener} object.
         *
         * @param view The {@link TextView} that this
         *             {@link LeftDrawableClickListener} is associated with.
         * @param fuzz Override
         *             The number of pixels of &quot;fuzz&quot; that should be
         *             included to account for the size of a finger.
         */
        public LeftDrawableClickListener(final TextView view, final int fuzz) {
            super(view, DrawableClickListener.DRAWABLE_INDEX_LEFT, fuzz);
        }

        /* PUBLIC METHODS */
        public boolean isClickOnDrawable(final int x, final int y, final View view, final Rect drawableBounds, final int fuzz) {
            return x >= (view.getPaddingLeft() - fuzz)
                    && x <= (view.getPaddingLeft() + drawableBounds.width() + fuzz)
                    && y >= (view.getPaddingTop() - fuzz)
                    && y <= (view.getHeight() - view.getPaddingBottom() + fuzz);
        }

    }

    /**
     * This class can be used to define a mTextChangedListener for a <b>TOP</b> compound
     * drawable.
     */
    public static abstract class TopDrawableClickListener extends DrawableClickListener {

        /* CONSTRUCTORS */

        /**
         * This will create a new instance of a {@link TopDrawableClickListener}
         * object.
         *
         * @param view The {@link TextView} that this
         *             {@link TopDrawableClickListener} is associated with.
         */
        public TopDrawableClickListener(final TextView view) {
            super(view, DrawableClickListener.DRAWABLE_INDEX_TOP);
        }

        /**
         * This will create a new instance of a {@link TopDrawableClickListener}
         * object.
         *
         * @param view The {@link TextView} that this
         *             {@link TopDrawableClickListener} is associated with.
         * @param fuzz Override
         *             The number of pixels of &quot;fuzz&quot; that should be
         *             included to account for the size of a finger.
         */
        public TopDrawableClickListener(final TextView view, final int fuzz) {
            super(view, DrawableClickListener.DRAWABLE_INDEX_TOP, fuzz);
        }

        /* PUBLIC METHODS */
        public boolean isClickOnDrawable(final int x, final int y, final View view, final Rect drawableBounds, final int fuzz) {
            return x >= (view.getPaddingLeft() - fuzz)
                    && x <= (view.getWidth() - view.getPaddingRight() + fuzz)
                    && y >= (view.getPaddingTop() - fuzz)
                    && y <= (view.getPaddingTop() + drawableBounds.height() + fuzz);
        }
    }

    /**
     * This class can be used to define a mTextChangedListener for a <b>RIGHT</b> compound
     * drawable.
     */
    public static abstract class RightDrawableClickListener extends DrawableClickListener {

        /* CONSTRUCTORS */

        /**
         * This will create a new instance of a
         * {@link RightDrawableClickListener} object.
         *
         * @param view The {@link TextView} that this
         *             {@link RightDrawableClickListener} is associated with.
         */
        public RightDrawableClickListener(final TextView view) {
            super(view, DrawableClickListener.DRAWABLE_INDEX_RIGHT);
        }

        /**
         * This will create a new instance of a
         * {@link RightDrawableClickListener} object.
         *
         * @param view The {@link TextView} that this
         *             {@link RightDrawableClickListener} is associated with.
         * @param fuzz Override
         *             The number of pixels of &quot;fuzz&quot; that should be
         *             included to account for the size of a finger.
         */
        public RightDrawableClickListener(final TextView view, final int fuzz) {
            super(view, DrawableClickListener.DRAWABLE_INDEX_RIGHT, fuzz);
        }

        /* PUBLIC METHODS */
        public boolean isClickOnDrawable(final int x, final int y, final View view, final Rect drawableBounds, final int fuzz) {
            return x >= (view.getWidth() - view.getPaddingRight() - drawableBounds.width() - fuzz)
                    && x <= (view.getWidth() - view.getPaddingRight() + fuzz)
                    && y >= (view.getPaddingTop() - fuzz)
                    && y <= (view.getHeight() - view.getPaddingBottom() + fuzz);
        }

    }

    /**
     * This class can be used to define a mTextChangedListener for a <b>BOTTOM</b> compound
     * drawable.
     */
    public static abstract class BottomDrawableClickListener extends DrawableClickListener {

        /* CONSTRUCTORS */

        /**
         * This will create a new instance of a
         * {@link BottomDrawableClickListener} object.
         *
         * @param view The {@link TextView} that this
         *             {@link BottomDrawableClickListener} is associated with.
         */
        public BottomDrawableClickListener(final TextView view) {
            super(view, DrawableClickListener.DRAWABLE_INDEX_BOTTOM);
        }

        /**
         * This will create a new instance of a
         * {@link BottomDrawableClickListener} object.
         *
         * @param view The {@link TextView} that this
         *             {@link BottomDrawableClickListener} is associated with.
         * @param fuzz Override
         *             The number of pixels of &quot;fuzz&quot; that should be
         *             included to account for the size of a finger.
         */
        public BottomDrawableClickListener(final TextView view, final int fuzz) {
            super(view, DrawableClickListener.DRAWABLE_INDEX_BOTTOM, fuzz);
        }

        /* PUBLIC METHODS */
        public boolean isClickOnDrawable(final int x, final int y, final View view, final Rect drawableBounds, final int fuzz) {
            return x >= (view.getPaddingLeft() - fuzz)
                    && x <= (view.getWidth() - view.getPaddingRight() + fuzz)
                    && y >= (view.getHeight() - view.getPaddingBottom() - drawableBounds.height() - fuzz)
                    && y <= (view.getHeight() - view.getPaddingBottom() + fuzz);
        }

    }

}
