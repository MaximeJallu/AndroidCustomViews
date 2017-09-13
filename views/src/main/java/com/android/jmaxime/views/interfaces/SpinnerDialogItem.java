package com.android.jmaxime.views.interfaces;

import android.os.Parcelable;

/**
 * Created by Maxime on 31/08/2016.
 */
public interface SpinnerDialogItem extends Parcelable {

    String getTitle();

    String getCode();

    String getId();
}
