# (Half) Smart Align

A IntelliJ plugin to provide better vertical alignment.  
Align your code by assignment(`=`, `+=`, `-=`, `*=`, `/=`), colon(`:`) and comma(`,`). also support trailing comment.

This has been overhauled from [this repo](https://github.com/ttyniwa/intellij-smart-align) to simplify the algorithm
and make its behavior closer to [this plugin for VS](https://github.com/cpmcgrath/codealignment).
Essentially it is now less smart and provides the ability to
align by a custom string inputted by the user in a popup.

## Compatibility

IntelliJ IDEA, Android Studio. (and maybe other JetBrains families.)

## How to use

Select the lines of code to be aligned, and click on the action
buttons in the nav toolbar. This should be near the run actions
at the top right of the IDE (on IntelliJ).

There is a button for aligning by equals, otherwise another
button to open a prompt for you to enter a custom delimiter
for the alignment (ex. aligning by `(` or `:` or `return`, etc.).

## Contribute

* `gradle test`
* `gradle runIde`
* `gradle buildPlugin`

## Acknowledgements

Intellij-smart-align is heavily inspired by [Better Align for Visual Studio Code](https://marketplace.visualstudio.com/items?itemName=wwm.better-align)

and this overhaul was heavily inspired by heavy usage of [Code alignment in Visual Studio](https://github.com/cpmcgrath/codealignment)
