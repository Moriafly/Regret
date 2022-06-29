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

import org.junit.Test

class RegretUnitTest {

    @Test
    fun regretTest() {
        // My Text Editor String
        var myText = ""
        // Color 1 - Red, Color 2 - Green, Color 3 - Blue
        var color = 1

        val regret = Regret(
            onDo = { key, value ->
                println("do $key $value")
            },
            onCanDo = { canUndo, canRedo ->
                println("can undo: $canUndo, can redo: $canRedo")
            }
        )

        // Add some text
        myText += "Hello"
        regret.add(KEY_TEXT, "", myText)

        myText += " World"
        regret.add(KEY_TEXT, "Hello", myText)

        color = 2
        regret.add(KEY_COLOR, 1, color)

        myText += "!"
        regret.add(KEY_TEXT, "Hello World", myText)

        // Now we can undo twice
        regret.undo()
        println(regret.toString())

        regret.undo()
        println(regret.toString())

        regret.undo()
        println(regret.toString())

        regret.undo()
        println(regret.toString())
    }

    companion object {
        private const val KEY_TEXT = "key_text"
        private const val KEY_COLOR = "key_color"
    }

}