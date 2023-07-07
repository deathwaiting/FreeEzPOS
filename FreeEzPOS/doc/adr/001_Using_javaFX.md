# Using javaFX
 
* Status: accepted
* Date: 2023-07-05

# Context

The Target of this application is to be a user friendly opensource POS application for business owners who cannot afford a complete solution.
This means we have the following criteria:
- It should be lightweight, and can run on your decade old computer, and can run on windows, linux and MacOs.
  - Ideally it should run even on Single-board computers like rasberry-pi, or RISC-V architecture. to open the door for cheap POS work-stations.
- It require no technical experience to install, just double click its icon, it will do the rest.
- It should have a modern UI, but with long term support, as a POS system can be used for long time.

These requirements leaded to the following:
- being light weight meant we should exclude resource intensive solutions.
  - Using a web-stack means we need a browser, which is not always lightweight, and usually an overkill.
  - Using solutions like [Electorn](https://www.electronjs.org/) is also more resource intensive, as it uses a web-engine under the hood anyway.
- Being Cross-platform
  - This excludes .Net, as it is targeted mainly for windows, installing .net core on linux seems possible but not easy. diffinetly not for a simple user.
  - Python should be ok, but I am not familiar with it. Last time i used for desktop applications it wasn't great.
- Being offline
  - POS systems may not be connected to the internet, and it may also access to hardware.
  - So, less reason to use a web-stack
- Internationlization support
  - The application MUST support multiple languages
- This left me with [JavaFX](https://openjfx.io/):
  - Modern framework, have modern components
  - By using it + Java module system, it is possible to create a cross platform standalone applications
  - Expected to be lightweight if we used light frameworks or just vanilla java.
  - so, we give it a try first, see how the application will perform and how much resources it will take.
  - But still it is expected to be lighter than an Electron app.
  - It doesn't have a huge eco-system, but it is mature.

## Tech-stack:
- We will probably start with java17, then move to java-21 when it is released in Sep 2023.
- will try using [testFx](https://github.com/TestFX/TestFX) for e2e testing
  - it supports running headless tests on CI pipelines, using [monocle](https://wiki.openjdk.org/display/OpenJFX/Monocle)
    - This Monocle is actually interesting and can be useful later in-order to run the app on embedded devices.
- I was thinking of using kotlin + [tornadoFX](https://github.com/edvin/tornadofx)
  - Unfortunately it supports only JDK 1.8
- Loader and Auto-update
  - This a critical feature, to allow the program to automatically load new patches and updates if connected to internet
  - Till now I have two options:
    - [update4J](https://github.com/update4j/update4j)
      - Modular system friendly
      - More flexible
      - seems a bit more complicated with the flexiblity it provides
      - But seems like my first option for now
    - [fxLauncher](https://github.com/edvin/fxlauncher/tree/master)
      - a more opinionated solution made by the tornadoFX developers
      - It sync your classes with a repository
      - The UI can be customized and they have native installers
      - But the code base is JDK 1.8
      - Not sure how this will play with modular applications or fetching runtimes for different systems.
- Some libraries that can come in handy:
  - [JMetro](https://pixelduke.com/java-javafx-theme-jmetro/)
    - uses Microsoft Metro skins
    - It is just CSS styling, so, no worries for code changes and modular system support
    - Support controlsFX
    - Will probably use this
    - May share the app with the author?
  - [ControlFx](https://github.com/controlsfx/controlsfx)
    - the defacto javaFX components library
    - Supports module system
    - Supported by JMetro skin
  - [medusa](https://github.com/HanSolo/Medusa)
    - provides gauges and clocks
      - The clock part can be useful in the front page
  - [ribbon](https://pixelduke.com/fxribbon/)
    - Microsoft ribbon implementation for javafx
    - The same guy who made Jmetro, so, good change they are compatible
  - [FormsFX](https://github.com/dlsc-software-consulting-gmbh/FormsFX)
    - can be useful
    - support module system
    - Don't know how it will play with existing styling
  - [MaterialFx](https://github.com/palexdev/MaterialFX)
    - Material themed controls for javaFx
    - Looks really cool
    - But not sure if it just themes or a complete new controls ?
    - it seems to use modular systems at least
  - [tiles](https://github.com/HanSolo/tilesfx)
  - [ReactorFx](https://github.com/shadskii/ReactorFX)
  - [ktfx](https://github.com/hendraanggrian/ktfx)
    - This an extension library to allow using kotlin to develop javaFX apps
    - provides a DSL for building UI's instead of FXML files
    - This can be a good replacement for tornadoFX, as I can use kotlin in addition to java in the project
    - But not yet sure how this will play with the module system, and which versions is supported
    - also, I am currently using maven, but kotlin projects works usually with gradle
    - So, I may need to split the UI project into a separate kotlin project just to use this ?
      - Let's try the FXML approach first
  - [Stream-pi](https://github.com/stream-pi/client)
    - This an interesting javaFx project that is cross-platform ,run on android, uses native builds, and internationalization.
    - So, it provides features that can be useful to check
  - [GluonFX] (https://gluonhq.com/developers/)
    - Gluon implementation for javaFX
    - Provide a plugin for building native executables for different platforms including android and IOS. Check this [example](https://github.com/gluonhq/gluon-samples/tree/master/HelloFX )
    - This can be quite interesting and meets the project target, but we may add it latter.
# Decision

We try using javaFX

# Consequences
- a simple JavaFX app with basic libraries seems to take about 195 MB of ram on a 16 GB laptop
  - This is executed by the IDE, not a packaged application
  - Tested it on packaged application and it is the same
    - also, it seems running the same scene causes memory leak
  - We may need the gluonFX native builds or add options to launch scripts to crub heap memory
- Still experimenting, but may be we can reduce the heap size in the runners.
- The application size of a trivial javaFX + embedded JRE is about 100 MB
  - compressed , it is reduced to less than 47 MB, which is very nice in this age