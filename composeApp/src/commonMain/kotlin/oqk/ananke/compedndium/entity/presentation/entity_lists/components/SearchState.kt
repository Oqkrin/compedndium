package oqk.ananke.compedndium.entity.presentation.entity_lists.components

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue

@Stable
class SearchState {
    var isExpanded by mutableStateOf(false)
        private set
    
    var query by mutableStateOf("")
        private set
    
    fun toggle() {
        isExpanded = !isExpanded
        if (!isExpanded) query = ""
    }
    
    fun updateQuery(newQuery: String) {
        query = newQuery
    }
}