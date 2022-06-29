# Regret

[![](https://jitpack.io/v/Moriafly/Regret.svg)](https://jitpack.io/#Moriafly/Regret)

I've been developing on an editor for my Android App recently, using Jetpack Compose, but Google doesn't implement a built-in undo / redo for developers.
Nice to come across this library [Regret](https://github.com/Muddz/Regret), it's awesome. But I have to change something to achieve what I need.
Then I used Kotlin to rewrite the part and added the ability to limit the length of the linked list.

**Regret** is an Android library for apps that wants to implement an undo/redo feature.
The library is simple to use and works with all primitive types and objects and is especially suitable for drawing, text and photo editing apps.

# Usage

#### Add it in your root build.gradle at the end of repositories:

```groovy
allprojects {
    repositories {
        // ...
        maven { url 'https://jitpack.io' }
    }
}
```

#### Add the dependency

```groovy
dependencies {
    implementation 'com.github.Moriafly:Regret:Tag'
}
```
#### Create Regret

```kotlin
val regret = Regret(
    onDo = { key: String, value: Any ->
        
    },
    onCanDo = { canUndo: Boolean, canRedo: Boolean ->
        
    }
)
```

#### Add

Indicates that the user performed an operation type (key), changing from the current state (CurrentValue) to (NewValue).

```kotlin
regret.add(YOUR_KEY, CurrentValue, NewValue)
```

#### Undo / Redo

```kotlin
if (regret.canUndo()) {
    regret.undo()
}

if (regret.reUndo()) {
    regret.redo()
}
```

# TODO

- [ ] Limit the length of the linked list

# License

```
Copyright 2022 Moriafly

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    https://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```