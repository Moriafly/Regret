@file:Suppress("unused", "UNUSED_PARAMETER")

/*
 * Copyright 2022 Moriafly
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.moriafly.regret

/**
 * Regret
 *
 * @param onDo Returns a key-value pair when {@link #undo()} or {@link #redo()} is called,
 * the key to identify the returned value, the value associated with the key
 * @param onCanDo onCanDo() updates for every call to {@link #undo()}, {@link #redo()} or {@link #add(String, Object, Object)}.
 * This callback is specifically useful for updating the states of undo and redo buttons.
 */
class Regret(
    val onDo: (key: String, value: Any) -> Unit,
    val onCanDo: (canUndo: Boolean, canRedo: Boolean) -> Unit
) {
    private val undoRedoList = UndoRedoList()

    /**
     * Indicates that the user performed an operation type [key], changing from the current state [currentValue] to [newValue].
     *
     * I recommend to always define CurrentValue as the value before the specified Key changes,
     * although not always required, but it is used to represent the initial value of the specified Key operation,
     * and has an important role in the presence of Key Transformations
     *
     * @param key  an identifier for the values
     * @param currentValue the current value
     * @param newValue  the new value
     */
    fun add(key: String, currentValue: Any, newValue: Any) {
        undoRedoList.add(key, currentValue, newValue)
        updateCanDoListener()
    }

    /**
     * @return the current value
     * @throws NoSuchElementException
     */
    fun getCurrent(): Action? {
        return undoRedoList.current
    }

    /**
     * Returns the previous key-value pair via the callback onDo()
     *
     * @throws NoSuchElementException
     */
    fun undo() {
        val action = undoRedoList.undo()
        updateDoListener(action!!)
        updateCanDoListener()
    }

    /**
     * Returns the next key-value pair via the callback onDo()
     *
     * @throws NoSuchElementException
     */
    fun redo() {
        val action = undoRedoList.redo()
        updateDoListener(action!!)
        updateCanDoListener()
    }

    /**
     * @return true if a previous-element exists, else false
     */
    fun canUndo(): Boolean {
        return undoRedoList.canUndo()
    }

    /**
     * @return true if a next-element exists, else false
     */
    fun canRedo(): Boolean {
        return undoRedoList.canRedo()
    }

    /**
     * @return true if the collection is empty else false
     */
    fun isEmpty(): Boolean {
        return undoRedoList.isEmpty
    }

    /**
     * Deletes all elements in the collection
     */
    fun clear() {
        undoRedoList.clear()
        updateCanDoListener()
    }

    /**
     * @return the amount of elements in the list
     */
    fun getSize(): Int {
        return undoRedoList.size
    }

    override fun toString(): String {
        return undoRedoList.toString()
    }

    private fun updateDoListener(action: Action) {
        onDo(action.key, action.value)
    }

    private fun updateCanDoListener() {
        onCanDo(undoRedoList.canUndo(), undoRedoList.canRedo())
    }

    init {
        updateCanDoListener()
    }
}