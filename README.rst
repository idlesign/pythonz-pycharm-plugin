pythonz.net in PyCharm
======================
https://github.com/idlesign/pythonz-pycharm-plugin


Description
-----------

*Plugin for PyCharm IDEs featuring code hints from pythonz.net*

* Install the plugin and restart IDE;
* Select text you want to get hint for or set a caret on it;
* Press ``AltGr + P`` to trigger QR code popup
  (you may bind a custom keystroke for "Show pythonz.net Code Hints" action in ``Keymap``)


Requirements
------------

* PyCharm 2020.1 + JBRuntime with JCEF


Installation
------------

This plugin uses JCEF (Java Chromium Embedded Framework) which is available
since 2020.1 as an `experimental feature<https://www.jetbrains.org/intellij/sdk/docs/reference_guide/jcef.html>`_.

So for now in order to use this plugin one needs to:

1. Enable JCEF: "Help -> Find Action -> Registry -> ide.browser.jcef.enabled
2. Download for your platform and active latest JCEF enabled Runtime SDK (jbr_jcef) using "Choose Runtime" plugin
  as described `here<https://youtrack.jetbrains.com/issue/IDEA-231833#focus=streamItem-27-3993099.0-0>`_.
3. Done.
