# (Half) Smart Align

An IntelliJ plugin to provide better vertical alignment.

This has been overhauled from [this repo](https://github.com/ttyniwa/intellij-smart-align) to simplify the algorithm
and make its behavior closer to [this plugin for VS](https://github.com/cpmcgrath/codealignment).
Essentially it is now less smart and provides the ability to
align by a custom string inputted by the user in a popup.

## Compatibility

IntelliJ IDEA, Android Studio. (and maybe other JetBrains families.)

## How to install

Go to the [releases page](https://github.com/clovenski/intellij-smart-align/releases)
and download the latest version of the plugin. Then, install the plugin
by dragging the zip file onto Intellij.

If dragging and dropping doesn't work, you can also go to the
plugins settings in Intellij and click the gear icon to install
a plugin from the disk - selecting the zip file you downloaded.

## How to use

Select the lines of code to be aligned, and click on the action
buttons in the nav toolbar. This should be near the run actions
at the top right of the IDE (on IntelliJ).

There is a button for aligning by equals, period, braces or brackets.
Otherwise, there are other buttons to open a prompt for you to enter
a custom delimiter for the alignment (ex. aligning by `(` or `:` or `,` or `return`, etc.).

To quickly align by equals, highlight the lines to align, and then use the shortcut
`CTRL+ALT+SHIFT+EQUALS`.

To quickly open the prompt to align by a custom string, highlight the lines to align,
and then use the shortcut `ALT+EQUALS`, and to default the prompt to align from the
caret position, use `ALT+SHIFT+EQUALS`.

## Contribute

* `gradle test` to run tests
* `gradle runIde` to run a new IDE instance and test out the plugin
* `gradle buildPlugin` to build the plugin distribution

## Acknowledgements

Intellij-smart-align is heavily inspired by [Better Align for Visual Studio Code](https://marketplace.visualstudio.com/items?itemName=wwm.better-align)

and this overhaul was heavily inspired by heavy usage of [Code alignment in Visual Studio](https://github.com/cpmcgrath/codealignment)
