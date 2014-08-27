/*
 * Copyright (C) 2014 Murray Cumming
 *
 * This file is part of android-galaxyzoo.
 *
 * android-galaxyzoo is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * android-galaxyzoo is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with android-galaxyzoo.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.murrayc.galaxyzoo.app;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;

/**
 * Created by murrayc on 2/7/14.
 */
@SuppressLint("Registered") //This is a base class for other Activities.
public class BaseActivity extends Activity {

    //TODO: Avoid duplcation with the ARGs in the fragments:
    protected static final String ARG_USER_ID = "user-id";
    private String mUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //This lets us know what MIME Type to mention in the intent filter in the manifest file,
        //as long as we cannot register a more specific MIME type.
        //String type = intent.getType();
        //Log.v("glomdebug", "type=" + type);
    }

    /**
     * Navigate to the item,
     *
     */
    protected void navigate(final String itemId) {
        //Start the detail activity
        // for the selected item ID.
        // TODO: Just view it with DetailActivity if it has already been classified.
        final Intent intent = new Intent(this, ClassifyActivity.class);
        intent.putExtra(ARG_USER_ID, getUserId());

        if (!TextUtils.isEmpty(itemId)) {
            intent.putExtra(ItemFragment.ARG_ITEM_ID, itemId);
        }

        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        final int id = item.getItemId();

        if (id == android.R.id.home) {
            //Derived Activities should handle this.

            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. Use NavUtils to allow users
            // to navigate up one level in the application structure. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            navigateUpTo(new Intent(this, ListActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public String getUserId() {
        return mUserId;
    }

    public void setUserId(final String userId) {
        mUserId = userId;
    }
}
