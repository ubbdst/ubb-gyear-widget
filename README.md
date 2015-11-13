UBBgYearWidget
====
This is another extension for Protege 3.5 as a slot widget plugin. The plugin aims to validate Gegorian Year (gYear) as adhered to the Java XMLGegorianCalendar. We had to develop this plugin because the native Protege plugin that offers a functionality, <code> "SingleLiteralWidget" </code> does not seem to validate <code>gYear</code> input. 

### How to install the plugin

- Download the plugin from https://github.com/ubbdst/ubb-gyear-widget/releases. You would need <code> Java JDK6</code> or newer to run this plugin.

- Extract the zip file to the <code>plugins</code> folder in your Protege installation directory. If the <code>plugins</code> folder does not exist (which is unlikely), you need to create it.

- If you are running a client-server mode, you must install the plugin both to the server and all of your clients. 

