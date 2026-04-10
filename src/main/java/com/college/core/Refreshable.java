package com.college.core;

/**
 * Panels implementing this interface have their refresh() called
 * by NavigationManager each time they are navigated to.
 */
public interface Refreshable {
    void refresh();
}
