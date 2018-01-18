package com.rahul.notely.notes

/**
 * Created by rahul on 18/1/18.
 */
class Filter {
    enum class FilterType {
        HEARTED,
        FAVOURITE;
    }
    var appliedFilters = ArrayList<FilterType>()

    fun applyFilter(type: FilterType, apply: Boolean) {
        if (apply) {
            appliedFilters.add(type)
        } else {
            appliedFilters.remove(type)
        }
    }
}