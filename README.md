1. POM
 There is a page package and we have created Java classes to keep each page of the app as a class Base Page and Search Page were written to serve this purpose. 
2. StepDefinition Package
to define the steps that map to the scenarios in our feature files. Basic Method Implementations. This package contains the code for methods defined in the StepDefinition package.
3. Hooks
These methods are executed before and after each test, which helps set up the environment.
4. Utility Classes
Include Java classes and utilities like FileUtility for file operations, property file handling, and Log4J settings (XML file).
5. Resources
Properties Files: For various configuration settings.
Drivers: Maintains the drivers essential for the browser.
6. Features Package
Within the Feature package, the UI package uses the Gherkin language for defining test scenarios.
Java Method Mapping, Each step in the Gherkin scenario is backed by a Java method, representing the step's implementation.
Configurations for the Cucumber framework are defined here.
