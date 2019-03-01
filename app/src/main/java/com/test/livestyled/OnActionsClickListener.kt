package com.test.livestyled

import android.view.View
import com.test.livestyled.models.Event

//Interface to manage different actions in the RecyclerView
interface OnActionsClickListener<T : Event> {

    /**
     * The move icon (to favorite) has been tapped
     */
    fun onMoveClicked(view: View, item: T)

}
