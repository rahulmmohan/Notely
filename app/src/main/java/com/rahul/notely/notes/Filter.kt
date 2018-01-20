package com.rahul.notely.notes

import com.rahul.notely.data.Note
import java.util.*

/**
 * Created by rahul on 18/1/18.
 */
class Filter {
    enum class FilterType {
        HEARTED,
        FAVOURITE;
    }

    var appliedFilters = TreeSet<FilterType>()

    fun markFilter(type: FilterType, mark: Boolean) {
        if (mark) {
            appliedFilters.add(type)
        } else {
            appliedFilters.remove(type)
        }
    }

    fun applyFilter(notes: List<Note>) = notes.filter { shouldRetain(it) }


    private fun shouldRetain(it: Note): Boolean {
        var shouldRetain = true
        if (appliedFilters.contains(FilterType.HEARTED)) {
            shouldRetain = shouldRetain.and(it.hearted)
        }
        if (appliedFilters.contains(FilterType.FAVOURITE)) {
            shouldRetain = shouldRetain.and(it.favourite)
        }
        return shouldRetain
    }

}