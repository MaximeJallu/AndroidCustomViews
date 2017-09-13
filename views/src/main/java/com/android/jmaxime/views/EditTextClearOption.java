package com.android.jmaxime.views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.AppCompatEditText;
import android.text.TextUtils;
import android.util.AttributeSet;

import com.android.jmaxime.views.adapters.DrawableClickListener;

/**
 * @author Maxime Jallu
 * @since 13/09/2017
 * <p>
 * Use this Class for : <br/>
 * {DOCUMENTATION}
 */
public class EditTextClearOption extends AppCompatEditText {
    public EditTextClearOption(Context context) {
        super(context);
    }

    public EditTextClearOption(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EditTextClearOption(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        if (!isInEditMode()) {
            Drawable d = null;
            if (text.length() > 0) {
                d = DrawableCompat.wrap(ContextCompat.getDrawable(getContext(), R.drawable.ic_close_black_24dp));
                initListenerDrawableClick();
            }
            this.setCompoundDrawablesWithIntrinsicBounds(
                    getDrawablePosition(0),
                    getDrawablePosition(1),
                    d,
                    getDrawablePosition(3));
        }
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        if (!enabled) {
            this.setCompoundDrawablesWithIntrinsicBounds(
                    getDrawablePosition(0),
                    getDrawablePosition(1),
                    null,
                    getDrawablePosition(3));
        }
    }

    private Drawable getDrawablePosition(int position) {
        return getCompoundDrawables()[position];
    }

    private void initListenerDrawableClick() {
        setOnTouchListener(new DrawableClickListener.RightDrawableClickListener(this) {
            @Override
            public boolean onDrawableClick() {
                setText("");
                return true;
            }
        });
    }

    /**
     * Optient une valeur boolean qui détermine si la valeur actuelle est vide
     *
     * @return true si charsequence il Empty or Null
     */
    public boolean isEmpty() {
        return TextUtils.isEmpty(getText().toString());
    }

    /**
     * Returns a string with the same characters in the same order as in this sequence.
     *
     * @return a string based on this sequence.
     */
    public String getTextValue() {
        return getText().toString();
    }

    /***
     * Optient la valeur courante du textView
     *
     * @return a string based on this sequence. or Null if Empty value
     */
    @Nullable
    public String getTextValueOrNull() {
        if (isEmpty()) {
            return null;
        } else {
            return getTextValue();
        }
    }
}
